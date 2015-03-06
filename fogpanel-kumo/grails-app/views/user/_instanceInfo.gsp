<div class="row-fluid" id="currentInstanceInfo">
    <div class="navbar">  
        <div class="instanceinfo-title-cont span12">
            <div class="instanceinfo-oslogo span2">
                <img id="osImage" alt="os image" height="80" width="80" />
            </div>
            <div class="span6">
                <div class="row-fluid">
                    <div class="instanceinfo-osname">
                        <span id="osName"></span>
                        <input type="hidden" id="templateId">
                        <input type="hidden" id="baseOs">
                    </div>
                </div>  
                <div class="row-fluid">
                <div class="instanceinfo-hostname span6">
                    
                    <span id="vmName" class="overflowLabel" style="width: 180px;"></span>
                    <div class="instanceinfo-hostname-elements">
                        <ul>
                            <li class="hostname-edit" id="vmNameEditDiv">
                                <button class="hostname-editbtn" title="<g:message code="user.vm.editDiskplayName"/>" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.showChangeHostName(); "><i class="icon-edit"></i></button>
                            </li>
                            <li class="hostname-snapshot" id="vmSnapDiv">
                                <button class="hostname-snapshotbtn" title="<g:message code="menu.user.cloud.vmSnapShot"/>" data-dojo-type="dijit.form.Button" onclick ="CurrentInstanceInfo.showCreateVMSnapshotDialog()"><i class="icon-camera"></i></button>
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
            <div class="span1" style="margin-top: 8px;"><img title="<g:message code='common.vm.reset' />" onclick="CurrentInstanceInfo.resetVMConfirmMsg()" src="images/popup-icons/vm_reset_icon.png" style="cursor: pointer;"/></div>
            </div>
            </div>
            <div class="span4 console-cont">
                <div class="span5 console-editor" id="consoleDiv" style="color: #F5F5F5;">
                    <img id="viewCosoleImg" title='<g:message code="user.vm.startConsole"/>' src="${resource(dir: 'images')}/console_start_ntxt.png" height="80" width="80" onClick="CurrentInstanceInfo.viewConsole();">
                    <div class="row-fluid"><span><g:message code="user.vm.startConsole"/></span></div>                        
                </div>
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
                            <div class="span2">
                                <img id="isoLoader" style="float: right; display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                <button title="<g:message code="common.eject"/>" class="btn btn-inverse" style="display: none;" id="isoEject" onClick="CurrentInstanceInfo.detachIso();"><i class="icon-eject"></i></button>
                            </div>
                        </div>
                        <div class="row-fluid">	
                            <div class="span3">
                                <button class="console-elements-startbtn" title="<g:message code="common.start"/>" id="startVMButton" data-dojo-type="dijit.form.Button" onClick="CurrentInstanceInfo.startVMConfirm();"><i class="icon-play"></i></button>
                            </div>
                            <div class="span3">
                                <button class="console-elements-stopbtn" title="<g:message code="common.stop"/>" id="stop" data-dojo-type="dijit.form.Button" onClick="CurrentInstanceInfo.stopInstanceShow();"><i class="icon-off"></i></button>
                            </div>
                            <div class="span3">
                                <button class="console-elements-rebootbtn" title="<g:message code="common.reboot"/>" id="reboot" data-dojo-type="dijit.form.Button" onClick="CurrentInstanceInfo.rebootInstanceConfirm();"><i class="icon-refresh"></i></button>
                            </div>
                            <div class="span3">
                                <button class="hostname-deletebtn" title="<g:message code="common.delete"/>" data-dojo-type="dijit.form.Button" onclick ="CurrentInstanceInfo.deleteInstanceShow()"><i class="icon-remove"></i></button>
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
        <div class="row-fluid">
            <div class="span5">
                <div class="row-fluid vm-instance-infodetail" id="vmInfo">
                    <div class="span12">
                        <div class="span3"><g:message code="user.createVM.zone.label"/></div>
                        <div class="span8">
                            <span id="location"></span>
                            <input type="hidden" id="locationId">
                        </div>
                    </div>
                    <div class="span12" id="networkInfo" class="hide_text">
                        <div class="span3"><g:message code='common.network' /></div>
                        <div class="span8">
                            <a id="networkName"></a>
                            <span class="verticalSeparator" id="acquireIpDiv">
                                <span><a onclick="CurrentInstanceInfo.showAcquireIp();" title="<g:message code='common.addNetworkToVM' />" class="overflowLabel"><g:message code='common.addNetworkToVM' /></a></span>
                            </span>                            
                        </div>
                    </div>
                    <div class="span12">
                        <div class="span3"><g:message code='user.vm.ipAddress' /></div>
                        <div class="span8">
                            <span id="nicIp" class="verticalSeparator"></span>
                            <span class="verticalSeparator" id="aquireIPContainer">
                                <span id="acquireIpDiv"><a onclick="CurrentInstanceInfo.showAcquireIp();" title="<g:message code='common.ip' />" class="overflowLabel"><g:message code='common.user.acquire' /></a></span>
                            </span>
                            <span id="revokeIpDiv"><a  title="<g:message code='common.revoke' />" id="revokeIpLink"><g:message code='common.revoke' /></a></span>
                            <span style="display: none;" id="viewSecondIPAction" class="hide_text"><a>View SecondaryIP</a> </span>
                        </div>
                    </div>
                    <div class="span12">
                        <div class="span3"><g:message code='common.user' /></div>              
                        <div class="span8">
                            <span id="user" class=""><g:message code='common.root' /></span>                        
                        </div>
                    </div>
                    <div class="span12">
                        <div class="span3"><g:message code='common.password' /></div>
                        <div class="span8">
                            <span>
                                <span style="border-right: 1px solid #000000;height: 18px; margin-right: 10px; padding: 0 10px 0 0; width: auto;display: none;"></span>
                                <a class="verticalSeparator" onclick="CurrentInstanceInfo.getPassword();" title="<g:message code='common.showPassword' />"><g:message code="common.showPassword"/></a>
                                <a onclick="CurrentInstanceInfo.resetPassword();"  title="<g:message code='common.resetPassword' />" id="resetPasswordTag" class="overflowLabel"><g:message code="common.resetPassword"/></a>
                                <img id="resetPaaLoader" title="<g:message code='common.loading' />" style="float: right; display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="28" width="28"> 
                            </span>
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
                            <span id="plan" class="verticalSeparator" style="overflow: hidden; text-overflow: ellipsis;"></span>
                            <a id="changePlanOption" onclick="CurrentInstanceInfo.changePlanShow();" title="<g:message code='common.changePlan' />"><g:message code='common.changePlan' /></a>
                            <img id="changePlanLoader" title="<g:message code='common.loading' />" style="float: right; display: none;" src="${resource(dir: 'images')}/password.gif" alt="loading" height="28" width="28">
                        </div>
                    </div>
                    <div class="span12" id="vmBandwidthInfo">
                        <div class="span3"><g:message code='common.bandwidth' />:</div>
                        <div class="span8">
                            <span id="totalBandWidth"></span><br>
                        </div>
                    </div>
                    <div class="span12" id="vmFirewallInfo">
                        <div class="span3"><g:message code='user.createVM.firewall.label' /></div>
                        <div class="span8">
                            <span id="fireWall"></span>
                        </div>
                    </div>
                    <div class="span12">
                        <div class="span3"><g:message code='user.createVM.SSHKey.label' /></div>
                        <div class="span8">
                            <!--<span>-->
                                <span id="sshKeyName" class="verticalSeparator" style="overflow: hidden; text-overflow: ellipsis;"></span>
                                <a onclick="CurrentInstanceInfo.showChangeSSHKey();" title="<g:message code='user.vm.changeKey' />"><g:message code='user.vm.changeKey' /></a>
                            <!--</span>-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="span7">

                <div class="row-fluid" id="instanceUsageLoad" style="display: none">
                    <img  src="${resource(dir: 'images')}/vmload.gif" alt="progress" height="9" width="100"> 
                </div>
                <div class="row-fluid" id="instanceUsageDiv">
                    <div class="row-fluid" style="display: none">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"><g:message code='user.vm.networkRead' /> :</div>
                            <div class="span4 resource_items_allocation" id="readTotal"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="readTotalExe" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right" id="readTotalProgress"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="instanceNetworkRead" id="instanceNetworkRead"></div>
                            </div>
                        </div>
                    </div>
                            <div class="row-fluid" id="networkWriteDiv">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"> <g:message code='user.vm.networkWrite' /> :</div>
                            <div class="span4 resource_items_allocation" id="writeTotal"></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="writeTotalExe" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right" id="writeTotalProgress"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="instanceNetworkWrite" id="instanceNetworkWrite"></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"> <g:message code='user.vm.cpuUsage' />:</div>
                            <div class="span4 resource_items_allocation" id="cpuTotal"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="instanceCpuUsage" id="instanceCpuUsage"></div>
                            </div>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"><g:message code='user.vm.diskIoRead' />:</div>
                            <div class="span4 resource_items_allocation">0 <g:message code='common.gb' />/0 <g:message code='common.gb' /></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="" id=""></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"><g:message code='user.vm.diskIoWrite' />:</div>
                            <div class="span4 resource_items_allocation" id="">0 <g:message code='common.gb' />/0 <g:message code='common.gb' /></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="" id=""></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"><g:message code='user.vm.diskByteWriter' />:</div>
                            <div class="span4 resource_items_allocation" id="">0 <g:message code='common.gb' />/0 <g:message code='common.gb' /></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="" id=""></div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span12 resource_items_cont">
                            <div class="span3 resource_items_memory"><g:message code='user.vm.diskByteRead' />:</div>
                            <div class="span4 resource_items_allocation" id="">0 <g:message code='common.gb' />/0 <g:message code='common.gb' /></div>
                            <div class="span4 resource_items_progress_exceeded pull-right" id="" style="display: none;"></div>
                            <div class="span4 resource_items_progress pull-right"> 
                                <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2"></div>
                            </div>
                        </div>
                    </div>           
                    <div class="row-fluid">
                        <div class="span12 resource_refresh">
                            <button data-dojo-type="dijit.form.Button"  onclick="CurrentInstanceInfo.refresh()" class="defaultbtn" title="<g:message code='common.refresh' />"><i class="icon-refresh"></i> <g:message code='common.refresh' /></button> 
                        </div>         
                    </div>
                </div>
            </div> 
            <div class="span12"></div>            
        </div>
        <input type="hidden" id="instanceBilingType"> 
        <div data-dojo-type="dijit.Dialog" id="changeSSHKeyDialog" title="<g:message code='user.vm.changeSSHKey' />" class="span4">
            <div class="form-horizontal">
                <input type="hidden" id="vmPlanId">
                <div class="control-group">
                    <label for="sshKeyList" class="control-label"><g:message code='user.createVM.SSHKey.label' />:</label>
                    <div class="controls">
                        <div id="sshKeyPlanList"></div>
                    </div>
                </div> 
                <div class="row-fluid" id="changeSSHKeyBtnDiv">
                    <div class="pull-right">
                        <button id="" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.changeSSHKey()"><g:message code='common.ok' /></button>
                        <button id="" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeChangeSSHKeyDialog()"><g:message code='common.cancel' /></button>
                    </div>
                </div>
                <div id="changeSSHKeyLoader" class="row-fluid offset1">
                    <img id="" style="width: 10%" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="100"> 
                </div>
            </div>
        </div>

        <div data-dojo-type="dijit.Dialog" id="changeServiceDialog" title="<g:message code='common.resizeComputOffer' />" style="background: #FFFEFF; height: auto;">
            <div class="span8">
                <input type="hidden" id="vmPlanId">
                <input type="hidden" id="offerId">
            <form class="form-horizontal" data-dojo-type="dijit.form.Form">       
                <div class="row-fluid"><div class="span8"></div>
                    <div class="span4">
                        <div class="span6"><h5><g:message code='common.summary' /></h5></div>
                        <div class="span6">
                            <div class="span12 control-group">
                                <g:render template="rateCard" />
                                <a style="float: right;" onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a>
                            </div>
                        </div>                        
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span8">
                        <div class="span12"></div>
                        <div class="span12 control-group field-box">
                            <label for="instancePlanList" class="control-label" style="font-size:12px;"><g:message code='common.plans' />:</label>
                            <div class="controls">
                                <div id="OfferingListCollection"><div id="instancePlanList"></div></div>
                                <img id="currentVMOfferingLoader" style="display: none; width: 10%" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="100">
                                <span id="instancePlanName" class=""></span>                                                                                                                                
                            </div>
                        </div> 
                        <div class="span12 control-group field-box">
                            <label class="costLabel control-label" id="changeplaneRunningCost"></label>
                            <div class="controls">
                                <span id="instancePlanCost" class="unitCost template_text span10"></span>
                            </div>
                        </div> 
                        <div class="span12 control-group field-box">
                            <label class="control-label"><g:message code='common.warning' /></label>
                            <div class="controls">
                                <span id="" class=""><g:message code='common.vm.stopVMWarning' /></span>
                            </div>
                        </div> 
                        <div class="span12 control-group">
                            <label class="costLabel" id="changeplaneSetupCost" style="display: none"></label>
                            <div class="controls">
                                <span id="instancePlanSetupCost" class="unitCost" style="display: none"></span>
                            </div>
                        </div> 
                    <div class="span12">
                        <!--<div class="row-fluid">-->
                        <div class="span8"></div>
                    <div class="control-group span4">
                        <button id="changePlan" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.changePlan()"><g:message code='common.resize' /></button>
                        <img id="changePlaneLoader" style="display: none; width: 10%" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="100">
                        <button id="cancelPlan" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeChangeServiceDialog()"><g:message code='common.cancel' /></button>
                    </div>
                <!--</div>-->
                    </div>
                    
                    </div>
                    <div class="span4 with-sidebar">
                        <div class="span12"></div>
                        <div class="span12 control-group">
                            <label for="" class="control-label"><g:message code='common.cpu' />:</label>
                            <div class="controls">
                                <span id="instancePlanCpu"></span>
                            </div>
                        </div> 
                        <div class="span12 control-group">
                            <label for="" class="control-label"><g:message code='common.ram' />:</label>
                            <div class="controls">
                                <span id="instancePlanRam"></span>
                            </div>
                        </div>
                        <div class="span12 control-group">
                            <label for="" class="control-label"><g:message code='common.systemDisk' />:</label>
                            <div class="controls">
                                <span id="instancePlanTempSize"></span>
                            </div>
                        </div>
                        <div class="span12 control-group">
                            <label for="" class="control-label"><g:message code='common.network' />:</label>
                            <div class="controls">
                                <span id="instancePlanNwRate"></span>
                            </div>
                        </div>
                        <div class="span12 control-group">
                            <label for="" class="control-label"><g:message code='common.bandwidth' />:</label>
                            <div class="controls">
                                <span id="instancePlanBandwidth"></span>
                            </div>
                        </div>
                        <div class="span12 control-group">
                            <label for="" class="control-label"><g:message code='common.diskIO' />:</label>
                            <div class="controls">
                                <span id="vmPlanIODisk"></span>
                            </div>
                        </div>                                                                     
                    </div>
                </div>                                                          
            </form>
            </div>
        </div>
        <div data-dojo-type="dijit.Dialog" id="deleteVmDialog" title="<g:message code='common.vm.destroyInstance' />" class="span4">
            <div class="row-fluid">
                <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
                <div class="span10">
                    <div class="span12">
                        <p><g:message code='common.vm.deleteItem.confirm' /></p>
                    </div>
                </div>             
            </div>    
            <div class="row-fluid">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.deleteInstance()"><g:message code='default.button.delete.common' /></button>
                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeDeleteDialog()"><g:message code='common.cancel' /></button>
            </div>
        </div>

        <div data-dojo-type="dijit.Dialog" id="changeHostNameDialog" title="<g:message code='user.vm.changeDisplayName' />" class="span4">     
            <div class="form-horizontal" id="addVolumeForm">
                <div class="control-group"> 
                    <label for="hostName" class="control-label">
                        <g:message code='common.name' />
                        <span class="require">*</span>:
                    </label>
                    <div class="controls">
                        <input type="text" 
                        data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="invalidMessage: '<g:message code="user.createVM.diskplayName.invalidMessage"/>',
                        required: 'required', placeHolder: '<g:message code='user.createVM.diskplayName.placeHolder' />', 
                        regExp: '[a-zA-Z0-9-.]{4,63}'" 
                        name="hostName" id="hostName"> 
                    </div>
                </div> 
                <div class="row-fluid">
                    <button id="changeName" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.changeHostName()"><g:message code='common.save' /></button>
                    <img id="changeHostNameLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
                    <button id="cancelName" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeChangeHostName()"><g:message code='common.cancel' /></button>
                </div>
            </div>
        </div>                  
        </div>           
        <div data-dojo-type="dijit.Dialog" id="stopVmDialog" title="<g:message code='user.vm.shutdown.title' />" style="color: black; width: 350px;background: #FFFFFF">    
            <div class="row-fluid">
                <div class="span2"><img src='images/popup-icons/vm_shutdown_icon.png'/></div>
                <div class="span10">
                    <form class="new_user_form inline-input form-horizontal overflow-text" data-dojo-type="dijit.form.Form">
                        <div class="span12"><g:message code='user.vm.stopVm.confirm' /></div>
                        <div class="span12 field-box control-group" style="margin-bottom: 0 !important;margin-left: 0">                     
                            <div class="span5"><label for="vmForce" style="font-size: 13px;"><g:message code='common.forceshutdown' /></label></div>                                                                     
                            <div class="span1"> <input type="checkbox" data-dojo-type="dijit.form.CheckBox"  data-dojo-props="checked: false" id="vmForce" name="agreement" value= ""></div>
                        </div>
                    </form>
                </div>        
            </div>                      
            <div class="row-fluid">  
                <button type="button" data-dojo-type= "dijit.form.Button" onclick="CurrentInstanceInfo.stopInstance()" class="primarybtn" > <g:message code='common.ok' /> </button>
                <button  data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeStopDialog()" class="cancelbtn"><g:message code='common.cancel' /></button> 
            </div>        
        </div>
        <div data-dojo-type="dijit.Dialog" id="showPasswordDialog" style="width: 450px;" title="<g:message code='common.vm.passwordReset' />"> 
            <div class="row-fluid">
                <div class="span12">
                    <div class="span2">
                        <div class="span12 hide_text" id="resetPasswordImage"><img src='images/popup-icons/reset_password_icon.png'/></div>
                        <div class="span12 hide_text" id="showPasswordImage"><img src='images/popup-icons/show_password_icon.png'/></div>                
                    </div>
                    <div class="span10 form-horizontal">                
                        <div class="span12 field-box control-group" id="showPasswordInfo">
                            <label for="instanceZone" class="control-label"><g:message code='common.vmPassword' />:</label>
                            <div class="controls elements">
                                <input type="text" id="vmPasswordField" data-dojo-type="dijit.form.TextBox" data-dojo-props="disabled: false"/>                            
                            </div>
                        </div>
                        <div id="passwordResetMsg"><span id="vmPassword"></span></div>
                        <div class="span12" id="resetPassAction">
                            <a class="btn-flat default" onclick="CurrentInstanceInfo.stopInstanceShow()"><g:message code='common.vm.stop' /></a>
                            <button type="button" class="cancelbtn" data-dojo-type = "dijit.form.Button" onclick="CurrentInstanceInfo.cancelPasswordReset()"><g:message code='common.cancel' /></button>
                        </div>
                    </div>
                </div>  

            </div>
        </div>
<!--<script type="text/javascript"> CurrentInstanceInfo.populateValues();</script>-->

<!--<div class="row-fluid" id="statusButtons">
      <div class="span6">
        <div class="row-fluid" id="statusInfo">
          <button class="" type="button" data-dojo-type="dijit.form.Button"
                  onclick="UserInstances.getStartConformation();">
            Start</button>
          <button class="" type="button" data-dojo-type="dijit.form.Button" 
                  onclick="UserInstances.getStopConformation();">
            Stop</button>
          <button class="" type="button" data-dojo-type="dijit.form.Button"
                  onclick="UserInstances.getRebootConformation();">
            Restart</button>
          <button class="" type="button" data-dojo-type="dijit.form.Button" onclick="UserInstances.getPassword();">
            Get Password
          </button>
          <button class="" type="button" data-dojo-type="dijit.form.Button" onclick="UserInstances.resetPassword();">
            Reset Password
          </button>
          <img id="resetPaaLoader" src="${resource(dir: 'images')}/loader.gif" alt="loading" height="42" width="42" style="display: none">
        </div>
        <div class="row-fluid" id="vmInfo">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" 
           id="vmDetailForm">
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">Host</label>
              <div class="controls">
                <span>www.assistanz.com</span>                        
              </div>
            </div>
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">OS</label>
              <div class="controls">
                <span>Linux</span>                        
              </div>
            </div>
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">User</label>
              <div class="controls">
                <span>Test User</span>                        
              </div>
            </div>
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">Password</label>
              <div class="controls">
                <span>xxxxxx</span>                        
              </div>
            </div>
            
          </form>
        </div>
        <div class="row-fluid">
          <button class="" type="button" data-dojo-type="dijit.form.Button" 
                  onclick="CurrentInstanceInfo.showIpPage();">
                  Ip</button>
          <button class="" type="button" data-dojo-type="dijit.form.Button" 
                  onclick="CurrentInstanceInfo.showIsoPage();">
                  Iso</button>
          <button class="" type="button" data-dojo-type="dijit.form.Button"
                  onclick="CurrentInstanceInfo.showCpuPage();">
                  Cpu </button>
          <button class="" type="button" data-dojo-type="dijit.form.Button" 
                  onclick="CurrentInstanceInfo.showRamPage()">
                  Ram</button>
          
      </div>
        <div class="row-fluid">
          <button type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.storageDialog()">
                  Storage</button>
          <button type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.bandwidthDialog()">
                  Network</button>
    
          <button type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.snapShotDialog()">
                  Snap Shoot</button>
          <button class="" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.securityGroupsDialog()">
            Security Groups</button>
    
        </div>
  
        
      </div>
  <div class="span5">
    <div class="row-fluid" id="currentPlanDetails">
          <button class="offset5" type="button"
                  data-dojo-type="dijit.form.Button" onclick="UserInstances.showCreateVmPage()">Create VM</button>
        </div>
    <div class="row-fluid">
      <img class="span12 offset2" src="${resource(dir: 'images')}/console.png" alt="console" style="height: 380px"> 
    </div>
        <div class="row-fluid" id="createVmButton">
          <button class="offset6" type="button" data-dojo-type="dijit.form.Button">Change Plane</button>
        </div>
      </div>

  
  </div>
<div data-dojo-type="dijit.Dialog" id="cpuDialog" 
     title="Stop Instance" style="width: 500px" >
  <div class="squareContainer setBackground"></div>
 </div>
  
<div data-dojo-type="dijit.Dialog" id="ramDialog" 
     title="Stop Instance" style="width: 500px" >
  <div class="squareContainer setBackground"></div>
</div>

<div data-dojo-type="dijit.Dialog" id="isoDialog" 
     title="Stop Instance" style="width: 500px" >
  <div class="squareContainer "></div>
 </div>
  
<div data-dojo-type="dijit.Dialog" id="ipDialog" 
     title="Stop Instance" style="width: 500px">
  <div class="squareContainer"></div>
 </div>
     
<div data-dojo-type="dijit.Dialog" id="storageDialog" 
      title="Stop Instance" class="span10">
   <button data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.showAddVolume()">Add Disk</button>
   <div id="userStorageGrid">
</div>
   
 </div>
<div data-dojo-type="dijit.Dialog" id="storageAddDialog" 
     title="Add Volume" class="">
  <form id="createDiskForm" data-dojo-type="dijit.form.Form" class="form-horizontal">

    <div class="control-group"> 
    <label for="diskNme" class="control-label">
      <span class="require">*</span>
      Name
    </label>
    <div class="controls">
    <input type="text" 
           data-dojo-type="dijit.form.ValidationTextBox" 
           data-dojo-props="invalidMessage: 'invalid diskName',
           required: 'required', placeHolder: 'Enter diskName', 
           regExp: '[a-zA-Z.]{4,20}', propercase: true" 
           name="diskName" id="">  
    </div>
  </div> 
    <div class="control-group"> 
    <label for="diskNme" class="control-label">
      
      Disk Offering
    </label>
    <div class="controls">
      <select></select>
    </div>
  </div> 
    <div class="control-group" > 
    <label for="diskNme" class="control-label">
      <span class="require">*</span>
      Disk Size
    </label>
    <div class="controls">
     
    </div>
  </div> 
    <div class="row-fluid">
      <button type="button" class="offset5" data-dojo-type="dijit.form.Button">OK</button>
      <button type="button" data-dojo-type="dijit.form.Button">Cancel</button>
      
    </div>
  </form>
   </div>
   
 </div>

     
 <div data-dojo-type="dijit.Dialog" id="bandwidthDialog" 
     title="Stop Instance" style="width: 500px" >
  <div class="squareContainer setBackground"></div>
  </div>
     
 <div data-dojo-type="dijit.Dialog" id="snapShotDialog" 
     title="Stop Instance" style="width: 500px" >
  <div class="squareContainer"></div>
 </div>
     
 <div data-dojo-type="dijit.Dialog" id="securityGroupsDialog" 
     title="Stop Instance" style="width: 500px" >
  <div class="squareContainer"></div>
  
   
  
  
 </div>-->
        <div data-dojo-type="dijit.Dialog" class="full_loader" id="ipLoader" class="span6">
            <div class="row-fluid" style="display: block">
                <img src="images/configLoader.gif" class="span1 spacing"/>
                <p class="message span10"><g:message code='common.processing.donotReload' /></p>
            </div>
          <!--    <div class="row-fluid hide_lable" id="successMessage">
              <img src="images/successMsg.jpg" class="span1 spacing"/>
              <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
              </div>-->
        </div>
        <div data-dojo-type="dijit.Dialog" id="acquireIpDialog" title="<g:message code='common.acquireNewIP' />" class="span4">
            <div class="row-fluid">
                <div class="span2"><img src="images/popup-icons/ip_icon.png" /></div>
                <div class="span10">
                    <div class="span12">
                        <p><g:message code='common.user.ip.confirm' /></p>
                    </div>
                    <div class="span12" style="margin-left: 0">              
                        <div class="span9">
                            <p><g:message code="common.agreeThe"/><a href="#" onClick="CurrentInstanceInfo.showCondition()"> <g:message code="signup.termsAndConditions"/></a></p>
                        </div>
                        <div class="span2">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="vmInfoAgreement" name="agreement">     
                        </div>                    
                    </div>
                    <div class=""> <span class="validation" id="conditionExceptionMsg" style="margin-left: 0"><g:message code="signup.termsCondition.info"/></span></div>
                </div>        
            </div>
            <div class="row-fluid">
                <div class="span8">               
                    <button class="primarybtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.acquireIp()"><g:message code='common.ok' /></button>
                    <button class="cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeAcquireIpDialog()"><g:message code='common.cancel' /></button>
                </div>  
                <div class="span4"><span id="vmInfoIPCost" class="require"></span></div>
            </div>              
        </div>
        <div data-dojo-type="dijit.Dialog" class="full_loader" id="currentVMSnapshotLoader" class="span6">
            <div class="row-fluid" style="display: block">
                <img src="images/configLoader.gif" class="span1 spacing"/>
                <p class="message span10"><g:message code="common.processing.donotReload"/></p>
            </div>
        </div>
        <div data-dojo-type="dijit.Dialog" id="currentVMSnapshotDialog" title="<g:message code='user.vmSnapshot.addTitle' />"  class="customDialgue">
            <div class="span6 dijitDialogueBackground">
                <div class="row-fluid" style="display: none">
                    <div class="value_dollar pull-right"><g:message code='default.valueIn' /><span id="VMSnapshotCurrencyValue"></span></div>
                </div>
                <div class="row-fluid">
                    <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
                    <div class="span10">
                        <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="currentVMSnapshotFormPage">  
                            <div id="currentVMSnapshotForm">
                                <!--<div class="span12"></div>-->
                                <div class="row-fluid">
                                    <div class="control-group field-box span12"> 
                                        <label for="vmSnapshotName" class="control-label">      
                                            <g:message code='common.name' />
                                            <span class="require">*</span>
                                        </label>
                                        <div class="controls">
                                            <input type="text" 
                                            data-dojo-type="dijit.form.ValidationTextBox" 
                                            data-dojo-props="invalidMessage: '<g:message code='common.invalidMessage.name' />',
                                            required: 'required', placeHolder: '<g:message code='common.name.placeHolder' />', 
                                            regExp: '[a-zA-Z.0-9- ]{4,50}'" 
                                            name="Name" id="currentVMSnapshotName">  
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="control-group field-box span12"> 
                                        <label for="currentVMSnapshotDescription" class="control-label">      
                                            <g:message code='common.description' />
                                            <span class="require">*</span>
                                        </label>
                                        <div class="controls">
                                            <input type="text" 
                                            data-dojo-type="dijit.form.ValidationTextBox" 
                                            data-dojo-props="invalidMessage: '<g:message code='common.description.invalid' />',
                                            required: 'required', placeHolder: '<g:message code='common.description.prompt' />', 
                                            regExp: '[a-zA-Z.0-9- ]{4,50}'" 
                                            name="Name" id="currentVMSnapshotDescription">  
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="control-group span12"> 
                                        <label for="vmSnapshotMemory" class="control-label">      
                                            <g:message code="common.snapshotMemory"/>
                                        </label>
                                        <div class="controls">
                                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                            data-dojo-props="checked: false" id="currentVMSnapshotMemory"> 
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">                       

                                    <div class="control-group span12" id="currentVMSnapshotBillingTypeDiv"> 

                                        <label for="" class="control-label"><g:message code='user.createVM.billingType.label' />:</label>
                                        <div class="controls">
                                            <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="currentVMSnapshot-billingType" id="currentVMSnapshot-hourlyBilling" value="hourly" />
                                            <label for="currentVMSnapshot-hourlyBilling"><g:message code='common.hourly' /></label> 
                                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="currentVMSnapshot-billingType"  id="currentVMSnapshot-monthlyBilling" value="monthly" /> 
                                            <label for="currentVMSnapshot-monthlyBilling" class=""><g:message code='common.monthly' /></label> 
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>                        
                </div>
                <div class="row-fluid">
                    <div class="control-group span7"> 
                        <button id="addCurrentVMSnapshotButton" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.createVMSnapshot()"><g:message code='common.ok' /></button>
                        <button id="cancelCurrentVMSshotButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeCreateVMSnapshotDialog()"><g:message code='common.cancel' /></button>
                    </div>
                   <div class="span5">
                       <!--<span class="require">*<g:message code='common.vm.constMsg' /></span>-->
                   </div>

                </div>                                                  
            </div>
        </div>
        <div style="display: none; margin-left:-100px;">
            <div id="changePlantooltipContent">
                <div class="row-fluid">                        
                    <div class="span12">
                        <a style="float: right; color: #FFFFFF" onclick="CurrentInstanceInfo.hideToolTip();">Close</a>
                        <img src="${resource(dir: 'images')}/createvm_server_logo.png" height="151" width="238">
                        <h2 class="alphaStyle"><g:message code="user.createVM"/></h2>
                        <h2 class="alphaStyle"><g:message code="common.customDisk"/></h2>
                        <h2 class="alphaStyle"><g:message code="common.template"/></h2>
                        <h2 class="alphaStyle"><g:message code="common.firewall"/></h2>
                    </div>
                </div>
            </div>
        </div>

        <div data-dojo-type="dijit.Dialog"  id="vmInfoContentDialog" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
            <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
                <div id="vmInfoContentArea">
            </div>                           
            <div class="row-fluid">
                <div class="span10"></div>
                <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.cancelConditionBox()"><g:message code="common.ok"/></button></div>
                </div>
        </div>
        </div>
        <div data-dojo-type="dijit.Dialog" id="vmInfoRebootDialog" title="<g:message code='user.vm.rebootVm.title' />" style="color: black; width: 350px; background: #FFFFFF;">
            <div class="row-fluid">
                <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
                <div class="span10">
                    <div class="span12"><g:message code='user.vm.rebootVm.confirm' /> </div>            
                </div>        
            </div>
            <div class="row-fluid">
                <div class="span12">            
                    <button id="instanceInfoRebootButton" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.rebootInstance()"> <g:message code='common.reboot' />  </button>
                    <button  id="vmInfoRebootCancelButton"  class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.closeReboot()"><g:message code='common.cancel' /></button> 
                </div>
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
                <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="CurrentInstanceInfo.startInstance()" id="">   
                    <g:message code='common.ok' />    
                </button>
                <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.cancelStartStart()()">
                    <g:message code='common.cancel' />
                </button> 
            </div>   
        </div>
        
        <div data-dojo-type="dijit.Dialog" id="vmInfoattachDialog" title="<g:message code='common.storage.detach' />" style="color: black; width: 350px;" class="customDialgue">
            <div class="row-fluid">               
                <div class="span12">
                    <div class="span12"><g:message code='user.storage.detachDisk' /></div>
                    <input type="hidden" id="currentVolumeWidget">
                </div>          
            </div>
            <div class="row-fluid">
                <button type="button"  data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="CurrentVMStorageInfo.vmInfoDetachSnapshot()" id="vmDetachButton">   
                    <g:message code='common.yes' />    
                </button>
                    <img id="detachloader" class="" style="display: none;" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                <button class="cancelbtn"  id="vmInfoSnapCancelButton" data-dojo-type="dijit.form.Button" onclick="CurrentVMStorageInfo.closeDetachdialog()">
                    <g:message code='common.no' />
                </button> 
            </div>   
        </div>
        <div data-dojo-type="dijit.Dialog" id="vmInfoResetDialog" title="<g:message code='common.vm.reset' />" style="color: black; width: 350px;" class="customDialgue">
            <div class="row-fluid">               
                <div class="span12">
                    <div class="span12"><p><g:message code='common.vm.conifrmResetMsg' /></p></div>                   
                </div>          
            </div>
                <div class="row-fluid">
                    <div id="vmDetailResetVMButton" class="span2">
                        <button type="button"   data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="CurrentInstanceInfo.resetVM()">   
                            <g:message code='common.ok' />    
                        </button>
                    </div>                    
                    <img id="vmDetailResetVMImgLoader" class="hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                    <button id="vmDetailResetVMCancelButton" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="CurrentInstanceInfo.cancelResetVMDialog()">
                        <g:message code='common.cancel' />
                    </button> 
                </div>   
        </div>
