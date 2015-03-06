<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li> 
            <li>/<li>
            <li><a href="#/admin/infrastructure"><g:message code="menu.admin.cloud.infrastructure"/></a></li>
            <li>/</li>
            <li><g:message code="common.bandwidth"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span3 stat">
                <div class="data">
                    <div class="span7 price_info_value">
                        <div class="number">
                            <sup class="span2" id=""><g:message code="common.gb"/></sup>
                            <span class="span6" id="adminTotalBandwidth"></span> 
                        </div>
                    </div>
                    <div class="span5 price_info_details">
                        <div class="span12 info_tags"><g:message code="stat.user.bandwidth"/></div>
                    </div>
                </div>

            </div>
            <div class="span3 stat">
                <div class="data">
                    <div class="span7 price_info_value">
                        <div class="number">
                            <sup class="span2" id=""><g:message code="common.gb"/></sup>
                            <span class="span6" id="adminExceededBandwidth"></span> 
                        </div>
                    </div>
                    <div class="span5 price_info_details">
                        <div class="span12 info_tags"><g:message code="stat.user.exceeded"/></div>
                    </div>
                </div>

            </div>
            <div class="span3 stat">          
                <div class="data">
                    <div class="span7 price_info_value">
                        <div class="number">
                            <sup class="span3" id="adminTotalBandwidthUsd"></sup>
                            <span class="span6" id="adminTotalBandwidthCost"></span> 
                        </div>
                    </div>
                    <div class="span5 price_info_details">
                        <div class="span12 info_tags"><g:message code="stat.user.totalCost"/></div>
                    </div>                
                </div>            
            </div>
            <div class="span3 stat last">
                <div class="customData span12">
                    <div id="adminBandZoneList"></div>
                </div>
            </div> 
        </div>
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
        <li class="active">
            <a href="#/admin/infrastructure/vmBandwidth"><g:message code="common.bandwidth"/></a>
        </li> 
        <li>
            <a href="#/admin/infrastructure/ipInfo"><g:message code="common.ipStatics"/></a>
        </li>
        <li>
            <a href="#/admin/infrastructure/network/"><g:message code="common.network"/></a>
        </li>
    </ul>
</div>
<div  class="new-user">
    <div class="row-fluid header"> 
    </div>   
    <div class="row-fluid">
        <div id="adminBandwidthInfo"></div>
        <div class="alert alert-info hide" id="noBandWidthMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.noInstanceAdmin"/>
        </div>
    </div>
</div>

