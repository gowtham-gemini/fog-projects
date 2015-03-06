<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/</li>
            <li><g:message code="common.orgBillingSetting"/></li>
        </ul>
    </div>
</div>

<div  class="new-user">
    <div class="row-fluid">
        <input type="hidden" id="countryId">
        <input type="hidden" id="stateId">   
    </div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span8 with-sidebar">
            <div class="container" id="organizationBillSettingsPage">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="organizationDetailForm">
                    <div class="span12 field-box control-group">
                        <label for="organizationName" class="control-label settings_lable"><g:message code="common.name"/>: <span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',regExp:'[a-zA-Z0-9- ]{1,200}',
                            promptMessage: '<g:message code="common.name.placeHolder"/>',missingMessage: '<g:message code="common.name.placeHolder"/>',
                            placeHolder: '<g:message code="common.name.placeHolder"/>'"
                            id="organizationName">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationEmail" class="control-label settings_lable"><g:message code="common.email"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="required:'true', promptMessage:'<g:message code="common.promptMessage.email"/>', 
                            invalidMessage:'<g:message code="common.invalidMessage.email"/>', trim:true,missingMessage:'<g:message code="common.promptMessage.email"/>', 
                            placeHolder: '<g:message code="common.promptMessage.email"/>', regExp: dojox.validate.regexp.emailAddress"
                            id="organizationEmail">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationAddress" class="control-label settings_lable">
                            <g:message code="common.address"/>:<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage:'<g:message code="common.invalidMessage.address1"/>', required: 'true',missingMessage:'<g:message code="common.promptMessage.address1"/>',
                            promptMessage:'<g:message code="common.promptMessage.address1"/>',
                            placeHolder: '<g:message code="common.promptMessage.address1"/>', regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:]{4,250}'" id="organizationAddress"> 
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationExtensionAddress" class="control-label settings_lable">
                            <g:message code="common.addressExtension"/>:
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.address2"/>', missingMessage:'<g:message code="common.promptMessage.address2"/>',
                            promptMessage:'<g:message code="common.promptMessage.address2"/>',
                            placeHolder: '<g:message code="common.promptMessage.address2"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^:]{4,250}'"
                            id="organizationExtensionAddress">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationCity" class="control-label settings_lable"><g:message code="common.city"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.city"/>', required: 'true',promptMessage:'<g:message code="common.promptMessage.city"/>',
                            missingMessage: '<g:message code="common.promptMessage.city"/>',
                            placeHolder: '<g:message code="common.promptMessage.city"/>', regExp: '[a-zA-Z0-9-_| ]{3,100}'" id="organizationCity">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organisationCountryWidget" class="control-label settings_lable"><g:message code="common.country"/>:<span class="require">*</span></label>
                        <div class="controls updatable elements">
                            <div id="billingConfigCountryList" class="selectOption"></div>
                        </div>
                    </div>            
                    <div class="span12 field-box control-group">
                        <label for="organisationStateWidget" class="control-label settings_lable"><g:message code="common.state"/>:<span class="require">*</span></label>
                        <div class="controls updatable elements">
                            <div id="billingConfigStateList"></div>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationZip" class="control-label settings_lable"><g:message code="common.zip"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.zip"/>', required: 'true',
                            promptMessage:'<g:message code="common.promptMessage.zip"/>',
                            missingMessage: '<g:message code="common.promptMessage.zip"/>',
                            placeHolder: 'Zip', regExp: '[0-9A-Z- a-z]{1,10}$'" id="organizationZip">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationPhoneNumber1" class="control-label settings_lable"><g:message code="common.pnoneNumber"/>:<span class="require">*</span></label>
                        <div class="controls">
                                    <!--<div class="row-fluid">-->
                            <div class="span3">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="required:true, promptMessage: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                missingMessage: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                invalidMessage:'<g:message code="common.invalidMessage.pnoneNumber"/>', placeHolder: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                accept: '',regExp: '[0-9-+ ]{4,20}'" id="organizationPhoneNumber1"  class="span4">
                            </div>
                            <div class="span3">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="promptMessage: '<g:message code="common.promptMessage.pnoneNumber"/>', missingMessage: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                invalidMessage:'<g:message code="common.invalidMessage.pnoneNumber"/>', placeHolder: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                regExp: '[0-9-+ ]{4,20}'" id="organizationPhoneNumber2"  class="span4">
                            </div>
                            <div class="span3">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="promptMessage: '<g:message code="common.promptMessage.pnoneNumber"/>', missingMessage: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                invalidMessage:'<g:message code="common.invalidMessage.pnoneNumber"/>', placeHolder: '<g:message code="common.promptMessage.pnoneNumber"/>',
                                accept: '',regExp: '[0-9-+ ]{4,20}'" id="organizationPhoneNumber3"  class="span4">
                            </div>
                            <!--</div>-->
                        </div>
                    </div>
                    <div class="span12 field-box  control-group">
                        <label for="organizationFaxNumber1" class="control-label settings_lable"><g:message code="common.fax"/>:<span class="require">*</span></label>
                        <div class="controls">
                                    <!--<div class="row-fluid">-->
                            <div class="span3">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="required:true, promptMessage: '<g:message code="common.promptMessage.fax"/>',missingMessage: '<g:message code="common.promptMessage.faz"/>',
                                invalidMessage:'<g:message code="common.invalidMessage.fax"/>', placeHolder: '<g:message code="common.promptMessage.fax"/>',
                                accept: '',regExp: '[0-9-+ ]{4,20}'" id="organizationFaxNumber1" class="span4">
                            </div>
                            <div class="span3">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"                    
                                data-dojo-props="promptMessage: '<g:message code="common.promptMessage.fax"/>', missingMessage: '<g:message code="common.promptMessage.fax"/>',
                                invalidMessage:'<g:message code="common.invalidMessage.fax"/>', placeHolder: '<g:message code="common.promptMessage.fax"/>',
                                accept: '',regExp: '[0-9-+ ]{4,20}'" id="organizationFaxNumber2" class="span4">
                            </div>
                            <div class="span3">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="promptMessage: '<g:message code="common.promptMessage.fax"/>',missingMessage: '<g:message code="common.promptMessage.fax"/>',
                                invalidMessage:'<g:message code="common.invalidMessage.fax"/>', placeHolder: '<g:message code="common.promptMessage.fax"/>',
                                accept: '',regExp: '[0-9-+ ]{4,20}'" id="organizationFaxNumber3" class="span4">
                            </div>
                            <!--</div>-->
                        </div>
                    </div>           
                    <div class="span12 field-box control-group">
                        <label for="organizationLogoURL" class="control-label settings_lable"><g:message code="common.logoUrl"/>:</label>
                        <div class="controls">
                            <input data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.logoUrl"/>', 
                                   invalidMessage:'<g:message code="common.invalidMessage.logoUrl"/>', trim:'true', missingMessage: '<g:message code="common.promptMessage.logoUrl"/>',
                                   placeHolder: '<g:message code="common.promptMessage.logoUrl"/>',  
                                   regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'" id="organizationLogoURL">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationBgnImgURL" class="control-label settings_lable"><g:message code="common.bgImgUrl"/>:</label>
                        <div class="controls">
                            <input data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="promptMessage:'<g:message code="common.promptMessage.bgnUrl"/>', 
                                   invalidMessage:'<g:message code="common.invalidMessage.bgnUrl"/>', trim:'true',missingMessage: '<g:message code="common.promptMessage.bgnUrl"/>',
                                   placeHolder: '<g:message code="common.promptMessage.bgnUrl"/>',  
                                   regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'" id="organizationBgnImgURL">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="organizationSignature" class="control-label settings_lable"><g:message code="common.signatureName"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="required:'true', promptMessage:'<g:message code="common.promptMessage.signature"/>',regExp:'[a-zA-Z0-9- ]{1,200}',
                            invalidMessage:'<g:message code="common.invalidMessage.signature"/>',missingMessage: '<g:message code="common.promptMessage.signature"/>',
                            placeHolder: '<g:message code="common.promptMessage.signature"/>'"
                            id="organizationSignature">
                        </div>
                    </div>


                    <div class="span12 field-box control-group">
                        <label for="organizationTermsCondition" class="control-label settings_lable"><g:message code="common.termsAndConditions"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <div data-dojo-type="dijit.Editor" id="organizationTermsCondition" class="span10"></div>
                        </div>
                    </div>
                    <div class="span3 pull-right">
                        <button id="organizationBillingButton" type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="OrganizationBilling.add()"><g:message code="common.update"/></button>
                        <img id="organizationBillingLoader" style="display: none; width: 30px" src="${resource(dir: 'images')}/preloader_circle.gif" 
                             alt="reset" height="20" width="20">

                    </div>
                </form>
            </div>                            
        </div>
        <div class="span4">
            <div class="new_user_form inline-input settings-form">

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgNameIfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgEmailInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgAddressInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgAddressExtInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgCityInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgCountryInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgStateInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgZipInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgPhoneInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgFaxInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgLogoUrlInfo"/></span>
                </div>

                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgSignatureInfo"/></span>
                </div>  
                <div class="span12 field-box">
                    <span id=""><g:message code="admin.orgTermsInfo"/></span>
                </div>  
            </div>      
        </div>
    </div>
</div>

