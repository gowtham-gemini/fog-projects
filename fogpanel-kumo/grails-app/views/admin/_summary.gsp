<div class="row-fluid">   
<div class="span12 breadcrumbs">
<ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
    <li>/</li>
    <li id="currentAccountName"></li>    
</ul>
</div>
</div>
<g:render template="generalAccountView" />
<input type="hidden" id="currentAccountId">
<div class="row-fluid" style="display: none;">   
    <ul class="nav nav-tabs span12">
      <li class="active">
        <a href="#/admin/account/summary/"><g:message code="common.summary"/></a>
      </li>
      <li>
          <a href="#/admin/account/infrastructure/"><g:message code="menu.admin.cloud.infrastructure"/></a>
        </li>
      <li>
        <a href="#/admin/account/invoice/"><g:message code="menu.admin.billing.invoice"/></a>
      </li>

         <li>
          <a href="#/admin/account/payment/"><g:message code="menu.admin.billing.payments"/></a>
        </li>
         <li>
          <a href="#/admin/account/recurringItem/"><g:message code="common.recurringItem"/></a>
        </li>
    </ul>
</div>
<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="" class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.summary"/>" selected="true">
            <div  class="new-user">
              <div class="row-fluid header">    
                <!--<h4>Summary</h4>-->
              </div> 
              <div class="row-fluid">
                <div class="value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="summaryCurrencyValue"></span></div>  
              </div> 
              <div class="row-fluid">
                <div id="chartLodDiv" style="display: block;"><img src='images/vmload.gif' alt='Loading' height='36' width='100'/> </br> <p><g:message code="common.message.loading"/></p></div>
                <div id="chartDiv" style="display: none;">
                    <div class="span12">
                        <div class="span6">
                          <g:message code="common.summary.invoice"/>
                          <div id="invoiceSummaryChart" style="width: 400px; height: 400px;"></div>
                        </div>
                        <div class="span6">
                              <g:message code="common.summary.payment"/>
                              <div class="row-fluid">
                                  <div class="span12">
                                      <div id="paymentSummaryChart" style="width: 400px; height: auto;"></div>
                                  </div>
                              </div>
                        </div>
                    </div>
                  <div class="row-fluid">
                    <div class="span6">
                      <g:message code="common.summary.customAndRecurring"/>
                      <div id="customItemChart" style="width: 400px; height: 300px;"></div>
                      <div id="legend"></div>
                    </div>
                    <div class="span6" id="supportChartDiv">
                            <g:message code="common.summary.support"/>
                            <div id="supportChart" style="width: 400px; height: 300px;"></div>
                    </div>
                    </div>
                </div>  
              </div>
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.admin.cloud.infrastructure"/>" onshow="InfrastructureInfo.populateValues()">
            <g:render template="infrastructure"/>       
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.admin.billing.invoice"/>" onshow="CurrentInvoiceInfo.populateValues();">
            <g:render template="userInvoiceInfo"/> 
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="menu.admin.billing.payments"/>" onshow="PyamentsInfo.populateValues();">
            <g:render template="payment"/> 
        </div>
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.recurringItem"/>" onshow=" RecurringListInfo.populateValues();">
            <g:render template="currentRecurringItem"/> 
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="accountProcessLoader2" class="span8">
  <div class="row-fluid" id="processPaymentMessage" style="display: block">
    <img src="images/vmload.gif" class="span1 spacing"/>
    <p class="message span10"><g:message code="common.processing"/></p>
  </div>
<!--    <div class="row-fluid hide_lable" id="successMessage">
    <img src="images/successMsg.jpg" class="span1 spacing"/>
    <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>-->
</div>
<div data-dojo-type="dijit.Dialog" id="cancelDialog" title="<g:message code="common.accounts.cancel"/>" class="customDialgue">      
    <div  class="span4">
      Please confirm that you want to cancel this account.
      <div class="row-fluid offset1">
        <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="Summary.cancelAccount()" id="" >
        <g:message code="common.yes"/>
      </button>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="Summary.closeCancelAccountDialog()">
        <g:message code="common.no"/>
      </button> 
      </div>
    
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="enableDialog"  title="<g:message code="common.accounts.enableAccount"/>" style="color: black; width: 350px; height: auto">
  <g:message code="common.accounts.enableAccountInfo"/>
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="Summary.enableAccount()" id="" >
      <g:message code="common.yes"/>
    </button>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="Summary.closeEnableAccountDialog()">
      <g:message code="common.no"/>
    </button> 
  </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="disableDialog" 
               title="<g:message code="common.accounts.disableAccount"/>" style="color: black; width: 350px; 
               height: 150px">
 <g:message code="common.accounts.disableAccountInfo"/>
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
              onclick="Summary.disableAccount()" id="" >
      <g:message code="common.yes"/>
    </button>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
              onclick="Summary.closeDisableAccountDialog()">
      <g:message code="common.no"/>
    </button> 
  </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="suspendDialog" title="<g:message code="common.accounts.suspendAccount"/>" class="span9">
 <g:message code="common.accounts.suspendAccountInfo"/>
  <div class="row-fluid">
      <textarea class="span12" rows="12" cols="250" id="suspendTextContent"></textarea>
  </div>
   
  <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="Summary.suspendAccount()" id="" >
      <g:message code="common.yes"/>
    </button>
    <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button" onclick="Summary.closeSuspendAccountDialog()">
      <g:message code="common.no"/>
    </button> 
  </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="refundDialog" title="<g:message code="common.refundAccount"/>"  class="span8">
  <g:message code="common.accounts.refunfAccountInfo"/>
    <div class="new-user"> 
      <div class="row-fluid form-wrapper"> 
        <div class="span12"> 
          <div class="container">
            <form id="" data-dojo-type="dijit.form.Form" class="form-horizontal">
              <div class="row-fluid" id='refundFormPage'>
                  <div class="control-group span6"> 
                      <label for="refundAmount" class="control-label">
                          <span class="require">*</span>
                          <g:message code="common.billing.refundAmount"/>
                      </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="required:'true', constraints:{min: 1, max: 10000000, pattern:'#.00'}, placeHolder: 'value', promptMessage:'value', invalidMessage:'Invalid Value.'" id="refundAmount">  
                      </div>
                  </div> 
                  <div class="control-group span10">
                        <label for="refundDescription" class="control-label">
                          <span class="require">*</span>
                          <g:message code="common.description"/>
                        </label>
                    <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="promptMessage:'<g:message code="common.description.prompt"/>',  missingMessage: '<g:message code="common.description.required"/>',required: true,  placeHolder: '<g:message code="common.description"/>'"  name="description" id="refundDescription"> 
                    </div>  
                  </div>
                </div>
            </form> 
          </div>
        </div>
      </div>
    </div>
    <div style="margin-left: 100px">
      <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
                onclick="Summary.refundAccount()" id="" >
         <g:message code="common.yes"/>
      </button>
      <button class="cancelbtn" id="" data-dojo-type="dijit.form.Button"
                onclick="Summary.closeRefundAccountDialog()">
         <g:message code="common.no"/>
      </button> 
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="updateCreditLimitDialog" class="span4">    
    <div class="row-fluid">
        <form id="updateCreditLimitForm" data-dojo-type="dijit.form.Form" class="form-horizontal">                    
            <div id="updateCreditLimitPage">
                <div class="row-fluid">
                    <div class="control-group">
                        <label for="tierName" class="control-label" style="width: 140px !important;">
                             <g:message code="common.currentCreditLimit"/>                             
                         </label>
                         <div class="controls" style="margin: 5px 0 0 10px;">
                          <span id="updateCurrentCreditLimit"></span>
                      </div>
                    </div>
                    <div class="control-group">
                        <label for="tierNetworkOffering" class="control-label">
                            <g:message code="common.due"/>                            
                        </label>
                        <div class="controls " style="margin: 5px 0 0 141px !important;">
                          <span id="updateCurrentDue"></span>
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="tierGateway" class="control-label">
                             <g:message code="common.newLimit"/>
                             <span class="require">*</span>
                         </label>
                             <div class="controls span7" style="margin-left: 30px !important">
                         <input type="text" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', 
                                required: 'required', placeHolder: '<g:message code="common.enterValue"/>', promptMessage:'<g:message code="common.enterValue"/>', regExp:'[0-9]{1,200}'"  name="updateCreditLimit" id="updateCreditLimit"> 
                      </div>
                    </div>                                       
                </div>
            </div>   
        </form>
    </div>
    <div class="row-fluid">
         <div class="pull-right">
           <button data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.showUpdateCreitLimitConfirm();" class="primarybtn">
              <g:message code="common.ok"/>
           </button>
           <button data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.cancelCreditLimit();" class="cancelbtn">
              <g:message code="common.cancel"/>
           </button>
         </div>
     </div>
</div>

<div data-dojo-type="dijit.Dialog" id="vpcUpdateCreditConfirm" title="<g:message code="common.ip.releaseIP"/>" class="span4">     
    <div class="row-fluid">        
        <div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>        
        <div class="span10">            
            <div class="span12"><p><g:message code="common.user.ip.releaseNetwork"/></p></div>        
        </div>
    </div> 
    <div class="row-fluid">        
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.updateCreditLimit()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ViewCurrentAccountInfo.cancelCreditConfirm()"><g:message code="common.cancel"/></button>    
    </div>
</div>