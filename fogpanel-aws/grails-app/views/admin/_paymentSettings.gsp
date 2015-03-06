<div class="row-fluid"> 
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>    
            <li><a href="#/admin/settings/paymentGatway"><g:message code="common.paymentGateway"/></a></li>
            <li>/</li>  
            <li><g:message code="common.paypal"/></li>  
            <li>/</li>  
            <li><g:message code="common.processingFeeSetting"/></li>  

        </ul>
    </div>
</div>
<div class="row-fluid"> 
    <ul class="nav nav-tabs span12">
        <li>
            <a href="#/admin/settings/paypal"><g:message code="common.settings"/></a>
        </li>
        <li class="active">
            <a href="#/admin/settings/paymentConfig"><g:message code="common.processingFeeSetting"/></a>
        </li>    
    </ul>
</div>
<div  class="new-user">
<!--    <div class="row-fluid header">
        <h3>Processing Fee Setting</h3>
    </div>-->

    <div class="row-fluid form-wrapper" id="paymentSettingPage">
          <!-- left column -->
        <div class="span7 with-sidebar">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="paymentSettingForm">
                  <!--<div class="span12 field-box"></div>-->
                    <div class="span12 field-box control-group">
                        <label class="control-label settings_lable">                        
                            <g:message code="common.processingFeeType"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls"> 
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="ProcessingFee" id="includeProcessingFee" value="INCLUDE"/>
                            <label for="includeProcessingFee" class=""><g:message code="common.include"/></label> 
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="ProcessingFee" id="excludeProcessingFee" value="EXCLUDE"/> 
                            <label for="excludeProcessingFee" class=""><g:message code="common.exclude"/></label>  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="processingFeePercentage" class="control-label settings_lable">                          
                            <g:message code="common.processingFeePercetage"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls"> 
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                            data-dojo-props="required:'true', constraints:{max: 100, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                            promptMessage:'<g:message code="common.value.invalid"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="processingFeePercentage">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="processingFeeAmount" class="control-label settings_lable">                          
                            <g:message code="common.processingFeeAmount"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls"> 
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                            data-dojo-props="required:'true', constraints:{max: 10000, pattern:'#.##'}, placeHolder: '<g:message code="common.value"/>',
                            promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="processingFeeAmount"> 
                        </div>
                    </div>
                    <div class="pull-right">                     
  <!--                     <input type="reset" value="Cancel" class="reset" onclick="PaymentConfig.cancel()">  
                       <span>OR</span>-->
                        <button id="paymentButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="PaymentConfig.add()"><g:message code="common.update"/></button>
                        <img id="paymentLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>   


                    </div>
                </form>
            </div>
        </div> 
        <div class="span5">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id="includeProcessingFeeDescription"><g:message code="admin.paymentProcessingFeeTypeInfo"/></span>
                </div>
                <div class="span12 field-box">
                    <span id="processingFeePercentageDescription"><g:message code="admin.paymentPercentageInfo"/></span>
                </div>
                <div class="span12 field-box">
                    <span id="processingFeeAmountDescription"><g:message code="admin.paymentProcessingFeeAmountInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>


