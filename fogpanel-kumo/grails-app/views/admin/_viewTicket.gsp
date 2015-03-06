<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>   
        <li><a href="#/admin/support/tickets"><g:message code="menu.admin.tickets"/></a></li>
        <li>/</li>
        <li><g:message code="menu.ticket.viewTicket"/></li>
    </ul>
  </div>
</div>
<div class="row-fluid">   
  <div class="well well-large span12">
    <div class="row-fluid">
      <div class="span6">
        <div class="row-fluid">
          <div class="span6">
            <label class="headerLabel"><g:message code="common.account.name"/></label>
            <span id="ticAcName" class="labelValues"></span>
          </div>
          <div class="span6">
            <label class="headerLabel"><g:message code="common.ticket.id"/></label>
            <span id="ticId" class="labelValues"></span>
          </div>  
        </div>
        <div class="row-fluid">
          <div class="span6">
            <label class="headerLabel"><g:message code="common.status"/></label>
             <select class="valid" name="ticketPriority" data-dojo-type="dijit.form.Select" 
                       data-dojo-props="maxHeight: -1" id="ticketAdminState"> 
                  <option value="OPEN"><g:message code="common.ticket.status.open"/></option>
                  <option value="IN_PROGRSS"><g:message code="common.ticket.status.inProgress"/></option>
                  <option value="ON_HOLD"><g:message code="common.ticket.status.onHold"/></option>
                  <option value="CLOSE"><g:message code="common.ticket.status.close"/></option>
              </select>
          </div>
          <div class="span6">
            <label class="headerLabel"><g:message code="common.department"/></label>
            <div id="supportAdminDepList"></div>
        </div>         
        </div>
      </div>
    <div class="span6">
        <div class="row-fluid">
            <div class="span6">
                <label class="headerLabel"><g:message code="common.subject"/></label>
               <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="replyTicketSubject"
                 data-dojo-props="invalidMessage: 'invalid subject', required: 'true', disabled: 'true',
                   placeHolder: '<g:message code="common.subject.placeHolder"/>',  invalidMessage:'<g:message code="common.value.invalid"/>'">
            </div> 
            <div class="span6">
                <label class="headerLabel"><g:message code="common.ticket.postedDate"/></label>
                <span id="ticDate" class="labelValues"></span>
            </div>  
        </div>
    </div>
    </div> 
  </div>
</div>
<div class="row-fluid">   
  <div class="well well-large span12">
    <div class="row-fluid">
      <form class="form-horizontal" id="replyTicketForm" data-dojo-type="dijit.form.Form">
        <div id="cardDetails">
        <input type="hidden" id="ticketId">
        <div class="span12 control-group field-box">
                <label for="replyTicketContent" class="control-label">
                 <g:message code="common.content"/>
                <span class="require">*</span>
                </label>
                <div class="controls">
                  <textarea class="span10" rows="10" cols="200"  id="replyTicketContent"></textarea>
                </div>
          </div>
          <div class="span4 control-group field-box">
                <label for="supportAdminDefinedReplyWidget" class="control-label">
                <g:message code="common.ticket.preDefinedReply"/>
                </label>
                <div class="controls">
                  <div id="supportAdminDefinedReplyList"></div>
                </div>
          </div>
        </div>      
        <div class="span4 pull-right">
          <button id="viewTicketButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="ViewTickets.reply();">
          <g:message code="common.send"/>
        </button>
          <img id="viewTicketLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
        </div>
      </form>  
  </div>
</div>
</div>
<div class="row-fluid">
  <div id="chatDiv">
  </div>
</div>


