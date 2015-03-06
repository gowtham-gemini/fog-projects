<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li> 
            <li>/<li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="stat.snapshot"/></li>
        </ul>
    </div>
</div>
<!--Cloud stack bug, so this code has hidden temporary, coming in next version-->
<div class="row-fluid" style="display: none">
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminManualSnap">0</span>
                    <g:message code="stat.storage.manualSnapshot"/>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminAutoSnap">0</span>

                     <g:message code="stat.storage.automatedSnapshot"/>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminJobsSnap">0</span>
                    <g:message code="stat.jobs"/>
                </div>            
            </div>
            <div class="span3 stat last">
                <div class="customData span12">
                    <div id="adminSnapZoneList"></div>
                </div>
                <div class="row-fluid">
                    <form data-dojo-type="dijit.form.Form" id="adminSnapActionForm" class="customForm">
                        <div class="span4">
                            <input type="radio" name="snapInfo" id="manualSanpRadio" 
                            data-dojo-type="dijit.form.RadioButton"  checked value="Manual" onclick="SnapshotInfo.showVolumes(this)">          
                            <label for="manualSanpRadio"><g:message code="common.manual"/></label>      

                        </div>
                        <div class="span5">
                            <input type="radio" name="snapInfo" data-dojo-type="dijit.form.RadioButton"  
                            id="autoSnapRadio" value="auto" onclick="SnapshotInfo.showVolumes(this)">           
                            <label for="autoSnapRadio"><g:message code="common.automated"/></label> 


                        </div>
                        <div class="span3">  
                            <input type="radio" name="snapInfo" data-dojo-type="dijit.form.RadioButton"  
                            id="jobSnapRadio" value="Job" onclick="SnapshotInfo.showVolumes(this)"> 
                            <label for="jobSnapRadio"><g:message code="stat.job"/></label>           
                        </div> 
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--End Of the Command-->
<div class="row-fluid">   
    <ul class="nav nav-tabs span12 customNav">
       <li>
            <a href="#/admin/infrastructure/instance/"><g:message code="menu.user.cloud.instance"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/storage/"><g:message code="menu.user.cloud.storage"/></a>
        </li>
        <li class="active">
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
<div class="new-user">
    <div class="row-fluid header">
  <!--    <h4>SnapShot</h4>    -->
    </div>   
    <div class="row-fluid">
        <div id="adminSnapshotInfo"></div>
        <div class="alert alert-info hide" id="noSnapshotMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.noSnapshotAdmin"/>
        </div>
    </div>
</div>


