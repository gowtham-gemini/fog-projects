<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li> 
    <li>/<li>
    <li><a href="#/admin/firewall"><g:message code="user.createVM.firewall.label"/></a></li>
    <li>/</li>
    <li><g:message code="common.add"/></li>
  </ul>
</div>
</div> 

<div  class="new-user">
  <div class="row-fluid header">
    <h3>Create Firewall</h3>
</div>
<div class="row-fluid form-wrapper">
  <div class="span4 createvm-banner">
  	<img src="${resource(dir: 'css/theme/fog-classic/images/cloud_icons')}/firewall_large_icon.png" height="256" width="300">
    </div>
    <!-- left column -->
    <div class="span6" id="adminSecurityPage">
      
        <!--<div class="container">-->
            <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="firewallAddForm">
              <!--<div class="span12 field-box"></div>-->
                <div class="span12 field-box control-group">
                    <label class="control-label" ><g:message code="common.baseOS"/>:<span class="require">*</span></label>
                    <div class="controls">
                    <input type="radio" name="baseOs" id="linuxOs" 
                     data-dojo-type="dijit.form.RadioButton" value="Linux" onclick="">          
                    <label for="linuxOs"><g:message code="common.linux"/></label> 
                    <input type="radio" name="baseOs" data-dojo-type="dijit.form.RadioButton"  
                 id="windowsOs" value="Windows" onclick="">           
                  <label for="windowsOs"><g:message code="common.windows"/></label> 
                  <span class="validation" id="osExceptionExceptionMsg"><g:message code="common.pleaseChooseBseOS"/></span>
                    </div>
                </div>
                <div class="span12 field-box control-group">
                    <label for="dashboardSecurityGoupsName" class="control-label">                      
                      <g:message code="common.name"/>
                      <span class="require">*</span>
                    </label>
                  <div class="controls">
                    <input type="text" 
                            data-dojo-type="dijit.form.ValidationTextBox" 
                            accept=""data-dojo-props="invalidMessage: '<g:message code="common.name.invalid"/>',
                            required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                            regExp: '[-a-zA-Z0-9 ]{4,50}'" 
                            name="diskName" id="adminSecurityGoupsName">  
                  </div>
                </div>
                <div class="span12 field-box control-group">
                    <label for="dashboardSecurityGoupsDescription" class="control-label">                     
                      <g:message code="common.description"/>
                       <span class="require">*</span>
                    </label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                        data-dojo-props="promptMessage:'<g:message code="common.description.prompt"/>', 
                        invalidMessage: '<g:message code="common.description.invalid"/>', required: 'required',
                        placeHolder: '<g:message code="common.description.prompt"/>', regExp: '[|a-z0-9A-Z, ]{4,50}'"
                        name="displayText" id="adminSecurityGoupsDescription">
                  </div>
                </div>
              <div class="span11 field-box">                            
<!--                <input type="reset" value="Cancel" class="reset" onclick="FirewalInfo.cancel()">
                    <span>OR</span>-->
                <button id="firewalButton" type="button" data-dojo-type="dijit.form.Button" onclick="FirewalInfo.add()" class="defaultbtn offset11"><g:message code="common.add"/></button>
                    <img id="firewalLoader" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" 
             alt="<g:message code="common.reset"/>" height="20" width="20">
              </div>
            </form>
        <!--</div>-->
    </div>        
</div>
</div>