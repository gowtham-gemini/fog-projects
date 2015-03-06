<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#"><g:message code="menu.user.services"/></a></li>
            <li>/<li>
            <li><g:message code="common.portForwarding"/></li>
        </ul>
    </div> 
</div>
<div class="row-fluid"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="ipManagerTotalNetwork">0</span>
                    <g:message code="stat.user.totalNetwork"/>
                </div>
            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="portForwardingCount">0</span>
                    <g:message code="common.rules"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <div class="span7 price_info_value">
                        <div class="number span12">
                                <sup id="accquiredIPCurrency" class="span3">USD</sup>
                                <span id="portForwardingCost" class="span6">0</span>
                        </div>
                    </div>
                    <g:message code="common.cost"/>
                </div>            
            </div>
        </div>
    </div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div class="table-wrapper products-table">
    <div class="row-fluid filter-block">

    </div>
    <div class="row-fluid">
        <div id="portForwardingPublicIpList"></div>
        <div class="alert alert-info hide" id="noPortForwardingPublicIpAddressMessageBox" style="display: none">
          <i class="icon-exclamation-sign"></i> 
          <g:message code="common.user.noNetworkIp"/>
        </div>
    </div>
</div>
