<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/<li>
            <li><a href="#/user/billing/payment"><g:message code="menu.admin.billing.payments"/></a></li>
            <li>/<li>
            <li><g:message code="common.gateway"/></li>
        </ul>
    </div>
</div>
<div class="new-user">
<div class="row-fluid form-wrapper">
<div class="span8 with-sidebar" id="updateRecurringItemPage">
<div class="container">
    <form id="manualPaymentForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
        <div class="span12 field-box control-group" id="paymentGateways">
            <label class="control-label settings_lable"><g:message code="common.selectGateway"/>:<span class="require">*</span>
            </label>             
            <div id="paymentGatewaysList"></div>
        </div>        
        <div id="paypalDiv">
                <div class="row-fluid container">        
            <div class="" id="manualPaymentPage">                 
              <div id="payAmountDiv" class="span12 field-box control-group">
                  <label for="paymentAmount" class="control-label settings_lable"> <g:message code="common.payment.paymentAmount"/><span class="require">*</span></label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                           data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.00'}, placeHolder: '<g:message code="common.value"/>',
                           promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="paymentAmount">  
                    <div  style="cursor: pointer; float: right; position: relative">
                        <i class="icon-info-sign" id="paymentAmtHelp"></i>
                        <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentAmtHelp', showDelay: 1"> <g:message code="common.payment.paymentAmount"/></div>
                    </div>
                 </div>

              </div>        
              <div  class="span12 field-box" id="defaultCardDetails"> 
                <label for="useDefaultCard" class="control-label settings_lable"><g:message code="common.defaulPaymentCard"/>:</label>
                <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                         data-dojo-props="checked: true" id="useDefaultCard" onchange="ManualPayment.enableCard(this)">
                  <div  style="cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentDefaultHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentDefaultHelp', showDelay: 1"><g:message code="common.defaulPaymentCard"/></div>
                </div>
                </div>
              </div>         
              <div id="cardDetails" style="display: none">                  
                <div  class="span12 field-box"> 
                  <label for="cardNumber" class="control-label settings_lable">
                      <g:message code="common.cardNumber"/>
                    <span class="require">*</span>                   
                  </label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cardNumber"
                           data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.cardNumber"/>', required: 'true',
                           placeHolder: '<g:message code="common.cardNumber"/>', regExp: '[0-9]{16,16}'">
                    <div  style="cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentCardNoHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentCardNoHelp', showDelay: 1"><g:message code="common.cardNumber"/></div>
                </div>
                  </div>
                </div>               
                <div class="span12 field-box"> 
                  <label for="cvvNumber" class="control-label settings_lable">
                      <g:message code="common.cvvNumber"/>
                    <span class="require">*</span>                  
                  </label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cvvNumber"
                     data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.CVV"/>', required: 'true',
                       placeHolder: '<g:message code="common.cvvNumber"/>', regExp: '[0-9]{3,3}'">
                    <div  style="cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentCvvNoHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentCvvNoHelp', showDelay: 1"><g:message code="common.cvvNumber"/></div>
                </div>
                  </div>
                </div>               
                <div class="span12 field-box"> 
                    <label class="control-label settings_lable" for="cardType"><g:message code="common.cardType"/><span class="require">*</span>:</label>
                  <div class="controls">
                      <div class="span4">
                          <select class="valid" name="cardType" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: -1" id="cardType">
                              <option value="visa"><g:message code="common.visa"/></option>
                              <option value="mastercard"><g:message code="common.master"/></option>
                              <option value="americanexpress"><g:message code="common.americanExpress"/></option>
                          </select>
                      </div>
                    <div class="span1">
                        <div  style="cursor: pointer; float: right; position: relative">
                            <i class="icon-info-sign" id="paymentCardTypeHelp"></i>
                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentCardTypeHelp', showDelay: 1"><g:message code="user.card.cardType.toottip"/></div>
                        </div>
                    </div>                    
                  </div>
                </div>             
                <div  class="span12 field-box"> 
                  <label class="control-label settings_lable" for="expiryMonth"><g:message code="common.expiry"/><span class="require">*</span>:</label>
                  <div class="controls">
                      <div class="span2">
                          <select class="valid" name="expiryMonth" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: 100" id="expiryMonth">
                              <option value="1">1</option>
                              <option value="2">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                              <option value="7">7</option>
                              <option value="8">8</option>
                              <option value="9">9</option>
                              <option value="10">10</option>
                              <option value="11">11</option>
                              <option value="12">12</option>
                          </select>
                      </div>                                          
                      <div class="span1"><span>/</span></div>                      
                      <div id="yearList" class="span4"></div>
                      <div class="span1">
                          <i class="icon-info-sign" id="paymentYearAndMonthHelp"></i>
                          <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentYearAndMonthHelp', showDelay: 1"><g:message code="common.expiryMonthYear"/></div>
                      </div>       
                  </div>
                </div>                          
              </div>  
                <div class="row-fluid">
                    <div class="pull-right">
                      <button data-dojo-type="dijit.form.Button" onclick="ManualPayment.add();" class="primarybtn">
                         <g:message code="common.payNow"/>
                      </button>
                      <button data-dojo-type="dijit.form.Button" onclick="ManualPayment.cancel();" class="cancelbtn">
                         <g:message code="common.cancel"/>
                      </button>
                    </div>
		  </div>
          </div>
      <!--</div>-->
      <!--</div>-->
    <!--</div>-->
  <!--</div>-->
</div>
</div>
</form>
<div id="ccAvenueDiv">  
    <div class="row-fluid container">
        <form data-dojo-type="dijit.form.Form" id="ccAvenuPaymentForm" class="form-horizontal">
            <div class="" id="ccAvenuePage">                
                <div class="span12 field-box control-group" id="ccAvenuePaymentAmount"> 
                    <label for="paymentAmountCCA" class="control-label settings_lable">
                        <g:message code="common.payment.paymentAmount" /> 
                        <span class="require">*</span>
                    </label>
                    <div class="controls elements">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" id="paymentAmountCCA" name="Amount"
                        data-dojo-props="required:'true', constraints:{min:1,max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value" /> ',
                        promptMessage:'<g:message code="common.value" /> ', invalidMessage:'<g:message code="common.value.invalid" /> '">  
                        <div  style="cursor: pointer; float: right; position: relative">
                            <i class="icon-info-sign" id="ccAvenuepaymentAmtHelp"></i>
                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ccAvenuepaymentAmtHelp', showDelay: 1"> <g:message code="common.payment.paymentAmount"/></div>
                        </div>
                    </div>        
                </div>
                <div class="span12 field-box control-group" id="ccAvenuePaymentOption"> 
                    <label for="paymentOption" class="control-label settings_lable">
                        <g:message code="common.payment.paymentOption" /> 
                        <span class="require">*</span>
                    </label>
                    <div class="controls elements">
                        <input type="radio" data-dojo-type="dijit.form.RadioButton" name="cardOption" id="debitCard" value="NonMoto" onclick="CCAvenueGateway.loadBankInfo();"/>
                        <label for="debitCard" class="payment_label"><g:message code="common.debitCard"/></label> 
                        <input type="radio" data-dojo-type="dijit.form.RadioButton" name="cardOption" id="netBanking" value="netBanking" onclick="CCAvenueGateway.loadBankInfo();"/> 
                        <label for="netBanking" class="payment_label"><g:message code="common.netBanking"/></label> 
                        <input type="radio" data-dojo-type="dijit.form.RadioButton" name="cardOption" id="cashCard" value="CCRD" onclick="CCAvenueGateway.loadBankInfo();"/>
                        <label for="cashCard" class="payment_label"><g:message code="common.cashCard"/></label>                         
                        <div  style="cursor: pointer; float: right; position: relative">
                            <i class="icon-info-sign" id="ccAvenuepaymentCardTypeHelp"></i>
                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ccAvenuepaymentCardTypeHelp', showDelay: 1"><g:message code="user.card.cardType.toottip"/></div>
                        </div>
                    </div>        
                </div>
                <div class="row-fluid">
                    <div style="margin-left: -100px;" class="span1"></div>
                    <div class="span10 field-box control-group" id="ccAvenuePaymentBank"> 
                        <label for="bankDataList" class="control-label settings_lable">
                            <g:message code="common.payment.paymentBank" /> 
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <div id="bankDataList" class="selectOption"></div>
                            <div class="" style="float: right; margin-top: -20px;">
                            <i class="icon-info-sign" id="ccAvenuepaymentBankHelp"></i>
                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'ccAvenuepaymentBankHelp', showDelay: 1"><g:message code="common.paymentBankHelp"/></div>
                    </div> 
                        </div>
                    </div>
                    
                </div>
                <div class="row-fluid">
                    <div class="pull-right">
                        <button data-dojo-type="dijit.form.Button" onclick="CCAvenueGateway.addPayment();" class="primarybtn">
                            <g:message code="common.payNow"/>
                        </button>
                        <button data-dojo-type="dijit.form.Button" onclick="CCAvenueGateway.cancelPayment();" type="reset" class="cancelbtn">
                            <g:message code="common.cancel"/>
                        </button>
                    </div>
                </div>                 
            </div>
            <div class="span6">
                <div class="span12"></div>
                <div class="span12"></div>
                <div class="span12"><span class="validation" style="margin-left: 0px;" id="cardExceptionMsg"><g:message code="common.pleaseChooseCard"/></span></div>
            </div>
       </form>
    </div>
</div>
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
