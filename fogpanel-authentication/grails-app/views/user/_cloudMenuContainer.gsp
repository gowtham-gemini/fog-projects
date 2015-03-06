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
            <div class="span4 stat-box" id="networkContainer"> 
                <div class="data span12">    
                    <div class="span2 number" id="userNetworkCount"></div>
                    <div class="span4">
                        <a href="#/user/server" class="index_title_icons_cldinstances"><g:message code="menu.user.cloud.instance"/></a>
                    </div>
                    <div class="span3 view"><a  href="#/user/server">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div> 
            <div class="span4 stat-box" id="volumeContainer"> 
                <div class="data span12">    
                    <div class="span2 number" id="userVolumeCount"></div>
                    <div class="span4">
                        <a href="#/user/volume/list" class="index_title_icons_cldstorage"><g:message code="menu.user.cloud.volume"/></a>
                    </div>
                    <div class="span3 view"><a  href="#/user/volume/list">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div>   
            <div class="span4 stat-box" id="imageContainer"> 
                <div class="data span12">    
                    <div class="span2 number" id="userimageCount"></div>
                    <div class="span4">
                        <a href="#/user/image/list" class="index_title_icons_cldimage" style="width:20px"><g:message code="menu.user.cloud.image"/></a>
                    </div>
                    <div class="span3 view"><a  href="#/user/image/list">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div> 
            <!--<div class="span4 stat-box" id="networkContainer"> 
                <div class="data span12">    
                    <div class="span2 number" id="totalNetwork"></div>
                    <div class="span4">
                        <a style="padding: 5px 0px 5px 42px;" href="#/user/network/list" class="index_title_icons_network"><g:message code="menu.user.services.networks"/></a>
                    </div>
                    <div class="span3 view"><a  href="#/user/network/list">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div>-->     
        </div>
        <div class="row-fluid stats-row">
            <div class="span4 stat-box" id=""> 
                <div class="data span12">    
                    <div class="span2 number" id="userSnapshotCount"></div>
                    <div class="span4">
                        <a href="#/user/snapshot/list" class="index_title_icons_cldsnapshot"><g:message code="menu.user.cloud.snapshots"/></a>
                    </div>
                    <div class="span3 view"><a  href="#/user/snapshot/list">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div> 
            <div class="span4 stat-box" id = "userHealthMenuContainer">
                <div class="data span12">    
                    <div class="span2 number"></div>
                    <div class="span4">
                        <a href="#/user/health" class="index_title_icons_cldhealth"><g:message code="menu.user.cloud.health"/></a>
                    </div>
                    <div class="span3 view"><a href="#/user/health">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>
<!--            <div class="span4 stat-box">
                <div class="data span12">    
                    <div class="span2 number"></div>
                    <div class="span4">
                        <a href="#/user/notification" class="index_title_icons_cldnotification"><g:message code="common.notification"/></a>
                    </div>
                    <div class="span3 view"><a href="#/user/notification">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>-->
        </div>
    </div>
</div>    
