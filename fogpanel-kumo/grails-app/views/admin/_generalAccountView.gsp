
<div class="row-fluid">   
  <div class="well well-large">            
      <div class="row-fluid overflow-text">
        <div class="span4">  
          <div>
          </div>
          <div class="head span12">
            <h5 style="text-decoration: underline"><g:message code="common.accounts.clientInformation"/></h5>
        </div>
          <div class="span12 field-box">
            <label class="headerLabel"><g:message code="common.account"/></label>
            <span class="labelValues" id="currentAcId"></span>
            <span class="labelValues">|</span>
            <span class="labelValues" id="currentAcType" style="color: #4285F4"></span>
            <span  class="labelValues">|</span>
            <span class="labelValues highlight-text overflowLabel" id="currentAcStatus"></span>  
          </div>
          <div class="span12 field-box"> 
            <label class="headerLabel"><g:message code="common.cardVerified"/></label>
            <span id="currentAcCardVerifiedStatus" class="labelValues"></span>
          </div>
          <div class="span12 field-box">
            <label class="headerLabel"><g:message code="common.firstName"/></label>
            <span id="currentAcFirstName" class="labelValues"></span>
          </div>         
          <div class="span12 field-box">                    
            <label class="headerLabel"><g:message code="common.lastName"/></label>
            <span id="currentAcLastName" class="labelValues"></span>             
          </div> 
          <div class="span12 field-box"> 
            <label class="headerLabel"><g:message code="common.email"/></label>
            <span id="currentAcEmail" class="labelValues"></span>
          </div>
            
        </div>
        <div style="float:left; width:1px;height:210px;background:#ccc;"></div>
        <div class="span3">      
            <div class="span12">
              <!--<h5 style="text-decoration: underline">Address</h5>-->
          </div>
          <div class="row-fluid">
              <div class="span3">
                  <span class="overflowLabel"><g:message code="common.address"/></span>
              </div>             
              <div class="span9">
            <div class="span12 field-box">                
                <span id="currentUserAddress1" class="labelValues"></span>
                <span id="currentUserAddress2" class="labelValues"></span>
            </div> 
            <div class="row-fluid">                
                
            </div>
            <div class="row-fluid">                
                <span id="currentUserCity" class="labelValues"></span><span id="currentUserState" class="labelValues"></span>
            </div>
            <div class="row-fluid">                
                <span id="currentUserCountry" class="labelValues"></span><span id="currentUserZip" class="labelValues"></span>
            </div>
              </div>
          </div>
           
          <!--<div class="span12 field-box">-->
              <div class="row-fluid">
                  <div class="span3">
                      <span class="overflowLabel"><g:message code="common.phone"/></span>
                  </div>
                  <div class="span9">
                      <span id="currentAcPhone" class="labelValues"></span>
                </div> 
              </div>            
          <!--</div>-->                        
          <div class="span12 field-box" style="display: none">
            <span class="headerLabel"><g:message code="common.joinedDate"/></span>
            <span id="currentAcJoinedDate" class="labelValues"></span>
          </div>  
            <div class="span12 field-box" style="display: none">
            <span class="headerLabel"><g:message code="common.nextBillingDate"/></span>
            <span id="currentAcNextBillData" class="labelValues"></span>
          </div> 
        </div>
        <div style="float:left; width:1px;height:210px;background:#ccc;"></div>
        <div class="span3">                        
          <div>
          </div>
          <div class="head span12">
            <h5 style="text-decoration: underline"><g:message code="common.invoiceAndBilling"/></h5>
        </div>
          <div class="span12 field-box">
              <label class="headerLabel min_width_label"><g:message code="common.creditLimit"/></label>
              <span id="currentAcCreditLimit" class="labelValues"></span>
              <span class="labelValues" id="updateCLDivider">|</span>
              <span class="labelValues highlight-text overflowLabel" id="udateCreditLimitDiv">
                  <a style="text-decoration: none; font-weight: bolder;" id="accountInfoUpdateCreditTag" onclick="ViewCurrentAccountInfo.showUpdateCredit();"><g:message code="common.update"/></a>
                  <img id="accountInfoUpdateCreditLoader" class="offset4 hide_text " src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
              </span> 
          </div> 
          <div class="span12 field-box">
            <label class="headerLabel min_width_label"><g:message code="common.paid"/></label>
            <span id="currentAcPaid" class="labelValues"></span>
          </div>
        <div class="span12 field-box">
            <label class="headerLabel min_width_label"><g:message code="common.due"/></label>
            <span id="currentAcDue" class="labelValues"></span>
          </div>    
        <div class="span12 field-box">
            <label class="headerLabel min_width_label"><g:message code="common.refund"/></label>
            <span id="currentAcRefund" class="labelValues" style="color: #D30000">0.0</span>
          </div>                                            
         <div class="span12 field-box">
            <label class="headerLabel min_width_label"><g:message code="common.avlCredit"/></label>
            <span id="currentAcBc" class="labelValues">0.0</span>
          </div>           
         <div class="span12 field-box">
             <label class="headerLabel min_width_label" style="color: #FF9900 !important; font-weight: bolder !important">Income</label>
             <span id="currentAcIncome" class="labelValues" style="color: #FF9900; font-size: 18px ">0.0</span>
         </div>          
        </div>
        <div style="float:left; width:1px;height:210px;background:#ccc;"></div>
        <div class="span1">
          <div>
          </div>
          <div class="head span12">
            <h5 style="text-decoration: underline"><g:message code="common.action"/></h5>
        </div>
            <div class="span12 field-box" id="enableAccount">           
                <button data-dojo-type="dijit.form.Button" class="defaultbtn customBtn" onclick="Summary.showEnableAccountDialog()" 
                    title="Enable Account" id="enableAccountBtn"><g:message code="common.activate"/></button>            
            </div>
          <div class="span12 field-box" id="cancelAccount">
            <button data-dojo-type="dijit.form.Button" class="defaultbtn customBtn" onclick="Summary.showCancelAccountDialog()"
                    title="Cancel Account" id="cancelAccountBtn">
              <g:message code="common.cancel"/>
            </button>
          </div>
          <div class="span12 field-box" id="suspendAccount">
            <button data-dojo-type="dijit.form.Button" class="defaultbtn customBtn" onclick="Summary.showSuspendAccountDialog()"
                    title="<g:message code="common.suspend"/>" id="suspendAccountBtn">
              <g:message code="common.suspend"/>
            </button>
          </div>
          <div class="span12 field-box" id="refund">
            <button data-dojo-type="dijit.form.Button" class="defaultbtn customBtn" onclick="Summary.showRefundAccountDialog()"
                    title="<g:message code="common.cancel"/>" id="refundBtn">
              <g:message code="common.refund"/>
            </button>
          </div>
        </div>
      </div>        
  </div>  
</div>
