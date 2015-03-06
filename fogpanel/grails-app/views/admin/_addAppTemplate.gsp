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
      <li><g:message code="common.add" /></li>
    </ul>
  </div>
</div>
<div class="new-user">
  <div class="row-fluid header">
 
  </div>
  <div class="row-fluid form-wrapper">
    <div class="span4 createvm-banner">
  	<img src="${resource(dir: 'css/theme/fog-classic/images/services_icons')}/app_template_icon.png" height="256" width="300">
    </div>
    <!-- Right column -->
    <div class="span6" id="addAppTempPage">
      <!--<div class="container">-->
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="osTempAddForm">
              <div class="span12 field-box control-group">
                  <label for="appTempName" class="control-label">                   
                    <g:message code="common.name" />: 
                     <span class="require">*</span>
                  </label>
                <div class="controls">
                   <input type="text"
                          data-dojo-type="dijit.form.ValidationTextBox" 
                          data-dojo-props="invalidMessage: '<g:message code="common.template.invalid" />',
                          required: 'required', placeHolder: '<g:message code="common.template.prompt" />', 
                          propercase: true" 
                          name="templateName" id="appTempName">
                </div>
              </div>
              <div class="span12 field-box control-group">
                 <label for="appTempDescription" class="control-label">                   
                   <g:message code="common.description" />:
                   <span class="require">*</span>
                 </label>
                <div class="controls">
                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="appTempDescription"
                      data-dojo-props="invalidMessage: '<g:message code="common.description.invalid" />',
                      required: 'required', placeHolder: '<g:message code="common.description" />', 
                      propercase: false" >  
                </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="appTempURL" class="control-label">                    
                     <g:message code="common.url" />: 
                    <span class="require">*</span>
                  </label>
                <div class="controls">
                  <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
                        id="appTempURL" data-dojo-props="required: true,
                        invalidMessage:'<g:message code="common.url.invalid" />', trim:'true', placeHolder: '<g:message code="common.url.prompt" />',
                        regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                </div>
              </div>
            <div class="span12 field-box control-group">
              <label for="appTempUrl" class="control-label">                
                <g:message code="common.template.pasteImageURL" />: 
              </label>
              <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="placeHolder:'<g:message code="common.template.pasteImageURL" />', invalidMessage:'<g:message code="common.url.invalid" />',
                     regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+', trim:'true'" id="appTempUrl"/>
              </div>
            </div>
            <div class="span12 field-box control-group">
                  <label for="dashboardTempURL" class="control-label">                   
                    <g:message code="common.referenceURL" />:                   
                  </label>
                <div class="controls">
                  <input type="url" data-dojo-type ="dijit.form.ValidationTextBox"
                        id="appTempReferenceURL" data-dojo-props="invalidMessage:'<g:message code="common.url.invalid" />', trim:'true', placeHolder: '<g:message code="common.url.prompt" />',
                        regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+\.[A-Za-z:0-9-_%&\?\/\.=#]+'">
                </div>
              </div>
                <div class="span12 field-box control-group">
                  <label for="dashboardTempZone" class="control-label">                    
                    <g:message code="common.zone" />: 
                    <span class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="appTempZone"></div>
                  </div>
                  
              </div>
                <div class="span12 field-box control-group">
                  <label for="dashboardTempHypervisor" class="control-label">                   
                    <g:message code="common.hyperviser" />: 
                     <span class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="appTempHypervisor"></div>
                  </div>
                  
              </div>
                <div class="span12 field-box control-group">
                  <label for="appTempFormat" class="control-label">                   
                    <g:message code="common.format" />: 
                     <span class="require">*</span>
                  </label>
                  <div class="controls">
                    <div id="appTempFormatDiv"></div>
                  </div>
                  
              </div>
                <div class="span12 field-box control-group">
                  <label for="appTemplateCost" class="control-label">                   
                    <g:message code="common.cost" />:( 0 - <g:message code="common.forFree" />)
                     <span class="require">*</span>
                  </label>
                  <div class="controls">
                  <input type="text" data-dojo-type="dijit.form.NumberTextBox"
       data-dojo-props="placeHolder: '<g:message code="common.value" />',
       promptMessage:'<g:message code="common.value" />', invalidMessage:'<g:message code="common.value.invalid" />', required: true" id="appTemplateCost" value="0">  
                  </div>
              </div>
              <div class = "span12 field-box control-group">
                      <label for="appMinCpu" class="control-label">              
                        <g:message code="common.minimumCore" />: 
                        <span class="require">*</span>
                      </label>
                      <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                               id="appMinCpu" data-dojo-props="required: 'true',
                               invalidMessage: '<g:message code="common.invalid.cpuNumber" />', trim: 'true',
                               placeHolder: '<g:message code="common.cpuNumber" />', constraints:{min:1,max:3000,pattern:'#'}, timeoutChangeRate: '0.2',
                               value:1,  regExp: '[0-9]{1,5}', promptMessage:'<g:message code="common.prompt.cpuNumber" />'"
                               name="appMinCpu">
                      </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="appMinRam" class="control-label">              
                          <g:message code="common.minimumMemory" />(<g:message code="common.mb" />): 
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                          <input type="text" data-dojo-type="dijit.form.NumberSpinner"
                                 data-dojo-props="required: 'true',
                                 invalidMessage: '<g:message code="common.memory.invalid" />', trim: 'true',
                                 placeHolder: 'Minimum Memory', constraints:{min:512, max:1024000,pattern:'#'}, timeoutChangeRate: '0.2',
                                 value:512, regExp: '[0-9]{3,7}', promptMessage:'<g:message code="common.minimumMemory" />'"
                                 name="appMinRam" id="appMinRam">
                        </div>
                    </div>
                    <div class="span12 field-box control-group">
                        <label for="appOneTimeChargeable" class="control-label">
                          <g:message code="common.oneTimeChargeable" />:
                        </label>
                        <div class="control">
                        <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                               data-dojo-props="checked: false" id="appOneTimeChargeable">
                        </div>
                  </div>
                <div class="span12 field-box control-group">
                  <label for="dashboardTempOsCategoryList" class="control-label">                    
                   <g:message code="common.osCategory" />: 
                    <span class="require">*</span>
                  </label>
                  <div class="controls">
                     <div id="appTempOsCategoryList"></div>
                  </div>
                 
              </div>
                <div class="span12 field-box control-group">
                  <label for="dashboardTempOsType" class="control-label">                      
                    <g:message code="common.osType" />:     
                     <span class="require">*</span> 
                  </label>
                  <div class="controls">
                    <div id="appTempOsTypeList"></div>
                  </div>                  
              </div>     
                <div class="span12 field-box control-group" id="appTempOSArchDiv">
                  <label for="" class="control-label"><g:message code="common.architecture"/>:<span class="require">*</span></label>
                  <div class="controls">
                      <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" name="architecture" id="addAppTemp64BitRedio" value="64bit"/> 
                      <label for="addAppTemp64BitRedio" class=""><g:message code="common.64bit"/></label>
                      <input type="radio" data-dojo-type="dijit.form.RadioButton" name="architecture" id="addAppTemp32BitRedio" value="32bit"/>
                      <label for="addAppTemp32BitRedio"><g:message code="common.32bit"/></label>                         
                  </div>
              </div>
                <div class="span12 field-box control-group">
                  <label for="appTempPasswordEnabled" class="control-label">
                    <g:message code="common.passwordEnabled" />:
                  </label>
                  <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="appTempPasswordEnabled">
                  </div>
              </div>
                <div class="span12 field-box control-group">
                   <label for="appTempExtractable" class="control-label">
                     <g:message code="common.extractable" />:
                   </label>
                  <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="appTempExtractable">
                  </div>
              </div>
                <div class="span12 field-box control-group">
                  <label for="appTempPublic" class="control-label">
                     <g:message code="common.public" />:
                  </label>
                  <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="appTempPublic">
                  </div>
              </div>
                <div class="span12 field-box control-group">
                  <label for="appTempFeatured" class="control-label"><g:message code="common.featured" />::</label>
                   <div class="controls">
                  <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
           data-dojo-props="checked: false" id="appTempFeatured">
                   </div>
              </div>
              <div class="span12 field-box control-group">
                  <label for="" class="control-label">                       
                    <g:message code="common.detailDesc" />: 
                    <span class="require">*</span> 
                  </label>
                  <div class="controls">
                    <div data-dojo-type="dijit.Editor" id="appTemplateDetailDesc" class=""></div>
                  </div>                  
                </div>
                <span style="display: none" class="offset4 require" id="addAppTemplateErrorMsg"><g:message code="user.createVM.required"/></span>
            <div class="span11 field-box">                            
<!--                  <input type="reset" value="Cancel" class="reset" onclick="AddAppTemplateInfo.cancel()">
                  <span>OR</span>-->
                  <button id="appTempButton" class="offset11 defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="AddAppTemplateInfo.add()" id="dashboardTempAddButton"><g:message code="common.add" /></button>
                  <img id="appTempLoader" style="display: none; float: right" src="${resource(dir: 'images')}/preloader_circle.gif" 
             alt="<g:message code="common.reset" />" height="20" width="20">
            </div>
          </form>
      <!--</div>-->
  </div>    
  <div class="span3">

    </div>
                </div>
            </div>