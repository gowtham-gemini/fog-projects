<%@ page contentType="text/html;charset=UTF-8" %>

<div class="span12" id="diskOfferingPage">

	<div class="row-fluid wizhead_title">
    	<div class="control-group span1">
          <img src="${resource(dir: 'images')}/disk.jpeg" alt="Computing offer" height="42" width="42"> 
    	</div>
    	<div class="control-group span8">
          <h3> Disk Offering </h3>
    	</div>
  </div>
  
    <div class="row-fluid infobox_widget non_updatable" id="diskListCollection">
		<a onclick="FogWizardDiskOffer.viewAllZoneInfo()" class="hide_lable infobox_widget_viewall" id="wizardDiskViewAllTag"><div class="viewall_icon"></div>View All</a>
  		<div class="servicelist_widget" data-dojo-type="dojox.layout.ScrollPane" data-dojo-props='orientation:"horizontal"' style="width:800px; overflow:hidden; margin:0px auto;">                  
                  <div class="customTable">
                    <div class="table-row">
                      <div id="diskList"></div>  
                
                    </div>
                  </div>
        </div>
   </div>

  <form id="diskOfferZoneForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
    <span  style="display: none" id="currentDiskOfferId"></span>

    <div  class="row-fluid">
      <div class="span12">
      <div class="control-group span4 horizontalcontent">
        <label for="diskOfferZoneList" class="control-label">
          Zone: 
        </label>
        <div class="controls updatable elements">
          <div id="diskOfferZoneList" class="selectOption"></div>
        </div>
        <span id="zoneNameLabel" class="hide_lable"></span>
      </div>
	  
      <div class="control-group span4 horizontalcontent">
        <label for="diskOfferPodList"  class="control-label ">
          Pod:
        </label>
        <div class="controls updatable elements">
          <div id="diskOfferPodList" class="selectOption"></div>
        </div>
        <span id="podNameLabel" class="hide_lable"></span>
      </div> 
	  
      <div class="control-group span4 horizontalcontent">
        <label for="diskOfferClusterList"  class="control-label ">
          Cluster:
        </label>
        <div class="controls updatable elements">
          <div id="diskOfferClusterList" class="selectOption"></div>
        </div>
        <span id="clusterNameLabel" class="hide_lable"></span>
      </div> 
   
      
      </div>
    </div>
    </form>
  <div class="row-fluid" id="diskOfferContent">
   <form id="diskOfferContentForm" data-dojo-type="dijit.form.Form" class="form-horizontal"> 
    <div class="row-fluid">
      
    <div class="control-group span6 horizontalcontent"> 
      <label for="diskName" class="control-label">
        <span class="require">*</span>
       Name:
      </label>
      <div class="controls elements">
        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                 data-dojo-props="invalidMessage: 'invalid disk name', required: 'true',
                 placeHolder: 'Enter Disk Name', regExp: '[|a-z0-9A-Z- ]{4,100}'"
                id="diskName">
      </div>
      
    </div> 
    <div class="control-group span6 horizontalcontent">
      <label for="diskDescription" class="control-label">
        <span class="require">
          *
        </span>
        Desc:
      </label>
      <div class="controls elements">
        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                  id="diskDescription"  data-dojo-props="required: 'true',
                 invalidMessage: 'invalid Desc', 
                 placeHolder: 'Description',regExp:'[a-zA-Z0-9- .,]{4,200}',
                 promptMessage:'Enter Description'"/>

      </div>
    </div>
    
</div>
     <div class="row-fluid">
       <div class="control-group span4 horizontalcontent">
        <label for="serviceStorageType" class="control-label">Storage Type:</label>
        <div class="controls updatable elements">
          <div class="selectOption">
          <select data-dojo-type="dijit.form.Select" id="diskStorageType"
                  onchange="FogWizardDiskOffer.getTags(this)">
            <option  value="Shared">Shared</option>
            <option value="Local">Local</option>
          </select>
          </div>
        </div>
        <span id="diskStorageTypeLabel" class="hide_lable"></span>
        </div>
        <div class="control-group span4 horizontalcontent">
      <label for="diskStorageTags" class="control-label">
        Tags:
      </label>
      <div class="controls updatable elements">
        <div id="diskOfferStorageTags" class="selectOption"></div>
      </div>
      
     <span id ="diskStorageTagsLabel" class="hide_lable"></span>
    </div>
    <div class="control-group customDisk horizontalcontent span4">
      <label class="control-label" for="customDisk">Custom Disk:</label>
          <div class="controls updatable elements">
            <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
              data-dojo-props="checked: false" id="customDisk" 
              onClick="FogWizardDiskOffer.enableContent(this)"> 
          </div>
          <span id="customDiskLabel" class="hide_lable"></span>
        </div>
    
    
      </div>
    <div class="row-fluid" id="secondRowWidgets">
      <div class="control-group span4 horizontalcontent" id="diskSizeHide">
        <label for="diskSize" class="control-label">
         
           Size(GB):
        </label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                 data-dojo-props="promptMessage:'Enter disk Size',
                 invalidMessage: 'invalid size',constraints:{min:1,max:1000,pattern:'#'}, 
                 timeoutChangeRate: '0.2',
                 placeHolder: 'Enter disk size', regExp: '[0-9]{1,5}',
                 promptMessage:'Enter Size',  value:1"
                 name = "diskSize" id="diskSize">
<!--          <span class ="highlight">GB</span> -->
        </div>
        <span id="diskSizeLabel" class="hide_lable"></span>
      </div>
      <div class="control-group span4 horizontalcontent diskCustomSize">
        <label for="diskSize" class="control-label">
         
           Min Size(GB):
        </label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                 data-dojo-props="required: 'true',
                     invalidMessage: 'invalid size', trim: 'true',
                     constraints:{min:1,max:1000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:1, regExp:'[0-9]{1,4}', promptMessage:'Min Size'" id="diskMinSize">
        </div>
        <span id="diskMinSizeLabel" class="hide_lable"></span>
      </div>
      <div class="control-group span4 horizontalcontent diskCustomSize">
        <label for="diskSize" class="control-label">
         
           Max Size(GB):
        </label>
        <div class="controls updatable elements">
          <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                 data-dojo-props="required: 'true',
                     invalidMessage: 'invalid size', trim: 'true',
                     constraints:{min:1,max:1000,pattern:'#'}, timeoutChangeRate: '0.2',
                     value:1, regExp:'[0-9]{1,4}', promptMessage:'Max Size'" id="diskMaxSize">
        </div>
       <span id="diskMaxSizeLabel" class="hide_lable"></span>
      </div>
    </div>
     <div class="row-fluid">
       <div id="diskZoneInfo" class="zoneInfo span12 zoneCostHeading"
            data-dojo-type="dijit.TitlePane" data-dojo-props="title: 'Price Information', open: true">
         <div class="row-fluid">
           <h5>Zone: <span id="diskZoneName"></span>   </h5>  <h5>Pod: <span id="diskPodName"></span>   </h5>  <h5>Cluster: <span id="diskClusterName"></span>   </h5>     
        </div>
         <div id="currentDiskZoneInfo" class="row-fluid">
           <div id="diskZoneCost" class="span8 offset2"></div>
         </div>
       </div>
     </div>
     <div class="buttonGroup">
       <button type="button" data-dojo-type= "dijit.form.Button" 
               onclick="FogWizardDiskOffer.add()" id="diskAddButton" data-dojo-props="disabled: true" class="okbtn">
         OK
       </button>
       <button id="diskCancelButton" data-dojo-type="dijit.form.Button"
               onclick="FogWizardDiskOffer.cancel()" data-dojo-props="disabled: true" class="cancelbtn">
         Cancel
       </button>
       <button onclick="FogWizardDiskOffer.update()" data-dojo-type="dijit.form.Button"
               id="diskUpdateButton" style="display: none">
         Apply
       </button>
     </div>
     <div data-dojo-type ="List.ListItem" id="disk_item_id" style="display: none">
     </div>
   </form>
  </div>
</div>
<!--<div class="infobox_widget" id="diskListCollection">
  <div class="row-fluid">
	  <a onclick="FogWizardDiskOffer.viewAllZoneInfo()" class="hide_lable infobox_widget_viewall" id="wizardDiskViewAllTag">View All</a>
  </div>
  <div id="diskList"></div>
  
</div>-->

<div data-dojo-type="dijit.Dialog" id="deleteDiskOfferDialog" 
     title="Stop Instance" style="color: black; width: 350px;">
  Are you sure you want to delete this item?
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button"
            onclick="FogWizardDiskOffer.conformDelete()" id="">
      Yes
    </button>
    <button id="" data-dojo-type="dijit.form.Button"
            onclick="FogWizardDiskOffer.closeDeleteDialog()">
      No
    </button> 
  </div>  
</div>
   
