<div class="row-fluid">
	<div class="span12 breadcrumbs">
  	<ul>
    	<li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>   
            <li><a href="#/user/reports"><g:message code="menu.user.report.stats"/></a></li>
            <li>/</li>
    	<li><g:message code="menu.user.report.statsBillableItem"/></li>
  	</ul>
	</div>
</div>
<div class="row-fluid">   
<ul class="nav nav-tabs span12 customNav">
  <li class="active">
    <a href="#/user/reports/billableItem"><g:message code="menu.user.report.billableItemReport"/></a>
  </li>
  <li>
      <a href="#/user/reports/billableItemChartReport"><g:message code="menu.user.report.billableItemChartReport"/></a>
    </li> 
</ul>
</div>
 <div class="row-fluid">
  <div class="value_dollar"><g:message code="default.valueIn"/><span id="billableItemCurrencyValue"></span></div>  
</div>
<div class="row-fluid" style="background:#F7F7F7;padding:8px;width:auto;border:1px solid #DAE1E8;">
<div class="span12">
  <form class="form-horizontal" id="billableItemReportForm" data-dojo-type="dijit.form.Form">
    <input type="hidden" id="forAccount" name="forAccount">
    <div class="row-fluid">
      <div class="span3 field-box">
            <label for="forDateRange" class="control-label">              
              <g:message code="user.report.dateRange"/> :
              <span class="require">*</span>
            </label>
			<div class="controls updatable elements">
            <select data-dojo-type="dijit.form.Select" name="forDateRange"
                       data-dojo-props="maxHeight: -1" onchange="BillableItemReport.showDate(this.value)">
               <option value="ALL"><g:message code="common.ticket.status.all"/></option>
               <option value="SELECTIVE"><g:message code="common.period"/></option>
           </select>
		   </div>
      </div>
      <div id="billableItemDateRangeDiv" style="display: none">
        <div class="span4 field-box">
            <label for="reportFromDate" class="control-label">              
              <g:message code="common.fromDate"/> :
              <span class="require">*</span>
            </label>
			<div class="controls updatable elements">
            <input type="text" name="reportFromDate" id="reportFromDate" data-dojo-id="billableItemUserFromDate"
            onChange="billableItemUserToDate.constraints.min = arguments[0];" data-dojo-type="dijit.form.DateTextBox" 
            data-dojo-props="required:'true', placeHolder: '<g:message code="common.date.placeholder"/>', 
            promptMessage:'<g:message code="common.date.prompt"/>', invalidMessage:'<g:message code="common.date.invalid"/>', 
            constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>
			</div>
        </div>
        <div class="span4 field-box">
          <label for="reportToDate" class="control-label">            
             <g:message code="common.toDate"/> :
            <span class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <input type="text" name="reportToDate" id="reportToDate" data-dojo-id="billableItemUserToDate"
            onChange="billableItemUserFromDate.constraints.max = arguments[0];"
            data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true',
            placeHolder: '<g:message code="common.date.placeholder"/>', promptMessage:'<g:message code="common.date.prompt"/>',
            invalidMessage:'<g:message code="common.date.invalid"/>', constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>  	
          </div>
        </div> 
      </div>
    </div>
    <div class="row-fluid">
      <div class="span3 field-box">
          <label for="forZone" class="control-label">  
            <g:message code="user.report.zoneBased"/> :
            <span class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemZoneList"></div>
          </div>
      </div>
    </div> 
    <div class="row-fluid">
      <div class="span3 field-box">
          <label for="forZone" class="control-label">            
             <span id="billableItemLable"><g:message code="user.report.forNonZone"/></span> <g:message code="menu.user.report.billableItem"/>:<span class="require">*</span>
          </label>
          <div class="controls updatable elements">
              <select data-dojo-type="dijit.form.Select" name="forBillableItem" id="forBillableItem" data-dojo-props="maxHeight: -1" onchange="BillableItemReport.showBillableItemList(this.value)">
                 <option value="ALL"><g:message code="common.ticket.status.all"/></option>
                 <option value="SELECTIVE"><g:message code="common.selective"/></option>
              </select>
          </div>
      </div>
      <div class="span8 field-box" id="billableItemListDiv" style="display: none">
        <label for="billableItemList" class="control-label">          
          <g:message code="menu.user.report.billableItem"/>(<g:message code="common.use"/> ',' <g:message code="common.multiple"/>) :
          <span class="require">*</span>
        </label>
        <div class="controls updatable elements">
          <div id="billableItemList"></div>
        </div> 
      </div> 
    </div>
    <div class="row-fluid">
      <div class="span3 field-box" id="planListDiv" style="display: none">
          <label for="plan" class="control-label">            
            <g:message code="user.report.forPlan"/>:
            <span class="require">*</span>
          </label>
          <div class="controls updatable elements">
            <div id="billableItemPlanList"></div>
          </div>
      </div>
    </div>
    <div class="row-fluid">
      <button data-dojo-type="dijit.form.Button" onclick="BillableItemReport.generate();" class="generatebtn">
         <g:message code="default.button.generate.common"/>
      </button>
      <button data-dojo-type="dijit.form.Button" onclick="BillableItemReport.cancel();" class="cancelbtn">
         <g:message code="common.cancel"/>
      </button>
    </div>
    </form>
  </div>
</div> 
<div class="row-fluid">
<!--  <div class="alert alert-info">
        <i class="icon-exclamation-sign"></i> 
        Please choose the billable item to generate report
      </div>-->
</div>

<div class="row-fluid" id="billableItemActionButtonDiv" style="display: none">
		<div class="">
			<a href='' id="billableItemCsv" class="generatebtn_csv"><div class="csv_icon"></div><g:message code="common.csv"/></a>
	    	<a href='' id="billableItemPdf" class="generatebtn_pdf"><div class="pdf_icon"></div><g:message code="common.pdf"/></a>
		</div>
</div>
<div class="alert alert-danger errorMessage" id="billableItemErrorDiv">
  <span id="errorContent">
     <g:message code="user.report.pleaseVerify"/>
  </span>
</div>
<div class="row-fluid">
  <iframe class="span12" height="1000px" id="billableItemIframe" name="cuurrentInvoiceFrame" frameBorder="0">
  </iframe>
</div>