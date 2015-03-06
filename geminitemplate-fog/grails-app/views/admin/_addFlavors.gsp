<%@ page coentType="text/html;charset=UTF-8" %>
<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services"><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a href="#/admin/flavors/list"><g:message code="menu.flavors"/></a></li>
            <li>/</li>
            <li id="editFlavorBreadcrum"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-md-12" id="">
        <div class="row header">
            <h3 id="addFlavorhead"></h3>   
            <div class="col-md-2 value_dollar pull-right" style="display: none;"><g:message code="default.valueIn"/>   <span  id="currencyValue"></span></div>
        </div>
        <form id="adminFlavorContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="flavorContent">
                <div class="row">
                    <div class="col-md-12">
        <!--             <div class="control-group col-md-4 horizontalcontent">
                       <input type="hidden" id="currentFlavorId" value="">
                       <label for="regionList" class="control-label">
                         <g:message code="common.region"/>: 
                           <span  class="require">*</span>
                       </label>
                        <div class="controls updatable elements">
                         <div id="regionList" class="selectOption"></div>
                        </div>
                        <span  id="regionNameLabel" class="hide_lable"></span>
                     </div>-->
                        <div class="control-group col-md-4 horizontalcontent">
                            <input type="hidden" id="currentFlavorId" name="currentFlavorId" value="">
                            <label for="flavorName" class="control-label">          
                                <g:message code="common.name"/> :  <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="flavorName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.name.placeHolder"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorNameHelp', showDelay: 1"><g:message code="common.help.message.flavorName"/></div>
                                </div>    
                            </div>

                        </div>
                        <div class="control-group col-md-4 horizontalcontent" id="flavorDecsDiv">
                            <label for="flavorDescription"  class="control-label">         
                                <g:message code="common.description"/> :
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="flavorDescription" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{4,200}',
                                promptMessage:'<g:message code="common.description.prompt"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorDescriptionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorDescriptionHelp', showDelay: 1"><g:message code="common.help.message.flavorDescription"/></div>
                                </div>
                            </div>
                        </div> 
                    </div>
                    <div class="row">      
                        <div class="control-group col-md-4 horizontalcontent">
                            <label for="flavorID"  class="control-label">         
                                <g:message code="common.id"/> :
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="flavorID" data-dojo-props="required: 'true', value:'Auto',
                                invalidMessage: '<g:message code="common.value.invalid"/>', placeHolder: '<g:message code="common.id"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                promptMessage:'<g:message code="common.id"/>'"/>
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorIDHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorIDHelp', showDelay: 1"><g:message code="common.help.message.flavorID"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class = "control-group col-md-4 customDisk">
                            <label for="flavorCpuNumber" class="control-label">              
                                <g:message code="common.VcpuCore"/> : 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                                id="flavorCpuNumber" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalid.cpuNumber"/>', trim: 'true',
                                placeHolder: '<g:message code="common.cpuNumber"/>', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:1,  regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.prompt.cpuNumber" />'"
                                name="cpu_number">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorCpuNumberHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorCpuNumberHelp', showDelay: 1"><g:message code="common.help.message.flavorCpuNumber"/></div>
                                </div>
                            </div>
                             <span  id="flavorCpuNumberLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group col-md-4 customDisk">
                            <label for="flavorMemory" class="control-label">              
                                <g:message code="common.memoryMB"/> : 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                                id="flavorMemory" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.memory.invalid"/>', trim: 'true',
                                placeHolder: 'Memory', constraints:{min:512, max:1024000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:512, regExp: '[0-9]{3,7}', promptMessage:'<g:message code="common.memoryName"/>'"
                                name="">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorMemoryHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorMemoryHelp', showDelay: 1"><g:message code="common.help.message.flavorMemory"/></div>
                                </div>
                            </div>
                             <span  id="flavorMemoryLabel" class="hide_lable"></span>
                        </div>                          
                    </div>
                    <div class="row">          
                        <div class="control-group col-md-4 customDisk">
                            <label for="flavorRoodDisk" class="control-label">
                                <g:message code="common.flavorRoodDisk"/>(<g:message code="common.gb"/>) :
                            </label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="flavorRoodDisk" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', 
                                placeHolder: '<g:message code="common.flavorRoodDisk"/>', constraints:{min:0, max:3000, pattern:'#'}, timeoutChangeRate: '0.2',
                                value:5, regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.common.flavorRoodDisk"/>'"
                                name="flavorRoodDisk">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorRoodDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorRoodDiskHelp', showDelay: 1"><g:message code="common.help.message.flavorRoodDisk"/></div>
                                </div>
                            </div>
                             <span  id="flavorRoodDiskLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group col-md-4 customDisk">
                            <label for="flavorEphemeralDisk" class="control-label">              
                                <g:message code="common.flavorEphemeralDisk"/>(<g:message code="common.gb"/>) : 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="flavorEphemeralDisk" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                placeHolder: '<g:message code="common.flavorEphemeralDisk"/>', constraints:{min:0,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:0, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.flavorEphemeralDisk"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorEphemeralDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorEphemeralDiskHelp', showDelay: 1"><g:message code="common.help.message.flavorEphemeralDisk"/></div>
                                </div>
                            </div>
                             <span  id="flavorEphemeralDiskLabel" class="hide_lable"></span>
                        </div> 
                        <div class="control-group col-md-4 customDisk">
                            <label for="flavorSwapDisk" class="control-label">              
                                <g:message code="common.flavorSwapDisk"/>(<g:message code="common.gb"/>) : 
                                 <span  class="require">*</span>
                            </label>
                            <div class="controls updatable">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                id="flavorSwapDisk" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.value.invalid"/>', trim: 'true',
                                placeHolder: '<g:message code="common.flavorSwapDisk"/>', constraints:{min:0,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:0, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.flavorSwapDisk"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="flavorSwapDiskHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'flavorSwapDiskHelp', showDelay: 1"><g:message code="common.help.message.flavorSwapDisk"/></div>
                                </div>
                            </div>
                             <span  id="flavorSwapDiskLabel" class="hide_lable"></span>
                        </div>
                    </div></br>
                    <div class="row" style="display: none">     
                        <div data-dojo-type="dijit/TitlePane" data-dojo-props="open: false,title:'<g:message code="common.flavorAccess"/>'">
                            In development
                        </div>
                    </div>
                    <div class="row">     
                        <h4 ><g:message code="common.flavor.pricingInfo"/></h4>
                        <hr class="text_gray" style="margin-top: 15px">                       
                        <div>
                            <div id="flavorPriceHeadLabel">
                                <div class="row">
                                    <div class="col-md-2"><h5><g:message code="common.zoneName"/></h5></div>
                                    <div class="col-md-2"><h5><g:message code="common.instance.runningCostByMonth"/></h5></div>
                                    <div class="col-md-2"><h5><g:message code="common.instance.runningCostByHour"/></h5></div>
                                    <div class="col-md-2"><h5><g:message code="common.instance.stopageCostByMonth"/></h5></div>
                                    <div class="col-md-2"><h5><g:message code="common.instance.stopageCostByHour"/></h5></div>
                                    <div class="col-md-2"><h5><g:message code="common.instance.setupCost"/></h5></div>

                                </div>
                            </div> 
                        </div></br>
                        <div id="currentFlavorZoneInfo" class="row">
                            <div id="currentZoneCostDiv" style="margin-top: 0px"></div>
                        </div>
                    </div>
                </div>                                  
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-9"></div>
                    <div class="col-md-3">
                        <img class="hide_text" id="flavorLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                        <div id="flavorCancelButtonDiv" class="col-md-4 pull-right">
                            <button id="flavorCancelButton"  data-dojo-type="dijit.form.Button" onclick="AddFlavorInfo.cancel()" class="cancelbtn">
                                <g:message code="common.cancel"/>
                            </button>
                        </div>
                        <div id="flavorAddButtonDiv" class="col-md-3 pull-right" style="display: block;" >
                            <button   id="flavorAddButton"  data-dojo-type="dijit.form.Button" onclick="AddFlavorInfo.add()" class="okbtn">
                                <g:message code="common.create"/>
                            </button>
                        </div>        
                        <div id="flavorUpdateButtonDiv" style="display: none;" class="col-md-4 pull-right">
                            <button id="flavorUpdateButton"  data-dojo-type="dijit.form.Button" onclick="FlavorInfo.update()" class="okbtn">
                                <g:message code="common.update"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="pullPlanLoader" class="col-md-6">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.createFlavorInfoOne' /></p></div>
            <div class="col-md-12" style="margin-left: 0"><p><g:message code='common.createFlavorInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="FlavorInfo.gotoList()"><g:message code='common.gotoFlavorList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div>
</div>



<!--<div data-dojo-type="dijit.Dialog" id="computeOfferEditConformationDialog" title="Update" class="col-md-4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser"/></p>
      <div class="row offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>-->
