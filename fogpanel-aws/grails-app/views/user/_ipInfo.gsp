<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#"><g:message code="menu.user.services"/></a></li>
            <li>/<li>
            <li><g:message code="menu.user.services.ipManager"/></li>
        </ul>
    </div> 
</div>
<div class="row-fluid"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="ipManagerTotalNetwork">0</span>
                    <g:message code="stat.user.totalNetwork"/>
                </div>
            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="accquiredIP">0</span>
                    <g:message code="common.accquiredIP"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <div class="span7 price_info_value">
                        <div class="number span12">
                                <sup id="accquiredIPCurrency" class="span3">USD</sup>
                                <span id="accquiredIPCost" class="span6">0</span>
                        </div>
                    </div>
                    <g:message code="common.cost"/>
                </div>            
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="span12" style="margin-top: 20px;">
        <div id="ipTabContainer" data-dojo-type="dijit/layout/TabContainer"  class="span12" style="overflow: visible;" tabStrip="true">
            <div onShow="PublicIpInfo.populateValues()" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.publicIP"/>" selected="true">
                <div class="table-wrapper products-table">
                    <div class="row-fluid">
                        <div class="alert alert-danger" style="display: none;" id="publicIPLimitReachedMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div> 
                    </div>
                    <div class="row-fluid filter-block">
                        <div class="pull-right span10 accquireip-form-boxcont">
                            <form class="form-horizontal" id="ipManagerAccquireIpForm" data-dojo-type="dijit.form.Form">
                              <div class="row-fluid">  
                                <div  class="span1"></div>
                                <div  class="span9" style="position: absolute; margin-top: 10px;">
                                    <div class="control-group span4"> 
                                        <label for="ipManagerZone" class="control-label">
                                            <g:message code='common.zone' />
                                            <span class="require">*</span>:
                                        </label>
                                        <div class="controls">
                                            <div id="ipManagerZoneList"></div>
                                        </div>
                                    </div>
                                    <div class="control-group span4" id="networkListEnable" style="display: none;"> 
                                        <label for="ipManagerZone" class="control-label">
                                            <g:message code='common.network' />
                                            <span class="require">*</span>:
                                        </label>
                                        <div class="controls">
                                            <div id="networkDivInIpManager"></div>
                                        </div>
                                    </div>
                                    <div class="control-group span4" id="vmListEnable" style="display: none;"> 
                                        <label for="ipManagerZone" class="control-label">
                                            <g:message code='common.instance' />
                                            <span class="require">*</span>:
                                        </label>
                                        <div class="controls">
                                            <div id="basicInstanceDivInIpManager"></div>
                                        </div>
                                    </div>
                                    <div class="span2" id="vmAccquireIpButtom"  style="display: none;"><button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.vmAcquireIPShow()"><g:message code="common.acquireIP"/></button></div>
                                    <div class="span2" id="networkAccquireIpButtom"  style="display: none;"><button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.networkAcquireIPShow()"><g:message code="common.acquireIP"/></button></div>
                                </div>
                            </div>
                            </form> 
                           
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="row-fluid">
                          <div id="userPublicIpList"></div>
                          <div class="alert alert-info hide" id="noPublicIpAddressMessageBox" style="display: none">
                            <i class="icon-exclamation-sign"></i> 
                            <g:message code="common.user.noNetworkIp"/>
                          </div>
                        </div>
                    </div>
                </div>
            </div>
            <div data-dojo-type="dijit/layout/ContentPane" id="privateIPTab" onShow="PrivateIpInfo.populateValues()" title="<g:message code="common.privateIP"/>" selected="true">
                <div class="table-wrapper products-table">
                    <div class="row-fluid">
                        <div class="alert alert-danger" style="display: none;" id="privateIPLimitReachedMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div> 
                    </div>
                    <div class="row-fluid filter-block">
                        <div class="pull-right span10 accquireip-form-boxcont">
                            <form class="form-horizontal" id="ipManagerAccquirePrivateIpForm" data-dojo-type="dijit.form.Form">
                                <div class="row-fluid">
                                    <div  class="span1"></div>
                                    <div  class="span9" id="nicAcquireIpPage" style="position: absolute; margin-top: 10px;">
                                        <div class="control-group span4"> 
                                            <label for="ipManagerPrivateZone" class="control-label">
                                                <g:message code='common.zone' />
                                                <span class="require">*</span>:
                                            </label>
                                            <div class="controls">
                                                <div id="ipManagerPrivateZoneList"></div>
                                            </div>
                                        </div>
                                        <div class="control-group span3"> 
                                            <label for="" class="control-label">
                                                <g:message code='common.instance' />
                                                <span class="require">*</span>:
                                            </label>
                                            <div class="controls">
                                                <div id="privateVMDivInIpManager"></div>
                                            </div>
                                        </div>
                                        <div class="control-group span3"> 
                                            <label for="" class="control-label">
                                                <g:message code='menu.user.vm.nics' />
                                                <span class="require">*</span>:
                                            </label>
                                            <div class="controls">
                                                <div id="nicDivInIpManager"></div>
                                            </div>
                                        </div>
                                        <div class="span2" id="ipInfoAccquirePrivateIpButtom">
                                            <button  type="button"  class="primarybtn pull-right" data-dojo-type="dijit.form.Button" onclick="PrivateIpInfo.nicAcquireIpShow()"><g:message code="common.acquireIP"/></button>
                                        </div>
                                    </div>
                                </div>
                            </form> 
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="row-fluid">
                        <div id="userPrivateIpList"></div>
                        <div class="alert alert-info hide" id="noPrivateIpAddressMessageBox" style="display: none">
                            <i class="icon-exclamation-sign"></i> 
                            <g:message code="common.user.noNetworkIp"/>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
            <div data-dojo-type="dijit/layout/ContentPane" onShow="PublicIpInfo.populateVPCIPValues()" title="<g:message code="common.vpcIP"/>" selected="true">
                <div class="row-fluid">
                    <div class="row-fluid">
                    <div id="userVpcIpList"></div>
                    <div class="alert alert-info hide" id="noVpcIpAddressMessageBox" style="display: none">
                        <i class="icon-exclamation-sign" style="float: left;"></i> 
                        <p id="noVPCMsgInfo" style="margin-top: 10px;"><g:message code="common.user.noNetworkIp"/></p>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="currentIPId">
<div data-dojo-type="dijit.Dialog" id="publicIpReleaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.releaseIP()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.closeReleaseIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="releaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PrivateIpInfo.releaseIP()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PrivateIpInfo.closeReleaseIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="ipManagerLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="ipDisableStaticNatDialog" title="<g:message code="common.disableStaticNat"/>" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.disableStaticNatMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.disableStaticNat()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.closeDisableStaticNat()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="ipEnableStaticNatDialog" title="<g:message code="common.enableStaticNat"/>" class="span4"> 
    <input type="hidden" id="currentIPNetworkId">
    <div class="row-fluid container">
    <form id="ipEnableStaticNatForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div class="span9" id="ipEnableStaticNatFormPage">
            <div class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="ipPortForwardingVM" class="control-label">
                            <g:message code="common.instance"/>:
                        </label>
                        <div class="controls ">
                            <div id="staticNatVMListInIpManager"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="portForwardingVMIp" class="control-label">
                            <g:message code="common.ipAddress"/>:
                        </label>
                        <div class="controls ">
                            <div id="staticNatVMIpDivInIpManager"></div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.enableStaticNat()"><g:message code="common.ok"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.closeEnableStaticNat()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkAcquireIpDialogInIpManager" title="<g:message code="common.acquireIP"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.user.ip.confirmNetwork' /></p></div>
            <div class="span12" style="margin-left: 0">              
                <div class="span9">
                    <p><g:message code="common.agreeThe"/><a href="#" onClick="PublicIpInfo.showCondition()"> <g:message code="signup.termsAndConditions"/></a></p>
                </div>
                <div class="span2">
                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="serviceIPInfoAgreement" name="agreement">     
                </div>                    
            </div>
            <div class=""> <span class="validation" id="ipConditionExceptionMsg" style="margin-left: 0"><g:message code="signup.termsCondition.info"/></span></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.acquireIp()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.closeAcquireIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="serviceAquireIPContent" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
    <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
        <div id="ipInfoContentArea">
        </div>                           
        <div class="row-fluid">
            <div class="span10">                
            </div>
            <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.cancelConditionBox()"><g:message code="common.ok"/></button></div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vmAcquireIpDialogInIpManager" title="<g:message code="common.acquireIP"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.user.ip.confirmNetwork' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.vmAcquireIp()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PublicIpInfo.closeAcquireIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="nicAcquireIpDialogInIpManager" title="<g:message code="common.acquireIP"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.user.ip.confirmNetwork' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="PrivateIpInfo.acquireIp()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="PrivateIpInfo.closeAcquireIpDialog()"><g:message code="common.cancel"/></button>
    </div>
</div>