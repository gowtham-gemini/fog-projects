<div class="row-fluid" style="display: none;">   
<div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li class="current"><a href="#/admin/account/summary"></a></li>    
    <li>/</li>
    <li><g:message code="menu.user.billing.payments"/></li>    
  </ul>
</div>
</div>
<div class="row-fluid" style="display: none;">   
<ul class="nav nav-tabs span12">
  <li>
    <a href="#/admin/account/summary/"><g:message code="common.summary"/></a>
  </li>
  <li>
      <a href="#/admin/account/infrastructure/"><g:message code="menu.admin.cloud.infrastructure"/></a>
    </li>
  <li>
    <a href="#/admin/account/invoice/"><g:message code="menu.user.billing.invoice"/></a>
  </li>
    
     <li class="active">
      <a href="#/admin/account/payment/"><g:message code="menu.user.billing.payments"/></a>
    </li>
     <li>
     <a href="#/admin/account/recurringItem/"><g:message code="common.items"/></a>
    </li>
</ul>
</div>
<div class="new-user">
    <div class="row-fluid">
        <div class="row-fluid filter-block">
            <div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="paymentCurrencyValue"></span></div>  
            <div class="pull-right" id="addPaymentBtn" style="margin-top: 10px;">
              <button id="" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="PyamentsInfo.loadManualPaymentForm();">
                  <g:message code="common.addPayment"/>
              </button>      
            </div>
        </div>  
    </div>
    <div class="row-fluid">
      <div class="alert alert-info overflowLabel" id="noInvoiceMsgBox">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.noInvoiceMsg"/>
      </div>    
  </div> 
</div>
<div class="row-fluid form-wrapper" id="paymentTotalDataDiv" style="margin-top: 10px;">
    <div id="paymentGrid">
    </div>
    <div class="alert alert-info hide" id="noUserPaymentMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.billing.noPayment"/>
    </div>
  </div>
  <div id="manualPaymentDiv" class="row-fluid form-wrapper">
      <div class="new-user"> 
          <div class="row-fluid form-wrapper">   
              <div class="span6">  
                  <form id="manualPayForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
                      <div class="row-fluid" id="manualPaymentFormPage">  
                          <div class="span12 field-box control-group"> 
                            <label for="acHolderName" class="control-label settings_lable"> <g:message code="common.payment.name"/></label>
                            <div class="controls">
                               <span id="acHolderName"></span>  
                               <span id="acHolderACNo" style="display:none;"></span>  
                            </div>
                          </div> 
                          <div class="span12 field-box control-group"> 
                            <label for="amount" class="control-label settings_lable"> <g:message code="common.payment.paymentAmount"/><span class="require">*</span></label>
                            <div class="controls">
                               <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                                     data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.00'}, placeHolder: '<g:message code="common.value"/>',
                                     promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="amount">  
                            </div>
                          </div>
                          <div class="span12 field-box control-group"> 
                            <label for="TransDate" class="control-label settings_lable"> <g:message code="common.transactionDate"/><span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <input type="text" id="TransDate" data-dojo-id="TransDate" 
                                data-dojo-type="dijit.form.DateTextBox"
                                data-dojo-props="required:'true', 
                                placeHolder: '<g:message code="common.date.placeholder"/>',
                                promptMessage:'<g:message code="common.date.prompt"/>', 
                                invalidMessage:'<g:message code="common.date.invalid"/>', 
                                constraints:{datePattern:'dd/MM/yyyy', strict:'true'}"/>
                            </div>
                          </div>
                          <div class="span12 field-box control-group"> 
                            <label for="payDescription" class="control-label settings_lable"> <g:message code="common.description"/> </label>                           
                            <div class="controls">
                                <div data-dojo-type="dijit.form.Textarea" id="payDescription">
                                </div>
                            </div>
                          </div>
                          <div class="pull-right">  
                            <button class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="PyamentsInfo.submitManualPaymentForm();">
                                  <g:message code="common.submit"/>
                            </button>
                            <button data-dojo-type="dijit.form.Button" onclick="PyamentsInfo.cancelManualPayment();" type="reset" class="cancelbtn">
                                <g:message code="common.cancel"/>
                            </button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
      </div>
  </div>
<div data-dojo-type="dijit.Dialog" id="paymentLoader" class="span6">
  <div class="row-fluid" id="processPaymentMessage" style="display: block">
    <img src="images/configLoader.gif" class="span1 spacing"/>
    <p class="message span10"> <g:message code="common.payment.processing" /></p>
  </div>
<!--    <div class="row-fluid hide_lable" id="successMessage">
    <img src="images/successMsg.jpg" class="span1 spacing"/>
    <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>-->
</div>
