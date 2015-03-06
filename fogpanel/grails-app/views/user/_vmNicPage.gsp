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
        <li class="active">
             <a id="vmInfoNicTag"><g:message code="menu.user.vm.nics"/></a>
        </li> 
        <li id="aquireIPMenuItem">
            <a id="vmInfoAquireIPTag"><g:message code="common.secondaryIp"/></a>
        </li>
    </ul>
</div>
<div class="row-fluid">
    <div class="pull-right" id="vmNicButtonDiv">
        <button data-dojo-type="dijit.form.Button" class="primarybtn" id="addNetworkButton" onclick="CurrentVMNicInfo.confirmADDNetwork()"><g:message code="common.addNetworkToVM"/></button>
    </div>
</div>
<div class="row-fluid" style="display: none" id="nicLoaderContainer">
    <div class="span4"></div>
    <div class="span6">
        <img src='images/vmload.gif'height='36' width='100' id="nicLoader"/>
        <p><g:message code='common.loadingNetWork' /></p>
    </div>            
</div>
<div class="row-fluid" style="display: none" id="nicSetDefaultLoaderContainer">
    <div class="span4"></div>
    <div class="span6">
        <img src='images/vmload.gif'height='36' width='100'/>
        <p><g:message code='common.loading' /></p>
    </div>            
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="nicProcessLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div class="row-fluid" id="nicListContainer"> 
    <div class="row-fluid">
        <div class="span12">
            <div id="nicList" class="span12"></div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vmInfoAddNetworkDialogue" title="<g:message code='common.addNetworkToVM' />" style="color: black; width: 350px;background: #FFFFFF">    
    <div class="row-fluid">
        <div class="span12"><p><g:message code='common.network.addInfo' /></p></div>
    </div>
    <div class="row-fluid">        
        <div class="span2">
        <input type="hidden" id="currentVMRefId" />
        <input type="hidden" id="currentNicRefID"/>
        <input type="hidden" id="currentPaneWidgetId"/>
        <img src='images/statsBox/network.png' id="networkImage"/></div>
        <div style="display: none;" id="noNetworkList">
        <div class="alert alert-info" id="noOfferMessageBox">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.user.allNetworkAdedToVM"/>
        </div>
        
        </div>
        <div class="span8" id="networkListForm">
            <form class="form-horizontal" data-dojo-type="dijit.form.Form">
                <div class="control-group"> 
                    <label for="hostName" class="control-label">
                        <g:message code='common.network' />
                        <span class="require">*</span>:
                    </label>
                    <div class="controls">
                        <div id="nicInfoNetworkList"></div>
                    </div>
                </div>                 
            </form>
        </div>        
    </div>     
    <div class="row-fluid" id="networkListFormButton">
        <button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentVMNicInfo.addNetworktoVM()"><g:message code='common.add' /></button>
        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
        <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentVMNicInfo.cancelAddNetwork()"><g:message code='common.cancel' /></button>
        <span class="require" id="requiredMsgForNic" style="display: none"><g:message code='user.createVM.required' /></span>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="vmdefaultDialogue" title="<g:message code='common.nic.setasDefault' />" style="color: black; width: 350px;" class="customDialgue">
    <div class="row-fluid">               
        <div class="span12">
            <div class="span12"><g:message code='common.nic.setasDefaultConfirm' /></div>            
        </div>          
    </div>
    <div class="row-fluid">
        <button type="button"  data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="CurrentVMNicInfo.setDefaultNic()" id="">   
            <g:message code='common.yes' />    
        </button>
            
        <button class="cancelbtn"  id="" data-dojo-type="dijit.form.Button" onclick="CurrentVMNicInfo.closeDefaultDialogue()">
            <g:message code='common.no' />
        </button> 
    </div>   
</div>
<div data-dojo-type="dijit.Dialog" id="vmNicDeleteDialogue" title="<g:message code='common.nic.deleteNic' />" style="color: black; width: 350px;" class="customDialgue">
    <div class="row-fluid">               
        <div class="span12">
            <div class="span12"><g:message code='common.nic.deleteNicConfirm' /></div>            
        </div>          
    </div>
    <div class="row-fluid">
        <button type="button"  data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="CurrentVMNicInfo.deleteNic()" id="">   
            <g:message code='common.yes' />    
        </button>
           
        <button class="cancelbtn"  id="" data-dojo-type="dijit.form.Button" onclick="CurrentVMNicInfo.closeDeleteDialogue()">
            <g:message code='common.no' />
        </button> 
    </div>   
</div>