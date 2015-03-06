<div class="row"> 
    <div class="col-md-12 breadcrumbs">
        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/openstack"><g:message code="menu.admin.configuration.openstack"/></a></li>
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
<div id="importDataFromOpenStack" style="display : block">
    <div class="row"> 
        <div class="col-md-12">
            <h3><g:message code="common.openstack.import.head"/></h3>
        </div> 
    </div>
    <div class="row">
        <div class="row filter-block" id="" style="display: block;margin-top: 10px;">
            <div class="pull-right">
                <button type="button" data-dojo-type= "dijit.form.Button" id="" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullAllDataFromOpenstack()">
                    <g:message code="common.pullAllData"/>
                </button>
            </div>
        </div>
    </div>
    <div class="row"> 
         <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.import.regions"/></b>
                </div>
                <div class="col-md-2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullRegion()" id="pullRegionsButton">
                            <g:message code="common.import"/>
                            <img id="pullRegionsLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullRegionsCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportRegionsStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
         </div>
         <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.pullDomainsFromOpenstack"/></b>
                </div>
                <div class="col-md-2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullDomain()" id="pullDomainsButton">
                            <g:message code="common.import"/>
                            <img id="pullDomainsLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullDomainsCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportDomainsStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
         </div>
         <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.pullAccounts"/></b>
                </div>
                <div class="col-md-2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullAccount()" id="pullAccountButton">
                        <g:message code="common.import"/>
                        <img id="pullAccountLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullAccountCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportAccountStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>      
        <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.pullUsers"/></b>
                </div>
                <div class="col-md-2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullUser()" id="pullUserButton">
                        <g:message code="common.import"/>
                        <img id="pullUserLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullUserCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportUserStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div> 
        <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.import.volumeType"/></b>
                </div>
                <div class="col-md-2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullVolumeType()" id="pullVolumeTypeButton">
                            <g:message code="common.import"/>
                            <img id="pullVolumeTypeLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullVolumeTypeCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportVolumeTypeStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
         </div> 
         <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.import.availabilityZone"/></b>
                </div>
                    <div class="col-md-2 pull-right" style="margin-top: 10px;">
                        <button type="button" data-dojo-type= "dijit.form.Button" id="pullZoneButton" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmZonePull()" >
                                <g:message code="common.import"/>
                                <img id="pullZoneLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                <img id="pullZoneCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                        </button>
                        <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportZoneStatus()">
                            <g:message code="common.check"/>
                        </button>
                </div>
            </div> 
        </div>
        <div class="row accquireip-form-boxcont">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.import.flavor"/></b>
                </div>
                <div class="col-md-2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.conformPullPlan()" id="pullPlanButton">
                            <g:message code="common.import"/>
                            <img id="pullPlanLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullPlanCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportPlanStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>      
        <div class="row accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="col-md-12">
                <div class="col-md-8" style="padding: 16px;">
                    <b><g:message code="common.import.image"/></b>
                </div>
                    <div class="col-md-2 pull-right" style="margin-top: 10px;">
                        <button type="button" data-dojo-type= "dijit.form.Button" id="pullImageButton" class="cancelbtn" onclick="ImportDataFromOpenstack.confirmPullImage()" id="">
                                <g:message code="common.import"/>
                                <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                <img id="pullImageCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                        </button>
                        <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromOpenstack.checkDataImportImageStatus()">
                            <g:message code="common.check"/>
                        </button>
                </div>
            </div> 
        </div>      
        
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullAllDataConfirm" class="col-md-4">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullAllDataMessage' /></p></div>
                <div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullAllDataMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullAllDataFromOpenstack()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelpullAllDataFromOpenstack()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullPlanConform" class="col-md-4">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullPlanMessage' /></p></div>
                <div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullPlanMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullPlan()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelPullPlan()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullImageConfirm" class="col-md-4" style="display:none;">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullImageMessage' /></p></div>
                <div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullImageMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullImages()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelPullImages()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullVolumeTypeConfirm" class="col-md-4" style="display:none;">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullVolumeTypeMessage' /></p></div>
                <div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullVolumeTypeNote' /></p></div>
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullVolumeType()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelPullVolumeType()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullZoneConfirm" class="col-md-4" style="display:none;">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullAvailabilityZonesMessage' /></p></div>
                <div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullAvailabilityZoneNote' /></p></div>
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullAllZone()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelZonePull()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullRegionsConfirm" class="col-md-4" style="display:none;">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullRegionMessage' /></p></div>
                <div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullRegionNote' /></p></div>
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullAllRegion()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelRegionPull()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullDomainsConfirm" class="col-md-4" style="display:none;">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.openstack.pullDomainMessage' /></p></div>
                <!--div class="col-md-12"><p class="alert alert-info"><g:message code='common.openstack.pullRegionNote' /></p></div-->
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullAllDomain()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelDomainPull()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullAccountConfirm" class="col-md-4">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.pullAccountMessage' /></p></div>
                <!--<div class="col-md-12"><p class="alert alert-info"><g:message code='common.pullAccountMessageNote' /></p></div>-->
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullAccount()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelPullAccount()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullUserConfirm" class="col-md-4">
        <div class="row">
            <!--<div class="col-md-2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="col-md-10">
                <div class="col-md-12"><p><g:message code='common.pullUserMessage' /></p></div>
                <!--<div class="col-md-12"><p class="alert alert-info"><g:message code='common.pullUserMessageNote' /></p></div>-->
            </div>                                    
        </div>
        <div class="row">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.pullUser()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromOpenstack.cancelPullUser()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>