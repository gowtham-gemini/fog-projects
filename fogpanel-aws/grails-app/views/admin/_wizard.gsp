<div id="pad-wrapper">
  <div class="row-fluid">
    <div class="span12" data-dojo-type="FogPanel.Wizard"  
            data-dojo-props="attrib:{steps:{'1' : 'Welcome', 
            '2': 'Cloudstack Config','3' : 'Zones', '4': 'Computation', 
            '5': 'Storage', '6': 'Miscellaneous'}, rotatorId:'rotator'}" data-dojo-id="setupWizard" id="">
      <div class="step-content" data-dojo-type="dojox.widget.Rotator" data-dojo-id="rotator" 
           data-dojo-props="transition:'dojox.widget.rotator.pan'">
        <div class="wizardPane">Page1</div>
        <div class="wizardPane">Page2</div>       
        <div class="wizardPane"><g:render template="testWizardTempPage3" /></div>
        <div class="wizardPane">Page4</div>
        <div class="wizardPane">Page5</div>
        <div class="wizardPane">Page6</div>        
      </div>'
      <div class="wizard-actions">
        <button type="button" id="wizardPrevButton" data-dojo-type="dijit.form.Button"   onclick="rotator.prev(); DashboardWizard.previous();"> 
              <i class="icon-chevron-left"></i> Prev
          </button>
        <button type="button" id="wizardNextButton" data-dojo-type="dijit.form.Button"  data-last="Finish" onclick="DashboardWizard.next();">
              Next <i class="icon-chevron-right"></i>
        </button>         
      </div>
    </div>
      
    </div>
  </div>
