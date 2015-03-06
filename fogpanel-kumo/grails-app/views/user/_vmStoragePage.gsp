<div class="row-fluid" style="display: none;">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/" class="overflowLabel"><g:message code="menu.user.cloud"/></a></li>
            <li>/<li>
            <li class="overflowLabel"><a href="#/user/cloud/userInstancePage"> <g:message code="menu.user.cloud.instance"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.vm.storage"/></li>
          </ul>
    </div>
</div>
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12">
        <li>
            <a id="vmInfoDetailTag"><g:message code="menu.user.vm.detail"/></a>
        </li>
        <li class="active">
             <a id="vmInfoStorageTag"><g:message code="menu.user.vm.storage"/></a>
        </li>
        <li>
            <a id="vmInfoNicTag"><g:message code="menu.user.vm.nics"/></a>
        </li>  
        <li id="aquireIPMenuItem">
            <a id="vmInfoAquireIPTag"><g:message code="common.secondaryIp"/></a>
        </li>
    </ul>
</div>
<input type="hidden" id="rootDiskId">
<input type="hidden" id="computTag">
<input type="hidden" id="computReferId">
<input type="hidden" id="offerType">
<div class="row-fluid">
    <div class="span6"></div>
    <div class="span6">
        <div class="span12">
            <div class="control-group field-box span9">
                <div id="addVolumeWarningMsg" class=" hide_text">
                <label class="control-label">              
                    <g:message code="common.warning"/>:          
                </label>
                <div class="controls">
                    <span class="require"><g:message code="common.vmSnapshotWarning"/></span>
                </div>
                </div>
            </div>
            <div class="span3" id="addVolume">
                <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="CurrentVMStorageInfo.showAddVolume();"><g:message code='user.disk.addVolume' /></button>
            </div>
        </div>        
    </div>    
</div>
<div class="span10">
    <div id="volumeListWidget"></div>
</div>
<div data-dojo-type="dijit.Dialog" id="addVolumeDialog" title="<g:message code='user.disk.addNewDisk' />"  style="background: #FFFEFF; height: auto;">
    <div class="span8">
        <form class="form-horizontal" id="addVolumeForm" data-dojo-type="dijit.form.Form">
            <div class="row-fluid"></div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="span2"><img src='images/add_disk_icon.png'/></div>
                    <div class="span6">
                        <div class="span12"></div>                    
                        <div class="control-group span12"> 
                            <label for="vmVolumeName" class="control-label">      
                                <g:message code='common.name' />
                                <span class="require">*</span>
                            </label>
                                <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: 'invalid diskName',
                                required: 'required', placeHolder: '', regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                name="diskName" id="vmVolumeName">  
                                </div>
                        </div>
                        <div class="control-group span12">
                            <label for="vmVolumeName" class="control-label">     
                                <g:message code='common.type' />
                            </label>
                            <div class="controls">
                                <div class="span3">
                                    <span> <g:message code='common.plans' /></span>
                                </div>
                                <div class="span4"><div id="diskCustomSwitch" data-dojo-type="dojox.mobile.Switch" class="mblSwRoundShape1" value="off"  data-dojo-props="leftLabel: '', rightLabel: ''"></div></div>
                                <div class="span3"><span><g:message code='common.custom' /></span></div>

                            </div>
                        </div>
                        <div class="control-group span12" id="diskOfferList">
                            <input type="hidden" id="volumeDiskId">
                            <label for="volumeDiskSlider" class="control-label"><g:message code='common.offer' />:</label>
                            <div class="controls">
                                <div id="volumeDiskList"></div>  
                                <img id="currentVMStorageLoader" style="display: none; width: 10%" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="100">
                                <span id="volumeDiskName"></span></br>
                                <span id="volumeDiskSize"></span>
                                <span id="volumeDiskCost" class="unitCost"></span>  
                                <span id="cusDiskCost" style="display: none"></span>
                            </div>
                        </div>
                        <div class="control-group span12 non_updatable" id="vmDiskSizeContent">
                            <label for="instanceDiskSize" class="control-label">  
                                <g:message code='user.createVM.diskSize.label' />:
                            </label>
                            <div class="controls">                            
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                data-dojo-props="constraints:{min:1,max:100,pattern:'#'},
                                timeoutChangeRate: '0.2', invalidMessage: 'invalid size'"
                                id="vmStorageDiskSizeSpinner" onchange="CurrentVMStorageInfo.setVMDiskSliderSize(this)" onKeyDown="CurrentVMStorageInfo.setVMDiskSliderSize(this)" 
                                onKeyPress="CurrentVMStorageInfo.setVMDiskSliderSize(this)"
                                onMouseDown="CurrentVMStorageInfo.setVMDiskSliderSize(this)" style="width: 70px!important;">      
                            </div>
                        </div>                                                       
                        <div class="control-group span12" id="vm-disk-billingTypeDiv">
                            <label for="vmVolumeName" class="control-label">     
                                <g:message code='common.billing' /> 
                            </label>
                            <div class="controls">
                                <div class="span3">
                                    <span><g:message code='common.hourly' /></span>
                                </div>
                                <div class="span4"><div id="diskBillingTypeSwitch" data-dojo-type="dojox.mobile.Switch" class="mblSwRoundShape1" value="off"  data-dojo-props="leftLabel: '', rightLabel: ''"></div></div>
                                <div class="span3"><span><g:message code='common.monthly' /></span></div>                            
                            </div>
                        </div>                                
                    </div>
                    <div class="span4 with-sidebar">
                        <div class="row-fluid">
                            <div class="span6"><a onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a></div>
                            <div class="span6 value_dollar create_vm_cost"><g:message code='default.valueIn' /> <span id="instanceInfoCurrencyValue"></span></div>
                        </div>
                            <div class="span12"><h3 class="header_space"><g:message code='common.diskInfo' /></h3></div>
                            <div class="span12 control-group" id="vmDiskInfo">
                                <label for="" class="control-label"><g:message code='common.size' />:</label>
                                <div class="controls">
                                    <span id="vmCurrentDiskSize"></span>
                                </div>
                            </div>                    
                            <div class="span12 control-group">
                                <label for="" class="control-label"><g:message code='common.diskIO' />:</label>
                                <div class="controls">
                                    <span id="addDiskIO"></span>
                                </div>
                            </div>
                            <div class="span12 control-group">
                                <label for="" class="control-label"><g:message code='common.price' />:</label>
                                <div class="controls">
                                    <span id="vmDiskCost"></span><span id="vmDetailStorageUnit"> /<g:message code='common.gb' />/<g:message code='common.hr' /></span>  
                                </div>
                            </div>
                            <div class="control-group span12" id="vmDiskMonthlyInfo" style="display: none;">
                                <label for="instanceDiskSize" class="control-label">  
                                    <g:message code='common.monthlyCost' />
                                </label>
                                <div class="controls">
                                    <span id="vmDiskMonthlyCost"></span>       
                                </div>
                            </div>                                
                    </div>
                </div>
            </div>                                    
                <span style="display: none" class="offset1 require" id="vmDiskErrorMessage"><g:message code="commom.disk.noDiskOrRequired"/></span>
                <div class="control-group offset2"> 
                    <button id="addVolumeButton" class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentVMStorageInfo.createVolume()"><g:message code='common.add' /></button>
                    <img id="addVolumeLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
                    <button id="cancelVolume" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="CurrentVMStorageInfo.closeAddVolumeDialog()"><g:message code='common.cancel' /></button>
                </div>
        </form>
    </div> 
</div>  
        
        <div data-dojo-type="dijit.Dialog" id="vmCreateSnapshot" class="span6" title="<g:message code='user.snapshot.confirmation' />">
            <input type="hidden" id="currentWidgetId">
            <p><g:message code='user.snapsot.confirm' /></p>
            <div class="row-fluid" id="snapNameDiv">
                <div class="form-horizontal overflow-text">
                    <div class="row-fluid">
                        <div class="control-group">
                            <label class="control-label" for="snapshotStorageName"><g:message code='common.name' /><span class="require">*</span>:</label>
                            <div class="controls ">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code='common.name.invalid' />', required: true, placeHolder: '<g:message code='common.name.placeHolder' />', 
                                       regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" name="snapName" id="vmSnapshotName">  
                            </div>
                        </div>
                        <div class="control-group" id="vm-add-snap-billingTypeTr">
                            <label for="" class="control-label"><g:message code='user.createVM.billingType.label' />:</label>
                            <div class="controls ">
                                <div id="vm-add-snap-billingTypeDiv">
                                    <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="vm-add-snap-billingType" id="vm-add-snap-hourlyBilling" value="hourly"/>
                                    <label class="control-label" for="vm-add-snap-hourlyBilling"><g:message code='common.hourly ' /></label> 
                                    <input type="radio" data-dojo-type="dijit.form.RadioButton" name="vm-add-snap-billingType"  id="vm-add-snap-monthlyBilling" value="monthly"/> 
                                    <label class="control-label" for="vm-add-snap-monthlyBilling" class=""><g:message code='common.monthly' /></label> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid offset1">
                <button type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick ="CurrentVMStorageInfo.takeSnapShot()"><g:message code='common.yes' /></button>
                <button type="button" class="cancelbtn" data-dojo-type = "dijit.form.Button" onclick="CurrentVMStorageInfo.cancelSanpshot()"><g:message code='common.no' /></button>
            </div>
        </div>