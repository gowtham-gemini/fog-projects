<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li> 
            <li>/<li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="common.network"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">   
    <ul class="nav nav-tabs span12 customNav">
        <li>
            <a href="#/admin/infrastructure/instance/"><g:message code="menu.user.cloud.instance"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/storage/"><g:message code="menu.user.cloud.storage"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/snapShot/"><g:message code="menu.user.cloud.snapShot"/></a>
        </li>        
        <li>
            <a href="#/admin/infrastructure/vmBandwidth"><g:message code="common.bandwidth"/></a>
        </li> 
        <li>
            <a href="#/admin/infrastructure/ipInfo"><g:message code="common.ipStatics"/></a>
        </li>
        <li class="active">
            <a href="#/admin/infrastructure/network/"><g:message code="common.network"/></a>
        </li> 
    </ul>
</div>
<div class="row-fluid" style="display: none;"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="userTotalNetwork">0</span>
                    <g:message code="stat.user.totalNetwork"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                  <span class="number" id="userEnabledTotalNetwork">0</span>
                    <g:message code="stat.user.enableNetwork"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                  <span class="number" id="userDisabledTotalNetwork">0</span>
                   <g:message code="stat.user.disableNetwork"/>
                </div>            
            </div>
        </div>
    </div>
</div>
<div class="new-user">
    <div class="row-fluid header">
  <!--    <h4>SnapShot</h4>    -->
    </div>   
    <div class="row-fluid">
        <div id="userNetworkList"> </div>
        <div class="alert alert-info hide" id="noOfferMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.admin.noNetwork"/>
        </div>
    </div>
</div>