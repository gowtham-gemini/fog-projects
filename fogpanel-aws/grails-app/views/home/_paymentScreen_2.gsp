<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Payment</title>
    <link rel="stylesheet" href="${resource(dir: 'css/theme/fog-classic/', file: 'fog-classic.css')}"  
          media="screen" type="text/css" />
    
     <link rel="shortcut icon" href="${resource(dir: 'images')}/favicon.ico" type="image/x-icon" />
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
    <link rel="stylesheet" type="text/css" 
      href = "${resource(dir:'js/dojo-1.8/dojox/grid/resources/', 
      file: 'claroGrid.css')}"/>
    <link type="text/css" rel="stylesheet"
      href="${resource(dir: 'js/dojo-1.8/dojox/widget/Toaster',
      file: 'Toaster.css')}"/>
    <script type = "text/javascript" src = "${resource(dir: 'js/dojo-1.8/dojo')}/dojo.js"
            data-dojo-config = "async: true, parseOnLoad:true">
    </script>
    <script type="text/javascript"
        src = "${resource(dir: 'js/app')}/payment.js">
    </script>
  </head>
  <body class="fog-classic">
    <div id="screen-loader"></div> 
    <div class="loader" id="loader">
      <div class="loader-cont">
        <div>
          <img src="${resource(dir: 'images')}/vmload.gif" alt="progress" height="9" width="100">
        </div>
        <div>
          <p class="span2" style="text-align: center">Loading Please wait..</p>
        </div>         
      </div>
    </div>
    <div class="row-fluid">
        <div class="navbar navbar-inverse">
          <div data-dojo-type="FogPanel.Navigator" id="userTemplateNavigator">          
            <a class="brand" href="">
              <img height="49" width="133" src="${resource(dir: 'images')}/fogpanel_logo.png">
            </a>
            <div data-dojo-type="dijit.Toolbar" class="nav pull-right"> 
            <span>
              <sec:ifLoggedIn>
                <span style="color: #fff">Logged in as <sec:username/></span>
              </sec:ifLoggedIn>
              <sec:ifSwitched>            - 
                <a href='${request.contextPath}/j_spring_security_exit_user'>
                  Resume as <sec:switchedUserOriginalUsername/>
                </a>
              </sec:ifSwitched>
            </span>
              <g:link controller='logout' class="icon-share-alt" ></g:link>
                </div>
            </div>
          </div>    
    </div>  
	<div class="content-payment-panel">
  <div class="row-fluid">
    <div id="main-stats">
      <div class="row-fluid stats-row">
         <div class="span4 stat">
              <div class="data">
                <span class="number" id="currentDue"></span>
                  Current Due
              </div>

          </div>
          <div class="span4 stat">
              <div class="data">
                <span class="number" id="credit"></span>
                  Credit
              </div>

          </div>
          <div class="span4 stat">
              <div class="data">
                <span class="number" id="Payments"></span>
                  Payments
                <p>(<span id="PaymentPeriod"></span>)</p>
              </div>            
          </div>
      </div>
    </div>
  </div>
<div id="pad-wrapper">
  <div class="table-wrapper products-table">
    <div class="row-fluid filter-block">
      <div class="pull-right">
        <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="payment.showSummary()">
          Summary
        </button>
        <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="payment.showPaymentForm()">
          +Make Payment
        </button> 
      </div>
    </div>
    <div class="row-fluid">
      <div class="span12" id="paymentListGrid"></div>
    </div>
  </div>
</div>
<div data-dojo-type="dijit.Dialog" id="paymentDialog" title="Make a payment" class="span5">  
<div class="new-user"> 
    <div class="row-fluid form-wrapper"> 
    <div class="span12"> 
      <!--<div class="container">-->
      <form id="manualPaymentForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
          <!--<div class="row-fluid" id="">--> 
            <!--<div class="row-fluid">-->
              <div class="span12 field-box control-group">
                <label for="paymentAmount" class="control-label">
                  <span class="require">*</span>
                  Payment Amount
                </label>
                <div class="controls elements">
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox" id="paymentAmount"
                         data-dojo-props="required:'true', constraints:{max: 10000000, pattern:'#.##'}, placeHolder: 'value',
                           promptMessage:'value', invalidMessage:'Invalid Value.'">  
                </div>
              </div> 
            <!--</div>-->
            <!--<div class="row-fluid" >-->
              <div class="span12 field-box control-group" id="defaultCardDetails"> 
                <label for="useDefaultCard" class="control-label">
                  Use default payment card:
                </label>
                <div class="controls elements">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                     data-dojo-props="checked: true" id="useDefaultCard" onchange="payment.enableCard(this)">
                </div>
              </div>
            <!--</div>-->
            <div id="cardDetails" style="display: none">
              <!--<div class="row-fluid">-->
                <div class="span12 field-box control-group"> 
                  <label for="cardNumber" class="control-label">
                    <span class="require">*</span>
                   Card number
                  </label>
                  <div class="controls elements">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cardNumber"
                     data-dojo-props="invalidMessage: 'invalid number', required: 'true',
                       placeHolder: 'Enter card number',  invalidMessage:'Invalid Value.', regExp: '[0-9]{16,16}'">
                  </div>
                </div> 
              <!--</div>-->
              <!--<div class="row-fluid">-->
                <div class="span12 field-box control-group"> 
                  <label for="cvvNumber" class="control-label">
                    <span class="require">*</span>
                  CVV number
                  </label>
                  <div class="controls elements">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="cvvNumber"
                     data-dojo-props="invalidMessage: 'invalid number', required: 'true',
                       placeHolder: 'Enter card number',  invalidMessage:'Invalid Value.', regExp: '[0-9]{3,3}'">
                  </div>
                </div> 
              <!--</div>-->
              <!--<div class="row-fluid">-->
                <div class="span12 field-box control-group"> 
                    <label class="control-label" for="cardType">Card Type</label>
                  <div class="controls elements">
                    <select class="valid" name="cardType" data-dojo-type="dijit.form.Select" 
                            data-dojo-props="maxHeight: -1" id="cardType">
                      <option value="visa">Visa</option>
                      <option value="mastercard">Master Card</option>
                      <option value="americanexpress">American Express</option>
                    </select>
                  </div>
                </div>
              <!--</div>-->
              <!--<div class="row-fluid">-->
                <div class="span12 field-box control-group"> 
                    <label class="control-label" for="expiryMonth">Month</label>
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
              <!--<div class="row-fluid">-->
                <div class="span12 field-box control-group"> 
                    <label class="control-label" for="expiryYear">Expiry Year</label>
                  <div class="controls elements">
                    <div id="yearList"></div>
                  </div>
                </div>
              <!--</div>-->
            <!--</div>-->      
          <!--</div>-->
          
          </div>
          <div class="span12 field-box control-group">
            <button data-dojo-type="dijit.form.Button"
                    onclick="payment.add();" class="defaultbtn">
               Pay Now
            </button>
            <button data-dojo-type="dijit.form.Button"
                    onclick="payment.cancel();" class="cancelbtn">
               Cancel
            </button>
          </div>
        </form> 
      </div>
      </div>
    </div>
  </div>    
  <div data-dojo-type="dijit.Dialog" id="summaryDialog" 
     title="Summary" class="span12">  

  <div id="pad-wrapper" class="new-user"> 
    <div class="row-fluid form-wrapper"> 
      <div class="span12"> 
         <iframe class="span12" height="600px" frameborder="0" id="currentInvoice" name="cuurrentInvoiceFrame"></iframe>     
      </div>
    </div>
  </div>
</div>      
    
<div data-dojo-type="dijit.Dialog" id="paymentLoader" 
      class="span8">
  <div class="row-fluid" id="processPaymentMessage" style="display: block">
    <img src="images/configLoader.gif" class="span1 spacing"/>
    <p class="message span10">Processing Payment</p>
    </div>
<!--    <div class="row-fluid hide_lable" id="successMessage">
    <img src="images/successMsg.jpg" class="span1 spacing"/>
    <p class="message span9 success">Success! Fogpanel and cloudstack has integrated</p>
    </div>-->
</div>
<div data-dojo-type="dojox.widget.Toaster"
         data-dojo-props="positionDirection:'tr-left'" id="paymentToaster">
</div>
</div>
</body>
<script type="text/javascript">
  payment.require();
</script>
</html>
    