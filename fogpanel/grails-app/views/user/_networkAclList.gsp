<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpc/dashboard"><g:message code="common.vpcDashboard"/></a></li>  
            <li>/</li>
            <li><a href="#/user/vpcSecurity"><g:message code="common.security"/></a></li>  
            <li>/</li>
            <li><g:message code="common.networkACL"/></li>  
        </ul>
    </div>
</div>
<div class="table-wrapper products-table">       
  <div class="row-fluid" style="display: none;">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id=""></span></div>
  </div>
  <div class="row-fluid filter-block">
    <div class="pull-right" id="aclListActionButtonCollection">
        <a class="btn-flat success new-product" onclick="ListNetworkACL.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a> 
      <a class="btn-flat success new-product" href="#/user/vpcSecurity/addNetworlAcl">+ <g:message code="common.add"/></a>
    </div>
    </div>
    <div class="row-fluid"> 
        <div class="alert alert-info hide" id="noNetworkAclMsgBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.message.noNetworkAcl"/>
        </div>
    </div>
    <div class="row-fluid">
      <div id="networkAclListGrid"></div>      
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkAclLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<input id="currentAclId" type="hidden">
<div data-dojo-type="dijit.Dialog" id="networkAclDeleteDialog" class="span4">    
    <div class="row-fluid">
        <div class="span2"><img src='js/dojo-1.8/FogPanel/themes/InstanceStatus/images/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.networkAclDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListNetworkACL.deleteAcl()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListNetworkACL.deleteAclClose()"><g:message code="common.cancel"/></button>
    </div>
</div>
