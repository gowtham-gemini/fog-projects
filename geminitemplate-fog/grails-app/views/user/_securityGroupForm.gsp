<%@ page coentType="text/html;charset=UTF-8" %>
<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud"><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/securityGroup/list"><g:message code="menu.user.securityGroups"/></a></li>
            <li>/</li>
            <li id="securityGroupEdit"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12" id="">
        <div class="row header">
            <h3 id="securityGroupPage"></h3><div class="col-md-2 value_dollar pull-right" style="display: none"> <g:message code="default.valueIn"/>  <span  id="securityGroupCurrencyValue"></span></div>
        </div>
        <form id="securityGroupAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="securityGroupContent">
                <div class="row">
                    <div class="col-md-12">
                        <div class="control-group col-md-6 horizontalcontent">
                            <input type="hidden" id="currentSecurityGroupId" name="currentSecurityGroupId" value="">
                            <label for="securityGroupName" class="control-label">          
                                <g:message code="common.name"/> :  <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                    id="securityGroupName" data-dojo-props="required: 'true',
                                    invalidMessage: 'Invalid security group name', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                    promptMessage:'<g:message code="common.securityGroupName.promptMessage"/>'">
                                <div class="form_help_icon" style="top: 0; left: -25px;">
                                    <i class="icon-info-sign" id="securityGroupNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'securityGroupNameHelp', showDelay: 1"><g:message code="common.help.message.securityGroupName"/></div>
                                </div>
                            </div>
                           
                        </div>
                        <div class="control-group col-md-6 horizontalcontent">
                            <label for="securityGroupDescription"  class="control-label">         
                                <g:message code="common.description"/> :
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                    id="securityGroupDescription" data-dojo-props="required: 'true',
                                    invalidMessage: 'Invalid security group description', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                    promptMessage:'<g:message code="common.securityGroupDescription.promptMessage"/>'">
                                <div class="form_help_icon" style="top: 0; left: -25px;">
                                    <i class="icon-info-sign" id="securityGroupDescriptionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'securityGroupDescriptionHelp', showDelay: 1"><g:message code="common.help.message.securityGroupDescription"/></div>
                                </div>
                            </div>
                        </div>  
                    </div>
                    <div class="row">
                        <div class="col-md-8"></div>
                        <div class="col-md-4">
                            <!--<img class="hide_text" id="securityGroupLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>-->
                            <div class="col-md-7 pull-right">
                                <div id="securityGroupAddButtonDiv" class="col-md-3" style="display: block;" >
                                    <button id="securityGroupAddButton"  data-dojo-type="dijit.form.Button" onclick="SecurityGroup.add()" class="okbtn">
                                        <g:message code="common.ok"/>
                                    </button>
                                </div>        
                                <div id="securityGroupUpdateButtonDiv" style="display: none;" class="col-md-4">
                                    <button id="securityGroupUpdateButton"  data-dojo-type="dijit.form.Button" onclick="SecurityGroup.update()" class="okbtn">
                                        <g:message code="common.update"/>
                                    </button>
                                </div>
                                <div id="securityGroupCancelButtonDiv" class="col-md-4">
                                    <button id="securityGroupCancelButton"  data-dojo-type="dijit.form.Button" onclick="SecurityGroup.cancel()" class="cancelbtn">
                                        <g:message code="common.cancel"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="securityGroupLoader" style="color: black;" class="customDialgue col-md-6">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.createSecurityGroupInfo1' /></p></div>
            <div class="col-md-12" style="margin-left: 0"><p><g:message code='common.createSecurityGroupInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="SecurityGroup.gotoList()"><g:message code='common.gotoSecurityGroupList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<div data-dojo-type="dijit.Dialog" id="securityGroupUpdateLoader" style="color: black;" class="customDialgue col-md-6">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.updateSecurityGroupInfo1' /></p></div>
            <div class="col-md-12" style="margin-left: 0"><p><g:message code='common.updateSecurityGroupInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="SecurityGroup.gotoList()"><g:message code='common.gotoSecurityGroupList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
