<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#"><g:message code="menu.user.services"/></a></li>
            <li>/<li>
            <li><g:message code="common.vpn"/></li>
        </ul>
    </div> 
</div>
<div class="row-fluid"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="ipManagerTotalNetwork">0</span>
                    <g:message code="stat.user.totalNetwork"/>
                </div>
            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="totalIPCount">0</span>
                    <g:message code="common.totalIP"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="totalEnabledVPNCount">0</span>
                    <g:message code="common.totalIPEnabledVPN"/>
                </div>           
            </div>
        </div>
    </div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div class="table-wrapper products-table">
    <div class="row-fluid filter-block">
        <input type="hidden" id="vpnInfoIPId">
    </div>
    <div class="row-fluid">
        <div id="vpnPublicIpList"></div>
        <div class="alert alert-info hide" id="noVPNPublicIpAddressMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
          <g:message code="common.user.noNetworkIp"/>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="" id="vpnInfoEnableDialog" class="span6" title="<g:message code="common.network.vpnEnable.title"/>">
    <div class="row-fluid">        
        <div class="span2"><img src='images/ip_enable_icon.png' title="<g:message code="common.network.vpnEnable.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.network.vpnEnable.message"/></p>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
            <div id="vpnInfoListEnableOKButton" class="span2">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNInfo.enableVPN()"><g:message code="common.ok"/></button>
        </div>         
        <img id="VPNInfoEnableLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
        <div class="span2 offset1"><button  id="vpnInfoEnableCancelButton" type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VPNInfo.cancelVPNDialogue()"><g:message code="common.cancel"/></button>
        </div></div>
        <div class="span6"></div>
        
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="" id="vpnInfoDisableDialog" class="span6" title="<g:message code="common.network.vpnDisable.title"/>">
   <div class="row-fluid">        
        <div class="span2"><img src='images/ip_disable_icon.png' title="<g:message code="common.network.vpnDisable.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.network.vpnDisable.message"/></p>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span6">
        <div id="vpnInfoDisableOKButton"  class="span2">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VPNInfo.disableVPN();"><g:message code="common.ok"/></button>
        </div>        
        <img id="vpnInfoDisableLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
        <div class="span2 offset2"><button id="vnpInfoDisableCancelButton"  type="button" class="cancelbtn offset3" data-dojo-type="dijit.form.Button" onclick="VPNInfo.cancelVPNDialogue()"><g:message code="common.cancel"/></button>
        </div></div>
        <div class="span6"></div>
    </div>
</div>