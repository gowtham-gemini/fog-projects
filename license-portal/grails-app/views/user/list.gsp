<html>
    <head>
        <meta name='layout' content='admin'/>
        <title><g:message code="user.list.title"/></title>
    </head>
    <body>
        
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="user.list.title"/></h1>
            </div>
        </div>

        <div class="stack stack-container">
            <div class="container">
                <div class="row-fluid">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <g:sortableColumn property="username" title="${message(code: 'user.name.label', default: 'Username')}" />
                                    <g:sortableColumn property="company name" title="${message(code: 'user.companyName.label', default: 'Company Name')}" />
                                    <g:sortableColumn property="enabled" title="Enabled" />
                                    <g:sortableColumn property="actions" title="${message(code: 'user.action.label', default: 'Actions')}" />
                                </tr>
                            </thead>
                            <tbody>
                                <g:each in="${userInstanceList}" status="i" var="userInstance">
                                    <tr>
                                        <td><g:link controller="user" action="show" id="${userInstance.id}">${fieldValue(bean: userInstance, field: "username")}</g:link></td>
                                        <td>${fieldValue(bean: userInstance, field: "companyName")}</td>
                                        <td>
                                            <g:if test="${userInstance.enabled}">
                                                <span class="label label-success">Active</span>  
                                              </g:if>
                                              <g:if test="${!userInstance.enabled}">
                                                <span class="label label-danger">Disabled</span>  
                                              </g:if>
                                        </td>
                                        <td>
                                            <g:link controller="user" action="edit" id="${userInstance.id}">Edit</g:link>
                                            <a href="${request.contextPath}/j_spring_security_switch_user?j_username=${fieldValue(bean: userInstance, field: "username")}" title="Login as Client">Login</a>
                                        </td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                        <div class="pagination">
				<g:paginate  controller="user" action="list" total="${userCount ?: 0}" />
			</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
