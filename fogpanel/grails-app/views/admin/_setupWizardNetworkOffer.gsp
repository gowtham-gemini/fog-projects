<%@ page contentType="text/html;charset=UTF-8" %>
<input type="hidden" id="currentNetworkOfferId" 
       name="currentNetworkOfferName">
<div class ="span7" id="networkOfferPage">
  <form data-dojo-type="dijit.form.Form"
        class="form-vertical" id="networkOfferForm">
    <h3> Network Offering </h3>
    <div class="row-fluid">
      <div class="control-group span5"> 
      <label for="networkName" class="control-label">
        <span class="require">
          *
        </span>
        Name
      </label>
        <div class ="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                 data-dojo-props="promptMessage:'Enter name', 
                 invalidMessage: 'invalid name',
                 required: 'required', placeHolder: 'Enter name', 
                 regExp: '[a-zA-Z0-9-]{4,200}', propercase: true" 
                 name="networkName" id="networkName">
        </div>
      </div>
      <div class="control-group span5">
        <label for="networkDescription" class="control-label">
        <span class="require">
          *
        </span>
          Description
        </label>
        <div class ="controls">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="promptMessage:'Enter Description here', 
                 invalidMessage: 'invalid Text', required: 'required',
                 placeHolder: 'Enter Description', regExp: '[|a-z0-9A-Z-]{4,200}'" 
                 name="networkDescription" id="networkDescription">
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div class="control-group span5">
        <label for="networkRate" class="control-label">
          Network Rate
        </label>
        <div class="controls updatable">
          <input type="number" data-dojo-type="dijit.form.ValidationTextBox"
                 data-dojo-props="promptMessage:'Enter Network Rate here', 
                 invalidMessage: 'invalid Network Rate', placeHolder: 'Enter Network Rate', 
                 regExp: '[0-9]{1,5}'" name="networkRate" id="networkRate">
        </div>
        <span id ="networkRateLabel" class="hide_lable"></span> 
      </div>
      <div class="control-group span5">
        <label for="networkTags" class="control-label">Tags</label>
        <div class="controls updatable">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                 data-dojo-props="promptMessage:'Enter tag here',
                 invalidMessage: 'invalid tag', placeHolder: 'Enter tags', 
                 regExp: '[a-z0-9 A-Z]{4,20}'" 
                 name="networkTags" id="networkTags">
        </div>
        <span id ="networkTagsLabel" class="hide_lable"></span>
      </div>
    </div>
    <div class="row-fluid">
      <div class="control-group span5">
        <label for="networkVlan" class="control-label">Specify VLAN</label>
        <div class="controls updatable">
          <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                 data-dojo-props="checked: false" id="networkVlan">
        </div>
        <span id ="networkLanLabel" class="hide_lable"></span>
      </div>
      <div class ="control-group span5">
        <label for ="networkMode" id="mode" class="control-label">
          Conserve Mode
        </label>
        <div class="controls updatable">
          <input type="checkbox" data-dojo-type ="dijit.form.CheckBox"
                 id ="networkMode" name="networkMode"
                 data-dojo-props="checked: false"> 
        </div>
        <span id ="networkModeLabel" class="hide_lable"></span>
      </div> 
    </div>
    
<!--  <div class="element">
    <label for="networkGuestType">Guest Type</label>
    <select data-dojo-type="dijit.form.FilteringSelect"
            data-dojo-props="placeHolder: 'Select a type'"
            id="networkGuestType" value = "Isolated">
      <option  selected>Isolated</option>
      <option>Shared</option>
    </select>
    <span id="networkGuestTypeLabel" class="result"></span>
  </div> -->
<div class="">
  <label>Supported Services:</label>
  <div class="serviceListContainer">
    <div class="serviceListContainerElement">
      <label for="networkVpn">VPN:</label>
      <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
             data-dojo-props="checked: false" id="networkVpn" 
             onClick="FogWizardNetworkOffer.enableContent(this)"> 
      <span id="networkVpnLabel" style="display: none"></span>
    </div>
      <div class="serviceListContainerElement">
        <label for="networkDhcp">DHCP:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkDhcp" 
           onClick="FogWizardNetworkOffer.enableContent(this)">
        <span id="networkDhcpLabel" style="display: none"></span>
      </div>
      <div class="serviceListContainerElement">
        <label for="networkDns">DNS:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkDns" 
           onClick="FogWizardNetworkOffer.enableContent(this)">
        <span id="networkDnsLabel" style="display: none"></span>
      </div>
      <div class="serviceListContainerElement">
        <label for="networkFirewall">Firewall:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkFirewall" 
           onClick="FogWizardNetworkOffer.enableContent(this)">
        <span id="networkFirewallLabel" style="display: none"></span>
      </div>
      
      <div class="serviceListContainerElement">
        <label for="networkLoadBalancer">Load Balancer:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkLoadBalancer"
           onClick="FogWizardNetworkOffer.enableContent(this)">
        <span id="networkLoadBalancerLabel" style="display: none"></span>
      </div>
      <div class="serviceListContainerElement">
        <label for="networkUserData">User Data:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkUserData"
           onClick="FogWizardNetworkOffer.enableContent(this)">
        <span id="networkUserDataLabel" style="display: none"></span>
      </div>
      <div class="serviceListContainerElement">
        <label for="networkSourceNat">Source NAT:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkSourceNat"
           onClick="FogWizardNetworkOffer.enableContent(this)">
        <span id="networkSourceNatLabel" style="display: none"></span>
      </div>
     
      <div class="serviceListContainerElement">
        <label for="networkStaticNat">Static NAT:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkStaticNat">
        <span id="networkStaticNatLabel" style="display: none"></span>
      </div>
      <div class="serviceListContainerElement">
        <label for="networkPortForwarding">Port Forwarding:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkPortForwarding">
        <span id="networkPortForwardingLabel" style="display: none"></span>
      </div>
      <div class="serviceListContainerElement">
        <label for="networkSecurityGroups">Security Groups:</label>
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
           data-dojo-props="checked: false" id="networkSecurityGroups">
        <span id="networkSecurityGroupsLabel" style="display: none"></span>
      </div>
    </div>
  </div> 
 <div class="element" style="display: none" 
       id="networkSystemOffer">
    <label for="networkSystemOffer">System Offering:</label>
    <select data-dojo-type="dijit.form.FilteringSelect"
            data-dojo-props="placeHolder: 'Select a type'" 
            id="systemOffer" value = "None">
      <option  selected>None</option>
      <option>test2</option>
      <option>test3</option>
      <option>System Offering for software router</option>
    </select>
    <span id="networkGuestTypeLabel" class="result"></span>
  </div> 


<div class ="element" style="display: none"
       id="networkRouterCapability">
    <label for ="routerCapability">
      Redundant router capability:
    </label>
    <input type="checkbox" data-dojo-type ="dijit.form.CheckBox"
           id ="routerCapability" data-dojo-props="checked: false">
  </div> 


<div class="element" style="display: none" id="supportedSource">
    <label for="networkSupportedSource">Supported Source NAT type:</label>
    <select data-dojo-type="dijit.form.FilteringSelect"
            data-dojo-props="placeHolder: 'Select a type'"
            id="networkSupportedSource" value = "Per account">
      <option  selected>Per account</option>
      <option>Per zone</option>
    </select>
  </div> 
  <div class ="element" style="display: none" id="networkElastic">
    <label for ="elasticIp">
      Elastic LB:
    </label>
    <input type="checkbox" data-dojo-type ="dijit.form.CheckBox"
           id ="elasticIp" data-dojo-props="checked: false">
  </div> 
  <div class="element" style="display: none" id="lbIsolation">
    <label for="networkLbIsolation">LB Isolation:</label>
    <select data-dojo-type="dijit.form.FilteringSelect"
            data-dojo-props="placeHolder: 'Select a type'" 
            id="networkLbIsolation" value = "Dedicate">
      <option  selected>Dedicate</option>
      <option>Shared</option>
    </select>
    <span id="networkGuestTypeLabel" class="result">
    </span>
  </div> 
  <div class="row-fluid">
  <div id="networkZoneInfo" class="zoneInfo"
       data-dojo-type="dijit.TitlePane"
       data-dojo-props="title: 'Price Information', 
       open: true">
    <h4>Zone</h4><h4>Cost/Month</h4><h4>Cost/GB/HR</h4><br>
    <div id="networkZoneCollection">
    <div id="currentNetworkZoneInfo"></div>
    </div>
  </div>
  </div>
  <div class="buttonGroup">
    
    <button type="" data-dojo-type= "dijit.form.Button" 
            onclick="FogWizardNetworkOffer.add()" id="networkAddButton">
      OK
    </button>
    
    <button id="networkCancelButton" data-dojo-type="dijit.form.Button"
            onclick="FogWizardNetworkOffer.cancel()">
      Cancel
    </button> 
    <button onclick="FogWizardNetworkOffer.update()"            
            data-dojo-type="dijit.form.Button"
            id="networkUpdateButton" style="display: none">
      Apply
    </button>
  </div>
  <div data-dojo-type ="List.ListItem" id="network_item_Id"
       style="display: none">
  </div>
  </form>
  
</div>
<div id ="networkListCollection" class="span4">
  <div id="networkList"></div>
</div>
