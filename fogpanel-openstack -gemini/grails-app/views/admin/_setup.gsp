<!--div id="pad-wrapper"-->
  <div class="row-fluid">
    <div class="span10 welcome_wiz" data-dojo-type="FogPanel.Wizard" data-dojo-props="attrib:{steps:{'1' : 'Welcome', '2': 'Cloudstack Config','3' : 'Zones', '4': 'Computation', '5': 'Storage', '6': 'Miscellaneous'}, rotatorId:'rotator'}" data-dojo-id="setupWizard" id="setupWizard">
      <div class="step-content" data-dojo-type="dojox.widget.Rotator" data-dojo-id="rotator" data-dojo-props="transition:'dojox.widget.rotator.fade'" style="">
        <div class="wizardPane"><g:render template="setupWizardWelcome"/></div>
      <div class="wizardPane" id="systemConfigPage">
        <div style="position: relative"> 
          <g:render template="setupWizardConfiguration" /></div>
        </div>
      <div class="wizardPane"><g:render template="setupWizardZone" /></div>
      <div class="wizardPane"><g:render template="setupWizardComputingOffer" /></div>
      <div class="wizardPane"><g:render template="setupWizardDiskOffer" /></div>
      <!--<div class="wizardPane"><g:render template="setupWizardIpManagement" /></div>-->
      <div class="wizardPane"><g:render template="setupWizardMiscellaneous" /></div>
      </div>
    </div>
<div class="wizard-actions">
        <button type="button" id="wizardPrevButton" data-dojo-type="dijit.form.Button" class="wiz_prevbtn"  onclick="FogSetupWizard.previous();"> 
              <i class="icon-chevron-left"></i> Prev
          </button>
        <button type="button" id="wizardNextButton" data-dojo-type="dijit.form.Button" class="wiz_nextbtn"  data-last="Finish" onclick="FogSetupWizard.next();">
              Next <i class="icon-chevron-right"></i>
        </button>         
      </div>      
    </div>
  <!--/div-->
