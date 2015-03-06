<%@ page contentType="text/html;charset=UTF-8" %>
<div  class="row-fluid">
    <div class="control-group span1">
          <img src="${resource(dir: 'images')}/bandwidth.jpeg" alt="Computing offer" height="42" width="42"> 
    </div>
    <div class="control-group span1">
            <img src="${resource(dir: 'images')}/snapshot.jpeg" alt="Computing offer" height="42" width="42"> 
    </div>
    <div class="control-group span8">
          <h3>Miscellaneous Cost</h3>
    </div>
</div>
<input type="hidden" id="currentMisscellaneousOfferId" name="currentMisscellaneousOfferId" value="">
<div  class="row-fluid">
<div class="span3" id ="miscellaneousListContainer">
  <div id="misceList"></div>
</div>
<div class="span9" id ="misscellaneousPage">
  <div class="row-fluid">
  <div id="miscelCostInfo">
    <div id="miscelZoneCostInfo" class="zoneCostHeading" data-dojo-type="dijit.TitlePane" 
         data-dojo-props="Title: '', open: false" onclick="">
      <div class="form-horizontal" id="miscelZoneForm">
      <div  class="row-fluid">
      
      <div class="control-group span4 horizontalcontent">
        
        <label for="miscZoneList" class="control-label">
          
          Zone: 
        </label>
        <div class="controls elements">
          <div id="miscZoneList" class="selectOption"></div>
        </div>
      </div>
        <div class="control-group span4 horizontalcontent" style="display: none">
        <label for="miscPodList"  class="control-label">
          
          Pod:
        </label>
        <div class="controls elements">
          <div id="miscPodList" class="selectOption"></div>
        </div>
      </div> 
      <div class="control-group span4 horizontalcontent" style="display: none">
        <label for="miscClusterList"  class="control-label">
          
          Cluster:
        </label>
        <div class="controls elements">
          <div id="miscClusterList" class="selectOption"></div>
        </div>
      </div> 
      </div>
           
      <div id="currentMisceZoneCostList" class="row-fluid">
          <div id="misceZoneCost" class="span12"></div>
        </div>
        <div style="float: right; margin-top: -25px">
          <button  id="updateBandwidthCostButton" onclick="FogWizardMiscellaneousCost.update()" data-dojo-type="dijit.form.Button" style="" class="applybtn">
            Apply
          </button>
          <button  id="cancelBandwidthCostButton" onclick="FogWizardMiscellaneousCost.cancel()" data-dojo-type="dijit.form.Button" class="cancelbtn">
            Cancel
          </button>
        </div> 
    </div>
    </div>
  </div>
  <span id="costItemId" style="display: none"></span>
</div>
</div>  

</div>
<div data-dojo-type="dijit.Dialog" id="summaryDialog" 
     title="Summary" class="span8">
  <div id="summaryGrid"></div>  
    <button onclick="FogWizardMiscellaneousCost.closeSummaryDialogue()" data-dojo-type="dijit.form.Button" style="">OK</button>
  </div>