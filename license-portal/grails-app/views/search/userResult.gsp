<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name="layout" content="admin">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
    </head>
    <body>
        <div class="stack stack-section-title">
            <div class="container">
                <h1 id="page-title"><g:message code="applications.page.resultforUser" /></h1>
            </div>
        </div>
        <div class="stack stack-container" id="results">
            <div class="container">
                <div class="row-fluid">
                    <table class="table table-striped table table-hover table-bordered">
                        <thead>
                            <th>User Name</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Company Name</th>
                            <th>IP Address</th>
                            <th>Last Login</th>
                            <th>Registration Date</th>
                        </thead>
                        <tbody> 
                            <g:each in="${results}" var ="result">
                            <tr>
                                <td><g:link controller="user" action="show" id="${result.id}">${result.username}</g:link>  </td>
                                <td>${result.firstName}</td>
                                <td>${result.lastName}</td>
                                <td>${result.companyName}</td>
                                <td>${result.ipAddress}</td>
                                <td><g:formatDate date="${result.lastLogin}" type="datetime" style="MEDIUM"/></td>
                                <td><g:formatDate date="${result.registrationDate}" type="datetime" style="MEDIUM"/></td>
                            </tr> 
                            </g:each>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
