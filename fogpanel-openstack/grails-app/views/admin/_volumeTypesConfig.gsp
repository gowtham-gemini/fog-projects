<div class="row-fluid"> 
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a></li>
            <li>/</li>
            <li><g:message code="settings.vloumetypes"/></li>   
        </ul>
    </div>
</div>
<div class="row-fluid">
    <form id="volumeTypeListListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
                <div class="row-fluid">
                    <!--<div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="floavourCurrencyValue"></span></div>-->
                </div>
                <div class="row-fluid filter-block">
                    <div class="pull-right">
<!--                            <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="VolumeTypesConfig.confirmPull()" id="pullVolumeTypeButton">
                                <g:message code="common.pullVolumeTypesFromOpenstack"/>
                                <img id="pullVolumeTypeLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            </button>-->
                            <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VolumeTypesConfig.populateValues()">
                                <g:message code='common.refresh' />
                            </button>
                            <!--<a class="btn-flat success new-product" href="#/admin/flavors/addFlavor">+ <g:message code="common.add"/></a>-->
                    </div>
                </div>
                <div class="row-fluid">
                    <div id="volumeTypesListGrid"></div>
                    <div class="alert alert-info hide" id="noOfferMessageBox" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="common.admin.noFlavors"/>
                    </div>
                </div>
            </div>
    </form>
</div>
<!--div class="row-fluid" id="regionDiv" style="margin-top: 25px;">   
    <div class="well well-large span12">
        <div class="row-fluid">
            <div class="span12"> 
                <input type="hidden" id="regionId"/>
                <form class="form-horizontal" id="addRegionForm" data-dojo-type="dijit.form.Form">
                    <div class="row-fluid" id="regionsPage">
                        <div class="span12 control-group field-box">
                            <label for="regionName" class="control-label">
                                <g:message code="common.region.name"/>:<span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="regionName"  data-dojo-props="placeHolder: '<g:message code="common.enterRegionName"/>', 
                                       required: 'true',invalidMessage:'<g:message code="common.region.invalid"/>'">
                            </div>
                        </div>    
                    </div> 
                    <div class="pull-right span4">
                        <div id="addRegionButton">
                            <button type="button"  onclick="RegionConfig.addRegion()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.add"/></button>
                            <button   class="cancelbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" RegionConfig.cancelRegion()"><g:message code="common.cancel"/></button>
                        </div>
                        <div style="display: none;" id="updateCancelRegionButton">
                            <button   class="defaultbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" RegionConfig.updateRegion()"><g:message code="common.update"/></button>

                            <button   class="cancelbtn"  type="button" data-dojo-type="dijit.form.Button" onclick=" RegionConfig.cancelRegion()"><g:message code="common.cancel"/></button>
                        </div>
                    </div>

                </form> 
            </div>
        </div> 
    </div>
</div-->
    <!--div data-dojo-type="dijit.Dialog" id="showDeleteRegionDialog" title="<g:message code="common.region.delete"/>" class="span3">
        <input type="hidden" id="regionId">
        <div class="row-fluid form-wrapper"> 
            <p> Are you sure you want to delete this item? </p>
            <div class="span5 field-box control-group">
                <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="RegionConfig.deleteRegion()" id="" >
                    <g:message code="common.yes"/>
                </button>
                <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="RegionConfig.closeDeleteRegionDialog()">
                    <g:message code="common.no"/>
                </button> 
            </div>
        </div> 
    </div-->
    <div data-dojo-type="dijit.Dialog" id="pullVolumeTypeConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.openstack.pullVolumeTypeMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullVolumeTypeNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="VolumeTypesConfig.pull()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VolumeTypesConfig.cancelPull()"><g:message code="common.cancel"/></button>
        </div>
    </div>