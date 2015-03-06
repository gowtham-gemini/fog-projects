<%@ page coentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/virtualDataCenter" ><g:message code="menu.user.virtualDataCenter"/></a></li>
            <li>/</li>
            <li><a href="#/user/network/list"><g:message code="menu.user.networklist"/></a></li>
            <li>/</li>
            <li><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div class="new-user">
         <div class="row-fluid header createVm">     
         </div>
        <div class="row-fluid form-wrapper">            
            <div class="span3 createvm-banner">
                <img src="${resource(dir: 'images')}/createnw_logo.png" height="151" width="238">
                <h2 class="alphaStyle overflowLabel"><g:message code="common.createNetwork"/></h2>
		<h2 class="alphaStyle overflowLabel"><g:message code="common.subnet"/></h2>
		<h2 class="alphaStyle overflowLabel"><g:message code="common.gatewayIP"/></h2>		
            </div>
            <div class="span6 with-sidebar">
                <div class="container">
                    <form id="adminNetworkAddForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
                        <div id="networkContent">                           
                            <div class="span12 field-box">
                                <input type="hidden" id="currentNetworkId" name="currentNetworkId" value="">
                                <label for="networkName" class="control-label">          
                                    <g:message code="common.networkName"/>: <span class="require">*</span>
                                </label>
                                <div class="controls elements">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                        id="networkName" data-dojo-props="required: 'true',
                                        invalidMessage: '<g:message code="common.invalidMessage.networkName"/>', placeHolder: '<g:message code="common.networkName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                        promptMessage:'<g:message code="common.networkName.promptMessage"/>'">
                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                        <i class="icon-info-sign" id="networkNameHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkNameHelp', showDelay: 1"><g:message code="common.help.message.networkName"/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="span12 field-box">
                                <label for="subnetName"  class="control-label">         
                                    <g:message code="common.subnetName"/> :
                                    <span class="require">*</span>
                                </label>
                                <div class="controls elements">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                        id="subnetName" data-dojo-props="required: 'true',
                                        invalidMessage: '<g:message code="common.subnetName.invalid"/>', placeHolder: '<g:message code="common.subnetName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                        promptMessage:'<g:message code="common.subnetName.promptMessage"/>'"/>
                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                        <i class="icon-info-sign" id="subnetNameHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'subnetNameHelp', showDelay: 1"><g:message code="common.help.message.subnetName"/></div>
                                    </div>
                                </div>
                            </div> 
                            <div class="span12 field-box">
                                <label for="networkAddress"  class="control-label">         
                                    <g:message code="common.networkAddress"/>: <span class="require">*</span>
                                </label>
                                <div class="controls elements">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                        data-dojo-props="required:'true', regExp: '[0-9/.]{0,16}', invalidMessage:'<g:message code="common.networkAddress.invalid"/>',
                                        placeHolder: '<g:message code="common.networkAddress"/>', 
                                        promptMessage:'<g:message code="common.networkAddress.promptMessage"/>'" id="networkAddress">  
                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                        <i class="icon-info-sign" id="networkAddressHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkAddressHelp', showDelay: 1"><g:message code="common.help.message.networkAddress"/></div>
                                    </div>
                                </div>
                            </div> 
                        <!--</div>-->
                        <!--<div class="row-fluid">-->      
                            <div class = "span12 field-box">
                                <label for="ipVersion" class="control-label">              
                                    <g:message code="common.ipVersion"/>: 
                                    <span class="require">*</span>
                                </label>
                                <div class="controls updatable">
                                    <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="ipVersions">
                                        <option value=4 selected="selected">IPv4</option>
                                        <option value=6>IPv6</option>
                                    </select>
                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                        <i class="icon-info-sign" id="ipVersionHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ipVersionHelp', showDelay: 1"><g:message code="common.help.message.ipVersion"/></div>
                                    </div>
                                </div>
                            </div>
                            <div class = "span12 field-box">
                                <label for="gatewayIp" class="control-label">              
                                    <g:message code="common.gatewayIp"/>:
                                </label>
                                <div class="controls updatable">
                                     <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                    data-dojo-props="invalidMessage:'<g:message code="common.gatewayIp.invalid"/>',
                                    regExp: dojox.validate.regexp.ipAddress, placeHolder: '<g:message code="common.gatewayIp"/>', 
                                    promptMessage:'<g:message code="common.gatewayIp.promptMessage"/>'" id="gatewayIp">
                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                        <i class="icon-info-sign" id="gatewayIpHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'gatewayIpHelp', showDelay: 1"><g:message code="common.help.message.gatewayIp"/></div>
                                    </div>
                                </div>
                            </div>
                            <div class = "span12 field-box">
                                <label for="adminStateUp" class="control-label">              
                                    <g:message code="common.adminState"/>:
                                </label>
                                <div class="controls updatable">
                                     <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                        data-dojo-props="checked: true" id="networkAdminState">
                                    <div class="form_help_icon" style="top: 0; left: 0%;">
                                        <i class="icon-info-sign" id="networkAdminStateHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'networkAdminStateHelp', showDelay: 1"><g:message code="common.help.message.networkAdminState"/></div>
                                    </div>
                                </div>
                            </div>
                            <div class="span12 field-box control-group hide_text" id="networkBillingTypeDiv">
                                <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                                <div class="controls">
                                    <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="billingType" id="hourlyNetworkBilling" value="hourly" onchange="NetworkInfo.enableMonthlyCost()"/>
                                    <label for="hourlyNetworkBilling"><g:message code="common.hourly"/></label> 
                                    <input type="radio" data-dojo-type="dijit.form.RadioButton" name="billingType"  id="monthlyNetworkBilling" value="monthly" onchange="NetworkInfo.enableMonthlyCost()"/> 
                                    <label for="monthlyNetworkBilling" class=""><g:message code="common.monthly"/></label> 
                                    <div class="form_help_icon" style="left: 0px;">
                                        <i class="icon-info-sign" id="createVmBillingTypeHelp"></i>
                                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmBillingTypeHelp', showDelay: 1">
                                            <g:message code="user.network.billingType.toottip"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <!--</div>-->
                        <!--<div class="row-fluid">-->
<!--                            <div class="span9"></div>-->
                            <div class="span12 field-box">
                                <img class="hide_text" id="networkLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                                <div id="networkCancelButtonDiv" class="span4 pull-right">
                                    <button id="networkCancelButton"  data-dojo-type="dijit.form.Button" onclick="NetworkInfo.cancel()" class="cancelbtn">
                                        <g:message code="common.cancel"/>
                                    </button>
                                </div>
                                <div id="networkAddButtonDiv" class="span2 pull-right" style="display: block;" >
                                    <button   id="networkAddButton"  data-dojo-type="dijit.form.Button" onclick="NetworkInfo.add()" class="okbtn">
                                        <g:message code="common.ok"/>
                                    </button>
                                </div>        
                                <div id="networkUpdateButtonDiv" style="display: none;" class="span2 pull-right">
                                    <button   id=""  data-dojo-type="dijit.form.Button" onclick="NetworkInfo.update()" class="okbtn">
                                        <g:message code="common.apply"/>
                                    </button>
                                </div>
                            </div>
                        <!--</div>-->
                    <!--</div>-->
                </div>                                
            </form>
            </div>
            </div>
            <div class="span3 offeringInfo">
                <div class="new_user_form inline-input">     
                  <div class="row-fluid">    
                      <!--<div class="span4"><a onclick=""><g:message code='common.rateCard' /></a><g:render template="rateCard" /></div>-->
                      <div  class="span6 create_vm_cost offset6"><div class="value_dollar"><g:message code="default.valueIn"/><span id="createVolumeCurrecy" style="color: #374859; float: none"></span></div></div>             
                  </div>
                  <div class="row-fluid costInfoDesign" id="networkInfo">
                      <div class="span12">
                          <h3><g:message code="common.networkCostInfo"/></h3>
                      </div>
                      <div class="span12">       
                          <label><g:message code="common.networkCost"/>:</label>
                          <span id="networkCost"></span>
                      </div>
                        <div class="span12 hide_text" id="networkMonthCostDiv">       
                          <label><g:message code="common.monthCost"/>:</label>
                          <span id="networkMonthCost"></span>
                      </div>
                      <div class="span12">
                        <label><g:message code="common.bandwidthReadCost"/>:</label>
                        <span id="bandwidthRead"></span>
                      </div>
                      <div class="span12">
                        <label><g:message code="common.bandwidthWriteCost"/>:</label>
                        <span id="bandwidthWrite"></span>
                      </div>                                          
                    </div> 
                </div>
            </div>  
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="pullNetworkLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createNetworkInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createNetworkInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="NetworkInfo.gotoList()"><g:message code='common.gotoNetworkList' /></a>
    </div>
</div>