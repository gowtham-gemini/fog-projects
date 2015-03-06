<%@ page contentType="text/html;charset=UTF-8" %>

<div class="span12" id="wizardComputingOfferPage">
 
  <div  class="row-fluid wizhead_title">
    <div class="control-group span1">
          <img src="${resource(dir: 'images')}/cpu.jpeg" alt="Computing offer" height="42" width="42"> 
    </div>
    <div class="control-group span8">
          <h3>Computing Offer</h3>
    </div>
  </div>
  <div class="row-fluid infobox_widget non_updatable" id="serviceListItems">
		<a onclick="FogWizardComputingOffer.viewAllZoneInfo()" class="hide_lable infobox_widget_viewall" id="wizardComputViewAllTag"><div class="viewall_icon"></div>View All</a>
  		<div class="servicelist_widget" data-dojo-type="dojox.layout.ScrollPane" data-dojo-props='orientation:"horizontal"' style="width:800px; overflow:hidden; margin:0 auto;">                  
                  <div class="customTable">
                    <div class="table-row">
                      <div id="serviceList"></div>  
                
                    </div>
                  </div>
        </div>
   </div>
  <form id="computingOfferZoneForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
	
    <div class="row-fluid">
     <div class="span12">
      <div class="control-group span4 horizontalcontent">
        <input type="hidden" id="currentComputingOfferId" name="" value="">
        <label for="computingOfferZoneList" class="control-label">
          Zone: 
        </label>
        <div class="controls updatable elements">
          <div id="computingOfferZoneList" class="selectOption"></div>
      </div>
        <span id="computingZoneNameLabel" class="hide_lable"></span>
      </div>
      <div class="control-group span4 horizontalcontent">
        <label for="computingOfferPod"  class="control-label">
          Pod:
        </label>
        <div class="controls updatable elements">
          <div id="computingOfferPod" class="selectOption"></div>
        </div>
        <span id="computingPodNameLabel" class="hide_lable"></span>
      </div>
      <div class="control-group span4 horizontalcontent">
        <label for="computingOfferCluster"  class="control-label">
          Cluster:
        </label>
        <div class="controls updatable elements">
          <div id="computingOfferCluster" class="selectOption"></div>
        </div>
        <span id="computingClusterNameLabel" class="hide_lable"></span>
      </div>  
    </div>
    </div>
  </form>
  
  <div id="computingOfferContent">
    <form id="computingOfferContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
     
    <div class="row-fluid">
      
      <div class="control-group span6 horizontalcontent">
        <input type="hidden" id="currentComputingOfferId" name="currentComputingOfferId" value="">
        <label for="serviceName" class="control-label">
          <span class="require">*</span>
          Name: 
        </label>
        <div class="controls elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                 id="serviceName" data-dojo-props="required: 'true',
                 invalidMessage: 'invalid Name', placeHolder: 'Name',regExp:'[a-zA-Z0-9- ]{4,200}',
                 promptMessage:'Enter Name'">
        </div>
        
      </div>
      <div class="control-group span6 horizontalcontent">
        <label for="serviceDescription"  class="control-label">
          <span class="require">*</span>
          Desc:
        </label>
        <div class="controls elements">
          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                    id="serviceDescription" data-dojo-props="required: 'true',
                 invalidMessage: 'invalid Desc', placeHolder: 'Description',regExp:'[a-zA-Z0-9- .,]{4,200}',
                 promptMessage:'Enter Description'"/>

        </div>
      </div>   
    </div>

    <div class="row-fluid">
      <div class="control-group span4 customDisk">
        <label for="serviceStorageType" class="control-label">Storage Type:</label>
        <div class="controls updatable">
          <div class="selectOption">
          <select data-dojo-type="dijit.form.Select" id="serviceStorageType"
                  onchange="FogWizardComputingOffer.hideOfferHA(this)">
            <option  value="Shared">Shared</option>
            <option value="Local">Local</option>
          </select>
          </div>
        </div>
        <span id="serviceStorageTypeLabel" class="hide_lable"></span>
        </div>
      <div class="control-group span4 customDisk">
        <label for="serviceHostTag" class="control-label">Host Tag:</label>
        <div class="controls updatable">
          <div id="computingOfferHostTagList" class="selectOption"></div>
        </div>
        <span id="serviceHostTagLabel" class="hide_lable"></span> 
      </div>
      
        
      <div class="control-group customDisk span4">
        <label for="serviceStorageTag" class="control-label">
          Storage Tag: 
        </label>
        <div class="controls updatable elements">
          <div id="computingOfferStorageTagList" class="span4 selectOption"></div>
        </div>
        <span id="serviceStorageTagLabel" class="hide_lable"></span>
      </div>
      
    </div>
    <div class="row-fluid">           
          <div class = "control-group span4 customDisk">
            <label for="serviceCpuNumber" class="control-label">
              <span class="require">*</span>
              Vcpu Core: 
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                     id="serviceCpuNumber" data-dojo-props="required: 'true',
                     invalidMessage: 'invalid CPU Number', trim: 'true',
                     placeHolder: 'CPU Number', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:1,  regExp: '[0-9]{1,5}', promptMessage:'Enter CPU Number'"
                     name="cpu_number">
            </div>
            <span id="serviceCpuNumberLabel" class="hide_lable"></span>
          </div>
          <div class="control-group span4 customDisk">
            <label for="serviceCpuSpeed" class="control-label">
              <span class="require">*</span>
              Speed(MHz): 
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     id="serviceCpuSpeed" data-dojo-props="required: 'true',
                     invalidMessage: 'invalid CPU Speed', trim: 'true',
                     placeHolder: 'CPU Speed', constraints:{min:128,max:30000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:128, regExp: '[0-9]{3,5}', promptMessage:'Enter Speed'"
                     name="cpu_speed">
            </div>
            <span id="serviceCpuSpeedLabel" class="hide_lable"></span>
          </div>
          <div class="control-group span4 customDisk">
            <label for="serviceMemory" class="control-label">
              <span class="require">*</span>
              Memory(MB): 
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                     id="serviceMemory" data-dojo-props="required: 'true',
                     invalidMessage: 'invalid Memory', trim: 'true',
                     placeHolder: 'Memory', constraints:{min:512, max:1024000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:512, regExp: '[0-9]{3,7}', promptMessage:'Memory'"
                     name="">
            </div>
            <span id="serviceMemoryLabel" class="hide_lable"></span>
          </div>
      
      
        
      
    </div>
      <div class="row-fluid">          
          <div class="control-group span4 customDisk">
            <label for="serviceNetworkRate" class="control-label">
              N/W Rate(MB):
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     id="serviceNetworkRate" data-dojo-props="invalidMessage: 'invalid HNetwork Rate', 
                     placeHolder: 'Network Rate', constraints:{min:10, max:20480, pattern:'#'}, timeoutChangeRate: '0.2',
                     value:10, regExp: '[0-9]{1,5}', promptMessage:'Enter Network Rate'"
                     name="service_network_rate" id="serviceNetworkRate">
            </div>
            <span id="serviceNetworkRateLabel" class="hide_lable"></span>
          </div>
            <div class="control-group span4 customDisk">
            <label for="bandwidthCost" class="control-label">
              <span class="require">*</span>
              B/w(GB): 
            </label>
            <div class="controls updatable">
              <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                     id="computingOfferBandwidthCost" data-dojo-props="required: 'true',
                     invalidMessage: 'invalid Bandwidth', trim: 'true',
                     placeHolder: 'CPU Speed', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:1, regExp:'[0-9]{1,4}', promptMessage:'Enter Bandwidth'">
            </div>
            <span id="serviceBwLabel" class="hide_lable"></span>
          </div>       
          <div class="control-group span4 customDisk">
        <label for="serviceHostTag" class="control-label">Base Os:</label>
        <div class="controls updatable">
          <div class="selectOption">
          <select data-dojo-type="dijit.form.Select" id="computingOfferBaseOs" 
                  class="">
            <option value="LINUX">Linux</option>
            <option value="WINDOWS">Windows</option>
          </select>
          </div>
        </div>
        <span id="serviceBaseOsLabel" class="hide_lable"></span> 
      </div>
            
        </div>
        
        <div class="row-fluid">
          <div class="control-group spacing span4 customDisk">
            <label for="serviceCpuCap" class="control-label" style="width: 100px">Cpu Cap:</label>
            <div class="controls updatable" style="margin: 0 0 0 110px">
              <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                  data-dojo-props="checked: false" id="serviceCpuCap">
              </div>
              <span id="serviceCpuCapLabel" class="hide_lable"></span>
            </div> 
          <div class="control-group span4 spacing customDisk" id="computingOfferHA">
              <label for="serviceOfferHa" id="offerHaToolTip"  
                     class="control-label" style="width: 100px">
                Offer HA:
              </label>
              <div class="controls updatable elements">
                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                   data-dojo-props="checked: false" id="serviceOfferHa">
              </div>
              <span id="serviceofferHaLabel" class="hide_lable"></span>
              <div data-dojo-type="dijit.Tooltip"
                   data-dojo-props="connectId:'offerHaToolTip'">
                HIGH Offering
              </div>
            </div> 
        </div>
      
    
    <div class="row-fluid">
      <div id="computingZoneInfo" class="zoneCostHeading" data-dojo-type="dijit.TitlePane"
           data-dojo-props="title: 'Zone Cost Information'">
        <div class="row-fluid">
			<div class="span4"><h5>Zone: <span id="computingZoneName"></span>   </h5></div>
			<div class="span4"><h5>Pod: <span id="computingPodName"></span>   </h5></div>
			<div class="span4"><h5>Cluster: <span id="computingClusterName"></span>   </h5></div>
                   
        </div>
        
        <div id="currentComputingZoneInfo" class="row-fluid">
          <div id="currentZoneCost" class="span12"></div>
        </div>
      </div>
    </div>
    <div class="buttonGroup">
      <button   id="serviceAddButton"  data-dojo-type="dijit.form.Button"  
              onclick="FogWizardComputingOffer.add()" data-dojo-props="disabled: true" class="okbtn">
        OK
      </button>
      <button   id="serviceCancelButton"  data-dojo-type="dijit.form.Button"  
              onclick="FogWizardComputingOffer.cancel()" data-dojo-props="disabled: true" class="cancelbtn">
        Cancel
      </button>
      <button   id="serviceUpdateButton" style="display: none;"
                data-dojo-type="dijit.form.Button" 
                onclick="FogWizardComputingOffer.update()">
        Apply
      </button>
    </div>
    <div data-dojo-type ="List.ListItem" id="serviceItemId" style="display: none">  
    </div>
  </form>
</div>
</div>

<div data-dojo-type="dijit.Dialog" id="deleteComputingOfferDialog" 
       title="Stop Instance" style="color: black; width: 350px;">
    Are you sure you want to delete this item?
    <div style="margin-left: 100px">
      <button type="button" data-dojo-type= "dijit.form.Button"
              onclick="FogWizardComputingOffer.conformDelete()" id="">
      Yes
      </button>
      <button id="" data-dojo-type="dijit.form.Button"
              onclick="FogWizardComputingOffer.closeDeleteDialog()">
        No
      </button> 
    </div>  
  </div>
   
