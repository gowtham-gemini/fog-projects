<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>
            <li><g:message code="common.paymentSettings"/></li>    
        </ul>
    </div>
</div>
<div id="" class="new-user">
    <div class="row-fluid header"></div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span6 with-sidebar">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="paymentCardConfigForm">            
                    <div id="paymentCardConfigFormFormPage">
                        <div class="span12 field-box control-group">
                            <label for="creditCardEnabled" class="control-label settings_lable"><g:message code="common.cardProcessingEnable"/>:</label>
                            <div class="controls">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="creditCardEnabled" onclick="PaymentCardConfig.enableCreditCard()">  
                            </div>
                        </div>
                        <div id="cardProDiv" style="display: none;"> 
                            <div class="span12 field-box control-group">
                                <label for="signupCardVerificationEnabled" class="control-label settings_lable"><g:message code="common.cardProcessingEnableOnSignup"/>:</label>
                                <div class="controls">
                                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="signupCardVerificationEnabled" onclick="PaymentCardConfig.enableCreditCardSignup()">  
                                </div>
                            </div>
                            <div class="span12 field-box control-group" id="createVMCardDiv" style="display: none;">
                                <label for="vmCardVerificationEnabled" class="control-label settings_lable"><g:message code="common.cardProcessingEnableOnCreateVM"/>:</label>
                                <div class="controls">
                                    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false, disabled: true " id="vmCardVerificationEnabled">  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">            
                        <button id="paymentCardConfigButtton" class="defaultbtn" type="button" 
                        data-dojo-type="dijit.form.Button"  onclick="PaymentCardConfig.update()"><g:message code="common.update"/></button>
                        <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>   
                    </div>
                </form>
            </div>
        </div> 
        <div class="span6">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id=""><g:message code="admin.cardProcessingEnableInfo"/></span>
                </div>
                <div class="span12 field-box" id="signupCardDis" style="display: none;">
                    <span id=""><g:message code="admin.cardProcessingEnableOnSignupInfo"/></span>
                </div> 
                <div class="span12 field-box" id="createVmCardDis" style="display: none;">
                    <span id=""><g:message code="admin.cardProcessingEnableOnCreateVMInfo"/></span>
                </div>  
            </div>
        </div>
    </div>
</div>