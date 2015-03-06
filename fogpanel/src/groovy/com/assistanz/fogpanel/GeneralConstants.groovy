package com.assistanz.fogpanel;

import java.util.HashMap;

/**
 *
 * @author Gowtham
 */
class GeneralConstants {
	
    public static final String STORAGE_POOL_TYPE_LVM = "LVM";
    public static final String STORAGE_POOL_TYPE_NFS = "NetworkFilesystem";
    public static final String RESULT_SUCCESS = "OK";
    public static final String RESULT_TRUE = "TRUE";
    public static final String RESULT_FALSE = "FALSE";
       
    public static final String JOB_RESULT_PENDING = "Pending";
    public static final String JOB_RESULT_FAILURE = "JOB_FAILED";
    public static final String RESULT_FAILURE = "FAILED";
    public static final String RESULT_AVAILIABLE = "available";
    public static final String XEN_HYPERVISOR = "XenServer";
    public static final String ZONE_TYPE_BASIC = "Basic";
    public static final String ZONE_TYPE_ADVANCE = "Advance";
    public static final String VM_RUNNING_STATE = "Running";
    public static final String VM_STOPPING_STATE = "Stopping";
    public static final String VM_STOPPED_STATE = "Stopped";
    public static final String VM_DESRROYED_STATE = "Destroyed";
    public static final String VM_EXPUNGING_STATE = "Expunging";
    public static final Long IP_L1 = 16777216l;
    public static final Long IP_L2 = 65536l;
    public static final Long IP_L3 = 256l;
    public static final String BACKING_UP = "BackingUp";
    public static final String INTERVAL_TYPE_DAILY = "DAILY";
    public static final String INTERVAL_TYPE_WEEKLY = "WEEKLY";
    public static final String INTERVAL_TYPE_MONTHLY = "MONTHLY";
    public static final String BASE_OS_LINUX = "Linux";
    public static final String BASE_OS_WINDOWS  = "Windows";
    public static final String INGRESS  = "INGRESS";
    public static final String EGRESS  = "EGRESS";
    public static final String RESULT_EXPIRED  = "EXPIRED";
    
    public static final String ACCOUNT_STATUS_ACTIVE  = "ACTIVE";
    public static final String ACCOUNT_STATUS_BLOCKED  = "BLOCKED";
    
    public static final String ACCOUNT_STATUS_LOCKED  = "LOCKED";
    public static final String ACCOUNT_STATUS_DISABLED  = "DISABLED";
    
    public static final String ACCOUNT_STATUS_NOT_VERIFIED  = "NOT_VERIFIED";
    public static final String ACCOUNT_STATUS_SUSPENDED  = "SUSPENDED";
    
    public static final String ACCOUNT_STATUS_CANCELED  = "CANCELED";
    public static final String ACCOUNT_STATUS_CLOSED = "CLOSED";
    
    
    public static final enum SECURITY_GROUP_TYPE  {INGRESS, EGRESS};   
    public static final enum SECURITY_GROUP_BASE_OS  {Linux, Windows};   
    
    
    
     public static final String PAYMENT_RESULT_FAILURE = "FAILED";
     public static final String PAYMENT_RESULT_SUCCESS = "SUCCESS";
    
    
    
}

