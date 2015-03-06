<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpc" ><g:message code="common.vpc"/></a></li>
            <li>/</li>
            <li><g:message code="common.vpc.routeTables"/></li>    
        </ul>
    </div>
</div>
<div class="table-wrapper products-table">       
  <div class="row-fluid" style="display: none;">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkCurrencyValue"></span></div>
  </div>
  <div class="row-fluid">
      <div class="alert alert-danger" style="display: none;" id="vpcListAddRachedLimitMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div> 
  </div>
  <div class="row-fluid filter-block">
    <div class="pull-right">           
        <a class="btn-flat success new-product" onclick="RouteTable.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>
        <!--<a class="btn-flat success new-product" id="listVpcAddButton" href="#/user/vpc/addVpc">+ <g:message code="common.add" /></a>-->
    </div>
    </div>
    <div class="row-fluid">
      <div id="userRouteTableList">  
        </div>
      <div class="alert alert-info hide" id="noRouteTableMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.vpc.noRouteTable"/>
      </div>
    </div>
</div>
