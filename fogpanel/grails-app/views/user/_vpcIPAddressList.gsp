<div class="row-fluid filter-block">
    <div class="row-fluid">
        <div class="alert alert-danger" style="display: none;" id="vpcInfoAddIPLimitReachedMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div> 
    </div>
    <div class="pull-right">
        <button  data-dojo-type="dijit.form.Button" onclick="ViewVpc.populateIPListValues()" class="okbtn">
          <i class="icon-refresh"></i> <g:message code='common.refresh' />
        </button>
        <button   id=""  data-dojo-type="dijit.form.Button" onclick="ViewVpc.acquireIPShow()" class="okbtn">
          <g:message code="common.user.acquireIp"/>
        </button>
    </div>
    <div class="row-fluid" style="margin-top: 70px;">
      <div id="vpcIpList"></div>
      <div class="alert alert-info hide" id="noIpAddressMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noNetworkIp"/>
      </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcReleaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">
    <input type="hidden" id="currentIPId">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" id="vpcInfoAddIPButton" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.releaseIP()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeReleaseIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcAcquireIpDialog" title="<g:message code="common.acquireIP"/>" class="span4">
    <input type="hidden" id="currentIpId">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.user.ip.confirmNetwork' /></p></div>
            <div class="span12" style="margin-left: 0">              
                <div class="span9">
                    <p><g:message code="common.agreeThe"/><a onClick="ViewVpc.showCondition()"> <g:message code="signup.termsAndConditions"/></a></p>
                </div>
                <div class="span2">
                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="ipVpcInfoAgreement" name="agreement">     
                </div>                    
            </div>
            <div class=""> <span class="validation" id="vpcIConditionExceptionMsg" style="margin-left: 0"><g:message code="signup.termsCondition.info"/></span></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.acquireIp()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeAcquireIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="vpckAquireIPContent" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
    <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
        <div id="vpcInfoContentArea">
        </div>                           
        <div class="row-fluid">
            <div class="span10"></div>
            <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="ViewVpc.cancelConditionBox()"><g:message code="common.ok"/></button></div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcIpEnableStaticNatDialog" title="<g:message code="common.enableStaticNat"/>" class="span4"> 
    <input type="hidden" id="currentEnableStaticNatIpId">
    <div class="row-fluid container">
    <form id="vpcIpEnableStaticNatForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
        <div class="span2"><img src="css/theme/fog-classic/images/cloud_icons/staticnat_icon.png"></div>
        <div class="span9" id="vpcInfoIpEnableStaticNatFormPage">
            <div class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="vpcTier" class="control-label">
                            <g:message code="common.tier"/>:
                        </label>
                        <div class="controls ">
                            <div id="vpcTierList"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="vpcTierVm" class="control-label">
                            <g:message code="common.instance"/>:
                        </label>
                        <div class="controls ">
                            <div id="vpcTierVmList"></div>
                        </div>
                    </div>
                </div> 
            </div>
            <div class="row-fluid">
            <div class="span6"><span  class="hide_text require" id="vpcEnableStaticNatErrorMessage"><g:message code="user.createVM.required"/></span></div>
            <div class="span6">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.enableStaticNat()"><g:message code="common.ok"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeEnableStaticNat()"><g:message code="common.cancel"/></button>
            </div>
        </div>
        </div>
        
    </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vpcIPDisableStaticNatDialog" title="<g:message code="common.disableStaticNat"/>" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src="css/theme/fog-classic/images/cloud_icons/staticnat_icon.png"></div>        
        <div class="span10">
            <div class="span12"><p><g:message code="common.disableStaticNatMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.disableStaticNat()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewVpc.closeDisableStaticNat()"><g:message code="common.cancel"/></button>
    </div>
</div>
