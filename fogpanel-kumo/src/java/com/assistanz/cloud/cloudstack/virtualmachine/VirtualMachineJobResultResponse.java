/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.virtualmachine;

import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import java.util.List;

/**
 *
 * @author Az-PHP
 */
class VirtualMachineJobResultResponse {
    
    /**
     * the account that executed the async command
     */
    String asychronousAccountId;

    /**
     * 	the async command executed
     */
    String asychronousCmd;

    /**
     * the created date of the job
     */
    String asychronousCreated;	

    /**
     * the unique ID of the instance/entity object related to the job
     */
    String asychronousJobInstanceId;

    /**
     * 	the instance/entity object related to the job
     */
    String asychronousJobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    String asychronousJobProgressStatus;

    /**
     * the result reason
     */
    String asychronousJobResult;

    /**
     * the result code for the job
     */
    String asychronousJobResultCode;

    /**
     * the result type
     */
    String asychronousJobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    String asychronousJobStatus;

    /**
     * the user that executed the async command
     */
    String asychronousUserId;

    /**
     * the ID of the async job
     */
    String asychronousJobId;
    
    /**
     * the current error Code of the latest async job acting on this object
     */
    private String errorCode;
    /**
     * the current error Text of the latest async job acting on this object
     */
    private String errorText;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getAsychronousAccountId() {
            return asychronousAccountId;
    }

    public void setAsychronousAccountId(String asychronousAccountId) {
            this.asychronousAccountId = asychronousAccountId;
    }

    public String getAsychronousCmd() {
            return asychronousCmd;
    }

    public void setAsychronousCmd(String asychronousCmd) {
            this.asychronousCmd = asychronousCmd;
    }

    public String getAsychronousCreated() {
            return asychronousCreated;
    }

    public void setAsychronousCreated(String asychronousCreated) {
            this.asychronousCreated = asychronousCreated;
    }

    public String getAsychronousJobInstanceId() {
            return asychronousJobInstanceId;
    }

    public void setAsychronousJobInstanceId(String asychronousJobInstanceId) {
            this.asychronousJobInstanceId = asychronousJobInstanceId;
    }

    public String getAsychronousJobInstanceType() {
            return asychronousJobInstanceType;
    }

    public void setAsychronousJobInstanceType(String asychronousJobInstanceType) {
            this.asychronousJobInstanceType = asychronousJobInstanceType;
    }

    public String getAsychronousJobProgressStatus() {
            return asychronousJobProgressStatus;
    }

    public void setAsychronousJobProgressStatus(String asychronousJobProgressStatus) {
            this.asychronousJobProgressStatus = asychronousJobProgressStatus;
    }

    public String getAsychronousJobResult() {
            return asychronousJobResult;
    }

    public void setAsychronousJobResult(String asychronousJobResult) {
            this.asychronousJobResult = asychronousJobResult;
    }

    public String getAsychronousJobResultCode() {
            return asychronousJobResultCode;
    }

    public void setAsychronousJobResultCode(String asychronousJobResultCode) {
            this.asychronousJobResultCode = asychronousJobResultCode;
    }

    public String getAsychronousJobResultType() {
            return asychronousJobResultType;
    }

    public void setAsychronousJobResultType(String asychronousJobResultType) {
            this.asychronousJobResultType = asychronousJobResultType;
    }

    public String getAsychronousJobStatus() {
            return asychronousJobStatus;
    }

    public void setAsychronousJobStatus(String asychronousJobStatus) {
            this.asychronousJobStatus = asychronousJobStatus;
    }

    public String getAsychronousUserId() {
            return asychronousUserId;
    }

    public void setAsychronousUserId(String asychronousUserId) {
            this.asychronousUserId = asychronousUserId;
    }

    public String getAsychronousJobId() {
            return asychronousJobId;
    }

    public void setAsychronousJobId(String asychronousJobId) {
            this.asychronousJobId = asychronousJobId;
    }	
    
    
    //virtual machine
    
    
      	/**
	 * the ID of the virtual machine
	 */
	private String virtualMachineId;
	
	/**
	 * the account associated with the virtual machine
	 */
	private String accountName;
	
	/**
	 * the number of cpu this virtual machine is running with
	 */
	private String cpuNumber;
	
	/**
	 * the speed of each cpu
	 */
	private String cpuSpeed;
	
	/**
	 * the amount of the vm's CPU currently used
	 */
	private String cpuUsed;
	
	/**
	 * the date when this virtual machine was created
	 */
	private String created;
	
	/**
	 * user generated name. 
	 * The name of the virtual machine is returned if no display name exists.
	 * 
	 */
	private String displayName;
	
	/**
	 * the name of the domain in which the virtual machine exists
	 */
	private String domainName;
	
	/**
	 * the ID of the domain in which the virtual machine exists
	 */
	private String domainId;
	
	/**
	 * the virtual network for the service offering
	 */
	private String forVirtualNetwork;
	
	/**
	 * the group name of the virtual machine
	 */
	private String group;
	
	/**
	 * the group ID of the virtual machine
	 */
	private String groupId;
	
	/**
	 * Os type ID of the virtual machine
	 */
	private String guestOsId;
	
	/**
	 * true if high-availability is enabled, false otherwise
	 */
	private String highEnable;
	
	/**
	 * the ID of the host for the virtual machine
	 */
	private String hostId;
	
	/**
	 * the name of the host for the virtual machine
	 */
	private String hostName;	
	
	/**
	 * the hyper visor on which the template runs
	 */
	private String hyperVisor;
	        
        /**
	 * the name of the virtual machine
	 */
	private String instanceName;	
        
	/**
	 * an alternate display text of the ISO attached to the virtual machine
	 */
	private String isoDisplayText;
	
	/**
	 * the ID of the ISO attached to the virtual machine
	 */
	private String isoId;
	
	/**
	 * the name of the ISO attached to the virtual machine
	 */
	private String isoName;
	
	/**
	 * the memory allocated for the virtual machine
	 */
	private String memory;
	
	/**
	 * the name of the virtual machine
	 */
	private String virtualMachineName;	
	
	/**
	 * the incoming network traffic on the virtual machine
	 */
	private String networkKbpsRead;
	
	/**
	 * the outgoing network traffic on the host
	 */
	private String networkKbpsWrite;
	
	/**
	 * the password (if exists) of the virtual machine
	 */
	private String password;	
	
	/**
	 * true if the password rest feature is enabled, false otherwise
	 */
	private String passwordEnabled;
	
	/**
	 * the project name of the virtual machine
	 */
	private String projectName;
	
	/**
	 * the project id of the virtual machine
	 */
	private String projectId;
	
	/**
	 * public IP address id associated with virtual machine via Static nat rule
	 */
	private String publicIp;
	
	/**
	 * public IP address id associated with virtual machine via Static nat rule
	 */
	private String publicIpId;
	
	/**
	 * device ID of the root volume
	 */
	private String rootDeviceId;
	
	/**
	 * device type of the root volume
	 */
	private String rootDeviceType;
	
	/**
	 * the ID of the service offering of the virtual machine
	 */
	private String serviceOfferingId;
	
	/**
	 * the name of the service offering of the virtual machine
	 */
	private String serviceOfferingName;
	
	/**
	 * the state of the virtual machine
	 */
	private String virtualMachineState;
	
	/**
	 * an alternate display text of the template for the virtual machine
	 */
	private String templateDisplayText;
	
	/**
	 * the ID of the template for the virtual machine. 
	 * A -1 is returned if the virtual machine was created from an ISO file.
	 * 
	 */
	private String templateId;
	
	/**
	 * the name of the template for the virtual machine
	 */
	private String templateName;
	
	/**
	 * the ID of the availablility zone for the virtual machine
	 */
	private String zoneId;
	
	/**
	 * the name of the availability zone for the virtual machine
	 */
	private String zoneName;	
	
	/**
	 * the list of NetworkInterfaceCards associated with virtual machine
	 */
	private List<NetworkInterfaceCardResponse> networkInterfaceCards;	
	
	/**
	 * list of security groups associated with the virtual machine
	 */
	private List<SecurityGroupResponse> securityGroups;	
	
	/**
	 * the ID of the latest async job acting on this object
	 */
	private String jobId;

	/**
	 * the current status of the latest async job acting on this object
	 */
	private String jobStatus;	
			
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

        public String getInstanceName() {
            return instanceName;
        }

        public void setInstanceName(String instanceName) {
            this.instanceName = instanceName;
        }

	public String getJobStatus() {
		return jobStatus;
	}
        
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}	

	public List<SecurityGroupResponse> getSecurityGroups() {
		return securityGroups;
	}

	public void setSecurityGroups(List<SecurityGroupResponse> securityGroups) {
		this.securityGroups = securityGroups;
	}

	public String getVirtualMachineId() {
		return virtualMachineId;
	}

	public void setVirtualMachineId(String virtualMachineId) {
		this.virtualMachineId = virtualMachineId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCpuNumber() {
		return cpuNumber;
	}

	public void setCpuNumber(String cpuNumber) {
		this.cpuNumber = cpuNumber;
	}

	public String getCpuSpeed() {
		return cpuSpeed;
	}

	public void setCpuSpeed(String cpuSpeed) {
		this.cpuSpeed = cpuSpeed;
	}

	public String getCpuUsed() {
		return cpuUsed;
	}

	public void setCpuUsed(String cpuUsed) {
		this.cpuUsed = cpuUsed;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getForVirtualNetwork() {
		return forVirtualNetwork;
	}

	public void setForVirtualNetwork(String forVirtualNetwork) {
		this.forVirtualNetwork = forVirtualNetwork;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGuestOsId() {
		return guestOsId;
	}

	public void setGuestOsId(String guestOsId) {
		this.guestOsId = guestOsId;
	}

	public String getHighEnable() {
		return highEnable;
	}

	public void setHighEnable(String highEnable) {
		this.highEnable = highEnable;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHyperVisor() {
		return hyperVisor;
	}

	public void setHyperVisor(String hyperVisor) {
		this.hyperVisor = hyperVisor;
	}

	public String getIsoDisplayText() {
		return isoDisplayText;
	}

	public void setIsoDisplayText(String isoDisplayText) {
		this.isoDisplayText = isoDisplayText;
	}

	public String getIsoId() {
		return isoId;
	}

	public void setIsoId(String isoId) {
		this.isoId = isoId;
	}

	public String getIsoName() {
		return isoName;
	}

	public void setIsoName(String isoName) {
		this.isoName = isoName;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}	

        public String getVirtualMachineName() {
            return virtualMachineName;
        }

        public void setVirtualMachineName(String virtualMachineName) {
            this.virtualMachineName = virtualMachineName;
        }

        public String getNetworkKbpsRead() {
            return networkKbpsRead;
        }

        public void setNetworkKbpsRead(String networkKbpsRead) {
            this.networkKbpsRead = networkKbpsRead;
        }

        public String getNetworkKbpsWrite() {
            return networkKbpsWrite;
        }

        public void setNetworkKbpsWrite(String networkKbpsWrite) {
            this.networkKbpsWrite = networkKbpsWrite;
        }
        
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordEnabled() {
		return passwordEnabled;
	}

	public void setPasswordEnabled(String passwordEnabled) {
		this.passwordEnabled = passwordEnabled;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}	

        public String getPublicIpId() {
            return publicIpId;
        }

        public void setPublicIpId(String publicIpId) {
            this.publicIpId = publicIpId;
        }

	public String getRootDeviceId() {
		return rootDeviceId;
	}

	public void setRootDeviceId(String rootDeviceId) {
		this.rootDeviceId = rootDeviceId;
	}

	public String getRootDeviceType() {
		return rootDeviceType;
	}

	public void setRootDeviceType(String rootDeviceType) {
		this.rootDeviceType = rootDeviceType;
	}

	public String getServiceOfferingId() {
		return serviceOfferingId;
	}

	public void setServiceOfferingId(String serviceOfferingId) {
		this.serviceOfferingId = serviceOfferingId;
	}

	public String getServiceOfferingName() {
		return serviceOfferingName;
	}

	public void setServiceOfferingName(String serviceOfferingName) {
		this.serviceOfferingName = serviceOfferingName;
	}

        public String getVirtualMachineState() {
            return virtualMachineState;
        }

        public void setVirtualMachineState(String virtualMachineState) {
            this.virtualMachineState = virtualMachineState;
        }
	

	public String getTemplateDisplayText() {
		return templateDisplayText;
	}

	public void setTemplateDisplayText(String templateDisplayText) {
		this.templateDisplayText = templateDisplayText;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public List<NetworkInterfaceCardResponse> getNetworkInterfaceCards() {
		return networkInterfaceCards;
	}

	public void setNetworkInterfaceCards(
			List<NetworkInterfaceCardResponse> networkInterfaceCards) {
		this.networkInterfaceCards = networkInterfaceCards;
	}
    
    
}
