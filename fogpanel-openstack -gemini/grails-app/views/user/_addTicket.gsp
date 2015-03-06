<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
        <li>/</li>   
        <li><a href="#/user/support/tickets"><g:message code="menu.user.support"/></a></li>
        <li>/</li>
        <li><g:message code="common.user.addTicket"/></li>
    </ul>
  </div>
</div>
<div class="row-fluid form-wrapper">
<!--  <div class="span4 createvm-banner">
  	<img src="${resource(dir: 'images')}/cloud_cc_icon.png" height="256" width="300">
  </div>-->
  <div class="span6" id="addTicketPage"> 
  <form class="form-horizontal" id="addTicketForm" data-dojo-type="dijit.form.Form">
        <div id="cardDetails">
			<div class="span12"></div>
          <div class="span12 control-group field-box">
              <label for="supportDepWidget" class="control-label">
                 <g:message code="common.department"/>
                <span class="require">*</span>
              </label>
              <div class="controls">
                <div id="supportDepList"></div>
              </div>
          </div>
          <div class="span12 control-group field-box">
              <label for="ticketPriority" class="control-label">
                <g:message code="common.priority"/>
                <span class="require">*</span>
              </label>
              <div class="controls">
                <select class="valid" name="ticketPriority" data-dojo-type="dijit.form.Select" 
                      data-dojo-props="maxHeight: -1" id="ticketPriority">
                <option value="LOW"><g:message code="common.low"/></option>
                <option value="NORMAL"><g:message code="common.normal"/></option>
                <option value="HIGH"><g:message code="common.high"/></option>
              </select>
              </div>
          </div>
          <div class="span12 control-group field-box">
              <label for="addTicketSubject" class="control-label">
                <g:message code="common.subject"/>
                <span class="require">*</span>
              </label>
              <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="addTicketSubject"
                 data-dojo-props="invalidMessage: 'invalid subject', required: 'true',
                   placeHolder: '<g:message code="common.subject.placeHolder"/>',  invalidMessage:'<g:message code="common.value.invalid"/>'">
              </div>
          </div>
          <div class="span12 control-group field-box">
              <label for="addTicketContent" class="control-label">
                <g:message code="common.content"/>
                <span class="require">*</span>
              </label>
              <div class="controls">
                <textarea class="span12" rows="12" cols="250"  id="addTicketContent"></textarea>
              </div>
          </div>
        </div>      
        <div class="pull-right">
          <button id="addTicketButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddTicket.add();">
          <g:message code="common.send"/>
        </button>
          <img id="addTicketLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
          <button id="cancelTicketButton" class="cancelbtn" data-dojo-type="dijit.form.Button"  onclick="AddTicket.cancel();">
           <g:message code="common.cancel"/>
        </button>
      </div>
      </form> 
  </div>
</div>
