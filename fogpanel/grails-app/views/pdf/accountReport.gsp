<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
      <title>Report For Account</title>
      <!--<link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'report.css')}" media="all"/>-->
    <style type="text/css" media="all">
            .fogpanel_report_widget{
                    border-left:1px solid #DEE3EA;
                    font-family: 'Open Sans', sans-serif;
                    float:left;
            }
            .fogpanel_report_widget th{
                    font-size:12px;
                    font-weight:600;
                    color:#333333;
                    text-align:left;
                    width:15%;
                    white-space:nowrap;
                    padding:8px 5px;
                    border-top:1px solid #DEE3EA;
                    border-bottom:1px solid #DEE3EA;
                    border-right:1px solid #DEE3EA;
                    background:#F7F7F7;
            }
            .fogpanel_report_widget td{
                    font-size:12px;
                    color:#333333;
                    text-align:left;	
                    padding:8px 5px;
                    border-right:1px solid #DEE3EA;
                    border-bottom:1px solid #DEE3EA;
            }
            
            
            .report-filter-sec{
                background:#3B9FF3;
                color:#fff;
                font-family:'Open Sans', sans-serif;
                font-size:12px;
                margin-bottom:10px;
                padding:5px 8px;
                float:left;
                width:100%;
            }

            .report-filter-sec > div{
                float:left;
                padding:0 8px;
                border-right:1px dotted #fff;
            }
            .report-filter-sec > span{
                font-weight:700;
                font-size:14px;
                margin-right:5px;
            }
            
            
      </style>
      <style media="print">
        @page {
          size: A4 landscape;  /* width height */
          margin: 0.25in;
          }
      </style>
    </head>
<body>
    <div class="report-filter-sec">
        <g:if test="${params.forDateRange.equals("SELECTIVE")}">
            <div><span><g:message code="common.dateRange"/>: </span> ${fdate} - ${tdate}</div>
        </g:if>
        <g:else>
          <div><span><g:message code="common.dateRange"/>: </span> <g:message code="common.all"/></div>
        </g:else>
        <g:if test="${params.accountType.equals("0")}">
            <div><span><g:message code="common.accountType"/>: </span> <g:message code="common.trial"/></div>
        </g:if>
        <g:else>
          <div><span><g:message code="common.accountType"/>: </span> <g:message code="common.retail"/></div>
        </g:else>
    </div>
    <table class="fogpanel_report_widget" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <th><g:message code="common.accountNo"/></th>
          <th><g:message code="common.signUpDate"/></th>
          <th><g:message code="common.email"/></th>
          <th><g:message code="common.status"/></th>
          <th><g:message code="common.cardVerified"/></th>
          <th><g:message code="common.accountType"/></th>
          <th><g:message code="common.totalAmount"/></th>
          <th><g:message code="common.totalPayable"/></th>
          <th><g:message code="common.creditLimit"/></th>
        </tr>
        <g:each in="${accountList}">
          <tr>
            <td>${it.id}</td>
            <td>${it.signUpDate}</td>
            <td>${it.email}</td>
            <td>${it.status}</td>
            <td>${it.cardVerified}</td>
            <td>${it.accountType}</td>
            <td>${it.totalAmount}</td>
            <td>${it.totalPayable}</td>
            <td>${it.creditLimit}</td>
          </tr>
        </g:each>
    </table>
</body>
</html>