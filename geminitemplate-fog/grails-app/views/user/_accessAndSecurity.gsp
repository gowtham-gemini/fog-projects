<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><g:message code="menu.user.accessAndSecurity"/></li>    
        </ul>
    </div>
</div>
<div class="row">
    <div id="main-stats-inner">
        <div class="row stats-row"> 
            <div class="col-md-4 stat-box" id="accesssAndSecurityContainer"> 
                <div class="data col-md-12">    
                    <div class="col-md-2 number" id="UsersecurityGroupsCount"></div>
                    <div class="col-md-7">
                        <a href="#/user/securityGroup/list" class="index_title_icons_cldfirewall"><g:message code="menu.securityGroups"/></a>
                    </div>
                    <div class="col-md-3 view"><a  href="#/user/securityGroup/list">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>
            <div class="col-md-4 stat-box" id=""> 
                <div class="data col-md-12">    
                    <div class="col-md-2 number" id="userSshKeyCount"></div>
                    <div class="col-md-4">
                        <a href="#/user/cloud/sshKey" class="index_title_icons_serv_sshkey"><g:message code="menu.user.services.sshKey"/></a>
                    </div>
                    <div class="col-md-3 view"><a  href="#/user/cloud/sshKey">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>            
            <div class="col-md-4 stat-box" id="floatingIpContainer"> 
                <div class="data col-md-12">    
                    <div class="col-md-2 number" id="userFloatingIpCount"></div>
                    <div class="col-md-7">
                        <a href="#/user/floatingIp/list"><img src='images/popup-icons/ip_icon.png' width="50px" height="60px"/><g:message code="menu.floatingIps"/></a>
                    </div>
                    <div class="col-md-3 view"><a  href="#/user/floatingIp/list">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>
            
        </div>
    </div>
</div>