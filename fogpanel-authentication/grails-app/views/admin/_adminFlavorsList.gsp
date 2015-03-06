<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/services" ><g:message code="menu.admin.services"/></a></li>
            <li>/</li>
            <li><g:message code="menu.flavors"/></li>    
        </ul>
    </div>
</div>
<!--<div class="row-fluid header">
        <div class="span12">
            <h2>Computing Offer</h2>
        </div>
    </div>-->
<div class="row-fluid"> 
    <div id="main-stats">
        <div class="row-fluid stats-row">
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminTotalFlavor"></span>
                    <g:message code="stat.admin.totalFlavors"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminEnabledFlavor"></span>
                    <g:message code="stat.admin.enableFlavors"/>
                </div>

            </div>
            <div class="span4 stat">
                <div class="data">
                    <span class="number" id="adminDisabledFlavor"></span>
                    <g:message code="stat.admin.disableFlavors"/>
                </div>            
            </div>

        </div>
    </div>
</div>
<div class="row-fluid" style="display: block;">
    <form id="flavorListForm" data-dojo-type="dijit.form.Form">
        <div class="table-wrapper products-table">       
            <div class="row-fluid" style="display: none;">
                <div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="floavourCurrencyValue"></span></div>
            </div>
            <div class="row-fluid filter-block" id="adminFlavorPullDiv" style="display: block;margin-top: 10px;">
                <div class="pull-right">
<!--                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ListFlavors.conformPull()" id="pullPlanButton">
                        <g:message code="common.pullPlanFromOpenstack"/>
                        <img id="pullPlanLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                    </button>-->
                    <button class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListFlavors.populateValues()">
                        <g:message code='common.refresh' />
                    </button>
                    <!--<a class="btn-flat success new-product" href="#/admin/flavors/createFlavor">+ <g:message code="common.createFlavor"/></a></div>-->
            </div>
            <div class="row-fluid">
                <div id="adminFlavorsList">  
                </div>
                <div class="alert alert-info text_gray" id="openStackNotConfiguredMsgFlavor" style="display: none; margin-top: 50px;" >
                    <i class="icon-warning-sign text_gray"></i>
                    <g:message code="common.openStackNotConfigured"/>
                    <a href="#/admin/settings/openstackConfig">
                        <g:message code="common.clickToConfigure"/>
                    </a>
                </div>
                <div class="alert alert-info hide text_gray" id="noOfferMessageBox" style="display: none">
                    <i class="icon-exclamation-sign text_gray"></i> 
                    <g:message code="common.admin.noFlavors"/>&nbsp;&nbsp;<a href="#/admin/flavors/createFlavor"><g:message code="common.createOneNow"/></a>
                </div>
            </div>
        </div>
    </form>
</div>        
<div data-dojo-type="dijit.Dialog" id="deleteFlavorDialog"  title="<g:message code="common.delete"/>" style="color: black; width: 350px;">
    <input type="hidden" id="flavorDeleteId"/>
    <div class="row-fluid form-wrapper">
        Are you sure you want to delete this item?
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="FlavorInfo.confirmDelete()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="FlavorInfo.closeDeleteFlavorDialog()">
                <g:message code="common.no"/>
            </button>       
        </div> 
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="pullPlanConform" class="span4">
    <div class="row-fluid">
        <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
        <div class="span10">
            <div class="span12"><p><g:message code='common.openstack.pullPlanMessage' /></p></div>
            <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullPlanMessageNote' /></p></div>
        </div>                                    
    </div>
    <div class="row-fluid">
        <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ListFlavors.pullPlan()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ListFlavors.cancelPullPlan()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" class="full_loader" id="flavorLoaderFromDelete" class="span6">
    <div class="row-fluid">
        <div class="span3"><img src='images/popup-icons/new_vm_launch.gif'/></div>
        <div class="span9">
            <div class="span12"><p><g:message code='common.deleteFlavorInfoOne' /></p></div>
            <div class="span12" style="margin-left: 0"><p><g:message code='common.deleteFlavorInfoTwo' /></p></div>              
        </div>          
    </div>
    <div class="row-fluid">        
        <a class="btn-flat default" onclick="FlavorInfo.gotoList()"><g:message code='common.gotoFlavorList' /></a>
<!--        <a class="btn-flat default" onclick="AddServer.gotoDashboard()"><g:message code='common.gotoDashboard' /></a>-->      
    </div> 
</div>
<div data-dojo-type="dijit.Dialog" id="flavorStatChangeDialog"  title="<g:message code="common.updateStatus"/>" style="color: black; width: 350px;dispaly:none">
    <input type="hidden" id="currentFlavorStatUpdateId"/>
    <input type="hidden" id="currentFlavorStatus"/>
    
    <div class="row-fluid form-wrapper">
        <p id="flavorAvailableConfimMsg"></p>
        <div class="span5 field-box control-group">
            <button type="button" data-dojo-type= "dijit.form.Button" class="defaultbtn" onclick="ListFlavors.flavorAvailableAction()" id="">
                <g:message code="common.yes"/>
            </button>
            <button id="" data-dojo-type="dijit.form.Button" class="cancelbtn" onclick="ListFlavors.closeDialogue()">
                <g:message code="common.no"/>
            </button>       
        </div>  
    </div>
</div>