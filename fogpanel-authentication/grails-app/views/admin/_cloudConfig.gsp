<div class="row-fluid"> 
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/cloudStack"><g:message code="menu.admin.configuration.cloudStack"/></a></li>
    <li>/</li>
    <li><g:message code="common.systemConfig"/></li>   
  </ul>
</div>
</div>

<div class="row-fluid"> 
    <div class="span8" id="dashboardSystemConfigPage">
      <form id="dashboardSystemConfigForm" data-dojo-type="dijit.form.Form"
            class="form-horizontal">
        <div class="row-fluid">
          <div class="alert alert-danger" style="display: none" id="dashboardInvalidMessage">
            <span>
                <g:message code="admin.missingConfigError"/>
            </span>
          </div>
        </div>
        <div id="dashboardConfigWidgets">
        <div class ="control-group">          
          <label for="dashboardUrl" class="control-label"><g:message code="common.URL"/>:<span class="require">*</span></label>
          <div class="controls">
            <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                   id="dashboardUrl" data-dojo-props="required: true,
                   invalidMessage:'Invalid Url', trim:'true', 
                   regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+', disabled: true">
            
          </div>
          <span id="dashboardUrlLabel" class="hide_lable configLabel"></span>
        </div>
        <div class ="control-group">
          
          <label for="dashboardssoUrl" class="control-label"><g:message code="common.singleSignOnURL"/>:<span class="require">*</span></label>
          <div class="controls">
            <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                   id="dashboardssoUrl" data-dojo-props="required: true,
                   invalidMessage:'Invalid Url', trim:'true', 
                   regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+', disabled: true">
            
          </div>
          <span id="dashboardssoUrlLabel" class="hide_lable configLabel"></span>
        </div>
        <div class ="control-group">
          <label for="dashboardApi" class="control-label"><g:message code="common.APIKey"/>:<span class="require">*</span></label>
          <div class="controls">
            <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                   data-dojo-props="required: true, invalidMessage:'Invalid Root Key', disabled: true"
                   accept="" id="dashboardApi" name="">
            
          </div>
          <span id="dashboardApiLabel" class="hide_lable configLabel"></span>
        </div>
          
        <div class ="control-group">
          <label for="dashboardSecretKey" class="control-label"><g:message code="common.secretKey"/>:<span class="require">*</span></label>
          <div class="controls">
            <input type="text" data-dojo-type ="dijit.form.ValidationTextBox"
                   data-dojo-props="required: true, invalidMessage:'Invalid Secret Key', disabled: true" 
                   accept=""id="dashboardSecretKey" name="">
            
          </div>
          <span id="dashboarSecretKeyLabel" class="hide_lable configLabel"></span>
        </div>
    </div>
<div class="buttonGroup" id="dashboardButtonCollection" style="display: none">
          <button class="verifyButton verfitybtn" id="dashboardVerifyButton" type="button" data-dojo-type ="dijit.form.ToggleButton" 
                onclick="DashboardConfig.update();" data-dojo-props="checked: true">
          Verify
        </button>
        <button id="editButton" class="editbtn" type="button" data-dojo-type ="dijit.form.Button" 
                onclick="DashboardConfig.edit();">
          <g:message code="admin.edit"/>
        </button>
        </div>
      </form>
    </div>
    <div class="span4">
      <section class="well well-small">
          <g:message code="admin.cloudConfigInfo"/>
        <ul>
          <li><g:message code="admin.cloudConfigURLInfo"/></li>
          <li><g:message code="admin.cloudConfigSingleSignOnInfo"/></li>
          <li><g:message code="admin.cloudConfigAPIKeyInfo"/></li>
          <li><g:message code="admin.cloudConfigSecretkeyInfo"/></li>
        </ul>
      </section>
    </div>
  <!--</div>-->
  <div data-dojo-type="dijit.Dialog" id="dashboardConfigLoader" 
      class="span6">
    <div class="row-fluid" id="dashboardProcessMessage">
    <img src="images/configLoader.gif" class="span1 spacing"/>
    <p class="message span10">Fog panel verifying your cloud stack connectivity</p>
    </div>
    <div class="row-fluid" id="dashboardSuccessMessage">
    <img src="images/successMsg.jpg" class="span1 spacing"/>
    <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>
    <div class="row-fluid" id="dashboardFailMessage">
    <img src="images/errorMessage.jpg" class="span1 spacing"/>
    <p class="message span9 error">Error! Fogpanel and cloudstack is not integrated, 
      so please verify your config</p>
    </div>
    <div class="row-fluid" id="dashboardMessageAction" style="display: none">
      
      <div class="pull-right">
        <button  class="defaultbtn" type="button" data-dojo-type="dijit.form.Button"
                onclick="DashboardConfig.closeMessageBox()">OK</button>
      </div>     
    </div>
</div>
 
</div>
