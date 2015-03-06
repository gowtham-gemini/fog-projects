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
    <li><g:message code="common.billing.addCard"/></li>
  </ul>
</div>
</div>

<div class="row-fluid form-wrapper" id="pad-wrapper">

  <div class="row-fluid">
      <span class="alert alert-danger errorMessage" id="errorInfo"><g:message code="common.failed"/></span>
      <span class="alert alert-info" id="createVmInfo" style="display: none"><g:message code="user.billing.cardVerifiedInfo"/><a href="#/user/cloud/userInstancePage"><strong><g:message code="common.click"/></strong></a><g:message code="common.to"/> <g:message code="user.createVM"/></span>
  </div>
  <div class="span4 createvm-banner">
  	<img src="${resource(dir: 'images')}/cloud_cc_icon.png" height="256" width="300">
  </div>
  <div class="span6" id="creditCardPage"> 
  <form class="form-horizontal" id="creditCardForm" data-dojo-type="dijit.form.Form">
        <div id="cardDetails">
            <div class="span12"></div>
          <div class="span12 control-group field-box">
              <label for="newCardNumber" class="control-label">                
               <g:message code="common.cardNumber"/>
               <span class="require">*</span>
              </label>
              <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="newCardNumber"
                 data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.cardNumber"/>', required: 'true',
                   placeHolder: '<g:message code="common.cardNumber"/>', regExp: '[0-9]{16,16}'">
                <div class="form_help_icon" style="top: 10px; left: -46px">
                  <i class="icon-info-sign" id="cardNameHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'cardNameHelp', showDelay: 1"><g:message code="common.promptMessage.cardNumber"/></div>
                </div>
              </div>
          </div>
          <div class="span12 control-group field-box">
              <label for="newCvvNumber" class="control-label">                
              <g:message code="common.cvvNumber"/>
              <span class="require">*</span>
              </label>
              <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="newCvvNumber"
                 data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.CVV"/>', required: 'true',
                   placeHolder: '<g:message code="common.cvvNumber"/>', regExp: '[0-9]{3,3}'">
                <div class="form_help_icon" style="top: 11px; left: -46px">
                  <i class="icon-info-sign" id="cardcCvHelp"></i>
                  <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'cardcCvHelp', showDelay: 1"><g:message code="common.billing.credit.CVVNumber.toottip"/></div>
                </div>
              </div>
          </div>
          <div class="span12 control-group field-box">
                <label class="control-label" for="newCardType"><g:message code="common.cardType"/><span class="require">*</span></label>
              <div class="controls">
                  <div class="span5">
                      <select class="valid" name="newCardType" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: -1" id="newCardType">
                          <option value="visa"><g:message code="common.visa"/></option>
                          <option value="mastercard"><g:message code="common.master"/></option>
                          <option value="americanexpress"><g:message code="common.americanExpress"/></option>
                      </select>
                  </div>
                  <div class="span1">
                      <div class="form_help_icon" style="top: 2px; left: -46px">
                          <i class="icon-info-sign" id="cardTypeHelp"></i>
                          <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'cardTypeHelp', showDelay: 1"><g:message code="user.card.cardType.toottip"/></div>
                      </div>
                  </div>                                                  
              </div>
          </div>
          <div class="span12 control-group field-box">
                <label class="control-label" for="newExpiryMonth"><g:message code="common.expiry"/><span class="require">*</span></label>
              <div class="controls">
                  <div class="span2">
                      <select class="valid" name="newExpiryMonth" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: 100" id="newExpiryMonth">
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
                  <div class="span4"><div id="newYearList"></div></div>
                  <div class="span1 offset3" style="margin-left: 10px;">
                      <i class="icon-info-sign" id="cardMonthYearHelp"></i>
                      <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'cardMonthYearHelp', showDelay: 1"><g:message code="common.expiryMonthYear"/></div>
                </div> 
              </div>
          </div>          
        </div>      
		<div class="pull-right">
        <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="SaveCreditCard.add();">
          <g:message code="common.save"/>
        </button>
                  <button class="cancelbtn" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="SaveCreditCard.cancel();">
            <g:message code="common.cancel"/>
        </button>
      </div>
      </form> 
	  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="cardVerifyLoader" class="span6">
  <div class="row-fluid" id="processCardVerifyMessage" style="display: block">
    <img src="images/configLoader.gif" class="span1 spacing"/>
    <p class="message span10"> <g:message code="user.verify"/></p>
  </div>
<!--    <div class="row-fluid hide_lable" id="successMessage">
    <img src="images/successMsg.jpg" class="span1 spacing"/>
    <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>-->
</div>
