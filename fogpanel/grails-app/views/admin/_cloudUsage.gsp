<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/activity"><g:message code="menu.admin.activity"/></a></li>    
            <li>/</li>
            <li><g:message code="stat.cloudUsageAlerts"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">   
    <ul class="nav nav-tabs span12 customNav">
        <li>
            <a href="#/admin/activity/events"><g:message code="stat.events"/></a>
        </li>
        <li>
            <a href="#/admin/activity/alerts"><g:message code="stat.alerts"/></a>
        </li> 
        <li class="active">
            <a href="#/admin/activity/cloudUsage"><g:message code="stat.cloudUsageAlerts"/></a>
        </li> 
    </ul>
</div>
<div class="row-fluid">
    <div id="pad-wrapper" class="new-user">
        <div class="table-wrapper products-table"> 
            <div class="row-fluid">
                <div id="cloudUsageInfoGrid">
                </div>
                <div class="alert alert-info hide" id="noCloudUsageMessageBox" style="display: none">
                    <i class="icon-exclamation-sign"></i> 
                    <g:message code="admin.noUsageAlerts"/>
                </div>
            </div>
        </div>
    </div>
</div>
