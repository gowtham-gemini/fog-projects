<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>
            <li><g:message code="common.billingTypeSettings"/></li>    
        </ul>
    </div>
</div>
<div id="" class="new-user">
    <div class="row header"></div>
    <div class="row form-wrapper">
      <!-- left column -->
        <div class="col-md-6 with-sidebar">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="billingTypeConfigForm">            
                    <div id="billingTypeConfigFormFormPage">
                        <div class="col-md-12 field-box control-group">
                            <label for="" class="control-label settings_lable"><g:message code="common.hourlyBillingEnable"/>:</label>
                            <div class="controls">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: true, disabled: true" id="">  
                            </div>
                        </div>
                        <div class="col-md-12 field-box control-group">
                            <label for="monthlyEnabled" class="control-label settings_lable"><g:message code="common.monthlyBillingEnable"/>:</label>
                            <div class="controls">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: true" id="monthlyEnabled">  
                            </div>
                        </div>
                        <div class="col-md-12 field-box control-group" id="usageCalcTypeValue">
                            <label class="control-label settings_lable"><g:message code="common.usageCalcType"/>:</label>
                              <div class="controls">
                                  <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" name="usageCalcType" id="usageTypeActual" value="ACTUAL"/> 
                                  <label for="usageTypeActual" style="margin-left: 10px;"><g:message code="common.usageTypeActual"/></label>
                                  <input type="radio" data-dojo-type="dijit.form.RadioButton" name="usageCalcType" id="usageTypeHourly" value="HOURLY"/>
                                  <label for="usageTypeHourly" style="margin-left: 10px;"><g:message code="common.usageTypeHourly"/></label> 
                              </div>
                        </div>
                    </div>
                    <div class="pull-right">            
                        <button id="billingTypeConfigButtton" class="defaultbtn" type="button" 
                        data-dojo-type="dijit.form.Button"  onclick="BillingTypeConfig.update()"><g:message code="common.update"/></button>
                        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>   
                    </div>
                </form>
            </div>
        </div> 
        <div class="col-md-6">
            <div class="new_user_form inline-input">
                <div class="col-md-12 field-box">
                     <span  id=""><g:message code="admin.hourlyBillingEnableInfo"/> </span>
                </div>
                <div class="col-md-12 field-box">
                     <span  id=""><g:message code="admin.monthlyBillingEnableInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>