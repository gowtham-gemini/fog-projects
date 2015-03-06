package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.object.SwiftContainer;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.openstack.identity.domain.v3.KeystoneTokenV3;
import org.openstack4j.model.identity.v3.Catalog;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.CreateUpdateContainerOptions;
import grails.plugin.springsecurity.SpringSecurityService;
import org.openstack4j.model.storage.object.options.ObjectListOptions;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.openstack4j.model.common.DLPayload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.storage.block.options.DownloadOptions;
import org.openstack4j.api.types.Facing;
import org.openstack4j.model.identity.v3.EndpointV3;
import org.openstack4j.model.common.Payloads;
import com.assistanz.cloud.config.ConfigLoader
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import org.springframework.context.MessageSource

@Transactional
class ObjectStoreService {
    
    def springSecurityService;
    OpenStackAuthService openStackAuthService
    MessageSource messageSource

    def containerList() {
        
        try {
            ArrayList<ArrayList<String>> containerList = new ArrayList<ArrayList<String>>(); 
            
            def user = springSecurityService.currentUser; 
  
            def projectId = user.account.uuid;
                        
            def containers = Container.withCriteria {               
                eq("deleted", false)
                eq("account", user.account)
            }
              
            for(def container : containers) {
              
                def sizeInKb = Math.ceil(container.size * 100d) / (100d * 1024);
                int round = (int) Math.round(sizeInKb*100);
                double totalSize = round / 100.0;

                HashMap item = new HashMap();
                item.put("name", container.name)
                item.put("containerId", container.id)
                item.put("access", container.access)
                item.put("objectCount", container.objectCount)
                item.put("size", totalSize)
                item.put("publicURL", container.publicURL)
                item.put("createdAt", DateFormat.getDateTimeInstance().format(container.createdAt))
                containerList.add(item)
                
            }
            
            return containerList;
            
            
        } catch(Exception e) {
            throw e;
        }

    }
    
    def viewContainer(id) {
        
        try {
            
            def user = springSecurityService.currentUser; 
  
            def projectId = user.account.uuid;
            
            ArrayList<ArrayList<String>> containerDetails = new ArrayList<ArrayList<String>>();
            
            def container = Container.findWhere(id: Long.parseLong(id), deleted: false)
            
            def sizeInKb = Math.ceil(container.size * 100d) / (100d * 1024);
            int round = (int) Math.round(sizeInKb*100);
            double totalSize = round / 100.0;
            
            HashMap item = new HashMap();
            item.put("name", container.name)
            item.put("containerId", container.id)
            item.put("access", container.access)
            item.put("objectCount", container.objectCount)
            item.put("size", totalSize)
            item.put("publicURL", container.publicURL + "/" + container.name)
            item.put("createdAt", DateFormat.getDateTimeInstance().format(container.createdAt))
            item.put("accountName", container.account.accountIdentifier)
            containerDetails.add(item)
            
            return containerDetails;
            
            
        }  catch(Exception e) {
            throw e;
        }
    }
    
    def objectList(containerId, objectName) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();  
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate();  
  
            def projectId = user.account.uuid;
            
            def id = Long.parseLong(containerId);
            
            def container = Container.get(id);
            
            List<? extends SwiftObject> swiftObjects;
            List<? extends SwiftObject> allSwiftObjects;
            
            def containerTotalSize = 0;
            def containerTotalCount = 0;
            
            def containerSize = 0;
            def containerCount = 0;
                 
            //Get object of its object list
            if(objectName) {
                
                swiftObjects = os.objectStorage().objects().list(container.name, ObjectListOptions.create().path(objectName));
            
                allSwiftObjects = os.objectStorage().objects().list(container.name);

                for(SwiftObject allSwiftObject: allSwiftObjects) {

                    containerTotalSize += allSwiftObject.getSizeInBytes();
                    containerTotalCount++;
                }
                
            } else {
                
                swiftObjects = os.objectStorage().objects().list(container.name);
            }
            
            
            for(SwiftObject swiftObject: swiftObjects) {
                
                def sizeInKb = Math.ceil(swiftObject.getSizeInBytes() * 100d) / (100d * 1024);
                int round = (int) Math.round(sizeInKb*100);
                double totalSize = round / 100.0;
                
                containerSize += swiftObject.getSizeInBytes();
                containerCount++;
                
                //Remove '/' charctor for folder name
                def name = swiftObject.getName();
                
                def swiftObjectName = swiftObject.getName();               
                
                 if(name.indexOf("/") != -1) {
                    
                    if(objectName) {
                        String[] split = name.split("/");
                        int splitSize = split.length;

                        name = split[splitSize-1]; 
                    } else {
                        int position = name.indexOf("/");
                        name = name.substring(0,position)
                    }
                    
 
                }
                
//                println("name2: "+name)
                
                HashMap item = new HashMap();
                //Get root child and additional files
                if((swiftObjectName.split("/").size() == 1) || objectName) {
//                    println("final name: "+name)
                    item.put("name", name)
                    item.put("pathName", swiftObject.getName())
                    item.put("containerName", container.name)
                    item.put("containerId", id)
                    item.put("size", totalSize)
                    item.put("mimeType", swiftObject.getMimeType())
                    item.put("lastModified", DateFormat.getDateTimeInstance().format(swiftObject.getLastModified()))
                    item.put("directory", swiftObject.isDirectory())
                    item.put("tag", swiftObject.getETag())
                    response.add(item);
                    
                }
                
            }
            
            if(objectName) {
                 //Total container size and object count 
                container.size = containerTotalSize
                container.objectCount = containerTotalCount
                container.save(flush: true)
            } else {
                 //Total container size and object count 
                container.size = containerSize
                container.objectCount = containerCount
                container.save(flush: true)
            }
            
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
     def viewObject(containerId, objectName, pathName) {
        try {
                        
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
                        
            def container = Container.findWhere(id: Long.parseLong(containerId), deleted: false)
     
            SwiftObject swiftObject = os.objectStorage().objects().get(container.name, pathName);
                        
            def sizeInKb = Math.ceil(swiftObject.getSizeInBytes() * 100d) / (100d * 1024);
            int round = (int) Math.round(sizeInKb*100);
            double totalSize = round / 100.0;
            
            println("date: "+DateFormat.getDateTimeInstance().format(swiftObject.getLastModified()))
             println("date: "+swiftObject.getLastModified())
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("name", objectName)
            item.put("pathName", swiftObject.getName())
            item.put("containerName", container.name)
            item.put("containerId", container.id)
            item.put("size", totalSize)
            item.put("mimeType", swiftObject.getMetadata().get("Content-Type"))
            item.put("lastModified", DateFormat.getDateTimeInstance().format(swiftObject.getLastModified()))
            item.put("directory", swiftObject.isDirectory())
            item.put("hashValue", swiftObject.getMetadata().get("Etag"))
            response.add(item);
            
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def createContainer(requestData) {
        
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
            
            String name = requestData.name;
            
            def criteria = Container.createCriteria();             

            def isOldContainer =  false;
            
            def oldContainers = criteria {
                
                  eq('name', requestData.name)
                  eq('account', user.account)
                  eq('deleted', false)                
            }
            
            for(def oldContainerr : oldContainers) {
                if(oldContainerr.name.equals(name)) {
                    isOldContainer = true;
                }
            }
            
            println("results: "+oldContainers)
            println("isOldContainer: "+isOldContainer)
            if(isOldContainer) {
                //Validate with case sensitive
//                if(requestData.name.equals(oldContainer.name)) {
                    Object[] containerArgs = new Object[2];
                    containerArgs[0] = user.account.accountIdentifier
                    Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                    item.put("result", GeneralConstants.ALREADY_EXISTS);
                    item.put("message", messageSource.getMessage('common.container.alreadyexists.in.account', containerArgs, new Locale(defaultLanguage.value)));
                    response.add(item)

//                } else {
//                    
//                    ActionResponse actionResponse = containerCreation(requestData, os); 
//                
//                    if(actionResponse.isSuccess() == true) {
//
//                       item.put("result", GeneralConstants.RESULT_SUCCESS);
//                       response.add(item)
//
//                    } else {
//                        item.put("result", GeneralConstants.RESULT_FAILURE);
//                        item.put("message", actionResponse.getFault());
//                        response.add(item)
//                    }
//                    
//                    
//                }
            }
             else {
                 
                ActionResponse actionResponse = containerCreation(requestData, os); 
                
                if(actionResponse.isSuccess() == true) {

                   item.put("result", GeneralConstants.RESULT_SUCCESS);
                   response.add(item)

                } else {
                    item.put("result", GeneralConstants.RESULT_FAILURE);
                    item.put("message", actionResponse.getFault());
                    response.add(item)
                }
                
                
            }
                         
            
            return response;
        } catch(Exception e) {
            throw e;
        }
    }
    
    def containerCreation(requestData, os) {
        
        ActionResponse actionResponse; 
        
        def user = springSecurityService.currentUser; 
            
        def projectId = user.account.uuid;

        def objectStoreUrl;

        //check container access private or public
        if(requestData.access == "private") {
            actionResponse = os.objectStorage().containers().create(requestData.name);
        } else {

            actionResponse = os.objectStorage().containers().create(requestData.name, CreateUpdateContainerOptions.create().accessAnybodyRead());

        }
        println("actionResponse: "+actionResponse.toString())
        if(actionResponse.isSuccess() == true) {

           def container = new Container();
           container.name = requestData.name;
           container.access = requestData.access;

           //If container access is public to set public URL for object access
           if(requestData.access == "public") {

               KeystoneTokenV3 token = (KeystoneTokenV3) os.getAccess().getToken();

               for (Catalog catalog : token.getCatalog()) {
                   if(catalog.getType().equals("object-store")) {

                       for(EndpointV3 endpointV3: catalog.getEndpoints()) {
                           if(endpointV3.getInterface() == Facing.PUBLIC) {
                                System.out.println("EndPoint url: "+endpointV3.getURL());
                                objectStoreUrl =  endpointV3.getURL();
                           }

                       }

                   }
               }

               container.publicURL = objectStoreUrl;
           }
           container.account = user.account;
           container.createdAt = new Date();
           List<? extends SwiftContainer> swiftContainers = os.objectStorage().containers().list();

           for(SwiftContainer swiftContainer : swiftContainers) {
               if(swiftContainer.getName().equals(requestData.name)) {
                   container.size = swiftContainer.getTotalSize()
                   container.objectCount = swiftContainer.getObjectCount()
               }
           }

           container.save(flush: true);

           if (!container.save()) {
               container.errors.allErrors.each { println it }
           } 
        }
        
        return actionResponse;
    }
    
    def addPseudoFolder(requestData) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate();
            
            def name = requestData.name;
            def path;

            def position = name.lastIndexOf( '/' );
            def length = name.length();
           
//            //Check if '/ ' appended with psuedo name or not.
            if(position == length-1) {
                path = name ;
            } else {
                path = name + "/";
            }
                       
            String hashValue = os.objectStorage().containers().createPath(requestData.containerName, path.trim());
            
            println("hashValue :"+hashValue)
            
            if(hashValue) {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)
            }
////            
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def updateContainerAccess(requestData) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            ActionResponse actionResponse;
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
             
            def objectStoreUrl;
            
            def id = Long.parseLong(requestData.id);
            
            def container = Container.findWhere(deleted: false, id: id);
             
             //change container access private or public
             if(container.access == "private") {
                 //Change access to public
                 actionResponse = os.objectStorage().containers().create(container.name, CreateUpdateContainerOptions.create().accessAnybodyRead());
             } else {
                 //Change access to private
                 actionResponse = os.objectStorage().containers().create(container.name, CreateUpdateContainerOptions.create().accessRead(".r")
                                  .accessWrite(".w"));
                 
             }
              
            //Set value to DB
             if(actionResponse.isSuccess() == true) {
                 
                if(container.access == "private") {
                    container.access = "public";
                    
                    //If container access to be changed as public. Then set public URL for object access
                    KeystoneTokenV3 token = (KeystoneTokenV3) os.getAccess().getToken();
                    
                    for (Catalog catalog : token.getCatalog()) {
                        if(catalog.getType().equals("object-store")) {
                            
                            for(EndpointV3 endpointV3: catalog.getEndpoints()) {
                                if(endpointV3.getInterface() == Facing.PUBLIC) {
                                     System.out.println("EndPoint url: "+endpointV3.getURL());
                                     objectStoreUrl =  endpointV3.getURL();
                                }

                            }

                        }
                    }
                    
                    container.publicURL = objectStoreUrl;
                } else {
                    container.access = "private";
                    container.publicURL = null;
                }

                
                if(container.access == "private") {
                    
                   
                }
                container.updatedAt = new Date();
                container.save(flush: true);
                
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)
                
             } else {
                 item.put("result", GeneralConstants.RESULT_FAILURE);
                 item.put("message", actionResponse.getFault());
                 response.add(item)
             }
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def uploadObject(file, fileName, containerId, pathName) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
            
            def id = Long.parseLong(containerId);
            
            def container = Container.findWhere(id: id, deleted: false)
            
//            File file = new File(requestData.uploadUrl);
            String checkSum;
                        
            if(pathName) {
                checkSum = os.objectStorage().objects().put(container.name, fileName, Payloads.create(file), ObjectPutOptions.create().path(pathName));
            } else {
                checkSum = os.objectStorage().objects().put(container.name, fileName, Payloads.create(file));
            }
//            
            if(checkSum) {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)
            } 
            
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def copyObject(requestData) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
            
            def sourceContainerId = Long.parseLong(requestData.sourceContainerId);
            def destinationConatinerId = new Long(requestData.destinationConatinerId);
            
            def sourceContainer = Container.findWhere(id: sourceContainerId, deleted: false)
            def destinationContainer = Container.findWhere(id: destinationConatinerId, deleted: false)
            
            def destinationPath = requestData.destinationPath ;
            def sourcePath = requestData.sourcePathName;
            
            def sourceObjectName;
            def destinationObjectName;
            
            //Get src object name
//            if(requestData.sourceObjectName) {
//                println("Slash found" + isSlashFound(sourcePath))
// 
//                if(isSlashFound(sourcePath) == true){
//                     sourceObjectName = sourcePath + requestData.sourceObjectName
//                } else {
//                    sourceObjectName = sourcePath + "/" + requestData.sourceObjectName
//                }
//               
//            } else {
//                sourceObjectName = requestData.sourceObjectName
//            }
            
            
            //Get dest object name
            if(requestData.destinationPath) {
 
                if(isSlashFound(destinationPath) == true){
                     destinationObjectName = destinationPath + requestData.destinationObjectName
                } else {
                    destinationObjectName = destinationPath + "/" + requestData.destinationObjectName
                }
               
            } else {
                destinationObjectName = requestData.destinationObjectName
            }
            
            println("sourceObjectName: "+sourceObjectName)
            println("destinationObjectName: "+destinationObjectName)
            
            String checkSum = os.objectStorage().objects().copy(ObjectLocation.create(sourceContainer.name, sourcePath), ObjectLocation.create(destinationContainer.name, destinationObjectName));
           
            if(checkSum) {
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)
            } else {
                item.put("result", GeneralConstants.RESULT_FAILURE);
                response.add(item)
            }
             
            return response;
        } catch(Exception e) {
            throw e;
        }
    }
    
    def isSlashFound(name) {
        def position = name.lastIndexOf( '/' );
        def length = name.length();
           
//            //Check if '/ ' appended with psuedo name or not.
            if(position == length-1) {
               return true;
            } else {
                return false;
            }
    }
    
    def deleteContainer(requestData) {
        try {
                        
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
            
            def id = Long.parseLong(requestData.containerId);
            
            def container = Container.findWhere(id: id, deleted: false)
                        
            ActionResponse actionResponse = os.objectStorage().containers().delete(container.name);
            
             if(actionResponse.isSuccess() == true) {
                 
                 container.deleted = true;
                 container.deletedAt = new Date();
                 container.save(flush: true);
                 
                 item.put("result", GeneralConstants.RESULT_SUCCESS);
                 response.add(item)
             } else {
                 item.put("result", GeneralConstants.RESULT_FAILURE);
                 item.put("message", actionResponse.getFault());
                 response.add(item)
             }
             
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def deleteObject(requestData) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def user = springSecurityService.currentUser; 
            
            OSClient os = openStackAuthService.authenticate(); 
  
            def projectId = user.account.uuid;
            
            def id = Long.parseLong(requestData.containerId);
            
            def container = Container.findWhere(id: id, deleted: false)
            def pathName = requestData.pathName;
            
            def position = pathName.lastIndexOf( '/' );
            def length = pathName.length();
            def fullPathName;
            
           
//            //Check if '/ ' appended with path.
//            if(position == length-1) {
//                fullPathName = pathName ;
//            } else {
//                fullPathName = pathName +  "/" ;
//            }
   
            
            ActionResponse actionResponse = os.objectStorage().objects().delete(container.name, pathName);
            
            if(actionResponse.isSuccess() == true) { 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                response.add(item)
            } else {
                 item.put("result", GeneralConstants.RESULT_FAILURE);
                 item.put("message", actionResponse.getFault());
                 response.add(item)
             }
             
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
}
