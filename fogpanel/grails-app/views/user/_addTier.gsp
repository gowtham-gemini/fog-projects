<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><a href="#/user/tier/list"><g:message code="common.tiers"/></a></li>    
        <li>/</li>
        <li><g:message code="common.addTier"/></li>
      </ul>
  </div>
</div>
<div class="row-fluid">
    <div class="span12" id="userNetworkPage">
        <div class="row-fluid header">
            <h3><g:message code="common.technicalInfo"/></h3><div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
        </div>
        <div class="new-user createVm"> 
            <div class="row-fluid form-wrapper">
                <div class="span3 createvm-banner">
                    <img src="${resource(dir: 'images')}/cloud_comput_icon.png" height="151" width="238">        
                </div>
                <div class="span7 createvm-form with-sidebar">
                    <div class="container">
                        <form id="userCreateTierForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                            <div id="vpcGeneralAddTierPageDiv" class="form-horizontal">
                <div class="row-fluid hide_text" id="listAddTierCloudstackException">
                    <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="listAddTierCloudstackExceptionMsg" class="span10"></span></div>
                </div> 
                <div class="row-fluid">
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        id="generalTierName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{3,200}',
                        promptMessage:'<g:message code="common.name.placeHolder"/>'">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.zone"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                          <div id="generalTierZoneList"></div>
                          <img id="generalTierZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="tierName" class="control-label">
                             <g:message code="common.vpc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                         <div id="generalVPCZoneList"></div>
                         <img id="generalTierVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                        <label for="tierNetworkOffering" class="control-label">
                            <g:message code="common.networkoffer"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="GeneralNetworkOfferingList" class="selectOption"></div>
                          <img id="generalTierNWOfferLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="tierGateway" class="control-label">
                             <g:message code="common.gateway"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="generalTierGateway" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.gateway.invalid"/>', placeHolder: '<g:message code="common.gateway"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.gateway.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group">
                         <label for="tierNetmask" class="control-label">
                             <g:message code="common.netmask"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="generalTierNetmask" data-dojo-props="required: 'true',
                        invalidMessage: '<g:message code="common.netmask.invalid"/>', placeHolder: '<g:message code="common.netmask"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                        promptMessage:'<g:message code="common.netmask.prompt"/>'"/>
                      </div>
                    </div>
                    <div class="control-group" id="generalAclDiv">
                        <label for="tierAcl" class="control-label">
                            <g:message code="common.ACL"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls ">
                          <div id="generalTierAclList" class="selectOption"></div>
                          <img id="generalTierACLLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                        </div>
                    </div>
                    <div class="control-group" style="display: none;">Add Zone List Drop Down for Outer page use</div>
                    <div class="control-group" style="display: none;">Add VPC List Drop Down for Outer page use</div>
                </div>
            </div> 
                        </form>
                    </div>
                        <div class="row-fluid">
                            <div class="span8">
                                <span style="display: none" class="offset3 require" id="addTierErrorMessage"><g:message code="user.createVM.required"/></span>
                            </div>
                            <div class="pull-right span4">            
                                <button data-dojo-type="dijit.form.Button" onclick="AddVPCInfo.addTier();" class="primarybtn">
                                <g:message code="common.ok"/>
                            </button>
                            <button data-dojo-type="dijit.form.Button" onclick="AddVPCInfo.cancelTier();" class="cancelbtn">
                                <g:message code="common.cancel"/>
                            </button>
                        </div>
                        </div>
                </div>
                <div class="span2"></div>
            </div>    
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="addTierLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
