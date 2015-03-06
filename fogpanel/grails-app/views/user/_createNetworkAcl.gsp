<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpc/dashboard"><g:message code="common.vpcDashboard"/></a></li>  
            <li>/</li>
            <li><a href="#/user/vpcSecurity"><g:message code="common.security"/></a></li>  
            <li>/</li>
            <li><a href="#/user/vpcSecurity/listNetworkAcl"><g:message code="common.networkACL"/></a></li>  
            <li>/</li>
            <li><g:message code="common.add"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div class="span12" id="">
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
                        <form id="userCreateNetworkAclForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                            <div class="row-fluid" id="createNetworkAclPage">
                                <div class="span12">
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclZone" class="control-label">
                                                <g:message code="user.createVM.zone.label"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls updatable elements">
                                                <div id="networkAclZoneList" class="selectOption"></div>
                                                <img id="generalNetworkACLZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclVpc" class="control-label">
                                                <g:message code="common.vpc"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls updatable elements">
                                                <div id="networkAclVpcList" class="selectOption"></div>
                                                <img id="generalNetworkACLVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclName" class="control-label">          
                                                <g:message code="common.name"/>: 
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                                id="networkAclName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                                                promptMessage:'<g:message code="common.name.placeHolder"/>'">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="control-group span12 horizontalcontent">
                                            <label for="networkAclDescription"  class="control-label">         
                                                <g:message code="common.desc"/> :
                                                <span class="require">*</span>
                                            </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                                id="networkAclDescription" data-dojo-props="required: 'true',
                                                invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                                promptMessage:'<g:message code="common.description.prompt"/>'"/>

                                            </div>
                                        </div> 
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span8">
                                        </div>
                                        <div class="span4" id="">
                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="AddNetworkAcl.add()" class="okbtn">
                                                <g:message code="common.ok"/>
                                            </button>
                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="AddNetworkAcl.cancel()" class="cancelbtn">
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

<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkAclLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>