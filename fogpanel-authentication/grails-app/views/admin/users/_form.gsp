<%@ page coentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/account"><g:message code="common.account"/></a></li>
            <li>/</li>
            <li><a id="usersListLink"><g:message code="common.users"/></a></li>
            <li>/</li>
            <li><g:message code="common.create"/></li>   
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div class="span12" >
        <div class="row-fluid header">
            <h3 id="volumePageHead"></h3>   
        </div>
        <input type="hidden" id="accountId">
        <form id="userForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="userContent">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent"> 
                            <label for="userName" class="control-label">
                                <g:message code="common.userName"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="promptMessage:'<g:message code="common.promptMessage.username"/>', 
                                missingMessage: '<g:message code="common.promptMessage.username"/>',required: true,regExp:'[a-zA-Z0-9-@. ]{1,200}',
                                placeHolder: '<g:message code="common.userName"/>'" 
                                name="userName" id="userName" onblur="app.signup.validateUserName(this)"> 
                            </div>
                        </div> 
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent"> 
                            <label for="newEmail" class="control-label">
                                <g:message code="common.email"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                                <input data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="required:true, promptMessage:'<g:message code="common.promptMessage.email"/>', 
                                missingMessage:'<g:message code="common.promptMessage.email"/>', 
                                placeHolder: '<g:message code="common.email"/>', regExp: dojox.validate.regexp.emailAddress" 
                                id="newEmail" onblur="app.signup.clearEmail(this)" >
                            </div>
                        </div>    
                    </div>    
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent"> 
                            <label for="newPassword" class="control-label">
                                <g:message code="common.password"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="password" value=""  name="newPassword" data-dojo-type="dijit.form.ValidationTextBox" 
                                onkeyup="checkPasswordStrength(this.id)" onchange="checkPasswordStrength(this.id)" id="newPassword" 
                                data-dojo-props="promptMessage:'<g:message code="common.promptMessage.password"/>', 
                                missingMessage: '<g:message code="common.promptMessage.password"/>',required: true,
                                placeHolder: '<g:message code="common.password"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,15}'">
                            </div>
                        </div>
                        <div class="span4 field-box  control-group "> 
                            <div id="password_strength" style="display: none; margin-top: 5px;">
                                <div style="width: 100px; border: #CCCCCC 1px solid;">
                                    <div id="progress_bar" style="height: 5px; border: #FFFFFF 0px solid; font-size: 1px; background-color: #FFD700;"></div>
                                </div>
                                <span id="strength_text" style="font-family: Arial; font-size: 10px; color: #333333;">Weak</span>
                                <input type="hidden" name="strength_id" id="strength_id" value="1" />
                            </div>
                        </div>
                    </div>
                </div>   
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent"> 
                            <label for="confirmPassword" class="control-label">
                                <g:message code="common.confirmPassword"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="password" value="" name="confirmPassword" data-dojo-type="dijit.form.ValidationTextBox"
                                onKeyUp="confirmPassword(this)" onmouseout="confirmPassword(this)"  id="confirmPassword" 
                                data-dojo-props="promptMessage:'<g:message code="common.promptMessage.confirmPassword"/>', 
                                missingMessage: '<g:message code="common.promptMessage.confirmPassword"/>',required: true,
                                placeHolder: '<g:message code="common.confirmPassword"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^]{8,54}'">

                            </div>
                        </div>
                        <div class="span4 field-box  control-group ">
                            <div class="span3 validationErrorMsg" id="passwordValidateMsg">
                                <span><g:message code="common.passwordMismatch"/></span>
                            </div>
                        </div>    
                    </div>  
                </div>

                <div class="row-fluid">
                    <div class="span6">
                        <div class="span6 pull-right">
                            <div id="userAddButtonDiv" class="span2" style="display: block;" >
                                <button   id="userAddButton"  data-dojo-type="dijit.form.Button" onclick="AddUser.add()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>        
                            <div id="userUpdateButtonDiv" style="display: none;" class="span3">
                                <button   id="userUpdateButton"  data-dojo-type="dijit.form.Button" onclick="AddUser.update()" class="okbtn">
                                    <g:message code="common.update"/>
                                </button>
                            </div>
                            <div id="userCancelButtonDiv" class="span3">
                                <button id="userCancelButton"  data-dojo-type="dijit.form.Button" onclick="AddUser.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
        </form>
    </div>
</div>
<img class="hide_text" id="userLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>