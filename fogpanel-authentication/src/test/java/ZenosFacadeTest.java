
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.zenoss.client.ZenossFacade;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.GraphDefType;

public class ZenosFacadeTest {
    
    ZenossFacade facade;
    
    @Before
    public void setUp() throws ApplicationException {

        String ZENOSS_INSTANCE = "http://192.168.1.108:8080";
        String ZENOSS_USERNAME = "sujai";
        String ZENOSS_PASSWORD = "sujai";
        
        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient(ZENOSS_INSTANCE, ZENOSS_USERNAME, 
                ZENOSS_PASSWORD);
        facade = new ZenossFacade(restServiceHandler);
    }
    
    @Test
    public void getGraphTest() throws ApplicationException, IOException {
        
//        Device  device = new Device.Builder("/zport/dmd/Devices/Server/Linux/devices/192.168.1.162").build(); 
//        
//        String graphData = facade.getGraph(device, GraphDefType.LOAD_AVERAGE, (long) 1000, new Long(1000));
//        
//        System.err.println("Graph Data: " + graphData);
        
    }
    
}
