<%@ page contentType="text/html;charset=UTF-8" %>
<form id="osTypeForm" data-dojo-type="dijit.form.Form">
<div class="row-fluid">
  <div class="span10">
    <h3>Grouping</h3>  
  </div> 
</div>
<div class="row-fluid">
  <table class="span10">
    <tr>
      <td>
        <label for="computingOfferZoneList" class="control-label">
          Zone: 
        </label>
      </td>
      <td>
        <div id="groupingZoneList"></div>
      </td>
      <td>
        <label for="computingOfferZoneList" class="control-label">
          OS Type: 
        </label>
      </td>
      <td>
        <div id="osTypeStorageList"></div>
      </td>
      <td id="selectedContentInfo"> <p style="color: #089553" class="offset6">Place the selected content here</p></td>
    </tr>
  </table>
  <div class="span2">
    <a href="#/admin/dashboard">Back to Dashboard</a>
  </div>
</div>
<div>
  <div class="span4 contentInfo" id="listCollection">    
    <div id="osListItem" class="span8 widgets"></div>
    
  </div>
  <div class="span2" id="listArrow">
    <div class="row-fluid"> 
      <div class="span12"></div>
    </div>
    <div class="row-fluid"> 
      <div class="span12"></div>
    </div>
    <div class="row-fluid"> 
      <div class="span12"></div>
    </div>
    
    <button type="button" disabled="true" data-dojo-type ="dijit.form.Button" 
     id = "selectAllButton" onclick="GroupingInfo.placeAllWidget()">>></button>
    <button id="selectButton" type="button" disabled="true" data-dojo-type ="dijit.form.Button"
            onclick="GroupingInfo.placeWidget()">></button>
    <button type="button" id="deselectButton" disabled="true" data-dojo-type ="dijit.form.Button"
            onclick="GroupingInfo.replaceCurrentWidget()"><</button>
    <button type="button" id="deselectAllButton" disabled="true" data-dojo-type ="dijit.form.Button"
            onclick="GroupingInfo.replaceAllWidget()"><<</button>
       
  </div>
  <div class="span4 contentInfo" id="selectedList">   
    <div id="selectedItems"></div>
  </div>    
  <div class="span3" id="sliderInfo">
        <div class="row-fluid">
          <div>
            <input type="hidden" id="currentWidgetId">
            <input type="hidden" id="selectWidgetId">
            
            
          </div>
        </div>
        <div class="row-fluid">
          <div class="span12"></div>
        </div>
        <div class="row-fluid">
          <div class="span12"></div>
        </div>
           
        <div class="row-fluid">          
          <a class="span6 non_updatable" onclick="GroupingInfo.editPageButton()" id="editPageButton">Edit</a>
          <a class="span6" onclick="GroupingInfo.showSlider()" id="previewPageButton">Preview</a>
        </div>  
       
      <div class="row-fluid non_updatable span12" id="osSliderList">
        <h6>Computing Offer Grouping.</h6>
        <div class="" id="osSlider"></div>
      </div>
      <div class="row-fluid span12">
        <div id="planListResult" class="non_updatable">
          <span id="planName"></span>
          <span id="cpuNo"></span> <span></span><span id="cpuSpeed"></span><span></span></br>
          <span id="cpuMemory"></span> <span></span><span id="groupingCost"></span> <span id="groupingCost"></span>
        </div>
      </div>
         </div>
   
   
  </div>
</div>
</form>
