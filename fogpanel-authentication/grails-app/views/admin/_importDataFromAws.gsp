<div class="row-fluid"> 
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/aws"><g:message code="menu.admin.configuration.aws"/></a></li>
            <li>/</li>
            <li><g:message code="common.import"/></li>   
        </ul>
    </div>
</div>
<div class="alert alert-info text_gray" id="openStackNotConfiguredMsgImport" style="display: none; margin-top: 50px;" >
    <i class="icon-warning-sign text_gray"></i>
    <g:message code="common.openStackNotConfigured"/>
    <a href="#/admin/settings/openstackConfig">
        <g:message code="common.clickToConfigure"/>
    </a>
</div>
<div id="importDataFromAws" style="display : block">
    <div class="row-fluid"> 
        <div class="span12">
            <h3><g:message code="common.aws.import.head"/></h3>
        </div> 
    </div>
<!--    <div class="row-fluid">
        <div class="row-fluid filter-block" id="" style="display: block;margin-top: 10px;">
            <div class="pull-right">
                <button type="button" data-dojo-type= "dijit.form.Button" id="" class="cancelbtn" onclick="ImportDataFromAws.confirmPullAllDataFromOpenstack()">
                    <g:message code="common.pullAllData"/>
                </button>
            </div>
        </div>
    </div>-->
    <div class="row-fluid">                
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.import.image"/></b>
                </div>
                    <div class="span2 pull-right" style="margin-top: 10px;">
                        <button type="button" data-dojo-type= "dijit.form.Button" id="pullImageButton" class="cancelbtn" onclick="ImportDataFromAws.confirmPullImage()" id="">
                                <g:message code="common.import"/>
                                <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                <img id="pullImageCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                        </button>
                        <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromAws.checkDataImportImageStatus()">
                            <g:message code="common.check"/>
                        </button>
                </div>
            </div> 
        </div>      
    </div>
    <div class="row-fluid">                
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.import.regions"/></b>
                </div>
                    <div class="span2 pull-right" style="margin-top: 10px;">
                        <button type="button" data-dojo-type= "dijit.form.Button" id="pullRegionButton" class="cancelbtn" onclick="ImportDataFromAws.pullConfirmRegions()" id="">
                                <g:message code="common.import"/>
                                <img id="pullRegionLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                <img id="pullRegionCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                        </button>
                        <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromAws.checkDataImportRegionStatus()">
                            <g:message code="common.check"/>
                        </button>
                </div>
            </div> 
        </div>      
    </div>
    
   <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
        <div class="span12">
            <div class="span8" style="padding: 16px;">
                <b><g:message code="common.import.availabilityZone"/></b>
            </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" id="pullZoneButton" class="cancelbtn" onclick="ImportDataFromAws.confirmZonePull()" >
                            <g:message code="common.import"/>
                            <img id="pullZoneLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullZoneCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromAws.checkDataImportZoneStatus()">
                        <g:message code="common.check"/>
                    </button>
            </div>
        </div> 
    </div>
        
    <div data-dojo-type="dijit.Dialog" id="pullImageConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.aws.pullImageMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.aws.pullImageMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromAws.pullImages()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromAws.cancelPullImages()"><g:message code="common.cancel"/></button>
        </div>
    </div>    
    <div data-dojo-type="dijit.Dialog" id="pullRegionConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.aws.pullRegionMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.aws.pullRegionMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromAws.pullRegions()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromAws.cancelPull()"><g:message code="common.cancel"/></button>
        </div>
    </div> 
    <div data-dojo-type="dijit.Dialog" id="pullZoneConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.aws.pullAvailabilityZonesMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.aws.pullAvailabilityZoneNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromAws.pullAllZone()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromAws.cancelZonePull()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>
