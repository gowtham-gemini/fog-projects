<div class="row-fluid"> 
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a></li>
            <li>/</li>
            <li><g:message code="common.openstackConfig"/></li>   
        </ul>
    </div>
</div>
    <div class="row-fluid"> 
        <div class="span8">
            <form id="openstackConfigForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
                <div class="row-fluid">
                    <div class="alert alert-danger" style="display: none" id="openstackInvalidMessage">
                        <span>
                            <g:message code="admin.missingConfigError"/>
                        </span>
                    </div>
                </div>
                <div id="openstackConfigWidgets">
                    <div class ="control-group">          
                        <label for="endpointUrl" class="control-label"><g:message code="common.endpointURL"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                            id="endpointUrl" data-dojo-props="required: true,
                            invalidMessage:'Invalid Url', trim:'true', placeHolder: 'Enter endpoint url',
                            regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'">

                        </div>
                        <span id="openstackUrlLabel" class="hide_lable configLabel"></span>
                    </div>
                    <div class="control-group"> 
                        <label for="adminUUId" class="control-label">
                            <g:message code="common.adminUUId"/>:<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: 'invalid UUId', required: 'true',
                            placeHolder: 'Enter uuid'" id="adminUuid" >
                        </div>
                    </div>
                    <div class="control-group"> 
                        <label for="adminPassword" class="control-label">
                            <g:message code="common.adminPassword"/>:<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="password" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: 'invalid password',
                            required: 'required', placeHolder: 'Enter password', 
                            promptMessage:'password'" name="password" id="adminPassword">  
                        </div>
                    </div>
                </div>
                <div class="span4 pull-right">
                    <button id="" type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="openstackConfig.update()"><g:message code="common.update"/></button>
                    <button id="" type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="openstackConfig.cancel()"><g:message code="common.cancel"/></button>
                </div>
            </form>
        </div>
        <div class="span4">
            <section class="well well-small">
                <g:message code="admin.openstackConfigInfo"/>
                <ul>
                    <li><g:message code="admin.endpointURLInfo"/></li>
                    <li><g:message code="admin.adminUUIDInfo"/></li>
                    <li><g:message code="admin.adminPasswordInfo"/></li> 
                </ul>
            </section>
        </div>
    </div>
    <g:render template="configPropertieReload" />

