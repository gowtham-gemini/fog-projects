<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud" onclick="CloudConfig.openCloud()"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.cloud.instance"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span3 stat">
                <div class="data span12">
                    <span class="number span12" id="adminTotalVM"></span>
                    <g:message code="stat.vm.totoalVMs"/>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminRunningVM"></span>
                    <g:message code="stat.vm.runningVMs"/>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminStoppedVM"></span>
                    <g:message code="stat.vm.stoppedVMs"/>
                </div>            
            </div>
            <div class="span3 stat last">
                <div class="customData span12">
                    <div id="adminZoneList"></div>
                </div>
                <div class="row-fluid">
                    <form data-dojo-type="dijit.form.Form" id="adminVmActionForm" class="customForm">
                        <div class="span3">
                            <input type="radio" name="vmInfo" id="allVmRadio" 
                            data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="InstancesInfo.showInstance(this)">          
                            <label for="allVmRadio"><g:message code="common.all"/></label>      

                        </div>
                        <div class="span4">
                            <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                            id="runningVmRadio" value="Running" onclick="InstancesInfo.showInstance(this)">           
                            <label for="runningVmRadio"><g:message code="common.vmStatus.running"/></label> 


                        </div>
                        <div class="span4">  
                            <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                            id="stoppedVmRadio" value="Stopped" onclick="InstancesInfo.showInstance(this)"> 
                            <label for="stoppedVmRadio"><g:message code="common.vmStatus.stopped"/></label>           
                        </div> 
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">   
    <ul class="nav nav-tabs span12 customNav">
        <li class="active">
            <a href="#/admin/infrastructure/instance/"><g:message code="menu.user.cloud.instance"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/storage/"><g:message code="menu.user.cloud.storage"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/snapShot/"><g:message code="menu.user.cloud.snapShot"/></a>
        </li>    

        <li>
            <a href="#/admin/infrastructure/vmBandwidth"><g:message code="common.bandwidth"/></a>
        </li>  
        <li>
            <a href="#/admin/infrastructure/ipInfo"><g:message code="common.ipStatics"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/network/"><g:message code="common.network"/></a>
        </li>
    </ul>
</div>
<div  class="new-user">
    <div class="row-fluid header">
      <!--<h4>Instances</h4>-->    
    </div>   
    <div class="row-fluid">
        <div id="adminInstanceInfo"></div>
    </div>
    <div class="alert alert-info hide" id="noVMMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.noInstanceAdmin"/>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="migrateVMDialog" title="<g:message code="common.vm.migrateInstance"/>" class="customDialgue">
    <div class="span6 dijitDialogueBackground">
        <p id="hostInfo"></p>
        <input type="hidden" id="vmID">
        <div id="hostListLoadingDiv"></div>  
        <div class="form-horizontal" id="hostListWidDiv">
            <div class="control-group"> 
                <label for="" class="control-label">     
                    <span id="lableValue"></span>
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <div id="hostListDiv">
                    </div> 
                </div>
            </div>
            <div class="control-group offset2"> 
                <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="InstancesInfo.migrateVM()"><g:message code="common.ok"/></button>
                <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="InstancesInfo.closeMigrateVMDialog()"><g:message code="common.cancel"/></button>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="migrateVMLoader" class="span6">
    <div class="row-fluid" id="processPaymentMessage">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

