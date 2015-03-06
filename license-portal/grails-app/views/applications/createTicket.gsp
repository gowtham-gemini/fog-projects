
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta name='layout' content='main'/>
        <title>Create Ticket</title>
        <script type="text/javascript" src="${resource(dir: 'js/')}/application.js"></script> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
       <div class="stack stack-section-title">
            <div class="container">
                <div class="row">
                    <div class='thumbnail' style="border: 0px;">
                        <div class="caption">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                               <div class="row"> 
                                   <div class="col-xs-12 col-sm-12 col-md-12">
                                       <h1>Create Ticket</h1>
                                   </div>    
                               </div>
                           </div>
                        </div>
                    </div>    
                </div>
            </div> 
        </div>
        
        <div class="container">
            <div class='thumbnail' style="border: 0px;">
                <div class="caption">
                    <div class="row">
                        <g:form class="form-horizontal" url="[action:'saveTicket',controller:'applications']">
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-10 col-md-10">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-10 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-offset-3 col-sm-2 col-md-offset-3 col-md-2">
                                                        <div class="form-group">
                                                            <label for="department" class="control-label"><g:message code="support.department.label"/>*</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-8 col-sm-7 col-md-7 ">
                                                        <g:select class="form-control" id="department" name="department" from="${departmentTitle}" />
                                                    </div>
                                                </div>    
                                            </div>    
                                        </div>
                                    </div>    
                                </div>
                                
                                <div class="row">
                                    <div class="col-xs-12 col-sm-10 col-md-10">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-10 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-offset-3 col-sm-2 col-md-offset-3 col-md-2">

                                                        <div class="form-group">
                                                            <label for="department" class="control-label"><g:message code="support.priority.label"/>*</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-8 col-sm-7 col-md-7">
                                                        <g:select class="form-control" id="priority" name="priority" from="${priorityTitle}" />
                                                    </div>
                                                </div>    
                                            </div>    
                                        </div>
                                    </div>    
                                </div>
                                
                                <div class="row">
                                    <div class="col-xs-12 col-sm-10 col-md-10">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-10 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-offset-3 col-sm-2 col-md-offset-3 col-md-2">
                                                        <div class="form-group">
                                                            <label for="department" class="control-label"><g:message code="support.instanceId.label"/>*</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-8 col-sm-7 col-md-7">
                                                        <select class="form-control" id="instanceId" name="instanceId">
                                                            <option selected="" value="General">General</option>
                                                            <g:each var="productInstance" in="${productInstanceList}">
                                                                <g:if test="${licenseId == productInstance.id.toString()}">
                                                                    <option selected="" value="${productInstance.id}"><g:formatNumber number="${productInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></option>
                                                                </g:if>
                                                                <g:else>
                                                                    <option value="${productInstance.id}"><g:formatNumber number="${productInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></option>
                                                                </g:else>
                                                            </g:each>
                                                        </select>
                                                    </div>
                                                </div>    
                                            </div>    
                                        </div>
                                    </div>    
                                </div>
                                
                                
                                
                                <div class="row">
                                    <div class="col-xs-12 col-sm-10 col-md-10">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-10 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-offset-3 col-sm-2 col-md-offset-3 col-md-2">
                                                        <div class="form-group">
                                                            <label for="department" class="control-label"><g:message code="support.subject.label"/>*</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-8 col-sm-7 col-md-7 ">
                                                        <input type="text" class="form-control" id="subject"  placeholder="Subject" name="subject">
                                                        <g:hasErrors bean="${ticketInstance}">    
                                                            <span class="help-block text-danger">
                                                                <p class="text-danger">
                                                                    <g:eachError bean="${ticketInstance}" var = "error" field = "subject" > 
                                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                                    </g:eachError>
                                                                </p>
                                                            </span>
                                                        </g:hasErrors>
                                                    </div>
                                                </div>    
                                            </div>    
                                        </div>
                                    </div>    
                                </div>
                                
                                
                                
                                <div class="row">
                                    <div class="col-xs-12 col-sm-10 col-md-10">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-10 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-4 col-sm-offset-3 col-sm-2 col-md-offset-3 col-md-2">

                                                        <div class="form-group">
                                                            <label for="department" class="control-label"><g:message code="support.description.label"/>*</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-8 col-sm-7 col-md-7">
                                                        <textarea class="form-control" id="description" rows="4" name="description"></textarea>
                                                        <g:hasErrors bean="${ticketInstance}">    
                                                            <span class="help-block text-danger">
                                                                <p class="text-danger">
                                                                    <g:eachError bean="${ticketInstance}" var = "error" field = "description" > 
                                                                        <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                                    </g:eachError>
                                                                </p>
                                                            </span>
                                                        </g:hasErrors>
                                                    </div>
                                                </div>    
                                            </div>    
                                        </div>
                                    </div>    
                                </div>
                                
                                <div>&nbsp;</div>
                                
                                <div class="row">
                                    <div class="col-xs-12 col-sm-10 col-md-10">
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-10 col-md-10">
                                                <div class="row">
                                                    <div class="col-xs-8 col-sm-6 col-md-6 ">
                                                    </div>
                                                    <div class="col-xs-12 col-sm-6 col-md-6 ">
                                                        <button type="submit" class="btn btn-primary pull-right">Submit</button>
                                                    </div>
                                                </div>    
                                            </div>    
                                        </div>
                                    </div>    
                                </div>
                            </div>
                        </g:form>    
                    </div>  
                </div>
            </div>
        </div>     
    </body>
</html>
        
<!-- 

<div class="stack stack-container container">
            <div class="signup ">
                <div class="row-fluid "> 
                    <div class="col-xs-12 col-sm-6 col-md-8">
                        <g:form class="form-horizontal" url="[action:'saveTicket',controller:'applications']">
                            <g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
                            <div class="form-group">
                                <label for="department" class="control-label col-md-4"><g:message code="support.department.label"/>*</label>
                                <div class="col-md-6">
                                    <g:select class="form-control" id="department" name="department" from="${departmentTitle}" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="priority" class="control-label col-md-4"><g:message code="support.priority.label"/>*</label>
                                <div class="col-md-6">
                                    <g:select class="form-control" id="priority" name="priority" from="${priorityTitle}" />
                                </div>
                            </div>   

                            <div class="form-group">
                                <label for="InstanceId" class="control-label col-md-4"><g:message code="support.instanceId.label"/>*</label>
                                <div class="col-md-6">
                                    <select class="form-control" id="instanceId" name="instanceId">
                                        <option selected="" value="General">General</option>
                                        <g:each var="productInstance" in="${productInstanceList}">
                                            <g:if test="${licenseId == productInstance.id.toString()}">
                                                <option selected="" value="${productInstance.id}"><g:formatNumber number="${productInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></option>
                                            </g:if>
                                            <g:else>
                                                <option value="${productInstance.id}"><g:formatNumber number="${productInstance.id}" type="number" minIntegerDigits="12" groupingUsed="false" /></option>
                                            </g:else>
                                        </g:each>
                                    </select>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="subject" class="control-label col-md-4"><g:message code="support.subject.label"/>*</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" id="subject"  placeholder="Subject" name="subject">
                                    <g:hasErrors bean="${ticketInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${ticketInstance}" var = "error" field = "subject" > 
                                                    <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label for="description" class="control-label col-md-4"><g:message code="support.description.label"/>*</label>
                                <div class="col-md-6">
                                    <textarea class="form-control" id="description" rows="3" name="description"></textarea>
                                    <g:hasErrors bean="${ticketInstance}">    
                                        <span class="help-block text-danger">
                                            <p class="text-danger">
                                                <g:eachError bean="${ticketInstance}" var = "error" field = "description" > 
                                                    <g:if test="${error in org.springframework.validation.FieldError}"><span data-field-id="${error.field}"><g:message error="${error}"/></span></g:if>
                                                </g:eachError>
                                            </p>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-md-10">
                                    <button type="submit" class="btn btn-primary pull-right">Submit</button>
                                </div>
                            </div>
                        </g:form>    
                    </div>
                </div>
            </div>    
        </div> 

-->