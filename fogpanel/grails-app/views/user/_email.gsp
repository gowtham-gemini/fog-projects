<g:render template="accountSetup" />
<div class="row-fluid">
  <div data-dojo-type="dijit.layout.TabContainer" id="notificationTabContainer"
       class="span12" style="height: 400px;">
    <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme" 
         title="Email Notification" selected="true" id="emailNotificationInfo"
         onShow=""> 
      <div class="row-fluid">
           <div class="navbar">
             <div class="navbar-inner">
               <a class="brand">Email Notification</a>
             </div>
           </div>
         </div>
      <div class="row-fluid">
        <div id="notificationInfoGrid">
        </div>
      </div>
      <div class="row-fluid">
        
            <form class="navbar-form pull-left span12">
             
              <h4 class="control-label span6">Specify an additional email address to use for notifications</h4>
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="required:'true', promptMessage:'Enter email Address', 
                         invalidMessage:'Invalid Email Adrress', trim:'true', 
                         placeHolder: 'Your Email', regExp: dojox.validate.regexp.emailAddress" id="emailBox">
              <button type="button"  onclick="NotificationDetail.add()" data-dojo-type="dijit.form.Button">Add Email</button>
            </form>
       
      </div>
    </div>
    <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme"
       id="dummyNotification" data-dojo-props="disabled: 'true'"></div>
  </div>
</div>