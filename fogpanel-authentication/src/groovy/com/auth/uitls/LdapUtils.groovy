/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.auth.uitls


import com.assistanz.fogpanel.LdapGroup
import com.assistanz.fogpanel.Project
import com.assistanz.fogpanel.User
import javax.naming.Name
import javax.naming.ldap.LdapName
import org.springframework.ldap.core.ContextMapper
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DistinguishedName
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.filter.EqualsFilter
import org.springframework.security.ldap.userdetails.Person
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.codehaus.groovy.grails.commons.GrailsApplication



/**
 *
 * @author deveops
 */
class LdapUtils {
    
    LdapTemplate ldapTemplate 
    private static GrailsApplication app = ApplicationHolder?.getApplication();   
    
    //Get all users 
    //Get admin users
    //Get all groups
    //Get users by groups
    //Get users by projects
    //Get groups by projects           
    //create/update users in localDB
    //create/update groups in localDB
    //create/update projects in localDB
    
    /**
     * getAllUsers
     * 
     * This method returns list all users from the LDAP in the specified search filter 
     * group.Echa user can be identified by their uid.
     * 
     * returns List<Users> 
     */
    def getAllUsers(){                              
        
        //creating filter for search        
        EqualsFilter filter = new 
        EqualsFilter(app?.config.ldap.attributes.objectClass.toString(), 
            app?.config.ldap.attributes.orgPerson.toString());                                   
        
        DistinguishedName dn = new 
        DistinguishedName(app?.config.ldap.search.getAllUsers.toString());                              
                
        def results= ldapTemplate.search(dn, filter.encode(),getContextMapper());            
        
        println(" Results after search " + results)
        
        //TODO Remove Iterating values for consile display.
        for(User user : results){                        
            
//            println(" username/uid : " + user.getUuid())
//            println(" First name: " + user.getFirstName())
//            println(" Last name: " + user.getLastName())
//            println(" Email : " + user.getEmail())
//            println(" ContactNumber : " + user.getContactNumber())            
            
        }      
        
        println(" ******* after all users  ******* ")
        results
        
    }   
    
    /**
     * getAdminUsers 
     * 
     * This method is used to get all the admin users in from the LDAP.
     * 
     * returns List<User>
     */
    def getAdminUsers(){    
        
        //creating filter for search        
        EqualsFilter filter = new 
        EqualsFilter("objectclass", 
            "groupOfUniqueNames");                                   
        
        DistinguishedName dn = new 
        DistinguishedName(app?.config.ldap.search.adminGroup.toString());                              
        
        
        def results= ldapTemplate.search(dn, filter.encode(),
            getGroupContextMapper());                  
        
        println(" Results after search " + results)
        
        //        TODO Remove Iterating values for consile display.
        for(User user : results){                                    
            
            println(" username/uid : " + user.getUuid())
            println(" First name: " + user.getFirstName())
            println(" Last name: " + user.getLastName())
            println(" Email : " + user.getEmail())
            println(" ContactNumber : " + user.getContactNumber())            
            
        }            
        
    }
    
      
    def getAllGroups(){
        
        println(" get all groups")                                        
        //Filter all users        
        EqualsFilter filter = new  EqualsFilter( app?.config.ldap.attributes.objectClass.toString(),
            app?.config.ldap.attributes.uniqueNames.toString());                         
        DistinguishedName dn = new 
        DistinguishedName(app?.config.ldap.search.getAllGroups.toString());                              
        
        def results   = ldapTemplate.search( dn, filter.encode(), getGroupContextMapper());                 
        println("results == " + results)                                
        
        for(LdapGroup  group : results){                                                
            println(group.getMemberDn())
//            println("g name " + group.getName())                        
            
            for(String s  : group.getMemberDn() ){                                                                            
                LdapName ld = new LdapName(s);                
                User user = User.findByUsername(ld.getRdn(ld.size()-1).getValue())                                
//                println(" suffix " + user.uuid)
//                println(" uuid " + user.email)                
                
            }
            
        }                        
        return results        
        
    } 
    
    def getAllProjects(){
        
        println(" **************** get all Projects ************** ")                                
        //Filter all projects       
        EqualsFilter filter = new 
        EqualsFilter(app?.config.ldap.attributes.objectClass.toString(), 
            app?.config.ldap.attributes.uniqueNames.toString());                                    
        
        DistinguishedName dn = 
        new DistinguishedName(app?.config.ldap.search.getAllProjects.toString());                                                     
        
        def results   = 
        ldapTemplate.search( dn, filter.encode(), getProjectContextMapper());                         
        
        println("results == " + results)                
        
        for(Project  project : results){                                                            
            println(project.getGroupDn())
            println(project.getName())                                    
            
            for(String s  : project.getGroupDn() ){                                                                                                            
                
                LdapName ld = new LdapName(s);                                                
                LdapGroup group = findGroupByCn(ld.getRdn(ld.size()-1).getValue())                                                                                
//                println(" group name  " + group ?.name)                               
            }
        }        
        return results         
    } 
    
    def findUserByUid(String uid){         
        
        def dnStr  = "uid=" + uid +","+ 
        app?.config.ldap.search.getAllUsers.toString();                                          
        Name dn = new DistinguishedName(dnStr);                                                                     
        
        User user   = (User) ldapTemplate.lookup( dn,getContextMapper());                                        
//        println( "user name  " + user ?.username)                        
//        println( "user email " + user ?.email)                        
        
        return user
    }

    /**
     * findGroupByCn
     * 
     * This method is used to get the group from LDAP using the group cn.
     * Using the Cn we can identify the group uniquely.
     * 
     * return LdapGroup
     */
    def findGroupByCn(String cn){                                         
         
        def dnStr  = "cn=" + cn +","+ app?.config.ldap.search.getAllGroups.toString();                              
        Name dn = new DistinguishedName(dnStr);                                                                            
        
        LdapGroup group   = (LdapGroup) ldapTemplate.lookup( dn,getGroupContextMapper());                                        
//        println( "group name  " + group ?.name)                        
        return group
        
    }
    
    /**
     *  findProjectByCn
     *  
     *  This method is used to get the project from LDAP using CN.
     *  Using the CN we can uniquely identify the project.
     *  
     *  return Project.
     */
    def findProjectByCn(String cn){                        
        
        def dnStr  = "cn=" + cn +","+ app?.config.ldap.search.getAllProjects.toString();                              
        Name dn = new DistinguishedName(dnStr);                                                                            
        
        Project project   = (Project) ldapTemplate.lookup( dn,getProjectContextMapper());                                        
//        println( "group name  " + project ?.name)                        
        
        return project
        
    }
    
    
    
    /**
     *  getUsersFromGroup
     *  
     *  This method gives the list of "LDAP Users" of the given group if exists
     *  
     *  returns List<String>
     */
    def getUsersFromGroup(LdapGroup group){
        
        List<User> users = new ArrayList<String>()
        
        if(group != null && group.name != null && group.name != "" ){                        
            
            LdapGroup  ldapGroup = findGroupByCn(group.name)                       
//            println(" ldap group " + ldapGroup )
//            println(" ldap group " + ldapGroup ?.getMemberDn()  )
         
            
            for(String s  : ldapGroup ?.getMemberDn() ){                                                                                            
                
                LdapName ld = new LdapName(s);                
                User ldapUser = findUserByUid(ld.getRdn(ld.size()-1).getValue())                                                
                users.add(ldapUser)                
//                println(" suffix " + ldapUser.username)
//                println(" uuid " + ldapUser.email)                                
            }
        }
        
        return users
    }
    
    
    public List<String> getPersonNamesByLastName() {
        
        //Query users with sanjeev as admin
        //      LdapQuery query = query()
        //         .base("ou=users,uid=sanjeev,dc=assistanz,dc=com")
        //         .attributes("uid", "sn")
        //         .where("objectclass").is("person")         
        //         
        //         def result  = ldapTemplate.search(query,getContextMapper())
        //         println("result ======== > " + result )
        //
        //      return result  
    } 
      
    
    
    protected ContextMapper getContextMapper() {
        return new PersonContextMapper();
    }
   
    protected ContextMapper getGroupContextMapper() {
        return new GroupContextMapper();
    }    
    
    protected ContextMapper getProjectContextMapper() {
        return new ProjectContextMapper();
    }    
    
    private static class PersonContextMapper implements ContextMapper {        
        
        public Object mapFromContext(Object ctx) {                                              
            
            DirContextAdapter context = (DirContextAdapter)ctx;                  
            
            User p = new User();            
            p.setUuid(context.getStringAttribute("uid"));
            p.setFirstName(context.getStringAttribute("sn"));         
            p.setLastName(context.getStringAttribute("cn"));                                        
//            p.setEmail(context.getStringAttribute("mail"));                                        
//            p.setContactNumber(context.getStringAttribute("mobile"));                                                                                        
            
            return p;
        }
    }
    
    private static class GroupContextMapper implements ContextMapper {        
        
        public Object mapFromContext(Object ctx) {                                              
                        
            DirContextAdapter context = (DirContextAdapter)ctx;                                                  
            LdapGroup group = new LdapGroup()                            
            group.setMemberDn(context.getStringAttributes(app?.config.ldap.attributes.uniqueMember.toString()));
            group.setName(context.getStringAttribute(app?.config.ldap.attributes.cn.toString()));            
            
            return group
        }
    }
    
    private static class ProjectContextMapper implements ContextMapper {        
        
        public Object mapFromContext(Object ctx) {                                              
                        
            DirContextAdapter context = (DirContextAdapter)ctx;                                                  
            Project project= new Project()                            
            project.setGroupDn(context.getStringAttributes(app?.config.ldap.attributes.uniqueMember.toString()));
            project.setName(context.getStringAttribute(app?.config.ldap.attributes.cn.toString()));            
            
            return project
        }
    }   
    
    def getProjects(){        
         
        println(" **************** get all Projects ************** ")                                
        //Filter all projects       
        EqualsFilter filter = new 
        EqualsFilter(app?.config.ldap.attributes.objectClass.toString(), 
            app?.config.ldap.attributes.uniqueNames.toString() 
//        "groupOfNames"
        );                                    
        
        DistinguishedName dn = 
        new DistinguishedName(app?.config.ldap.search.getAllProjects.toString());                                                     
        println(" dn  == " + dn)                
        def results   = 
        ldapTemplate.search( dn, filter.encode(), getProjectContextMapper());                         
        
        println("results == " + results)                
        
        for(Project  project : results){                                                            
            println(project.getGroupDn())
            println(project.getName())                                    
            
            for(String s  : project.getGroupDn() ){                                                                                                            
                
                LdapName ld = new LdapName(s);                                                
                LdapGroup group = findGroupByCn(ld.getRdn(ld.size()-1).getValue())                                                                                
//                println(" group name  " + group ?.name)                               
            }
        }        
        return results         
    } 	
}

