<div class="row">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/notification" ><g:message code="common.notification"/></a></li>
            <li>/<li>
            <li id="viewTopicName"></li>
        </ul>
    </div>
</div>
<input type="hidden" id="selectedTopicId">
<!--<input type="hidden" id="selectedTopicName">-->
<div class="row">
    <div data-dojo-type="dijit/layout/TabContainer"  class="span12" style="overflow: visible;" tabStrip="true">
        <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.view"/>" selected="true"  onshow="">
            <div class="table-wrapper products-table">
                <!--<div class="page-header">-->
                    <div class="control-group">
                        <h3><g:message code="common.topicOverview"/></h3>
                    </div></br>
                    <div class="row">
                        <div class="span6">
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.name"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="topicName"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.id"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="topicId"></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt1-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.status"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="topicStatus"  ></span></div>
                                </div>
                            </div>
                            <div class="grd-row-alt2-tbl">
                                <div class="grd-tbl-row">
                                    <div class="grd-tbl-cell clm-first"><g:message code="common.createdDate"/></div>
                                    <div class="grd-tbl-cell clm-second"><span id="topicCreatedDate"  ></span></div>
                                </div>
                            </div>
                        </div>
                        <div class="span6">

                        </div>
                    </div>
                <!--</div>-->
            </div>
        </div>
        <div data-dojo-type="dijit/layout/ContentPane"  title="<g:message code="common.subscribers"/>" onshow="viewTopic.populateSubscribersValues()">
            <div class="row">
                <div class="span12"> 
                    <form class="form-horizontal" id="addSubscribersForm" data-dojo-type="dijit.form.Form">
                         <div class="span4 control-group field-box" id='subscriberPage'>
                              <label for="subscriberEmail" class="control-label">
                                 <g:message code="common.email"/>
                              </label>
                              <div class="controls">
                            <input data-dojo-type="dijit.form.ValidationTextBox"  
                                data-dojo-props="required:true, promptMessage:'<g:message code="common.promptMessage.email"/>', 
                                missingMessage:'<g:message code="common.promptMessage.email"/>', 
                                placeHolder: '<g:message code="common.email"/>', regExp: dojox.validate.regexp.emailAddress" 
                                id="subscriberEmail">
                                       
                              </div>
                         </div>
                         <div class="span2 filter-block">
                            <button id="addSubscribersButton" type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="viewTopic.addSubscriber()">
                              <g:message code="common.add"/>
                            </button>
                         </div>
                    </form> 
                  </div>
            </div>
            <div class="row" style="margin-top: 50px;">
              <div class="table-wrapper products-table">
                    <div class="row">
                      <div id="subscribersGrid"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

