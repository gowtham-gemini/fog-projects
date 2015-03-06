<g:render template="accountSetup" />
<div class="row">
  <div data-dojo-type="dijit.layout.TabContainer" id="notificationTabContainer"
       class="col-md-12" style="height: 400px;">
    <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme" 
         title="Email Notification" selected="true" id="emailNotificationInfo"
         onShow=""> 
      <div class="row">
           <div class="navbar">
             <div class="navbar-inner">
               <a class="brand">Email Notification</a>
             </div>
           </div>
         </div>
      <div class="row">
        <div id="notificationInfoGrid">
        </div>
      </div>
      <div class="row">
        
            <form class="navbar-form pull-left col-md-12">
             
              <h4 class="control-label col-md-6">Specify an additional email address to use for notifications</h4>
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