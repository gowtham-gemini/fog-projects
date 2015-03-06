<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services" ><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a href="#/admin/volumeType/list" ><g:message code="common.volumeType"/></a></li> 
            <li>/</li>
            <li id="volumeTypeBreadcrumName"><g:message code="common.add"/></li>
        </ul>
    </div>
</div>

<div class="row-fluid">
    <div class="span12" id="">
        <input type="hidden" id="currentVolumeTypeId">
        <div class="row-fluid header">
            <h3 id="addFlavorhead"></h3>   
            <div class="span2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
        </div>
        <form id="adminVolumeTypeContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="flavorContent">
                <div class="row-fluid">
                    <div class="span12">        
                        <div class="control-group span4 horizontalcontent">
                            <input type="hidden" id="currentVolumeTypeId" name="currentFlavorId" value="">
                            <label for="flavorName" class="control-label">          
                                <g:message code="common.name"/> : <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="volumeTypeName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.name.placeHolder"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorNameHelp', showDelay: 1"><g:message code="common.help.message.volumeTypeName"/></div>
                                </div>    
                            </div>
                        </div>                         
                    </div>                                                            
                    <div class="row-fluid">     
                        <h4 ><g:message code="common.flavor.pricingInfo"/></h4>
                        <hr class="text_gray" style="margin-top: 15px">                       
                        <div>
                            <div id="volumeTypePriceHeadLabel">
                                <div class="row-fluid">
                                    <div class="span2"><h5><g:message code="common.zoneName"/></h5></div>
                                    <div class="span2"><h5><g:message code="common.volumeType.runningCostByMonth"/></h5></div>
                                    <div class="span2"><h5><g:message code="common.volumeType.runningCostByHr"/></h5></div>                                    
                                </div>
                            </div> 
                        </div></br>
                        <div class="row-fluid">
                            <div id="currentVolumeTypeZoneCost" style="margin-top: 0px"></div>
                        </div>
                        <div class="row-fluid hide_text" id="volumeTypeZoneLoader">
                            <img   src="${resource(dir: 'images')}/vmload.gif" alt="reset" height="40" width="150">                                     
                            <p><g:message code="common.message.loading" /></p>
                        </div>
                    </div>
                </div>                                  
                <div class="row-fluid" style="margin-top: 10px">
                    <div class="span2"></div>
                    <div class="span3">
                        <img class="hide_text" id="volumeTypeLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                        <div id="volueTypeCancelButtonDiv" class="span4 pull-right">
                            <button id="volueTypeCancelButton"  data-dojo-type="dijit.form.Button" onclick="AddVolumeTypeInfo.cancel()" class="cancelbtn">
                                <g:message code="common.cancel"/>
                            </button>
                        </div>
                        <div id="volueTypeAddButtonDiv" class="span3 pull-right" style="display: block;" >
                            <button   id="volueTypeAddButton"  data-dojo-type="dijit.form.Button" onclick="AddVolumeTypeInfo.addConfirm()" class="okbtn">
                                <g:message code="common.create"/>
                            </button>
                        </div>        
                        <div id="volueTypeUpdateButtonDiv" class="span4 pull-right">
                            <button id="volueTypeUpdateButton"  data-dojo-type="dijit.form.Button" onclick="UpdateVolumeTypeInfo.updateConfirm()" class="okbtn">
                                <g:message code="common.update"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="pullVolumeTypePlanLoader" class="span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createFlavorInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createFlavorInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="FlavorInfo.gotoList()"><g:message code='common.gotoFlavorList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="volumeTypeUpdateConformationDialog" title="" class="span4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info" id="volumeTypeConfirmMsg"><g:message code="admin.updateItemInfoAllUser"/></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn" type="button"  data-dojo-type="dijit.form.Button" onclick="UpdateVolumeTypeInfo.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn" type="button"  data-dojo-type="dijit.form.Button" onclick="UpdateVolumeTypeInfo.cancelConfirm()"><g:message code="common.cancel"/></button>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="volumeTypeAddConformationDialog" title="" class="span4">
    <!--<p><g:message code="admin.updateItem"/></p>--> 
    <p class="alert alert-info" id="volumeTypeConfirmMsg"><g:message code="admin.addItemInfoAllUser"/></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn" type="button"  data-dojo-type="dijit.form.Button" onclick="AddVolumeTypeInfo.add()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn" type="button"  data-dojo-type="dijit.form.Button" onclick="UpdateVolumeTypeInfo.cancelConfirm()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="addVolumeTypeLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.addVolumeTypeInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.addVolumeTypeInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="AddVolumeTypeInfo.gotoList()"><g:message code='common.gotoVolumeTypeListFromAdd' /></a>
    </div>
</div>