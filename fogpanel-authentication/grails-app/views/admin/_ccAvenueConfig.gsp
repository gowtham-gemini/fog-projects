<div class="row-fluid">
    <div class="span12 breadcrumbs">

        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/billing"><g:message code="menu.admin.configuration.billing"/></a></li>
            <li>/</li>    
            <li><a href="#/admin/settings/paymentGatway"><g:message code="common.paymentGateway"/></a></li>
            <li>/</li>
            <li><g:message code="common.ccAvenue"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <ul class="nav nav-tabs span12">
        <li class="active">
            <a href="#/admin/settings/ccAvenue">Settings</a>
        </li>
 
    </ul>
</div>
<div  class="new-user">  
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span8 with-sidebar" id="ccAvenueCodePage">
            <div class="container">
                <form class="new_user_form inline-input form-horizontal" data-dojo-type="dijit.form.Form" id="ccAvenueForm">
                    <div class="span12 field-box control-group">
                        <label class="control-label settings_lable"><g:message code="ccAvenue.merchantId"/>:<span class="require">*</span></label>
                        <div class="controls"> 
                            <input type="text" 
                            data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                            required: 'required', placeHolder: '<g:message code="common.value"/>', 
                            promptMessage:'<g:message code="common.value"/>'" 
                            name="merchantId" id="merchantId"> 
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label class="control-label settings_lable"><g:message code="ccAvenue.workingKey"/>:<span class="require">*</span></label>
                        <div class="controls"> 
                            <input type="text" 
                            data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>',
                            required: 'required', placeHolder: '<g:message code="common.value"/>', 
                            promptMessage:'<g:message code="common.value"/>'" 
                            name="workingKey" id="workingKey"> 
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label class="control-label settings_lable"><g:message code="ccAvenue.typeOfGateway"/>:<span class="require">*</span></label>
                        <div class="controls"> 
                            <div id="gatewayList"></div>
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="paymentStatus">
                        <label class="control-label settings_lable"><g:message code="common.status"/>:
                        </label>
                        <div class="controls"> 
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="status" id="enableStatus" value="ENABLE"/>
                            <label for="enableStatus" class=""><g:message code="common.enableCCAvenue"/></label> 
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="status" id="disableStatus" value="DISABLE"/> 
                            <label for="disableStatus" class=""><g:message code="common.disableCCAvenue"/></label>  
                        </div>
                    </div>
                    <div class="pull-right">              
                        <button id="ccAvenueButton" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="CCAvenueConfig.add();">
                            <g:message code="common.update"/>
                        </button>
                        <img id="ccAvenueLoader" src='images/preloader_circle.gif' alt='Loading' height='16' width='23' style="display: none"/>
                    </div>
                </form>
            </div>
        </div>
        <div class="span4">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id="merchantIdDescription"><g:message code="ccAvenue.merchantIdInfo"/></span>
                </div>
                <div class="span12 field-box">
                    <span id="workingKeyDescription"><g:message code="ccAvenue.workingKeyInfo"/></span>
                </div>
                <div class="span12 field-box">
                    <span id="typeDescription"><g:message code="ccAvenue.typeOfGateway"/></span>
                </div>
                <div class="span12 field-box">
                    <span id="statusDescription"><g:message code="ccAvenue.statusInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>