<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/vpc"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/vpcSecurity/"><g:message code="common.security"/></a></li>
            <li>/</li>
            <li><a href="#/user/vpcSecurity/listNetworkAcl"><g:message code="common.networkACL"/></a></li>  
            <li>/</li>
            <li><g:message code="common.view"/></li>
        </ul>
    </div>
</div>
<input type="hidden" id="currentAclId">
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="aclDetailedTab" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.details"/>" onShow="ACLInfo.populateValues()">
                <div class="row-fluid">                
                  <div class="span6">                    
                      <div class="row-fluid">
                          <div class="grd-row-alt1-tbl">
                              <div class="grd-tbl-row">
                                  <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                  <div class="grd-tbl-cell clm-second"><span id="viewAclName"></span></div>
                              </div>
                          </div>
                          <div class="grd-row-alt2-tbl">
                              <div class="grd-tbl-row">
                                  <div class="grd-tbl-cell clm-first"><g:message code="common.desc"/></div>
                                  <div class="grd-tbl-cell clm-second"><span id="viewAclDesc"></span></div>
                              </div>
                          </div>
                          <div class="grd-row-alt1-tbl">
                              <div class="grd-tbl-row">
                                  <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                  <div class="grd-tbl-cell clm-second"><span id="viewAclReferenceId"  ></span></div>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>

        </div>
        <div data-dojo-type="dijit/layout/ContentPane" id="" title="<g:message code="common.aclListRules"/>" onShow="ACLInfo.populateAclRuleInfo()">
                <g:render template="aclAddRulePage" />
        </div>  
    </div>

