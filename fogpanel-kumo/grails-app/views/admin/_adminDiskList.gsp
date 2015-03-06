<div class="row-fluid">
    <div class="span12 breadcrumbs">
      <ul>
        <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
        <li>/</li>
        <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services"/></a></li>
        <li>/</li>
        <li><g:message code="common.storage"/></li>    
      </ul>
    </div>
</div>
<div class="row-fluid"> 
  <div id="main-stats">
    <div class="row-fluid stats-row">
        <div class="span4 stat">
            <div class="data">
                <span class="number" id="adminTotalDisk"></span>
                <g:message code="stat.totalDisk"/>
            </div>
           
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="adminEnabledDisk"></span>
               <g:message code="stat.enbledDisk"/>
            </div>
            
        </div>
        <div class="span4 stat">
            <div class="data">
              <span class="number" id="adminDisabledDisk"></span>
               <g:message code="stat.disabledDisk"/>
            </div>            
        </div>
        
    </div>
</div>
</div>
<div class="row-fluid">
  <form id="diskListForm" data-dojo-type="dijit.form.Form">
<div>
<div class="table-wrapper products-table">
  <div class="row-fluid">          
        <div class="value_dollar pull-right"><g:message code="default.valueIn"/> <span id="diskCurrencyValue"></span></div>  
  </div>
    
  <div class="row-fluid filter-block">
        <div class="pull-right">
            <button type="button" data-dojo-type= "dijit.form.Button" class="primarybtn" onclick="DiskOfferListInfo.conformPull()" id="">
              <g:message code="common.pullPlanFromCloudStack"/>
            </button>
            <a class="btn-flat success new-product" href="#/admin/disk/addDisk">+ <g:message code="common.add"/></a>
        </div>
    </div>

    <div class="row-fluid">
      <div id="adminDiskList">  
      </div>
    </div>
      <div class="alert alert-info hide" id="noDiskMessageBox" style="display: none">
        <i class="icon-exclamation-sign"></i> 
        <g:message code="user.storage.noStorage"/>
      </div>
    <!--</div>-->
</div>
</div>
  </form>
</div>              
<div data-dojo-type="dijit.Dialog" id="deleteDiskOfferDialog" title="<g:message code="common.admin.deleteDisk.title"/>" style="color: black; width: 350px;">
    <input type="checkbox" data-dojo-type="dijit.form.CheckBox" id="diskDeleteConfirm"/>
    <g:message code="common.deleteItem.confirm"/>  
    <div style="margin-left: 100px">
    <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn"
    onclick="DeleteDiskOffer.conformDelete()" id="">
    <g:message code="common.yes"/>
    </button>
    <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn"
    onclick="DeleteDiskOffer.closeDeleteDialog()">
    <g:message code="common.no"/>
    </button> 
    </div>  
</div>
 <div data-dojo-type="dijit.Dialog" id="pullPlanConform" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.pullPlanMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.pullPlanMessageNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="DiskOfferListInfo.pullPlan()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="DiskOfferListInfo.cancelPullPlan()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="pullPlanLoader" class="span6">
    <div class="row-fluid" style="display: block">
        <img src="images/configLoader.gif" class="span1 spacing"/>
        <p class="message span10"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>