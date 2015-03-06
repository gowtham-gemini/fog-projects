<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>   
            <li><a href="#/admin/reports"><g:message code="menu.admin.report"/></a></li>
            <li>/</li>
            <li><g:message code="menu.admin.report.signUpReport"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div class="value_dollar pull-right">Value in  <span id="reportSignupCurrencyValue"></span></div>  
</div>
<div class="row-fluid" style="background:#F7F7F7;padding:8px;width:auto;border:1px solid #DAE1E8;">
    <div class="span12">
        <form class="form-horizontal" id="reportFilterForm" data-dojo-type="dijit.form.Form">
            <div class="row-fluid">
                <div class="span3 field-box">
                    <label for="forDateRange" class="control-label">              
                        <g:message code="common.dateRange"/> :
                        <span class="require">*</span>
                    </label>
                    <div class="controls updatable elements">
                        <select data-dojo-type="dijit.form.Select" name="forDateRange" id="forDateRange"
                        data-dojo-props="maxHeight: -1" onchange="SignUpReport.showDate(this.value)">
                        <option value="ALL"><g:message code="common.all"/></option>
                        <option value="SELECTIVE"><g:message code="common.period"/></option>
                        </select>
                    </div>
                </div>
                <div id="dateRangeDiv" style="display: none">
                    <div class="span4 field-box">
                        <label for="reportFromDate" class="control-label">
                            <span class="require">*</span>
                            <g:message code="common.fromDate"/> :
                        </label>
                        <div class="controls updatable elements">
                            <input type="text" name="reportFromDate" id="reportFromDate" data-dojo-id="reportFromDate"  onChange="reportToDate.constraints.min = arguments[0];"
                            data-dojo-type="dijit.form.DateTextBox"
                            data-dojo-props="required:'true', 
                            placeHolder: '<g:message code="common.date.placeholder"/>',
                            promptMessage:'<g:message code="common.date.prompt"/>', 
                            invalidMessage:'<g:message code="common.date.invalid"/>', 
                            constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>
                        </div>
                    </div>
                    <div class="span4 field-box">
                        <label for="reportToDate" class="control-label">            
                            <g:message code="common.toDate"/> :
                            <span class="require">*</span>
                        </label>
                        <div class="controls updatable elements">
                            <input type="text" name="reportToDate" id="reportToDate" data-dojo-id="reportToDate" onChange="reportFromDate.constraints.max = arguments[0];"
                            data-dojo-type="dijit.form.DateTextBox" data-dojo-props="required:'true', 
                            placeHolder: '<g:message code="common.date.placeholder"/>',
                            promptMessage:'<g:message code="common.date.prompt"/>', 
                            invalidMessage:'<g:message code="common.date.invalid"/>', 
                            constraints:{datePattern:'dd-MM-yyyy', strict:'true'}"/>  			
                        </div>
                    </div> 
                </div>
            </div>
            <div class="row-fluid">
                <div class="span3 field-box">
                    <label for="accountType" class="control-label">           
                        <g:message code="common.accountType"/>:
                        <span class="require">*</span>
                    </label>
                    <div class="controls updatable elements">
                        <select data-dojo-type="dijit.form.Select" name="accountType" id="accountType"
                        data-dojo-props="maxHeight: -1">
                        <option value="0"><g:message code="common.trial"/></option>
                        <option value="1"><g:message code="common.retail"/></option>
                        </select>
                    </div>
                </div>
            </div>  
            <div class="span12 field-box" style="display: none" id="accountListDiv">
                <label for="reportAccountList" class="control-label">         
                    Account:
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <select name="accounts" id="reportAccountList" multiple="multiple" ></select>
                    <!--<div id="invoiceItemAccountList"></div>-->
                </div>
            </div>
            <div class="span12"></div>
            <div class="row-fluid">
                <button data-dojo-type="dijit.form.Button" onclick="SignUpReport.generate();" class="generatebtn">
                    <g:message code="default.button.generate.common"/>
                </button>
                <button data-dojo-type="dijit.form.Button" onclick="SignUpReport.cancel();" class="cancelbtn">
                    <g:message code="common.cancel"/>
                </button>
            </div>
        </form>
    </div>
</div> 
<div class="row-fluid" id="actionButtonDiv" style="display: none">
    <div class="span2 offset10">
        <a href='' id="currentCsv" class="generatebtn_csv"><div class="csv_icon"></div><g:message code="common.csv"/></a>
        <a href='' id="currentPdf" class="generatebtn_pdf"><div class="pdf_icon"></div><g:message code="common.pdf"/></a>
    </div>
</div>
<div class="alert alert-danger errorMessage" id="signUpReportErrorDiv">
    <span id="signUpReportContent"><g:message code="common.pleaseVerifyDate"/></span>
</div>
<div class="row-fluid">
    <iframe class="span12" height="1000px" id="currentReport" name="cuurrentInvoiceFrame" frameBorder="0">
    </iframe>
</div>
