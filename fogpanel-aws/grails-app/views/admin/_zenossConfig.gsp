<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><g:message code="common.zenossConfig"/></li>
        </ul>
    </div>
</div>
            <!--<div class="row-fluid"> <h2 style="margin-bottom: 10px;">Zenoss Config</h2></div>-->
<div class="row-fluid"> 
        <div class="span8">
            <form id="zenossConfigForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
                <div class="row-fluid">
                    <div class="alert alert-danger" style="display: none" id="zenossInvalidMessage">
                        <span>
                            <g:message code="admin.missingConfigError"/>
                        </span>
                    </div>
                </div>
                <div id="zenossConfigWidgets">
                    <div class ="control-group">          
                        <label for="endpointUrl" class="control-label"><g:message code="common.endpointURL"/>:<span class="require">*</span></label>
                        <div class="controls">
                            <input type="text" name="" data-dojo-type ="dijit.form.ValidationTextBox"
                            id="zenossEndpointUrl" data-dojo-props="required: true,
                            invalidMessage:'Invalid Url', trim:'true', placeHolder: 'Enter endpoint url',
                            regExp:'(https?|ftp)://[A-Za-z:0-9-^.]+.[A-Za-z:0-9-_%&\?\/\.=#]+'">

                        </div>
                        <span id="zenossUrlLabel" class="hide_lable configLabel"></span>
                    </div>
                    <div class="control-group"> 
                        <label for="adminUUId" class="control-label">
                            <g:message code="common.username"/>:<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: 'invalid username', required: 'true', trim: 'true',
                            placeHolder: 'Enter username'" id="zenossUsername" >
                        </div>
                    </div>
                    <div class="control-group"> 
                        <label for="adminPassword" class="control-label">
                            <g:message code="common.password"/>:<span class="require">*</span>
                        </label>
                        <div class="controls">
                            <input type="password" data-dojo-type="dijit.form.ValidationTextBox" 
                            data-dojo-props="invalidMessage: 'invalid password',
                            required: 'required', trim: 'true', placeHolder: 'Enter password', 
                            promptMessage:'password'" name="password" id="zenossPassword">  
                        </div>
                    </div>
                </div>
                <div class="span4 pull-right">
                    <button id="" type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="zenossConfig.update()"><g:message code="common.update"/></button>
                    <button id="" type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="zenossConfig.cancel()"><g:message code="common.cancel"/></button>
                </div>
            </form>
        </div>
        <div class="span4">
            <section class="well well-small">
                <g:message code="admin.zenossConfigInfo"/>
                <ul>
                    <li><g:message code="admin.zenossEndpointURLInfo"/></li>
                    <li><g:message code="admin.zenossAdminUUIDInfo"/></li>
                    <li><g:message code="admin.zenossAdminPasswordInfo"/></li> 
                </ul>
            </section>
        </div>
    </div>
    <g:render template="configPropertieReload" />
<!--</div>-->