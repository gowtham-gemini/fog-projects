<div class="row">
  <div class="span12 breadcrumbs">
    <ul>
        <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
        <li>/</li>   
        <li><g:message code="common.notification"/></li>
        <li>/</li>
        <li><g:message code="common.topic"/></li>
    </ul>
  </div>
</div>
<div class="row">
<div class="" id="">
  <div class="span1"></div>
  <div class="span12"> 
    <form class="form-horizontal" id="addTopicForm" data-dojo-type="dijit.form.Form">
         <div class="span4 control-group field-box" id='topicPage'>
              <label for="topicName" class="control-label">
                 <g:message code="common.topic"/>
              </label>
              <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="topicName"
                    data-dojo-props="invalidMessage: 'Invalid name', required: 'true',
                    placeHolder: '<g:message code="common.name.placeHolder"/>',  invalidMessage:'<g:message code="common.value.invalid"/>'">          
              </div>
         </div>
         <div class="span2 filter-block">
            <button id="addTopicButton" type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="TopicInfo.addTopic()">
              <g:message code="common.add"/>
            </button>
         </div>
    </form> 
  </div>
</div>
</div>
<div class="row" style="margin-top: 50px;">
  <div class="table-wrapper products-table">
        <div class="row">
          <div id="topicGrid"></div>
        </div>
    </div>
</div>
<div class="span5" data-dojo-type="dijit.Dialog" id="editTopicDialog" title="<g:message code="common.edit"/>" class="">
  <input type="hidden" id="topicId">
  <div id="editPage">
  <g:message code="common.name"/>:<span class="require">*</span>
  <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="topicEditName"
    data-dojo-props="invalidMessage: 'Invalid name', required: 'true',
    placeHolder: '<g:message code="common.name.placeHolder"/>',  invalidMessage:'<g:message code="common.value.invalid"/>'">    
</div>
  <div style="pull-right">
    <button id="sendTicketButton" type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="TopicInfo.editTopic()" >
      <g:message code="common.edit"/>
    </button>
    <img id="sendTicketLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="TopicInfo.closeEditDialog()">
      <g:message code="common.cancel"/>
    </button> 
  </div>  
</div>
