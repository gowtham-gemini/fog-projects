<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li> 
            <li>/<li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="menu.admin.services.storage"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminAllocatedVolumes"></span>
                    <g:message code="stat.storage.total"/>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminAttachedVolumes"></span>
                    <g:message code="stat.storage.attached"/>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <span class="number span12" id="adminDetachedVolumes"></span>
                   <g:message code="stat.storage.detached"/>
                </div>            
            </div>
            <div class="span3 stat last">
                <div class="customData span12">
                    <div id="adminStorageList"></div>
                </div>
                <div class="row-fluid">
                    <form data-dojo-type="dijit.form.Form" id="adminStorageActionForm" class="customForm">
                        <div class="span3">
                            <input type="radio" name="volumeInfo" id="allVolumeRadio" 
                            data-dojo-type="dijit.form.RadioButton"  checked value="All" onclick="StorageInfo.showVolumes(this)">          
                            <label for="allVolumeRadio"><g:message code="common.all"/></label>      

                        </div>
                        <div class="span4">
                            <input type="radio" name="volumeInfo" data-dojo-type="dijit.form.RadioButton"  
                            id="runningVolumeRadio" value="Attached" onclick="StorageInfo.showVolumes(this)">           
                            <label for="runningVolumeRadio"><g:message code="common.storage.attached"/></label> 


                        </div>
                        <div class="span5">  
                            <input type="radio" name="volumeInfo" data-dojo-type="dijit.form.RadioButton"  
                            id="stoppedVolumeRadio" value="Detached" onclick="StorageInfo.showVolumes(this)"> 
                            <label for="stoppedVolumeRadio"><g:message code="common.storage.detached"/></label>           
                        </div> 
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">   
    <ul class="nav nav-tabs span12 customNav">
        <li>
            <a href="#/admin/infrastructure/instance/"><g:message code="menu.user.cloud.instance"/></a>
        </li>
        <li class="active">
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
<div class="new-user">
    <div class="row-fluid header">
      <!--<h4>Storage</h4>-->    
    </div>   
    <div class="row-fluid">
        <div id="adminVolumeInfo"></div>
        <div class="alert alert-info hide" id="noStorageMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.noStorageAdmin"/>
        </div>
    </div>
</div>

