
package com.assistanz.fogpanel

import com.auth.uitls.LdapUtils
import javax.naming.ldap.LdapName
import grails.transaction.Transactional
/**
 *
 * @author deveops
 */
@Transactional
class GroupService {
    
    LdapUtils ldapUtils
    def springSecurityService
    
       
    /**
     *  syncLdapGroups
     *  
     *  This method is used to sync LDAP syncLdapGroups with Faas .
     *  If the group doesnot exist it will create a new entry if the user exist 
     *  the data will be updated.Here the group is identified by the CN of the 
     *  LDAP group.
     */
    def syncLdapGroups(){        
        
        try{
            
            println("in sync groups")
            //get the users from LDAP        
            List<LdapGroup> groups = ldapUtils.getAllGroups()        
            LdapGroup appGroup
            List<User> users 
            
            println("in sync groups" + groups)
            //Iterate the list 
            for(LdapGroup ldapGroup : groups ){                                            
                
                //In the iteration check if the group exist by CN
                //If group exist update else create a new entry                
                //Update the the group user table updating/creating the groups                
                if(LdapGroup.findByName(ldapGroup.name) != null ){                    
                    appGroup  =  LdapGroup.findByName(ldapGroup.name)                                                          
                } else {                                        
                    appGroup   = new LdapGroup()                                                            
                }                                                                
                appGroup = this.copyLdap(ldapGroup ,appGroup)                                                            
                appGroup.save(flush:true)                                   
                
                users = getUsersByGroup(appGroup)                    
                for(User user : users){                                                
                    //Check if the user has role already
                    //if role present then do nothing else create role                                                                         
                    LdapGroupUser  lgu = LdapGroupUser.get(user.id,appGroup.id)                                                                        
                    if(lgu == null){                                                                                                          
                        LdapGroupUser.create(user,appGroup)                            
                    } 
                    //TODO need to write for delete operation                        
                }                
            }
            
            println(" groups saved successfully ")
            
        } catch (Exception ex){            
            
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;
        }
        
    }
    
    /**
     *  copy
     *  
     *  This method copies the ldap users with the existing users in the 
     *  database or  new users.
     *  
     *  returns appUser.
     */
    LdapGroup copyLdap(LdapGroup ldapGroup,LdapGroup appGroup){       
        try{           
            
            
            //TODO need to add more fields according to each LDAP server. 
            appGroup.name = ldapGroup.name                         
            appGroup.memberDn = ldapGroup.memberDn                         
            println("  copy approup " + appGroup.id  +" appGroup.name " + appGroup.name  )
            
            return appGroup 
                                            
            
        }catch(Exception ex){
            println(" Exception while syncing ")
            ex.printStackTrace();
            throw ex;            
        }
    }            

    /**
     *  getUsersByGroup
     *  
     *  Get the users from the app if the user 
     */
    def getUsersByGroup(LdapGroup appGroup){              
        
        List<User> appUsers = new ArrayList<User> ()
        try{            
            
            for(String s  : appGroup ?.getMemberDn() ){                                                                                                      
                
                println(" s " + s )
                LdapName ld = new LdapName(s);                
                User ldapUser = User.findByUuid(ld.getRdn(ld.size()-1).getValue())                                                
                println(" rdn " + ld.getRdn(ld.size()-1).getValue() )
                println(" ldapUser " + ldapUser )
                //TODO if the user is null need to create the user 
                if(ldapUser != null){
                    appUsers.add(ldapUser)                    
                    println(" suffix " + ldapUser.username)
                    println(" uuid " + ldapUser.email)                                
                } else if(ld.getRdn(ld.size()-1).getValue().equals("admin") ){                    
                    
                    User user = User.findByUsername(ld.getRdn(ld.size()-1).getValue())                                                                    
                    if(user != null ){
                        user.uuid = "admin"
                        appUsers.add(user)                        
                    }                    
                }
            }    
        } catch(Exception e ){
            println("Errorin getting user from group :" + e)
        }        
        
        return appUsers
    }
    

    def getList() {
        try {
            def groups = LdapGroup.findAll()
            ArrayList list = new ArrayList()
            for(def group : groups) {
                HashMap map = new HashMap()
                map.put("id",group.id)
                map.put("name",group.name)
                list.add(map)
            }
            
            return list
            
        } catch(Exception ex){
            throw ex
        }
       
    }
    
    def getUserDetails(id) {
        
        try {
            def group = LdapGroup.findById(id)
            def users = LdapGroupUser.findAllWhere(group:group)
            ArrayList list = new ArrayList()
            for(def user : users) {
                def eachUser = User.findById(user.user.id)
                HashMap map = new HashMap()
                map.put("id",eachUser.id)
                map.put("name",eachUser.username)
                map.put("firstName",eachUser.firstName)
                map.put("lastName",eachUser.lastName)
                map.put("email",eachUser.email)
                map.put("accountLocked",eachUser.accountLocked)
                map.put("enabled",eachUser.enabled)
                map.put("groupName",group.name)
                list.add(map)
            }
            return list
            
        } catch(Exception ex){
            throw ex
        }
    }
    
    def getGroupsByUser(User user) {              
//         def user = springSecurityService.currentUser      
            
        
            User appUser  = User.findById(2)                      
            
        println(" in group by user ")
        def groups = new ArrayList<LdapGroup>();
        def groupCriteria = LdapGroupUser.createCriteria()                        
        def groupList = groupCriteria.list {
            eq("user", appUser)                               
        }                    
        println(" froup list "+ groupList)
        
//        for(LDApGroupUser)
    }
}


