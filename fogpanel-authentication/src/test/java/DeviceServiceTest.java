import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.zenoss.client.ZenosServicesRegistry;
import org.zenoss.client.ZenossFacade;
import org.zenoss.client.api.DeviceService;
import org.zenoss.client.api.JobService;
import org.zenoss.client.api.TemplateService;
import org.zenoss.client.api.ZenossService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.modal.entities.device.Direction;
import org.zenoss.client.modal.entities.device.IPService;
import org.zenoss.client.modal.entities.device.LocationNode;
import org.zenoss.client.modal.entities.device.MetaType;
import org.zenoss.client.modal.entities.device.Threshold;
import org.zenoss.client.modal.service.DeviceServiceImpl;
import org.zenoss.client.modal.service.JobServiceImpl;
import org.zenoss.client.modal.service.TemplateServiceImpl;

/**
 * JUnit test class for DeviceService
 */
public class DeviceServiceTest {

    private DeviceService deviceService;
    private TemplateService templateService;
    private JobService jobService;
    
    ZenossFacade zenossFacade;

    @Before
    public void setUp() throws ApplicationException {

        String ZENOSS_INSTANCE = "http://192.168.1.109:8080";
        String ZENOSS_USERNAME = "sujai";
        String ZENOSS_PASSWORD = "sujai";
        
        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient(ZENOSS_INSTANCE, ZENOSS_USERNAME, ZENOSS_PASSWORD);
        
        
          zenossFacade = new ZenossFacade(restServiceHandler);
        ZenosServicesRegistry registry = ZenosServicesRegistry.getInstance(restServiceHandler);
        deviceService = (DeviceService)registry.getService(DeviceServiceImpl.class);
        
        templateService = (TemplateService)registry.getService(TemplateServiceImpl.class);
        jobService = (JobService)registry.getService(JobServiceImpl.class);
    }

//    @Test
//    public void testAddLocationNode() throws ApplicationException {
//        String id = "2";
//
//        LocationNode node = new LocationNode.Builder(id, "/zport/dmd/Devices/Locations").address("Address")
//                .description("desc").build();
//                
//        
//        Dictionary dic = deviceService.addLocationNode(node);
//
//        assertEquals(".zport.dmd.Locations." + id, dic.getId());
//
//    }
    
     @Test
    public void addDevice() throws ApplicationException {
        
//        System.out.println(" getDeviceClasses : "+deviceService.getDeviceClasses().get(3));
//        System.out.println(" getProductionStates : "+deviceService.getProductionStates().size());
//        System.out.println(" getPriorities : "+deviceService.getPriorities().size());
        System.out.println("getCollectors : " + deviceService.getCollectors().size());
        
//        Device  device = new Device.Builder("localhost", "test", ).address("Address")
//                .description("desc").build();
        
//         System.out.println("getThresholdTypes  "+templateService.getThresholdTypes().size());
         
//         
//         String uId="/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/os/ipservices/tcp_00053";
//         
//         deviceService.getComponents(deviceId,compotype);
//         Dictionary dictionary = templateService.getObjTemplates(uId).get(0);
//         System.out.println("getId  "+dictionary.getId());
//         System.out.println("getUid  "+dictionary.getUid());
//         System.out.println("getName  "+dictionary.getName());
         
//         Device device1 = new Device.Builder("/zport/dmd/Devices/Server/Linux/devices/192.168.1.200").build();
//         System.out.println(" getComponents : "+deviceService.getComponents(device1.getUid()).size());
         
         
//         HashMap map = new HashMap();       
//                map.put("dataPoints","");
//        map.put("thresholdId","balaji-test1");
//        map.put("thresholdType", "MinMaxThreshold");
//        map.put("uid", "/zport/dmd/Devices/Server/Windows/devices/192.168.1.105/Device");
//        System.out.println(""+templateService.addThreshold(map));
         
        
//        String deviceId = "/zport/dmd/Devices/Server/Windows/devices/192.168.1.105";
//        
//            
//        Device addedDevice = new Device.Builder(deviceId).build();
        
        
        
//        zenossFacade.addAlarm(addedDevice, threshold);
        

//        HashMap hashMap1 = new HashMap();
//        
//        hashMap1.put("uid", deviceId);
//        hashMap1.put("meta_type", "FileSystem");
//        hashMap1.put("meta_type", "HardDisk");
        
//        List<Dictionary> dictionarys = deviceService.getComponents(hashMap1);
        
//        System.out.println("size"+dictionarys.size());
//        HashMap templateMap =  new HashMap();
//        templateMap = zenossFacade.getPartitionTemplates(hashMap1);
        
//        System.out.println("templateMap"+templateMap);
//        
//        map = new HashMap();
//        map.put("uid", "/zport/dmd/Services/IpService");
//        List<Dictionary> instanceList= routerService.getInstances(map);
//        
//        for(Dictionary dictionary: instanceList) {
//            
//            System.out.println("port "+dictionary.getPort() +" Name "+dictionary.getName());
//        
//        }
//        map = new HashMap();
//        map.put("newClassName","IpService/serviceclasses/fogpanel-123");
//        map.put("protocol", "udp");
//        map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.154");
//        map.put("userCreated","true");
//        System.out.println("ip ser" +deviceService.addIpService(map));
        
//         IPService iPService = new IPService.Builder("").port("122388").build();
         
//        System.out.println("IP"+ zenossFacade.addIpService(addedDevice, iPService));
        
//        addedDevice.setPort("135");
//        IPService iPService = zenossFacade.getIpServiceByPort(addedDevice);
        
//        System.out.println("getPort"+iPService.getPort());
//        System.out.println("getUid"+iPService.getUid());
//        System.out.println("isMonitored"+iPService.isMonitored());
//        System.out.println("isMonitor"+iPService.isMonitor());
//        System.out.println("getSeverity"+iPService.get());
        
        
//        HashMap hashMap1 = new HashMap();
//        
//        hashMap1.put("uid", deviceId);
//        hashMap1.put("meta_type", "FileSystem");
////        hashMap1.put("meta_type", "HardDisk");
//        
//        List<Dictionary> dictionarys = deviceService.getComponents(hashMap1);
//        
//        System.out.println("size"+dictionarys.size());
//        HashMap templateMap =  new HashMap();
//        templateMap = zenossFacade.getPartitionTemplates(hashMap1);
//        
//        System.out.println("templateMap"+templateMap);                                
 
       
//        map.put("contextUid", "/zport/dmd/Services/IpService");
//        map.put("id", "fogpanel-123");
//        
//        System.out.println(routerService.addClass(map));        
//        
//        map.put("uid", "/zport/dmd/Services/IpService/serviceclasses/fogpanel-123");
//        map.put("name", "fogpanel-12331");
//        map.put("port", "12331");
//        
//         System.out.println(""+routerService.setInfoForAddedClass(map));
         
         
//         System.out.println("makeLocalRRDTemplate"+templateService.makeLocalRRDTemplate("/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/os/ipservices/tcp_00053", 
//                 "IpService"));
         
         
//         System.out.println("Remove template"+templateService.removeThreshold("/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/Device/thresholds/sample2"));
         
         
         
//        System.out.println(jobService.userjobs("2a54c3eb-e927-4f25-8a9a-b451a0f2428b"));                                                                                
    }
}
