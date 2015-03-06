<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
    <li>/<li>
    <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
    <li>/<li>
    <li><a href="#/admin/promotion"><g:message code="common.promotionalCode"/></a></li>
    <li>/</li>
    <li><g:message code="common.add"/></li>
  </ul>
</div>
</div>

<div  class="new-user">
  <div class="row-fluid header">
    <!--<h3>Add Promotion Code</h3>-->
  </div>
  <div class="row-fluid form-wrapper" id="promotionCodePage">
    <!-- left column -->
    <div class="span6 with-sidebar">
      <div class="container">
          <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="promotionCodeForm">
              <div class="span12 field-box control-group">
                  <label for="promotionCode" class="control-label">                    
                    <g:message code="common.code"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                   <input type="text" 
                        data-dojo-type="dijit.form.ValidationTextBox" 
                        data-dojo-props="invalidMessage: '<g:message code="common.invalidCode"/>',
                        required: 'required', placeHolder: '<g:message code="common.enterCode"/>', 
                        regExp: '[a-zA-Z0-9-]{4,20}', 
                        promptMessage:'<g:message code="common.enterCode"/>'" 
                        name="promotionCode" id="promotionCode"> 
                </div>
              </div>
              
              <div class="span12 field-box control-group">
                  <label for="promotionType" class="control-label">                    
                    <g:message code="common.type"/>:
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                      <select class="customSelectWidth" data-dojo-type="dijit.form.Select" id="promotionType">
                      <option value="FIXED">Fixed Amount</option>
                      <!--<option  value="PERCENTAGE">Percentage</option>-->
                  </select>
                </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="promotionValue" class="control-label">                    
                    <g:message code="common.value"/>
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                      <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="required:'true', constraints:{pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                       promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="promotionValue">  
                </div>
              </div>
            <div class="span12 field-box control-group">
                  <label for="promotionStartDate" class="control-label">
                     <g:message code="common.startDate"/>:
                  </label>
              <div class="controls">
                      <input type="text" name="date1"  data-dojo-id="promotionStartDate" id="promotionStartDate" onchange="promotionEndDate.constraints.min = arguments[0];"
                data-dojo-type="dijit.form.DateTextBox"
                data-dojo-props="constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
              </div>
              </div>
            <div class="span12 field-box control-group">
                  <label for="promotionEndDate" class="control-label">
              <g:message code="common.endDate"/>:
            </label>
              <div class="controls">
            <input type="text" name="date1" id="promotionEndDate"  data-dojo-id="promotionEndDate" onchange="promotionStartDate.constraints.max = arguments[0];"
                  data-dojo-type="dijit.form.DateTextBox" data-dojo-props="constraints:{datePattern:'yyyy-MM-dd', strict:'true'}"/>
              </div>
              </div>
<!--            <div class="span12 field-box">
                  <label for="applyTo" class="control-label">
              <span class="require">*</span>
              Apply to:
            </label>
            <select id="applyTo" multiple="multiple" ></select>
              </div>-->
            <div class="span12 field-box control-group">
                  <label for="promotionMaximumUses" class="control-label">
              <g:message code="common.maxUse"/>:
            </label>
              <div class="controls">
            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                     data-dojo-props="placeHolder: '<g:message code="common.maxUse"/>',
                       promptMessage:'<g:message code="common.maxUse"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" 
                       id="promotionMaximumUses">  
              </div>
              </div>            
<!--            <div class="span12 field-box">
                  <label for="promotionCodeUses" class="control-label">
              Uses:
            </label>
            <span id="promotionCodeUses"></span>  
              </div>-->
            <div class="span12 field-box control-group">
                  <label for="promotionNotes" class="control-label">              
                <g:message code="common.adminNotes"/>:
              <span class="require">*</span>
            </label>
              <div class="controls">
            <textarea data-dojo-type="dijit.form.SimpleTextarea"id="promotionNotes" data-dojo-props="trim: 'true'"></textarea>
              </div>
            </div>
            
            
            <div class="pull-right">               
<!--                  <input type="reset" value="Cancel" class="reset" onclick="PromotionCodeManagement.cancel()">
                  <span>OR</span>-->
                  <button id="promocodeButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="PromotionCodeManagement.add()"><g:message code="common.add"/></button>
                  <img id="promoCodeLoader" style="display: none; float: right" src="${resource(dir: 'images')}/preloader_circle.gif" 
                          alt="reset" height="20" width="20">
              </div>
          </form>
      </div>
    </div>
    <div class="span6">
      <div class="new_user_form inline-input">
        <div class="span12 field-box">
          <span><g:message code="admin.promotionCodeInfo"/></span>
        </div>
        <div class="span12 field-box" id ="fixedDayContainer">
          <span><g:message code="admin.promotionTypeInfo"/></span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span><g:message code="admin.promotionValueInfo"/></span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span><g:message code="admin.promotionStartDateInfo"/></span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span><g:message code="admin.promotionEndDateInfo"/></span>
        </div>
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span><g:message code="admin.promotionMaxUseInfo"/></span>
        </div>
<!--        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span>Uses</span>
        </div>-->
        <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
          <span><g:message code="admin.promotionNotesInfo"/></span>
        </div>
      </div>
    </div>
  </div>
</div>