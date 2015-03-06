<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/service/"><g:message code="menu.user.services"/></a></li>
    <li>/<li>
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
                  <span class="span6" id="userTotalBandwidth"></span> 
                  </div>
                </div>
                <div class="span5 price_info_details">
                  <div class="span12 info_tags"><g:message code="stat.user.bandwidth"/></div>
                </div>
                <span style="color: #c31b1b; font-size: 10px;" id="trialTotalBandwidth"></span>
            </div>
        </div>
        <div class="span3 stat">
             <div class="data">
                <div class="span7 price_info_value">
                  <div class="number">
                  <sup class="span2" id=""><g:message code="common.gb"/></sup>
                  <span class="span6" id="userExceededBandwidth"></span> 
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
              		<div class="number span12">
                                <sup id="userBandwidthCurrency" class="span3"></sup>
                		<span id="userTotalBandwidthCost" class="span6"></span>
					</div>
				</div>
				<div class="span5 price_info_details">
					<div class="span12 info_tags"><g:message code="stat.user.totalCost"/></div>
				</div>
              </div>              
        </div>
        <div class="span3 stat last">
          <div class="customData span12">
              <div id="userBandZoneList"></div>
          </div>
        </div> 
      </div>
    </div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div  class="new-user">
  <div class="row-fluid header"> 
  </div>   
  <div class="row-fluid">
      <div id="userBandwidthInfo"></div>
      <div class="alert alert-info hide" id="noBandWidthMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noInstance"/>
      </div>
  </div>
</div>
 
