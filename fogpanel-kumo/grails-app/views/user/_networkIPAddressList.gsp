<div class="row-fluid" style="display: none;">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/network/"><g:message code="menu.user.services.network"/></a></li>  
            <li>/</li>
            <li><a id="currentNetworkName"></a></li>
            <li>/</li>
            <li><g:message code="common.ipAddress"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
<ul class="nav nav-tabs span12 customNav">
    <li>
        <a id="networkSummaryLink"><g:message code="common.details"/></a>
    </li>
    <li>
        <a id="networkEgressLink"><g:message code="common.egressRule"/></a>
    </li>
    <li id="networkIPTab" style="display: none;" class="active">
        <a id="networkIPLink"><g:message code="common.ipAddress"/></a>
    </li>
</ul>
</div>
<div class="table-wrapper products-table" style="display: none;">       
  <div class="row-fluid">
    <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/><span id="networkCurrencyValue"></span></div>
  </div>
</div> 
<input id="viewIPNetworkId" type="hidden">
<div class="row-fluid filter-block">
    <div class="pull-right">
        <button   id=""  data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.acquireIPShow()" class="okbtn">
          <g:message code="common.user.acquireIp"/>
        </button>
    </div>
    <div class="row-fluid" style="margin-top: 70px;">
      <div id="userNetworkIpList"></div>
      <div class="alert alert-info hide" id="noIpAddressMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noNetworkIp"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="neworkReleaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">
    <input type="hidden" id="currentIPId">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.releaseIP()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.closeReleaseIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkAcquireIpDialog" title="<g:message code="common.acquireIP"/>" class="span4">
    <input type="hidden" id="currentIpId">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.user.ip.confirmNetwork' /></p></div>
            <div class="span12" style="margin-left: 0">              
                <div class="span9">
                    <p><g:message code="common.agreeThe"/><a href="#" onClick="ViewNetworkIP.showCondition()"> <g:message code="signup.termsAndConditions"/></a></p>
                </div>
                <div class="span2">
                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="ipInfoAgreement" name="agreement">     
                </div>                    
            </div>
            <div class=""> <span class="validation" id="conditionExceptionMsg" style="margin-left: 0"><g:message code="signup.termsCondition.info"/></span></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.acquireIp()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.closeAcquireIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="networkAquireIPContent" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
    <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
        <div id="networkInfoContentArea">
        </div>                           
        <div class="row-fluid">
            <div class="span10"></div>
            <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.cancelConditionBox()"><g:message code="common.ok"/></button></div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="networkIpLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkIPEnableStaticNatDialog" title="<g:message code="common.enableStaticNat"/>" class="span4"> 
    <input type="hidden" id="sourceNatIPId">
    <div class="row-fluid container">
    <form id="networkIPEnableStaticNatForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div class="span9" id="networkIPEnableStaticNatFormPage">
            <div class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                              <label for="ipPortForwardingVM" class="control-label">
                            <g:message code="common.instance"/>:
                        </label>
                        <div class="controls ">
                            <div id="staticNatVMList"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="portForwardingVMIp" class="control-label">
                            <g:message code="common.ipAddress"/>:
                        </label>
                        <div class="controls ">
                            <div id="staticNatVMIpDiv"></div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.enableStaticNat()"><g:message code="common.ok"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.closeEnableStaticNat()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="networkIPDisableStaticNatDialog" title="<g:message code="common.disableStaticNat"/>" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.disableStaticNatMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.disableStaticNat()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.closeDisableStaticNat()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="" id="networkIpListEnableVPN" class="span6" title="<g:message code="common.network.vpnEnable.title"/>">
    <div class="row-fluid">        
        <div class="span2"><img src='images/ip_enable_icon.png' title="<g:message code="common.network.vpnEnable.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.network.vpnEnable.message"/></p>
        </div>
    </div>        
    <div class="row-fluid">
        <div class="span6">
            <div id="vpnIpListEnableOKButton" class="span2">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.enableVPN()"><g:message code="common.ok"/></button>
            </div>         
                <img id="networkIPListVPNEnableLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                <div class="span2"><button  id="vnpipListEnableCancelButton" type="button" class="cancelbtn offset3" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.cancelVPNDialogue()"><g:message code="common.cancel"/></button>
                </div>
        </div>   
        <div class="span6"></div>        
    </div>        
</div>
<input type="hidden" id="ipListcurrentIPId"/>
<div data-dojo-type="dijit.Dialog" class="" id="networkIpListDisableVPN" class="span6" title="<g:message code="common.network.vpnDisable.title"/>">
   <div class="row-fluid">        
        <div class="span2"><img src='images/ip_disable_icon.png' title="<g:message code="common.network.vpnDisable.title"/>"></div>                                   
        <div class="span10">
            <p><g:message code="common.network.vpnDisable.message"/></p>
        </div>
    </div>
    
    <div class="row-fluid">
        <div class="span6">
        <div id="vpnipListDisableOKButton"  class="span2">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.disableVPN();"><g:message code="common.ok"/></button>
        </div>        
        <img id="networkIPListVPNDisableLoader" class="hide_text vpn_loader" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
       <div class="span2 offset2"> <button id="vnpipListDisableCancelButton"  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewNetworkIP.cancelVPNDialogue()"><g:message code="common.cancel"/></button>
        </div>
        </div>
        <div class="span6"></div>
    </div>                
</div>
