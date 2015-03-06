<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
   <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services"/></a></li>
    <li>/</li>
    <li><g:message code="menu.admin.services.networkoffer"/></li>    
  </ul>
</div>
</div>
<div class="row-fluid"> 
  <div id="main-stats" style="display:none;">
    <div class="row-fluid stats-row">
        <div class="span4 stat">
            <div class="data">
                <span class="number" id="adminTotalNetworkoffer">0</span>
                <g:message code="stat.admin.totalOffering"/>
            </div>
           
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="adminEnabledTotalNetworkoffer">0</span>
                <g:message code="stat.admin.enableOffering"/>
            </div>
            
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="adminDisabledTotalNetworkoffer">0</span>
               <g:message code="stat.admin.disableOffering"/>
            </div>            
        </div>
    </div>
</div>
</div>
<div class="table-wrapper products-table">       
  <div class="row-fluid">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkOfferCurrencyValue"></span></div>
  </div>
  <div class="row-fluid filter-block">
    <div class="pull-right">
      <!--<a class="btn-flat success new-product" href="#/admin/networkOffer/addNetworkoffer">+ <g:message code="common.add"/></a></div>-->
  </div>
  <div class="row-fluid">
      <div id="adminNetworkOfferList">  
        </div>
      <div class="alert alert-info hide" id="noOfferMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.admin.noNetworkOffer"/>
      </div>
    </div>
</div>
</div>
</div>