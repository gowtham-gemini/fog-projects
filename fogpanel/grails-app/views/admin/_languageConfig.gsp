<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
                <a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a>
            <li>/</li>
            <li><g:message code="common.language"/></li>    
        </ul>
    </div>
</div>

<div id="" class="new-user">
    <div class="row-fluid header"></div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span6 with-sidebar">
            <div class="container">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="languageConfigForm">            
                    <div id="languageConfigFormPage">
                        <div class="span12 field-box control-group">
                            <label for="language" class="control-label settings_lable"><g:message code="common.selectLanguage"/>:</label>
                            <div class="controls">
                                <select name="language" id="panelLanguage" data-dojo-type="dijit/form/Select">
                                    <option value="en">English</option>
                                    <option value="es">Spanish</option>
<!--                                    <option value="ja">Japanese</option>
                                    <option value="zh_CN">Chinese (Simplified)</option>
                                    <option value="ru_RU">Russian</option>
                                    <option value="fr_FR">French</option>
                                    <option value="pt_BR">Brazilian Portugese</option>
                                    <option value="ca">Catalan</option>
                                    <option value="ko_KR">Korean</option>
                                    
                                    <option value="de_DE">German</option>
                                    <option value="it_IT">Italian</option>
                                    <option value="nb_NO">Norwegian</option>
                                    <option value="ar">Arabic</option>-->
                                </select>
                            </div>
                        </div>                        
                    </div>
                    <div class="pull-right">            
                        <button id="languageConfigButtton" class="defaultbtn" type="button" 
                        data-dojo-type="dijit.form.Button"  onclick="LanguageConfig.showUpdateConfirmationDialog()"><g:message code="common.update"/></button>
                <!--<img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>-->   
                    </div>
                </form>
            </div>
        </div> 
        <div class="span6">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id=""><g:message code="admin.defaultlangInfo"/></span>
                </div>
                <div class="span12 field-box">
                    <span id=""><g:message code="common.dateFormat"/></span>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="languageUpdateConfirmationdialog" class="span4">
    <p><g:message code="admin.languageUpdateMessageOne"/></p>
    <p class="alert alert-info"><g:message code="admin.languageUpdateMessageTwo"/></p>
    <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="LanguageConfig.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="LanguageConfig.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>