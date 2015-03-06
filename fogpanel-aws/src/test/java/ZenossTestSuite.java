import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;

/**
 * Zenoss Test Suite
 */
public class ZenossTestSuite {
    public static void main(String[] a) throws ApplicationException {

        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient("","","");

        Result result = JUnitCore.runClasses(DeviceServiceTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("Success: " + result.wasSuccessful());
    }
}
