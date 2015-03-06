<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta name="layout" content="main">
            <g:set var="entityName" value="${message(code: 'applications.page.title', default: 'My Applications')}" />
        <title>My Support Tickets</title>
    </head>
    <body>
        
        <div class="stack stack-section-title">
            <div class="container">
                <div class="row">
                    <div class='thumbnail' style="border: 0px;">
                        <div class="caption">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row"> 
                                    <div class="col-xs-6 col-sm-8 col-md-8"> 
                                        <div class="row">
                                            <h1 id="page-title">My Support Tickets</h1> 
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-4 col-md-4"> 
                                        <div class="row pull-right"> 
                                            <g:link controller="applications" class="btn btn-primary btn-sm" action="createTicket" >New Ticket</g:link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> 
                </div>
            </div> 
        </div>
        <div class="container">
            <div class="row" style="overflow: auto">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <table class="table table-hover table table-bordered appDataTable">
                        <thead>
                            <th class="active"><center>Ticket ID</center></th>
                            <th class="active"><center>Priority</center></th>
                            <th class="active"><center>Department</center></th>
                            <th class="active"><center>Subject</center></th>
                            <th class="active"><center>Status</center></th>
                            <th class="active"><center>Created On</center></th>
                            <th class="active"><center>Updated On</center></th>
                        </thead>  
                        <tbody>
                            <g:each var="ticket" in="${tickets}">
                            <tr> 
                                <td><center><g:link action="showTicket" controller="applications" id="${ticket.id}">${ticket.id}</g:link></center></td>
                                <td><center>${ticket.priority.title}</center></td>
                                <td><center>${ticket.department.title}</center></td>
                                <td>${ticket.subject}</td>
                                <td><center>${ticket.status.title}</center></td>
                                <td><center><g:formatDate date="${new Date(((long)ticket.creationTime) * 1000)}" type="datetime" style="MEDIUM"/></center></td>
                                <td><center><g:formatDate date="${new Date(((long)ticket.lastActivity) * 1000)}" type="datetime" style="MEDIUM"/></center></td>
                            </tr>
                            </g:each>
                        </tbody>
                    </table>     
                </div>
            </div>
        </div>
    </body>
</html>