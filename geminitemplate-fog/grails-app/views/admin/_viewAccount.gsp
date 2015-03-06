<div class="row">   
  <div class="well well-large col-md-12">
    <div class="row overflow-text">
      <div class="col-md-4">  
        <div></div>
        <div class="head col-md-12">
            <h5 style="text-decoration: underline"><g:message code="common.accounts.clientInformation"/></h5>
        </div>
        <div class="col-md-12 field-box">
          <label class="headerLabel"><g:message code="common.account"/>: </label>
           <span  class="labelValues" id="currentAcId"></span>
           <span  class="labelValues">|</span>
           <span  class="labelValues" id="currentAcType" style="color: #4285F4"></span>
           <span  class="labelValues">|</span>
           <span  class="labelValues highlight-text" id="currentAcStatus">Status</span> 
        </div>   
        <div class="col-md-12 field-box"> 
          <label class="headerLabel"><g:message code="common.cardVerified"/></label>
           <span  id="currentAcCardVerifiedStatus" class="labelValues"></span>
        </div>
        <div class="col-md-12 field-box">
            <label class="headerLabel"><g:message code="common.firstName"/></label>
             <span  id="currentAcFirstName" class="labelValues"></span>
          </div>         
          <div class="col-md-12 field-box">                    
            <label class="headerLabel"><g:message code="common.lastName"/></label>
             <span  id="currentAcLastName" class="labelValues"></span>             
          </div> 
          <div class="col-md-12 field-box"> 
            <label class="headerLabel"><g:message code="common.email"/></label>
             <span  id="currentAcEmail" class="labelValues"></span>
          </div>
      </div> 
      <div style="float:left; width:1px;height:170px;background:#ccc;"></div>
      <div class="col-md-4">
        <div class="col-md-12"></div>
        <div class="row">
            <div class="col-md-3"> <span  class="overflowLabel"><g:message code="common.address"/></span></div>
            <div class="col-md-9">
                <div class="col-md-12 field-box">       
                     <span  id="currentUserAddress1" class="labelValues"></span>
                      <span  id="currentUserAddress2" class="labelValues"></span>
                </div> 
               <div class="row">                
                 <span  id="currentUserCity" class="labelValues"></span> <span  id="currentUserState" class="labelValues"></span>
            </div>
              <div class="row">             
                 <span  id="currentUserCountry" class="labelValues"></span> <span  id="currentUserZip" class="labelValues"></span>
            </div>
            </div>            
        </div>                                  
        <!--<div class="col-md-12 field-box">--> 
            <div class="row">
                <div class = "col-md-3">
                     <span  class="overflowLabel"> <g:message code="common.phone"/></span>
                </div>
                <div class = "col-md-9"> <span  id="currentAcPhone" class="labelValues"></span></div>
            </div>                   
        <!--</div>-->
        <div class="col-md-12 field-box" style="display: none">
          <label class="headerLabel"><g:message code="common.joinedDate"/></label>
           <span  id="currentAcJoinedDate" class="labelValues"></span>
        </div>
        <div class="col-md-12 field-box" style="display: none">
          <label class="headerLabel"><g:message code="common.nextBillingDate"/></label>
           <span  id="currentAcNextBillData" class="labelValues"></span>
        </div>
      </div>      
      <div style="float:left; width:1px;height:170px;background:#ccc;"></div>
      <div class="col-md-3">
        <div></div>
        <div class="head col-md-12">
            <h5 style="text-decoration: underline"><g:message code="common.invoiceAndBilling"/></h5>
        </div>
        
        <div class="col-md-12 field-box">
          <label class="headerLabel min_width_label"><g:message code="common.paid"/></label>
           <span  id="currentAcPaid" class="labelValues"></span>
        </div>   
        <div class="col-md-12 field-box">
          <label class="headerLabel min_width_label"><g:message code="common.due"/></label>
           <span  id="currentAcDue" class="labelValues"></span>
        </div>
        <div class="col-md-12 field-box">
            <label class="headerLabel min_width_label"><g:message code="common.refund"/></label>
             <span  id="currentAcRefund" class="labelValues" style="color: #D30000">0.0</span>
          </div>                                            
         <div class="col-md-12 field-box">
            <label class="headerLabel min_width_label"><g:message code="common.avlCredit"/></label>
             <span  id="currentAcBc" class="labelValues">0.0</span>
          </div>           
         <div class="col-md-12 field-box">
             <label class="headerLabel min_width_label" style="color: #FF9900 !important; font-weight: bolder !important">Income</label>
              <span  id="currentAcIncome" class="labelValues" style="color: #FF9900; font-size: 18px ">0.0</span>
         </div> 
        <div class="col-md-12 field-box" style="display: none">
          <label class="headerLabel"><g:message code="common.creditLimit"/></label>
           <span  id="currentAcCreditLimit" class="labelValues"></span>
        </div>        
      </div>     
    </div>    
  </div>
</div>
