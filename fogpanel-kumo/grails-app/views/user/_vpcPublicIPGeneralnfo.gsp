<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.publicIP"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right">
        <input type="hidden" id="currentGeneralIPId">
        <input type="hidden" id="currentGeneralEnableStaticNatIpId">
        <a class="btn-flat success new-product" onclick="ListAllPublicIPInfo.acquireIPShow()"><g:message code="common.acquireIP"/></a>          
    </div>
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid"><div class="span12"></div></div>    
    <div class="row-fluid">
      <div id="vpcGeneralPublicIP"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noGeneralPublicIPMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noNetworkIp"/>
      </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcGenerealReleaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">   
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.releaseIP()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.closeReleaseIPShow()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcGenerealIpEnableStaticNatDialog" title="<g:message code="common.enableStaticNat"/>" class="span4"> 
    <input type="hidden" id="currentEnableStaticNatIpId">
    <div class="row-fluid container">
    <form id="vpcGeneralEnableStaticNatForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
        <div class="span9" id="vpcIpEnableStaticNatFormPage">
            <div class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="vpcTier" class="control-label">
                            <g:message code="common.tier"/>:
                        </label>
                        <div class="controls ">
                            <div id="vpcGeneralTierList"></div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="vpcTierVm" class="control-label">
                            <g:message code="common.instance"/>:
                        </label>
                        <div class="controls ">
                            <div id="vpcGeneralTierVmList"></div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.enableStaticNat()"><g:message code="common.ok"/></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.closeEnableStaticNat()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </form>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcGenerealIPPageDisableStaticNatDialog" title="<g:message code="common.disableStaticNat"/>" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code="common.disableStaticNatMessage"/></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.disableStaticNat()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.closeDisableStaticNat()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="viewGeneralIpLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>



<div data-dojo-type="dijit.Dialog" id="generalPublicIPAcquireDialog" title="<g:message code="common.acquireIP"/>" class="span6">   
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code='common.user.ip.confirmNetwork' /></p></div> 
            <div class="span12">
                <div class="row-fluid">   
                    <form data-dojo-type="dijit.form.Form" class="form-horizontal">        
                        <div class="span9">
                            <div class="form-horizontal">
                                <div class="row-fluid">                    
                                    <div class="control-group">
                                        <label class="control-label">
                                            <g:message code="common.zone"/>
                                            <span class="require">*</span>
                                        </label>
                                            <div class="controls ">
                                                <div id="generalPublcIPZoneList"></div>
                                                <img id="generalPublcIPZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            <g:message code="common.vpc"/>
                                            <span class="require">*</span>
                                        </label>
                                            <div class="controls ">
                                                <div id="generalPublicIPVPCList"></div>
                                                <img id="generalPublcIPVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                    </div>                    
                                </div>
                            </div> 
                        </div>
                    </form>
                </div>
            </div>
            <div class="span12">
                <div class="row-fluid">
                    <div class="span2"></div>
                    <div class="span10">
                        <div class="span12" style="margin-left: 0">              
                            <div class="span7">
                                <p><g:message code="common.agreeThe"/><a onClick="ListAllPublicIPInfo.showCondition()"> <g:message code="signup.termsAndConditions"/></a></p>
                            </div>
                            <div class="span2">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="generalPublicIPInfoAgreement" name="agreement">     
                            </div>                    
                        </div>                        
                </div>
            </div>            
            <div class="span12"><div class=""> <span class="validation" id="conditionExceptionMsg" style="margin-left: 0"><g:message code="common.termsCondition.info"/></span></div></div>
            </div>
        </div>                                            
    <div class="row-fluid">
        <div class="pull-right">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.acquireIp()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.closeAcquireIPShow()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>
</div>
<div data-dojo-type="dijit.Dialog"  id="generalPublicIPContent" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
    <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
        <div id="generalPublicIPContentArea">
        </div>                           
        <div class="row-fluid">
            <div class="span10"></div>
            <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListAllPublicIPInfo.cancelConditionBox()"><g:message code="common.ok"/></button></div>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="generalPublicIPLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>