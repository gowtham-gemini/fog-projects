<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Invoice</title>
<!--    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/', file: 'invoice.css')}" media="all"/>-->
    <style type="text/css" media="all">
            .invoice_frame{
                    border:1px solid #dad7d7;
                    -webkit-border-radius: 8px;
                border-radius: 8px;
                    padding:15px;
                    font-size:12px;
                    -webkit-box-shadow:  0px 0px 2px 2px rgba(164, 164, 164, 0.5);
                box-shadow:  0px 0px 2px 2px rgba(164, 164, 164, 0.5);
            }
            .invoice-acc-no-label{
                    white-space:nowrap;
                    text-align:left;
            }
            .invoice-acc-no-label h4{
                    text-align:left;
                    margin:0;
                    line-height:35px;
            }
            .invoice-acc-no{
                    border:1px solid #dad7d7;
                    -webkit-border-radius: 8px;
                border-radius: 8px;
                    padding:8px;
                    -webkit-box-shadow: inset 2px 2px 3px 1px rgba(163, 163, 163, 0.2);
                box-shadow: inset 2px 2px 3px 1px rgba(163, 163, 163, 0.2);
                    color:#818181;
                    font-weight:700;
                    font-size:14px;
            }
            .invoice-date-numb-label{
                    color:#4e4e4e;
                    font-size:14px;
                    font-weight:700;
                    float:left;
            }
            .invoice-date-numb-info{
                    color:#000;
                    font-size:14px;
                    font-weight:700;
                    float:left;
            }
            .generatebtn_csv, .generatebtn_pdf{
                    background: #ffffff !important;
                    border:1px solid #D0DDE9 !important;
                    /*text-shadow:1px 1px 0 rgba(0, 0, 0, 0.3) !important;*/
                    color:#626263 !important;
                    -webkit-box-shadow:  0px 0px 0px 0px !important;
                box-shadow:  0px 0px 0px 0px !important;
                    padding:3px 5px;
                    margin:10px 0px 3px 10px;
                    font-weight:400;
                    -moz-border-radius: 4px;
                    border-radius: 4px;
                    float:left;
                    font-family: 'Open Sans', sans-serif;
                    font-size:12px;
                    text-decoration:none;
            }
            .generatebtn_csv:hover, .generatebtn_pdf:hover{
                    text-decoration:none;
                    color:#000000 !important; 
            }
            .pdf_icon{
                    background:url(../images/pdf.png) no-repeat;
                    width:16px;
                    height:16px;
                    float:left;
                    margin-right:5px;
            }
            .pagebreak{
                    border-bottom:1px solid #dad7d7;
            }
            .duedate_cont{
                    background:#e74c3c;
                    border:1px solid #b63d31;
                    color:#fff;
                    font-size:12px;
                    font-weight:700;
                    -webkit-border-radius: 8px;
                border-radius: 8px;
                    padding:8px;
                    margin:8px 0;
            }
            .value_dollar{
                    background:#2c3e50;
                    /*border:1px solid #34495e;*/
                    color:#fff;
                    font-size:12px;
                    font-weight:700;
                    -webkit-border-radius: 8px;
                border-radius: 8px;
                    padding:0 4px 0 25px;
                    margin:8px 0;
                    text-align:center;
                    width : auto;
            }
            .invoice_pricebox_ui{
                    width:840px;
                    height:106px;
                    margin:0 auto;
                    background:url(../images/invoice_pricebox_ui_refund.png) no-repeat;
            }
            .invoice_pricebox_ui_wotrefund{
                    width:670px;
                    height:106px;
                    margin:0 auto;
                    background:url(../images/invoice_pricebox_ui.png) no-repeat;
            }
            .invoice_pricebox_ui ul, .invoice_pricebox_ui_wotrefund ul{
                    margin:0;
                    padding:0;
                    list-style:none;
            }
            .invoice_pricebox_ui ul li, .invoice_pricebox_ui_wotrefund ul li{
                    margin:0;
                    padding:0;
                    float:left;
                    display:block;
                    text-align:center;
                    font-size:14px;
                    font-weight:700;
                    color:#4e4e4e;
            }
            .invoice_pricebox_ui ul li.prev-bal, .invoice_pricebox_ui_wotrefund ul li.prev-bal{
                    width:157px;
                    margin:0 12px 0 0;
            }
            .prev-bal .amtvalue{
                    color:#d35400;
            }
            .invoice_pricebox_ui ul li.payment, .invoice_pricebox_ui_wotrefund ul li.payment{
                    width:157px;
                    margin:0 14px 0 0;
            }
            .payment .amtvalue{
                    color:#16a085;
            }
            .invoice_pricebox_ui ul li.credit, .invoice_pricebox_ui_wotrefund ul li.credit{
                    width:157px;
                    margin:0 14px 0 0;
            }
            .credit .amtvalue{
                    color:#3498db;
            }
            .credit_refund_wrapper{
                    width:124px;
                    line-height:18px;
                    margin:8px 0 0 15px;
            }
            .creditamt_cont{
                    font-size:12px;
                    color:#4e4e4e;
                    float:right;
                    margin-top:5px;
                    width:150px;
                    text-align:center;
                    font-style:italic;
            }
            .creditamt_cont span{
                    color:#F00;
            }
            .refundamt_cont{
                    font-size:14px;
                    color:#4e4e4e;
            }
            .refundamt_cont span{
                    font-size:14px;
                    display:block;
                    color:#3498db;
            }

            .invoice_pricebox_ui ul li.curr-chrg, .invoice_pricebox_ui_wotrefund ul li.curr-chrg{
                    width:157px;
                    margin:0 14px 0 0;
            }
            .invoice_pricebox_ui ul li.total-pay, .invoice_pricebox_ui_wotrefund ul li.total-pay{
                    width:157px;
                    color:#fff;
                    text-shadow: 1px 1px 0px #5193b8;
                filter: dropshadow(color=#5193b8, offx=1, offy=1);
            }
            .amtvalue{
                    font-size:30px;
                    line-height:80px;
            }
            /*-------------------------------------------------------------
            CURRENT USAGE INFO CONTAINER
            --------------------------------------------------------------*/
            .current-usage-info-cont{
                    background: #ffffff; /* Old browsers */
            background: -moz-linear-gradient(top, #ffffff 0%, #ededed 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(100%,#ededed)); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top, #ffffff 0%,#ededed 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top, #ffffff 0%,#ededed 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top, #ffffff 0%,#ededed 100%); /* IE10+ */
            background: linear-gradient(to bottom, #ffffff 0%,#ededed 100%); /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#ededed',GradientType=0 ); /* IE6-9 */
                    margin-top:30px;
            /*	border:1px solid #dad7d7;
            */	-webkit-border-radius: 8px;
                border-radius: 8px;
                    -webkit-box-shadow:  0px 0px 2px 2px rgba(164, 164, 164, 0.5);
                box-shadow:  0px 0px 2px 2px rgba(164, 164, 164, 0.5);
            }
            .current-usage-info-zone ul, .current-usage-info-vms ul, .current-usage-info-vms-desc ul{
                    margin:0;
                    padding:0;
                    list-style:none;
            }
            .current-usage-info-zone ul li, .current-usage-info-vms ul li, .current-usage-info-vms-desc ul li{
                    margin:0;
                    padding:0;
                    display:block;
            }
            .current-usage-info-zone{
                    background:#2c3e50;
                    -webkit-border-radius: 8px 0 0 8px;
                border-radius: 8px 0 0 8px;
                    font-weight:700;
            }
            .current-usage-info-vms{
                    background:#3f5972;
                    font-weight:700;
                    /*margin-left:0 !important;*/
                    /*-webkit-box-shadow: inset 3px 3px 2px 1px rgba(0, 0, 0, 0.5);
                box-shadow: inset 3px 3px 2px 1px rgba(0, 0, 0, 0.5);*/
            }
            .current-usage-info-vms-desc{
                    /*display:none!important;*/
                    /*position:absolute;*/
                    top:0;
                    right:0%;
                    background:#d6dee5;
                    -webkit-border-radius: 0 8px 8px 0;
                border-radius: 0 8px 8px 0;
                    /*margin-left:0 !important;*/
                    /*-webkit-box-shadow: inset 2px 0px 2px 0px rgba(0, 0, 0, 0.5);
                box-shadow: inset 2px 0px 2px 0px rgba(0, 0, 0, 0.5);*/
                    min-height:640px;
                    /*padding:10px;*/
            }
            .cuinfo-zone-item{
                    cursor: pointer;
                    padding:10px 8px !important;
                    /*margin:8px !important;*/
                    border-top:1px solid #34495e;
                    color:#fff;
            }
            .cuinfo-zone-item:hover{
                    background:#1c2834;
            }
            .cuinfo-zone-item-liactive{
                    background:#1c2834;
            }
            .cuinfo-zone-item-active{
            /*	width: 0px;
                    height: 0px;*/
                    border-style: solid;
                    border-width: 20px 0 20px 12px;
                    border-color: transparent transparent transparent #1c2834;
                    position:absolute;
                    margin-left:22.7% !important;
                    margin-top:-2.8%;
                    display:none;
                    z-index:500;
            }
            @media (max-width: 1024px) {
              .cuinfo-zone-item-active{
                    margin-left:22% !important;
                    margin-top:-3.6%;
            }
            }
            .cuinfo-zone-item i{
                    margin-right:5px;
            }
            .cuinfo-zone-item-title{
                    background:#ecf0f1;
                    font-size:18px;
                    font-weight:300;
                    padding:8px 5px !important;
            }
            .cuinfo-zone-item-title-price{
                    background:#2C3E50;
                    -webkit-border-radius: 4px;
                border-radius: 4px;
                    padding:4px;
                    float: right;
                    color:#ecf0f1;
                    margin-top:-4px;
                    font-size:14px !important;
                    font-weight:300;
            }
            .cuinfo-zone-item-price{
                    background:#ecf0f1;
                    -webkit-border-radius: 4px;
                border-radius: 4px;
                    padding:4px;
                    float: right;
                    color:#2C3E50;
                    margin-top:-5px;
                    font-weight:300;
            }
            .cuinfo-vms-item{
                    cursor: pointer;
                    padding:10px 8px 10px 15px !important;
                    /*margin:8px !important;*/
                    border-top:1px solid #486480;
                    color:#fff;
            }
            .cuinfo-vms-item:hover{
                    background:#33495d;
            }
            .cuinfo-vms-item-liactive{
                    background:#33495d;
            }
            .cuinfo-vms-item-active{
            /*	width: 0px;
                    height: 0px;*/
                    border-style: solid;
                    border-width: 20px 0 20px 15px;
                    border-color: transparent transparent transparent #33495d;
                    position:absolute;
                    margin-left:22% !important;
                    margin-top:-2.8%;
                    display:none;
                    z-index:500;
            }
            @media (max-width: 1024px) {
              .cuinfo-vms-item-active{
                    margin-left:21.2% !important;
                    margin-top:-3.6%;
            }
            }
            .cuinfo-vsm-item-price{
                    background:#ECF0F1;
                    -webkit-border-radius: 4px;
                border-radius: 4px;
                    padding:4px;
                    float: right;
                    color:#2C3E50;
                    margin-top:-5px;
                    font-weight:300;
            }
            .cuinfo-vms-item-title{
                    background:#ecf0f1;
                    font-size:18px;
                    font-weight:300;
                    padding:8px 5px !important;
            }
            .cuinfo-vms-item-title-price{
                    background:#2C3E50;
                    -webkit-border-radius: 4px;
                border-radius: 4px;
                    padding:4px;
                    float: right;
                    color:#ecf0f1;
                    margin-top:-4px;
                    font-size:14px !important;
                    font-weight:300;
            }
            .cuinfo-vms-item-infobox{
            }
            .cuinfo-vms-desc-item-title{
                    background:#ecf0f1;
                    font-size:18px;
                    font-weight:300;
                    padding:8px 5px !important;
            }
            .cuinfo-vms-desc-item-title-price{
                    background:#2C3E50;
                    -webkit-border-radius: 4px;
                border-radius: 4px;
                    padding:4px;
                    float: right;
                    color:#ecf0f1;
                    margin-top:-4px;
                    font-size:14px !important;
                    font-weight:300;
            }
            .cuinfo-vms-desc-table{
                    padding:5px;
            }
            /*------------------------------------------------------------
            DIV TABLE STRUCTURE
            ----------------------------------------------------------------*/
            .divTable { width: 100%; height: auto; display: table; border-right: 1px solid #cacaca; border-left: 1px solid #cacaca;-webkit-box-shadow: 1px 1px 2px 0px rgba(184, 184, 184, 0.8); box-shadow: 1px 1px 2px 0px rgba(184, 184, 184, 0.8); margin-bottom:15px; }

            .divTableRow {max-width: 100%; height: auto; display: table-row;}
            .divTableRow_title {width: 100%; height: auto; display: table-row;}

            .divTableRow_title .divTableCell { width: 10%; height: auto; display: table-cell; border-top: 1px solid #cacaca; border-left: 1px solid #cacaca; border-bottom: 1px solid #cacaca; border-right:0; text-align:center; line-height:20px; padding:5px; background: #ffffff; color:#1C2834; font-weight:700; font-size:12px;}
            .divTableRow_title .divTableCell.first{border-left: 0px;}

            .divTableCell { 
                width: 10%;
                height: auto; 
                display: table-cell; 
                border-right: 0px solid #e9eaef; 
                border-bottom: 1px solid #b5b7c3; 
                border-left: 1px solid #e9eaef; 
                text-align:center; 
                line-height:20px; 
                padding:5px; 
                background:#f3f4f8;
                white-space:nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                max-width: 0;
            }

            /*-----------------------------------------------------------------*/
            .content {
                width: 100%;
                display: block;
                clear: both;
            }

            .content .leftContent {
                float: left;
            }

            .content .leftContent h5, h4, img {
                margin: 0 0 0 40px;
                padding: 0;
                text-align: left;
                font-family: sans-serif;

            }

            .content .leftContent h4 {
                font-family: sans-serif;
                color: #575757;
               font-size: 20px;
               padding-bottom: 10px; 
            }

            .content .resultContent span {
                font-size: 11px;
                font-weight: bold; 
                margin-left: 17px;   
                float: left;
                margin-top: 3px;
            }

            .content .resultContent label {
                font-size: 10px;
                font-weight: bold;
                float: left;
                width: 200px;
                line-height: 20px;
                text-align: right;
                font-family: sans-serif;
            }

            .content .resultContent .mainContent {
                border: 1px solid #000000;
                float: right;
                /*margin-right: 40px;*/
                width: 80px;
            }


            .content .resultContent .mainContent span {
                float: right;   
                padding: 5px;
                /*font-size: 12px;*/
            }
            .content .resultContent .offset1 {
                margin-left: 50px;
            }

            .content .resultContent .offset2 {
                margin-left: 90px;
            }
            .content .resultContent .offset8 {
                margin-left: 345px;
            }
            .content .rigthContent {
                float: right;
                margin-bottom: 10px;
            }
            .content h2 {
                font-family: sans-serif;
                font-weight: normal;
                text-align: center;
                font-size: 23px;
            }

            .content ul li {
                font-size: 12px;
                font-weight: normal;    
                font-family: sans-serif;
            }

            .content ul {
                clear: both;
                margin: 0px;
                list-style-type: none;
                text-align: left;
            }

            .content p {
                text-align: right;
                font-size: 8px;
                font-family: sans-serif;
                margin: 0;
                line-height: 12px;

            }
            .content h6 {
                text-align: left;
                margin: 0;
                font-family: sans-serif;
                padding: 3px;

            }

            .content .invoiceDetails {
                /*float: right;*/
                display: block;
                clear: right;
                margin: 0;
            }
            .content .rigthContent hr, .content .leftContent hr {
                display: block;
                width: 390px;
                height: 0px;
                color: #000000;
                margin-bottom: 0;
            }

            .content .leftContent hr {
                width: 60px;
                margin-left: 40px;
                margin-bottom: 0;
            }
            .content .rigthContent .invoiceDetails .labelContaicer {
                width: 175px;
                background: #CCCCCC;
                float: left;
            }
            .content .rigthContent .invoiceDetails .labelContaicer label,
            .content .rigthContent .invoiceDetails .spanContainer span {
                display: block;
                font-size: 10px;
                text-align: right;
                font-family: sans-serif;
                padding: 3px;
                border-style: solid;
                border-color: #000000;
                border-width: 1px 1px 0 1px;
                color: #000000;
            }

            .content .rigthContent .invoiceDetails .bottomBorder label,
            .content .rigthContent .invoiceDetails .bottomBorder span {
                border-bottom: 1px solid #000000;
            }

            .content .rigthContent .invoiceDetails .spanContainer span {
                border-left: 0px;
            }



            .content .rigthContent .invoiceDetails .spanContainer {
                width: 120px;
                float: left;
            }

            .sample_table {

                border-collapse: collapse;
            }

            .sample_table th {
                text-align:center;   
                font-size: 11px;
                font-weight: normal; 
                border: 1px solid #000000;
                /*padding: 5px;*/
                padding: 3px 6px 3px 7px;
                background-color:#b2b2b2;
                font-family: sans-serif;
                }

            .sample_table td {
                font-family: sans-serif;
                background : #FFFFFF;
                font-size: 10px;
                text-align: right;
                padding-left: 1px;
                /*padding-right: 1px;*/
            }     
            .sample_table tr .leftAlign {
                text-align: left;
            }
            
            .sample_table tr .rightAlign {
                text-align: right;
            }

            .sample_table tr .centerAlign {
                text-align: center;
            }

            .content .summaryDetail label {
                font-size: 10px;
                font-weight: bold;
                font-family: sans-serif;
            }
            .content .summaryDetail span {
                font-size: 10px; 
                font-weight: bold; 
                margin-left: 40px;
            }

            .offset12 {
              margin-left: 980px;
            }
            .offset11 {
              margin-left: 900px;
            }
            .offset10 {
              margin-left: 820px;
            }
            .offset9 {
              margin-left: 740px;
            }
            .offset8 {
              margin-left: 660px;
            }
            .offset7 {
              margin-left: 580px;
            }
            .offset6 {
              margin-left: 500px;
            }
            .offset5 {
              margin-left: 420px;
            }
            .offset4 {
              margin-left: 340px;
            }
            .offset3 {
              margin-left: 260px;
            }
            .offset2 {
              margin-left: 180px;
            }
            .offset1 {
              margin-left: 100px;
            }
    </style>
    <style media="print" type="text/css">
        @page {
            size: 8.5in 8in;  /* width height */
            margin: 0.25in;
        }
    </style>
    <style media="screen" type="text/css">
      .content {
        width: 700px;
        display: block;
        }
    </style>
  </head>
  <body>
    <div class="content">
      <h2><g:message code="user.pdf.invoice"/></h2>
    </div>
    <div class="content">
      <div class="leftContent">
        <h4>${orgName}</h4>
        <ul class="">
          <g:each in="${orgAddressDetails}"> 
            <li>${it}</li>
          </g:each>
        </ul>
      </div>
      <div class="rigthContent">
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label><g:message code="user.pdf.invoiceDate"/></label>
          </div>
          <div class="spanContainer"> 
            <span>${invoiceDate}</span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label><g:message code="user.pdf.dueDate"/></label>
          </div>
          <div class="spanContainer"> 
            <span>${dueDate}</span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label><g:message code="user.pdf.accountNo"/></label>
          </div>
          <div class="spanContainer"> 
            <span>${userAccount.id}</span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label><g:message code="common.currency"/></label>
          </div>
          <div class="spanContainer"> 
            <span>${currency}</span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer bottomBorder">
            <label class="control-label"><g:message code="user.pdf.invoiceNo"/></label>
          </div>
          <div class="spanContainer bottomBorder"> 
            <span>0000${invoice.id}</span>
          </div>
        </div>
          </div>
          </div>
    <div class="content" style="margin-top: 160px;">
      <div class="leftContent"> 
        <h5>${userAccount.firstName}</h5>
        <ul>
          <li>${userAccount.streetAddress}</li>
          <li>${userAccount.city}</li>
          <li>${userAccount.state.stateName}</li>
          <li>${userAccount.country.countryName}</li>
          <li>${userAccount.zip}</li>
        </ul>  
      </div>
      <div class="rigthContent">
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label><g:message code="common.billing.prevBalance"/></label>
          </div>
          <div class="spanContainer"> 
            <span><g:formatNumber number="${invoice.previousBalance}" format="0.00"/></span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label><g:message code="menu.admin.billing.payments"/> (-)</label>
          </div>
          <div class="spanContainer"> 
            <span><g:formatNumber number="${invoice.payment}" format="0.00"/></span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label class="control-label"><g:message code="stat.user.credit"/> (-)</label>
          </div>
          <div class="spanContainer">
            <span><g:formatNumber number="${invoice.credit}" format="0.00"/></span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer">
            <label class="control-label"><g:message code="menu.admin.billing.currentCharge"/> (+)</label>
          </div>
          <div class="spanContainer">
            <span><g:formatNumber number="${invoice.currentDue}" format="0.00"/></span>
          </div>
        </div>
        <div class="invoiceDetails">
          <div class="labelContaicer bottomBorder">
            <label class="control-label" style="font-weight: bold"><g:message code="common.billing.totalPayable"/></label>
          </div>
          <div class="spanContainer bottomBorder">
            <span  style="font-weight: bold">
                <g:if test="${invoice.totalAmount < 0.0}">
                    0
                </g:if>
                <g:else>
                    <g:formatNumber number="${invoice.totalAmount}" format="0.00"/>
                </g:else>
            </span>
          </div>
        </div>
            <g:if test="${invoice.totalAmount < 0.0}">
                <div class="invoiceDetails" style="margin-top: 10px;">
                  <div class="labelContaicer bottomBorder">
                    <label class="control-label" style="font-weight: bold"><g:message code="common.availableCredit"/></label>
                  </div>
                  <div class="spanContainer bottomBorder">
                    <span  style="font-weight: bold"><g:formatNumber number="${invoice.totalAmount * -1}" format="0.00"/></span>
                  </div>
                </div>
            </g:if>  
        </div>
    </div>
    <div class="content">
      <p><g:message code="user.pdf.contentInfo1"/> ${orgName}</p>
      <p><g:message code="user.pdf.contentInfo2"/></p>
      <p><g:message code="user.pdf.contentInfo3.lateFeeOf"/> ${lateFee} <g:message code="user.pdf.contentInfo3.paymentReceivedByDueDate"/></p>
    </div>
    <div class="content">
      <h6><g:message code="user.pdf.chargesTitle"/>:</h6>
      <table class="sample_table">
        <tr>
          <th style="width: 200px;"><g:message code="user.pdf.serviceOffered"/></th>
          <th><g:message code="user.pdf.unit"/></th>
          <th colspan="2"><g:message code="user.pdf.unitPrice"/></th>
          <th><g:message code="common.qty"/></th>
          <th><g:message code="common.price"/></th>
          <th><g:message code="common.billing.discount"/>%</th>
          <th><g:message code="user.pdf.discountAmt"/></th>
          <th><g:message code="menu.admin.tax"/>%</th>
          <th><g:message code="user.pdf.taxAmt"/></th>
          <th colspan="2"><g:message code="common.totalAmount"/></th>
        </tr>
        <g:each in="${billableItemList}">
           <h5>${it.billableItem}</h5>  
            <g:each in="${it.invoiceItem}">
                <tr>
                    <td class="leftAlign">${it.itemName}</td>
                    <td class="leftAlign">${it.units}</td>   
                    <td>${currency}</td> 
                    <td><g:formatNumber number="${it.usageUnitPrice}" format="#.########"/></td>    
                    <td><g:formatNumber number="${it.usageUnits}" format="0.00"/></td>
                    <td><g:formatNumber number="${it.usageAmount}" format="0.00"/></td>
                    <td><g:formatNumber number="${it.discountPercent}" format="0.00"/></td>
                    <td><g:formatNumber number="${it.discountAmount}" format="0.00"/></td>
                    <td><g:formatNumber number="${it.taxPercent}" format="0.00"/></td>
                    <td><g:formatNumber number="${it.taxAmount}" format="0.00"/></td>
                    <td class="rightAlign">${currency}</td>    
                    <td><g:formatNumber number="${it.totalAmount}" format="0.00"/></td>
                </tr>
            </g:each>
        </g:each>
      </table>
    </div>
    <div class="content"> 
      <div class="rigthContent">
        <hr/>
      </div>
    </div>
    <div class="content">
      <div class="resultContent offset2">
        <label class=""><g:message code="user.pdf.totalBalTitle"/>:</label>
        <div class="mainContent">
            <g:if test="${invoice.totalAmount < 0.0}">
                   <span class="">0</span>
            </g:if>
            <g:else>
                <span class=""><g:formatNumber number="${invoice.totalAmount}" format="0.00"/></span>
            </g:else>
        </div>
      </div>
    </div>
    <div class="content">
      <h6><g:message code="common.summary.payment"/>:</h6>
      <table class="sample_table">
        <tr>
          <th><g:message code="user.pdf.tokenNo"/></th>
          <th><g:message code="user.pdf.paymentDate"/></th>
          <th><g:message code="common.amount"/></th>
          <th><g:message code="common.processingFee"/></th>
          <th><g:message code="common.totalAmount"/></th>
        </tr>
        <g:each in="${payments}">
        <tr>
          <td>${it.tokenNo}</td>
          <td>${it.date}</td>
          <td><g:formatNumber number="${it.amount}" format="0.00"/></td>
          <td><g:formatNumber number="${it.processingFee}" format="0.00"/></td>
          <td><g:formatNumber number="${it.totalAmount}" format="0.00"/></td>
        </tr>
        </g:each>
      </table>
    </div> 
  </body>
</html>
