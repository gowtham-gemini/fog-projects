<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
        <li>/</li>   
        <li><a href="#/user/support/tickets"><g:message code="menu.user.support"/></a></li>
        <li>/</li>
        <li><a href="#/user/support/tickets"><g:message code="menu.user.tickets"/></a></li>
        <li>/</li>
        <li><g:message code="menu.ticket.viewTicket"/></li>
    </ul>
  </div>
</div>
<div class="row-fluid">   
  <div class="well well-large span12 viewticket_detail_info">
    <div class="row-fluid form-horizontal">
      <div class="span6">
        <div class="row-fluid">
          <div class="span6 viewticket_detail_section">
            <label class="headerLabel"><g:message code="common.account.name"/></label>
            <span id="ticAcName" class="labelValues"></span>
          </div>
          <div class="span6 viewticket_detail_section">
            <label class="headerLabel"><g:message code="common.ticket.id"/></label>
            <span id="ticId" class="labelValues"></span>
          </div>        
        </div>
        <div class="row-fluid">
            <div class="span6 viewticket_detail_section">
                <label class="headerLabel"><g:message code="common.state"/></label>
                <span id="ticState" class="labelValues viewticket_detail_status_open"></span>
            </div>
            <div class="span6 viewticket_detail_section">
                <label class="headerLabel control-label"><g:message code="common.department"/></label>
				<div class="controls">
                <div id="supportAdminDepList"></div>
				</div>
            </div>         
        </div>
      </div>
      <div class="span6">
        <div class="row-fluid">
            <div class="span12 viewticket_detail_section">
                <label class="headerLabel control-label"><g:message code="common.subject"/></label>
				<div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="replyTicketSubject"
                 data-dojo-props="invalidMessage: '<g:message code="common.subject.invalid"/>', required: 'true', disabled: 'true',
                   placeHolder: '<g:message code="common.subject.placeHolder"/>'">
				</div>
            </div>
            <div class="row-fluid">
                <div class="span7  viewticket_detail_section">
                    <label class="headerLabel"><g:message code="common.ticket.postedDate"/></label>
                    <span id="ticDate" class="labelValues"></span>
                </div>
                <div class="span5  viewticket_detail_section">
                    <label class="headerLabel control-label"><g:message code="common.priority"/></label>
                    <div class="controls">
                        <select class="valid" name="userTicketPriority" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: -1" id="userTicketPriority">
                          <option value="LOW"><g:message code="common.low"/></option>
                          <option value="NORMAL"><g:message code="common.normal"/></option>
                          <option value="HIGH"><g:message code="common.high"/></option>
                        </select>
                    </div>
                </div>
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
                <textarea class="span11" rows="10" cols="200"  id="replyTicketContent"></textarea>
              </div>
          </div>
        </div>      
        <div class="span2 offset10">
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

