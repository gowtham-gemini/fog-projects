<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services" /></a></li>
      <li>/</li>
      <li><a href="#/admin/miscellaneous"><g:message code="menu.admin.services.misc" /></a></li> 
      <li>/</li>
      <li><g:message code="common.portForwarding" /></li>
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
    <li>
     <a href="#/admin/miscellaneous/vmSnapCost"><g:message code="common.snapshot.VMSnapshot" /></a>
    </li>
     <li>
     <a href="#/admin/miscellaneous/loadBalancer"><g:message code="common.loadBalancer" /></a>
    </li>
    <li class="active">
     <a href="#/admin/miscellaneous/portForwarding"><g:message code="common.portForwarding" /></a>
    </li>
    <li>
     <a href="#/admin/miscellaneous/vpn"><g:message code="common.vpnConnection" /></a>
    </li>
  </ul>
</div>
<div class="new-user"> 
  <div class="row-fluid">
    <div class="value_dollar pull-right"><g:message code="default.valueIn" />  <span id="micPortForwardingCurrencyValue"></span></div>  
  </div>
  <div class="row-fluid" id="adminmicLoadBancerPage">
      <form data-dojo-type="dijit.form.Form" id="adminMiscPortForwardingForm" class="form-horizontal">
      <div class="row-fluid" id="pfPriceLabelContainer">
        <div class="span2 offset1"><h5><g:message code="common.zoneName"/></h5></div>
        <div class="span3 offset1"><h5> <g:message code="common.costPerRulePerMonth"/></h5></div>                                        
    </div>
    <div class="row-fluid">        
        <div class="span9 offset1">         
            <div id="micpfCostInfo"></div>              
        </div>                            
    </div>
    <div class="row-fluid" id="PFUpdateActionButton">     
        <div class="span9 offset7">
            <button type="button" class="defaultbtn" id="micPortForwardingButton" data-dojo-type="dijit.form.Button" onclick="MiscPortForwardingInfo.updateShow()"><g:message code="default.button.update.common" /></button>
            <img src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' id="micPortForwardingLoader" style="display: none"/>
        </div>                           
    </div>
    <div class="span12 hide_text" id="pfZoneLoader">
        <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
        <p><g:message code="common.message.loading" /></p>
    </div>
      </form>
  </div>  
</div>
<div class="row-fluid">
  <div id="pad-wrapper">
    <div class="table-wrapper products-table">   
      <div class="row-fluid">      
        <div id="micPortForwardingInfo"></div>    
        <div class="alert alert-info hide" id="noMicPortForwardingMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
          <g:message code="common.noPortForwardingInfo" />
        </div>
      </div>   
    </div>
  </div>    
</div>
<div data-dojo-type="dijit.Dialog" id="portForwardingEditConformationDialog" title="Update" class="span4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="MiscPortForwardingInfo.update()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="MiscPortForwardingInfo.closeUpdate()"><g:message code="common.cancel" /></button>
    </div>
</div>