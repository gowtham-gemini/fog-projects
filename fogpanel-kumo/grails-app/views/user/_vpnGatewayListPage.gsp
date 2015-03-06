<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/gateway/"><g:message code="common.gateway"/></a></li>
        <li>/</li>
        <li><g:message code="common.vpcGateway"/></li>    
      </ul>
  </div>
</div>
<div class="table-wrapper products-table">       
  <div class="row-fluid" style="display: none;">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkCurrencyValue"></span></div>
  </div> 
  <div class="row-fluid"><div class="span12"><input type="hidden" id="currentGeneralS2SVPNID"></div></div>
  <div class="row-fluid"><div class="span12"></div></div>
  <div class="row-fluid"><div class="span12"></div></div>
  
  <div class="row-fluid">
      <div id="userVpcGeneralGatewayList"></div>
  </div>
  <div class="row-fluid">      
      <div class="alert alert-info hide" id="noVpcGeneralGatewayMsgMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.NOS2SVPN"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="generals2svpnDeleteDialog"  class="span4">    
    <div class="row-fluid">        
        <div class="span10">
            <div class="span12"><p><g:message code="common.removeS2SVPNMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNGatewayInfo.deleteS2SVPN()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPNGatewayInfo.closeS2SGatewayShow()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="generalGatewayvpnLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>