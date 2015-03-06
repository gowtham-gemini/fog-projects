<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/user/vpc/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/user/vpc/vpcContainer"><g:message code="common.vpc.yourVPC"/></a></li>
        <li>/</li>
        <li><g:message code="common.publicLB"/></li>    
      </ul>
  </div>
</div>
<div class="row-fluid filter-block">
    <div class="pull-right" id="publicLBListActionButtonCollection">
        <a class="btn-flat success new-product" onclick="ListAllPublicLBInfo.populateValues();"><i class="icon-refresh"></i> <g:message code='common.refresh' /></a> 
        <a class="btn-flat success new-product" onclick="ListAllPublicLBInfo.acquireIPShow();"><g:message code="common.addPublicLB"/></a>        
    </div>
    <div class="row-fluid"><div class="span12"></div></div>
    <div class="row-fluid"><div class="span12"></div></div>    
    <div class="row-fluid">
      <div id="vpcGeneralPublicLB"></div>
    </div>
    <div class="row-fluid">
      <div class="alert alert-info hide" id="noGeneralPublicLBMsg" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <span id="noLBMsg"><g:message code="common.user.noLoadBalancing"/></span>
      </div>
    </div>
</div>


<div data-dojo-type="dijit.Dialog" id="generalVpcAcquireIpDialog" title="<g:message code="common.addPublicLB"/>" class="span5">   
    <div class="row-fluid"> 
            <div class="span2">                
                <a class="index_title_icons_lb span12"></a>                
            </div>
        <!--<div class="span10">-->            
            <div class="span9">
                <div class="row-fluid">   
                    <form data-dojo-type="dijit.form.Form" class="form-horizontal">        
                        <!--<div class="span9">-->
                            <div class="form-horizontal">
                                <div class="row-fluid">                    
                                    <div class="control-group">
                                        <label class="control-label">
                                            <g:message code="common.zone"/>
                                            <span class="require">*</span>
                                        </label>
                                            <div class="controls ">
                                                <div id="generalPublcLBZoneList"></div>
                                                <img id="generalPublcLBZoneLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            <g:message code="common.vpc"/>
                                            <span class="require">*</span>
                                        </label>
                                            <div class="controls ">
                                                <div id="generalPublicLBVPCList"></div>
                                                <img id="generalPublcLBVPCLoader" class="offset4 hide_text" src="${resource(dir: 'images')}/preloader_circle.gif" alt="reset" height="20" width="20">
                                            </div>
                                    </div>
                                    <div class="span12 field-box control-group" id="ipConfigContainer">
                                        <label for="" class="control-label"><g:message code="common.ip"/>:<span class="require">*</span></label>
                                        <div class="controls">
                                            <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton" id="publicIPNewIP" name="ipConfig"  value="newIP"/>
                                            <span style="float: left"><g:message code="common.newIP"/></span> 
                                            <input type="radio" id="publicIPVPCIP" data-dojo-type="dijit.form.RadioButton" name="ipConfig"  value="vpcIP" style="margin-left: 10px;"/> 
                                            <span style="float: left"><g:message code="common.vpcIP"/></span>                     
                                        </div>
                                    </div>
                                </div>
                            </div> 
                        <!--</div>-->
                    </form>
                </div>
            </div>
            <div class="span12" id="acquireIPContainer">
                <div class="row-fluid">                                        
                    <div class="span12">              
                        <div class="span6 offset2">
                            <p><g:message code="common.agreeThe"/><a onClick="ListAllPublicLBInfo.showCondition()"> <g:message code="signup.termsAndConditions"/></a></p>
                        </div>
                        <div class="span2">
                            <input type="checkbox" data-dojo-type="dijit.form.CheckBox" data-dojo-props="checked: false" id="generalPublicLBInfoAgreement" name="agreement">     
                        </div>                    
                    </div>
                
                </div>
            </div>              
            <div class="span12"> <span class="validation" id="publicLBConditionExceptionMsg" style="margin-left: 0"><g:message code="common.termsCondition.info"/></span></div>
            <div class="span12">
                <div class="row-fluid">
                    <div class="span8"></div>
                    <div class="span4">
                        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicLBInfo.acquireIp()"><g:message code="common.ok"/></button>
                        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListAllPublicLBInfo.closeAcquireIPShow()"><g:message code="common.cancel"/></button>
                    </div>
                </div>
            </div>
        <!--</div>-->
    </div>                                                
</div>

<div data-dojo-type="dijit.Dialog"  id="generalPublicLBContent" style="height: 400px;" title="<g:message code="signup.termsAndConditions"/>" class="customDialog span8">
    <div style="overflow-x: hidden; overflow-y: scroll; height: 350px">
        <div id="generalPublicLBContentArea">
        </div>                           
        <div class="row-fluid">
            <div class="span10"></div>
            <div class="span1"><button class="primarybtn" type="button" data-dojo-type="dijit.form.Button" onclick="ListAllPublicLBInfo.cancelConditionBox()"><g:message code="common.ok"/></button></div>
        </div>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="generalPublicLBLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>