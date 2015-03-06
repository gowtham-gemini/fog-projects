<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/computation/services"><g:message code="menu.admin.services" /></a></li>
            <li>/</li>
            <li><a href="#/admin/template"><g:message code="menu.user.templates" /></a></li>
            <li>/</li>
            <li><a href="#/admin/template/appTemplate"><g:message code="common.appTemplate" /></a></li>
            <li>/</li>
            <li><a href="#/admin/template/appTemplate" id="currentAppTempName"></a></li>
            <li>/</li>
            <li><g:message code="common.view" /></li>
        </ul>
    </div>
</div>
<div id="pad-wrapper" class="new-user">
    <div class="row-fluid header">
        <h3 id="appTempOs"><g:message code="common.viewAPPTemplate" /></h3>  
    </div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span9 with-sidebar" id="appOsTempPage">
            <div class="container">
                <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="appTempEditForm">
                    <div class="span12 field-box control-group">
                        <label for="dashboardEditOsName" class="control-label">Name:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.template.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.template.prompt" />', 
                            propercase: true" name="templateName" id="EditAppTempName">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editOsTempDescription" class="control-label"><g:message code="common.description" />::<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="EditAppTempDescription"
                            data-dojo-props="invalidMessage: '<g:message code="common.description.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.description" />', 
                            propercase: false" > 
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="templateCost" class="control-label"><g:message code="common.cost" />:(0 - <g:message code="common.forFree" />)<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="constraints:{max: 10000, pattern:'#.##'}, placeHolder: '<g:message code="common.cost" />:',
                            promptMessage:'<g:message code="common.value" />', invalidMessage:'<g:message code="common.value.invalid" />', required: 'required'" id="EditAppTempCost">  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                    
                           <g:message code="common.oneTimeChargeable" />:
                        </label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                data-dojo-props="checked: false" id="editAppTemOneTime">
                        </div>
                    </div>                    
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempURL" class="control-label">                   
                            <g:message code="common.referenceURL" />:                   
                        </label>
                        <div class="controls">
                             <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
                                id="appTemplateReferelUrlEdit" data-dojo-props="invalidMessage:'<g:message code="common.url.invalid" />', trim:'true', placeHolder: '<g:message code="common.url.prompt" />',
                                regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempURL" class="control-label">                   
                            <g:message code="common.template.pasteImageURL" /><span class="require">*</span>:                   
                        </label>
                        <div class="controls">
                             <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
                                id="appTemplateImageURL" data-dojo-props="invalidMessage:'<g:message code="common.url.invalid" />', trim:'true', placeHolder: '<g:message code="common.url.prompt" />',
                                regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+', required: true">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempURL" class="control-label"> <g:message code="common.url" />:<span class="require">*</span></label>
                        <div class="controls">
                            <span id="editAppTempUrl"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempZone" class="control-label"> <g:message code="common.zone" />: <span class="require">*</span></label>
                        <div class="controls">
                            <span id="editAppTempZone"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempHypervisor" class="control-label"><g:message code="common.hyperviser" />:  
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editAppTempHypervisor"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempFormat" class="control-label"><g:message code="common.format" />: <span class="require">*</span></label>
                        <div class="controls">
                            <span id="editAppTempFormat"></span>
                        </div>
                    </div>          
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempOsCategoryList" class="control-label">                   
                            <g:message code="common.osCategory" />:
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editAppTempOsCategory"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempOsType" class="control-label"> <g:message code="common.osType" />: <span class="require">*</span></label>
                        <div class="controls">
                            <span id="editAppTempOsType"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="appArchDiv">
                        <label for="dashboardTempOsType" class="control-label">                      
                            <g:message code="common.architecture"/>: 
                            <span class="require">*</span> 
                        </label>
                        <div class="controls">
                            <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" name="architecture" id="editAppTemp64BitRedio" value="64bit"/> 
                            <label for="editAppTemp64BitRedio" class=""><g:message code="common.64bit"/></label>
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="architecture" id="editAppTemp32BitRedio" value="32bit"/>
                            <label for="editAppTemp32BitRedio"><g:message code="common.32bit"/></label>  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                    
                             <g:message code="common.minimumCore" />: 
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                               id="editAppTemCpu" data-dojo-props="required: 'true',
                               invalidMessage: '<g:message code="common.invalid.cpuNumber" />', trim: 'true',
                               placeHolder: '<g:message code="common.cpuNumber" />', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                               value:1,  regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.prompt.cpuNumber" />'"
                               name="editAppTemCpu">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                    
                            <g:message code="common.minimumMemory" />(<g:message code="common.mb" />):
                        </label>
                        <div class="controls">
                           <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                                 id="editAppTemRam" data-dojo-props="required: 'true',
                                 invalidMessage: '<g:message code="common.memory.invalid" />', trim: 'true',
                                 placeHolder: '<g:message code="common.memoryName" />', constraints:{min:512, max:1024000,pattern:'#'}, timeoutChangeRate: '0.2',
                                 value:512, regExp: '[0-9]{3,7}', promptMessage:'<g:message code="common.memoryName" />'"
                                 name="editAppTemRam">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsPasswordEnabled" class="control-label"><g:message code="common.passwordEnabled" />:</label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="EditAppTempPasswordEnabled">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsExtractable" class="control-label"><g:message code="common.extractable" />:</label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="EditAppTempExtractable">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsPublic" class="control-label"><g:message code="common.public" />:</label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="EditAppTempPublic">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsFeatured" class="control-label"><g:message code="common.featured" />:</label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="EditAppTempFeatured">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                       
                            <g:message code="common.detailDesc" />: 
                            <span class="require">*</span> 
                        </label>
                            <div class="controls">
                                <div data-dojo-type="dijit.Editor" id="appTemplateDetailDescEdit" class=""></div>
                            </div>                  
                    </div>
                    <div class="span12">                            
                        <img class="hide_text offset10" id="viewAppTempLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                        <button class="offset11 defaultbtn" id="viewAppTempUpdateButton" type="button" data-dojo-type="dijit.form.Button" onclick="ViewsAppTemplate.updateShow()"><g:message code="common.update" /></button>
                    </div>
                </form>
            </div>
        </div>    
        <div class="span3">
      <!--      <div class="new_user_form inline-input">
              <div class="span12 field-box">
                <span id="editTaxNameLabel">Name of the Tax</span>
              </div>
              <div class="span12 field-box" id ="fixedDayContainer">
                <span id="editDescriptionLabel">A detail description about the tax</span>
              </div>
              <div class="span12 field-box" id="billingPeriodDaysDescriptionContainer">
                <span id="editTaxPercentageLabel">The allocated Percentage(%) of the tax</span>
              </div>
            </div>-->
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="appTemplateEditConformationDialog" title="Update" class="span4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
    <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ViewsAppTemplate.update()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="ViewsAppTemplate.closeUpdate()"><g:message code="common.cancel" /></button>
    </div>
</div>
