<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/computation/services"><g:message code="menu.admin.services" /></a></li>
    <li>/</li>
    <li><a href="#/admin/template/osTemplate"><g:message code="menu.user.templates" /></a></li>
    <li>/</li>
    <li><g:message code="common.osTemplate" /></li>
  </ul>
</div>
</div>
<div class="row-fluid">   
  <ul class="nav nav-tabs span12 customNav">
    <li class="active">
      <a href="#/admin/template/osTemplate"><g:message code="common.osTemplate" /></a>
    </li>
    <li>
      <a href="#/admin/template/appTemplate"><g:message code="common.appTemplate" /></a>
    </li>  
  </ul>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
        <div class="span3 stat">
            <div class="data span12">
                <span class="number span12" id="adminTotalOsTemp"></span>
                <g:message code="stat.template.total" />
            </div>
           
        </div>
        <div class="span3 stat">
            <div class="data span12">
              <span class="number span12" id="adminTotalLinuxTemp"></span>
                <g:message code="common.linux" />
            </div>
            
        </div>
        <div class="span3 stat">
            <div class="data span12">
              <span class="number span12" id="adminTotalWindowsTemp"></span>
               <g:message code="common.windows" />
            </div>            
        </div>
        <div class="span3 stat last">
          <div class="row-fluid">
            <div class="customData span12">
              <div id="adminOsTempZoneList"></div>
            </div>
            </div>
          <div class="row-fluid">
            <form data-dojo-type="dijit.form.Form" id="adminOSTempActionForm" class="customForm">
            <div class="span3">
              <input type="radio" name="vmInfo" id="allOsTempRadio" 
                     data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="OsTemplateInfo.showTemplate(this)">          
              <label for="allOsTempRadio"> <g:message code="common.vmStatus.all" /></label>      
          
            </div>
        <div class="span4">
          <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="runningOsTempRadio" value="Linux" onclick="OsTemplateInfo.showTemplate(this)">           
          <label for="runningOsTempRadio"><g:message code="common.linux" /></label> 
            
           
        </div>
        <div class="span4">  
          <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="stoppedOsTempRadio" value="Windows" onclick="OsTemplateInfo.showTemplate(this)"> 
          <label for="stoppedOsTempRadio"><g:message code="common.windows" /></label>           
        </div> 
            </form>
          </div>
        </div>
    </div>
</div>
</div>
<div class="row-fluid">
  <div  class="new-user">
    <input type="hidden" id="currentOsTempId"/>
    <div class="table-wrapper products-table">   
    <div class="row-fluid">
         <div class="value_dollar pull-right"><g:message code="default.valueIn" /><span id="tempOsTempCurrencyValue"></span></div>  
      </div>
    <div class="row-fluid filter-block">
            <div class="pull-right">
                <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="OsTemplateInfo.conformPull()" id="">
                  <g:message code="common.pullTemplateFromCloudStack"/>
                </button>
                <a class="btn-flat success new-product" href="#/admin/template/addOsTemplate">+ <g:message code="common.add" /></a>
            </div>
    </div>
      <div class="row-fluid">
        <div id="adminInstanceInfo">
        </div>
        <div class="alert alert-info hide" id="noOsTempMessageBox" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="common.noOSTemplate" />
          </div>
      </div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteOsTemplateDialog" 
       title="Delete Template" style="color: black; width: 350px;">  
    Are you sure you want to delete this Template?
    <div style="margin-left: 100px">
      <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="OsTemplateInfo.deleteOsTemp()" id="deleteOsTempButton">
      <g:message code="common.yes" />
      </button>
      <img id="deleteOsTempLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23' style="display: none"/>
      <button  data-dojo-type="dijit.form.Button" class="cancelbtn"
              onclick="OsTemplateInfo.closeDeleteDialog()" id="deleteOsTempCancelButton">
        <g:message code="common.no" />
      </button> 
      
    </div>  
  </div>
  <div data-dojo-type="dijit.Dialog" id="pullTemplateConform" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.pullTemplateMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.pullTemplateMessageNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="OsTemplateInfo.pullTemplate()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="OsTemplateInfo.cancelPullTemplate()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="pullTemplateLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>
