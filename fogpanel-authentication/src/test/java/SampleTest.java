import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.zenoss.client.ZenossFacade;
import org.zenoss.client.api.JobService;
import org.zenoss.client.api.TemplateService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.DeviceResponse;
import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.modal.entities.device.Threshold;

public class SampleTest {

    public static void main(String args[]) throws Exception {
//        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient("","","");
        HashMap map = new HashMap();

        //1. getDeviceClasses
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getDeviceClasses", map);
//        System.out.println(obj.toString());
        //2. getProductionStates
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getProductionStates", map);
//        System.out.println(obj.toString());
        //3. getPriorities
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getPriorities", map);
//        System.out.println(obj.toString());
        //4. getCollectors
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getCollectors", map);
//        System.out.println(obj.toString());
//        // add device
//        map.put("deviceName", "balaji-test");
//        map.put("deviceClass", "/Server/Linux");
//        map.put("snmpPort",161);
//        map.put("model",false);
//        map.put("collector","localhost");
//        map.put("rackSlot",0);
//        map.put("productionState",1000);
//        map.put("priority",3);
//        
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "addDevice", map);
//        System.out.println(obj.toString());
        
        
//        map.put("uid","/zport/dmd/Devices/Server/Linux/devices/192.168.1.163");
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getDevices", map);
//        System.out.println(obj.toString());
        
        
//        1.getThresholdTypes
//        JSONObject obj1 = restServiceHandler.test("TemplateRouter", "template", "getThresholdTypes", map);
//        System.out.println(obj1.toString());
        
//        2.getTemplates
//            map.put("id","/zport/dmd/Devices/Server/Linux/devices/192.168.1.163");
//        JSONObject obj2 = restServiceHandler.test("DeviceRouter", "device", "getTemplates", map);
//        System.out.println(obj2.toString());
        
        
//          3. copyTemplate
//           map.put("uid","/zport/dmd/Devices/Server/Linux/rrdTemplates/Device");
//           map.put("targetUid","/zport/dmd/Devices/Server/Linux/devices/192.168.1.163");
//           JSONObject obj3 = restServiceHandler.test("TemplateRouter", "template", "copyTemplate", map);
//           System.out.println("Results"+obj3.toString());
           
        
        // 4. addThreshold
//        map.put("dataPoints","");
//        map.put("thresholdId","balaji-test");
//        map.put("thresholdType", "MinMaxThreshold");
//        map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/Device");
        
        
        //5. setInfo
//        map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/Device/thresholds/balaji-test");
//        map.put("description","Editing threshold by balaji");
//        JSONObject obj4 = restServiceHandler.test("TemplateRouter", "template", "setInfo", map);
//        System.out.println(obj4.toString());
        
        
        // getThresholds
        
//                map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/Device");
//        JSONObject obj4 = restServiceHandler.test("TemplateRouter", "template", "getThresholds", map);
//        System.out.println(obj4.toString());
        
        
        //removeThreshold
//        map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/Device/thresholds/high load");
//        JSONObject obj5 = restServiceHandler.test("TemplateRouter", "template", "removeThreshold", map);
//        System.out.println(obj5.toString());
        
        
        //getComponents
//        map.put("id","/zport/dmd/Devices/Server/Linux/devices/192.168.1.163");
        
//       map.put("meta_type", "IpService");
//       map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163");
//        
//        JSONObject obj2 = restServiceHandler.test("DeviceRouter", "device", "getComponents", map);
//        System.out.println(obj2.toString());
        
        
        
        
        // getObjTemplates
//        
//        map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/os/ipservices/tcp_00053");
//        JSONObject obj5 = restServiceHandler.test("TemplateRouter", "template", "getObjTemplates", map);
//        System.out.println(obj5.toString());
//        
//        
//        // makeLocalRRDTemplate
//        
//        map.put("templateName", "IpService");
//        
//        map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.163/os/ipservices/tcp_00053");
//        
//         
//        
//        JSONObject obj6 = restServiceHandler.test("TemplateRouter", "template", "makeLocalRRDTemplate", map);
//        System.out.println(obj6.toString());
//        
//        
//        
//        map.put("uid","/zport/dmd/Devices/Server/Linux/devices/192.168.1.162");
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getDevices", map);
//        System.out.println(obj.toString());



        
        
        String ZENOSS_INSTANCE = "http://192.168.1.109:8080";
        String ZENOSS_USERNAME = "sujai";
        String ZENOSS_PASSWORD = "sujai";
        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient(ZENOSS_INSTANCE, ZENOSS_USERNAME, ZENOSS_PASSWORD);
        ZenossFacade facade = new ZenossFacade(restServiceHandler);
        
        
        
        Device device
                = new Device.Builder("192.168.1.105", "WINDOWS test by balaji", "public")
//                .deviceClass("/Server/Windows")
                .productionState(1000)
                .priority(3)
//                .rackSlot("0")
                .model(Boolean.TRUE)
                .collector("localhost")
                .snmpPort(161).build();
        
        device.setOsType("WINDOWS");
        
//        
//        DeviceResponse resultMap = facade.enable(device);
//        System.out.println("resultmap" + resultMap);
        

        String deviceId = "/zport/dmd/Devices/Server/Linux/devices/192.168.1.200";
        System.out.println("deviceID"+deviceId);
//        
//        
        Device addedDevice = new Device.Builder(deviceId).build();
        
//       
//        
//        System.out.println("check "+ facade.checkComponentsAvailable(addedDevice));
        
   
        
        
//        facade.overWriteDefaultTemplates(addedDevice);
        
   /**     
        addedDevice.setOsType("WINDOWS");
        
        
//        System.out.println("device deleted success"+facade.disable(addedDevice));
        
        **/
        Threshold threshold = new Threshold.Builder("balaji-test 1", "MinMaxThreshold")
//                .uid(deviceId+"/Device")
                .maxval("30")
                .minval("3")
                .build();
        
        threshold.setAlarmType("CPU_WINDOWS");
        
        //facade.addAlarm(addedDevice, threshold);
        
        /**
        
        String thresholdId="/zport/dmd/Devices/Server/Windows/devices/192.168.1.105/Device/thresholds/test";
        Dictionary dictionary = new Dictionary.Builder(thresholdId, thresholdId).build();
        
        try {
            System.out.println("removed " + facade.deleteAlarm(dictionary));
        } catch (ApplicationException applicationException) {
            
            System.out.println(""+applicationException.getMessage());
        }
        
              
        
        
        
//        threshold = new Threshold.Builder("DISK_IO_READ_WINDOWS", "MinMaxThreshold")
//                .uid(deviceId+"/Device")
//                .maxval("30")
//                .minval("3")
//                .build();
//        
        threshold.setAlarmType("DISK_IO_READ_WINDOWS");
//        
//        facade.addAlarm(addedDevice, threshold);
//        
//        
//        threshold = new Threshold.Builder("DISK_IO_WRITE_WINDOWS", "MinMaxThreshold")
//                .uid(deviceId+"/Device")
//                .maxval("30")
//                .minval("3")
//                .build();
//        
//        threshold.setAlarmType("DISK_IO_WRITE_WINDOWS");
////        
//        facade.addAlarm(addedDevice, threshold);
        
        */
 
//           map.put("meta_type", "IpService");
//       map.put("uid", "/zport/dmd/Devices/Server/Linux/devices/192.168.1.200");
//        
//        JSONObject obj2 = restServiceHandler.test("DeviceRouter", "device", "getComponents", map);
//        System.out.println(obj2.toString());
//        
//                map.put("uid","/zport/dmd/Devices/HTTP/devices/192.168.1.30");
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getDevices", map);
//        System.out.println(obj.toString());
                
        
        
//        map.put("uid","/zport/dmd/Devices/Server/Linux/devices/192.168.1.162");
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "getDevices", map);
//        System.out.println(obj.toString());
        
        
        //removeDevices
                
//        map.put("uids", "/zport/dmd/Devices/Server/Windows/devices/192.168.1.105");
//        
//        map.put("deleteEvents", true);
//        map.put("deletePerf", true);
//        map.put("hashcheck", 1);
//        map.put("action", "delete");
//        JSONObject obj = restServiceHandler.test("DeviceRouter", "device", "removeDevices", map);
//        System.out.println(obj.toString());
//      
//                    map.put("id","/zport/dmd/Devices/Server/Linux/devices/192.168.1.200");
//        JSONObject obj2 = restServiceHandler.test("DeviceRouter", "device", "getTemplates", map);
//        System.out.println(obj2.toString());
        
//                map.put("uid", "/zport/dmd/Devices/Server/Windows/devices/192.168.1.105/Device/thresholds/balaji-test1");
//        map.put("description","Editing threshold by balaji");
//        JSONObject obj4 = restServiceHandler.test("TemplateRouter", "template", "setInfo", map);
//        System.out.println(obj4.toString());
        
                
//        map.put("uid", "/zport/dmd/Services/IpService");
//        JSONObject obj4 = restServiceHandler.test("ServiceRouter", "service", "query", map);
//        System.out.println("obj4"+obj4.toString());
        
         

//        map.put("uid", "/zport/dmd/Services/IpService");
//        map.put("name", "fogpanel-12331");
//        map.put("port", "12331");
        
        List<String> uids = new ArrayList<String>();
        uids.add("/zport/dmd/Devices/Server/Windows/devices/192.168.1.105/os/ipservices/fogpanel-122388");
        map.put("uids", uids);
        map.put("hashcheck", null);
        map.put("monitor",true);        
        
        JSONObject obj4 = restServiceHandler.test("DeviceRouter", "device", "setComponentsMonitored", map);
//        JSONObject obj4 = restServiceHandler.test("DeviceRouter", "device", "deleteComponents", map);
        System.out.println("obj4"+obj4.toString());
                
            
        

        
    }

}
