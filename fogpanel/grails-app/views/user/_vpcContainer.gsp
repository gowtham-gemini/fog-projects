<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><g:message code="common.vpc.yourVPC"/></li>    
    </ul>
  </div>
</div>
<div class="row-fluid">
    <div id="main-stats-inner">
        <div class="row-fluid stats-row">
            <div class="span4 stat-box">
                <div class="data span12">
                    <div class="span5 number" id="vpcTopoTotalCountNumber"></div>
                    <div class="span4">
                        <a href="#/user/vpc/topology" class="index_title_icons_vpc"><g:message code="common.topology"/></a>
                    </div>           
                        <div class="span2 view"><a href="#/user/vpc/topology">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>
            
            <div class="span4 stat-box">
                <div class="data span12">
                    <div class="span5 number" id="vpcTotalCountNumber"></div>
                    <div class="span4">
                        <a href="#/user/vpc/list" class="index_title_icons_vpc"><g:message code="common.vpc"/></a>
                    </div>           
                        <div class="span2 view"><a href="#/user/vpc/list">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>
            <div class="span4 stat-box">
                <div class="data span12">
                    <div class="span5 number" id="vpcTierMenuCount"></div>
                    <div class="span4">
                        <a href="#/user/tier/list" class="index_title_icons_network"><g:message code="common.tiers"/></a>
                    </div>
                        <div class="span2 view"><a href="#/user/tier/list">&#8250; <g:message code="common.view"/></a></div>
                </div>            
            </div>             
        </div>
        <div class="row-fluid stats-row">
            <div class="span4 stat-box">
              <div class="data span12">    
                  <div class="span5 number" id="vpcMenuVMCount"></div>
                  <div class="span4">
                      <a href="#/user/tier/instance" class="index_title_icons_cldstorage"><g:message code="common.instance"/></a>
                  </div>
                   <div class="span2 view"><a href="#/user/tier/instance">&#8250; <g:message code="common.view"/></a></div>
              </div>            
            </div>
            <div class="span4 stat-box"> 
                <div class="data span12"> 
                    <div class="span5 number" id="vpcMenuPublicLBCount"></div>
                    <div class="span5">
                        <a href="#/user/tier/publicLB" class="index_title_icons_lb"><g:message code="common.publicLB"/></a>
                    </div>
                        <div class="span2 view"><a href="#/user/tier/publicLB">&#8250; <g:message code="common.view"/></a></div>
                </div>            
            </div>
            <div class="span4 stat-box">
                <div class="data span12">    
                    <div class="span4 number" id="vpcMenuInternetLB"></div>
                    <div class="span5">
                        <a href="#/user/tier/internalLB" class="index_title_icons_lb"><g:message code="common.internaleLB"/></a>
                    </div>
                        <div class="span2 view"><a href="#/user/tier/internalLB">&#8250; <g:message code="common.view"/></a></div>
                </div>
            </div>             
                                   
        </div>
        <div class="row-fluid stats-row">
            <div class="span4 stat-box" id="networkContainer"> 
                <div class="data span12">    
                    <div class="span2 number offset1" id="vpcMenuPublicIPAddr"></div>
                    <div class="span7">
                        <a href="#/user/tier/publicIP" class="index_title_icons_publicIP"><g:message code="common.publicIP"/></a>
                    </div>
                        <div class="span2 view"><a  href="#/user/tier/publicIP">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div>
            <div class="span4 stat-box" id="vpcMenustatisNat"> 
                <div class="data span12">    
                    <div class="span5 number offset" id="vpcMenuStaicNat"></div>
                    <div class="span5">
                        <img src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img>
                        <a href="#/user/tier/staticNat" class=""><g:message code="common.staticNat"/></a>
                    </div>
                        <div class="span2 view"><a  href="#/user/tier/staticNat">&#8250; <g:message code="common.view"/></a></div>
                </div> 
            </div> 
            <div class="span4 stat-box"> 
                <div class="data span12">    
                    <div class="span3 number offset" id="vpcPrivateGatewayCount">0</div>
                    <div class="span8">
                        <img src='css/theme/fog-classic/images/cloud_icons/staticnat_icon.png'></img>
                        <a class="" style="height: auto; width: auto;" href="#/user/gateway/privateGateway" class=""><g:message code="common.privateGateways"/></a>
                    </div>
                    <div class="span2 view"><a  href="#/user/gateway/privateGateway">&#8250; <g:message code="common.view"/></a></div>

                </div> 
            </div>
        </div>
    </div>
</div>
