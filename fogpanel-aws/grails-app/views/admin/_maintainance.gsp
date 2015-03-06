<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
      <li>/<li>   
      <li><g:message code="menu.admin.cloud.maintenance"/></li>
    </ul>
  </div>
</div>
<div class="table-wrapper products-table">
  <div class="row-fluid filter-block">
    <div class="pull-right">
      <a class="btn-flat success new-product" href="#/admin/dashboard/cloudMaintenance">+ <g:message code="common.add"/></a>
    </div>
  </div>
  <div class="row-fluid">
      <div id="maintainCalendarDiv"><g:message code="common.message.loading"/></div>
  </div>                
</div>
<div data-dojo-type="dijit.Dialog" id="viewEventPage" title="<g:message code="common.event.description"/>" class="customDialgue">  
  <div class="span4">
    <div class="products-table"> 
    <div class="row-fluid">
      <div class="span2"><label><g:message code="common.date"/></label></div>
      <div class="span3"><p id="eventDate"></p> </div>
    </div>    
    <div class="row-fluid">
      <label><g:message code="common.description"/><span class="require">*</span></label>
      <div data-dojo-type="dijit.form.Textarea" data-dojo-props="trim: true" id="cloudMaintainDescription">   
      </div>
       <span class="validation" id="maintenanceErrorMsg"><g:message code="common.description.prompt"/></span>
      <p id="eventDescription"></p> 
    </div>
    <div class="row-fluid">
        <div class="pull-right">
            <button class="defaultbtn"  type="button" data-dojo-type= "dijit.form.Button" onclick="CalendarInfo.update()">              
                <g:message code="default.button.update.common"/>   
            </button>
             <button class="cancelbtn"  type="button" data-dojo-type= "dijit.form.Button" onclick="CalendarInfo.deleteItem()">                              
                <g:message code="default.button.delete.common"/>   
             </button> 
        </div>                
    </div>
  </div>
  <input type="hidden" id="currentItemId">
  </div>
</div>
           


                
              

              
