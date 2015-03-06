<div class="row-fluid" style="display: none;">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/" class="overflowLabel"><g:message code="menu.user.cloud"/></a></li>
            <li>/<li>
            <li class="overflowLabel"><a href="#/user/cloud/userInstancePage"> <g:message code="menu.user.cloud.instance"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.vm.nics"/></li>
          </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12">
        <li>
            <a id="vmInfoDetailTag"><g:message code="menu.user.vm.detail"/></a>
        </li>
        <li>
            <a id="vmInfoStorageTag"><g:message code="menu.user.vm.storage"/></a>
        </li>
        <li>
             <a id="vmInfoNicTag"><g:message code="menu.user.vm.nics"/></a>
        </li> 
        <li class="active" id="aquireIPMenuItem">
            <a id="vmInfoAquireIPTag"><g:message code="common.secondaryIp"/></a>
        </li>
    </ul>
</div>
<div class="row-fluid"><div class="span12"></div></div>
<div class="row-fluid"><div class="span12"></div></div>
<div class="row-fluid">
<div class="alert alert-danger" style="display: none;" id="vmInfoprivateIPLimitReachedMsg"><i class="icon-exclamation-sign"></i><g:message code="common.reachedLimit"/></div></div>
<div class="row-fluid" id="acquireIpPageDiv">
    <form class="form-horizontal" data-dojo-type="dijit.form.Form">
        <div class="control-group span5 zone-cost-boxcont" id="acquireIpPage"> 
            <label for="hostName" class="control-label">
                <g:message code='common.network' />
                <span class="require">*</span>:
            </label>
            <div class="controls">
                <div id="vmInfoAquireIPNetworkList"></div>
            </div>
        </div>
        <div class="span3" id="vmInfoPrivateIPActionButton">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="CurrentVMSecondaryIP.nicAcquireIpShow()"><g:message code="common.acquireIP"/></button>
        </div>        
    </form>
</div>
<div class="row-fluid">
    <div id="nicSecondaryIpList">  
    </div>
    <div class="alert alert-info hide" id="noOfferMessageBox" style="display: none">
      <i class="icon-exclamation-sign"></i> 
      <g:message code="common.user.noIPForNic"/>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="nicAcquireIpDialog" title="<g:message code='common.acquireIP' />" class="span4">
    <div class="row-fluid">
        <div class="span2"><img src="images/popup-icons/ip_icon.png" /></div>
        <div class="span10">
            <div class="span12">
                <p><g:message code='common.user.ip.confirmNic' /></p>
            </div>            
        </div>        
    </div>
    <div class="row-fluid">
        <div class="span8">               
            <button class="primarybtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="CurrentVMSecondaryIP.acquireIp()"><g:message code='common.ok' /></button>
            <button class="cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="CurrentVMSecondaryIP.closeAcquireIpDialog()"><g:message code='common.cancel' /></button>
        </div>  
    </div>              
</div>
<div data-dojo-type="dijit.Dialog" id="nicReleaseIpDialog" title="<g:message code="common.ip.releaseIP"/>" class="span4">
    <input type="hidden" id="currentNicIpId">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>
        <div class="span10">
            <div class="span12"><p><g:message code="common.user.ip.releaseNic"/></p></div>
        </div>                                    
    </div>

    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="CurrentVMSecondaryIP.revokeIp()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="CurrentVMSecondaryIP.closeReleaseIpDialog()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="nicIpLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog"  id="vmSecondaryIPContentDialogue" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
    <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
        <div id="vmDetailInfoContentArea">
        </div>                           
        <div class="row-fluid">
            <div class="span10">                
            </div>
            <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentVMSecondaryIP.cancelConditionBox()"><g:message code="common.ok"/></button></div>
        </div>
    </div>
</div>