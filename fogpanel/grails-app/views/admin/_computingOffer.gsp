<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services"/></a></li>
    <li>/</li>
    <li><a href="#/admin/computation/list"><g:message code="user.createVM.ComputingOffer.label"/></a></li>
    <li>/</li>
    <li id="editComputBreadcrum"><g:message code="common.add"/></li>
  </ul>
</div>
</div>
<div class="row-fluid">
<div class="span12" id="adminComputingOfferPage">
 <div class="row-fluid header">
     <h3 id="computingOfferTitle"><g:message code="common.admin.offer.title"/></h3>   
  </div>
  <form id="adminComputingOfferZoneForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
    <div class="row-fluid">
     <div class="span12">
      <div class="control-group span4 horizontalcontent">
        <input type="hidden" id="currentComputingOfferId" name="currentComputingOfferId" value="">
        <label for="serviceName" class="control-label">          
          <g:message code="common.name"/>: 
          <span class="require">*</span>
        </label>
        <div class="controls elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="serviceName" data-dojo-props="required: 'true',
                 invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                 promptMessage:'<g:message code="common.name.placeHolder"/>'">
        </div>
        
      </div>
      <div class="control-group span4 horizontalcontent">
        <label for="serviceDescription"  class="control-label">         
         <g:message code="common.desc"/> :
           <span class="require">*</span>
        </label>
        <div class="controls elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                    id="serviceDescription" data-dojo-props="required: 'true',
                 invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                 promptMessage:'<g:message code="common.description.prompt"/>'"/>

        </div>
      </div> 
        <div class="control-group span4 customDisk">
        <label for="serviceStorageType" class="control-label"><g:message code="user.createVM.storageType.label"/>:</label>
        <div class="controls updatable">
          <div class="selectOption">
          <select data-dojo-type="dijit.form.FilteringSelect" id="serviceStorageType" onchange="AddComputingOfferInfo.hideOfferHA(this)">
            <option  value="shared"><g:message code="common.shared"/></option>
            <option value="local"><g:message code="common.local"/></option>
          </select>
          </div>
        </div>
        <span id="serviceStorageTypeLabel" class="hide_lable"></span>
        </div>
    </div>
  </form>
  
  <div id="computingOfferContent">
    <form id="adminComputingOfferContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
    <div class="row-fluid">
      
      <div class="control-group span4 customDisk">
        <label for="serviceHostTag" class="control-label"><g:message code="common.hostTag"/>:</label>
        <div class="controls updatable">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="computingOfferHostTag" data-dojo-props="invalidMessage: '<g:message code="common.invalid.host"/>', placeHolder: '<g:message code="common.hostTag"/>',regExp:'[a-zA-Z0-9- ]{1,200}',
                 promptMessage:'<g:message code="common.prompt.host"/>'">
        </div>
        <span id="serviceHostTagLabel" class="hide_lable"></span> 
      </div>
      
        
      <div class="control-group customDisk span4">
        <label for="serviceStorageTag" class="control-label">
          <g:message code="common.storageTag"/>: 
        </label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="computingOfferStorageTag" data-dojo-props="invalidMessage: '<g:message code="common.invalid.storageTag"/>', placeHolder: '<g:message code="common.storageTag"/>',regExp:'[a-zA-Z0-9- ]{1,200}',
                 promptMessage:'<g:message code="common.prompt.storageTag"/>'">
        </div>
        <span id="serviceStorageTagLabel" class="hide_lable"></span>
      </div>
      <div class="control-group span4 customDisk">
            <label for="serviceMemory" class="control-label">              
              <g:message code="common.memoryMB"/>: 
              <span class="require">*</span>
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                     id="serviceMemory" data-dojo-props="required: 'true',
                     invalidMessage: '<g:message code="common.memory.invalid"/>', trim: 'true',
                     placeHolder: 'Memory', constraints:{min:512, max:1024000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:512, regExp: '[0-9]{3,7}', promptMessage:'<g:message code="common.memoryName"/>'"
                     name="">
            </div>
            <span id="serviceMemoryLabel" class="hide_lable"></span>
          </div>
    </div>
    <div class="row-fluid">           
          <div class = "control-group span3 customDisk">
            <label for="serviceCpuNumber" class="control-label">              
              <g:message code="common.VcpuCore"/>: 
              <span class="require">*</span>
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                     id="serviceCpuNumber" data-dojo-props="required: 'true',
                     invalidMessage: '<g:message code="common.invalid.cpuNumber"/>', trim: 'true',
                     placeHolder: '<g:message code="common.cpuNumber"/>', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:1,  regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.prompt.cpuNumber" />'"
                     name="cpu_number">
            </div>
            <span id="serviceCpuNumberLabel" class="hide_lable"></span>
          </div>
          <div class="control-group span3 customDisk">
            <label for="serviceCpuSpeed" class="control-label">              
              <g:message code="common.speedMhz"/>: 
              <span class="require">*</span>
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     id="serviceCpuSpeed" data-dojo-props="required: 'true',
                     invalidMessage: '<g:message code="common.invalid.cpuNumber"/>', trim: 'true',
                     placeHolder: '<g:message code="common.cpuSpeed"/>', constraints:{min:128,max:30000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:128, regExp: '[0-9]{3,5}', promptMessage:'<g:message code="common.prompt.cpuSpeed"/>'"
                     name="cpu_speed">
            </div>
            <span id="serviceCpuSpeedLabel" class="hide_lable"></span>
          </div>
           <div class="control-group span3 customDisk">
            <label for="serviceNetworkRate" class="control-label">
              <g:message code="common.NWRate"/>(<g:message code="common.mb"/>):
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     id="serviceNetworkRate" data-dojo-props="invalidMessage: '<g:message code="common.networkRate.invalid"/>', 
                     placeHolder: '<g:message code="common.networkRate"/>', constraints:{min:10, max:20480, pattern:'#'}, timeoutChangeRate: '0.2',
                     value:10, regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.networkRate.promt"/>'"
                     name="service_network_rate" id="serviceNetworkRate">
            </div>
            <span id="serviceNetworkRateLabel" class="hide_lable"></span>
          </div>   
            <div class="control-group span3 customDisk">
            <label for="bandwidthCost" class="control-label">              
              <g:message code="common.bw"/>(<g:message code="common.gb"/>): 
              <span class="require">*</span>
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     id="computingOfferBandwidthCost" data-dojo-props="required: 'true',
                     invalidMessage: '<g:message code="common.bandwidth.invalid"/>', trim: 'true',
                     placeHolder: '<g:message code="common.cpuSpeed"/>', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:1, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.bandwidth.prompt"/>'">
            </div>
            <span id="serviceBwLabel" class="hide_lable"></span>
          </div>
    </div>      
    <div class="row-fluid">
      <div class="control-group span3 customDisk">
        <label for="diskReadRateBPS" class="control-label"><g:message code="common.diskReadRate"/>:</label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="diskReadRateBPS" data-dojo-props="invalidMessage: '<g:message code="common.diskReadRate.invalid"/>',
                 placeHolder: '<g:message code="common.diskReadRate"/>',regExp:'[0-9]{1,200}',
                 promptMessage:'<g:message code="common.diskReadRate.prompt"/>'">
        </div>
        <span id="serviceDiskReadRateBPSLabel" class="hide_lable"></span>
        </div>
      <div class="control-group span3 customDisk">
        <label for="diskWriteRateBPS" class="control-label"><g:message code="common.diskWriteRate"/>: </label>
        <div class="controls updatable elements">
         <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="diskWriteRateBPS" data-dojo-props="invalidMessage: '<g:message code="common.diskWriteRate.invalid"/>', 
                 placeHolder: '<g:message code="common.diskWriteRate.invalid"/>',regExp:'[0-9]{1,200}',
                 promptMessage:'<g:message code="common.diskWriteRate.prompt"/>'">
        </div>
        <span id="serviceWriteRateBPSLabel" class="hide_lable"></span>
      </div>
      
        
      <div class="control-group customDisk span3">
        <label for="diskReadRateIOPS" class="control-label">
           <g:message code="common.diskReadRateIOPS"/>: 
        </label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="diskReadRateIOPS" data-dojo-props="invalidMessage: '<g:message code="common.diskReadRateIOPS.invalid"/>', 
                 placeHolder: '<g:message code="common.diskReadRateIOPS"/>',regExp:'[0-9]{1,200}',
                 promptMessage:'<g:message code="common.diskReadRateIOPS.prompt"/>'">
        </div>
        <span id="serviceDiskReadRateIOPSLabel" class="hide_lable"></span>
      </div>
      <div class="control-group customDisk span3">
        <label for="diskWriteRateIOPS" class="control-label">
           <g:message code="common.diskWriteRateIOPS"/>: 
        </label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="diskWriteRateIOPS" data-dojo-props="invalidMessage: '<g:message code="common.diskWriteRateIOPS.invalid"/>',
                 placeHolder: '<g:message code="common.diskWriteRateIOPS"/>',
                 regExp:'[0-9]{1,200}', promptMessage:'<g:message code="common.diskWriteRateIOPS.prompt"/>'">
        </div>
        <span id="serviceWriteRateIOPSLabel" class="hide_lable"></span>
      </div>
      
    </div>        
    <div class="row-fluid">
        <div class="control-group span3 customDisk">
            <label for="diskIO" class="control-label"><g:message code="common.diskIO"/>:</label>
            <div class="controls">
                <div class="selectOption">
                <select data-dojo-type="dijit.form.Select" id="diskIO">
                  <option value="Average"><g:message code="common.average"/></option>
                  <option value="Good"><g:message code="common.good"/></option>
                  <option value="Excellent"><g:message code="common.excellent"/></option>
                </select>
                </div>
            </div>
        </div>                                    
          <div class="control-group span3 customDisk">
            <label for="serviceCpuCap" class="control-label" style="width: 100px"><g:message code="common.cpuCap"/>:</label>
            <div class="controls updatable" style="margin: 0 0 0 110px">
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                  data-dojo-props="checked: false" id="serviceCpuCap">
              </div>
              <span id="serviceCpuCapLabel" class="hide_lable"></span>
            </div> 
            <div class="control-group span3 customDisk" id="computingOfferHA">
              <label for="serviceOfferHa" id="offerHaToolTip"  
                     class="control-label" style="width: 100px">
                <g:message code="common.offerHA"/>:
              </label>
              <div class="controls updatable elements">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                   data-dojo-props="checked: false" id="serviceOfferHa">
              </div>
              <span id="serviceofferHaLabel" class="hide_lable"></span>
              <div data-dojo-type="dijit.Tooltip"
                   data-dojo-props="connectId:'offerHaToolTip'">
                <g:message code="common.highOffering"/>
            </div>     
            <div class="control-group span3 customDisk" style="display: block;"><div class="span12"></div></div>
        </div>               
       
      <div class="span12" id="billingInfo">
      <div class="row-fluid header">
            <h3><g:message code="common.pricingInfo"/></h3>
            <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
        </div> 
        <div id="currentComputingZoneInfo" class="row-fluid">
            <div class="row-fluid" id="zonePriceLabelContainer">
                <div>
                    <div class="row-fluid">
                        <div class="span2"><h5><g:message code="common.zoneName"/></h5></div>
                        <div class="span2"><h5><g:message code="common.instance.runningCostByMonth"/></h5></div>
                        <div class="span2"><h5><g:message code="common.instance.runningCostByHour"/></h5></div>
                        <div class="span2"><h5><g:message code="common.instance.stopageCostByMonth"/></h5></div>
                        <div class="span2"><h5><g:message code="common.instance.stopageCostByHour"/></h5></div>
                        <div class="span2"><h5><g:message code="common.instance.setupCost"/></h5></div>
                    </div>
                </div>
            </div> 
            <div class="row-fluid"><div id="currentZoneCost" class="span12"></div> </div>             
        </div> 
        <div class="row-fluid hide_text" id="addComputOfferZoneLoader">
            <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
            <p><g:message code="common.message.loading" /></p>
        </div>
        <div class="row-fluid">
      <div class="span9"></div>
      <div class="span3">
        <img class="hide_text" id="computingOfferLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
        <div id="serviceAddButtonDiv" class="span2">
            <button   id="serviceAddButton"  data-dojo-type="dijit.form.Button" onclick="AddComputingOfferInfo.add()" class="okbtn">
                <g:message code="common.ok"/>
            </button>
        </div>        
        <div id="serviceUpdateButtonDiv" class="span3">
            <button   id="serviceUpdateButton" style="display: none;" data-dojo-type="dijit.form.Button" onclick=" ViewComputingOfferInfo.updateShow()" class="okbtn">
                <g:message code="common.apply"/>
            </button>
        </div>
        <div id="serviceCancelButtonDiv" class="span2">
            <button id="serviceCancelButton"  data-dojo-type="dijit.form.Button" onclick="AddComputingOfferInfo.cancel()" class="cancelbtn">
                <g:message code="common.cancel"/>
            </button>
        </div>
      </div>
    </div>
      
      </div>
      
      
    </form>
  </div>
</div>
</div>
</div>
<div data-dojo-type="dijit.Dialog" id="computeOfferEditConformationDialog" title="Update" class="span4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser"/></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>
