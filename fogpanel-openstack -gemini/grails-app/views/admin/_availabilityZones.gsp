<div class="row-fluid"> 
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a></li>
            <li>/</li>
            <li><g:message code="settings.availabilityZones"/></li>   
        </ul>
    </div>
</div>
<div class="row-fluid">
    <form id="availabilityZoneListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid">
            </div>
            <div class="row-fluid filter-block">
                <div class="pull-right">
<!--                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="AvailabilityZones.confirmPull()" id="pullAvailabilityZoneButton">
                        <g:message code="common.pullAvailabilityZonesFromOpenStack"/>
                        <img id="pullZoneLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                    </button>-->
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AvailabilityZones.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <!--<a class="btn-flat success new-product" href="#/admin/flavors/addFlavor">+ <g:message code="common.add"/></a>-->
                </div>
            </div>
            <div class="row-fluid">
                <div id="availabilityZoneListGrid"></div>
                <div class="alert alert-info hide" id="noZoneMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="common.admin.noAvailabilityZone"/>
                </div>
            </div>
        </div>
    </form>
</div>
<div data-dojo-type="dijit.Dialog" id="pullAvailabilityZoneConfirm" class="span4" style="display:none;">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.openstack.pullAvailabilityZonesMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullAvailabilityZoneNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="AvailabilityZones.pull()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AvailabilityZones.cancelPull()"><g:message code="common.cancel"/></button>
    </div>
</div>