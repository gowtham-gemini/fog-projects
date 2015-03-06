<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><a href="#/admin/disk/list"><g:message code="common.storage"/></a></li>
            <li>/</li>
            <li id="editComputBreadcrum"><g:message code="common.add"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">
    <div class="span12" id="adminDiskOfferPage">
        <div class="row-fluid header">
            <h3><g:message code="common.technicalInfo"/></h3> 
            <input type="hidden" id="CurrentDiskSizeRef">
            <div class="value_dollar pull-right"><g:message code="default.valueIn"/>  
                <span id="currencyValue"></span>
            </div>
        </div>
        <div class="span12" id="diskOfferingPage"> 
            <form id="adminDiskOfferZoneForm" data-dojo-type="dijit.form.Form" class="form-horizontal">
                <div  class="row-fluid">
                    <div class="span12">
                        <div class="control-group span4 horizontalcontent">
                            <label for="diskOfferZoneList" class="control-label"><g:message code="user.createVM.zone.label"/>:<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="diskOfferZoneList" class="selectOption"></div>
                            </div>
                            <span id="zoneNameLabel" class="hide_lable"></span>      
                        </div>
                        <div class="control-group span4 horizontalcontent"> 
                            <label for="diskName" class="control-label"><g:message code="common.name"/>:<span class="require">*</span></label>            
                            <div class="controls elements">                                        
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                data-dojo-props="invalidMessage: '<g:message code="common.disk.invalid"/>', required: 'true',
                                placeHolder: '<g:message code="common.disk.prompt"/>', regExp: '[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;=]{4,100}'"
                                id="diskName">
                            </div>      
                        </div>                                       
                        <div class="control-group span4 horizontalcontent">
                            <label for="diskDescription" class="control-label">        
                                <g:message code="common.desc"/>:<span class="require">*</span>
                            </label>
                            <div class="controls elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                id="diskDescription"  data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.description.invalid"/>', 
                                placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9#$%!_@|&*?+-/\ ^:;=]{4,200}',
                                promptMessage:'<g:message code="common.description.prompt"/>'"/>                                        
                            </div>
                        </div>
                        <div class="control-group span4 horizontalcontent" style="display: none">
                            <label for="diskOfferPodList"  class="control-label"> <g:message code="common.pod"/>:<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="diskOfferPodList" class="selectOption"></div>
                            </div>
                            <span id="podNameLabel" class="hide_lable"></span>
                        </div> 	  
                        <div class="control-group span4 horizontalcontent" style="display: none">
                            <label for="diskOfferClusterList"  class="control-label "><g:message code="common.cluster"/>:<span class="require">*</span></label>
                            <div class="controls updatable elements">
                                <div id="diskOfferClusterList" class="selectOption"></div>
                            </div>
                            <span id="clusterNameLabel" class="hide_lable"></span>
                        </div>       
                    </div>
                </div>
            </form>
            <div class="row-fluid" id="diskOfferContent">
                <form id="diskOfferContentForm" data-dojo-type="dijit.form.Form" class="form-horizontal"> 
                    <div class="row-fluid">
                        <div class="control-group span3 horizontalcontent">
                            <label for="serviceStorageType" class="control-label"><g:message code="user.createVM.storageType.label"/>:</label>
                            <div class="controls updatable elements">
                                <div class="selectOption">
                                    <select data-dojo-type="dijit.form.FilteringSelect" id="diskStorageType">
                                        <option  value="shared"><g:message code="common.shared"/></option>
                                        <option value="local"><g:message code="common.local"/></option>
                                    </select>
                                </div>
                            </div>
                            <span id="diskStorageTypeLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group span3 horizontalcontent">
                            <label for="diskStorageTags" class="control-label"><g:message code="common.storageTag"/>: </label>
                            <div class="controls updatable elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                    id="diskStorageTags" data-dojo-props="invalidMessage: '<g:message code="common.invalid.storageTag"/>', placeHolder: '<g:message code="common.storageTag"/>',regExp:'[a-zA-Z0-9- ]{1,200}',
                                    promptMessage:'<g:message code="common.prompt.storageTag"/>'">
                            </div>      
                            <span id ="diskStorageTagsLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group customDisk horizontalcontent span3" id="customDiskDiv">
                            <label class="control-label" for="customDisk"><g:message code="common.customDisk"/>:</label>
                            <div class="controls updatable elements">
                                <input type="checkbox" id="customDisk" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false"  onclick="AddDiskOfferInfo.enableContent(this)"> 
                            </div>
                            <span id="customDiskLabel" class="hide_lable"></span>
                        </div> 
                    </div>
                    <div class="row-fluid" id="secondRowWidgets">
                        <div class="control-group span4 horizontalcontent" id="diskSizeHide">
                            <label for="diskSize" class="control-label"><g:message code="common.size"/>(<g:message code="common.gb"/>):</label>
                            <div class="controls updatable elements">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                data-dojo-props="promptMessage:'<g:message code="common.diskSize.prompt"/>',                 
                                invalidMessage: '<g:message code="common.diskSize.invalid"/>',constraints:{min:1,max:1000,pattern:'#'}, 
                                timeoutChangeRate: '0.2',intermediateChanges:true,
                                placeHolder: '<g:message code="common.diskSize.prompt"/>', regExp: '[0-9]{1,5}', value:1"
                                name = "diskSize" id="diskSize" onChange="AddDiskOfferInfo.showZoneCost(this)">
                            </div>
                            <span id="diskSizeLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group span4 horizontalcontent diskCustomSize">
                            <label for="diskSize" class="control-label">         
                                <g:message code="common.minSize"/>(<g:message code="common.gb"/>):
                            </label>
                            <div class="controls updatable elements">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.diskSize.invalid"/>', trim: 'true',
                                constraints:{min:1,max:1000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:1, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.minSize"/>'" id="diskMinSize">
                            </div>
                            <span id="diskMinSizeLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group span4 horizontalcontent diskCustomSize">
                            <label for="diskSize" class="control-label"><g:message code="common.maxSize"/>(<g:message code="common.gb"/>):</label>
                            <div class="controls updatable elements">
                                <input type="text" data-dojo-type="dijit.form.NumberSpinner" 
                                data-dojo-props="required: 'true',
                                invalidMessage: '<g:message code="common.diskSize.invalid"/>', trim: 'true',
                                constraints:{min:1,max:1000,pattern:'#'}, timeoutChangeRate: '0.2',
                                value:1, regExp:'[0-9]{1,4}', promptMessage:'<g:message code="common.maxSize"/>'" id="diskMaxSize">
                            </div>
                            <span id="diskMaxSizeLabel" class="hide_lable"></span>                                                                               
                        </div>
                        <div class="control-group span3 horizontalcontent">
                            <label for="qoSType" class="control-label"><g:message code="common.qosType"/>:</label>
                            <div class="controls updatable elements">
                                <div class="selectOption">
                                    <select data-dojo-type="dijit.form.Select" id="qoSType" onchange="AddDiskOfferInfo.enableQoSType(this)">
                                        <option value=""><g:message code="common.select"/></option>
                                        <option value="hypervisor"><g:message code="common.hypervisor"/></option>
                                        <option value="storage"><g:message code="common.storage"/></option>
                                    </select>
                                </div>
                            </div>
                            <span id="qoSTypeLabel" class="hide_lable"></span>
                        </div>
                    </div>
                    <div class="row-fluid" id="qOSTypeHypervisorDiv" style="display: none">
                      <div class="control-group span3 customDisk">
                        <label for="diskOfferDiskReadRateBPS" class="control-label"><g:message code="common.diskReadRate"/>:</label>
                        <div class="controls updatable elements">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                 id="diskOfferDiskReadRateBPS" data-dojo-props="invalidMessage: '<g:message code="common.diskReadRate.invalid"/>',
                                 placeHolder: '<g:message code="common.diskReadRate"/>',regExp:'[0-9]{1,200}',
                                 promptMessage:'<g:message code="common.diskReadRate.prompt"/>'">
                        </div>
                        <span id="diskOfferDiskReadRateBPSLabel" class="hide_lable"></span>
                        </div>                                                     

                      <div class="control-group span3 customDisk">
                        <label for="diskOfferDiskWriteRateBPS" class="control-label"><g:message code="common.diskWriteRate"/>: </label>
                        <div class="controls updatable elements">
                         <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                 id="diskOfferDiskWriteRateBPS" data-dojo-props="invalidMessage: '<g:message code="common.diskWriteRate.invalid"/>', 
                                 placeHolder: '<g:message code="common.diskWriteRate.invalid"/>',regExp:'[0-9]{1,200}',
                                 promptMessage:'<g:message code="common.diskWriteRate.prompt"/>'">
                        </div>
                        <span id="diskOfferWriteRateBPSLabel" class="hide_lable"></span>
                      </div>


                      <div class="control-group customDisk span3">
                        <label for="diskOfferDiskReadRateIOPS" class="control-label">
                           <g:message code="common.diskReadRateIOPS"/>: 
                        </label>                        
                        <div class="controls updatable elements">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                 id="diskOfferDiskReadRateIOPS" data-dojo-props="invalidMessage: '<g:message code="common.diskReadRateIOPS.invalid"/>',
                                 placeHolder: '<g:message code="common.diskReadRateIOPS"/>: ',regExp:'[0-9]{1,200}',
                                 promptMessage:'<g:message code="common.diskReadRateIOPS.prompt"/>'">
                        </div>
                        <span id="diskOfferDiskReadRateIOPSLabel" class="hide_lable"></span>
                      </div>
                      <div class="control-group customDisk span3">
                        <label for="diskOfferDiskWriteRateIOPS" class="control-label">
                           <g:message code="common.diskWriteRateIOPS"/>: 
                        </label>                        
                        <div class="controls updatable elements">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                 id="diskOfferDiskWriteRateIOPS" data-dojo-props="invalidMessage: '<g:message code="common.diskWriteRateIOPS.invalid"/>',
                                 placeHolder: '<g:message code="common.diskWriteRateIOPS"/>',regExp:'[0-9]{1,200}',
                                 promptMessage:'<g:message code="common.diskWriteRateIOPS.prompt"/>'">
                        </div>
                        <span id="diskOfferWriteRateIOPSLabel" class="hide_lable"></span>
                      </div>

                    </div>
                    <div class="row-fluid" id="qOSTypeStorageDiv" style="display: none">
                        <div class="control-group span3 customDisk" style="display: none">
                            <label for="isCustomizedIops" class="control-label"><g:message code="common.customIOPS"/>:</label>
                            <div class="controls updatable elements">
                             <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                                    data-dojo-props="checked: false" id="isCustomizedIops" 
                                    onClick="AddDiskOfferInfo.enableCustomizedIops(this)"> 
                            </div>
                            <span id="diskOfferCustomizedIopsLabel" class="hide_lable"></span>
                        </div>
                        <div id="customizedIopsDiv">
                            <div class="control-group span3 customDisk">
                              <label for="minIOPS" class="control-label"><g:message code="common.minIOPS"/>: </label>
                              <div class="controls updatable elements">
                               <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                       id="minIOPS" data-dojo-props="invalidMessage: '<g:message code="common.minIOPS.invalid"/>', 
                                       placeHolder: '<g:message code="common.minIOPS"/>',regExp:'[0-9]{1,200}',
                                       promptMessage:'<g:message code="common.minIOPS.prompt"/>'">
                              </div>
                              <span id="minIOPSLabel" class="hide_lable"></span>
                            </div>
                            <div class="control-group customDisk span3">
                              <label for="maxIOPS" class="control-label">
                                <g:message code="common.maxIOPS"/>: 
                              </label>
                              <div class="controls updatable elements">
                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                       id="maxIOPS" data-dojo-props="invalidMessage: '<g:message code="common.maxIOPS.invalid"/>',
                                       placeHolder: '<g:message code="common.maxIOPS"/>',regExp:'[0-9]{1,200}',
                                       promptMessage:'<g:message code="common.maxIOPS.prompt"/>'">
                              </div>
                              <span id="maxIOPSLabel" class="hide_lable"></span>
                            </div>
                        </div>
                        <div class="control-group customDisk span3" style="display: none">
                          <label for="hypervisorSnapReserve" class="control-label">
                            <g:message code="common.hypervisorSnapshotReserve"/>:
                          </label>
                          <div class="controls updatable elements">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                   id="hypervisorSnapReserve" data-dojo-props="invalidMessage: '<g:message code="common.hypervisorSnapshotReserve.invalid"/>',
                                   regExp:'[0-9]{1,200}',
                                   promptMessage:'<g:message code="common.hypervisorSnapshotReserve.prompt"/>'">
                          </div>
                          <span id="hypervisorSnapReserveLabel" class="hide_lable"></span>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="control-group span3 customDisk">
                            <label for="isPublic" class="control-label"><g:message code="common.public"/>:</label>
                            <div class="controls updatable elements">
                             <input type="checkbox" data-dojo-type="dijit.form.CheckBox"
                                    data-dojo-props="checked: true" id="isPublic" 
                                    onClick="AddDiskOfferInfo.enablePublic(this)"> 
                            </div>
                            <span id="diskOfferIsPublicLabel" class="hide_lable"></span>
                        </div>
                        <div class="control-group span4 customDisk" id="diskDomainDiv" style="display: none">
                            <label for="diskOfferDomain" class="control-label"><g:message code="common.domain"/>:</label>
                            <div class="controls updatable elements">
                                <div id="diskOfferDomainList" class="selectOption"></div>
                            </div>
                            <span id="diskOfferDomainLabel" class="hide_lable"></span>      
                        </div>
                    </div>
                    <div class="row-fluid" id="billingInfo">       
                        <div class="row-fluid header">            
                            <h3><g:message code="signup.wizard.title.billingInfo"/></h3>        
                        </div> 
                        <div id="currentDiskZoneInfo" class="row-fluid">
                            <div class="span2"></div>
                            <div class="span10"><div id="diskZoneCost" class="span12"></div></div>                            
                        </div>      
                    </div>
                    <div class="row-fluid">
                        <div class="span9">
                        </div>
                        <div class="span3">
                            <img class="hide_text" id="diskOfferLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading" />' height='16' width='23'/>
                            <div id="diskAddButtonDiv" class="span2">
                                <button type="button" data-dojo-type= "dijit.form.Button"  onclick="AddDiskOfferInfo.add()" id="diskAddButton" data-dojo-props="disabled: true" class="okbtn">
                                    <g:message code="common.ok"/>
                                </button>
                            </div>                            
                            <div id="diskUpdateButtonDiv" class="span3">
                                <button onclick="ViewDiskOfferInfo.updateShow()" data-dojo-type="dijit.form.Button" id="diskUpdateButton" style="display: none" class="okbtn">
                                    <g:message code="common.apply"/>
                                </button>
                            </div>
                            <div id="diskCancelButtonDiv" class="span2">
                                <button id="diskCancelButton" data-dojo-type="dijit.form.Button"onclick="AddDiskOfferInfo.cancel()" data-dojo-props="" class="cancelbtn">
                                    <g:message code="common.cancel"/>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>    
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="diskOfferEditConformationDialog" title="Update" class="span4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser"/></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn" type="button"  data-dojo-type="dijit.form.Button" onclick="ViewDiskOfferInfo.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn" type="button"  data-dojo-type="dijit.form.Button" onclick="ViewDiskOfferInfo.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>