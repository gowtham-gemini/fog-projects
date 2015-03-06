
import com.assistanz.fogpanel.licensemanager.Role
import com.assistanz.fogpanel.licensemanager.User
import com.assistanz.fogpanel.licensemanager.UserRole

class BootStrap {

    def init = { servletContext ->
        
        def adminUser = User.findByUsername("admin@assistanz.com");
        if (!adminUser) {
        
            Calendar calendar = Calendar.getInstance(); 
        
            Role adminAuthority = new Role()
            adminAuthority.authority = "ROLE_ADMIN"
            adminAuthority.save(flush: true)
        
            Role userAuthority = new Role()
            userAuthority.authority = "ROLE_USER"
            userAuthority.save(flush: true)
        
            Role supportAuthority = new Role()
            supportAuthority.authority = "ROLE_SUPPORT"
            supportAuthority.save(flush: true)
        
        
            User admin = new User()
            admin.username = "admin@assistanz.com";
            admin.password = "password"
            admin.enabled = true;
            admin.tokenExpiryDate = calendar.getTime() 
            admin.accountExpired = false;
            admin.accountLocked = false;
            admin.passwordExpired = false;
            admin.firstName = "admin";
            admin.lastName = "admin";
            admin.companyName = "assistanz";
            admin.address1 = "admin";
            admin.address2 = "admin";
            admin.state = "tamilnadu";
            admin.country = "india";
            admin.city = "coimbatore";
            admin.status = "ACTIVE";
            admin.zip = "642001";
            admin.save(flush:true);   
        
        
            UserRole userRole = new UserRole(); 
            userRole.user = admin;
            userRole.role = Role.findByAuthority("ROLE_ADMIN");
            userRole.save(flush:true); 
        }
        
    }
    def destroy = {
    }
}
