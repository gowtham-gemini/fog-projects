<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud" ><g:message code="menu.user.cloud"/></a></li>
    <li>/<li>
    <li><g:message code="menu.user.services.sshKey"/></li>
  </ul>
</div> 
</div>
<div class="row-fluid" id="pad-wrapper">
    <form class="form-horizontal" id="sshKeyForm" data-dojo-type="dijit.form.Form">
        <div class="span12" id="sshKeyPage">   
        <div class="span4 field-box control-group">
              <label for="sshKeyName" class="control-label">
                <g:message code="common.SSHKey"/> :
               <span class="require">*</span>
              </label>
              <div class="controls elements">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="sshKeyName" data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true', placeHolder: '<g:message code="common.SSHKey.prompt"/>', invalidMessage:'<g:message code="common.value.invalid"/>'">
                <div class="form_help_icon" style="top: 0; left: 0%;">
                  <i class="icon-info-sign" id="sshHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'sshHelp', showDelay: 1"><g:message code="common.SSHKey.message"/></div>
                </div>
              </div>
          
        </div>
        <div class="span4" id="createSSHKeyBtnDiv">
            <button id="sshKeyButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddSSHKey.createSShKey();">
                <g:message code="common.create"/>
            </button>
            <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AddSSHKey.cancel();">
             <g:message code="common.cancel"/>
            </button>    
        </div>
        <div class="span4" id="sshKeyLoader" style="display: none"> 
            <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
        </div>
        </div>
      </form> 
</div>
<div class="row-fluid form-wrapper" id="pad-wrapper"></div>
<div class="row-fluid form-wrapper">
    <div id="sshKeyGrid"></div>
    <div class="alert alert-info hide overflowLabel text_gray" id="noSSHKey" style="display: none">
      <i class="icon-exclamation-sign text_gray"></i> 
      <g:message code="common.user.noSSHKey"/>
    </div>
</div> 
<div data-dojo-type="dijit.Dialog" id="privateKeyDialog" title="" class="span8">
    <div class="form-horizontal">
        <input type="hidden" id="sshId">
        <div class="row-fluid">
        <textarea class="span12" rows="15" cols="200" id="sshPrivateKey"></textarea>
        </div>
        <div class="pull-right">
            <div id="deleteSSHKeyBtnDiv">
                <a class="btn-flat success new-product" id="sshKeyDownload" href="" title="Download"><g:message code="common.download"/></a> 
                <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="AddSSHKey.cancelViewDialog();">
                    <g:message code="common.cancel"/>
                </button>
            </div>
            <div class="span4" id="sshDeleteKeyLoader" style="display: none"> 
                <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
            </div>
        </div>
     </div>
</div>
<input type="hidden" id="currentSshId">
<div data-dojo-type="dijit.Dialog" id="privateKeyDeleteDialog" title="<g:message code='user.deletePrivateSshKey.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><g:message code='user.deletePrivateSshKey.confirm' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="AddSSHKey.deletePrivateKey()" >
                <g:message code='common.delete' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="AddSSHKey.cancelPrivateKeyDeleteDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
<input type="hidden" id="currentSshKeyName">
<div data-dojo-type="dijit.Dialog" id="deleteSshDialogAlert" title="<g:message code='user.deleteSsh.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><g:message code='user.deleteSsh.confirm' />  </div>            
        </div>        
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="AddSSHKey.sshKeyDelete()" >
                <g:message code='common.delete' />   
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="AddSSHKey.cancelDeleteAlert()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
        </div>
    </div>                        
</div>
