<%@ page coentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud"><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/volume/list"><g:message code="menu.user.cloud.volume"/></a></li>
            <li>/</li>
            <li id="volumeEdit"><g:message code="common.create"/></li>
        </ul>
    </div>
</div>
<div class="new-user">
    <div class="row-fluid header createVm"></div>
    <div class="row-fluid form-wrapper">
        <div class="span3 createvm-banner">
            <img src="${resource(dir: 'images')}/createvm_banner_logo.png" height="151" width="238">
            <h2 class="alphaStyle overflowLabel"><g:message code="user.createVolume"/></h2>
            <h2 class="alphaStyle overflowLabel"><g:message code="common.volumeType"/></h2>        
        </div>
        <div class="span6 createvm-form with-sidebar" id="">
    <!--        <div class="row-fluid header">
                <h3 id="volumePageHead"></h3>   
            </div>-->
            <div class="container">
                <form id="volumeForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
                    <div id="volumeContent">
                    <!--<div class="row-fluid">-->
                        <!--<div class="span12">-->
                        <div class="span12 field-box">
                            <input type="hidden" id="currentVolumeId" name="currentVolumeId" value="">
                            <label for="name" class="control-label">          
                                <g:message code="common.name"/> : <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="volumeName" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="volume.invalidMessage.volumeName"/>', placeHolder: '<g:message code="volume.name"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;= ]{1,200}',
                                promptMessage:'<g:message code="volume.volumeName.promptMessage"/>'">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="volumeNameHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeNameHelp', showDelay: 1"><g:message code="common.help.message.volumeName"/></div>
                                </div>
                            </div>                            
                        </div>
                        <div class="span12 field-box">
                            <label for="cloudMaintenanceDateDescription" class="control-label">          
                                <g:message code="common.description"/> :</label>
                            <div class="controls">
                                <input type="text" 
                                data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>',
                                placeHolder: '<g:message code="common.description.prompt"/>', promptMessage:'<g:message code="volume.volumeDescription.promptMessage"/>',
                                regExp: '[a-zA-Z.0-9- ]{0,255}', propercase: true" 
                                id="volumeDescription">
                                <div class="form_help_icon" style="top: 0; left: 0%;">
                                    <i class="icon-info-sign" id="volumeDescriptionHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeDescriptionHelp', showDelay: 1"><g:message code="common.help.message.volumeDescription"/></div>
                                </div>
                            <!--</div>-->  
                            </div>
                        </div> 
                        <div class="volumeUpdateHide span12 field-box" id="zoneListDiv" style="display:block ; margin-top: -15px">
                            <label for="" class="control-label"><g:message code="volume.zone"/>:<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="listAvailabilityZone" class="selectOption span12"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="zoneListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'zoneListHelp', showDelay: 1"><g:message code="common.help.message.zoneList"/></div>
                                </div>
                            </div> 
                        </div>
                    <!--</div>-->
                <!--</div>-->
                <!--<div class="row-fluid">
                    <div class="span12">-->
                        <div class="volumeUpdateHide span12 field-box">
                            <label for="" class="control-label"><g:message code="volume.type"/> :</label>
                            <div class="controls updatable elements">
                                <div id="volumeTypeList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="volumeTypesHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeTypesHelp', showDelay: 1"><g:message code="common.help.message.volumeTypes"/></div>
                                </div>
                            </div>
                        </div> 
                        <div class="volumeUpdateHide span12 field-box">
                            <label for="" class="control-label"><g:message code="volume.size"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                                data-dojo-props="required:'true', constraints:{min: 0}, placeHolder: '<g:message code="volume.size"/>',
                                promptMessage:'<g:message code="volume.size.promptMessage"/>', invalidMessage:'<g:message code="volume.invalidMessage.size"/>'" id="volumeSize">  
                                <div class="form_help_icon" style="top: 0px; left: 0px;">
                                    <i class="icon-info-sign" id="volumeSizeHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeSizeHelp', showDelay: 1"><g:message code="common.help.message.volumeSize"/></div>
                                </div>
                            </div>    
                        </div> 
<!--                    </div>
                </div>-->
<!--                <div class="row-fluid">
                    <div class="span12">-->
                        <div class="volumeUpdateHide span12 field-box" id="VolumeSourceDiv" style="display:block; margin-top: -15px" >
                            <label for="" class="control-label"><g:message code="volume.volumeSource"/> :</label>
                            <div class="controls updatable elements">
                                <div id="volumeSourceList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="volumeSourcesHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeSourcesHelp', showDelay: 1"><g:message code="common.help.message.volumeSources"/></div>
                                </div>
                            </div>    
                        </div>
                        <div class="volumeUpdateHide span12 field-box" id="imageSourceListDiv" style="display:none; margin-top: -15px">
                            <label for="" class="control-label"><g:message code="volume.imageSource"/> :</label>
                            <div class="controls updatable elements">
                                <div id="addVolumeImageList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="imageListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageListHelp', showDelay: 1"><g:message code="common.help.message.imageList"/></div>
                                </div>
                            </div>
                        </div>                        
                        <div class="volumeUpdateHide span12 field-box" id="volumeSourceListDiv" style="display:none; margin-top: -15px">
                            <label for="" class="control-label"><g:message code="volume.source.of.volume"/> :</label>
                            <div class="controls updatable elements">
                                <div id="volumeList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="volumeListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeListHelp', showDelay: 1"><g:message code="common.help.message.volumeList"/></div>
                                </div>
                            </div>
                        </div>
                        <div class="volumeUpdateHide span12 field-box" id="snapshotAsSourceDiv" style="display:none; margin-top: -15px">
                            <label for="" class="control-label"><g:message code="volume.snapshotSource"/> :</label>
                            <div class="controls updatable elements">
                                <div id="volumeSnapshotList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="volumeSnapshotListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'volumeSnapshotListHelp', showDelay: 1"><g:message code="common.help.message.volumeSnapshotList"/></div>
                                </div>
                            </div>    
                        </div>
                        <div class="span12 field-box control-group hide_text" id="addVolumeBillingTypeDiv">
                            <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
                            <div class="controls">
                                <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="billingType" id="volumeHourlyBilling" value="hourly" onchange="AddVolume.enableMonthlyCost()"/>
                                <label for="volumeHourlyBilling"><g:message code="common.hourly"/></label> 
                                <input type="radio" data-dojo-type="dijit.form.RadioButton" name="billingType"  id="volumeMonthlyBilling" value="monthly" onchange="AddVolume.enableMonthlyCost()"/> 
                                <label for="volumeMonthlyBilling" class=""><g:message code="common.monthly"/></label> 
                                <div class="form_help_icon">
                                    <i class="icon-info-sign"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'createVmBillingTypeHelp', showDelay: 1">
                                        <g:message code="user.createVM.billingType.toottip"/>
                                    </div>
                                </div>
                            </div>
                        </div>
<!--                <div class="row-fluid" id="imageSourceListDiv" style="display:none;">
                    <div class="span12">
                        <div class="volumeUpdateHide span4 field-box control-group" style="margin-top: -20px">
                            <label for="" class="control-label"><g:message code="volume.imageSource"/> :</label>
                            <div class="controls updatable elements">
                                <div id="imageList" class="selectOption"></div>
                                <div class="form_help_icon" style="top: -25px; left: -1px;">
                                    <i class="icon-info-sign" id="imageListHelp"></i>
                                    <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'imageListHelp', showDelay: 1"><g:message code="common.help.message.imageList"/></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->

                        <div class="span12">
                            <div class="span6 pull-right">
                                <div id="volumeAddButtonDiv" class="span2" style="display: block;" >
                                    <button   id="volumeAddButton"  data-dojo-type="dijit.form.Button" onclick="AddVolume.add()" class="okbtn">
                                        <g:message code="common.ok"/>
                                    </button>
                                </div>        
                                <div id="volumeUpdateButtonDiv" style="display: none;" class="span3">
                                    <button   id="volumeUpdateButton"  data-dojo-type="dijit.form.Button" onclick="AddVolume.update()" class="okbtn">
                                        <g:message code="common.update"/>
                                    </button>
                                </div>
                                <div id="volumeCancelButtonDiv" class="span3">
                                    <button id="volumeCancelButton"  data-dojo-type="dijit.form.Button" onclick="AddVolume.cancel()" class="cancelbtn">
                                        <g:message code="common.cancel"/>
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
        <div class="span3" id="offeringInfo">
            <div class="new_user_form inline-input">     
                <div class="row-fluid">    
                    <!--<div class="span4"><a onclick=""><g:message code='common.rateCard' /></a><g:render template="rateCard" /></div>-->
                    <div  class="span7 create_vm_cost offset6"><div class="value_dollar"><g:message code="default.valueIn"/><span id="createVolumeCurrecy" style="color: #374859; float: none"></span></div></div>             
                </div>
                <div class="row-fluid customLabel costInfoDesign" id="volumeTypeInfo">
                    <div class="span12">
                        <h3><g:message code="common.volumeTypeInfo"/></h3>
                    </div>
                    <div class="span12">       
                        <label><g:message code="common.nameOrCost"/>:</label>
                        <span id="volumeTypeCost"></span>
                    </div> 
                    <div class="span12 hide_text" id="volumeMonDiv">
                        <label><g:message code="common.monthly"/>:</label>
                        <span id="monthlyVolumeTypeCost"></span>
                    </div>
                    <hr>  
                </div> 
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="customDialgue span6" id="addVolumeLoader">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.createNetworkInfoInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.createNetworkInfoInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="VolumeInfo.gotoList()"><g:message code='common.gotoVolumeList' /></a>    
    </div>
</div>
<img class="hide_text" id="volumeLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
