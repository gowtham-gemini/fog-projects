
import com.assistanz.app.SecAppRole
import com.assistanz.app.SecAppUser
import com.assistanz.app.SecAppUserSecAppRole
import com.assistanz.app.ConfigLoader
import com.assistanz.app.MailManager
import com.assistanz.app.AppConstant
import com.assistanz.app.Config

class BootStrap {

    def init = { servletContext ->
        def adminRole = new SecAppRole(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new SecAppRole(authority: 'ROLE_USER').save(flush: true)

        def testUser = new SecAppUser(username: 'admin', enabled: true, password: 'admin')
        testUser.save(flush: true)

        SecAppUserSecAppRole.create testUser, adminRole, true
        
        def testUserOne = new SecAppUser(username: 'balaji', enabled: true, password: 'balaji')
        testUserOne.save(flush: true)

        SecAppUserSecAppRole.create testUserOne, userRole, true
        
        // instantiate static instance for ConfigLoader class
        ConfigLoader configLoader = ConfigLoader.getInstance();
        configLoader.loadProperties();
        
        MailManager mailManager = MailManager.getInstance(); 
        mailManager.sendMail("balajim0788@gmail.com", "Config test", "this is send from fog panel mail manager")
        println("Test config for Email")
        

        assert SecAppUser.count() == 2
        assert SecAppRole.count() == 2
        assert SecAppUserSecAppRole.count() == 2
    }
  
    def destroy = {
    }
}