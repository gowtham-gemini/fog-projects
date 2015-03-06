<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/cloudStack"><g:message code="menu.admin.configuration.cloudStack"/></a></li>
    <li>/</li>
    <li><g:message code="common.zoneDesc"/></li>   
  </ul>
</div>
</div>

<div class="new-user">
  <div class="row-fluid">
    <input type="hidden" id="adminCurrentZoneId">
    <input type="hidden" id="adminCurrentPodId">
    <input type="hidden" id="adminCurrentClusterId"> 
<!--    <h3>Zone</h3>-->
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <!--<div class="span9 with-sidebar">-->
    <div class="container">
      <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="">
        <div class="row-fluid">
           <div class="field-box">
          <span><g:message code="admin.zoneInfo"/></span>
        </div>
        </div>
        <div class="row-fluid">
          <div class="alert alert-danger" id="adminZoneError" style="display: none">
            <span><g:message code="admin.fillAllInfo"/></span>
          </div>
        </div>  
        <div class="row-fluid" id="adminInfoMenu">
          <div class="span2 zoneitem_lftmenu zoneList_cont" id ="adminZoneList">
            <div id="adminZoneItem" class="row-fluid"></div>
          </div>
          <div class="span10 zoneinfo_container">
            <div class="span3">
              <div id="adminZoneCollection">
                <ul class="nav nav-pills row-fluid" id="adminZoneDetails" style="display: none">
                  <li id="adminCurrentZoneInfo"><a onclick="AdminZoneInfo.zoneInfo()"><g:message code="common.zoneInfo"/> </a></li>
                  <li id="adminPodListDetails"><a onclick="AdminZoneInfo.showPod()"><g:message code="common.podList"/></a></li>
                </ul> 
                <div id="adminZoneInfo" class="row-fluid"></div>
              </div>
              <div id="adminPodListCollection" class="podListCollection_cont">
                <div id="adminPodList" class="row-fluid"> </div>                
              </div> 
            </div>
            <div class="span4">
              <div id="adminPodCollection" style="display: none">
                <ul class="nav nav-pills row-fluid" id="adminPodDetails">
                  <li id="adminCurrentPodInfo"><a  onclick="AdminZoneInfo.podInfo()"><g:message code="common.podInfo"/> </a></li>
                  <li id="adminClusterListDetails"><a  onclick="AdminZoneInfo.showCluster()"><g:message code="common.clusterList"/></a></li>
                </ul>
                <div id="adminPodInfo" class="row-fluid"></div>
              </div>
              <div id="adminClusterListCollection" class="clusterListCollection_cont">
                <div id="adminClusterListItem" class="row-fluid"></div>
              </div> 
            </div>
            <div class="span4">
              <div id="adminClusterCollection" style="display: none">
                <ul class="nav nav-pills row-fluid" id="adminClusterDetails">
                  <li id="adminCurrentClusterInfo"><a  onclick="AdminZoneInfo.clusterInfo()"><g:message code="common.clusterInfo"/> </a></li>
                  <li id="adminHostListDetails"><a onclick="AdminZoneInfo.showHost()"><g:message code="common.hostList"/></a></li>
                </ul> 
                <div id="adminClusterInfo" class="row-fluid"></div>
              </div>    	
              <div id="adminHostListCollection" class="hostListCollection_cont ">
                <div id="adminHostListItem" class="row-fluid"></div>
              </div>
            </div>
          </div>                              
        </div>   
        </form>
    </div>

    </div>  
</div>
</div>

 
 


  





    





