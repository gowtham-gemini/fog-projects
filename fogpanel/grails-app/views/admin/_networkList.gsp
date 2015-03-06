<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
        <li>/</li>
        <li><g:message code="menu.user.services.network"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid" style="display: none;"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="userTotalNetwork">0</span>
                    <g:message code="stat.user.totalNetwork"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                  <span class="number" id="userEnabledTotalNetwork">0</span>
                    <g:message code="stat.user.enableNetwork"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                  <span class="number" id="userDisabledTotalNetwork">0</span>
                   <g:message code="stat.user.disableNetwork"/>
                </div>            
            </div>
        </div>
    </div>
</div>
<div class="table-wrapper products-table">       
  <div class="row-fluid" style="display: none;">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkCurrencyValue"></span></div>
  </div>
  <div class="row-fluid filter-block">
    <div class="pull-right">
      <a class="btn-flat success new-product" href="#/user/network/add">+ <g:message code="common.add"/></a></div>
    </div>
  <div class="row-fluid">
      <div id="userNetworkList">  
        </div>
      <div class="alert alert-info hide" id="noOfferMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noNetwork"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage" style="display: block">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkDeleteDialog" class="span4">
    <input id="currentNetworkId" type="hidden">
    <div class="row-fluid">
        <div class="span10">
            <div class="span12"><p><g:message code='common.networkDeleteConform' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListNetwork.deleteNetwork()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListNetwork.deleteNetworkClose()"><g:message code="common.cancel"/></button>
    </div>
</div>