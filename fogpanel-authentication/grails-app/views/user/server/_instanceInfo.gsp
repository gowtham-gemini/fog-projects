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
                                    <button title="<g:message code="menu.user.cloud.vmSnapShot"/>" data-dojo-type="dijit.form.Button" onclick ="ViewServer.showCreateVMSnapshotDialog()"><i class="icon-camera"></i></button>
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
            <div class="span4 console-cont">
                <div class="span5 console-editor" id="consoleDiv" style="color: #F5F5F5;">
                    <input id="consoleUrl" type="hidden">
                    <input id="shutOffState" type="hidden">
                    <img id="viewCosoleImg" title='<g:message code="user.vm.startConsole"/>' src="${resource(dir: 'images')}/console_start_ntxt.png" height="80" width="80" onClick="ViewServer.viewConsole();">
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
                                <button title="<g:message code="common.hardReboot"/>" id="reboot" data-dojo-type="dijit.form.Button" onClick="ViewServer.getHardRebootConformation();"><i class="icon-refresh"></i></button>
                            </div>
                            <div class="span3">
                                <button title="<g:message code="common.delete"/>" data-dojo-type="dijit.form.Button" onclick ="ViewServer.getDeleteConformation()"><i class="icon-remove"></i></button>
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
                                <div class="span3"><g:message code="user.createVM.zone.label"/></div>
                                <div class="span8">
                                    <span id="location"></span>
                                    <input type="hidden" id="locationId">
                                </div>
                            </div>
                            <div class="span12">
                                <div class="span3"><g:message code='user.vm.ipAddress' /></div>
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
            <div data-dojo-type="dijit/layout/ContentPane" class="span12" title="<g:message code="menu.user.vm.volume"/>" onshow="ViewServer.attachedVolumeList(), GraphInfo.clearGraphSession()">
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

            </div>
            <div  data-dojo-type="dijit/layout/ContentPane" class="span12" title="<g:message code="menu.user.vm.nics"/>" onshow="NicInfo.populateValues(), GraphInfo.clearGraphSession()">
                <div class="row-fluid">
                    <form id="listNicForm" data-dojo-type="dijit.form.Form">
                        <div class="table-wrapper products-table">       
                            <div class="row-fluid filter-block">
                                <div class="pull-right">
                                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="NicInfo.populateValues()">
                                        <g:message code='common.refresh' />
                                    </button>                                   
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span12">
                                    <div id="userNicList">  
                                    </div>
                                </div>
                                <div class="alert alert-info hide text_gray" id="noNicMessageBox" style="display: none">
                                    <i class="icon-exclamation-sign text_gray"></i> 
                                    <g:message code="user.vm.noNicMessage"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
<!--            <div data-dojo-type="dijit/layout/ContentPane" onshow="GraphInfo.clearGraphSession()" class="span12" title="<g:message code="common.secondaryIp"/>">
                <div class="row-fluid">
                    In Development
                </div>
            </div>-->
            <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.log"/>" onshow="ViewServer.getSreverLog(), GraphInfo.clearGraphSession()">
                <div class="row-fluid">
                    <textarea id="instanceLogInfo" rows="10" style="width: 98%; height: auto;"></textarea>
                </div>
            </div>

            <div id="vmMonitoringTab" data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.monitoring"/>" onshow="InstanceMonitoring.init();">
                <div id="monitoringMsgDiv" class="alert alert-info" style="display: none; margin-top: 50px">
                    <i class="icon-warning-sign"></i> <g:message code="common.monitoring.pageAlert"/>
                </div>
                <div id="monitoringInstanceStopMsgDiv" class="alert alert-info" style="display: none; margin-top: 50px">
                    <i class="icon-warning-sign"></i> <g:message code="common.monitoring.instanceStopPageAlert"/>
                </div>
                                            
                <div class="row-fluid" id="instanceCompleteMonitoringDiv"style="display: none;">
                    <div class="span3">

                        <!--Left Side Panel :: Begins-->
                        <div class="monitor_sidepanel">
                            <!--<div class="mblSwSquareShape" data-dojo-type="dojox/mobile/Switch" style = "margin-top: 50px; margin-bottom: 20px; margin-left: 70px"></div>-->
                            <div class="row-fluid" id="monitoringDeviceFormDiv" style = "margin-top: 20px; display: block">
                                <form id="monitoringDeviceForm" data-dojo-type="dijit.form.Form" class="form-vertical">

                                    <div id="monitoringDeviceContent" class="span12 field-box control-group">
                                        <div></div>
                                        <div class="span12 field-box" id="monitoringIpDiv">
                                            <label for="monitoringIp"  class="control-label">         
                                                <g:message code="common.monitoringIp"/> :<span class="require">*</span></label>
                                            <div class="controls elements span8">
                                                <div id="instanceMonitoringIpList" class="selectOption"></div>
    <!--                                              <div class="form_help_icon" style="top: 0; left: -30px;">
                                                    <i class="icon-info-sign" id="collectorsHelp"></i>
                                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'collectorsHelp', showDelay: 1"><g:message code="common.help.message.collectors"/></div>
                                                </div>-->
                                            </div>
                                        </div>
                                        <div class="span12 field-box" id="collectorsDiv">
                                            <label for="collectors"  class="control-label">         
                                                <g:message code="common.collectors"/> :<span class="require">*</span></label>
                                            <div class="controls elements span8">
                                                <div id="instanceCollectorList" class="selectOption"></div>
    <!--                                              <div class="form_help_icon" style="top: 0; left: -30px;">
                                                    <i class="icon-info-sign" id="collectorsHelp"></i>
                                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'collectorsHelp', showDelay: 1"><g:message code="common.help.message.collectors"/></div>
                                                </div>-->
                                            </div>
                                        </div>
                                        <div class="span11 field-box">
                                            <label for="instanceSnmpPort" class="control-label">              
                                                <g:message code="common.instanceSnmpPort"/> :<span class="require">*</span></label>
                                            <div class="controls updatable span9">
                                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                                id="instanceSnmpPort" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                                placeHolder: '<g:message code="common.instanceSnmpPort"/>', constraints:{min:0,max:10000,pattern:'#'}, timeoutChangeRate: '0.2',
                                                value: 161, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.instanceSnmpPort"/>'">
    <!--                                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                                    <i class="icon-info-sign" id="snmpHelp"></i>
                                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'snmpHelp', showDelay: 1"><g:message code="common.help.message.snmp"/></div>
                                                </div>-->
                                            </div>
                                            <span id="flavorSwapDiskLabel" class="hide_lable"></span>
                                        </div>
                                        <div class="span12 field-box">
                                            <label for="snmpCommunity" class="control-label">          
                                                <g:message code="common.snmpCommunity"/> :<span class="require">*</span></label>
                                            <div class="controls span8">
                                                <input type="text" 
                                                data-dojo-type="dijit.form.ValidationTextBox" 
                                                data-dojo-props="invalidMessage: '<g:message code="common.snmpCommunity.invalid"/>',required: 'true',
                                                placeHolder: '<g:message code="common.snmpCommunity"/>', promptMessage:'<g:message code="common.snmpCommunity"/>'" 
                                                id="snmpCommunity">
    <!--                                            <div class="form_help_icon" style="top: 0; left: 0%;">
                                                    <i class="icon-info-sign" id="snmpCommunityHelp"></i>
                                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'snmpCommunityHelp', showDelay: 1"><g:message code="common.help.message.snmpCommunity"/></div>
                                                </div>-->
                                            <!--</div>-->  
                                            </div>
                                        </div> 
                                        <div class="span12 field-box">
                                            <label for="monitorOsType" class="control-label">              
                                                <g:message code="common.monitorOsType"/>: <span class="require">*</span>
                                            </label>
                                            <div class="controls span8">
                                                <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" 
                                                id="monitorOsType" onchange="" required = 'true'>
                                                <!--<option value="" selected></option>-->
                                                <option value="LINUX">Linux</option>
                                                <option value="WINDOWS">Windows</option>                                                              

                                                </select>
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                    <i class="icon-info-sign" id="monitorAlertTypeHelp"></i>
                                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorAlertTypeHelp', showDelay: 1"><g:message code="common.help.message.monitorAlertType"/></div>
                                                </div>-->
                                            </div>
                                        </div>

                                        <div class="span4 pull-right">
                                            <img class="hide_text" id="monitorDeviceLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                                            <div class="">
                                                <div id="monitorDeviceAddButtonDiv" style="display: block;" class="span3">
                                                    <button id="monitorDeviceAddButton"  data-dojo-type="dijit.form.Button" onclick="InstanceMonitoring.enableDevice()" style = "width:50px" class="okbtn">
                                                        <g:message code="common.enable"/>
                                                    </button>
                                                </div> 
                                                <div class="span4" id="monitorAddButtonLoader" style="display: none"> 
                                                    <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
                                                </div>
    <!--                                                <div id="imageCancelButtonDiv" class="span2">
                                                    <button id="imageCancelButton"  data-dojo-type="dijit.form.Button" onclick="ImageInfo.cancel()" class="cancelbtn">
                                                        <g:message code="common.cancel"/>
                                                    </button>
                                                </div>-->
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="row-fluid" id="instanceDeviceViewDiv" style = "margin-top: 20px; display: none">
                                <div class="span12">
                                    <dl>
                                      <dt><g:message code="common.monitoringIp" /> :</dt>
                                      <dd><span id="monitorIpInfo"  ></span></dd>
                                      <dt><g:message code="common.collector" /> :</dt>
                                      <dd><span id="monitorCollectorInfo"  ></span></dd>
                                      <dt><g:message code="common.instanceSnmpPort" /> :</dt>
                                      <dd><span id="monitorSnmpPortInfo"  ></span></dd>
                                      <dt><g:message code="common.snmpCommunity" /> :</dt>
                                      <dd><span id="monitorSnmpCommunityInfo"  ></span></dd>
                                      <dt><g:message code="common.monitorOsType" /> :</dt>
                                      <dd><span id="monitorOsTypeInfo" ></span></dd>
                                    </dl>
                                </div>
                                <div class="span4 pull-right">
                                    <img class="hide_text" id="monitorDeviceLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                                    <div class="">
                                        <div id="monitorDisbleDeviceBtn" style="display: block;" class="span3">
                                            <button id=""  data-dojo-type="dijit.form.Button" onclick="InstanceMonitoring.diableDeviceAlert()" style = "width:70px" class="okbtn">
                                                <g:message code="common.disable"/>
                                            </button>
                                        </div>  
                                        <div class="span4" id="monitorDisableButtonLoader" style="display: none"> 
                                            <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <!--Left Side Panel :: Ends-->



                    </div>
                    <input type="hidden" id="instanceDeviceId">
                    <input type="hidden" id="deviceOsType">
                    <!--<div id="instanceMonitoringLoaderDiv" style="display: none; margin-top: 150px">
                    </div>-->
                    <div class="row-fluid hide_text" id="monitoringContainerLoader">
                        <div class="span4"><img src="images/vmload.gif" width="150" height="20" class="spacing"/><p><g:message code="common.loading"/></p>   </div><div class="span8"></div><br>
                                             
                    </div>                             
                    <div class="span9 hide_text" id="instanceMonitoringDiv">
                                               
                        <div class="row-fluid">
                            <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.alarm"/>'" >
                                <div class="row-fluid well well-small" style= "width: 790px ">
                                    <form id="instanceAlarmContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-vertical">
                                        <div id="instanceAlarmContentPage">
                                            <div class="row-fluid">

                                                <div class="control-group span3 horizontalcontent" id="instanceAlarmNameListDiv" style="display: block; margin-left: 10px">
                                                    <input type="hidden" id="" value="">
                                                    <label for="alertName" class="control-label">          
                                                        <g:message code="common.alertName"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls elements" style="width: 180px">

                                                        <div id="alarmNameList" class="selectOption">
                                                            <button class="cancelbtn" title= '<g:message code="common.createNewTopic"/>' style="position: absolute;right: 620px;top:90px" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.createTopicDiv()"> + </button>
                                                        </div>
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="alertNameHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'alertNameHelp', showDelay: 1"><g:message code="common.help.message.alertName"/></div>
                                                        </div>-->
                                                    </div>

                                                </div>

                                                <div class="control-group span3 horizontalcontent" id="instanceAlarmNameDiv" style="display: none; margin-left: 10px">
                                                    <input type="hidden" id="" value="">
                                                    <label for="alertName" class="control-label">          
                                                        <g:message code="common.alertName"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls elements" style="width: 200px">
                                                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                                            id="instanceAlertName" data-dojo-props="required: 'true',
                                                            invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.ph.alertName"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                                            promptMessage:'<g:message code="common.alarm.promptMessage"/>'">
                                                        <!--<button class="cancelbtn" style="position: absolute;" type="button" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.createTopic()"> + </button>-->
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="alertNameHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'alertNameHelp', showDelay: 1"><g:message code="common.help.message.alertName"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                                <div class="control-group span3 horizontalcontent">
                                                    <label for="monitorAlertType" class="control-label">              
                                                        <g:message code="common.monitorAlertType"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls updatable" style="width: 200px">
                                                        <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" 
                                                        id="monitorAlertType" onchange="InstanceAlarm.changeAlertType(this)" required = 'true'>
                                                        <!--<option value="" selected></option>-->
                                                        <option value="memory">Memory Utilization </option>
                                                        <option value="cpu">CPU Utilization </option>
                                                        <option value="disk">Disk I/O utilization </option>
                                                        <option value="ipService">Port Monitoring </option>
                                                        <option value="fileSystem">File System Utilization</option>                                                                

                                                        </select>
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorAlertTypeHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorAlertTypeHelp', showDelay: 1"><g:message code="common.help.message.monitorAlertType"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="monitorAlertTypeCpuDiv" style="display: none; margin-left: 10px">
                                                    <label for="monitorAlertTypeCpu" class="control-label">              
                                                        <g:message code="common.monitorAlertTypeCpu"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls updatable" style="width: 200px">
                                                        <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" id="monitorAlertTypeCpu">
                                                            <!--<option value="" selected="selected"></option>-->
                                                            <option value="CPU_LINUX_SYSTEM">System</option>
                                                            <option value="CPU_LINUX_USER">User</option>


                                                        </select>
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorAlertTypeHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorAlertTypeHelp', showDelay: 1"><g:message code="common.help.message.monitorAlertType"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="monitorAlertTypeDiskDiv" style="display: none; margin-left: 10px">
                                                    <label for="monitorAlertTypeDisk" class="control-label">              
                                                        <g:message code="common.monitorAlertTypeDisk"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls updatable" style="width: 200px">
                                                        <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" id="monitorAlertTypeDisk">
                                                            <!--<option value="" selected="selected"></option>-->
                                                            <option value="ioRead">I/O READ </option>
                                                            <option value="ioWrite">I/O WRITE</option>
                                                        </select>
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorAlertTypeHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorAlertTypeHelp', showDelay: 1"><g:message code="common.help.message.monitorAlertType"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="fileSystemPartitionListDiv" style="display: none; margin-left: 10px">
                                                    <input type="hidden" id="" value="">
                                                    <label for="partitions" class="control-label">          
                                                        <g:message code="common.partitions"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls elements">

                                                        <div id="fileSystemPartitionList" class="selectOption"> 
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="monitorAlertFileTypeDiv" style="display: none; margin-left: 10px">
                                                    <label for="monitorAlertFileType" class="control-label">              
                                                        <g:message code="common.monitorAlertFileType"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls updatable" style="width: 200px">
                                                        <select class="customSelectWidth" data-dojo-type="dijit.form.FilteringSelect" id="monitorAlertFileType">
                                                            <!--<option value="" selected="selected"></option>-->
                                                            <option value="read">READ </option>
                                                            <option value="write">WRITE</option>
                                                        </select>
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorAlertTypeHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorAlertTypeHelp', showDelay: 1"><g:message code="common.help.message.monitorAlertType"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div></div>
                                                    <div class="control-group span3 horizontalcontent" style="margin-left: 10px; display: none" id="alarmReciepientDiv" >
                                                    <label for="monitorReciepient" class="control-label">          
                                                        <g:message code="common.monitorReciepient"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls elements" style="width: 200px">
                                                        <input type="text" data-dojo-type="dijit.form.SimpleTextarea" 
                                                        id="alarmReciepient" data-dojo-props="trim: 'true'" onblur = "InstanceMonitoring.validateEmail(this)">
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorReciepientHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorReciepientHelp', showDelay: 1"><g:message code="common.help.message.monitorReciepient"/></div>
                                                        </div>-->
                                                    </div>

                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="alarmThresholdMinValueDiv" style="display: block">
                                                    <label for="alarmThresholdMinValue" class="control-label">              
                                                        <g:message code="common.alarmThresholdMinValue"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls updatable" style="width: 200px">

                                                        <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                                                        id="alarmThresholdMinValue" data-dojo-props="required: 'true',
                                                        invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.pc.alarmThresholdMinValue"/>',
                                                        promptMessage:'<g:message code="common.prompt.alarmThresholdMinValue"/>'">

    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorInPercentageHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorInPercentageHelp', showDelay: 1"><g:message code="common.help.message.monitorInPercentage"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="alarmThresholdMaxValueDiv" style="display: block">
                                                    <label for="monitorInPercentage" class="control-label">              
                                                        <g:message code="common.alarmThresholdMaxValue"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls updatable" style="width: 200px">

                                                            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                                                        id="alarmThresholdMaxValue" data-dojo-props="required: 'true', 
                                                        invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.pc.alarmThresholdMaxValue"/>',
                                                        promptMessage:'<g:message code="common.prompt.alarmThresholdMaxValue"/>'">

    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorInPercentageHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorInPercentageHelp', showDelay: 1"><g:message code="common.help.message.monitorInPercentage"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                                <div class="control-group span3 horizontalcontent" id="monitorAlarmPortDiv" style="display: none">
                                                    <label for="monitorAlarmPort" class="control-label">          
                                                        <g:message code="common.monitorAlarmPort"/>: <span class="require">*</span>
                                                    </label>
                                                    <div class="controls elements" style="width: 200px">
                                                        <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                                        id="monitorAlarmPort" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true', required: 'true',
                                                        placeHolder: '<g:message code="common.monitorAlarmPort"/>', constraints:{min:1,max:65535,pattern:'#'}, promptMessage:'<g:message code="common.alarmThresholdPort"/>'">
    <!--                                                        <div class="form_help_icon" style="top: 0; left: 0%;">
                                                            <i class="icon-info-sign" id="monitorAlarmPortHelp"></i>
                                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'monitorAlarmPortHelp', showDelay: 1"><g:message code="common.help.message.monitorAlarmPort"/></div>
                                                        </div>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row-fluid">
                                                <div class="span8"></div>
                                                <div class="span4 pull-right">
                                                    <img class="hide_text" id="monitorDeviceLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                                                    <div class="" id="alarmButtonDiv" style="display:  block">
                                                        <div id="monitorAlertAddButtonDiv" style="display: block;" class="span3">
                                                            <button id=""  data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.addAlert()" style = "width:50px" class="okbtn">
                                                                <g:message code="common.add"/>
                                                            </button>
                                                        </div> 
                                                        <div id="monitorAlertUpdateButtonDiv" style="display: none;" class="span4">
                                                            <button   id=""  data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.update()" class="okbtn">
                                                                <g:message code="common.update"/>
                                                            </button>
                                                        </div>
                                                        <div id="imageAlertCancelButtonDiv" class="span2">
                                                            <button id=""  data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.cancelAlert()" class="cancelbtn">
                                                                <g:message code="common.cancel"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div class="span4" id="addAlarmButtonLoader" style="display: none"> 
                                                        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
                                                    </div>
                                                </div>

                                            </div>    
                                        </div>

                                    </form>
                                </div>  
                                <div data-dojo-type="dijit.Dialog" class="full_loader" id="instanceMonitoringLoaderDiv" class="span6">
                                    <div class="row-fluid" style="display: block">
                                        <img src="images/configLoader.gif" class="span1 spacing"/>
                                        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
                                        </div>          
                                    </div>
                                  <div class="row-fluid">        
                                    <form id="listMonitorAlertForm" data-dojo-type="dijit.form.Form" style="margin-top: 50px">
                                        <div class="products-table">       
                                            <div class="row-fluid">
                                                <div class="value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/> <span id="monitorAlertCurrencyValue"></span></div>
                                            </div>
    <!--                                                <div class="row-fluid filter-block">
                                                <div class="pull-right">
                                                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.populateAlertValues()">
                                                        <g:message code='common.refresh' />
                                                    </button>
                                                </div>
                                            </div>-->
                                            <div class="row-fluid">
                                                <div id="moniteringAlertList">  
                                                </div>
                                                <div class="alert alert-info hide text_gray" id="noMonitorAlertMessageBox" style="display: none">
                                                    <i class="icon-exclamation-sign text_gray"></i> 
                                                    <g:message code="user.instance.noMonitorAlertMsg"/>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                            </div> 
                            <div data-dojo-type="dijit.Dialog" id="deleteAlarmDialog" title="<g:message code="user.alarm.deleteConfirm"/>" class="span4">
                               <input type="hidden" id="currentAlarmId">
                               <p><g:message code="user.alarm.deleteConfirm.message"/></p>
                               <div class="row-fluid offset1">
                                <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.delete()"><g:message code="common.ok"/></button>
                                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.closeDeleteDialog()"><g:message code="common.cancel"/></button>
                               </div>
                            </div>
                            <div data-dojo-type="dijit.Dialog" id="disableAlarmDialog" title="<g:message code="user.alarm.disableConfirm"/>" class="span4">
                               <p><g:message code="user.alarm.disableConfirm.message"/></p>
                               <div class="row-fluid offset1">
                                <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.disable()"><g:message code="common.ok"/></button>
                                <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="InstanceAlarm.closeDisableDialog()"><g:message code="common.cancel"/></button>
                               </div>
                            </div>
                            </div>
                        </div>
                            <div class="row-fluid">                                
                                <div data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.graphs"/>'" >
                                    <div class="row-fluid"></div>
                                    <div class="row-fluid">
                                        <div class="span12">

                                            <div class="span3" style="position:absolute; right:0;z-index:999999999; margin-top:35px;">
                                                <form data-dojo-type="dijit.form.Form" id="graphFilterForm"class="form-horizontal">
                                                    <div class="span12 field-box">
    <!--                                                        <label for="drangeSelectBox"  class="control-label">         
                                                            <g:message code="common.range"/> :
                                                        </label>-->
                                                        <div class="controls elements">
                                                            <select id="drangeSelectBox" style=" border: 1px solid rgb(207, 207, 207) ! important;" data-dojo-type="dijit/form/FilteringSelect" onchange="GraphInfo.chnageGraphRange(this)">
                                                                <option value="129600"><g:message code="common.hourly"/></option>
                                                                <option value="864000"><g:message code="common.daily"/></option>
                                                                <option value="3628800"><g:message code="common.weekly"/></option>
                                                                <option value="41472000"><g:message code="common.monthly"/></option>
                                                                <option value="62208000"><g:message code="common.yearly"/></option>                                                        
                                                            </select>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>                                                                                                                                       
                                    <div class="row-fluid">
                                        <div id="graphMonitoringTab" data-dojo-type="dijit/layout/TabContainer" class="row-fluid" style="overflow: visible; display: block; min-height: 340px;" tabStrip="true">
                                            <div id="availablity" onshow="GraphInfo.showGraph('availablity'), GraphInfo.updateGraph('availablity')" style="overflow: hidden;" data-dojo-type="dijit/layout/ContentPane" class="span12" title="<g:message code="common.systemUptime"/>" selected="true">
                                                <div class="row-fluid" id="monitoringAvailGraphImage" onclick="GraphInfo.zoomGraph()" style="cursor: move;">
                                                </div>                                                
                                            </div>
                                            <div id="cpu" data-dojo-type="dijit/layout/ContentPane" onshow="GraphInfo.showGraph('cpu'), GraphInfo.updateGraph('cpu')" style="overflow: hidden;" class="span12" title="<g:message code="common.cpuUtilization"/>">                                                
                                                 <div class="row-fluid" id="monitoringCpuGraphImage" onclick="GraphInfo.zoomGraph()"  style="cursor: move;">                                                        
                                                 </div>
                                            </div>
                                            <div id="memory" data-dojo-type="dijit/layout/ContentPane" onshow="GraphInfo.showGraph('memory'), GraphInfo.updateGraph('memory')" style="overflow: hidden;" class="span12" title="<g:message code="common.memoryUtilization"/>">
                                                <div class="row-fluid" id="monitoringMemoryGraphImage" onclick="GraphInfo.zoomGraph()"  style="cursor: move;">                                                   
                                                </div>
                                            </div>
                                            <div id="io" onshow="GraphInfo.showGraph('io'), GraphInfo.updateGraph('io')" data-dojo-type="dijit/layout/ContentPane" style="overflow: hidden;" class="span12" title="<g:message code="common.diskIO"/>">
                                                <div class="row-fluid" id="monitoringDiskIOGraphImage" onclick="GraphInfo.zoomGraph()"  style="cursor: move;">                                                   
                                                </div>
                                            </div>
                                            <div  id="throughput" onshow="GraphInfo.showGraph('throughput'), GraphInfo.updateGraph('throughput')" data-dojo-type="dijit/layout/ContentPane" style="overflow: hidden;" class="span12" title="<g:message code="common.networkInboundOrOutbound"/>">
                                                <div class="row-fluid" onclick="GraphInfo.zoomGraph()" id="throughputGraphDiv"  style="cursor: move;"></div>
                                            </div>                                            
                                        </div>                                        
                                    </div>
                                </div>
                           </div>
                            <div class="row-fluid">
                                <div class="span12" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.portMonitoring"/>'" onshow="IPServices.populateValues()" >
                                    <div class="row-fluid"> <div class="span12"></div> </div>
                                    <div class="row-fluid"> <div class="span12"></div> </div>
                                    <div class="row-fluid"> <div class="span12"></div> </div>

                                    <div class="row-fluid">
                                        <div class="span12" id="ipServiceGridNode"></div>
                                    </div>                                    
                                    <div class="row-fluid">
                                        <div class="alert alert-info hide" id="noIPListMessageBox" style="display: none">
                                            <i class="icon-exclamation-sign"></i> 
                                            <g:message code="common.noDevicePortMsg"/>
                                        </div>
                                    </div>
                                </div>                                
                           </div>
    <!--                           <div class="row-fluid">
                             <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: 'pan3'" >
                                 In Dev
                             </div>
                        </div>-->
                        </div>
                        </div>
                    </div>
                </div>
<!--              <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.console"/>" onshow="ViewServer.getSreverLog()">
                  <div class="row-fluid">
                        <div class="alert alert-info overflowLabel text_gray">
                            <i class="icon-exclamation-sign text_gray"></i> 
                            if console is not responding to keyboard input: click the grey status bar below&nbsp;&nbsp;<a target="_blank" id="consoleLink">Click here to show only console in new window</a>
                        </div>
                    </div>  
                    <div class="row-fluid">
                        <iframe class="span12" height="400px" name="consoleIframe" id="consoleIframe"></iframe>
                    </div>
                </div>-->
            </div>
        </div>
    </div>
<!--</div>-->    

    <div data-dojo-type="dijit.Dialog" id="monitoringDisableAlert" title="<g:message code='user.monitoringDisableAlert.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
        <div class="row-fluid">
    <!--        <div class="span2"><img src='images/vm_detach_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><g:message code='user.monitoringDisableAlert.message' />  </div>            
            </div>        
            <div class="row-fluid">
                <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="InstanceMonitoring.diableDevice()">
                    <g:message code='common.disable' />   
                </button>
                <button data-dojo-type="dijit.form.Button" onclick="InstanceMonitoring.cancelDiableDevice()" class="cancelbtn">
                    <g:message code='common.cancel' />
                </button> 
            </div>
        </div>                        
    </div>

    <div data-dojo-type="dijit.Dialog" class="full_loader" id="instaceInfoLoader" class="span6">
        <div class="row-fluid" style="display: block">
            <img src="images/configLoader.gif" class="span1 spacing"/>
            <p class="message span10"><g:message code="common.processing.donotReload"/></p>
        </div>
    </div>
    <!-- VM snapshot dialog for creation-->
    <div data-dojo-type="dijit.Dialog" id="createVMSnapshotDialog" title="<g:message code='user.vmSnapshot.addTitle' />"  class="customDialgue">
        <div class="span6 dijitDialogueBackground">
            <div class="row-fluid" style="display: none">
                <div class="value_dollar pull-right"><g:message code='default.valueIn' /><span id="VMSnapshotCurrencyValue"></span></div>
            </div>
            <div class="row-fluid">
                <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
                <div class="span10">
                    <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="currentVMSnapshotForm">  
                        <div id="createVMSnapshotFormPage">
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
                                        <div class="form_help_icon" style="top: 0; left: -15px;">
                                            <i class="icon-info-sign" id="instanceSnapshotNameHelp"></i>
                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'instanceSnapshotNameHelp', showDelay: 1"><g:message code="common.help.message.VMSnapshotName"/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>                        
            </div>
            <div class="row-fluid">
                <div class="control-group span7"> 
                    <button id="createVMSnapshotButton" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="ViewServer.createVMSnapshot()"><g:message code='common.ok' /></button>
                    <button id="cancelCreateVMSshotButton" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeCreateVMSnapshotDialog()"><g:message code='common.cancel' /></button>
                </div>
                <div class="span5">
                    <!--<span class="require">*<g:message code='common.vm.constMsg' /></span>-->
                </div>

            </div>                                                  
        </div>
    </div>
    <!-- Loader-->
    <div data-dojo-type="dijit.Dialog"  id="vmSnapshotLoader" class="customDialgue span6">
        <div class="row-fluid">
            <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
            <div class="span9">
                <div class="span12"><p><g:message code='common.serverTaskInfo1' /></p></div>
                <div class="span12" style="margin-left: 0"><p><g:message code='common.serverTaskInfo2' /></p></div>              
            </div>          
        </div>
        <div class="row-fluid">        
            <a class="btn-flat default" onclick="ViewServer.gotoInstances()"><g:message code='common.gotoVMViewPage' /></a>
    <!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
        </div> 
    </div>
    <div data-dojo-type="dijit.Dialog" id="attachVolumeDialog" title="<g:message code="common.instance.volumeAttach"/>"  class="customDialgue">
        <div class="span5 dijitDialogueBackground">
            <div class="row-fluid">
                <div class="span2"><img src="images/vm_attach_icon.png"></div>
                <div class="span10">
                    <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="attachVolumeForm"> 
                        <input type="hidden" id="attachmentServerId"> 
                        <div id="attachVolumeFormPage">
                            <div class="control-group">
                                <label for="volumeList" class="control-label"><g:message code="common.volume"/> :
                                    <span class="require">*</span>
                                </label>
                                <div class="controls">
                                    <div id="" class="selectOption">
                                        <div id="volumeListForAttachment" ></div> 
                                        <div class="form_help_icon" style="top: -25px; left: -15px;">
                                            <i class="icon-info-sign" id="volumeListForAttachmentHelp"></i>
                                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeListForAttachmentHelp', showDelay: 1"><g:message code="common.help.message.volumeListForAttachment"/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>      
            </div>
            <div>
                <div class="control-group span2 pull-right"> 
                    <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ViewServer.attachVolume()"><g:message code="common.ok"/></button>
                    <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeAttachVolumeDialog()"><g:message code="common.cancel"/></button>            </div>
               <!--<div class="span5"></div>-->
            </div>
        </div>
    </div>

    <div data-dojo-type="dijit.Dialog" style="display: none" class="customDialgue span6" id="AttachedVolumeActionLoader" >
        <div class="row-fluid">
            <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
            <div class="span9">
                <div class="span12"><p><g:message code='common.volumeInfoInfoOne' /></p></div>
                <div class="span12" style="margin-left: 0"><p><g:message code='common.volumeInfoInfoTwo' /></p></div>              
            </div>          
        </div>
        <div class="row-fluid">        
            <a class="btn-flat default" onclick="ViewServer.gotoAttachmentList()"><g:message code='common.gotoAttachedVolumeList' /></a>    
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="detachVolumeAlert" title="<g:message code='user.volume.detachConfirm.title' />" class="customDialgue" style="display: none;color: black; width: 350px;">
        <div class="row-fluid">
            <div class="span2"><img src='images/vm_detach_icon.png'/></div>
            <div class="span10">
                <div class="span12"><g:message code='user.volume.detachConfirm.message' />  </div>            
            </div>        
            <div class="row-fluid">
                <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ViewServer.detachVolume()">
                    <g:message code='common.button.detach' />   
                </button>
                <button data-dojo-type="dijit.form.Button" onclick="ViewServer.closeDetachVolumeAlertDialog()" class="cancelbtn">
                    <g:message code='common.cancel' />
                </button> 
            </div>
        </div>                        
    </div>

<div data-dojo-type="dijit.Dialog" id="graphZoomDialogBox" title="<g:message code="common.graph"/>" class="span12">                   
    <div class="row-fluid" id="zoomGraphImage">            
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

<div data-dojo-type="dijit.Dialog" id="vmInfoHardRebootDialog" title="<g:message code='user.instance.hardRebootInstance.confirmTitle' />" style="color: black; width: 350px; background: #FFFFFF;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_reboot_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.instance.hardRebootInstance.confirmMessage' /> </div>            
        </div>        
    </div>            
        <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ViewServer.hardRebootInstance()"> <g:message code='common.hardReboot' /> </button>
            <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeHardRebootDialog()"> <g:message code='common.cancel' /></button> 
        </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="vmInfoDeleteDialog" title="<g:message code='user.vm.deleteVm.title' />" class="customDialgue" style="color: black; width: 350px;">
    <div class="row-fluid">
        <div class="span2"><img src='images/popup-icons/vm_delete_icon.png'/></div>
        <div class="span10">
            <div class="span12"><g:message code='user.vm.deleteVm.confirm' />  </div>            
        </div>        
         <div class="row-fluid">
            <button type="button" class="primarybtn" data-dojo-type= "dijit.form.Button" onclick="ViewServer.deleteInstance()" id="vmInfoDeleteButton">
                <g:message code='default.button.delete.common' />   
            </button>
            <button id="DeletevmInfoCancelButton" data-dojo-type="dijit.form.Button" onclick="ViewServer.closeDeleteDialog()" class="cancelbtn">
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
