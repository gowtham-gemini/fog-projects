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
  <div class="row-fluid"><div class="span12"><input type="hidden" id="currentGeneralS2SVPNID"></div></div>
  <div class="row-fluid"><div class="span12"></div></div>
  <div class="row-fluid">
      <div class="pull-right" id="vpnGatewayListActionButtonCollection">
          <a class="btn-flat success new-product" onclick="VPNGatewayInfo.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>
          <a class="btn-flat success new-product" onclick="VPNGatewayInfo.showVPNConnectionDialog();"><g:message code='common.addVPNGateway' /></a>          
      </div>
  </div>
  
  <div class="row-fluid">
      <div id="userVpcGeneralGatewayList"></div>
  </div>
  <div class="row-fluid">      
      <div class="alert alert-info hide" id="noVpcGeneralGatewayMsgMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.vpc.NOS2SVPN"/>
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
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="generalVpnGatewayAddDialog" title="<g:message code='common.addVPNGateway' />" class="span5">   
    <div class="row-fluid"> 
        <div class="span2">                
            <a class="index_title_icons_lb span12"></a>                
        </div>                  
        <div class="span9">
            <div class="row-fluid">   
                <form data-dojo-type="dijit.form.Form" class="form-horizontal">                        
                    <div class="form-horizontal">
                        <div class="row-fluid">                    
                            <div class="control-group">
                                <label class="control-label">
                                    <g:message code="common.zone"/>
                                    <span class="require">*</span>
                                </label>
                                    <div class="controls ">
                                        <div id="generalVPNGatewayZoneList"></div>
                                        <img id="generalVPNGatewayZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                    </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">
                                    <g:message code="common.vpc"/>
                                    <span class="require">*</span>
                                </label>
                                    <div class="controls ">
                                        <div id="generalVPNGatewayVPCList"></div>
                                        <img id="generalVPNGatewayVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                    </div>
                            </div>                            
                        </div>
                    </div> 
                </form>
            </div>
        </div>                            
        <div class="span12">
            <div class="row-fluid">
                <div class="span8"><span style="color: #F00003;" class="hide_text" id="generalVPNGatewayConditionExceptionMsg"><g:message code="common.requiredFieldsMissing"/></span></div>
                <div class="span4">
                    <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNGatewayInfo.addVPNGateway()"><g:message code="common.ok"/></button>
                    <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPNGatewayInfo.cancelVPNGateway()"><g:message code="common.cancel"/></button>
                </div>
            </div>
        </div>       
    </div>                                                
</div>