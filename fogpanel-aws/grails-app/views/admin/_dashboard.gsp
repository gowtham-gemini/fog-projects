<!-- <div id="configMsgId" style="display: none;" class="alert alert-warning">
    <div class="alert alert-warning">
        <i class="icon-warning-sign"></i>
        <g:message code="config.isModified.message"/>
        <a href="#/admin/settings/configSync">
            <i class="icon-exchange"></i>
        </a>
    </div>
</div> -->   
<div class="alert alert-danger errorMessage" id="errorInfo">
  <span>
    <g:message code="common.connectionServer.error" />
  </span>
</div>
<div class="alert alert-danger errorMessage" id="licenseErrorInfo" style="display: none;">
    <span id="messageInfo"></span>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
      <div class="span4 stat">
        <div class="data" id="">
          <div class="span7 price_info_value">
            <div class="number">
              <sup  id="dailyUsd" class="span2"></sup> 
              <span  id="totalDaily" class="span6">0</span>                             
            </div> 
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="stat.billing.usageCost"/></div>
          </div>             
          <div class = "span11 dbdates usagePeriod" id="dailyDate"></div>
        </div>             
      </div>
      <div class="span4 stat">
        <div class="data" id="">
          <div class="span7 price_info_value">
            <div class="number span12">
            <sup  id="monthlyUsd" class="span2"></sup>
            <span id="totalMonthly" class="span6">0</span>                
              </div> 
          </div>
          <div class="span5 price_info_details">
            <div class="span12 info_tags"><g:message code="common.billing.currentMonthCost"/> </div>
          </div>
        <div class="span11 dbdates usagePeriod" id="monDate"></div>   
             
            </div>
        
        </div>
        <div class="span4 stat">
            <div class="data" id="">
              <div class="span7 price_info_value">
                <div class="number span12">
                  <sup id="paymentUsd" class="span2"></sup>      
                 <span id="totalPayment" class="span6">0</span>
                            
              </div>
              </div>
              <div class="span5 price_info_details">
                <div class="span12 info_tags"><g:message code="common.billing.currentMonthPayment"/> </div>
              </div>
              <div class="span11 dbdates usagePeriod" id="monPayDate"></div>
               
            </div>  
           
        </div>      
    </div>
</div>
</div>
<div class="row-fluid stat_cont" id="adminCostCheckListDiv" style="display: none;">
     <ul class="span12 configList">
         <li id="orgLi" style="display: none;">
            <a href="#/admin/settings/organizationBilling" style="color: #e6250e;" class="item">
                <i class="icon-warning-sign"></i>
              <g:message code="common.orgBillingSetting.message"/>
            </a> 
        </li>
<!--        <li id="domainLi" style="display: none;">
            <a href="#/admin/domain" style="color: #e6250e;" class="item">
              <i class="icon-warning-sign"></i>
              <g:message code="common.domainConfig.message" />
            </a> 
        </li>-->
        <li id="openstackConfigLi" style="display: none;">
            <a href="#/admin/settings/openstackConfig/" class="item" style="color: #e6250e;">
              <i class="icon-warning-sign"></i>
              <g:message code="common.openstackConfig.message" />
            </a> 
        </li>
        <li id="zenossConfigLi" style="display: none;">
            <a href="#/admin/settings/zenoss" class="item" style="color: #e6250e;">
              <i class="icon-warning-sign"></i>
              <g:message code="common.zenossConfig.message" />
            </a> 
        </li>
        <li id="regionLi" style="display: none;">
            <a href="#/admin/settings/regionConfig/" class="item" style="color: #e6250e;">
              <i class="icon-warning-sign"></i>
              <g:message code="common.regionConfig.message" />
            </a> 
        </li>
        <li id="importData" style="display: none;">
            <a class="item" href="#/admin/settings/importData" style="color: #e6250e;">
                <i class="icon-warning-sign"></i>
                <g:message code="common.importDataFromOpenstack" />
            </a>
        </li>
<!--        <li id="volumeTypeLi" style="display: none;">
            <a href="#/admin/settings/volumeTypes/" class="item" style="color: #e6250e;">
              <i class="icon-warning-sign"></i>
              <g:message code="common.volumeTypeConfig.message" />
            </a> 
        </li>
        <li id="flavorEmptyAlert" style="display: none;">
            <a href="#/admin/flavors/list" class="item" style="color: #e6250e;">
              <i class="icon-warning-sign"></i>
              <g:message code="common.flavorEmpty.message" />
            </a> 
        </li>
        <li id="imageEmptyAlert" style="display: none;">
            <a href="#/admin/images/list" class="item" style="color: #e6250e;">
              <i class="icon-warning-sign"></i>
              <g:message code="common.imageEmpty.message" />
            </a> 
        </li>-->
        <li id="snapLi" style="display: none;">
            <a href="#/admin/miscellaneous/snapshot" style="color: #e6250e;" class="item">
              <i class="index_title_icons_cldsnapshot"></i>
              <g:message code="common.snapshot.message" />
            </a>  
        </li>
        <li id="vmsnapLi" style="display: none;">
            <a href="#/admin/miscellaneous/vmSnapCost" style="color: #e6250e;" class="item">
              <i class="index_title_icons_cldsnapshot"></i>
              <g:message code="common.VMSnapshot.message" />
            </a> 
        </li>
        <li id="ipLi" style="display: none;">
            <a href="#/admin/miscellaneous/ipCost" style="color: #e6250e;" class="item">
              <!--<i class="index_title_icons_serv_miscel"></i>-->
                <img src="images/popup-icons/ip_icon.png" style="margin-left: -13px;" width="50" height="30">
              <g:message code="common.ipCost.message" />
            </a> 
        </li>
        <li id="bandLi" style="display: none;">
            <a href="#/admin/miscellaneous/bandwidth" style="color: #e6250e;" class="item">
              <i class="index_title_icons_serv_miscel"></i>
              <g:message code="common.bandwidth.message" />
            </a> 
        </li>
        <li id="firewallLi" style="display: none;">
            <a href="#/admin/firewall" class="item" style="color: #e6250e;">
              <i class="index_title_icons_cldfirewall"></i>
              <g:message code="common.firewall.message" />
            </a> 
        </li>       
     </ul>
</div>


<div class="row-fluid stat_cont">
    <ul class="span12 configList">
      <li>
        <a class="item">
          <i class="icon-group"></i>
          <g:message code="common.account.status" />:
        </a>
        <a class="item">
          <g:message code="common.account.status.active" /> :  <span id="active" style="color: #2dca47">0</span> ,
        </a>
        <a class="item">
          <g:message code="common.account.status.disable" /> :  <span id="disable" style="color: #e6250e">0</span> ,
        </a>
        <a class="item" >
          <g:message code="common.account.status.inActive" /> :  <span id="inactive" style="color: #461ff5">0</span> ,
        </a>
        <a class="item" >
          <g:message code="common.account.status.suspended" /> :  <span id="suspend" style="color: #e6250e">0</span> ,
        </a>
        <a class="item" >
          <g:message code="common.account.status.cancelled" /> :  <span id="cancel" style="color: #461ff5">0</span> ,
        </a>
        <a class="item" >
          <g:message code="common.account.status.closed" /> :  <span id="closed" style="color: #461ff5">0</span>
        </a>
      </li>
    </ul>
</div>
<div class="row-fluid">
  <div class="span6">
    <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.billing.currentMonthIncome" />', open:false, onShow: function () { AdminDashboard.loadBillableItemChart();}" >
      <div id='billableItemChart'>
          <div class="span12 db_graph">
          <div class="create_item_box span10 offset1">
              <h1>
              <g:message code="common.noData" /> 
              </h1>
         </div>
        </div>
      </div>
    </div>
    <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.billing.CreditLimitStatus" />', open:false, onShow: function () {
                  AdminDashboard.loadcreditLimitGrid();}" >
      <div id='creditLimitGridLoad' class="row-fluid"></div>
      <div class="row-fluid stat_cont">
          <ul class="span12 configList">
            <li>
              <a class="item">
                <g:message code="common.accountExceeded" />: <span id="creditAccTotal" style="color: #e6250e">0</span> ,
              </a>
              <a class="item">
                 <g:message code="common.amount" /> :  <span id="exAmount" style="color: #2dca47">0</span>
              </a>
            </li>
          </ul>
      </div>
      <div id='creditLimitGrid' class="row-fluid">
      <table id="creditLimitTable" class="fogpanel_report_widget" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <th><g:message code="common.accountId" /></th>
            <th><g:message code="common.account" /></th>
            <th><g:message code="common.exceededAmount" /></th>
          </tr>
      </table>
      </div>
      <a title="More" class="btn-flat new-product" href="#/admin/reports/creditLimitReport">+<g:message code="common.more" /></a>  
    </div>
    <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.billing.incomeForecast" />', open:false, onShow: function () {
                  AdminDashboard.loadIncomeForecast();}" >
      <div id="">
          <div class="row-fluid stat_cont">
             <ul class="span12 configList">
               <li>
                 <a class="item">
                    <g:message code="common.billing.estQtrly" />: <span id="preMonth" style="color: #2dca47">0</span>
                 </a>
                </li>
             </ul>
         </div>
        <div class="row-fluid stat_cont">
             <ul class="span12 configList">
               <li>
                 <a class="item">
                     <g:message code="common.billing.estHlfrly" />: <span id="quat" style="color: #2dca47">0</span>
                 </a>
                </li>
             </ul>
         </div>
        <div class="row-fluid stat_cont">
             <ul class="span12 configList">
               <li>
                 <a class="item">
                     <g:message code="common.billing.estAnual" /> : <span id="half" style="color: #2dca47">0</span>
                 </a>
                </li>
             </ul>
         </div>
        <div class="row-fluid stat_cont">
             <ul class="span12 configList">
               <li>
                 <a class="item">
                     <g:message code="common.billing.previousMonth" />: <span id="annual" style="color: #2dca47">0</span>
                 </a>
                </li>
             </ul>
         </div>
      </div>
    </div>
  </div>
  <div class="span6">
    <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.billing.dailyUsageCost" />', open:false, onShow: function () {
                  AdminDashboard.loadDailyCostChart();}" >
      <div class="row-fluid">
        <div id='dailyCostChart'>
        </div>
        <div id='dailylegende'></div>
      </div>
    </div>
    <div id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.billing.currentMonthPendingPayment" />', open:false, onShow: function () {
                  AdminDashboard.loadPendingPaymentGrid();}" >
      <div id='pendingPaymentGridLoad' class="row-fluid"></div>
      <div class="row-fluid stat_cont">
          <ul class="span12 configList">
            <li>
              <a class="item">
                <i class="icon-group"></i>
               <g:message code="common.billing.paymentPendingAccounts" />: <span id="payAccTotal" style="color: #e6250e">0</span> ,
              </a>
              <a class="item">
                <g:message code="common.billing.pendingPayments" />:  <span id="payAmount" style="color: #2dca47">0</span>
              </a>
            </li>
          </ul>
      </div>
      <div id='pendingPaymentGrid' class="row-fluid">
      <table id="pendingPaymentTable" class="fogpanel_report_widget" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <th><g:message code="common.accountId" /></th>
            <th><g:message code="common.account" /></th>
            <th><g:message code="common.billing.pendingAmount" /></th>
          </tr>
      </table>
      </div>
      <a title="More" class="btn-flat new-product" href="#/admin/reports/pendingPaymentReport">+<g:message code="common.more" /></a>  
    </div>
    <div style="display: none;"  data-dojo-type="dijit/TitlePane" data-dojo-props="title: 'Import Data From Openstack', open:false">     
        <div class="row-fluid accquireip-form-boxcont">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullPlanFromOpenstack"/></b>
                </div>
                <div class="span4 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="AdminDashboard.conformPullPlan()" id="pullPlanButton">
                            <g:message code="common.import"/>
                            <img id="pullPlanLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullPlanCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="AdminDashboard.checkDataImportPlanStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>      
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullImagesFromOpenstack"/></b>
                </div>
                    <div class="span4 pull-right" style="margin-top: 10px;">
                        <button type="button" data-dojo-type= "dijit.form.Button" id="pullImageButton" class="cancelbtn" onclick="AdminDashboard.confirmPullImage()" id="">
                                <g:message code="common.import"/>
                                <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                <img id="pullImageCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                        </button>
                        <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="AdminDashboard.checkDataImportImageStatus()">
                            <g:message code="common.check"/>
                        </button>
                </div>
            </div> 
        </div>      
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullVolumeTypesFromOpenstack"/></b>
                </div>
                <div class="span4 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="AdminDashboard.confirmPullVolumeType()" id="pullVolumeTypeButton">
                            <g:message code="common.import"/>
                            <img id="pullVolumeTypeLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullVolumeTypeCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="AdminDashboard.checkDataImportVolumeTypeStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
         </div>      
    </div>
    
      <div style="display: none;" id="" data-dojo-type="dijit/TitlePane" data-dojo-props="title: '<g:message code="common.systemOverview" />', open:false, onShow: function () {
                  AdminDashboard.loadSystemOverview();}" >
      <div class="row-fluid stat_cont">
          <span id="systemOverviewLoading" style="display: none;"><g:message code="common.loading" /></span>
           <ul class="span12 configList" id="systemOverviewInfo">
            <li>
                <a class="item">
                  FogPanel <g:message code="common.version" />: <span id="version" style="color: #2dca47">0</span>
                </a>
            </li>
            <li>
              <a class="item">
                  <g:message code="common.licenseStatus" />: <span id="licenseStatus" style="color: #2dca47"></span> 
              </a>
              -<button class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.revalidateLicense()"><g:message code="common.revalidate" /></button>
            </li>
            <li>
              <a class="item">
                  <g:message code="common.activeSockets" />: <span id="activeSockets" style="color: #2dca47">0</span>
              </a>
            </li>
            <li>
              <a class="item">
                  <g:message code="common.instanceID" />: <span id="instanceID" style="color: #2dca47"></span>
              </a>
            </li>
            <li>
              <a class="item">
                  <g:message code="common.licenseeEmail" />: <span id="licenseeEmail" style="color: #2dca47"></span>
              </a>
            </li>
           </ul>
       </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="pullPlanConform" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.openstack.pullPlanMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullPlanMessageNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.pullPlan()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.cancelPullPlan()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="pullImageConfirm" class="span4" style="display:none;">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.openstack.pullImageMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullImageMessageNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.pullImages()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.cancelPullImages()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="pullVolumeTypeConfirm" class="span4" style="display:none;">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.openstack.pullVolumeTypeMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullVolumeTypeNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.pullVolumeType()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="AdminDashboard.cancelPullVolumeType()"><g:message code="common.cancel"/></button>
    </div>
</div>