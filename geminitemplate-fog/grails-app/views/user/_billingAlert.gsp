<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row">
<div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/home/activity"><g:message code="menu.user.activity"/></a></li>    
    <li>/</li>
    <li><g:message code="menu.user.activity.billingAlert"/></li>
  </ul>
</div>
</div>
<div class="row">   
<ul class="nav nav-tabs col-md-12 customNav">
  <li>
    <a href="#/user/home/userAlert"><g:message code="menu.user.activity.userAlert"/></a>
  </li>
  <li class="active">
      <a href="#/user/home/billingAlert"><g:message code="menu.user.activity.billingAlert"/></a>
    </li>   
</ul>
</div>
<div class="row">
  <div id="pad-wrapper" class="new-user">
    <div class="table-wrapper products-table">
    <div class="row">
      <div id="billingInfoGrid">
      </div>
      <div class="alert alert-info hide" id="noBillingAlertsMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="menu.user.activity.noAlert"/>
      </div>
    </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="viewBillingAlertPage" title="<g:message code="menu.user.activity.billingAlert"/>" class="col-md-5">  
    <div class="row"> 
         <form class="form-horizontal" data-dojo-type="dijit.form.Form">  
             <div class="col-md-12"></div>
             <div class="col-md-12 field-box control-group">
                 <label class="control-label"><g:message code="common.date"/></label>
                 <div class="controls elements">           
                     <p id="alertDate"></p> 
                 </div>
             </div>
                 <div class="col-md-12 field-box control-group">
                     <label class="control-label"><g:message code="common.description"/></label>
                     <div class="controls elements">           
                         <p id="alertDescription"></p> 
                     </div>
                 </div>        
         </form>
    </div>   
</div>

