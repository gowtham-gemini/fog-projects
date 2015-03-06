<div class="row">
  <div class="col-md-12 breadcrumbs">
    <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>   
        <li><g:message code="menu.admin.tickets"/></li>
    </ul>
  </div>
</div>
<div class="row">
  <div id="main-stats">
    <div class="row stats-row">
        <div class="col-md-4 stat">
            <div class="data">
                 <span  class="number" id="newTic">0</span>
                <g:message code="stat.newTickets"/>
            </div>
           
        </div>
        <div class="col-md-4 stat">
            <div class="data">
               <span  class="number" id="clientRep">0</span>
                <g:message code="stat.clientReply"/>
            </div>
            
        </div>
        <div class="col-md-4 stat">
            <div class="data">
               <span  class="number" id="staffRep">0</span>
               <g:message code="stat.staffReply"/>
            </div>            
        </div>
    </div>
</div>
</div>
<div class="row" style="margin-top: 25px;">   
  <div class="well well-large col-md-12">
    <div class="row">
        <div class="col-md-12" id="ticketsPage"> 
          <form class="form-horizontal" id="adminTicketsForm" data-dojo-type="dijit.form.Form">
              <div class="row">
              <div class="col-md-6 control-group field-box">
                  <label for="supportDepWidget" class="control-label">
                     <g:message code="common.department"/>
                     <span  class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="ticketDepList"></div>
                  </div>
                </div>  
                <div class="col-md-6 control-group field-box">
                  <label for="supportDepWidget" class="control-label">
                     <g:message code="common.status"/>
                     <span  class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="statusList"></div>
                  </div>
                </div>  
                </div>  
                <div class="row">
               <div class="col-md-6 control-group field-box">
                  <label for="supportDepWidget" class="control-label">
                     <g:message code="common.account"/>
                     <span  class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="ticaccountList"></div>
                  </div>
                </div>
                <div class="col-md-6 control-group field-box">
                    <label for="filterSubject" class="control-label">
                         <g:message code="common.subject"/>
                    </label>
                    <div class="controls">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="filterSubject"  data-dojo-props="placeHolder: '<g:message code="common.subject.placeHolder"/>',  invalidMessage:'<g:message code="common.subject.invalid"/>'">
                    </div>
                </div>
                </div>  
                <div class="pull-right col-md-2">
                    <button type="button" onclick="Tickets.ticketFilter()" class="defaultbtn" data-dojo-type="dijit.form.Button"><g:message code="common.filter"/></button>
                </div>
            </form> 
        </div>
    </div> 
  </div>
</div>
<div class="row">
      <div id="ticketGrid"></div>
</div> 