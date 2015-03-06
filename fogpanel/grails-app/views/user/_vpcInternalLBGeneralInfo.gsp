<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.internalLoadBalancing"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right" id="internalLBListActionButtonCollection">
        <a class="btn-flat success new-product" onclick="ListAllInternalLBInfo.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a> 
        <a class="btn-flat success new-product" onclick="ListAllInternalLBInfo.showAddInternalLB()"><g:message code="common.addInternalLB"/></a>            
    </div>
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid"><div class="span12"></div></div>    
    <div class="row-fluid">
      <div id="vpcGeneralInternalLB"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noGeneralInternalLBMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <span id="noInternalLBMsg"> <g:message code="common.user.noInternalLoadBalancing"/></span>
      </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog"  id="addGeneralInternalLbDialog" title="<g:message code="common.addInternalLB"/>" class="customDialog span6">
    
</div>



<div data-dojo-type="dijit.Dialog" class="full_loader" id="generalInternalLBLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>