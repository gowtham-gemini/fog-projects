<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
        <li>/</li>   
        <li><a href="#/user/support/tickets"><g:message code="menu.user.support"/></a></li>
        <li>/</li>
        <li><g:message code="menu.user.tickets"/></li>
    </ul>
  </div>
</div>
<div class="row-fluid">
<div class="" id="">
	<div class="span1"></div>
  <div class="span12" id="ticketsPage"> 
      <form class="form-horizontal" id="adminTicketsForm" data-dojo-type="dijit.form.Form">
          <div class="span4 control-group field-box">
              <label for="ticketDepWidget" class="control-label">
                 <g:message code="common.department"/>
              </label>
              <div class="controls">
               <div id="ticketDepList"></div>                
              </div>
          </div>
          <div class="ip_info_help_menu span1">
              <i class="icon-info-sign" id="ticketDepartmentHelp"></i>
                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ticketDepartmentHelp' ,showDelay: 1"><g:message code="common.department"/></div>
        </div>
          <div class="span4 control-group field-box">
              <label for="ticketState" class="control-label">
                 <g:message code="common.status"/>
              </label>
              <div class="controls">
               <select class="valid" name="ticketPriority" data-dojo-type="dijit.form.Select" 
                      data-dojo-props="maxHeight: -1" id="ticketState" onchange="Tickets.getTicket()">
                <option value="ALL"><g:message code="common.ticket.status.all"/></option> 
                 <option value="OPEN"><g:message code="common.ticket.status.open"/></option>
                  <option value="IN_PROGRSS"><g:message code="common.ticket.status.inProgress"/></option>
                  <option value="ON_HOLD"><g:message code="common.ticket.status.onHold"/></option>
                  <option value="CLOSE"><g:message code="common.ticket.status.close"/></option>
              </select>
                
              </div>
          </div>
          <div class="ip_info_help_menu span1">
              <i class="icon-info-sign" id="ticketStatHelp"></i>
                <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ticketStatHelp',showDelay: 1"><g:message code="common.status"/></div>
        </div>
        <div class="span2 filter-block">
            <a href="#/user/support/addTicket" class="btn-flat success new-product span7"><g:message code="common.user.addTicket"/></a>        
        </div>
      </form> 
    </div>
</div>
</div>
<div id="">
  <div class="table-wrapper products-table">
        
        <div class="row-fluid">
          <div id="ticketGrid"></div>
        </div>
    </div>
</div>

<div class="span5" data-dojo-type="dijit.Dialog" id="closeTicketDialog" title="<g:message code="common.ticket.close.title"/>"class="">
  <input type="hidden" id="ticketId">
  <input type="hidden" id="departmentId">
  <input type="hidden" id="subject"> 
  <g:message code="common.ticket.closeReason"/>:<span class="require">*</span>
  <textarea style="width: 440px; height: 120px;"  rows="7" cols="100"  id="closeTicketContent"></textarea>
  <div style="pull-right">
    <button id="sendTicketButton" type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="Tickets.closeTicket()" id="" >
      <g:message code="common.ticket.status.close"/>
    </button>
    <img id="sendTicketLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="Tickets.closeTicketDialog()">
      <g:message code="common.cancel"/>
    </button> 
  </div>  
</div>
