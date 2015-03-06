<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account/invitation "><g:message code="common.invitation"/></a></li>
    <li>/</li>
    <li><g:message code="common.addInvitation"/></li>   
  </ul>
</div>
</div>
<div class="row-fluid header"></div>
<div class="row-fluid">
    <div class="pull-right">
        <p class="require hide_text span8" id="invitationErrorMsg">Email already exist</p>
        <button class="defaultbtn span1" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.addMore()">Add More</button> 
    </div>
</div>
<div class="row-fluid">    
    <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addInvitationForm">                   
        <div class="row-fluid"><div id="multipleInvitationDiv"></div></div>
        <div class="row-fluid span1 field-box pull-right">                    
            <button id="addInvitationButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.addInvitationConfirm()"><g:message code="common.send"/></button>            
        </div>                              
    </form>                      
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="invitationLoader" class="span8">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>


<div data-dojo-type="dijit.Dialog" id="adminAddInvitationConfirmDialog" title="<g:message code="common.addInvitation.title"/>" class="customDialgue">  
  <div class="span4 dijitDialogueBackground">
    <p><g:message code="common.addInvitation.confirmMsg"/>.</p>    
    <div class="row-fluid">          
      <button id="" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.sendInvitation()"><g:message code="common.yes"/></button>
      <img class="hide_text" id="sendInviLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
      <button class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddInvitationInfo.cancelDialogu()"><g:message code="common.no"/></button>
    </div>
  </div>                     
</div>         
