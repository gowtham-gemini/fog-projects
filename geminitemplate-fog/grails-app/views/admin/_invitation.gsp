<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row">
  <div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.accounts"/></a></li>
    <li>/</li>
    <li><g:message code="common.invitation"/></li>   
  </ul>
</div>
</div>

<!--<div id="pad-wrapper">-->
<div class="table-wrapper products-table">
    <div class="row head">
        <div class="col-md-12">
            <input type="hidden" id="currentInvitationID">
            <!--<h4>Firewall</h4>-->
        </div>
    </div>

    <div class="row filter-block">
        <div class="pull-right">
          <a class="btn-flat success new-product" href="#/admin/account/addInvitation">+ <g:message code="common.sendInvitation"/></a>
        </div>
    </div>

    <div class="row">
      <div id="invitationGridList">  
      </div>
      <div class="alert alert-info hide" id="noInvitationMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.message.noInvitation"/>
      </div>
    </div>
</div>
<!--</div>-->
<div data-dojo-type="dijit.Dialog" class="full_loader" id="invitationInfoLoader" class="col-md-6">
    <div class="row" style="display: block">
        <img src="images/configLoader.gif" class="col-md-1 spacing"/>
        <p class="message col-md-10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div> 
<div data-dojo-type="dijit.Dialog" id="resendAddInvitationConfirmDialog" title="<g:message code="common.resendInvitation.title"/>" class="customDialgue">  
  <div class="col-md-4 dijitDialogueBackground">
    <p><g:message code="common.resendInvitation.confirmMsg"/>.</p>    
    <div class="row">          
      <button id="" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InvitationInfo.resetMail()"><g:message code="common.yes"/></button>
      <img class="hide_text" id="sendInviLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
      <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="InvitationInfo.cancelDialogu()"><g:message code="common.no"/></button>
    </div>
  </div>                     
</div>
  