
<!--Bread Crump :: Replaced :: Begins Here -->
                <ul class="breadcrumb">
                    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a><span class="divider">/</span></li>
                    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a><span class="divider">/</span></li>
                    <li><a href="#"><g:message code="menu.admin.configuration.openstack"/></a></li> 
                </ul>
<!--Bread Crump :: Replaced :: Ends Here -->
                    
<div class="row-fluid">
    <ul class="nav nav-tabs span12">
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
<div class="row-fluid form-wrapper"> 
    <ul class="span12 configList">
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
        <!--<li>
            <a class="item" href="#/admin/settings/volumeTypes">
                <i class="icon-envelope-alt"></i>
                <g:message code="settings.vloumetypes"/>
            </a>
            <a class="time" href="#/admin/settings/volumeTypes">
                <i class="icon-cog"></i>
            </a> 
        </li>-->
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