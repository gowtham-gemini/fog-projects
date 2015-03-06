<script type="text/javascript" src = "${resource(dir: 'js/app')}/user.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app')}/cloud.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/template.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/billing.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/reportings.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/health.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/support.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/service.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/network.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/vpc.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/vpcSecurity.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/tier.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/vpnCustomerGateway.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/gateway.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/limitSummary.js"></script>

<!--<script type="text/javascript" src = "${resource(dir: 'js/app')}/accountSetup.js"></script>-->
<r:layoutResources/>    
<link rel="stylesheet" type="text/css"  href = "${resource(dir:'css/',  file: 'elements.css')}"/>	     
<link rel="stylesheet" type="text/css" href = "${resource(dir:'js/dojo-1.8/FogPanel/themes/VolumeListItem/', file: 'VolumeListItem.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/FogPanel/themes/Pagination/', file: 'Pagination.css')}" /> 
<script id="user_index" type="text/template">
  <g:render template="home" />
</script>
<script id="addInternalLBGeneral" type="text/template">
  <g:render template="addInternalLBGeneral" />
</script>
<script id="limitSummaryPage" type="text/template">
  <g:render template="limitSummaryPage" />
</script>
<script id="addTier" type="text/template">
  <g:render template="addTier" />
</script>
<script id="vpcHelpMenu" type="text/template">
  <g:render template="vpcHelpMenu" />
</script>
<script id="listTier" type="text/template">
  <g:render template="listTier" />
</script>
<script id="listVPCVM" type="text/template">
  <g:render template="listVPCVM" />
</script>
<script id="topology" type="text/template">
  <g:render template="topology" />
</script>
<script id="vpnGatewayListPage" type="text/template">
  <g:render template="vpnGatewayListPage" />
</script>
<script id="privateGatewayListPage" type="text/template">
  <g:render template="privateGatewayListPage" />
</script>
<script id="gatewayContainer" type="text/template">
  <g:render template="gatewayContainer" />
</script>
<script id="vpnConnectionListPage" type="text/template">
  <g:render template="vpnConnectionListPage" />
</script>
<script id="vpcPublicLBGeneralInfo" type="text/template">
  <g:render template="vpcPublicLBGeneralInfo" />
</script>
<script id="vpcInternalLBGeneralInfo" type="text/template">
  <g:render template="vpcInternalLBGeneralInfo" />
</script>
<script id="vpcPublicIPGeneralnfo" type="text/template">
  <g:render template="vpcPublicIPGeneralnfo" />
</script>
<script id="vpcStativNatIPGeneralnfo" type="text/template">
  <g:render template="vpcStativNatIPGeneralnfo" />
</script>
<script id="viewPrivateGateway" type="text/template">
  <g:render template="viewPrivateGateway" />
</script>
<script id="securityContainer" type="text/template">
  <g:render template="securityContainer" />
</script>
<script id="viewACL" type="text/template">
  <g:render template="viewACL" />
</script>
<script id="viewTier" type="text/template">
  <g:render template="viewTier" />
</script>
<script id="vpcDashboard" type="text/template">
  <g:render template="vpcDashBoard" />
</script>
<script id="vpcContainer" type="text/template">
  <g:render template="vpcContainer" />
</script>
<script id="viewVpc" type="text/template">
  <g:render template="viewVpc" />
</script>
<script id="viewVpcIPAddress" type="text/template">
  <g:render template="viewVpcIPAddress" />
</script>
<script id="vmDetail" type="text/template">
  <g:render template="vmDetailPage" />
</script>
<script id="vmStorage" type="text/template">
  <g:render template="vmStoragePage" />
</script>
<script id="vmNic" type="text/template">
  <g:render template="vmNicPage" />
</script>
<script id="vmSecondaryIP" type="text/template">
  <g:render template="vmSecondaryIPPage" />
</script>
<script id="cloudMenuContainer" type="text/template">
  <g:render template="cloudMenuContainer" />
</script>
<script id="billingMenuInfo" type="text/template">
  <g:render template="billingMenuInfo" />
</script>
<script id="stateMenuInfo" type="text/template">
  <g:render template="stateMenuInfo" />
</script>
<script id="activityMenuInfo" type="text/template">
  <g:render template="activityMenuInfo" />
</script>

<script id="vpnCustomerGatewayIndex" type="text/template">
  <g:render template="vpnCustomerGatewayIndex" />
</script>
<script id="vpnCustomerGatewayList" type="text/template">
  <g:render template="vpnCustomerGatewayList" />
</script>
<script id="vpnCustomerGatewayAdd" type="text/template">
  <g:render template="vpnCustomerGatewayAdd" />
</script>

<script id="accountAlert" type="text/template">
  <g:render template="accountAlert" />
</script>
<script id="accountList" type="text/template">
  <g:render template="accountList" />
</script>
<script id="viewAccountInfo" type="text/template">
  <g:render template="viewAccountInfo" />
</script>
<script id="createVm" type="text/template">
  <g:render template="createVm" />
</script>
<script id="healthInfo" type="text/template">
  <g:render template="healthInfo" />
</script>

<script id="addDisk" type="text/template">
  <g:render template="addDisk" />
</script>
<script id="templateInfo" type="text/template">
  <g:render template="templateInfo" />
</script>
<script id="windowsTemplate" type="text/template">
  <g:render template="windowsTemplate" />
</script>
<script id="apTemplate" type="text/template">
  <g:render template="appTemplate" />
</script>
<script id="createVpc" type="text/template">
  <g:render template="createVpc" />
</script>
<script id="vpcList" type="text/template">
  <g:render template="vpcList" />
</script>
<script id="networkAclList" type="text/template">
  <g:render template="networkAclList" />
</script>
<script id="createNetworkAcl" type="text/template">
  <g:render template="createNetworkAcl" />
</script>
<script id="userInstancePage" type="text/template">
  <g:render template="userInstancePage" />
</script>
<script id="manualPayment" type="text/template">
  <g:render template="payment" />
</script>
<script id="userIsoPage" type="text/template">
  <g:render template="userIsoPage" />
</script>
<script id="ingressRulePage" type="text/template">
  <g:render template="ingressRulePage" />
</script>
<script id="egressRulePage" type="text/template">
  <g:render template="egressRulePage" />
</script>

<script id="userInstancePage" type="text/template">
  <g:render template="userInstancePage" />
</script>

<script id="accountSetup" type="text/template">
  <g:render template ="accountSetup" />
</script>
<script id="invoiceSummary" type="text/template">
  <g:render template ="invoiceSummary" />
</script>  
<script id="currentUsage" type="text/template">
  <g:render template ="currentUsage" />
</script> 
<script id="creditCard" type="text/template">
  <g:render template ="creditCard" />
</script>  
<script id="accountProfile" type="text/template">
  <g:render template ="accountProfile" />
</script>
    
<script id="storage" type="text/template">
  <g:render template ="storage" />
</script>

<script id="templatePage" type="text/template">
  <g:render template ="templatePage"/>
</script>

<script id="securityGroups" type="text/template">
  <g:render template ="securityGroups" />
</script>

<script id="general" type="text/template">
  <g:render template ="general" />
</script>

<script id="profile" type="text/template">
  <g:render template ="profile" />
</script>

<script id="contact" type="text/template">
  <g:render template ="contact" />
</script>
<script id="billing" type="text/template">
  <g:render template ="billing" />
</script>
<script id="email" type="text/template">
  <g:render template ="email" />
</script>
<script id="alert" type="text/template">
  <g:render template ="alert" />
</script>
<script id="vmSnapshot" type="text/template">
  <g:render template = "vmSnapshot" />
</script>
<script id="userAlert" type="text/template">
  <g:render template ="userAlert" />
</script>

<script id="billingAlert" type="text/template">
  <g:render template ="billingAlert" />
</script>
<script id="instanceInfo" type="text/template">
  <g:render template ="instanceInfo" />
</script>
<script id="cloud_index" type="text/template">
  <g:render template = "cloud" />
</script>
<script id="snapShot" type="text/template">
  <g:render template = "snapShot" />
</script>

<script id="firewall" type="text/template">
  <g:render template = "firewall" />
</script>
<script id="userRecurringItem" type="text/template">
  <g:render template ="userRecurringItem" />
</script>
<script id="userCustomItem" type="text/template">
  <g:render template ="userCustomItem" />
</script>
<script id="billableItemChartReportPage" type="text/template">
  <g:render template="billableItemChartReportPage" />
</script>
<script id="billableItemReportPage" type="text/template">
  <g:render template="billableItemReportPage" />
</script>
<script id="paymentReportPage" type="text/template">
  <g:render template="paymentReportPage" />
</script>
<script id="events" type="text/template">
  <g:render template="events" />
</script>
<script id="notification" type="text/template">
  <g:render template="notification" />
</script>
<script id="addTicket" type="text/template">
  <g:render template="addTicket" />
</script>
<script id="support" type="text/template">
  <g:render template="support" />
</script>
<script id="tickets" type="text/template">
  <g:render template="tickets" />
</script>
<script id="viewTicket" type="text/template">
  <g:render template="viewTicket" />
</script>
<script id="serviceIndex" type="text/template">
  <g:render template="serviceIndex" />
</script>
<script id="SSHKeyPage" type="text/template">
  <g:render template="SSHKeyPage" />
</script>
<script id="vmBandwidth" type="text/template">
  <g:render template="vmBandwidth" />
</script>
<script id="ipInfo" type="text/template">
  <g:render template="ipInfo" />
</script>
<script id="networkList" type="text/template">
  <g:render template="networkList" />
</script>
<script id="createNetwork" type="text/template">
  <g:render template="createNetwork" />
</script>
<script id="editNetwork" type="text/template">
  <g:render template="editNetwork" />
</script>
<script id="viewNetwork" type="text/template">
  <g:render template="viewNetwork" />
</script>
<script id="networkEgressRule" type="text/template">
  <g:render template="networkEgressRule" />
</script>
<script id="networkIPAddressList" type="text/template">
  <g:render template="networkIPAddressList" />
</script>
<script id="viewNetworkIPAddress" type="text/template">
  <g:render template="viewNetworkIPAddress" />
</script>
<script id="networkIPFirewall" type="text/template">
  <g:render template="networkIPFirewall" />
</script>
<script id="networkIPLoadBalancing" type="text/template">
  <g:render template="networkIPLoadBalancing" />
</script>
<script id="networkIPPortForwarding" type="text/template">
  <g:render template="networkIPPortForwarding" />
</script>
<script id="makePayment" type="text/template">
  <g:render template ="makePayment" />
</script>
<script id="loadBalancerInfo" type="text/template">
  <g:render template ="loadBalancerInfo" />
</script>
<script id="portForwardingInfo" type="text/template">
  <g:render template ="portForwardingInfo" />
</script>
<script id="vpnInfo" type="text/template">
  <g:render template ="vpnInfo" />
</script>
