<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpnCustomerGateway/list"><g:message code="common.vpnCustomerGateway"/></a></li>
        <li>/</li>
        <li id="vpncgEdit"><g:message code="common.add"/></li>
        <li>/</li>
        <li id="vpncgEditName" style="display: none;"><g:message code="common.add"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid header">
    <h3><g:message code="common.technicalInfo"/></h3>   <div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
</div>
<div class="new-user createVm"> 
    <div class="row-fluid form-wrapper">
        <div class="span3 createvm-banner">
            <img src="${resource(dir: 'images')}/vpn_lrg_icon.png" height="151" width="238">
<!--            <h2 class="alphaStyle overflowLabel"><g:message code="user.createVM"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.customDisk"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.template"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.firewall"/></h2>-->
        </div>
        <div class="span7 createvm-form with-sidebar">
            <div class="container">
<form id="vpnCutomerGatewayAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
    <div id="vpnCutomerGatewayAddFormPage" class="form-horizontal">
        <div class="row-fluid span9">
            <div class="row-fluid hide_text" id="customerGatewayCloudstackException">
                <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="customerGatewayExceptionMsg" class="span10"></span></div>
            </div> 
            <div class="control-group ">
                <label for="vpncgName" class="control-label"><g:message code="common.name"/>
                <span class="require">*</span>:
                </label>
                <div class="controls updatable">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         id="vpncgName" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{1,200}',
                         promptMessage:'<g:message code="common.name"/>'">
                </div>
            </div>
            <div class="control-group ">
                <label for="vpncgGateway" class="control-label">
                    <g:message code="common.gateway"/>
                    <span class="require">*</span>:
                </label>
                <div class="controls updatable">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                  id="vpncgGateway" data-dojo-props="required: 'true',
                  invalidMessage: '<g:message code="common.gateway.invalid"/>', placeHolder: '<g:message code="common.gateway"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                  promptMessage:'<g:message code="common.gateway"/>'"/>
                </div>
            </div>
            <div class="control-group ">
                <label for="vpncgCidr" class="control-label">
                    <g:message code="common.cidr"/>
                    <span class="require">*</span>:
                </label>
                <div class="controls updatable">
                 <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                    data-dojo-props="invalidMessage:'Invalid CIDR',required: 'true',regExp: '[0-9/.,]{0,160}',
                        placeHolder: '<g:message code="common.cidr"/>',
                        missingMessage:'<g:message code="common.value.invalid"/>',
                        promptMessage: '<g:message code="common.cidr"/>'"  
                        id="vpncgCidr" name="vpncgCidr">
                </div>
            </div>
            <div class="control-group ">
                <label for="ipsecPresharedKey" class="control-label">
                    <g:message code="common.ipsecPresharedKey"/>
                    <span class="require">*</span>
                </label>
                <div class="controls updatable">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                  id="ipsecPresharedKey" data-dojo-props="required: 'true',
                  invalidMessage: '<g:message code="common.value.invalid"/>', placeHolder: '<g:message code="common.ipsecPresharedKey"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                  promptMessage:'<g:message code="ipsecPresharedKey"/>'"/>
                </div>
            </div>
        <!--</div>-->
        <!--<div class="row-fluid">-->          
            <div class="control-group ">
                <label for="ikeEncryption" class="control-label">
                  <g:message code="common.ikeEncryption"/>:
                </label>
                <div class="controls updatable">
                    <select data-dojo-type="dijit.form.Select" id="ikeEncryption" class="">
                        <option value="3des">3des</option>
                        <option value="aes128">aes128</option>
                        <option value="aes192">aes192</option>
                        <option value="aes256">aes256</option>
                    </select>
                </div>
            </div>
            <div class="control-group ">
                <label for="ikeHash" class="control-label">
                  <g:message code="common.ikeHash"/>:
                </label>
                <div class="controls updatable">
                    <select data-dojo-type="dijit.form.Select" id="ikeHash" class="">
                        <option value="md5">md5</option>
                        <option value="sha1">sha1</option>
                    </select>
                </div>
            </div>
            <div class="control-group ">
                <label for="ikeDH" class="control-label">
                  <g:message code="common.ikeDH"/>:
                </label>
                <div class="controls updatable">
                    <select data-dojo-type="dijit.form.Select" id="ikeDH" class="">
                        <option value="">None</option>
                        <option value="modp1024">Group 2(modp1024)</option>
                        <option value="modp1536">Group 5(modp1536)</option>
                    </select>
                </div>
            </div>
        <!--</div>-->
        <!--<div class="row-fluid">-->          
            <div class="control-group ">
                <label for="espEncryption" class="control-label">
                  <g:message code="common.espEncryption"/>:
                </label>
                <div class="controls updatable">
                    <select data-dojo-type="dijit.form.Select" id="espEncryption" class="">
                        <option value="3des">3des</option>
                        <option value="aes128">aes128</option>
                        <option value="aes192">aes192</option>
                        <option value="aes256">aes256</option>
                    </select>
                </div>
            </div>
            <div class="control-group ">
                <label for="espHash" class="control-label">
                  <g:message code="common.espHash"/>:
                </label>
                <div class="controls updatable">
                    <select data-dojo-type="dijit.form.Select" id="espHash" class="">
                        <option value="md5">md5</option>
                        <option value="sha1">sha1</option>
                    </select>
                </div>
            </div>
            <div class="control-group ">
                <label for="perfectForwardSecrecy" class="control-label">
                  <g:message code="common.perfectForwardSecrecy"/>:
                </label>
                <div class="controls updatable">
                    <select data-dojo-type="dijit.form.Select" id="perfectForwardSecrecy" class="">
                        <option value="">None</option>
                        <option value="modp1024">Group 2(modp1024)</option>
                        <option value="modp1536">Group 5(modp1536)</option>
                    </select>
                </div>
            </div>
        <!--</div>-->
        <!--<div class="row-fluid">-->     
            <div class="control-group ">
                <label for="ikeLifetime" class="control-label"><g:message code="common.ikeLifetime"/>:
                </label>
                <div class="controls updatable">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         id="ikeLifetime" data-dojo-props="required: 'true', invalidMessage: '<g:message code="common.value.invalid"/>', value:'86400', placeHolder: '<g:message code="common.ikeLifetime"/>',regExp:'[0-9]{1,2000000}',
                         promptMessage:'<g:message code="common.ikeLifetime"/>'">
                </div>
            </div>
            <div class="control-group ">
                <label for="espLifetime" class="control-label"><g:message code="common.espLifetime"/>:
                </label>
                <div class="controls updatable">
                  <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                         id="espLifetime" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.value.invalid"/>', value:'3600', placeHolder: '<g:message code="common.espLifetime"/>',regExp:'[0-9]{1,2000000}',
                         promptMessage:'<g:message code="common.espLifetime"/>'">
                </div>
            </div>
            <div class="control-group ">
                <label for="deadPeerDetection" class="control-label"><g:message code="common.deadPeerDetection"/>:
                </label>
                <div class="controls updatable">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                        data-dojo-props="checked: false" id="deadPeerDetection">
                </div>
            </div>
        </div>
        <input type="hidden" id="editVPNCustomerGateway">
        <div class="row-fluid">   
            <div class="span8 pull-right">
                <div id="addButtonDiv" class="span3 pull-right">
                    <button data-dojo-type="dijit.form.Button" onclick="VPNCutomerGatewayList.addVPNCustomerGateway()" class="okbtn">
                        <g:message code="common.ok"/>
                    </button>
                </div>        
                <div id="updateButtonDiv" style="display: none;" class="span3 pull-right">
                    <button  data-dojo-type="dijit.form.Button" onclick=" VPNCutomerGatewayList.updateVPNCustomerGateway()" class="okbtn">
                        <g:message code="common.apply"/>
                    </button>
                </div>
                <div id="cancelButtonDiv" class="span3 pull-right">
                    <button  data-dojo-type="dijit.form.Button" onclick="VPNCutomerGatewayList.cancel()" class="cancelbtn">
                        <g:message code="common.cancel"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
</div>
</div>


<div data-dojo-type="dijit.Dialog" class="full_loader" id="vpnCustomerGatewayLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>