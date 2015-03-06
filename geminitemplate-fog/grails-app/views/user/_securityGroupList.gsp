<div class="row">
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/accessAndSecurity" ><g:message code="menu.user.accessAndSecurity"/></a></li>
            <li>/</li>
            <li><g:message code="menu.user.securityGroups"/></li>    
        </ul>
    </div>
</div>
<div class="row">
    <form id="securityGroupListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row">
                <div class="value_dollar pull-right" style="display: none"><g:message code="default.valueIn"/>  <span  id="currencyValue"></span></div>
            </div>
            <div class="row filter-block">
                <div class="pull-right">
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SecurityGroup.list()">
                    <g:message code='common.refresh' />
                    </button>
                    <a class="btn-flat success new-product" href="#/user/securityGroup/add">+ <g:message code="common.createSecurityGroup"/></a>
                </div>
            </div>
            <div class="row">
                <div id="userSecurityGroupList">  
                </div>
                <div class="alert alert-info hide text_gray" id="nosecurityGroupMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.user.noSecurityGroupMsg"/>&nbsp;&nbsp;<a href="#/user/securityGroup/add"><g:message code="common.createOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>
<div data-dojo-type="dijit.Dialog" id="deleteSecurityGroupDialog" title="<g:message code="common.delete"/>" style="color: black; width: 350px;">
    <input type="hidden" id="SecurityGroupId"/>
    <div class="row form-wrapper">
        Are you sure you want to delete this item?
        <div class="col-md-5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="SecurityGroup.confirmDelete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="SecurityGroup.closeDeleteDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>
<div data-dojo-type="dijit.Dialog" style="color: black;" id="securityGroupListLoader" class="customDialgue col-md-6">
    <div class="row">
        <div class="col-md-3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="col-md-9">
            <div class="col-md-12"><p><g:message code='common.deleteSecurityGroupInfo1' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteSecurityGroupInfo2' /></p></div>              
        </div>          
    </div>
    <div class="row">        
        <a class="btn-flat default" onclick="SecurityGroup.gotoList()"><g:message code='common.gotoSecurityGroupList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>

