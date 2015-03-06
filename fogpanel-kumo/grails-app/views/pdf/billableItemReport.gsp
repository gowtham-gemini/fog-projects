<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <title>Report For Billable Item</title>
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
      </style>
      <style media="print">
        @page {
          size: A4 landscape;  /* width height */
          margin: 0.25in;
          }
      </style>
    </head>
<body>
      <table class="fogpanel_report_widget" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <g:each in="${tableThArray}"> 
              <th>${it}</th>
          </g:each>
        </tr>
        <g:each in="${invoiceItemListArray}">
          <tr>
            <td>${it.account}</td>
            <td>${it.billableItem}</td>
            <td>${it.itemName}</td>
            <td>${it.itemReferenceId}</td>
            <td>${it.date}</td>
            <td>${it.totalAmount}</td>
          </tr>
        </g:each>
    </table>
</body>
</html>