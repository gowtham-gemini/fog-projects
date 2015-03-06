<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/template/linux"><g:message code="menu.user.templates"/></a></li>
    <li>/<li>
    <li><g:message code="common.windows"/></li>
  </ul>
</div>
</div> 
<g:render template="templateInfo" />
<div class="row-fluid"><div class="span1"></div></div>
<div class="row-fluid">   
<ul class="nav nav-tabs span12">
  <li>
    <a href="#/user/template/linux"> <g:message code="common.linux"/></a>
  </li>
  <li  class="active">
      <a href="#/user/template/Windows"><g:message code="common.windows"/></a>
    </li>
  <li>
    <a href="#/user/template/AppTemplate"><g:message code="stat.appTemplate"/></a>
  </li>
</ul>
</div>
<div>
  <div class="table-wrapper products-table">
   <div class="row-fluid">
    <div class="value_dollar"><g:message code="default.valueIn"/>  <span id="userTempCurrency"></span></div>  
  </div>
    <div class="row-fluid">
      <div class="linuxTemplate"  id="windowsTemplateList"></div>
    </div>
  </div>
</div>

