<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid"> 
  <!--<div class="row-fluid">-->
    <div class="span8" id="systemConfigPage">
      <form id="systemConfigForm" data-dojo-type="dijit.form.Form"
            class="form-horizontal">
        <div class="row-fluid header"> 
          <h3>Cloudstack Configuration</h3>
        </div>
        <div class="row-fluid">
          <div class="alert alert-danger" style="display: none" id="invalidMessage">
            <span>
               Please provide valid configuration to proceed further.
            </span>
          </div>
        </div>
        <div id="configWidgets">
        <div class ="control-group">
          
          <label class="control-label">URL:</label>
          <div class="controls updatable">
            <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                   id="cloudStackUrl" data-dojo-props="required: true,
                   invalidMessage:'Invalid Url', trim:'true', 
                   regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'">
            
          </div>
          <span id="cloudUrlLabel" class="hide_lable configLabel"></span>
        </div>
        <div class ="control-group">
          
          <label class="control-label">Single Sign On URL:</label>
          <div class="controls updatable">
            <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                   id="ssoUrl" data-dojo-props="required: true,
                   invalidMessage:'Invalid Url', trim:'true', 
                   regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'">
            
          </div>
          <span id="ssoUrlLabel" class="hide_lable configLabel"></span>
        </div>
        <div class ="control-group">
          <label class="control-label">Root API Key:</label>
          <div class="controls updatable">
            <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                   data-dojo-props="required: true, invalidMessage:'Invalid Root Key'"
                   accept="" id="cloudApi" name="">
            
          </div>
          <span id="cloudApiLabel" class="hide_lable configLabel"></span>
        </div>
          
        <div class ="control-group">
          <label class="control-label">Secret Key:</label>
          <div class="controls updatable">
            <input type="text" data-dojo-type ="dijit.form.ValidationTextBox"
                   data-dojo-props="required: true, invalidMessage:'Invalid Secret Key'" 
                   accept=""id="cloudSecretKey" name="">
            
          </div>
          <span id="cloudSecretKeyLabel" class="hide_lable configLabel"></span>
        </div>
    </div>
        <div class="buttonGroup" id="configButtonCollection">
          <button class="verifyButton verfitybtn" id="verifyButton" type="button" data-dojo-type ="dijit.form.ToggleButton" 
                onclick="FogWizardConfig.update();" data-dojo-props="checked: true">
          Verify
        </button>
        <button id="editButton" class="editbtn" type="button" data-dojo-type ="dijit.form.Button" 
                onclick="FogWizardConfig.edit();">
          Edit
        </button>
        </div>
      </form>
    </div>
    <div class="span4">
      <section class="well well-small">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum quis lacinia orci. 
        Suspendisse lacus orci, ullamcorper eget tincidunt eget, tempor vel lorem. Etiam consectetur ultrices lacus, 

        <ul>
          <li>sit amet dignissim neque sodales eget. Praesent vel feugiat ligula. Quisque sed blandit neque. </li>
          <li>Proin eleifend risus nec velit feugiat malesuada. Maecenas a aliquet lacus. </li>
          <li>Nam vel porta erat. Ut posuere, libero id condimentum dignissim. </li>
          <li>ligula elit vulputate magna, quis luctus libero risus at tortor. Curabitur tincidunt commodo est a elementum.</li>
        </ul>
      </section>
    </div>
  <!--</div>-->
  <div data-dojo-type="dijit.Dialog" id="configLoader" 
      class="span6">
    <div class="row-fluid" id="processMessage">
    <img src="images/configLoader.gif" class="span1 spacing"/>
    <p class="message span10">Fog panel verifying your cloud stack connectivity</p>
    </div>
    <div class="row-fluid hide_lable" id="successMessage">
    <img src="images/successMsg.jpg" class="span1 spacing"/>
    <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>
    <div class="row-fluid hide_lable" id="failMessage">
    <img src="images/errorMessage.jpg" class="span1 spacing"/>
    <p class="message span9 error">Error! Fogpanel and cloudstack is not integrated, 
      so please verify your config</p>
    </div>
    <div class="row-fluid" id="messageAction" style="display: none">
      <div class="span9"></div>
      <div class="span2 offset1">
        <button type="button" data-dojo-type="dijit.form.Button"
                onclick="FogWizardConfig.closeMessageBox()">OK</button>
      </div>     
    </div>
</div>
  <div data-dojo-type="dijit.Dialog" id="thankyouDialog" 
     class="span6">
  <div class="row-fluid" id="processMessage">
    <img src="images/thankYouMessage.jpg"/>
  </div>
  <div class="row-fluid" id="">
      <div class="span9"></div>
      <div class="span2 offset1">
        <button type="button" data-dojo-type="dijit.form.Button" onclick="FogSetupWizard.closeMessageBox()">OK</button>
      </div>     
    </div>
</div>

</div>
