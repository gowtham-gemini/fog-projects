<div class="row-fluid">
     <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a></li>
            <li>/</li>
            <li><g:message code="common.domainConfig"/></li>   
        </ul>
    </div>
</div>
<div id="" class="new-user">
    <div class="row-fluid header"></div>
    <div class="row-fluid form-wrapper">
      <!-- left column -->
        <div class="span6 with-sidebar">
            <div class="container">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="domainConfigForm">            
                    <div id="languageConfigFormPage">
                        <div class="span12 field-box control-group">
                            <label for="language" class="control-label settings_lable"><g:message code="common.domainDefault"/>:</label>
                            <div class="controls">
                                <div id="domainListDiv"></div>
                            </div>
                        </div>
                    </div>
                    <div class="pull-right">            
                        <button id="domainConfigButtton" class="defaultbtn" type="button" 
                        data-dojo-type="dijit.form.Button"  onclick="DomainConfig.showUpdateConfirmationDialog()"><g:message code="common.update"/></button>
                <!--<img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="creditLoader" style="display: none"/>-->   
                    </div>
                </form>
            </div>
        </div> 
        <div class="span6">
            <div class="new_user_form inline-input">
                <div class="span12 field-box">
                    <span id=""><g:message code="admin.defaultDomainInfo"/></span>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="doaminUpdateConfirmationdialog" class="span4">
    <p><g:message code="admin.domainUpdateMessageOne"/></p>
    <p class="alert alert-info"><g:message code="admin.domainUpdateMessageTwo"/></p>
    <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="DomainConfig.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="DomainConfig.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>