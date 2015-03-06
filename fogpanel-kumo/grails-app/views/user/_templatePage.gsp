<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/template" onclick="OsTemplateInfo.hideTempDesc()"><g:message code="menu.user.templates"/></a></li>
    <li class="hide_text" id="breadcrumTemp">/<li>
    <li class="hide_text" id="breadcrumTempName"></li>
  </ul>
</div>
</div> 
<g:render template="templateInfo" />
<div class="row-fluid">
    <form data-dojo-type="dijit.form.Form" id="filteringForm" class="form-horizontal tpl-form-element">
        <div class="span2 field-box control-group" id="hypervisorDiv">
        <label class="control-label"><g:message code="common.hypervisor"/>:</label>
        <div class="controls elements">
            <div id="tempHypervisorList" class="selectOption"></div>
        </div>
    </div>
    <div class="span2 field-box control-group">
        <label class="control-label"><g:message code="common.osType"/>:</label>
        <div class="controls elements">
            <div id="tempOSTypeList" class="selectOption"></div>
        </div>
    </div>
    <div class="span2 field-box control-group">
         <label class="control-label"><g:message code="common.arch"/>:</label>
        <div class="controls elements">
            <div id="tempCategoryList" class="selectOption"></div>
        </div>
    </div>
    <div class="span3 field-box control-group">
        <div class="span8">
            <label class="control-label"><g:message code="common.search"/>:</label>
            <div class="controls elements">
                <input type="text" data-dojo-type="dijit.form.TextBox" id="serachFilterBox" />            
            </div>
        </div>
            <div>
                <a onclick="OsTemplateInfo.serachTemplate(this)" class="tpl-search-btn btn-glow"></a>
            </div>        
    </div>
    <div class="pull-right"><a onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a><g:render template="rateCard" /></div>
    </form>
</div>
<div class="row-fluid">
    <div class="span1">        
        
    </div>
</div>
<div class="row-fluid">
    <div class="span12 tpl-fulldesc-box hide_text" id="templateDesc">
    <div class="span10">
        <h3 id="tempDescName"></h3>
        <div class="row-fluid">
            <div>
                <div class="span12 tpl-fulldesc-cont">
                    <p id="templateDetailDesc"></p>                                      
                </div>
            </div>
        </div>
    </div>
    <div class="span2">
        <div class="row-fluid">
            <div class="span12 tpl-avatar-thumb" id="tpl-avatar"><img src="images/os/os_ubuntu_logo.png" id="templatDecsLogo"/></div>
        </div>
        <div class="row-fluid">
            <div class="span12 tpl-category" id="tpl-category"><span><g:message code="common.osType"/>:</span><span id="templDescOSType"></span></div>
        </div>
        <div class="row-fluid">
            <div class="span12 tpl-category" id="tpl-category"><span><g:message code="common.zone"/>:</span><span id="templDescZone"></span></div>
        </div>
        <div class="row-fluid">
            <div class="span12 tpl-category" id="tpl-category"><span><g:message code="common.hyperviser"/>:</span><span id="templDescHypervisor"></span></div>
        </div>        
        <div class="row-fluid">
            <div class="span12 tpl-weblink"><a id="tpl-website" target="new"></a></div>
        </div>
        <div class="row-fluid">
            <div class="span12 tpl-cost" id="tpl-cost"><span><g:message code="common.cost"/>:</span><span id="templDescCost" class="unitCost"></span></div>
        </div>
        <div class="row-fluid">
                <div class="span12" id="tpl-launchvm">
                    <a id="tempDescCreateVM" title="<g:message code="common.vm.create"/>" class="btn-glow primary new-product overflowLabel" href="#/user/cloud/createVm"><img src="${resource(dir: 'images')}/vm_quicklch_icon.png" alt=""></img><g:message code="user.cloud.launchVM"/></a>                     
                </div>
                <a title="Go to template list" onclick="OsTemplateInfo.hideTempDesc()" class="tampl-back-tag"><g:message code="common.back"/></a>
        </div>
        <div class="row-fluid">
                <div class="span12" id="tpl-back"></div>
        </div>
    </div>
</div>
</div>
<div class="row-fluid">
    <h4 id="otherTempHeading" class="hide_text tpl-othertpls-title"></h4>
    <ul class="span12 templatebox-wrapper" id="templateCollection">
    </ul>
</div>
<div class="row-fluid">
    <!--<div class="span8"></div>--> 
    <div class="pull-right" id="templatePaginationList" ><div  id="tempPagination"></div></div>
</div>
<!--<div id="page"></div>-->
<div class="row-fluid">
    <div class="alert alert-info hide" id="noTemplateMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="common.user.noTemplate"/>
    </div>
</div>
