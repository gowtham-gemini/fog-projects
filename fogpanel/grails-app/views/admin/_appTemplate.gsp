<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/computation/services"><g:message code="menu.admin.services" /></a></li>
    <li>/</li>
    <li><a href="#/admin/template/appTemplate"><g:message code="menu.user.templates" /></a></li>
    <li>/</li>
    <li><g:message code="common.appTemplate" /></li>
  </ul>
</div>
</div>
<div class="row-fluid">   
<ul class="nav nav-tabs span12 customNav">
  <li>
    <a href="#/admin/template/osTemplate"><g:message code="common.osTemplate" /></a>
  </li>
  <li class="active">
      <a href="#/admin/template/appTemplate"><g:message code="common.appTemplate" /></a>
    </li>
<!--  <li>
    <a href="#/admin/template/userTemplate/">User Template</a>
  </li>   -->
</ul>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
        <div class="span3 stat">
            <div class="data span12">
                <span class="number span12" id="adminTotalAppTemp">0</span>
                <g:message code="stat.template.total" />
            </div>
           
        </div>
        <div class="span3 stat">
            <div class="data span12">
              <span class="number span12" id="adminTotalAppLinuxTemp">0</span>
                <g:message code="common.linuxTemplate" />
            </div>
            
        </div>
        <div class="span3 stat">
            <div class="data span12">
              <span class="number span12" id="adminTotalAppWindowsTemp">0</span>
               <g:message code="common.windowsTemplate" />
            </div>            
        </div>
        <div class="span3 stat last">
            <div class="customData span12">
              <div id="adminAppTempZoneList"></div>
            </div>
          <div class="row-fluid">
            <form data-dojo-type="dijit.form.Form" id="adminAppTempActionForm" class="customForm">
            <div class="span3">
              <input type="radio" name="vmInfo" id="allAppTempRadio" 
                     data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="AppTemplateInfo.showTemplate(this)">          
              <label for="allAppTempRadio"> <g:message code="common.vmStatus.all" /></label>      
          
            </div>
        <div class="span4">
          <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="linuxAppTempRadio" value="Linux" onclick="AppTemplateInfo.showTemplate(this)">           
          <label for="linuxAppTempRadio"> <g:message code="common.linux" /></label> 
            
           
        </div>
        <div class="span4">  
          <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="windowsOsTempRadio" value="Windows" onclick="AppTemplateInfo.showTemplate(this)"> 
          <label for="windowsOsTempRadio"> <g:message code="common.windows" /></label>           
        </div> 
            </form>
          </div>
        </div>
    </div>
</div>
</div>
<div class="row-fluid">
  <div class="new-user">
     <input type="hidden" id="currentAppTempId"/>
    <div class="table-wrapper products-table">
    <div class="row-fluid">
         <div class="value_dollar pull-right"><g:message code="default.valueIn" />  <span id="appTempCurrencyValue"></span></div>  
    </div>
    <div class="row-fluid filter-block">
        <div class="pull-right">            
            <a class="btn-flat success new-product" href="#/admin/template/addAppTemplate">+ <g:message code="common.add" /></a>
        </div>
    </div>
    <div class="row-fluid">
      <div id="adminAppTemplateInfo">
      </div>
      <div class="alert alert-info hide" id="noAppTempMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
            <g:message code="common.noAPPTemplate" />
      </div>
    </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteAppTemplateDialog" 
       title="Delete Template" style="color: black; width: 350px;">  
    Are you sure you want to delete this Template?
    <div style="margin-left: 100px">
      <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="AppTemplateInfo.deleteOsTemp()" id="deleteAppTempButton">
      <g:message code="common.yes" />
      </button>
      <img id="deleteAppTempLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' style="display: none"/>
      <button  data-dojo-type="dijit.form.Button" class="cancelbtn"
              onclick="AppTemplateInfo.closeDeleteDialog()" id="deleteAppTempCancelButton">
        <g:message code="common.no" />
      </button> 
      
    </div>  
  </div>