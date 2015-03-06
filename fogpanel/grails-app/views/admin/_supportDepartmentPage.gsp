<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/general"><g:message code="menu.admin.configuration.general"/></a></li>
            <li>/<li>
            <li><a href="#/admin/support"><g:message code="common.support"/></a></li>
            <li>/</li>
            <li><g:message code="common.department"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid form-wrapper" id="pad-wrapper">
    <input type="hidden" id="depId">
    <form class="form-horizontal" id="supportDepartmentForm" data-dojo-type="dijit.form.Form">
        <div class="span6" id="supportDepartmentPage">   
            <div class="span12 control-group field-box">
                <label for="departmentName" class="control-label settings_lable">
                    <g:message code="common.department"/>:
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="departmentName"
                    data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', required: 'true',regExp:'[a-zA-Z0-9- ]{1,200}',
                    placeHolder: '<g:message code="common.enterDepartmentName"/>', invalidMessage:'<g:message code="common.value.invalid"/>'">
                </div>
            </div>
            <div class="span6 pull-right">
                <div id="depOpenDiv">
                    <button id="departButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddDepartment.add();">
                        <g:message code="common.save"/>
                    </button>
                    <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="departLoader" style="display: none"/>
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddDepartment.cancel();">
                        <g:message code="common.cancel"/>
                    </button>
                </div>
                <div id="depEditDiv" style="display: none">
                    <button data-dojo-type="dijit.form.Button" id="departEditButton"  class="defaultbtn" onclick="AddDepartment.edit();">
                        <g:message code="common.update"/>
                    </button>
                    <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23' id="departEditLoader" style="display: none"/>
                    <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddDepartment.delete();">
                        <g:message code="common.delete"/>
                    </button>
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="AddDepartment.cancel();">
                        <g:message code="common.cancel"/>
                    </button>
                </div>

            </div>
        </div>
    </form> 

</div>
<div class="row-fluid form-wrapper" id="pad-wrapper"></div>
<div class="row-fluid form-wrapper">
    <div id="departmentGrid"></div>
</div> 
