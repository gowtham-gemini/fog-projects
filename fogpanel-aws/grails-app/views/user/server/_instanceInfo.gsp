<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/" class="overflowLabel"><g:message code="menu.user.cloud"/></a></li>
            <li>/<li>
            <li><a href="#/user/server/list" class="overflowLabel"><g:message code="menu.user.cloud.instance"/></a></li>
            <li>/<li>
            <li id="serverName"></li>
        </ul>
    </div>
</div>
<!--<div id="instanceInfoLoader" class="row-fluid">
</div>-->
<!--<div id="instnaceInfoPageDiv" class="row-fluid" style="display: none;">-->
<div class="row-fluid" style="display: none" id="vmDetailPageLoader">
    <div class="span12">                        
        <img  src="${resource(dir: 'images')}/vmload.gif" alt="progress" height="9" width="100">
        <p><g:message code="common.loading"/></p>
    </div>
</div>  
<div  style="display: none" id="currentInstanceDetailPage">
<div class="row-fluid" id="currentInstanceInfo">
    <div class="navbar">  
        <div class="instanceinfo-title-cont span12">
            <div class="instanceinfo-oslogo span2">
                <img  src="${resource(dir: 'images')}/vm_icon.png" id="osImage" alt="os image" height="110" width="110" />
            </div>
            <div class="span6">
                <div class="row-fluid">
                    <div class="instanceinfo-osname">
                        <span id="osName"></span>
                        <input type="hidden" id="templateId">
                        <input type="hidden" id="baseOs">
                        <input type="hidden" id="instanceId">
                    </div>
                </div>  
                <div class="row-fluid">
                    <div class="instanceinfo-hostname span6">

                        <span id="vmName" class="overflowLabel" style="width: 180px;"></span>
                        <div class="instanceinfo-hostname-elements">
                            <ul>
    <!--                            <li class="hostname-edit" id="vmNameEditDiv">
                                    <button class="hostname-editbtn" title="<g:message code="user.vm.editDiskplayName"/>" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.showChangeHostName(); "><i class="icon-edit"></i></button>
                                </li>-->
                                <li class="hostname-snapshot" id="vmSnapDiv">
                                    <button title="<g:message code="menu.user.cloud.vmSnapShot"/>" data-dojo-type="dijit.form.Button"><i class="icon-camera"></i></button>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="span5" id="viewVMSnapLinkDiv" style="display: none;">
                        <div class="instanceinfo-hostname span12">
                            <div class="row-fluid">
                                <div class="span8"><a class="vmSnap" href="" title="<g:message code="user.snapshot.viewVMSnapshot"/>" id="viewVMSnapLink"><g:message code="common.snapshot.VMSnapshot"/>:</a></div>
                                <div class="span3">
                                    <span id="vmSnapCount"  class="vmSnap" style="color: rgb(56, 124, 44);">0</span>   
                                </div>                                                                       
                            </div>                                                                
                        </div>
                    </div>
                </div>
            </div>
            <div class="span4">
                <div class="span7 console-editor-elements-cont" id="console-cont-btn-disable">
                    <div class="span12">
                        <div class="row-fluid">
                            <div class="controls span10">
                                <div id="isoListDiv"></div>
                                <div id="isoAttach" style="display: none">
                                    <span id="isoName"></span>
                                    <input type="hidden" id="isoId">
                                </div>
                            </div>
                            <div class="span2" style="display: none;">
                                <img id="isoLoader" style="float: right; display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <button title="<g:message code="common.eject"/>" class="btn btn-inverse" style="display: none;" id="isoEject" onClick="CurrentInstanceInfo.detachIso();"><i class="icon-eject"></i></button>
                            </div>
                        </div>
                        <div class="row-fluid">	
                            <div class="span3">
                                <button title="<g:message code="common.start"/>" id="startVMButton" data-dojo-type="dijit.form.Button" onClick="ViewServer.startVMConfirm();"><i class="icon-play"></i></button>
                            </div>
                            <div class="span3">
                                <button  title="<g:message code="common.stop"/>" id="stop" data-dojo-type="dijit.form.Button" onClick="ViewServer.stopInstanceShow();"><i class="icon-off"></i></button>
                            </div>
                            <div class="span3">
                                <button title="<g:message code="common.reboot"/>" id="reboot" data-dojo-type="dijit.form.Button" onClick="ViewServer.getRebootConformation();"><i class="icon-refresh"></i></button>
                            </div>
                            <div class="span3">
                                <button title="<g:message code="common.delete"/>" id="terminate" data-dojo-type="dijit.form.Button" onclick ="ViewServer.getTerminateConformation()"><i class="icon-remove"></i></button>
                            </div>
                        </div>
                    </div>
                </div>                    
            </div>
            <input type="hidden" id="computTag">
            <input type="hidden" id="computReferId">
            <input type="hidden" id="offerType">
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="span12" id="userViewNetworkPage">
        <div id="vmInfoTabContainer" data-dojo-type="dijit/layout/TabContainer" class="row-fluid" style="overflow: visible; display: block; min-height: 500px" tabStrip="true">
            <div onshow="GraphInfo.clearGraphSession()" data-dojo-type="dijit/layout/ContentPane" class="span12" title="<g:message code="common.details"/>" selected="true">                
                <div class="row-fluid span10">
                    <div class="span6">
                        <div class="row-fluid vm-instance-infodetail" id="vmInfo">
                            <div class="span12">
                                <div class="span3"><g:message code='user.vm.publicIp' /></div>
                                <div class="span8">
                                    <span id="nicIp" class=""></span>
            <!--                        <span class="verticalSeparator" id="aquireIPContainer">
                                        <span id="acquireIpDiv"><a onclick="CurrentInstanceInfo.showAcquireIp();" title="<g:message code='common.ip' />" class="overflowLabel"><g:message code='common.user.acquire' /></a></span>
                                    </span>
                                    <span id="revokeIpDiv"><a  title="<g:message code='common.revoke' />" id="revokeIpLink"><g:message code='common.revoke' /></a></span>
                                    <span style="display: none;" id="viewSecondIPAction" class="hide_text"><a>View SecondaryIP</a> </span>-->
                                </div>
                            </div>       
                            <div class="span12">
                                <div class="span3"><g:message code='user.vm.dnsName' /></div>
                                <div class="span8">
                                    <span id="dnsName" class=""></span>           
                                </div>
                            </div> 
                            <div class="span12">
                                <div class="span3"><g:message code='common.status' /></div>
                                <div class="span8">
                                    <span id="status"></span> 
                                    <img id="vmLoader" src="${resource(dir: 'images')}/vmload.gif" alt="progress" height="9" width="100" style="display: none"> 
                                </div>
                            </div>
                            <div class="span12">
                                <div class="span3"><g:message code='common.plan' /></div>
                                <div class="span8">
                                    <span id="plan" class="" style="overflow: hidden; text-overflow: ellipsis;"></span>
            <!--                        <a id="changePlanOption" onclick="CurrentInstanceInfo.changePlanShow();" title="<g:message code='common.changePlan' />"><g:message code='common.changePlan' /></a>
                                    <img id="changePlanLoader" title="<g:message code='common.loading' />" style="float: right; display: none;" src="${resource(dir: 'images')}/password.gif" alt="loading" height="28" width="28">-->
                                </div>
                            </div>
            <!--                <div class="span12" id="vmFirewallInfo">
                                <div class="span3"><g:message code='user.createVM.firewall.label' /></div>
                                <div class="span8">
                                    <span id="fireWall"></span>
                                </div>
                            </div>-->
                            <div class="span12">
                                <div class="span3"><g:message code='user.createVM.SSHKey.label' /></div>
                                <div class="span8">
                                    <!--<span>-->
                                    <span id="sshKeyName" class="" style="overflow: hidden; text-overflow: ellipsis;"></span>
                                    <!--<a onclick="CurrentInstanceInfo.showChangeSSHKey();" title="<g:message code='user.vm.changeKey' />"><g:message code='user.vm.changeKey' /></a>-->
                                <!--</span>-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="span12"></div>            
                </div> 
                
            </div>
<!--            <div data-dojo-type="dijit/layout/ContentPane" class="span12" title="<g:message code="menu.user.vm.volume"/>" onshow="ViewServer.attachedVolumeList(), GraphInfo.clearGraphSession()" style="display: none">
                <div class="row-fluid" style="margin-top: 20px"><div class="span1"></div>
                    <form id="attachVolumeFormList" data-dojo-type="dijit.form.Form">
                        <div class="table-wrapper products-table">
                            <div class="row-fluid filter-block">
                                <div class="pull-right" >
                                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewServer.attachedVolumeList()">
                                        <g:message code='common.refresh' />
                                    </button> 
                                    <a class="btn-flat success new-product" onclick="ViewServer.attachVolumeDialog()" title="<g:message code='common.instance.volumeAttach'/>"><g:message code="common.instance.volumeAttach"/></a>        
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div id="attachedVolumeListGrid"></div>
                                <div class="alert alert-info hide overflowLabel text_gray" id="noAttachedVolumeMessageBox" style="display: none">
                                    <i class="icon-exclamation-sign text_gray"></i> 
                                    <g:message code="common.instance.noVolumeAttached"/>&nbsp;&nbsp;<a onclick="ViewServer.attachVolumeDialog()"><g:message code="common.attachOneNow"/></a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>

                <input type="hidden" id="deatchedVolumeId">

            </div>-->
        </div>    
    </div>       
</div>
<div data-dojo-type="dijit.Dialog" id="vmInfoStopDialog" title="<g:message code='user.vm.stopVm.title' />" style="color: black; width: 350px; background: #FFFFFF">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_shutdown_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.stopVm.confirm' /></div>            
        </div>
    </div>
    <div class="row-fluid">           
        <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ViewServer.stopInstance()" id="vmInfoStopButton"> <g:message code='common.ok' /> </button>
        <button id="vmInfoCancelButton" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeStopDialog()" class="cancelbtn"> <g:message code='common.cancel' /></button> 
    </div>                    
</div>    
<div data-dojo-type="dijit.Dialog" id="vmInfoStartDialog" title="<g:message code='user.vm.startVm' />" style="color: black; width: 350px;" class="customDialgue">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_play_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.startVm.confirm' /></div>            
        </div>
    </div>
    <div class="row-fluid">
        <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="ViewServer.startInstance()" id="vmInfoStartButton">   
            <g:message code='common.ok' />    
        </button>
        <button class="cancelbtn" id="startvmInfoCancelButton" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeStartDialog()">
            <g:message code='common.cancel' />
        </button> 
    </div>        
</div>

<div data-dojo-type="dijit.Dialog" id="vmInfoRebootDialog" title="<g:message code='user.instance.rebootInstance.confirmTitle' />" style="color: black; width: 350px; background: #FFFFFF;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.instance.rebootInstance.confirmMessage' /> </div>            
        </div>        
    </div>            
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ViewServer.rebootInstance()"> <g:message code='common.reboot' /> </button>
            <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeRebootDialog()"> <g:message code='common.cancel' /></button> 
        </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="vmInfoTerminateDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.deleteVm.confirm' />  </div>            
        </div>        
         <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ViewServer.terminateInstance()" id="vmInfoDeleteButton">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="DeletevmInfoCancelButton" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeTerminateDialog()" class="cancelbtn">
                <g:message code='common.cancel' />
            </button> 
         </div>
    </div>                        
</div>
<div data-dojo-type="dijit.Dialog"  id="vmInfoActionLoader" class="customDialgue span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.serverTaskInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.serverTaskInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="ListServer.gotoInstances()"><g:message code='common.clickHereToClose' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
</div>
