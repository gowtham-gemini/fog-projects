<%@ page contentType="text/html;charset=UTF-8" %>
 <!DOCTYPE html>
<html>
    <head>
        <meta name='layout' content='main'/>
        <title>Show Ticket</title>
            <script src="${resource(dir: 'css/bootstrap/js', file: '/bootstrap.js')}" type="text/javascript"></script>
            <script src="${resource(dir: 'js', file: '/Test.js')}" type="text/javascript"></script>
            <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script>   
    </head>
    <body>
        <div class="container">
            <div class='thumbnail' style="border: 0px;">
                <div class="caption">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <g:if test="${flash.message}">
                                <div class="alert alert-success">${flash.message}</div>
                            </g:if>
                        </div>
                    </div>
                   
                    <div class="stack stack-container"> 
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="row"> 
                                <g:link class="btn btn-primary btn-sm pull-right" controller="applications" action="support">Support</g:link>
                                <span class="pull-right">&nbsp;&nbsp;</span>
                                <g:link class="btn btn-primary btn-sm pull-right" controller="applications" action="createTicket">New Ticket</g:link>
                            </div>
                        </div> 
                    </div>
                    
                    <div class="row" style="padding-top: 10px;">
                        <div class="col-xs-6 col-sm-6 col-md-7">
                            <div class="row"> 
                                <div class="col-xs-6 col-sm-6 col-md-7">
                                    <p style="font-size: 38px;">Ticket #${ticket.id}</p>
                                </div>    
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-offset-5 col-sm-1 col-md-offset-2 col-md-3">
                            <div class="row"> 
                                <div class="row pull-right" style="padding-right: 30px;">
                                    <p style="font-size: 32px;">[${ticket.status.title}]</p>
                                </div>
                            </div>    
                        </div>
                    </div> 
                    
                    <div class="container">
                        <div class="row" style="padding-top: 40px;"> 
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row" style="padding-bottom: 15px;">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-12 col-md-7">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-6 col-md-5">
                                                        <label>Subject:</label>
                                                    </div> 
                                                    <div class="col-xs-6 col-sm-6 col-md-7">
                                                        <p class="sub">${ticket.subject}</p><p></p>
                                                    </div>
                                                </div> 
                                            </div> 
                                        </div>
                                    </div>
                                    
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-12 col-md-7">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-6 col-md-5">
                                                        <label>Department:</label>
                                                    </div> 
                                                    <div class="col-xs-6 col-sm-6 col-md-7">
                                                        <p class="dept">${ticket.department.title}</p>
                                                        <p></p>
                                                    </div>    
                                                </div>     
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="row"> 
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-10 col-sm-12 col-md-7">
                                                <div class="row">
                                                    <div class="col-xs-5 col-sm-6 col-md-5">
                                                        <label>Priority:</label>
                                                        <g:form class="form-horizontal" url="[action:'editPriority',controller:'applications']">    
                                                            <input type="hidden" name="ticketId" id="ticketId" value="${ticketId}">
                                                        </div> 
                                                        <div class="col-xs-7 col-sm-6 col-md-7">
                                                            <div id="value">${ticket.priority.title} &nbsp; <span id="Edit"><a href="#" onclick="Application.editPriority(); return false;">Edit</a></span></div>
                                                            <p></p>
                                                            <div class ="row" id="saveButton" style="display: none;">  
                                                                <div class="row">
                                                                    <div class="col-xs-7 col-sm-8 col-md-8">
                                                                        <div class="row">
                                                                            <div class="col-xs-12 col-sm-8 col-md-10">
                                                                                <select class="form-control" id="listBox" name="listBox">
                                                                                    <g:each var="priority" in="${priorities}">
                                                                                        <g:if test="${priority == ticket.priority.title}">
                                                                                            <option value="${priority}" selected="true">${priority}</option>
                                                                                        </g:if>
                                                                                        <g:else>
                                                                                            <option value="${priority}">${priority}</option>
                                                                                        </g:else>
                                                                                    </g:each>
                                                                                </select>
                                                                            </div>    
                                                                        </div>
                                                                    </div>
                                                                <div class="col-xs-4 col-sm-4 col-md-1"><g:actionSubmit class="btn btn-primary btn-sm" value="Save" action ="editPriority"/></div>
                                                            </div>
                                                        </g:form>
                                                        </div>
                                                    </div>
                                                </div> 
                                            </div> 
                                        </div>
                                    </div>
                                    
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-7">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-6 col-md-5">
                                                        <label>Created On:</label>
                                                    </div> 
                                                    <div class="col-xs-8 col-sm-6 col-md-7">
                                                        <p class="date"><g:formatDate date="${new Date(((long)ticket.creationTime) * 1000)}" type="datetime" style="MEDIUM"/></p>
                                                    </div>    
                                                </div>     
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>    
                    <h2>Posts</h2>
                    <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <g:form class="form-horizontal" url="[action:'addPosts',controller:'applications']">
                            <g:each var="post" in="${ticket.posts}">
                                <blockquote>
                                    <p>${post.contents?.replaceAll("\n", "<br>")}</p>
                                    <footer>${post.fullName} @ <g:formatDate date="${new Date(((long)post.dateLine) * 1000)}" type="datetime" style="MEDIUM"/></footer>
                                </blockquote>  
                            </g:each>
                    </div>
                </div>
                <input type="hidden" name="ticketId" id="ticketId" value="${ticketId}">
                <div class="row" style="display: none;" id="textBlock">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <textarea class="form-control" name="posts" id="posts" cols="30" rows="3"></textarea>
                    </div>
                </div>
                
                <div>&nbsp;&nbsp;</div>
                
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <button type="button" class="btn btn-primary btn-sm" id="reply" onclick = "Application.generateRow()" >Reply</button>
                    </div>  
                </div>    
                    <div class="row">
                        <div class="col-xs-3 col-sm-1 col-md-1">
                            <button type="submit" class="btn btn-primary btn-sm" id="send" style="display: none;" >Send</button>
                        </div>
                        <div class="col-xs-3 col-sm-1 col-md-1">
                            <button type="button" class="btn btn-primary btn-sm" id="cancel" onclick = "Application.cancel()" style="display: none;" >Cancel</button>
                        </div>
                    </div>
                </g:form>
            </div>  
        </div>     
    </div>
     
<!-- Support Table:-

    <div class="row">
        <div class="col-xs-12">
            <div class="row">
                <div class="col-xs-6" style="padding-left: 80px;">
                    <div class="dataTables_length" id="DataTables_Table_0_length">
                        <label>
                            <select aria-controls="DataTables_Table_0" name="DataTables_Table_0_length" class="form-control input-sm">
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                        </label>    
                    </div>
                </div>
                <div class="col-xs-6">
                    records per page
                </div>    
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="dataTables_filter" id="DataTables_Table_0_filter">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="col-xs-4">
                                        Search : 
                                    </div>
                                    <div class="col-xs-4" style="padding-right:60px;">
                                        <input aria-controls="DataTables_Table_0" class="form-control input-sm" type="search">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>    
    </div>
-->
    </body>
</html>

      