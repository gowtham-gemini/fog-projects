<div class="row-fluid">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/home/general">Account</a></li>
    <li>/</li>    
    <li>Login Info</li>    
  </ul>
</div>
</div>
<!--<g:render template="viewAccountInfo" />-->
<div class="row-fluid">   
<ul class="nav nav-tabs span12">
  <li>
    <a href="#/user/home/general">General</a>
  </li>
  
  <li>
    <a href="#/user/home/billing">Billing Info</a>
  </li>
    
     <li class="active">
      <a href="#/user/home/profile">Password</a>
    </li>    
</ul>
</div>
<div class="new-user">
  <div class="row-fluid header">
    <!--<h3>Login Info</h3>-->
    <input type="hidden" id="profileCurrentId">     
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span9 with-sidebar">
      <div class="container" id="profilePage">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="profileForm">  
            <div class="span12 field-box">
              <label for="oldPassword">Old Password:</label>
              <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                     id="oldPassword" data-dojo-props="promptMessage:'Enter Password', 
                     invalidMessage: 'Password do not match',required: 'true',
                     placeHolder: 'Old Password'"> 
            </div>
            <div class="span12 field-box">
              <label for="newPassword">New Password:</label>
              <input type="password"  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                     onkeyup="ProfileDetail.checkPasswordStrength(this.id)" onchange="ProfileDetail.checkPasswordStrength(this.id)" 
                     id="newPassword" data-dojo-props="promptMessage:'Enter Password', 
                     invalidMessage: 'Enter Password',required: 'true',
                     placeHolder: 'Password'">
            </div>
            <div class="span12 field-box">
              <label for="confirmPassword">Confirm Password:</label>
              <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                     onblur="ProfileDetail.confirmPass()"  id="confirmPassword" data-dojo-props="promptMessage:'Enter Password', 
                     invalidMessage: 'Password do not match',required: 'required',
                     placeHolder: 'Confirm Password'">
            </div>
            <div class="span11 field-box actions">
              <!--<input type="reset" value="Cancel" class="reset" onclick="">-->
              <!--<span>OR</span>-->
              <button type="button" data-dojo-type="dijit.form.Button" onclick="ProfileDetail.resetNewPassword()">Reset</button>
            </div>
          </form>
      </div>
    </div>
    <div class="span3">
      <div class="new_user_form inline-input">
        <div class="span12 field-box" >
          <div id="oldPasswordValidateMsg" class="validationErrorMsg">
            <span>Old password do not match</span>
          </div>
          
        </div>
        <div class="span12 field-box">
          <div id="password_strength" style="display: none; margin-top: -25px;float: right;">
            <div style="width: 150px; border: #CCCCCC 1px solid;margin-right: 58px">
              <div id="progress_bar" style="height: 2px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
              </div>
            <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;">Weak</span>
            <input type="hidden" name="strength_id" id="strength_id" value="1" />
          </div>
        </div>
        <div class="span12 field-box validationErrorMsg" id="passwordValidateMsg">
          <span>Password do not match</span>
        </div>
      </div>     
    </div>
  </div>
</div>






<!--<g:render template="accountSetup" />
<div class="row-fluid" id="accountProfilePage">
  
  <div class="" style="" id="generalProfileInfo">
    <div data-dojo-type="dijit.layout.TabContainer" id="profileTabContainer"
         class="span9" style="height: 400px;">
      <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme" 
           title="User Details" selected="true" id="userDetails" 
           onShow="ProfileDetail.getContentName(this)">
        <input type="hidden" id="currentId"/>
        <input type="hidden" id="currentCountryId"/>
        
        <div class="row-fluid">
          <div class="navbar">
            <div class="navbar-inner">
              <a class="brand">User Details...</a>
            </div>
          </div>
        </div>
        <div class="row-fluid">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" 
                id="profileUserDetailForm">
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">First Name</label>
              <div class="controls ">
                <span class="updatable" id="profileFirstNameLable">Amal</span>
                <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                       data-dojo-props="required: 'true', promptMessage: 'Enter fullName',
                       invalidMessage: 'invalid name', trim: 'true', propercase: true,
                       placeHolder: 'FullName'"
                       name="fullName" value="" id="profileFirstName">
              </div>
            </div>
            <div class="control-group generalContents">
              <label class="control-label" for="inputPassword">Last Name</label>
              <div class="controls">
                <span class="updatable" id="profileLastNameLable">Ranjith</span>
                <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                       data-dojo-props="required: 'true', promptMessage: 'Enter fullName',
                       invalidMessage: 'invalid name', trim: 'true', propercase: true,
                       placeHolder: 'FullName'"
                       name="fullName" value="" id="profileLastName"> 
              </div>
            </div>
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">Status</label>
              <div class="controls">
                <span class="updatable" id="profileSatusLable">Active</span>
                <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                       data-dojo-props=" promptMessage: 'Enter Satus',
                       placeHolder: 'Status'" name="" value="" id="profileSatus"> 
              </div>
            </div>
            <div class="control-group generalContents">
              <label class="control-label" for="inputEmail">Email</label>
               <div class="controls">
                 <span class="updatable" id="profileEmailLable">Email Address</span>
                 <input type="text" class="hide_lable" data-dojo-type="dijit.form.TextBox"
                        data-dojo-props="required:'true', promptMessage:'Enter email Address', 
                         invalidMessage:'Invalid Email Adrress', trim:'true', regExp: dojox.validate.regexp.emailAddress, 
                         placeHolder: 'email'" id="profileEmail">
               </div> 
             </div>
            
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Country</label>
               <div class="controls">
                 <span class="updatable" id="profileCountryLable">India</span>
                 <div id="profileCountry" class="hide_lable span4"></div>
                
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">State</label>
               <div class="controls">
                 <span class="updatable" id="profileStateLable">Tamil Nadu</span>
                 <div class="hide_lable span4" id="profileState"></div>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Phone</label>
               <div class="controls">
                 <span class="updatable">+91 9597454545 </span>
                 <input type="text" class="hide_lable" data-dojo-type="dijit.form.ValidationTextBox"
                        data-dojo-props=" promptMessage:'Enter Phone No',
                         invalidMessage:'invalid Phone',  regExp:'\\d{10}',
                         placeHolder: 'Your Phone No'">
               </div>
             </div>
             
           </form>
         </div>
         <div class="row-fluid">
           <a class="updatable offset4" onclick="ProfileDetail.edit()">Edit</a>
           <div class="hide_lable offset5" id="buttonGroup">
             <button type="button" data-dojo-type="dijit.form.Button" onclick="ProfileDetail.update()">Save</button>
            <button type="button" data-dojo-type="dijit.form.Button" onclick="ProfileDetail.cancelUser()">Cancel</button>
           </div>
         </div>
      </div>
      <div data-dojo-type="dijit.layout.ContentPane" class="menuItemTheme"
           title="Login Information" id="loginDetailPage" onShow="ProfileDetail.getContentName(this)">
        <div class="row-fluid">
           <div class="navbar">
             <div class="navbar-inner">
               <a class="brand">Login Information...</a>
             </div>
           </div>
         </div>
        <div class="row-fluid">
           <form class="form-horizontal" data-dojo-type="dijit.form.Form">
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Username</label>
               <div class="controls">
                 <span id="profileUserNameLable">amal.ranjith</span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputPassword">Last Login</label>
               <div class="controls">
                 <span> 29 Mar 2013 05:15:21 </span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Profile Type</label>
               <div class="controls">
                 <span>Master User</span>
               </div>
             </div>
             <div class="control-group generalContents">
               <label class="control-label" for="inputEmail">Login  s</label>
               <div class="controls">
                 <span>19</span>
               </div>
             </div>
             <div class="row-fluid">
          <a class="offset4 updatable" onclick="ProfileDetail.resetPassword()">Change Password</a>
        </div>
        <div class="control-group generalContents hide_lable passwordField">
          <label class="control-label" for="">Old Password</label>
          <div class="controls">
            <input type="password" data-dojo-type="dijit.form.TextBox">
          </div>
        </div>
             <div class="control-group generalContents hide_lable passwordField">
                    <label for="newPassword">New Password</label> 
                    <div class="controls">
                     <input type="password" value=""  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                            onkeyup="checkPasswordStrength(this.id)" onchange="checkPasswordStrength(this.id)" id="newPassword" data-dojo-props="promptMessage:'Enter Password', 
                         invalidMessage: 'Enter Password',required: 'required',
                         placeHolder: 'Password'">
                </div>
                
                   <div id="password_strength" style="display: none; margin-top: -25px;float: right;">
                  <div style="width: 150px; border: #CCCCCC 1px solid;margin-right: 58px">
                    <div id="progress_bar" style="height: 2px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
                  </div>
                  <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;">Weak</span>
                  <input type="hidden" name="strength_id" id="strength_id" value="1" />
                </div>
             
                  </div>
                  <div class="control-group generalContents hide_lable passwordField">
                    <label for="confirmPassword">Confirm Password</label>
                    <div class="controls">
                   <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                            onblur="confirmPass()"  id="confirmPassword" data-dojo-props="promptMessage:'Enter Password', 
                         invalidMessage: 'Password do not match',required: 'required',
                         placeHolder: 'Confirm Password'">
                    </div>
                    <a onclick="showPasswordGenerator()" id="showGeneratePasswordButton">Generate Password</a>
                  </div>
                
             <div class="row-fluid">
               <div class="hide_lable offset5" id="buttonGroup">
            <button type="button" data-dojo-type="dijit.form.Button" onclick="ProfileDetail.resetNewPassword()">Save</button>
            <button type="button" data-dojo-type="dijit.form.Button" onclick="ProfileDetail.cancelLogin()">Cancel</button>
           </div>
             </div>
           </form>
          <div data-dojo-type="dijit.Dialog" id="passwordGeneratorDialog" 
              title="Password Generator" style="color: black; width: 350px; 
              height: 150px">
            Password Generator.
            <div style="margin-left: 100px">
              <input type="text" id="passwordGenerator" data-dojo-type="dijit.form.TextBox">
                
              <button type="button" data-dojo-type= "dijit.form.Button"
                      onclick="generatePassword()" id="generatePasswordButton">
              Generate Password
              </button>
              <button id="usePasswordButton" data-dojo-type="dijit.form.Button"
                      onclick="useGeneratedPassword()">
                Use Password
              </button> 
              <button id="copy" data-dojo-type="dijit.form.Button"
                      onclick="copy();">
               copy
              </button> 
            </div>  
          </div>
         </div>
        
      </div>
    </div>
  </div>
  <div class="span3 rectContainerMedium">
  </div>
  <div class="row-fluid"><a class="offset1">Upload Your Photo</a></div>
</div>


  -->