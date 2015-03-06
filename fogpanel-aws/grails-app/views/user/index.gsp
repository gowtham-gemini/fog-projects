<script type="text/javascript" src = "${resource(dir: 'js/app')}/user.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/server.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/service.js"></script>        
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/cloud.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/billing.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/reportings.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/health.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/support.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/network.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/volume.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/snapshot.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/securityGroup.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/virtualDataCenter.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/router.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/floatingIp.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/image.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/notification.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/accessAndSecurity.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/vpc.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/vpcSecurity.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/tier.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/ec2.js"></script>
<script type="text/javascript" src = "${resource(dir: 'js/app/user')}/internetGateway.js"></script>
<!--<script type="text/javascript" src = "${resource(dir: 'js/app')}/accountSetup.js"></script>-->
<r:layoutResources/>    
<link rel="stylesheet" type="text/css"  href = "${resource(dir:'css/',  file: 'elements.css')}"/>	     
<link rel="stylesheet" type="text/css" href = "${resource(dir:'js/dojo-1.8/FogPanel/themes/VolumeListItem/', file: 'VolumeListItem.css')}"/>
<link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/dojox/mobile/themes/android/', file: 'common.css')}" /> 
<link rel="stylesheet" type="text/css" href="${resource(dir: 'js/dojo-1.8/FogPanel/themes/Pagination/', file: 'Pagination.css')}" /> 
<script id="createVM" type="text/template">
    <g:render template="createVm" />
</script>
<script id="snapshotList" type="text/template">
    <g:render template="snapshot/snapshotList" />
</script>
<script id="instanceInfoPage" type="text/template">
    <g:render template="server/instanceInfo" />
</script>
<script id="topic" type="text/template">
    <g:render template="notificationManager/topic" />
</script>
<script id="viewTopic" type="text/template">
    <g:render template="notificationManager/viewTopic" />
</script>
<script id="serverList" type="text/template">
    <g:render template="serverList" />
</script>
<script id="user_index" type="text/template">
    <g:render template="home" />
</script>
<script id="securityContainer" type="text/template">
    <g:render template="securityContainer" />
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
<script id="accountAlert" type="text/template">
    <g:render template="accountAlert" />
</script>
<script id="accountList" type="text/template">
    <g:render template="accountList" />
</script>
<script id="viewAccountInfo" type="text/template">
    <g:render template="viewAccountInfo" />
</script>
<script id="healthInfo" type="text/template">
    <g:render template="healthInfo" />
</script>
<script id="manualPayment" type="text/template">
    <g:render template="payment" />
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
<script id="userAlert" type="text/template">
    <g:render template ="userAlert" />
</script>
<script id="billingAlert" type="text/template">
    <g:render template ="billingAlert" />
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
<script id="cloudMenuContainer" type="text/template">
    <g:render template="cloudMenuContainer" />
</script>
<script id="addNetwork" type="text/template">
    <g:render template="addNetworkForm" />
</script>
<script id="viewNetwork" type="text/template">
    <g:render template="viewNetworkForm" />
</script>
<script id="viewSubnet" type="text/template">
    <g:render template="viewSubnetForm" />
</script>
<script id="viewPort" type="text/template">
    <g:render template="viewPortForm" />
</script>
<script id="createSubnet" type="text/template">
    <g:render template="createSubnetForm" />
</script>
<script id="securityGroupList" type="text/template">
    <g:render template="securityGroupList" />
</script>
<script id="rulesList" type="text/template">
    <g:render template="securityGroupRulesList" />
</script>
<script id="securityGroupForm" type="text/template">
    <g:render template="securityGroupForm" />
</script>
<script id="securityGroupRuleForm" type="text/template">
    <g:render template="securityGroupRuleForm" />
</script>
<script id="userVolumeList" type="text/template">
    <g:render template="volumes/list" />
</script>
<script id="volumeCreateForm" type="text/template">
    <g:render template="volumes/form" />
</script>
<script id="volumeViewForm" type="text/template">
    <g:render template="volumes/view" />
</script>
<script id="SSHKeyPage" type="text/template">
    <g:render template="SSHKeyPage" />
</script>
<script id="virtualDataCenter" type="text/template">
    <g:render template="virtualDataCenter" />
</script> 
<script id="accessAndSecurity" type="text/template">
    <g:render template="accessAndSecurity" />
</script>
<script id="routerList" type="text/template">
    <g:render template="router/list" />
</script>
<script id="routerView" type="text/template">
    <g:render template="router/view" />
</script>
<script id="makePaymentPage" type="text/template"> 
    <g:render template="makePaymentPage" />
</script>
<script id="floatingIpList" type="text/template"> 
    <g:render template="floatingIp/list" />
</script>
<script id="floatingIpView" type="text/template"> 
    <g:render template="floatingIp/view" />
</script>
<script id="imageList" type="text/template"> 
    <g:render template="image/list" />
</script>
<script id="addImage" type="text/template"> 
    <g:render template="image/form" />
</script>
<script id="viewImage" type="text/template"> 
    <g:render template="image/view" />
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
<script id="createVpc" type="text/template">
    <g:render template="createVpc" />
</script>
<script id="vpcList" type="text/template">
    <g:render template="vpcList" />
</script>
<script id="ec2Dashboard" type="text/template">
    <g:render template="ec2Dashboard" />
</script>
<script id="listSubnet" type="text/template">
    <g:render template="subnetList" />
</script>
<script id="internetGatewayList" type="text/template">
    <g:render template="internetGatewayList" />
</script>
