<div class="row-fluid">
  <div class="span12 breadcrumbs">
    <ul>
      <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
      <li>/</li>
      <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
      <li>/<li>
      <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
      <li>/</li>
      <li><g:message code="common.lateFee"/></li>    
    </ul>
  </div>
</div>
<div class="new-user">
  <div class="row-fluid header">
    <!--<h3>Late Fee</h3>-->
  </div>
  <div class="row-fluid form-wrapper">
    <!-- left column -->
    <div class="span7 with-sidebar" id="lateFeeInfoPage">
      <div class="container">
        <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="lateFeeForm">
                  <!--<div class="span12 field-box"></div>-->
                   <div class="span12 field-box control-group">
                        <label for="lateFeeAmount" class="control-label settings_lable">                          
                          <g:message code="common.minAmount"/>
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                        <input type="text" 
                            data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.value.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.enterAmount" />', 
                            promptMessage:'<g:message code="common.enterAmount" />',regExp:'[0-9]{1,200}'" 
                            name="lateFeeAmount" id="lateFeeAmount">  
                    </div>
                   </div>
                    <div class="span12 field-box control-group">
                        <label for="lateFeePercentage" class="control-label settings_lable">                          
                          <g:message code="common.minPercentage"/>
                          <span class="require">*</span>
                        </label>
                      <div class="controls">
                        <input type="text" 
                            data-dojo-type="dijit.form.NumberTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.value.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.enterPercentage" />', 
                            promptMessage:'<g:message code="common.enterPercentage" />', constraints:{max: 100, pattern:'#.##'}" 
                            name="lateFeePercentage" id="lateFeePercentage">  
                    </div>
                    </div>
                   <div class="span12 field-box control-group">
                        <label for="lateFeeApplicableAmount" class="control-label settings_lable">                          
                          <g:message code="common.applicableAmount"/>
                          <span class="require">*</span>
                        </label>
                     <div class="controls">
                        <input type="text" 
                            data-dojo-type="dijit.form.NumberTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.value.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.enterAmount" />', 
                            promptMessage:'<g:message code="common.enterAmount" />', constraints:{max: 100000, pattern:'#.##'}" 
                            name="lateFeeApplicableAmount" id="lateFeeApplicableAmount">
                    </div>
                   </div>
                  <div class="pull-right">
<!--                    <input type="reset" value="Cancel" class="reset" onclick="LateFeeInfo.cancel()">
                    <span>OR</span>-->
                      <button id="lateFeeButton" class="defaultbtn"  type="button" data-dojo-type="dijit.form.Button" onclick="LateFeeInfo.add()"><g:message code="common.update"/></button>
                     <img id="lateFeeLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>    
                        
                    </div>
                </form>
            </div>
        </div> 
    <div class="span5">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span id="lateFeeAmountDescription"><g:message code="common.lateFeeMinAmount"/></span>
        </div>
        <div class="span12 field-box" >
          <span id="lateFeePercentageDescription"><g:message code="common.lateFeeMinPercent"/></span>
        </div>
        <div class="span12 field-box">
          <span id="lateFeeApplicableAmountDescription"><g:message code="common.lateFeeApplicableAmount"/></span>
        </div>
      </div>
    </div>
    </div>
</div>