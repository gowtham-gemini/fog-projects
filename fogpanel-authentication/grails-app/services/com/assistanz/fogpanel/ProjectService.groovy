package com.assistanz.fogpanel

import com.auth.uitls.LdapUtils
import grails.transaction.Transactional
import javax.naming.ldap.LdapName
import org.springframework.security.core.authority.GrantedAuthorityImpl

@Transactional
class ProjectService {
    
    LdapUtils ldapUtils
        
    def springSecurityService;
    GroupService groupService   

    /**
     * getGroupByProject
     * 
     * Get the list of groups by Project 
     * 
     * return List<LdapGroup>
     */
    def getGroupsByProject(Project appProject){              
        
        List<LdapGroup> appgroups = new ArrayList<LdapGroup> ()
        try{            
            
            for(String s  : appProject ?.getGroupDn() ){                                                                                                      
                
                LdapName ld = new LdapName(s);                
                LdapGroup ldapGroup = LdapGroup.findByName(ld.getRdn(ld.size()-1).getValue())                                                
                //TODO if the user is null need to create the user 
                if(ldapGroup != null){
                    appgroups.add(ldapGroup)                    
                    println(" suffix " + ldapGroup.name)                    
                }
            }    
        } catch(Exception e ){
            println("Errorin getting user from group :" + e)
        }        
        
        return appgroups
    }    
    
    /**
     *  syncLdapProject
     *  
     *  This method used to sync project from ldap to faas module.
     *  
     */
    def syncLdapProject(){        
        
        try{
            
            println("in sync groups")
            //get the users from LDAP        
            List<Project> projects = ldapUtils.getAllProjects()        
            
            Project appProject
            
            println("in sync groups" + projects)
            //Iterate the list 
            for(Project project : projects ){                                                                                            
                
                //In the iteration check if the group exist by CN
                //If group exist update else create a new entry                
                //Update the the group user table updating/creating the groups                                                                
                if(Project.findByName(project.name) != null ){                    
                    appProject  =  Project.findByName(project.name)                                                          
                } else {                    
                    appProject  =  new Project()           
                }                       
                
                appProject = this.copy(project ,appProject)                                                                                                                                                    
                //TODO remove
                //                appProject.cloudEngine = CloudEngine.findById(1)
                
                if (!appProject.save(flush:true)) {
                    appProject.errors.allErrors.each { println it }
                }
                
                List<LdapGroup> groups = getGroupsByProject(appProject)
                
                for(LdapGroup group : groups){                                                                                                                                            
                    
                    ProjectLdapGroup projectLdapGroup  = ProjectLdapGroup.get(appProject.id, group.id )                    
                    if(projectLdapGroup == null ){                        
                        println(" create in  ldapGroup")
                        ProjectLdapGroup.create(appProject,group)                                                                
                    }                    
                }                                                   
            }        
        } catch (Exception ex){            
            
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;
        }
        
    }    
    
    Project copy(Project ldapProject,Project appProject){               
        
        try{
            //TODO need to add more fields according to each LDAP server. 
            appProject.name = ldapProject.name                         
            appProject.groupDn = ldapProject.groupDn                                                             
            return appProject                                                         
            
        }catch(Exception ex){
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;            
        }
    }
    
    def getProjectList() {
        
        def projects = Project.findAll()
        ArrayList list = new ArrayList()
        for(def project : projects) {
            HashMap map = new HashMap()
            map.put('id',project.id)
            map.put('name',project.name)
            list.add(map)
        }
        
        return list
    }
    
    def getCloudEngines() {
        def cloudEngines = CloudEngine.findAll()
        ArrayList list = new ArrayList()
        for(def cloudEngine : cloudEngines) {
            HashMap map = new HashMap()
            map.put('id',cloudEngine.id)
            map.put('name',cloudEngine.name)
            map.put('status',cloudEngine.status)
            map.put('type',cloudEngine.type)
            map.put('url',cloudEngine.url)
            list.add(map)
        }
        
        return list
    }
    
    def assignCloudEngine(data) {
        try {
            
            ArrayList list = new ArrayList()
            def project = Project.findById(data.projectId)
            for (def cloudEngines : data.cloudEngine) {
                def cloudEngineProjects = new CloudEngineProjects()
                def cloudEngine = CloudEngine.findById(cloudEngines)
                cloudEngineProjects.project = project
                cloudEngineProjects.cloudEngine = cloudEngine
                cloudEngineProjects.save(flush:true)
            }
            list.add("OK")
            return list
            
        }catch (Exception ex){
            throw ex
        }
    } 
    
    def syncProject(){        
        
        try{            
            
            println("in sync groups")
            //get the users from LDAP        
            List<Project> projects = ldapUtils.getAllProjects()                    
            Project appProject            
            println("in sync groups" + projects)
            //Iterate the list 
            
            for(Project project : projects ){                                                                                                            
                //In the iteration check if the group exist by CN
                //If group exist update else create a new entry                
                //Update the the group user table updating/creating the groups                                                                
                if(Project.findByName(project.name) != null ){                    
                    appProject  =  Project.findByName(project.name)                                                          
                } else {                    
                    appProject  =  new Project()           
                }                       
                
                appProject = this.copy(project ,appProject)                                                                                                                                                    
                //TODO remove
                //                appProject.cloudEngine = CloudEngine.findById(1)
                
                if (!appProject.save(flush:true)) {
                    appProject.errors.allErrors.each { println it }
                }
                
                List<LdapGroup> groups = getGroupsByProject(appProject)
                
                for(LdapGroup group : groups){                                                                                                                                            
                    
                    ProjectLdapGroup projectLdapGroup  = ProjectLdapGroup.get(appProject.id, group.id )                    
                    if(projectLdapGroup == null ){                        
                        println(" create in  ldapGroup")
                        ProjectLdapGroup.create(appProject,group)                                                                
                    }                    
                }                                                   
            }        
        } catch (Exception ex){            
            
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;
        }
        
    }    
    
    def getAssignedProjects(){
        
        def user = springSecurityService.currentUser
        def result
        println(" List admin  " + user)
        if(user != null ){
            User appUser  = User.findByUsername(user.username)                      
            
            def authorities = appUser ?.authorities.collect { new GrantedAuthorityImpl(it.authority) }
            println(" authorities " +  authorities)
            if(authorities != null){
                
                boolean isAdmin =true ;
                
                for(def String : authorities){                    
                    if(authorities.equals("ROLE_ADMIN")){
                        isAdmin =true
                    }                    
                }                                
                println(" List admin  " + isAdmin)
                if(isAdmin){                    
                    result = CloudEngineProjects.findAll()                    
                    //                    println("results  ===== > " + result)                                    
                  
                }else {                    
                    def projects
                    def ldapGroups = groupService.getGroupsByUser(appUser)                                                            
                    for(LdapGroup ldapGroup : ldapGroups ){                        
                        def projectGroups = ProjectLdapGroup.findByGroup()
                        if(projectGroups != null ){
                            projects.add(projectGroups.project)
                        }                        
                    }                    
                    
                    if(projects != null ){                        
                        for(Project project : projects ){                        
                            
                            result.add ( ClouEngineProjects.findByProject(project) ) 
                            
                            //                            def projectCriteria = ClouEngineProjects.createCriteria()
                            //                            def projectList = projectCriteria.list {
                            //                                eq("p", appGroup)                               
                            //                            }                                 
                        }                                                                   
                    }
                }                
            }                               
            
        }   
        
        
          
                
        ArrayList<ArrayList<String>> projectList = new ArrayList<ArrayList<String>>();            
                
        for(int i=0; i < result.size(); i++) {                    
            def item = result[i];                     
            HashMap<String,String> userItem = new HashMap<String,String>();
            userItem.put("id", item.project.id);
            userItem.put("projectName", item.project.name);
            userItem.put("engineId", item.cloudEngine.id);
            userItem.put("engineType", item.cloudEngine.type);
            userItem.put("engineUrl", item.cloudEngine.url);           
                    
            projectList.add(userItem);
        }        
        println(" List admin  " + projectList)
        return projectList
        
    }
    
    def getProjectsUsers(User user ){
        
        def project 
        def projectCriteria = ClouEngineProjects.createCriteria()            
            
        def projectList = projectCriteria.list {
            eq("p", appGroup)                               
        }         
    }   
    
}
