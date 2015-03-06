<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpc/dashboard"><g:message code="common.dashboard"/></a></li>  
            <li>/</li>
            <li><a href="#/user/vpc/list"><g:message code="common.vpc"/></a></li>  
            <li>/</li>
            <li><g:message code="common.add"/></li>
        </ul>
    </div>
</div>

<div class="row-fluid">
    <div class="span12" id="userNetworkPage">
        <div class="row-fluid header">
            <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
        </div>
        <div class="new-user createVm"> 
            <div class="row-fluid form-wrapper">
                <div class="span3 createvm-banner">
                    <img src="${resource(dir: 'images')}/cloud_comput_icon.png" height="151" width="238">
        <!--            <h2 class="alphaStyle overflowLabel"><g:message code="user.createVM"/></h2>
                    <h2 class="alphaStyle overflowLabel"><g:message code="common.customDisk"/></h2>
                    <h2 class="alphaStyle overflowLabel"><g:message code="common.template"/></h2>
                    <h2 class="alphaStyle overflowLabel"><g:message code="common.firewall"/></h2>-->
                </div>
                <div class="span7 createvm-form with-sidebar">
                    <div class="container">
                        <form id="userCreateVpcForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                            <div class="row-fluid" id="createVpcPage">
                                <div class="span12">
                                    <div class="row-fluid hide_text" id="addVpcCloudstackException">
                                        <div class="span12 alert alert-error"><i class="icon-remove-sign"></i><span id="addVpcCloudstackExceptionMsg"></span></div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="vpcName" class="control-label">          
                                                <g:message code="common.name"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                                id="vpcName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                                                promptMessage:'<g:message code="common.name.placeHolder"/>'">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="vpcDescription"  class="control-label">         
                                                <g:message code="common.desc"/> :
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                                id="vpcDescription" data-dojo-props="required: 'true',
                                                invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                                promptMessage:'<g:message code="common.description.prompt"/>'"/>

                                            </div>
                                        </div> 
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="vpcZoneList" class="control-label">
                                                <g:message code="user.createVM.zone.label"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls updatable elements">
                                                <div id="vpcZoneList" class="selectOption"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="vpcCidr"  class="control-label">         
                                                <g:message code="common.vpcCidr"/> :
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                                    data-dojo-props="invalidMessage:'Invalid CIDR',required: 'true',
                                                        regExp: '[0-9/.,]{0,160}',
                                                        placeHolder: '<g:message code="common.firewall.cidr.prompt"/>',
                                                        missingMessage:'<g:message code="common.firewall.cidr.invalid"/>',
                                                        promptMessage: '<g:message code="common.firewall.cidr.prompt"/>'"  
                                                        id="vpcCidr" name="vpcCidr">

                                            </div>
                                        </div> 
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="vpcDomain"  class="control-label">         
                                                <g:message code="common.vpcDomain"/> :
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                                id="vpcDomain" data-dojo-props="invalidMessage: '<g:message code="common.vpnDomain.invalid"/>', placeHolder: '<g:message code="common.vpnDomain.placeHolder"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                                promptMessage:'<g:message code="common.vpnDomain.prompt"/>'"/>

                                            </div>
                                        </div> 
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkZoneList" class="control-label">
                                                <g:message code="common.vpcOffering"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls updatable elements">
                                                <div id="vpcOfferList" class="selectOption"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span8">
                                        </div>
                                        <div class="span4" id="createVpcButtonDiv">
                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="AddVpc.add()" class="okbtn">
                                                <g:message code="common.ok"/>
                                            </button>
                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="AddVpc.cancel()" class="cancelbtn">
                                                <g:message code="common.cancel"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="span2"></div>
            </div>    
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcLoader" title="<g:message code='common.vm.buildingVM' />" style="color: black;" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
          <div class="span9">
              <div class="span12"><p><g:message code='common.vpc.buildingVMMsg1' /></p></div>
              <div class="span12" style="margin-left: 0"><p><g:message code='common.vpc.buildingVMMsg2' /></p></div>              
          </div>          
      </div>
          <div class="row-fluid">        
              <a class="btn-flat default" onclick="AddVpc.gotoVPCList()" id="tierVmButton"><g:message code='common.gotoVPCList' /></a>
              <a class="btn-flat default" onclick="AddVpc.gotoVPCDashboard()"><g:message code='common.gotoVPCDashboard' /></a>
              <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="CreateVMInfo.cancelPopup()" style="display: none">
                  <g:message code='common.cancel' />
              </button>         
          </div>   
</div>
