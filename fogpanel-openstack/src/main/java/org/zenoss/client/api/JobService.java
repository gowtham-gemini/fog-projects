
package org.zenoss.client.api;

import org.zenoss.client.common.ApplicationException;

public interface JobService extends ZenossService{
    
    /**
     * Return user job status based on uuid
     * 
     * @param uuid
     * @return
     * @throws ApplicationException 
     */
    public String userjobs(String uuid) throws ApplicationException;
}
