<div class="row-fluid">
	<div class="span12 breadcrumbs">
  	<ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>   
            <li><a href="#/admin/reports"><g:message code="menu.admin.report"/></a></li>
            <li>/</li>
            <li><g:message code="menu.admin.report.creditLimitReport"/></li>
  	</ul>
	</div>
</div>
<div class="row-fluid">
    <div class="value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="creditCurrencyValue"></span></div>  
</div>
<div class="row-fluid" style="background:#F7F7F7;padding:8px;width:auto;border:1px solid #DAE1E8;">
<div class="span12">
  <form class="form-horizontal" id="creditLimitReportForm" data-dojo-type="dijit.form.Form">
    <div class="row-fluid">
      <div class="span3 field-box">
            <label for="forDateRange" class="control-label">              
              <g:message code="user.report.dateRange"/> :
              <span class="require">*</span>
            </label>
			<div class="controls updatable elements">
            <select data-dojo-type="dijit.form.Select" name="forDateRange"
                       data-dojo-props="maxHeight: -1" onchange="CreditLimitReport.showDate(this.value)">
                <option value="ALL"><g:message code="common.all"/></option>
                <option value="SELECTIVE"><g:message code="common.period"/></option>
           </select>
		   </div>
      </div>
      <div id="creditLimitReportDateRangeDiv" style="display: none">
        <div class="span4 field-box">
            <label for="reportFromDate" class="control-label">
              <span class="require">*</span>
               <g:message code="common.fromDate"/>:
            </label>
            <div class="controls updatable elements">
                <input type="text" name="reportFromDate" data-dojo-id="creditLimitReportFromDate"
                data-dojo-type="dijit.form.DateTextBox" onChange="creditLimitReportToDate.constraints.min = arguments[0];"
                data-dojo-props="required:'true', 
                placeHolder: '<g:message code="common.date.placeholder"/>',
                promptMessage:'<g:message code="common.date.prompt"/>', 
                invalidMessage:'<g:message code="common.date.invalid"/>', 
                constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>
            </div>
        </div>
        <div class="span4 field-box">
          <label for="reportToDate" class="control-label">
            <span class="require">*</span>
            <g:message code="common.toDate"/> :
          </label>
          <div class="controls updatable elements">
            <input type="text" name="reportToDate"  data-dojo-id="creditLimitReportToDate" onChange="creditLimitReportFromDate.constraints.max = arguments[0];"
                data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true', 
                placeHolder: '<g:message code="common.date.placeholder"/>',
                promptMessage:'<g:message code="common.date.prompt"/>', 
                invalidMessage:'<g:message code="common.date.invalid"/>', 
                constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>  			
          </div>
        </div> 
      </div>
    </div>
<!--    <div class="row-fluid">
      <div class="span3 field-box">
          <label for="forAccount" class="control-label">
            <span class="require">*</span>
            For Account:
          </label>
          <div class="controls updatable elements">
            <div id="paymentAccountList"></div>
          </div>
      </div>
    </div> -->
    <div class="span12"></div>
    <div class="row-fluid">
      <button data-dojo-type="dijit.form.Button" onclick="CreditLimitReport.generate();" class="generatebtn">
        <g:message code="default.button.generate.common"/>
      </button>
      <button data-dojo-type="dijit.form.Button" onclick="CreditLimitReport.cancel();" class="cancelbtn">
        <g:message code="common.cancel"/>
      </button>
    </div>
    </form>
  </div>
</div> 
<div class="row-fluid" id="creditLimitReportActionButtonDiv" style="display: none">
    <div class="span2 offset10">
            <a href='' id="creditLimitReportCsv" class="generatebtn_csv"><div class="csv_icon"></div><g:message code="common.csv"/></a>
    <a href='' id="creditLimitReportPdf" class="generatebtn_pdf"><div class="pdf_icon"></div><g:message code="common.pdf"/></a>
    </div>
</div>
<div class="alert alert-danger errorMessage" id="creditLimitErrorDiv">
  <span id="creditLimitErrorContent">
    <g:message code="user.report.pleaseVerify"/>
  </span>
</div>
<div class="row-fluid">
  <iframe class="span12" height="1000px" id="creditLimitReportIframe" name="creditLimitFrame" frameBorder="0">
  </iframe>
</div>