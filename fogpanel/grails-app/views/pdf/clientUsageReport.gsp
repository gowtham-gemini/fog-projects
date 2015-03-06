<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <title>Report For Client Usage</title>
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
          size: A3 landscape;  /* width height */
          margin: 0.25in;
          }
      </style>
    </head>
<body>
  <div class="report-filter-sec">
    <g:if test="${params.forDateRange.equals("SELECTIVE")}">
        <div> <span><g:message code="common.dateRange"/>:</span> ${fdate} - ${tdate} </div>
    </g:if>
    <g:else>
       <div> <span><g:message code="common.dateRange"/>:</span><g:message code="common.all"/> </div>
    </g:else>
  </div>
      <table class="fogpanel_report_widget" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <th><g:message code="common.accountId"/></th>
            <th><g:message code="common.account"/></th>
            <th><g:message code="common.companyName"/></th>
            <th><g:message code="common.status"/></th>
            <th><g:message code="common.instance"/></th>
            <th><g:message code="common.snapshot.VMSnapshot"/></th>
            <th><g:message code="common.storage"/></th>
            <th><g:message code="common.snapshotMemory"/></th>
            <th><g:message code="common.publicIp"/></th>
            <th><g:message code="common.portForwarding"/></th>
            <th><g:message code="common.loadBalancer"/></th>
            <th><g:message code="common.totalAmount"/> (${currency})</th>
          <th><g:message code="common.totalPayable"/>(${currency})</th>
          <th><g:message code="common.totalpaid"/>(${currency})</th>
          <th><g:message code="common.avlCredit"/>(${currency})</th>
            
            
        </tr>
        <g:each in="${accountList}">
          <tr>
            <td>${it.accountId}</td>
            <td>${it.accountName}</td>
            <td>${it.companyName}</td>
            <td>${it.status}</td>
            <td>${it.vmUsed}</td>
            <td>${it.vmSnapshotUsed}</td>
            <td>${it.storageUsed}</td>
            <td>${it.snapshotUsed}</td>
            <td>${it.ipUsed}</td>
            <td>${it.portForwaded}</td>
            <td>${it.loadBalanced}</td>
            <td><g:formatNumber number="${it.totalAmount}" format="###,##0.00"/></td>
            <td><g:formatNumber number="${it.totalPayable}" format="###,##0.00"/></td>
            <td><g:formatNumber number="${it.totalPaid}" format="###,##0.00"/></td>
            <td><g:formatNumber number="${it.creditLimit}" format="###,##0.00"/></td>
          </tr>
        </g:each>
    </table>
</body>
</html>