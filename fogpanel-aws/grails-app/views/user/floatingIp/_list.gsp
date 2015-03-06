<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/accessAndSecurity" ><g:message code="menu.user.accessAndSecurity"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.floatingIpList"/></li>    
        </ul>
    </div>
</div>
<div class="row-fluid">
    <form id="listFloatingIpForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid">
                <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="floatingIpCurrencyValue"></span></div>
            </div>
            <div class="row-fluid filter-block">
                <div class="pull-right">
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="FloatingIp.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <button data-dojo-type="dijit.form.Button" onClick="FloatingIpInfo.createFloatingIpDialog()" class="defaultbtn" title="<g:message code='common.allocateIpToProject'/>"> + <g:message code="common.allocateIpToProject"/></button> 
                </div>
            </div>
            <div class="row-fluid">
                <div id="userFloatingIpList">  
                </div>
                <div class="alert alert-info hide text_gray" id="noFloatingIpMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="user.noFloatingIp"/>&nbsp;&nbsp;<a onClick="FloatingIpInfo.createFloatingIpDialog()"><g:message code="common.pleaseCreateOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="selectedFloatingIpId">
<div data-dojo-type="dijit.Dialog" id="releaseFloatingIpAlert" title="<g:message code='user.releaseFloatingIp.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
<!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><g:message code='user.releaseFloatingIp.message' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="FloatingIp.delete()">
                <g:message code='common.delete' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="FloatingIp.cancelDelete()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog" id="disassociateFloatingIpAlert" title="<g:message code='user.disassociateFloatingIp.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
<!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><g:message code='user.disassociateFloatingIp.message' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="FloatingIp.disassociate()">
                <g:message code='common.disassociate' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="FloatingIp.cancelDisassociate()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog"  id="releaseFloatingIpLoader" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span9">
            <div class="span12"><p><g:message code='common.releaseFloatingIp1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.releaseFloatingIp2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="FloatingIp.closeDeleteDialog()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<div data-dojo-type="dijit.Dialog" id="createFloatingIpDialog" title="<g:message code="common.user.createFloatingIp"/>"  class="customDialgue">   
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span10"> 
                <form class="form-horizontal" id="createFloatingIpForm" data-dojo-type="dijit.form.Form">                   
                    <div id="floatingIpListPage">
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.pool"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="externalNetworksIPList" class="selectOption"></div>
                                <div class="form_help_icon" id= "externalNetworksListIPHelp" style="top: -25px; left: -1px;display: block">
                                    <i class="icon-info-sign" id="externalNetworksListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'externalNetworksListHelp', showDelay: 1"><g:message code="common.help.message.associateFloatingIp"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="control-group hide_text" id="listFloatingIPBillingTypeDiv">
                                <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                                <div class="controls">
                                    <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="listFloatingIPBillingType" id="hourlylistFloatingIPBilling" value="hourly" onchange="FloatingIpInfo.enableMonthlyCost()"/>
                                    <label for="hourlylistFloatingIPBilling" style="float: left ; display: inline ;"><g:message code="common.hourly"/></label> 
                                    <input type="radio" data-dojo-type="dijit.form.RadioButton" name="listFloatingIPBillingType"  id="monthlylistFloatingIPBilling" value="monthly" onchange="FloatingIpInfo.enableMonthlyCost()"/> 
                                    <label style="float: left ; display: inline ;" for="monthlylistFloatingIPBilling" class=""><g:message code="common.monthly"/></label> 
                                    <div class="form_help_icon" style="left: 0px;">
                                        <i class="icon-info-sign" id="floatingIPBillingTypeHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'floatingIPBillingTypeHelp', showDelay: 1">
                                            <g:message code="user.floatingIP.billingType.toottip"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <div class="control-group" id="listFloatingIPCostInfo">
                            <label for="" class="control-label"><g:message code="common.cost"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div><span id="listFloatingIPCost" class="require"></span></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;display: block">
                                    <i class="icon-info-sign" id="externalNetworksCostHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'externalNetworksCostHelp', showDelay: 1"><g:message code="common.help.message.associateFloatingIpCost"/></div>
                                </div>
                            </div>
                        </div> 
                    </div> 


                </form> 
            </div>
        </div> 
    
        <div class="control-group span3 pull-right"> 
                <div id="addFloatingIpButton">
                    <button type="button"  onclick="FloatingIpInfo.create()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.create"/></button>
                    <button   class="cancelbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" FloatingIpInfo.cancelFloatingIp()"><g:message code="common.cancel"/></button>
                </div>
            <div class="span4" id="floatingIpCreateLoader" style="display: none"> 
                <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
            </div>
        </div>
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="associateDialog" title="<g:message code="common.user.maangeFloatingIp"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid">
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="associateFloatingIpForm">  
                    <div id="associateFloatingIpListPage"> 
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.ipAddress"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="floatIpAddressList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="floatIpAddressListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'floatIpAddressListHelp', showDelay: 1"><g:message code="common.help.message.ipAddressAssociate"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class="control-group">
                            <label for="" class="control-label"><g:message code="common.portAssociate"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="floatIPPortsList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="portsIPListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'portsIPListHelp', showDelay: 1"><g:message code="common.help.message.portAssociate"/></div>
                                </div>
                            </div>
                        </div> 
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span3 pull-right"> 
               <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="FloatingIp.associate()"><g:message code="common.update"/></button>
                <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="FloatingIp.cancelAssociateDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>