<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/home/accountAlert"><g:message code="menu.user.activity"/></a></li>    
    <li>/</li>
    <li><g:message code="menu.user.activity.notification"/></li>
  </ul>
</div>
</div>
<div class="row-fluid">
  <div id="pad-wrapper" class="new-user">
    <div class="table-wrapper products-table">
    <div class="row-fluid">
      <div id="notificationInfoGrid">
      </div>
      <div class="alert alert-info hide" id="noNotificationAlertsMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.noNotification"/>
      </div>
    </div>
    </div>
  </div>
</div>


