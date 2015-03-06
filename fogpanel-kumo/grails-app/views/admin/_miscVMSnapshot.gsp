<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services" /></a></li>
      <li>/</li>
      <li><a href="#/admin/miscellaneous"><g:message code="menu.admin.services.misc" /></a></li> 
      <li>/</li>
      <li><g:message code="common.snapshot.VMSnapshot" /></li>
    </ul>
  </div>
</div>
<div class="row-fluid">   
  <ul class="nav nav-tabs span12">
    <li>
      <a href="#/admin/miscellaneous/bandwidth"><g:message code="common.bandwidth" /></a>
    </li> 
    <li>
      <a href="#/admin/miscellaneous/snapshot"><g:message code="menu.service.snapshot" /></a>
    </li>
    <li>
      <a href="#/admin/miscellaneous/ipCost"><g:message code="common.ipCost"/></a>
    </li>
    <li class="active">
      <a href="#/admin/miscellaneous/vmSnapCost"><g:message code="common.snapshot.VMSnapshot" /></a>
    </li> 
    <li>
     <a href="#/admin/miscellaneous/loadBalancer"><g:message code="common.loadBalancer" /></a>
    </li>
    <li>
     <a href="#/admin/miscellaneous/portForwarding"><g:message code="common.portForwarding" /></a>
    </li>
    <li>
    <a href="#/admin/miscellaneous/vpn"><g:message code="common.vpnConnection" /></a>
    </li>
  </ul>
</div>
<div class="new-user"> 
<div class="row-fluid">
    <div class="value_dollar pull-right"><g:message code="default.valueIn" /> <span id="miscVMSnapshotCurrencyValue"></span></div>  
  </div>
  <div class="row-fluid" id="VMSnapshotContainer">
      <form data-dojo-type="dijit.form.Form" id="adminMiscVMSnapshotForm" class="form-horizontal">
      <!--<div class="span12">-->
        <div class="span4 field-box control-group">
          <label for="" class="control-label">
            <g:message code="common.zone" />
            <span class="require">*</span>
          </label>
          <div class="controls">
          <div id="miscVMSnapshotZone"></div>
          </div>          
        </div>        
        <div id="VMSnapshotZoneCostList" class="span6 control-group">
          <div id="miscVMSnapshotCostInfo"></div>          
        </div>
        <div class="span1">              
          <button type="button" id="miscVMSnapButton" class="defaultbtn" data-dojo-type="dijit.form.Button" data-dojo-props="disabled: true" onclick="MiscVMSnapCostInfo.updateShow()"><g:message code="default.button.update.common" /></button>
          <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="miscVMSnapLoader" style="display: none"/>
        </div>   
        <div class="field-box" style="display: none">         
          <label for="networkStratPort" id="adminIngressStartPort" class="control-label">
            <g:message code="common.pod" />
          </label>          
          <div id="miscVMSnapshotPod"></div>
        </div>
        <div class=" field-box" style="display: none">
          <label for="networkEndPort" id="adminIngressEndPort" class="control-label">
            <g:message code="common.cluster" />
          </label>
          <div id="miscVMSnapshotCluster"></div>
        </div>
      <!--</div>-->      
    </form>  
  </div>  
  <div>
    <div class="row-fluid">
      <div class="row-fluid header">    
      </div> 
      <div id="miscVMSnapshotInfo"></div>
      <div class="alert alert-info hide" id="noMiscVMSnapshotMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
             <g:message code="common.noBabdwidthInfo" />
          </div>
    </div>
  </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="vmSnapshotEditConformationDialog" title="<g:message code="default.button.update.common" />" class="span4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="MiscVMSnapCostInfo.update()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="MiscVMSnapCostInfo.closeUpdate()"><g:message code="common.cancel" /></button>
    </div>
</div>
