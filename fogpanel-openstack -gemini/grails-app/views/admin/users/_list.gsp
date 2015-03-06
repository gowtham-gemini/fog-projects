<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li><g:message code="common.users"/></li>   
  </ul>
</div>
</div>
<input type="hidden" id="currentAccountId">
<!--<div id="pad-wrapper">-->
<div class="table-wrapper products-table">
    <div class="row-fluid head">
        <div class="span12">
            <!--<h4>Firewall</h4>-->
        </div>
    </div>

    <div class="row-fluid filter-block">
        <div class="pull-right">
          <a class="btn-flat success new-product" onClick="UsersInfo.addUser()">+ <g:message code="common.addUser"/></a>
        </div>
    </div>

    <div class="row-fluid">
      <div id="userGridList">  
      </div>
      <div class="alert alert-info hide" id="noUserMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.message.noUser"/>
      </div>
    </div>
</div>

  