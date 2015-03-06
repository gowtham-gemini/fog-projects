
import org.zenoss.client.ZenosServicesRegistry;
import org.zenoss.client.api.DeviceService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.GraphDef;
import org.zenoss.client.modal.service.DeviceServiceImpl;


/**
 * Test Class
 * 
 * @author Nandhini
 */
public class TestServiceConnection {
   
    public static void main(String args[]) throws ApplicationException {
        
        String ZENOSS_INSTANCE = "http://192.168.1.108:8080";
        String ZENOSS_USERNAME = "sujai";
        String ZENOSS_PASSWORD = "sujai";
        
        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient(ZENOSS_INSTANCE, ZENOSS_USERNAME, ZENOSS_PASSWORD);
        ZenosServicesRegistry registry = ZenosServicesRegistry.getInstance(restServiceHandler);
        
        DeviceService deviceService = (DeviceService)registry.getService(DeviceServiceImpl.class);
        
        RestServiceHandler restServiceHandler1 = RestServiceFactory.getHttpClient(ZENOSS_INSTANCE, ZENOSS_USERNAME, ZENOSS_PASSWORD);
        ZenosServicesRegistry registry1 = ZenosServicesRegistry.getInstance(restServiceHandler);
        
        DeviceService deviceService1 = (DeviceService)registry.getService(DeviceServiceImpl.class);
        
        System.out.println("Created Services");
        
        Device  device = new Device.Builder("/zport/dmd/Devices/Server/Linux/devices/192.168.1.200")
                .build(); 
        
        for(GraphDef def: deviceService.getGraphDefs(device)) {   
            System.out.println(def.getType());
        }        
        
        for(GraphDef def1: deviceService1.getGraphDefs(device)) {   
            System.out.println(def1.getType());
        }        
        
        
        
    }
}
