<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li>
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/</li>
            <li><g:message code="common.editCloudEngine"/></li>
        </ul>
    </div>
</div> 
<div class="row-fluid">
    <div class="span12" >
        
        <form id="adminCloudEngineAddForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
            <div id="currentCloudEngineId">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent" style="margin-left:28px;">
                            
                            <label for="name" class="control-label">          
                                <g:message code="common.name"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="cloudEngineNameId" data-dojo-props="required: 'true',
                                invalidMessage: '', placeHolder: '<g:message code="common.name"/>',
                                promptMessage:''">
                                
                            </div>

                        </div>
                    </div>
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent">
                            
                            <label for="url" class="control-label">          
                                <g:message code="common.url"/>: <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                id="cloudEngineUrlId" data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.invalidUrl"/>', placeHolder: '<g:message code="common.url"/>',
                                promptMessage:''">
                                
                            </div>

                        </div>
                    </div>
                    <div class="span12">
                        <div class="control-group span6 horizontalcontent">
                            <label for="" class="control-label"><g:message code="common.type"/> : <span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="engineTypeList" class="selectOption"></div>
                            </div>
                        </div>
                    </div>    
                    
                    <div class="row-fluid">
                        <div class="span8">
                            <img class="hide_text" id="admincloudEngineLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div id="adminCloudEngineCancelButtonDiv" class="span2 pull-right">
                                <button id="cloudEngineCancelButton"  data-dojo-type="dijit.form.Button" onclick="CloudEngine.cancel()" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                            <div id="admincloudEngineAddButtonDiv" class="span1 pull-right" style="display: block;" >
                                <button   id="cloudEngineAddButton"  data-dojo-type="dijit.form.Button" onclick="CloudEngine.update()" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>  
                        </div>
                        <div class="span6">
                        </div>
                    </div>
                </div>
            </div>
            
        </form>
    </div>
    

