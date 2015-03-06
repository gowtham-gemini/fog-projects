<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><g:message code="menu.user.cloud"/></li>    
    </ul>
  </div>
</div>
<div class="row-fluid">
    <div id="main-stats-inner">
        <div class="row-fluid stats-row">
            <div class="span4 stat-box">
                <div class="data span12">
                    <div class="span5 number" id="totalVMs"></div>
                    <div class="span4">
                        <a href="#/user/cloud/userInstancePage" class="index_title_icons_cldinstances"><g:message code="menu.user.cloud.instance"/></a>
                    </div>           
                        <div class="span3 view"><a href="#/user/cloud/userInstancePage">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>
            <div class="span4 stat-box">
                <div class="data span12">
                    <div class="span5 number" id="totalStorage">  </div>
                    <div class="span4">
                        <a href="#/user/cloud/storage" class="index_title_icons_cldstorage"><g:message code="menu.user.cloud.storage"/></a>
                    </div>
                        <div class="span3 view"><a href="#/user/cloud/storage">&#8250; <g:message code="common.view"/></a></div>
                </div>            
            </div>
            <div class="span4 stat-box">
              <div class="data span12">    
                  <div class="span5 number" id="snapshotTotalCount"></div>
                  <div class="span4">
                      <a href="#/user/cloud/snapShot" class="index_title_icons_cldsnapshot"><g:message code="menu.user.cloud.snapShot"/></a>
                  </div>
                   <div class="span3 view"><a href="#/user/cloud/snapShot">&#8250; <g:message code="common.view"/></a></div>
              </div>            
            </div> 
        </div>
        <div class="row-fluid stats-row">
            <div class="span4 stat-box" id="firewallContainer"> 
                <div class="data span12"> 
                    <div class="span5 number"></div>
                    <div class="span4">
                        <a href="#/user/cloud/firewall" class="index_title_icons_cldfirewall"><g:message code="menu.user.cloud.firewall"/></a>
                    </div>
                        <div class="span3 view"><a href="#/user/cloud/firewall">&#8250; <g:message code="common.view"/></a></div>
                </div>            
            </div>
            <div class="span4 stat-box" id = "userHealthMenuContainer">
                <div class="data span12">    
                    <div class="span5 number"></div>
                    <div class="span4">
                        <a href="#/user/health" class="index_title_icons_cldhealth"><g:message code="menu.user.cloud.health"/></a>
                    </div>
                        <div class="span3 view"><a href="#/user/health">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div> 
            <div class="span4 stat-box" id="networkContainer"> 
                <div class="data span12">    
                    <div class="span5 number" id="totalNetwork"></div>
                    <div class="span4">
                        <a href="#/user/network" class="index_title_icons_network"><g:message code="menu.user.services.network"/></a>
                    </div>
                        <div class="span3 view"><a  href="#/user/network">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div>
                                    
        </div>
        <div class="row-fluid stats-row">
            <div class="span4 stat-box" id="sshKeyContainer"> 
                <div class="data span12">    
                    <div class="span5 number" id=""></div>
                    <div class="span4">
                        <a href="#/user/service/sshKey" class="index_title_icons_serv_sshkey"><g:message code="menu.user.services.sshKey"/></a>
                    </div>
                        <div class="span3 view"><a  href="#/user/service/sshKey">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div>
            <div class="span4 stat-box"> 
                <div class="data span12">    
                    <div class="span5 number" ></div>
                    <div class="span4">
                        <a href="#/user/limitSummary/" class="index_title_icons_bill_current"><g:message code="common.limitSummary"/></a>
                    </div>
                        <div class="span3 view"><a  href="#/user/limitSummary">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div>
        </div>
    </div>
</div>
