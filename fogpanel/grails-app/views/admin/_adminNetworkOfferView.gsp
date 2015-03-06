<%@ page contentType="text/html;charset=UTF-8" %>
<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/computation/services" onclick="ServiceConfig.openTab()"><g:message code="menu.admin.services"/></a></li>
    <li>/</li>
    <li><a href="#/admin/networkOffer"><g:message code="menu.admin.services.networkoffer"/></a></li>
    <li>/</li>
    <li><g:message code="common.view"/></li>
  </ul>
</div>
</div>
<div class="row-fluid">
<div class="span12" id="adminComputingOfferPage">
    <div class="row-fluid header">
        <h3><g:message code="common.technicalInfoNetworkView"/></h3>   <div class="span2 value_dollar pull-right"><g:message code="default.valueIn"/>  <span id="currencyValue"></span></div>
    </div>
  <form id="adminNetworkOfferZoneForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
    <input type="hidden" id="currentNetworkOfferId" name="" value="">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="control-group span12 horizontalcontent">
                    <label for="computingOfferZoneList" class="control-label">
                      <g:message code="user.createVM.zone.label"/>: 
                       <!--<span class="require">*</span>-->
                    </label>
                    <span id="zoneNameLabel" class="hide_lable show_lable"></span>
                </div>
            </div>
            <div class="row-fluid">
                <div class="control-group span12 horizontalcontent">
                    <label for=""  class="control-label">         
                     <g:message code="common.name"/> :
                       <!--<span class="require">*</span>-->
                    </label>
                    <span id="networkOfferNameLabel" class="hide_lable show_lable"></span>
                </div> 
            </div>
            <div class="row-fluid">
                <div class="control-group span12 horizontalcontent">
                    <label for=""  class="control-label">         
                     <g:message code="common.desc"/> :
                       <!--<span class="require">*</span>-->
                    </label>
                    <span id="networkOfferDesLabel" class="hide_lable show_lable"></span>
                </div> 
            </div>
        </div>
    </div>
  </form>
  <div id="networkOfferContent">
    <form id="adminNetworkOfferContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
        <div class="row-fluid">
            <div class="control-group span12 customDisk">
                <label for="" class="control-label"><g:message code="common.NWRate"/>(<g:message code="common.mb"/>):</label>
                <div class="controls">
                </div>
                <span id="networkOfferNetWorkRate" class="hide_lable show_lable"></span>
            </div>
        </div>  
        <div class="row-fluid">
            <div class="control-group span4 customDisk">
                <label for="" class="control-label"><g:message code="common.supportedService"/>:</label>
                <div class="controls"> 
                </div>
                <span id="networkOfferService" class="hide_lable show_lable"></span>
            </div>
        </div>  
        <div class="control-group customDisk span4">

        </div>
        <div class="row-fluid">           

        </div>
        <div class="row-fluid">

        </div>
        <div class="row-fluid">

        </div> 
        <div class="row-fluid">

        </div> 
        </form>
    </div>
<!--    <div class="row-fluid non_updatable" id="billingInfo" >
        <div class="row-fluid header">
            <h3><g:message code="signup.wizard.title.billingInfo"/></h3>
        </div> 
        <div id="currentNetworkZoneInfo" class="row-fluid">
          
        </div>    
    </div>
    <div class="row-fluid">
      <div class="span10">
      </div>
      <div class="span2">
            <button   id="serviceCancelButton"  data-dojo-type="dijit.form.Button"  
                  onclick="AddComputingOfferInfo.cancel()" class="cancelbtn">
            <g:message code="common.cancel"/>
          </button>
          <button   id="serviceUpdateButton" style="display: none;"
                    data-dojo-type="dijit.form.Button" 
                    onclick=" ViewComputingOfferInfo.updateShow()" class="okbtn">
            <g:message code="common.apply"/>
          </button>
      </div>
    </div>-->
</div>
<!--<div data-dojo-type="dijit.Dialog" id="computeOfferEditConformationDialog" title="Update" class="span4">
    <p><g:message code="admin.updateItem"/></p> 
    <p class="alert alert-info"><g:message code="admin.updateItemInfoAllUser"/></p>
      <div class="row-fluid offset1">
        <button class="overflowLabel defaultbtn"  type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.update()"><g:message code="common.ok"/></button>
        <button class="overflowLabel cancelbtn"   type="button"  data-dojo-type="dijit.form.Button" onclick="ViewComputingOfferInfo.closeUpdate()"><g:message code="common.cancel"/></button>
    </div>
</div>-->
