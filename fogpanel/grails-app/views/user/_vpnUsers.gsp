<div class="row-fluid"> 
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid">
        <div class="alert alert-info">
            <!--<i class="icon-exclamation-sign"></i>--> 
            <g:message code="common.vpnUser.msg1"/>:   <span id="vpnUserInfoIP" class="adminBolder" style="margin-right: 100px;"></span> <g:message code="common.vpnUser.msg2"/>:    <span id="vpnIPSpecKey" class="adminBolder"></span>.
        </div>
    </div>
    <form id="vpnUserForm" data-dojo-type="dijit.form.Form"  class="form-horizontal">	
        <div id="">
            <div class="row-fluid">                            
                <div class="span4 control-group field-box zone-cost-boxcont">
                    <div class="span12">
                        <label for="vpnUserName" class="control-label">
                            <g:message code="common.username"/>:
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="vpnUserName" data-dojo-props="promptMessage:'<g:message code="common.network.vpnUser.userNameMsg"/>', missingMessage: '<g:message code="common.network.vpnUser.userNameMsg"/>',required: true, placeHolder: '<g:message code="common.username"/>', regExp: '[a-zA-Z0-9@#+=.-_]{3,32}'">
                    </div>
                    </div>
                </div>
                <div class="span4 control-group field-box zone-cost-boxcont">
                    <label for="newVPNUserPassword" class="control-label">
                      <g:message code="common.password"/>:
                    </label>
                    <div class="controls">
                    <input type="password" value=""  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                             onkeyup="" onchange="" id="newVPNUserPassword" 
                             data-dojo-props="promptMessage:'<g:message code="common.network.vpnUser.passwordMsg"/>', 
                             missingMessage: '<g:message code="common.network.vpnUser.passwordMsg"/>',required: true,
                             placeHolder: '<g:message code="common.password"/>', regExp: '[a-zA-Z0-9@#+=.-_]{3,32}'">
                    </div>
                </div> 
                <div class="span1 field-box">                                          
                    <button type="button" data-dojo-type="dijit.form.Button" class="defaultbtn overflowLabel" onclick="ViewVPNUserInfo.addVPNUser()"><g:message code="common.add"/></button>                                  
                </div>
                <div class="span3"> <span style="display: none" class="require" id="vpnUserRequireMsg"><g:message code="common.vpnUsers.existMsg"/></span></div>
               
            </div>                 
        </div>
    </form>
</div>
<div class="row-fluid"><div class="span12"></div></div>
<div class="row-fluid"><div class="span12"></div></div>

<div class="row-fluid">
    <div class="span12">
        <div id="viewVPnUserGrid"></div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkIpDeleteVPNUserDialogue" class="span4" title="<g:message code="common.vpn.deleteUser.title"/>">
   <div class="row-fluid">        
        <div class="span1"><img src='images/errorMessage.jpg' title="<g:message code="common.vpn.deleteUser.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.vpn.deleteUser.confirm"/></p>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <div id="vpnDeleteOKButton" class="span2">
                <button  type="button" data-dojo-props="disabled: false" id="deleteVpnUserButton" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVPNUserInfo.deleteUser();"><g:message code="common.yes"/></button>
            </div>       
        <img id="vpnUserDeleteLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
        <div class="span2 offset1"><button id="vnpUserDeleteCancelButton"  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVPNUserInfo.cancelDeleteUser()"><g:message code="common.no"/></button></div>
        </div>
        <div class="span4"></div>                
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkIPVPNUserDialogLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>