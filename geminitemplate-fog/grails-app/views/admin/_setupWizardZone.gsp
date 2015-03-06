<%@ page contentType = "text/html;charset=UTF-8" %>
<div class="row">
  
  <input type="hidden" id="currentZoneId">
  <input type="hidden" id="currentPodId">
  <input type="hidden" id="currentClusterId">  
</div>
  <div class="row">
    <div class="alert alert-danger" id="zoneError" style="display: none">
       <span >
        You must fill all the fields
      </span>
    </div>
  </div>  
<div class="row">
 
    <div class="col-md-2 zoneitem_lftmenu zoneList_cont" id ="zoneList">
      <div id="zoneItem" class="row"></div>
    </div>
	<div class="col-md-10 zoneinfo_container">
	<div class="col-md-3">
      	<div id="zoneCollection">
			<ul class="nav nav-pills row" id="zoneDetails" style="display: none">
      				<li id="currentZoneInfo"><a onClick="FogWizardZone.zoneInfo()">ZoneInfo </a></li>
      				<li id="podListDetails"><a  onClick="FogWizardZone.showPod()">PodList</a></li>
      		</ul> 
        	<div id="zoneInfo" class="row"></div>
      	</div>
      	<div id="podListCollection" class="podListCollection_cont">
          <div id="podList" class="row"> </div>
      	</div> 
	</div>
    <div class="col-md-4">
		<div id="podCollection" style="display: none">
			<ul class="nav nav-pills row" id="podDetails">
      				<li id="currentPodInfo"><a  onClick="FogWizardZone.podInfo()">PodInfo </a></li>
      				<li id="clusterListDetails"><a  onClick="FogWizardZone.showCluster()">ClusterList</a></li>
    		</ul>
        	<div id="podInfo" class="row"></div>
      	</div>
    	<div id="clusterListCollection" class="clusterListCollection_cont">
        	<div id="clusterListItem" class="row"></div>
      	</div> 
    </div>
    <div class="col-md-4">
		<div id="clusterCollection" style="display: none">
				<ul class="nav nav-pills row" id="clusterDetails">
      				<li id="currentClusterInfo"><a  onClick="FogWizardZone.clusterInfo()">ClusterInfo </a></li>
      				<li id="hostListDetails"><a onClick="FogWizardZone.showHost()">HostList</a></li>
				</ul> 
        		<div id="clusterInfo" class="row"></div>
      		</div>    	
      	<div id="hostListCollection" class="hostListCollection_cont ">
        	<div id="hostListItem" class="row"></div>
      	</div>
    </div>
	</div>
  </div>