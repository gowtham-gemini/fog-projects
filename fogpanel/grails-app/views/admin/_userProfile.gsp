<h3>User Profile</h3>
<a href="#/admin/dashboard">Back to Dashboard</a> 
<a href="#" onclick="DashboardUserProfile.gotoResetPage();">Reset Password</a>
<div class="alert alert-danger" id="errorInfo">
  <span>
    The connection with the server was terminated
  </span>
</div>
<form id="userProfileForm" data-dojo-type="dijit.form.Form">
  <div class="span8" id="zonePageContent">
    <div  id="userProfile">
    <div class="element"> 
      <label for="userProfileName">
        User Name:
      </label>
      <span id="userProfileName">User Name</span>
    </div>
      <div class="element"> 
        <label for="userProfileApiKey">
          Api Key:
        </label>
        <span id="userProfileApiKey"></span>
      </div>
      <div class="element"> 
        <label for="userProfileSecretKey">  
          Secret Key:
        </label>
        <span id="userProfileSecretKey"></span>
      </div>
    </div>
    <div id="userPasswordField" style="display: none">
      <div data-dojo-type = "dojox.form.PasswordValidator" 
            name="password" data-dojo-props = "" id="passwordField">
        <div class="element">
          <label>New Password</label>
          <input type="password" pwType="new">
        </div>
        <div class="element">
          <label>Confirm Password</label>
          <input type="password" pwType="verify">
        </div>
      </div>
      <div class="buttonGroup">
        <button type="reset" data-dojo-type="dijit.form.Button"
                id="resetButton" onclick="DashboardUserProfile.resetPassword()">
          Reset
        </button>
      </div>
    </div>
  </div>
</form>
<span id="currentUser"></span>
<div class="span4" id ="userProfileListItems">
  <div id="userProfileList">    
  </div>
</div>