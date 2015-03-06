package com.assistanz.fogpanel

import org.openstack4j.model.common.DLPayload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.storage.block.options.DownloadOptions;
import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.api.OSClient;
import com.assistanz.openstack.OpenStackServer
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import java.io.StringWriter;

class ObjectStoreController {
    
    def springSecurityService
    OpenStackAuthService openStackAuthService

    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER']) 
    def success() {
        println("success---")
        render(view: "success")
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def error() {
        println("error---")
        render(view: "error")
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def noFileSelected() {
        render(view: "noFileSelected")
    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def upload() {
        
        def file = request.getFile('objectFile')
        byte [] byteArr=file.getBytes();
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        def fileName = request.getParameter('uploadObjectFileName')
        def containerId = request.getParameter('selectedContainerId')
        def pathName = request.getParameter('selectedPathName')
           
        def checkSum;

        if(file.empty) {
            flash.message = "File cannot be empty"
        } else {
//            ObjectStoreService.uploadObject(file, fileName, containerId, pathName);
            OSClient os = openStackAuthService.authenticate(); 

            def id = Long.parseLong(containerId);

            def container = Container.findWhere(id: id, deleted: false)
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", file.originalFilename);
            
            //set file name for update
            if(params.editedObjectName) {
                fileName = params.editedObjectName;
            } 
   
            if(pathName) {
                //remove slash
                int position = pathName.lastIndexOf("/");
                int length = pathName.length();
                if(position == length-1) {
                      pathName = pathName.substring(0, position)
                }
                checkSum = os.objectStorage().objects().put(container.name, fileName, Payloads.create(inputStream), ObjectPutOptions.create().path(pathName).contentType(file.contentType).metadata(map));
            } else {
                checkSum = os.objectStorage().objects().put(container.name, fileName, Payloads.create(inputStream), ObjectPutOptions.create().contentType(file.contentType).metadata(map));
            }
            
            println("checkSum"+checkSum)
            
        }
        
        //Redirect respone url depending on result 
        if(file.originalFilename) { 
            if(checkSum) {
                redirect(controller: "objectStore", action: "success")
            } else {
                redirect(controller: "objectStore", action: "error")
            }
        } else {
             redirect(controller: "objectStore", action: "noFileSelected")
        }
        
          

    }
    
    @Secured(['ROLE_ADMIN', 'ROLE_FREE_USER', 'ROLE_PAID_USER'])
    def download(params) {
        
        def user = springSecurityService.currentUser; 
            
        OSClient os = openStackAuthService.authenticate(); 
        
        def id = Long.parseLong(params.containerId);
            
        def container = Container.findWhere(id: id, deleted: false)
        
        DLPayload dLPayload =  os.objectStorage().objects().download(ObjectLocation.create(container.name, params.pathName), DownloadOptions.create());
        
        InputStream input = dLPayload.getInputStream();
        
        //Get header detail of current object
        Map<String, String> metaData = os.objectStorage().objects().getMetadata(container.name, params.pathName);

        String origionalFileName = metaData.get(GeneralConstants.OBJECT_METADATA_NAME)
        String contentType = metaData.get(GeneralConstants.CONTENT_TYPE)
        
        println("input stream "+input);
        
        int position = origionalFileName.lastIndexOf(".");
            
        String ext = origionalFileName.substring(position);
        
        def fileName = params.objectName + ext

        System.out.println("ext: "+ext);
        
        
//        StringWriter writer = new StringWriter();
//        IOUtils.copy(input, writer, "UTF-8");
//        String theString = writer.toString();
//        System.out.println(theString);
        
       
        response.setContentType(contentType)
        response.setHeader("Content-Disposition", "Attachment;Filename="+fileName+"")
        InputStream is = dLPayload.getInputStream();
        byte[] buffer=new byte[4096];
        char c;
                  
        def outputStream = response.getOutputStream()

          
            int len;
            while ((len = is.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            outputStream.flush()
            outputStream.close()
//            fileInputStream.close()
       
    }
       
    
}
