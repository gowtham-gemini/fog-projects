<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.cloud.instance"/></li>
        </ul>
    </div>
</div>
<div class="row">
    <div id="main-stats">
        <div class="row stats-row">
            <div class="col-md-3 stat">
                <div class="data col-md-12">
                     <span  class="number col-md-12" id="adminTotalVM"></span>
                    <g:message code="stat.vm.totoalVMs"/>
                </div>

            </div>
            <div class="col-md-3 stat">
                <div class="data">
                     <span  class="number col-md-12" id="adminRunningVM"></span>
                    <g:message code="stat.vm.runningVMs"/>
                </div>

            </div>
            <div class="col-md-3 stat">
                <div class="data">
                     <span  class="number col-md-12" id="adminStoppedVM"></span>
                    <g:message code="stat.vm.stoppedVMs"/>
                </div>            
            </div>
            <div class="col-md-3 stat last">
                <div class="customData col-md-12">
                    <div id="adminZoneList"></div>
                </div>
                <div class="row">
                    <form data-dojo-type="dijit.form.Form" id="adminVmActionForm" class="customForm">
                        <div class="col-md-3">
                            <input type="radio" name="vmInfo" id="allVmRadio" 
                            data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="InstancesInfo.showInstance(this)">          
                            <label for="allVmRadio"><g:message code="common.all"/></label>      

                        </div>
                        <div class="col-md-4">
                            <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                            id="runningVmRadio" value="Running" onclick="InstancesInfo.showInstance(this)">           
                            <label for="runningVmRadio"><g:message code="common.vmStatus.running"/></label> 


                        </div>
                        <div class="col-md-4">  
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
<div class="row">   
    <ul class="nav nav-tabs col-md-12 customNav">      
        <li class="active">
            <a href="#/admin/infrastructure/instance/"><g:message code="menu.user.cloud.instance"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/volumes/"><g:message code="menu.volumes"/></a>
        </li>
<!--        <li>
            <a href="#/admin/infrastructure/snapShot/"><g:message code="menu.user.cloud.snapShot"/></a>
        </li>    
        <li>
            <a href="#/admin/infrastructure/network/"><g:message code="common.network"/></a>
        </li>-->
    </ul> 
</div>
<p>In development</p>
<!--<div  class="new-user">
    <div class="row header">
      <h4>Instances</h4>    
    </div>   
    <div class="row">
        <div id="adminInstanceInfo"></div>
    </div>
    <div class="alert alert-info hide" id="noVMMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.noInstanceAdmin"/>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="migrateVMDialog" title="<g:message code="common.vm.migrateInstance"/>" class="customDialgue">
    <div class="col-md-6 dijitDialogueBackground">
        <p id="hostInfo"></p>
        <input type="hidden" id="vmID">
        <div id="hostListLoadingDiv"></div>  
        <div class="form-horizontal" id="hostListWidDiv">
            <div class="control-group"> 
                <label for="" class="control-label">     
                     <span  id="lableValue"></span>
                     <span  class="require">*</span>
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
<div data-dojo-type="dijit.Dialog" class="full_loader" id="migrateVMLoader" class="col-md-6">
    <div class="row" id="processPaymentMessage">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row">
        <p class="message col-md-12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>-->