<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.internalLoadBalancing"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right">
        <a class="btn-flat success new-product" onclick="ListAllInternalLBInfo.showAddInternalLB()"><g:message code="common.addInternalLB"/></a>            
    </div>
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid"><div class="span12"></div></div>    
    <div class="row-fluid">
      <div id="vpcGeneralInternalLB"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noGeneralInternalLBMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noLoadBalancing"/>
      </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog"  id="addGeneralInternalLbDialog" title="<g:message code="common.addInternalLB"/>" class="customDialog span6">
    <div class="row-fluid">   
        <div class="row-fluid">
            <form id="addGeneralInternalLBPageForm" data-dojo-type="dijit.form.Form" class="form-horizontal">        
                <div class="span9">
                    <div id="addGeneralInternalLBPageDiv" class="form-horizontal">
                        <div class="row-fluid">
                            <div class="row-fluid">                    
                                <div class="control-group">
                                    <label class="control-label">
                                        <g:message code="common.zone"/>
                                        <span class="require">*</span>
                                    </label>
                                    <div class="controls">
                                        <div id="generalInternalLbZoneList"></div>
                                        <img id="generalInternalLBZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                    </div>
                                </div>
                    <div class="control-group">
                         <label class="control-label">
                             <g:message code="common.vpc"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                         <div id="generalInternalLbVPCList"></div>
                         <img id="generalInternalLBVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label">
                             <g:message code="common.tier"/>
                             <span class="require">*</span>
                         </label>
                      <div class="controls ">
                         <div id="generalInternalLbTierList"></div>
                         <img id="generalInternalLBTierLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                      </div>
                    </div>
                </div>
                    <div class="control-group">
                         <label for="internalLBName" class="control-label">
                             <g:message code="common.name"/>
                             <span class="require">*</span>
                         </label>
                        <div class="controls ">
                          <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                          id="generalInternalLBName" data-dojo-props="required: 'true',invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                          promptMessage:'<g:message code="common.name.placeHolder"/>'">
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="internalLBDescription"  class="control-label">         
                            <g:message code="common.desc"/> :
                        </label>
                        <div class="controls elements">
                            <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                            id="generalInternalLBDescription" data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                            promptMessage:'<g:message code="common.description.prompt"/>'"/>

                        </div>
                    </div>
                    <div class="control-group">
                        <label for="internalLBAlgorithm" class="control-label">
                            <g:message code="common.loadBalancingAlgorithm"/>
                            <span class="require">*</span>
                        </label>
                        <div class="controls">
                            <select name="internalLBAlgorithm" id="generalInternalLBAlgorithm" data-dojo-type="dijit.form.FilteringSelect">
                                <option value="roundrobin">Round-robin</option>
                                <option value="leastconn">Least connections</option>
                                <option value="source">Source</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="internalLBSourcePort" id="" class="control-label">
                          <g:message code="common.sourcePort"/>
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                                 data-dojo-props="invalidMessage: '<g:message code="common.sourcePort.invalid"/>', required: 'true',
                                 placeHolder: '<g:message code="common.sourcePort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.sourcePort.invalid"/>',
                                 promptMessage: '<g:message code="common.sourcePort.prompt"/>'"
                                id="generalInternalLBSourcePort">
                        </div>
                    </div>
                    <div class="control-group">
                         <label for="internalLBInstancePort" id="" class="control-label">
                          <g:message code="common.instancePort"/>
                          <span class="require">*</span>
                        </label>
                        <div class="controls">
                        <input type="text" data-dojo-type="dijit.form.NumberTextBox" 
                                 data-dojo-props="invalidMessage: '<g:message code="common.instancePort.invalid"/>', required: 'true',
                                 placeHolder: '<g:message code="common.instancePort.prompt"/>', constraints:{pattern:'#',min:-1,max:65535},  missingMessage:'<g:message code="common.instancePort.invalid"/>',
                                 promptMessage: '<g:message code="common.instancePort.prompt"/>'"
                                id="generalInternalLBInstancePort">
                        </div>
                    </div>                    
                </div>
            </div> 
        </div>
</form>
</div>
    </div>
    <div class="row-fluid">
        <div class="span8"><span style="display: none" class="offset3 require" id="internalLBErrorMessage"><g:message code="user.createVM.required"/></span></div>
        <div class="span4">
            <div class="pull-right">
           <button data-dojo-type="dijit.form.Button" onclick="ListAllInternalLBInfo.addLoadBalancing();" class="primarybtn">
              <g:message code="common.ok"/>
           </button>
           <button data-dojo-type="dijit.form.Button" onclick="ListAllInternalLBInfo.closeAddInternalLB();" class="cancelbtn">
              <g:message code="common.cancel"/>
           </button>
         </div>
        </div>                 
     </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="generalInternalLBLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>