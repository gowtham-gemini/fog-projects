<g:render template="accountSetup" />
<div class="row-fluid" id="accountContactPage">
  
  <div data-dojo-type="dijit.layout.TabContainer" id="contactTabContainer"
       class="span12" style="height: 400px;" onClick="">
       <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme" 
            title="Contact Info" selected="true" id="companyInfo" onShow="ContactDetail.getContentName(this)">  
         <input type="hidden" id="currentContactId">
         <div class="row-fluid">
           <div class="navbar">
             <div class="navbar-inner">
               <a class="brand">Contact Info...</a>
             </div>
           </div>
         </div>
         <div class="row-fluid">
           <form class="form-horizontal" data-dojo-type="dijit.form.Form" 
                 id="contactUserDetailForm">
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Country</label>
               <div class="controls ">
                 <span class="updatable" id="contactCountryLable">India</span>
                 <div class="hide_lable span4" id="contactCountry"> </div>            
               </div>
                
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">State</label>
               <div class="controls ">
                 <span class="updatable" id="contactStateLable">Tamil Nadu</span>
                 <div class="hide_lable span4" id="contactState"> </div>             
               </div>
                
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Street Address</label>
               <div class="controls ">
                 <span class="updatable" id="contactStreetLable">no-35, Marudakutty layout</span>
                 <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                        data-dojo-props="required: 'true', promptMessage: 'Enter Street Address',
                         invalidMessage: 'invalid Street Address', trim: 'true', 
                         placeHolder: 'Street Address'"
                         name="fullName" id="contactStreet">              
               </div>
                
             </div>
             
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">City/Suburb</label>
               <div class="controls ">
                 <span class="updatable" id="contactCityLable">Coimbatore</span>
                 <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                        data-dojo-props="required: 'true', promptMessage: 'Enter City',
                         invalidMessage: 'invalid City', trim: 'true',
                         placeHolder: 'City'"
                         name="fullName" id="contactCity">              
               </div>
                
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Zip Code</label>
               <div class="controls ">
                 <span class="updatable" id="contactZipLable">641027</span>
                 <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                        data-dojo-props="required: 'true', promptMessage: 'Enter Zip Code',
                         invalidMessage: 'invalid Zip Code', trim: 'true', 
                         placeHolder: 'Zip Code'"
                         name="fullName" id="contactZip">              
               </div>
                
             </div>
             <div class="row-fluid">
           <a class="updatable offset3" onclick="ContactDetail.edit()">Edit</a>
           <div class="hide_lable offset3" id="buttonGroup">
             <button type="button" data-dojo-type="dijit.form.Button" onclick="ContactDetail.update()">Save</button>
            <button type="button" data-dojo-type="dijit.form.Button" onclick="ContactDetail.cancel()">Cancel</button>
           </div>
         </div>
           </form>
         </div>
       </div>
       <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme" 
            title="User Info" selected="true" id="userInfo" onShow="ContactDetail.getContentName(this)">
          <div class="row-fluid">
           <div class="navbar">
             <div class="navbar-inner">
               <a class="brand">User Info...</a>
             </div>
           </div>
         </div>
         <div class="row-fluid">
           <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="userForm">
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">First Name</label>
               <div class="controls">
                 <span id="contactFirstName">Amal</span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputPassword">Last Name</label>
               <div class="controls">
                 <span id="contactLastName">Ranjith</span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Username</label>
               <div class="controls">
                 <span id="contactUserName">amal.ranjith</span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Email</label>
               <div class="controls">
                 <span id="contactEmail">assistanz.com</span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Phone</label>
               <div class="controls">
                 <span id="contactPhoneNo">91-9597454545</span>
               </div>
             </div>
             
           </form>
       </div>
  </div>
</div>
</div>

