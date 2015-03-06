<!DOCTYPE html>
<html>
    <head>
            <meta name="layout" content="main">
            <title><g:message code="applications.page.show.title" /></title>
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
                    <div></div>
                    
                    <div class="row" style="padding-top: 10px;">
                        <div class="col-xs-12 col-sm-6 col-md-7">
                            <div class="row"> 
                                <div class="col-xs-12 col-sm-6 col-md-10">
                                    <p style="font-size: 38px;">License for <g:formatNumber number="${productInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></p>
                                </div>    
                            </div>
                        </div>
                        
                        <div class="col-xs-offset-5 col-xs-7 col-sm-offset-5 col-sm-1 col-md-offset-3 col-md-2">
                            <div class="row pull-right" style="padding-right:  17px;">
                                <g:link class="btn btn-primary btn-sm" controller="applications" action="createTicket" id="${productInstance.id}">Open Support Ticket</g:link>
                            </div>
                        </div>
                    </div>
                    
                    <div>&nbsp;&nbsp;</div>
                    
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="alert alert-warning">
                                <p>We only offer Evaluation License in this portal, to extend your license period or to purchase license, please contact <a href="mailto:sales@fogpanel.com">sales@fogpanel.com</a></p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="container">
                        <div class="row" style="padding-top: 50px;"> 
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-6 col-md-5">
                                                        <label>Instance ID:</label>
                                                    </div> 
                                                    <div class="col-xs-8 col-sm-6 col-md-7">
                                                        <g:formatNumber number="${productInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" />
                                                    </div>
                                                </div> 
                                            </div> 
                                        </div>
                                    </div>
                                    
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-6 col-md-5">
                                                        <label>Custom Name:</label>
                                                    </div> 
                                                    <div class="col-xs-8 col-sm-6 col-md-7">
                                                        <g:form id="${productInstance.id}">
                                                            <span id="customNameLabel">${productInstance.name} <a href="#" onclick="product.enableEdit(); return false;">Edit</a></span>
                                                                <div class="row">
                                                                    <div class="col-xs-12 col-sm-12 col-md-12"> 
                                                                        <div class="row">
                                                                        <div class="col-xs-7 col-sm-7 col-md-7"> 
                                                                             <input type="text" class="form-control" id="customName" name="customName"  placeholder="Custom Name" value="${productInstance.name}" style="display: none;">
                                                                        </div> 
                                                                        <div class="col-xs-5 col-sm-5 col-md-5" id="saveButton" style="display: none;">
                                                                            <g:actionSubmit class="btn btn-primary btn-sm" value="Save" action="changeName" />
                                                                        </div>         
                                                                    </div> 
                                                                </div>
                                                            </div>
                                                        </g:form>
                                                    </div>   
                                                </div>     
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        
                        <div>&nbsp;&nbsp;</div>
                    
                        <div class="row"> 
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-6 col-md-5">
                                                        <label>Product Name:</label>
                                                    </div> 
                                                    <div class="col-xs-6 col-sm-6 col-md-7">
                                                        ${productInstance.product.name}
                                                    </div>    
                                                </div>     
                                            </div>   
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-6 col-md-5">
                                                        <label>Status:</label>
                                                    </div> 
                                                    <div class="col-xs-6 col-sm-6 col-md-7">
                                                        <g:if test="${productInstance?.status?.name() == 'ACTIVE'}">
                                                            <span class="label label-success">Active</span>  
                                                        </g:if>
                                                        <g:if test="${productInstance?.status?.name() == 'FREE_TRIAL'}">
                                                            <span class="label label-warning">Evaluation</span>
                                                        </g:if>
                                                        <g:if test="${productInstance?.status?.name() == 'LOCKED'}">
                                                            <span class="label label-danger">Locked</span> 
                                                        </g:if>
                                                        <g:if test="${productInstance?.status?.name() == 'DISABLED'}">
                                                            <span class="label label-danger">Disabled</span> 
                                                        </g:if>
                                                        <g:if test="${productInstance?.status?.name() == 'EMERGENCY_TRIAL'}">
                                                            <span class="label label-danger">Emergency Trial</span>
                                                        </g:if>
                                                        <g:if test="${productInstance?.status?.name() == 'EXPIRED'}">
                                                            <span class="label label-danger">EXPIRED</span>
                                                        </g:if>
                                                    </div>    
                                                </div>     
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    
                        <div>&nbsp;&nbsp;</div>
                        
                        <div class="row"> 
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-6 col-md-5">
                                                        <label>Base Licenses:</label>
                                                    </div> 
                                                    <div class="col-xs-6 col-sm-6 col-md-7">
                                                        ${productInstance.baseLicense} Sockets 
                                                    </div>
                                                </div> 
                                            </div> 
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-8 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-6 col-sm-6 col-md-5">
                                                        <label>Additional Licenses:</label>
                                                    </div> 
                                                    <div class="col-xs-6 col-sm-6 col-md-7">
                                                        ${productInstance.additionalLicense} Sockets 
                                                    </div>    
                                                </div>     
                                            </div>    
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    
                        <div>&nbsp;&nbsp;</div>
                    
                        <div class="row"> 
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-6 col-md-5">
                                                        <label>Inception Date:</label>
                                                    </div> 
                                                    <div class="col-xs-8 col-sm-6 col-md-7">
                                                        <g:formatDate date="${productInstance.inceptionDate}" type="datetime" style="MEDIUM" />
                                                    </div>
                                                </div> 
                                            </div> 
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-6 col-md-5">
                                                        <label>Expiration Date:</label>
                                                    </div> 
                                                    <div class="col-xs-8 col-sm-6 col-md-7">
                                                        <g:formatDate date="${productInstance.expirationDate}" type="datetime" style="MEDIUM" />
                                                    </div>    
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
                            <h3>License Verification Log</h3>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <div class="row" style="overflow: auto">
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <table class="table table-striped table-bordered">
                                                <thead>
                                                    <th>Last Updated Date</th>
                                                    <th>Host Name</th>
                                                    <th>Host IP</th>
                                                    <th>Sockets</th>
                                                    <th>Valid</th>
                                                    <th>Message</th>
                                                </thead>
                                                <tbody>
                                                    <g:each in="${validationLogList}" status="i" var="validationLog">
                                                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                                                            <td><g:formatDate date="${validationLog.lastUpdatedDate}" format="yyyy-MM-dd HH:mm:ss" /></td>

                                                            <td>${fieldValue(bean: validationLog, field: "hostName")}</td>

                                                            <td>${fieldValue(bean: validationLog, field: "hostIP")}</td>

                                                            <td>${fieldValue(bean: validationLog, field: "sockets")}</td>

                                                            <td>
                                                                <g:if test="${validationLog?.valid == true }">
                                                                <span class="fa fa-check-square-o" style="color: green"></span>
                                                                </g:if>
                                                                <g:if test="${validationLog?.valid == false }">
                                                                    <span class="fa fa-times" style="color: red"></span>
                                                                </g:if>
                                                            </td>
                                                            <td>${fieldValue(bean: validationLog, field: "errorMessage")}</td>
                                                        </tr>
                                                    </g:each>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>  
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-12 col-md-12">
                                            <g:if test="${!validationLogList}">
                                                <div class="alert alert-info">
                                                    <p> The instance has not been registered and activated yet. Do you have trouble in activation? Please check our <a target="_blank" href="http://confluence.fogpanel.com/"><u>knowledge base</u>.</a></p>
                                                </div>
                                            </g:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
        </div>     
    </body>
</html>

