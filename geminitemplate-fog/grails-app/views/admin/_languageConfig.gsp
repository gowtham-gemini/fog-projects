<div class="row">
    <div class="col-md-12 breadcrumbs">
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
    <div class="row header"></div>
    <div class="row form-wrapper">
      <!-- left column -->
        <div class="col-md-6 with-sidebar">
            <div class="container">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="languageConfigForm">            
                    <div id="languageConfigFormPage">
                        <div class="col-md-12 field-box control-group">
                            <label for="language" class="control-label settings_lable"><g:message code="common.selectLanguage"/>:</label>
                            <div class="controls">
                                <select name="language" id="panelLanguage" data-dojo-type="dijit/form/Select">
                                    <option value="en">English</option>
                                    <option value="ja">Japanese</option>
                                    <option value="zh_CN">Chinese (Simplified)</option>
                                    <option value="ru_RU">Russian</option>
                                    <option value="fr_FR">French</option>
                                    <option value="pt_BR">Brazilian Portugese</option>
                                    <option value="ca">Catalan</option>
                                    <option value="ko_KR">Korean</option>
                                    <option value="es">col-md-ish</option>
                                    <option value="de_DE">German</option>
                                    <option value="it_IT">Italian</option>
                                    <option value="nb_NO">Norwegian</option>
                                    <option value="ar">Arabic</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12 field-box control-group">
                            <label for="language" class="control-label settings_lable"><g:message code="common.dateFormat"/>:</label>
                            <div class="controls">
                                <select name="language" data-dojo-type="dijit.form.Select" data-dojo-props="maxHeight: 100"  id="adminUserDateFormatWidget">
                                    <option value="dd/MMM/yyyy">DD/MMM/YYYY</option>
                                    <option value="MMM/dd/yyyy">MMM/DD/YYYY</option>             
                                    <option value="M/d/yyyy">M/D/YYYY</option>
                                    <option value="M/d/yy">M/D/YY</option>
                                    <option value="MM/dd/yy">MM/DD/YY</option>
                                    <option value="MM/dd/yyyy">MM/DD/YYYY</option>
                                    <option value="yy-MM-dd">YY/MM/DD</option>
                                    <option value="yyyy-MM-dd">YYYY-MM-DD</option>
                                    <option value="dd-MMM-yyyy">DD-MMM-YY</option>
                                    <option value="M-d-yyyy">M-D-yyyy</option>
                                    <option value="M-d-yy">M-D-YY</option>
                                    <option value="MM-dd-yy">MM-DD-YY</option>            
                                    <option value="MM-dd-yyyy">MM-DD-YYYY</option>            
                                    <option value="yy-MM-dd">YY-MM-DD</option>            
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">            
                        <button id="languageConfigButtton" class="defaultbtn" type="button" 
                        data-dojo-type="dijit.form.Button"  onclick="LanguageConfig.update()"><g:message code="common.update"/></button>
                <!--<img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>-->   
                    </div>
                </form>
            </div>
        </div> 
        <div class="col-md-6">
            <div class="new_user_form inline-input">
                <div class="col-md-12 field-box">
                     <span  id=""><g:message code="admin.defaultlangInfo"/></span>
                </div>
                <div class="col-md-12 field-box">
                     <span  id=""><g:message code="admin.defaulDateFormatInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>