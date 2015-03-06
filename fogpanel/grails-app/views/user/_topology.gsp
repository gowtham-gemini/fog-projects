<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc"/></a></li>
        <li>/</li>
        <li><g:message code="common.topology"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid"> 
    <div class="span12">
        <div class="span10">
            <div class="zone-cost-boxcont span7">
                <div class="span12 zone_cost_row_1">
                    <div class="control-group">
                        <label  class="control-label" ><g:message code="common.vpc"/></label>
                        <div class="controls elements"><div id="generalTopologyList"></div></div>
                    </div>
                </div>                
            </div>
            <div class="span5" id="topologyTierLimitReachedDiv" style="display: none">
                <div class="alert alert-danger">        
                    <i class="icon-exclamation-sign"></i><g:message code="common.reachedNetworkLimit"/>
                </div> 
            </div>
        </div>
        <div class="span2">                       
            <div class="filter-block">                                                          
                <div class="pull-right"><a class="btn-flat success new-product" href="#/user/vpc/addVpc">+ <g:message code="common.addVPC"/></a></div>
            </div>            
        </div>        
    </div>
    <div class="span12"></div>
    <div class="span11 hide_text" id="GeneralTopologyPage">
        <div class="row-fluid">
            <div class="router-cont span12">
                <h2><g:message code="common.vpcRouter"/></h2>
                <div class="router-info-boxlst tiers-info-desc">
                    <div  class="span2 field_box">
                        <a class="no_cursor">
                            <div class="info-boxlist-value" id="vpcGeneralCidrValue" style="font-size: 14px;"></div>
                            <div class="info-boxlist-title" ><g:message code="common.cidr"/></div> 
                        </a>
                    </div>
                    <div class="span2 field_box">
                            <a  id="vpcGeneralPrivageGatewayLink">
                               <div class="info-boxlist-value" id="generlPrivateGateway"></div>
                               <div class="info-boxlist-title" ><g:message code="common.privateGateway"/></div>  
                            </a>
                        </div>
                        <div class="span2 field_box">
                            <a id="vpcGeneralPublicIpLink">
                                <div class="info-boxlist-value" id="generalPublicIp"></div>
                                <div class="info-boxlist-title" ><g:message code="common.publicIp"/></div>
                            </a>
                        </div>
                        <div class="span2 field_box">
                            <a id="vpcGenerals2sVpnLink">
                                <div class="info-boxlist-value" id="generalS2sVpn"></div>
                                <div class="info-boxlist-title" ><g:message code="common.s2sVpn"/></div>
                            </a>
                        </div>
                        <div class="span2 field_box">
                            <a id="vpcGeneralNetworkAclLink">
                                <div class="info-boxlist-value" id="generalNetworkAcl"></div>
                                <div class="info-boxlist-title" ><g:message code="common.networkAcl"/></div>
                            </a>
                        </div>                               
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12">
                <div class="span4"><div class="web-tier-info-cont row-fluid"></div></div><div class="span4"><div class="web-tier-info-cont row-fluid"></div></div><div class="span4"><div class="web-tier-info-cont row-fluid"></div></div>
            </div>
        </div>
        <div class="row-fluid" id="generalTopologyTierMenuList">
            <div class="web-app-db-cont span12">
                <div class="web-tier-cont span4">
                    <h2 class="webtier-title">
                                <g:message code="common.webTier"/><span id="generalTopoWebTierCount"></span>
                                <a onclick="NetworkTopology.addTierShow()" id="topoAddWebTierButton" title="<g:message code="common.addTier"/>" style="color: #FFFFFF; float: right; margin-right: 14px; text-decoration: none">+</a> 
                    </h2>
                    <div class="web-tier-info-cont row-fluid" id="generalWebTierList"></div>
                </div>
                <div class="app-tier-cont span4">
                    <h2 class="apptier-title">
                                <g:message code="common.appTier"/><span id="generalTopoAppTierCount"></span>
                                <a id="topoAddAppTierButton" onclick="NetworkTopology.addTierShow()" title="<g:message code="common.addTier"/>" style="color: #FFFFFF; float: right; margin-right: 14px; text-decoration: none">+</a> 
                    </h2>
                    <div class="app-tier-info-cont row-fluid" id="generalAppTierList"></div>
                </div>
                <div class="db-tier-cont span4">
                    <h2 class="dbtier-title">
                                <g:message code="common.dbTier"/><span id="generalTopoDBTierCount"></span>
                                <a onclick="NetworkTopology.addTierShow()" title="<g:message code="common.addTier"/>" id="topoAddDBTierButton" style="color: #FFFFFF; float: right; margin-right: 14px; text-decoration: none">+</a>
                    </h2>
                    <div class="db-tier-info-cont row-fluid" id="generalDbTierList"></div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div id="generalTopologyTierLoader" class="hide_text">
                <img src='images/vmload.gif' alt='' height='36' width='100'/> <br> <p><g:message code="common.loading"/></p>
            </div>            
        </div>
    </div>
    <div class="span12">
        <div class="alert alert-info hide" id="topologyNoVpcMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.user.noVpc"/>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog"  id="vpcTopologyAddTierDialog" title="<g:message code="common.addTier"/>" class="customDialog span6">
    <div class="row-fluid"> 
        <div class="span2"><a class="index_title_icons_network span12"></a></div>
        <form id="generalTopoAddTierForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
            <div class="span9">
                <div class="row-fluid hide_text" id="topoAddTierCloudstackException">
                    <div class="span12 alert alert-error"><i class="icon-remove-sign span1"></i><span id="topologyaddTierCloudstackExceptionMsg" class="span10"></span></div>
                </div>                
                <div id="topoAddTierPageDiv" class="form-horizontal">
                    <div class="row-fluid">
                        <div class="control-group">
                            <label for="tierName" class="control-label">
                                <g:message code="common.name"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                    id="topoTierName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{3,200}',
                                    promptMessage:'<g:message code="common.name.placeHolder"/>'">
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="tierNetworkOffering" class="control-label">
                                <g:message code="common.networkoffering"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls ">
                                <div id="topologyNetworkOfferingList" class="selectOption"></div>
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="tierGateway" class="control-label">
                                <g:message code="common.gateway"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                    id="topologyTierGateway" data-dojo-props="required: 'true',
                                    invalidMessage: '<g:message code="common.gateway.invalid"/>', placeHolder: '<g:message code="common.gateway"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                    promptMessage:'<g:message code="common.gateway.prompt"/>'"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="tierNetmask" class="control-label">
                                <g:message code="common.netmask"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls ">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"  id="topologyTierNetmask" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.netmask.invalid"/>', placeHolder: '<g:message code="common.netmask"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                promptMessage:'<g:message code="common.netmask.prompt"/>'"/>
                            </div>
                        </div>
                        <div class="control-group" id="topolAclDiv">
                            <label for="tierAcl" class="control-label">
                                <g:message code="common.ACL"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls ">
                                <div id="topologyTierAclList" class="selectOption"></div>
                            </div>
                        </div>                                
                    </div>
                </div> 
            </div>
        </form>
    </div>
    <div class="row-fluid">
        <div class="pull-right">
            <button data-dojo-type="dijit.form.Button" onclick="NetworkTopology.addTopoTier();" class="primarybtn">
                <g:message code="common.ok"/>
            </button>
            <button data-dojo-type="dijit.form.Button" onclick="NetworkTopology.cancelTier();" class="cancelbtn">
                <g:message code="common.cancel"/>
            </button>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="topoVpcLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>