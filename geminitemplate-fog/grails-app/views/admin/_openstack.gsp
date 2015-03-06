<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><g:message code="menu.admin.configuration.openstack"/></li>
        </ul>
    </div>
</div>
<div class="row">
    <ul class="nav nav-tabs col-md-12">
        <li>
            <a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a>
        </li>
        <li>
            <a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a>
        </li>
<!--            <li>
            <a href="#/admin/settings/cloudStack"><g:message code="menu.admin.configuration.cloudStack"/></a>
        </li>-->
        <li  class="active">
            <a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a>
        </li>
        <li>
                <a href="#/admin/settings/zenoss"> Zenoss </a>
        </li>
    </ul>
</div>
<div class="row form-wrapper"> 
    <ul class="col-md-12 configList">
        <li>
            <a class="item" href="#/admin/settings/openstackConfig">
                <i class="icon-envelope-alt"></i>
                <g:message code="common.openstackConfig"/>
            </a>
            <a class="time" href="#/admin/settings/openstackConfig">
                <i class="icon-cog"></i>
            </a> 
        </li>  
        <li>
            <a class="item" href="#/admin/settings/regionConfig">
                <i class="icon-envelope-alt"></i>
                <g:message code="common.regionConfig"/>
            </a>
            <a class="time" href="#/admin/settings/regionConfig">
                <i class="icon-cog"></i>
            </a> 
        </li> 
        <li>
            <a class="item" href="#/admin/domain">
                <i class="icon-group"></i>
                <g:message code="common.domainConfig"/>
            </a>
            <a class="time" href="#/admin/domain">
                <i class="icon-cog"></i>
            </a>
        </li> 
        <li>
            <a class="item" href="#/admin/settings/volumeTypes">
                <i class="icon-envelope-alt"></i>
                <g:message code="settings.vloumetypes"/>
            </a>
            <a class="time" href="#/admin/settings/volumeTypes">
                <i class="icon-cog"></i>
            </a> 
        </li> 
        <li>
            <a class="item" href="#/admin/settings/availabilityZones">
                <i class="icon-envelope-alt"></i>
                <g:message code="settings.availabilityZones" />
            </a>
            <a class="time" href="#/admin/settings/availabilityZones">
                <i class="icon-cog"></i>
            </a> 
        </li>
        <li>
            <a class="item" href="#/admin/settings/importData">
                <i class="icon-envelope-alt"></i>
                <g:message code="common.importDataFromOpenstack" />
            </a>
            <a class="time" href="#/admin/settings/importData">
                <i class="icon-cog"></i>
            </a> 
        </li> 
    </ul>

</div>
<!--</div>-->