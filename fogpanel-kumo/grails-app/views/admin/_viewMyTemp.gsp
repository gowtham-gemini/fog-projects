<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/admin/computation/services">Services</a></li>
      <li>/</li>
      <li><a href="#/admin/template">Template</a></li>
      <li>/</li>
      <li><a href="#/admin/template/userTemplate">User Template</a></li>
      <li>/</li>
      <li><a href="#/admin/template/userTemplate" id="currentMyTempName"></a></li>
      <li>/</li>
      <li>View</li>
    </ul>
  </div>
</div>
<div id="pad-wrapper" class="new-user">
  <div class="row-fluid header">
    <h3 id="muTempOsType">View User Template</h3>  
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span9 with-sidebar" id="appOsTempPage">
      <div class="container">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="myTempEditForm">
              <div class="span12 field-box">
                  <label for="dashboardEditOsName" class="control-label">
                    <span class="require">*</span>
                    Name: 
                  </label>
                   <input type="text"
                          data-dojo-type="dijit.form.ValidationTextBox" 
                          data-dojo-props="invalidMessage: 'invalid Template Name',
                          required: 'required', placeHolder: 'Enter Template Name', 
                          propercase: true" 
                          name="templateName" id="EditUserTempName">
              </div>
              <div class="span12 field-box">
                 <label for="editOsTempDescription" class="control-label">
                   <span class="require">*</span>
                   Description:
                 </label>
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="EditUserTempDescription"
                      data-dojo-props="invalidMessage: 'invalid Description Name',
                      required: 'required', placeHolder: 'Enter Description Name', 
                      propercase: false" >  
              </div>
            <div class="span12 field-box">
                  <label for="templateCost" class="control-label">
                    Cost:
                  </label>
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
       data-dojo-props="constraints:{max: 100, pattern:'#.##'}, placeHolder: 'value',
         promptMessage:'Value', invalidMessage:'Invalid Value.'" id="EditUserTempCost">  
              </div>
              <div class="span12 field-box">
                  <label for="dashboardTempURL" class="control-label">
                    <span class="require">*</span>
                    URL: 
                  </label>
                <span id="editUserTempUrl"></span>
              </div>
                <div class="span12 field-box">
                  <label for="dashboardTempZone" class="control-label">
                    <span class="require">*</span>
                    Zone: 
                  </label>
                  <span id="editUserTempZone"></span>
              </div>
                <div class="span12 field-box">
                  <label for="dashboardTempHypervisor" class="control-label">
                    <span class="require">*</span>
                    Hyperviser: 
                  </label>
                  <span id="editUserTempHypervisor"></span>
              </div>
                <div class="span12 field-box">
                  <label for="dashboardTempFormat" class="control-label">
                    <span class="require">*</span>
                    Format: 
                  </label>
                  <span id="editUserTempFormat"></span>
              </div>
                
                <div class="span12 field-box">
                  <label for="dashboardTempOsCategoryList" class="control-label">
                    <span class="require">*</span>
                    Os Category: 
                  </label>
                  <span id="editUserTempOsCategory"></span>
              </div>
                <div class="span12 field-box">
                  <label for="dashboardTempOsType" class="control-label">   
                    <span class="require">*</span> 
                    OS Type:     
                  </label>
                  <span id="editUserTempOsType"></span>
              </div>
                <div class="span12 field-box">
                  <label for="editTempOsPasswordEnabled" class="control-label">
                    Password Enabled:
                  </label>
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="EditUserTempPasswordEnabled">
              </div>
                <div class="span12 field-box">
                   <label for="editTempOsExtractable" class="control-label">
                     Extractable:
                   </label>
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="EditUserTempExtractable">
              </div>
                <div class="span12 field-box">
                  <label for="editTempOsPublic" class="control-label">
                    Public:
                  </label>
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="EditUserTempPublic">
              </div>
                <div class="span12 field-box">
                  <label for="editTempOsFeatured" class="control-label">Featured:</label>
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="EditUserTempFeatured">
              </div>
            <div class="span11 field-box actions">                            
<!--                  <input type="reset" value="Cancel" class="reset" >
                  <span>OR</span>-->
                  <button type="button" data-dojo-type="dijit.form.Button" onclick="ViewsMyTemplate.update()">Update</button>
              </div>
          </form>
      </div>
  </div>    
  <div class="span3">
<!--      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="editTaxNameLabel">Name of the Tax</span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span id="editDescriptionLabel">A detail description about the tax</span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span id="editTaxPercentageLabel">The allocated Percentage(%) of the tax</span>
        </div>
      </div>-->
    </div>
                </div>
            </div>