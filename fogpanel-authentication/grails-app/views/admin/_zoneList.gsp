<div class="row-fluid"> 
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/aws"><g:message code="menu.admin.configuration.aws"/></a></li>
            <li>/</li>
            <li><g:message code="common.zoneConfig"/></li>   
        </ul>
    </div>
</div>
<div class="row-fluid">
    <form id="zoneListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid">
            </div>
            <div class="row-fluid filter-block">
                <div class="pull-right">
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ZoneConfig.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                </div>
            </div>
            <div class="row-fluid">
                <div id="zoneListGrid"></div>
            </div>
        </div>
    </form>
</div>
