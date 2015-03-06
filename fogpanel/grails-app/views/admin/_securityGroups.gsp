<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
    <li>/</li>
    <li><g:message code="user.createVM.firewall.label"/></li>   
  </ul>
</div>
</div>

<!--<div id="pad-wrapper">-->
<div class="table-wrapper products-table">
    <div class="row-fluid head">
        <div class="span12">
            <!--<h4>Firewall</h4>-->
        </div>
    </div>

    <div class="row-fluid filter-block">
        <div class="pull-right">
          <a class="btn-flat success new-product" href="#/admin/firewall/add">+ <g:message code="common.add"/></a>
        </div>
    </div>

    <div class="row-fluid">
      <div id="adminfirewallList">  
      </div>
      <div class="alert alert-info hide" id="noFirewallMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noFirewall"/>
      </div>
    </div>
</div>
<!--</div>-->
               
