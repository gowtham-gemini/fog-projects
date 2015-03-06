
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span8">
    <h3>IP Management</h3> 
    <form id="ipManagementForm" data-dojo-type="dijit.form.Form"
          class="form-horizontal">
      <div class="row-fluid">
        <div class="control-group span5">
          <input type="hidden" id="currentIpListItem">
          <label for="diskOfferZoneList" class="control-label">
            Zone: 
          </label>
          <div class="controls">
            <div id="ipZoneList"></div>
          </div>
        </div>
        <div class="control-group span5">
          <label for="diskOfferPodList"  class="control-label">
            Pod:
          </label>
          <div class="controls">
            <div id="ipPodList"></div>
          </div>
        </div> 
      </div>
      </form>
    <div class="fluid">
      <form class="form-horizontal" id="ipManagementPageForm" data-dojo-type="dijit.form.Form">
        <div class="row-fluid" id="ipManagementPage"> 
        <div class="control-group span8"> 
          <label for="ipName" class="control-label">
            <span class="require">*</span>
            IP Block Name:
          </label>
          <div class="controls">
            <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: 'invalid IP Block Name',
                   required: 'required', placeHolder: 'Enter IP Block Name', 
                   regExp: '[a-zA-Z0-9]{4,20}', propercase: true, 
                   promptMessage:'Enter IP Block Name'" 
                   name="diskName" id="ipName">  
          </div>
        </div> 
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="startIp" class="control-label">
              <span class="require">*</span>
              Start Ip:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid Start Ip',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'Start Ip', promptMessage:'Start Ip'"  
                     id="startIp" onblur="FogWizardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="endIp" class="control-label">
              <span class="require">*</span>
              End Ip:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid End Ip',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'End Ip', 
                     promptMessage:'End Ip'" id="endIp" onblur="FogWizardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div>
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="ipNetmask" class="control-label">
              <span class="require">*</span>
              Netmask
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid Netmask',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'Netmask', 
                     promptMessage:'Netmask'" id="ipNetmask" onblur="FogWizardIpManagement.validateNetmask(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="ipGateway" class="control-label">
              <span class="require">*</span>
              Gateway:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid Gateway',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'Gateway',  promptMessage:'Gateway'"  
                     id="ipGateway" onblur="FogWizardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="ipDNS1" class="control-label">
              DNS1
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage:'Invalid DNS1', regExp: dojox.validate.regexp.ipAddress,
                     placeHolder: 'DNS1', promptMessage:'DNS1'" id="ipDns1" onblur="FogWizardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="ipDNS2" class="control-label">
              DNS2:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props=" invalidMessage:'Invalid DNS2', 
                     regExp: dojox.validate.regexp.ipAddress,placeHolder: 'DNS2',
                     promptMessage:'DNS2'" id="ipDns2" onblur="FogWizardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div> 
        <div class="row-fluid">
          <button type="button" data-dojo-type= "dijit.form.Button" 
                  onclick="FogWizardIpManagement.add()" id="ipAddButton">
            OK
          </button>
          <button id="" data-dojo-type="dijit.form.Button"
                  onclick="FogWizardIpManagement.cancel()">
            Cancel
          </button>
          <button onclick="FogWizardIpManagement.update()"
                  data-dojo-type="dijit.form.Button"
                  id="ipUpdateButton" style="display: none">
            Apply
          </button>
        </div>
      </div>   
    </form>
  </div>
</div>
  <div class="span4" id="ipListCollection">
    <div id="ipListItem"></div>
  </div>
</div>
  