<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/billing"><g:message code="menu.admin.billing"/></a></li>
    <li>/<li>
    <li><g:message code="menu.admin.billing.payments"/></li>
  </ul>
</div>
</div>

<div class="row-fluid">
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat child1">
                <div class="data">
                    <div class="span7 price_info_value">
                        <div class="number span12">
                            <sup id="dueCurrency" class="span2"></sup>
                            <span id="currentDue" class="span6"></span>
                        </div>
                    </div>
                    <div class="span5 price_info_details">
                        <div class="span12 info_tags"><g:message code="stat.user.currentDue"/></div>
                    </div>
                        <div class="span11 PaymentPeriod dbdates" id="creditBalance" style="display: none;"><span class="require">*</span> <g:message code="common.availableCredit"/><span style="margin-left: 10px;" id="creditBalanceAmount"></span></div>
                </div>
            </div>
                    <div class="span4 stat">
                        <div class="data">
                            <div class="span7 price_info_value">
                                <div class="number span12">
                                    <sup id="creditCurrency" class="span2"></sup>
                                    <span id="credit" class="span6"></span>
                                </div>                            
                            </div>
                            <div class="span5 price_info_details">
                                <div class="span12 info_tags"><g:message code="stat.user.credit"/></div>
                            </div>
                            <div class="span11 PaymentPeriod dbdates"></div>
                        </div>
                    </div>
                            <div class="span4 stat">
                                <div class="data">
                                    <div class="span7 price_info_value">
                                        <div class="number span12">
                                            <sup id="paymentsCurrency" class="span2"></sup>
                                            <span  id="Payments" class="span6"></span>
                                        </div>
                                    </div>
                                    <div class="span5 price_info_details">
                                        <div class="span12 info_tags"><g:message code="stat.user.payment"/></div>
                                    </div>	
                                    <div id="PaymentPeriod" class="span11 PaymentPeriod dbdates"></div>
                                </div>
                            </div>
        </div>
    </div>
</div>
<div class="row-fluid"><div class="span1"></div></div>
<div>
    <div class="table-wrapper products-table">
        <div class="row-fluid filter-block" >
            <div class="span12" id="paymentButtonDiv" style="display: none;">
                <div class="span12">
                    <a title="<g:message code="common.billing.makePayment"/>" class="span2 btn-glow primary new-product pull-right" href="#/user/billing/makePayment">+<g:message code="common.billing.makePayment"/></a> 
                    <span class="pull-right">&nbsp;&nbsp;</span>
                    <a id='addCardButton' style="display: none; width: 100px;" title="<g:message code="common.billing.addCard"/>" class="span2 btn-glow primary new-product pull-right" href="#/user/billing/creditCard">+<g:message code="common.billing.addCard"/></a> 
                </div>
                <button style="display: none;" data-dojo-type="dijit.form.Button" title="<g:message code="common.makePayment"/>" class="defaultbtn" onclick="ManualPayment.showPaymentForm()">
                    +<g:message code="common.makePayment"/> 
                </button> 
            </div>
        </div>
        <div class="row-fluid">
            <div class="span12" id="paymentListGrid"></div>
            <div class="alert alert-info hide" id="noPaymentMessageBox" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="common.payment.noPayment"/>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="paymentDialog" title="<g:message code="common.makePayment"/>" class="dijitDialogueBackground">  
    <div class="span5">  
        <div class="new-user"> 
            <div class="row-fluid form-wrapper">   
        <div class="span12">           
          <form id="manualPaymentForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
            <div class="row-fluid" id="manualPaymentPage">   
              <div id="payAmountDiv">
                <div class="span12 field-box control-group"> 
                  <label for="paymentAmount" class="control-label"> <g:message code="common.payment.paymentAmount"/><span class="require">*</span></label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                           data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.00'}, placeHolder: '<g:message code="common.value"/>',
                           promptMessage:'<g:message code="common.value"/>', invalidMessage:'<g:message code="common.value.invalid"/>'" id="paymentAmount">  
                  <div  style="left: -46px; cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentAmtHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentAmtHelp', showDelay: 1"> <g:message code="common.payment.paymentAmount"/></div>
                </div>
                </div>
              </div> 
           </div>        
              <div  class="span12 field-box" id="defaultCardDetails"> 
                <label for="useDefaultCard" class="control-label"><g:message code="common.defaulPaymentCard"/>:</label>
                <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                         data-dojo-props="checked: true" id="useDefaultCard" onchange="ManualPayment.enableCard(this)">
                  <div  style="left: -46px; cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentDefaultHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentDefaultHelp', showDelay: 1"><g:message code="common.defaulPaymentCard"/></div>
                </div>
                </div>
              </div>         
              <div id="cardDetails" style="display: none">           
                <div class="span12 field-box" > 
                  <label for="cardNumber" class="control-label">
                      <g:message code="common.cardNumber"/>
                    <span class="require">*</span>                   
                  </label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cardNumber"
                           data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.cardNumber"/>', required: 'true',
                           placeHolder: '<g:message code="common.cardNumber"/>', regExp: '[0-9]{16,16}'">
                    <div  style="left: -46px; cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentCardNoHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentCardNoHelp', showDelay: 1"><g:message code="common.cardNumber"/></div>
                </div>
                  </div>
                </div>               
                <div class="span12 field-box"> 
                  <label for="cvvNumber" class="control-label">
                      <g:message code="common.cvvNumber"/>
                    <span class="require">*</span>                  
                  </label>
                  <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cvvNumber"
                     data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.CVV"/>', required: 'true',
                       placeHolder: '<g:message code="common.cvvNumber"/>', regExp: '[0-9]{3,3}'">
                    <div  style="left: -46px; cursor: pointer; float: right; position: relative">
                  <i class="icon-info-sign" id="paymentCvvNoHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentCvvNoHelp', showDelay: 1"><g:message code="common.cvvNumber"/></div>
                </div>
                  </div>
                </div>               
                <div class="span12 field-box"> 
                    <label class="control-label" for="cardType"><g:message code="common.cardType"/><span class="require">*</span>:</label>
                  <div class="controls">
                      <div class="span4">
                          <select class="valid" name="cardType" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: -1" id="cardType">
                              <option value="visa"><g:message code="common.visa"/></option>
                              <option value="mastercard"><g:message code="common.master"/></option>
                              <option value="americanexpress"><g:message code="common.americanExpress"/></option>
                          </select>
                      </div>
                    <div class="span1 offset1">
                        <div  style="left: -46px; cursor: pointer; float: right; position: relative">
                            <i class="icon-info-sign" id="paymentCardTypeHelp"></i>
                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'paymentCardTypeHelp', showDelay: 1"><g:message code="user.card.cardType.toottip"/></div>
                        </div>
                    </div>                    
                  </div>
                </div>             
                <div  class="span12 field-box"> 
                  <label class="control-label" for="expiryMonth"><g:message code="common.expiry"/><span class="require">*</span>:</label>
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
        </form> 
      
      </div>
      <!--</div>-->
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
