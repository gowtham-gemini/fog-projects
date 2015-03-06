<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/computation/services"><g:message code="menu.admin.services" /></a></li>
            <li>/</li>
            <li><a href="#/admin/template"><g:message code="menu.user.templates" /></a></li>
            <li>/</li>
            <li><a href="#/admin/template/osTemplate"><g:message code="common.osTemplate" /></a></li>
            <li>/</li>
            <li><a href="#/admin/template/osTemplate" id="currentTemplateName"></a></li>
            <li>/</li>
            <li><g:message code="common.view" /></li>
        </ul>
    </div>
</div>
<div id="pad-wrapper" class="new-user">
    <div class="row-fluid header">
        <h3 id="tesmpOsType"><g:message code="common.viewOSTemplate" /></h3>  
    </div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span9 with-sidebar" id="addOsTempPage">
            <div class="container">
                <form class="new_user_form form-horizontal" data-dojo-type="dijit.form.Form" id="osTempEditForm">
                    <div class="span12 field-box control-group">
                        <label for="dashboardEditOsName" class="control-label">                    
                            <g:message code="common.name" />: 
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text"
                            data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: '<g:message code="common.template.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.template.prompt" />', 
                            propercase: true" 
                            name="templateName" id="dashboardEditOsName">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editOsTempDescription" class="control-label">                   
                            <g:message code="common.description" />:
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="editOsTempDescription"
                            data-dojo-props="invalidMessage: '<g:message code="common.description.invalid" />',
                            required: 'required', placeHolder: '<g:message code="common.description" />', 
                            propercase: false" >  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="templateCost" class="control-label">
                            <g:message code="common.cost" />:(0 - <g:message code="common.forFree" />)<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberTextBox"
                            data-dojo-props="constraints:{max: 10000, pattern:'#.##'}, placeHolder: '<g:message code="common.cost" />',
                            promptMessage:'<g:message code="common.value" />', invalidMessage:'<g:message code="common.value.invalid" />', required: 'required'" id="editOsTempCost">  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                    
                            <g:message code="common.oneTimeChargeable" />:
                        </label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                data-dojo-props="checked: false" id="editOsTemOneTime">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempURL" class="control-label">                    
                            <g:message code="common.url" />: 
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editOsTempUrl"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempURL" class="control-label">                   
                            <g:message code="common.referenceURL" />:                   
                        </label>
                        <div class="controls">
                            <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
                        id="osTemplateReferelUrl" data-dojo-props="invalidMessage:'<g:message code="common.url.invalid" />', trim:'true', placeHolder: '<g:message code="common.url.prompt" />',
                        regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempZone" class="control-label">                    
                            <g:message code="common.zone" />: 
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editOsTempZone"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempHypervisor" class="control-label">                    
                            <g:message code="common.hyperviser" />: 
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editOsTempHypervisor"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempFormat" class="control-label">                    
                            <g:message code="common.format" />: 
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editOsTempFormat"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempOsCategoryList" class="control-label">                    
                            <g:message code="common.osCategory" />:
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <span id="editOsTempOsCategory"></span>
                        </div>
                    </div>                    
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempOsType" class="control-label">                      
                            <g:message code="common.osType" />: 
                            <span class="require">*</span> 
                        </label>
                        <div class="controls">
                            <span id="editOsTempOsType"></span>
                        </div>
                    </div>
                    <div class="span12 field-box control-group" id="osArchDiv">
                        <label for="dashboardTempOsType" class="control-label">                      
                            <g:message code="common.architecture"/>: 
                            <span class="require">*</span> 
                        </label>
                        <div class="controls">
                            <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" name="architecture" id="editOSTemp64BitRedio" value="64bit"/> 
                            <label for="editOSTemp64BitRedio" class=""><g:message code="common.64bit"/></label>
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="architecture" id="editOSTemp32BitRedio" value="32bit"/>
                            <label for="editOSTemp32BitRedio"><g:message code="common.32bit"/></label>  
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="instanceZone" class="control-label"><g:message code="common.templateType"/>:<span class="require">*</span></label>
                        <div class="controls" id="tempTypeDiv">
                            <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" name="tempType" id="osTempType" value="OS" onclick="ViewOsTemplate.checkTemplateType(this);"/>                             
                            <label for="osTempType"><g:message code="common.osTemplate"/></label> 
                            <input type="radio" data-dojo-type="dijit.form.RadioButton" name="tempType" id="appTempType" value="APP" onclick="ViewOsTemplate.checkTemplateType(this);"/>                                                       
                            <label for="appTempType"><g:message code="common.appTemplate"/></label>  
                        </div>
                    </div> 
                    <div class="span12 field-box control-group hide_text" id="appTemplateURLDiv">
                        <label for="dashboardTempURL" class="control-label">                   
                            <g:message code="common.template.pasteImageURL" /><span class="require">*</span>:                   
                        </label>
                        <div class="controls">
                             <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
                                id="osTemplateImageURL" data-dojo-props="invalidMessage:'<g:message code="common.url.invalid" />', placeHolder: '<g:message code="common.url.prompt" />',
                                regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                    
                            <g:message code="common.minimumCore" />: 
                        </label>
                        <div class="controls">
                             <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                               id="editOsTemCpu" data-dojo-props="required: 'true',
                               invalidMessage: '<g:message code="common.invalid.cpuNumber" />', trim: 'true',
                               placeHolder: '<g:message code="common.cpuNumber" />', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                               value:1,  regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.prompt.cpuNumber" />'"
                               name="editOsTemCpu">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="" class="control-label">                    
                            <g:message code="common.minimumMemory" />(<g:message code="common.mb" />): 
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                                 id="editOsTemRam" data-dojo-props="required: 'true',
                                 invalidMessage: '<g:message code="common.memory.invalid" />', trim: 'true',
                                 placeHolder: '<g:message code="common.memoryName" />', constraints:{min:512, max:1024000,pattern:'#'}, timeoutChangeRate: '0.2',
                                 value:512, regExp: '[0-9]{3,7}', promptMessage:'<g:message code="common.memoryName" />'"
                                 name="editOsTemRam">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsPasswordEnabled" class="control-label">
                            <g:message code="common.passwordEnabled" />:
                        </label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="editTempOsPasswordEnabled">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsExtractable" class="control-label">
                            <g:message code="common.extractable" />:
                        </label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="editTempOsExtractable">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsPublic" class="control-label">
                            <g:message code="common.public" />:
                        </label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="editTempOsPublic">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="editTempOsFeatured" class="control-label"><g:message code="common.featured" />:</label>
                        <div class="controls">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                            data-dojo-props="checked: false" id="editTempOsFeatured">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="dashboardTempOsType" class="control-label">                       
                            <g:message code="common.detailDesc" />: 
                            <span class="require">*</span> 
                        </label>
                            <div class="controls">
                                <div data-dojo-type="dijit.Editor" id="osTemplateDetailDescEdit" class=""></div>
                            </div>                  
                    </div>
                    <div class="span12 field-box">                                                    
                        <img class="hide_text offset10" id="viewOSTempLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                        <button class="offset10 defaultbtn" id="viewOSTempUpdateButton"  type="button" data-dojo-type="dijit.form.Button" onclick="ViewOsTemplate.updateShow()"> <g:message code="common.update" /></button>                                
                    </div>
                </form>
            </div>
        </div>    
        <div class="span3">
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="osTemplateEditConformationDialog" title="Update" class="span4">
    <p><g:message code="admin.updateItem" /></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser" /></p>
    <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ViewOsTemplate.update()"><g:message code="common.ok" /></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="ViewOsTemplate.closeUpdate()"><g:message code="common.cancel" /></button>
    </div>
</div>
