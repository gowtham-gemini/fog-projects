<!DOCTYPE html>
<html lang="${lang}">
  <head>
    <meta charset="utf-8">
    <title>${orgName}</title>
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  
          media="screen" type="text/css" />    
    <link rel="stylesheet" href = "${resource(dir: 'js/dojo-1.8/dojox/widget/Wizard/', file: 'Wizard.css')}"  media="screen" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'style.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}"/>
    <link rel="stylesheet" type="text/css" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'dojo-detail-wrap.css')}" media="screen">        
    <link href="${resource(dir: 'css/', file: 'layout.css')}" rel="stylesheet" type="text/css" />
    <link href="${resource(dir: 'css/', file: 'icons.css')}" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${resource(dir: 'css/compiled/', file: 'new-user.css')}" media="screen">   
    <link rel="stylesheet" href="${resource(dir: 'css/', file: 'tables.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/compiled/', file: 'index.css')}" media="screen">                
    <script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="${resource(dir: 'js')}/bootstrap.min.js" ></script>
    <link rel="stylesheet" type="text/css" href = "${resource(dir:'js/dojo-1.8/dojox/grid/resources/', 
      file: 'claroGrid.css')}"/>
      <link type="text/css" rel="stylesheet"
            href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster',file: 'Toaster.css')}"/>
   <g:if test="${GrailsUtil.isDevelopmentEnv()}" >
        <script>
         var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 1,
            parseOnLoad: true,
            tlmSiblingOfDojo: true,
            locale: '${lang}',
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js" data-dojo-config="async: true, parseOnLoad:true"></script>
    </g:if >
    <g:else >
         <script>
         var dojoConfig = {
            async: 1,
            ioPublish: true,
            cacheBust: 0,
            parseOnLoad: false,
            tlmSiblingOfDojo: true,
            locale: '${lang}',
            packages: [
              { name: "bootstrap", location: "http://dev.dojobootstrap.com/2x" },
              { name: "experimental", location: "http://dev.dojobootstrap.com/2x/experimental" },
            ]
          };
        </script>
        <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo.js" data-dojo-config = "async: true, parseOnLoad:true"></script>
        <script type="text/javascript" src="${resource(dir: 'js/dojo-1.8/release/dojo/dojo')}/dojo-x-build.js"></script>
    </g:else>
    <script type="text/javascript" src = "${resource(dir: 'js/app')}/payment.js"></script>
      
    <script type="text/javascript">      
          payment.setConfig({
             baseURL: "${baseUrl}",
             homepage: "",
             context: "${contextPath}"
           });        
    </script>        
  </head>
  <body class="fog-classic">
    <div id="screen-loader"></div> 
    <div class="loader" id="loader">
      <div class="loader-cont">
        <div>
          <g:if test="${logoUrl == ''}">                 
              <img src="${resource(dir: 'images', file: 'fog_logo.png')}" class="logo fogPanelLogo"/>                 
          </g:if>
          <g:else>                 
              <img src="${logoUrl}" class="logo fogPanelLogo" />               
          </g:else>
        </div>
        <div>
          <p class="col-md-2" style="text-align: center"><g:message code="common.message.loading" /></p>
        </div>         
      </div>
    </div>
    <div class="row">
        <div class="navbar navbar-inverse">
          <div data-dojo-type="FogPanel.Navigator" id="userTemplateNavigator">          
            <a class="brand" href="">
              <img height="49" width="133" src="${logoUrl}" class="fogPanelLogo">
            </a>
            <div data-dojo-type="dijit.Toolbar" class="nav pull-right"> 
             <span >
              <sec:ifLoggedIn>
                 <span  style="color: #fff"><g:message code="common.loggedIn" /><sec:username/></span>
              </sec:ifLoggedIn>
              <sec:ifSwitched>            - 
                <a href='${request.contextPath}/j_spring_security_exit_user'>
                  <g:message code="common.resumeAs" /> <sec:switchedUserOriginalUsername/>
                </a>
              </sec:ifSwitched>
            </span>
              <g:link controller='logout' class="icon-share-alt" ></g:link>
                </div>
            </div>
          </div>    
    </div>  
	<div class="content-payment-panel">
  <div class="row">
    <div id="main-stats">
      <div class="row stats-row">
         <div class="col-md-4 stat">
              <div class="data">
                
                <div class="col-md-7 price_info_value">
                  <div class="number">
                    <sup id="currentDueCurrency" class="col-md-2"></sup>
                     <span  id="currentDue" class="col-md-6"></span>
                  </div>
                </div>
                <div class="col-md-5 price_info_details">
                    <div class="col-md-12 info_tags"><g:message code="stat.user.currentDue" /></div>
                </div>                       
              </div>

          </div>
          <div class="col-md-4 stat">
              <div class="data">
                <div class="col-md-7 price_info_value">
                  <div class="number">
                    <sup id="creditCurrency" class="col-md-2"></sup>
                     <span  id="credit" class="col-md-6"></span>
                  </div>
                </div>
                <div class="col-md-5 price_info_details">
                    <div class="col-md-12 info_tags"><g:message code="stat.user.credit" /></div>
                </div> 
              </div>

          </div>
          <div class="col-md-4 stat">
              <div class="data">
                <div class="col-md-7 price_info_value">
                  <div class="number">
                    <sup id="PaymentsCurrency" class="col-md-2"></sup>
                     <span  id="Payments" class="col-md-6"></span>
                  </div>
                </div>
                <div class="col-md-5 price_info_details">
                    <div class="col-md-12 info_tags"><g:message code="menu.admin.billing.payments" />
                <p> <span  id="PaymentPeriod"></span></p></div>
                </div>          
              </div>            
          </div>
      </div>
    </div>
  </div>
<div id="pad-wrapper">
  <div class="table-wrapper products-table">
    <div class="row filter-block">
    <div class="alert alert-danger errorMessage" id="" style="display: block;">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.account.suspend.info" />
    </div>
      <div class="pull-right">
        <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="payment.showTicketDialog()">
           <g:message code="common.contact" /> 
        </button> 
      </div>
    </div>
    <div class="row" style="display: none;">
      <div class="col-md-12" id="paymentListGrid"></div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="paymentDialog" title="<g:message code="common.makePayment" /> " class="col-md-5">  
<div class="new-user"> 
    <div class="row form-wrapper"> 
    <div class="col-md-12"> 
      <!--<div class="container">-->
      <form id="manualPaymentForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
          <!--<div class="row" id="">--> 
            <!--<div class="row">-->
              <div class="col-md-12 field-box control-group">
                <label for="paymentAmount" class="control-label">
                   <span  class="require">*</span>
                  <g:message code="common.payment.paymentAmount" />
                </label>
                <div class="controls elements">
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox" id="paymentAmount"
                         data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: '<g:message code="common.value" />',
                           promptMessage:'<g:message code="common.value" />', invalidMessage:'<g:message code="common.value.invalid" />'">  
                </div>
              </div> 
            <!--</div>-->
            <!--<div class="row" >-->
              <div class="col-md-12 field-box control-group" id="defaultCardDetails"> 
                <label for="useDefaultCard" class="control-label">
                  <g:message code="common.defaulPaymentCard" />:
                </label>
                <div class="controls elements">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                     data-dojo-props="checked: true" id="useDefaultCard" onchange="payment.enableCard(this)">
                </div>
              </div>
            <!--</div>-->
            <div id="cardDetails" style="display: none">
              <!--<div class="row">-->
                <div class="col-md-12 field-box control-group"> 
                  <label for="cardNumber" class="control-label">
                     <span  class="require">*</span>
                  <g:message code="common.cardNumber" />:
                  </label>
                  <div class="controls elements">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cardNumber"
                     data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.cardNumber" />', required: 'true',
                       placeHolder: '<g:message code="common.promptMessage.cardNumber" />', regExp: '[0-9]{16,16}'">
                  </div>
                </div> 
              <!--</div>-->
              <!--<div class="row">-->
                <div class="col-md-12 field-box control-group"> 
                  <label for="cvvNumber" class="control-label">
                     <span  class="require">*</span>
                  <g:message code="common.cvvNumber" />
                  </label>
                  <div class="controls elements">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cvvNumber"
                     data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.CVV" />', required: 'true',
                       placeHolder: '<g:message code="common.promptMessage.CVV" />', regExp: '[0-9]{3,3}'">
                  </div>
                </div> 
              <!--</div>-->
              <!--<div class="row">-->
                <div class="col-md-12 field-box control-group"> 
                    <label class="control-label" for="cardType"><g:message code="common.cardType" /></label>
                  <div class="controls elements">
                    <select class="valid" name="cardType" data-dojo-type="dijit.form.Select" 
                            data-dojo-props="maxHeight: -1" id="cardType">
                      <option value="visa"><g:message code="common.visa" /></option>
                      <option value="mastercard"><g:message code="common.master" /></option>
                      <option value="americanexpress"><g:message code="common.americanExpress" /></option>
                    </select>
                  </div>
                </div>
              <!--</div>-->
              <!--<div class="row">-->
                <div class="col-md-12 field-box control-group"> 
                    <label class="control-label" for="expiryMonth"><g:message code="common.month" /></label>
                  <div class="controls elements">
                    <select class="valid" name="expiryMonth" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: -1" id="expiryMonth">
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                      <option value="6">6</option>
                      <option value="7">7</option>
                      <option value="8">8</option>
                      <option value="9">9</option>
                      <option value="10">10</option>
                      <option value="11">11</option>
                      <option value="12">12</option>
                    </select>
                  </div>
                </div>
              <!--</div>-->
              <!--<div class="row">-->
                <div class="col-md-12 field-box control-group"> 
                    <label class="control-label" for="expiryYear"><g:message code="common.expiryYear" /></label>
                  <div class="controls elements">
                    <div id="yearList"></div>
                  </div>
                </div>
              <!--</div>-->
            <!--</div>-->      
          <!--</div>-->
          
          </div>
          <div class="col-md-12 field-box control-group">
            <button data-dojo-type="dijit.form.Button"
                    onclick="payment.add();" class="defaultbtn">
              <g:message code="common.payNow" />
            </button>
            <button data-dojo-type="dijit.form.Button"
                    onclick="payment.cancel();" class="cancelbtn">
              <g:message code="common.cancel" />
            </button>
          </div>
        </form> 
      </div>
      </div>
    </div>
  </div>    
  <div data-dojo-type="dijit.Dialog" id="summaryDialog" 
     title="Summary" class="col-md-12">  

  <div id="pad-wrapper" class="new-user"> 
    <div class="row form-wrapper"> 
      <div class="col-md-12"> 
         <iframe class="col-md-12" height="600px" frameborder="0" id="currentInvoice" name="cuurrentInvoiceFrame"></iframe>     
      </div>
    </div>
  </div>
</div>  

<div data-dojo-type="dijit.Dialog" id="supportDialog" 
     title="<g:message code="menu.user.support" />" class="col-md-8">  

  <div id="" class="new-user"> 
    <div class="row form-wrapper"> 
      <div class="col-md-10">          
          <div class="row form-wrapper" id="pad-wrapper">
  <div class="col-md-8" id="addTicketPage"> 
  <form class="form-horizontal" id="addTicketForm" data-dojo-type="dijit.form.Form">
        <div id="cardDetails">
          <div class="col-md-12 control-group field-box">
              <label for="supportDepWidget" class="control-label">
                 <g:message code="common.department" />
                 <span  class="require">*</span>
              </label>
              <div class="controls">
                <div id="supportDepList"></div>
              </div>
          </div>
          <div class="col-md-12 control-group field-box">
              <label for="ticketPriority" class="control-label">
                <g:message code="common.priority" />
                 <span  class="require">*</span>
              </label>
              <div class="controls">
                <select class="valid" name="ticketPriority" data-dojo-type="dijit.form.Select" 
                      data-dojo-props="maxHeight: -1" id="ticketPriority">
                <option value="LOW"><g:message code="common.low" /></option>
                <option value="NORMAL"><g:message code="common.normal" /></option>
                <option value="HIGH"><g:message code="common.high" /></option>
              </select>
              </div>
          </div>
          <div class="col-md-12 control-group field-box">
              <label for="addTicketSubject" class="control-label">
                <g:message code="common.subject" />
                 <span  class="require">*</span>
              </label>
              <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="addTicketSubject"
                 data-dojo-props="invalidMessage: '<g:message code="common.subject.invalid" />', required: 'true',
                   placeHolder: '<g:message code="common.subject.placeHolder" />'">
              </div>
          </div>
          <div class="col-md-12 control-group field-box">
              <label for="addTicketContent" class="control-label">
                <g:message code="common.content" />
                 <span  class="require">*</span>
              </label>
              <div class="controls">
                <textarea class="col-md-12" rows="12" cols="250"  id="addTicketContent"></textarea>
              </div>
          </div>
        </div>      
        <div class="pull-right">
          <button id="addTicketButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="payment.ticketAdd();">
          <g:message code="common.send" />
        </button>
          <img id="addTicketLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
          <button id="cancelTicketButton" class="cancelbtn" data-dojo-type="dijit.form.Button"  onclick="payment.ticketCancel();">
           <g:message code="common.cancel" />
        </button>
      </div>
      </form> 
  </div>
</div>
      </div>
    </div>
  </div>
</div> 
  
  
  
    
<div data-dojo-type="dijit.Dialog" id="paymentLoader" class="col-md-8">
  <div class="row" id="processPaymentMessage" style="display: block">
    <img src="images/configLoader.gif" class="col-md-1 spacing"/>
    <p class="message col-md-10"> <g:message code="common.payment.processing" /></p>
    </div>
<!--    <div class="row hide_lable" id="successMessage">
    <img src="images/successMsg.jpg" class="col-md-1 spacing"/>
    <p class="message col-md-9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>-->
</div>
<div data-dojo-type="dojox.widget.Toaster" data-dojo-props="positionDirection:'tr-left'" id="paymentToaster">
</div>
</div>
</body>
<script type="text/javascript">
  payment.require();
</script>
</html>
    