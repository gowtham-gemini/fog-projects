<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
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
                <g:message code="common.SSHKey"/>
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
        <div class="span4">
            <div id="createSSHKeyBtnDiv">
              <button id="sshKeyButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddSSHKey.createSShKey();">
                <g:message code="common.create"/>
                </button>
                <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AddSSHKey.cancel();">
                 <g:message code="common.cancel"/>
                </button>
            </div>            
        </div>
        <div class="span6 pull-right" id="sshKeyLoader" style="display: none"> 
            <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
        </div>
        </div>
      </form> 
</div>
<div class="row-fluid form-wrapper" id="pad-wrapper"></div>
<div class="row-fluid form-wrapper">
      <div id="sshKeyGrid"></div>
</div> 
<div data-dojo-type="dijit.Dialog" id="privateKeyDialog" title="<g:message code="user.vm.changeSSHKey"/>" class="span8">
    <div class="form-horizontal">
        <input type="hidden" id="sshId">
        <div class="row-fluid">
        <textarea class="span12" rows="15" cols="200" id="sshPrivateKey"></textarea>
        </div>
        <div class="pull-right">
            <div id="createSSHKeyBtnDiv">
                <button id="" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddSSHKey.sshKeyDelete();">
                    <g:message code="common.delete"/>
                </button>
                <a class="btn-flat success new-product" id="sshKeyDownload" href="" title="Download"><g:message code="common.download"/></a>    
            </div>            
        </div>
     </div>
</div>
