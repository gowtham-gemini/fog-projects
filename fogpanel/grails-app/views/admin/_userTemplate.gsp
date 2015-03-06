<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/computation/services">Services</a></li>
    <li>/</li>
    <li><a href="#/admin/template/userTemplate">Template</a></li>
    <li>/</li>
    <li>User Template</li>
  </ul>
</div>
</div>
<div class="row-fluid">   
<ul class="nav nav-tabs span12 customNav">
  <li>
    <a href="#/admin/template/osTemplate">Os Template</a>
  </li>
  <li>
      <a href="#/admin/template/appTemplate">App Template</a>
    </li>
  <li class="active">
    <a href="#/admin/template/userTemplate/">User Template</a>
  </li>   
</ul>
</div>
<div class="row-fluid">
  <div id="main-stats">
    <div class="row-fluid stats-row">
        <div class="span3 stat">
            <div class="data">
                <span class="number" id="adminTotalUserTemp">0</span>
                Total Templates
            </div>
           
        </div>
        <div class="span3 stat">
            <div class="data">
              <span class="number" id="adminTotalUserLinuxTemp">0</span>
                Linux
            </div>
            
        </div>
        <div class="span3 stat">
            <div class="data">
              <span class="number" id="adminTotalUserWindowsTemp">0</span>
               Windows
            </div>            
        </div>
        <div class="span3 stat last">
            <div class="customData">
              <div id="adminUserTempZoneList"></div>
            </div>
          <div class="row-fluid">
            <form data-dojo-type="dijit.form.Form" id="adminUserTempActionForm" class="customForm">
            <div class="span3">
              <input type="radio" name="vmInfo" id="allUserTempRadio" 
                     data-dojo-type="dijit/form/RadioButton"  checked value="All" onclick="UserTemplateInfo.showTemplate(this)">          
              <label for="allUserTempRadio">All</label>      
          
            </div>
        <div class="span4">
          <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="linuxUserTempRadio" value="Linux" onclick="UserTemplateInfo.showTemplate(this)">           
          <label for="linuxUserTempRadio">Linux</label> 
            
           
        </div>
        <div class="span4">  
          <input type="radio" name="vmInfo" data-dojo-type="dijit/form/RadioButton"  
                 id="windowsUserTempRadio" value="Windows" onclick="UserTemplateInfo.showTemplate(this)"> 
          <label for="windowsUserTempRadio">Windows</label>           
        </div> 
            </form>
          </div>
        </div>
    </div>
</div>
</div>
<div class="row-fluid">
  <div id="pad-wrapper" class="new-user">
    <div class="table-wrapper products-table">
    <div class="row-fluid header">
      <!--<h4>User Template</h4>-->    
    </div>   
<!--    <div class="row-fluid filter-block">
        <div class="pull-right">            
            <a class="btn-flat success new-product" href="#/admin/template/addAppTemplate">+ Add</a>
        </div>
    </div>-->
    <div class="row-fluid">
      <div id="adminUserTemplateInfo">
      </div>
      <div class="alert alert-info hide" id="noUserTempMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
            You don't have any App Template yet. Create one now.
      </div>
    </div>
    </div>
  </div>
</div>
