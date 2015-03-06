<div class="row-fluid">
    
<!--    
<sec:ifAnyGranted roles="ROLE_FREE_USER">  
        <div><g:message code="common.free"/></div>
    </sec:ifAnyGranted>
-->
    
    <sec:ifAnyGranted roles="ROLE_PAID_USER">
        <div class="alert alert-info hide" id="noProjectList" style="display: none">
            <g:message code="menu.admin.noProjectsAssisgned"/>
        </div>
        
        <div id="projectCloudListGrid">
        </div>
    </sec:ifAnyGranted>
</div>

