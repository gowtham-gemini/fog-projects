<div class="row">
  <div class="col-md-12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account/invitation "><g:message code="common.invitation"/></a></li>
    <li>/</li>
    <li><g:message code="common.addInvitation"/></li>   
  </ul>
</div>
</div>
<div class="row header"></div>
<div class="row">
    <div class="pull-right">
        <p class="require hide_text col-md-8" id="invitationErrorMsg">Email already exist</p>
        <button class="defaultbtn col-md-1" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.addMore()">Add More</button> 
    </div>
</div>
<div class="row">    
    <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addInvitationForm">                   
        <div class="row"><div id="multipleInvitationDiv"></div></div>
        <div class="row col-md-1 field-box pull-right">                    
            <button id="addInvitationButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.addInvitationConfirm()"><g:message code="common.send"/></button>            
        </div>                              
    </form>                      
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="invitationLoader" class="col-md-6">
    <div class="row" style="display: block">
        <img src="images/configLoader.gif" class="col-md-1 spacing"/>
        <p class="message col-md-10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="adminAddInvitationConfirmDialog" title="<g:message code="common.addInvitation.title"/>" class="customDialgue">  
  <div class="col-md-4 dijitDialogueBackground">
    <p><g:message code="common.addInvitation.confirmMsg"/>.</p>    
    <div class="row">          
      <button id="" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.sendInvitation()"><g:message code="common.yes"/></button>
      <img class="hide_text" id="sendInviLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
      <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.cancelDialogu()"><g:message code="common.no"/></button>
    </div>
  </div>                     
</div>         
