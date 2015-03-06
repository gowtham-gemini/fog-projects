<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid ">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
    <li>/</li>
    <li><g:message code="menu.user.cloud.firewall"/></li>   
  </ul>
</div>
</div>
<div id="pad-wrapper">
<div class="table-wrapper products-table">
    <div class="row-fluid head">
    </div>
    <div class="row-fluid">
      <div id="securityGroupList">  
      </div>
      <div class="alert alert-info hide" id="noFirewallMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noFirewall"/>
      </div>
    </div>
</div>
</div>
