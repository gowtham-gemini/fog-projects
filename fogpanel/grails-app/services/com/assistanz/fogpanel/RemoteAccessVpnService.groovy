package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.virtualmachine.CSVirtualMachineService
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.MessageSource
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.virtualmachine.VirtualMachineResponse
import groovy.json.JsonBuilder
import com.assistanz.cloud.cloudstack.iso.CSIsoService
import com.assistanz.cloud.cloudstack.iso.AttachISOResponse
import com.assistanz.cloud.cloudstack.iso.DetachISOResponse
import com.assistanz.cloud.cloudstack.iso.IsoResponse
import com.assistanz.cloud.cloudstack.nic.CSNicService
import com.assistanz.cloud.cloudstack.pool.CSPoolService
import com.assistanz.cloud.cloudstack.pool.StoragePoolResponse
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.cloud.cloudstack.asyncjob.CSAsyncJobService
import com.assistanz.cloud.cloudstack.host.CSHostService
import com.assistanz.cloud.cloudstack.host.HostResponse
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import com.assistanz.cloud.cloudstack.volume.VolumeResponse
import com.assistanz.cloud.cloudstack.vpn.CSVPNService
import java.net.URL;
import java.util.StringTokenizer;
import com.assistanz.fogpanel.NotificationService
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.text.DateFormat
import javax.xml.bind.DatatypeConverter;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse
import com.assistanz.cloud.cloudstack.address.CSAddressService
import com.assistanz.cloud.cloudstack.address.IpAddressResponse
import com.assistanz.cloud.cloudstack.securitygroup.CSSecurityGroupService
import javax.crypto.Cipher;
import java.security.Key;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import java.io.StringReader;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.context.MessageSource
import com.assistanz.fogpanel.*;
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory;

@Transactional
class RemoteAccessVpnService {
    
    def remoteAccessVpnService() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server = new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);                                
        CSVPNService csVPNService = new CSVPNService(server);          
        return csVPNService;
    }
    
    
}
