<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpnCustomerGateway"><g:message code="common.vpnUsers"/></a></li>
        <li>/</li>
        <li><g:message code="common.vpnConnection"/></li>    
      </ul>
  </div>
</div>
<div class="table-wrapper products-table">                
    <div class="row-fluid">
        <div class="row-fluid" id="vpnConnectionListActionButtonCollection">
            <div class="pull-right">
                <a class="btn-flat success new-product" onclick="GeneralVPNConnectionInfo.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a>  
                <a class="btn-flat success new-product" onclick="GeneralVPNConnectionInfo.showCreateVPNConnectionDialog();"><g:message code='common.addVPNConnection' /></a>  
            </div>
        </div>
        <div class="row-fluid"><div class="span12"></div></div>
        <div class="row-fluid"><div class="span12"></div></div>
        
        <div class="row-fluid">
            <div id="vpnGeneralConnectionList"></div>            
        </div>
        <div class="row-fluid">
            <div class="alert alert-info hide" id="noGeneralVpnConnectionMessage" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="common.vpc.noVpnConnection"/>
            </div>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="generalVpnConnectionAddDialog" title="<g:message code='common.addVPNConnection' />" class="span5">   
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
                                        <div id="generalVPNConncetionZoneList"></div>
                                        <img id="generalVPNConncetionZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                    </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">
                                    <g:message code="common.vpc"/>
                                    <span class="require">*</span>
                                </label>
                                    <div class="controls ">
                                        <div id="generalVPNConncetionVPCList"></div>
                                        <img id="generalVPNConncetionVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                    </div>
                            </div>                            
                        </div>
                    </div> 
                </form>
            </div>
        </div>                            
        <div class="span12">
            <div class="row-fluid">
                <div class="span8"><span style="color: #F00003;" class="hide_text" id="vpnConnectionConditionExceptionMsg"><g:message code="common.requiredFieldsMissing"/></span></div>
                <div class="span4">
                    <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="GeneralVPNConnectionInfo.addVPNConnection()"><g:message code="common.ok"/></button>
                    <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="GeneralVPNConnectionInfo.cancelVPNConnection()"><g:message code="common.cancel"/></button>
                </div>
            </div>
        </div>       
    </div>                                                
</div>