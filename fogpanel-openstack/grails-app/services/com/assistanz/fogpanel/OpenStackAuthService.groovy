package com.assistanz.fogpanel

import grails.transaction.Transactional
import org.openstack4j.api.OSClient;
import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.openstack.OpenStackServer
import grails.transaction.Transactional

@Transactional
class OpenStackAuthService {

    def springSecurityService
    
    // authenticate with OpenStack API
    def authenticate() {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();
        Properties props = configLoader.getProperties();
        
        def user = springSecurityService?.currentUser;
        
        if(user && user.username != "admin") {
            
            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), user.username, Util.getCurrentSessionPassword(), Util.getCurrentSessionDomainName());
            OSClient os = server.authenticate();
            
//            os.useRegion("RegionOne");
            
            return os;
        } else {

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
            OSClient os = server.authenticate();
            
//            os.useRegion("RegionOne");
            
            return os;
        }
    }
}
