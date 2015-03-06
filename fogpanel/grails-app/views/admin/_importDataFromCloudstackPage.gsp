<div class="row-fluid"> 
    <div class="span12 breadcrumbs">

        <ul>
            <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/admin/settings"><g:message code="menu.admin.configuration"/></a></li>
            <li>/<li>
            <li><a href="#/admin/settings/cloudStack"><g:message code="menu.admin.configuration.cloudStack"/></a></li>
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
<div id="importDataFromCloudstack" style="display : block">
    <div class="row-fluid"> 
        <div class="span12">
            <h3><g:message code="common.cloudstack.import.head"/></h3>
        </div> 
    </div>
    <!--div class="row-fluid">
        <div class="row-fluid filter-block" id="" style="display: block;margin-top: 10px;">
            <div class="pull-right">
                <button type="button" data-dojo-type= "dijit.form.Button" id="" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullAllData()">
                    <g:message code="common.pullAllData"/>
                </button>
            </div>
        </div>
    </div-->
    <div class="row-fluid"> 
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullDomain"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.conformPullDomain()" id="pullDomainButton">
                        <g:message code="common.import"/>
                        <img id="pullDomainLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullDomainCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportDomainStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div> 
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullZonesFromCloudstack"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" id="pullZoneButton" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmZonePull()" >
                        <g:message code="common.import"/>
                        <img id="pullZoneLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullZoneCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportZoneStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px; display: none;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullAccounts"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullAccount()" id="pullAccountButton">
                        <g:message code="common.import"/>
                        <img id="pullAccountLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullAccountCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportAccountStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>      
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px; display: none;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullUsers"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullUser()" id="pullUserButton">
                        <g:message code="common.import"/>
                        <img id="pullUserLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullUserCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportUserStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>    
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;" id="pullPlanContainer">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullPlan"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.conformPullPlan()" id="pullPlanButton">
                            <g:message code="common.import"/>
                            <img id="pullPlanLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullPlanCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportPlanStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;" id="pullDiskOfferContainer">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullDiskOffer"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullStorage()" id="pullStorageButton">
                            <g:message code="common.import"/>
                            <img id="pullStorageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullStorageCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportStorageStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullTemplate"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullTemplate()" id="pullTemplateButton">
                            <g:message code="common.import"/>
                            <img id="pullTemplateLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullTemplateCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportTemplateStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullVPCOffering"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullVPCOffering()" id="pullVPCOfferingButton">
                            <g:message code="common.import"/>
                            <img id="pullVPCOfferingLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullVPCOfferingCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportVPCOfferingStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div>
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px; display: none">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullVPC"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.conformPullVPC()" id="pullVPCButton">
                        <g:message code="common.import"/>
                        <img id="pullVPCLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullVPCCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportVPCStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div> 
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullNetworkOffering"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullNetworkOffering()" id="pullNetworkOfferingButton">
                        <g:message code="common.import"/>
                        <img id="pullNetworkOfferingLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullNetworkOfferingCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportNetworkOfferingStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div> 
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullNetwork"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullNetwork()" id="pullNetworkButton">
                        <g:message code="common.import"/>
                        <img id="pullNetworkLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                        <img id="pullNetworkCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportNetworkStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
        </div> 
        <!--div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullImagesFromOpenstack"/></b>
                </div>
                    <div class="span2 pull-right" style="margin-top: 10px;">
                        <button type="button" data-dojo-type= "dijit.form.Button" id="pullImageButton" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullImage()" id="">
                                <g:message code="common.import"/>
                                <img id="pullImageLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                                <img id="pullImageCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                        </button>
                        <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportImageStatus()">
                            <g:message code="common.check"/>
                        </button>
                </div>
            </div> 
        </div>      
        <div class="row-fluid accquireip-form-boxcont" style="margin-top: 5px;">
            <div class="span12">
                <div class="span8" style="padding: 16px;">
                    <b><g:message code="common.pullVolumeTypesFromOpenstack"/></b>
                </div>
                <div class="span2 pull-right" style="margin-top: 10px;">
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.confirmPullVolumeType()" id="pullVolumeTypeButton">
                            <g:message code="common.import"/>
                            <img id="pullVolumeTypeLoaderImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/ajaxloader.gif"  height="15" width="15">
                            <img id="pullVolumeTypeCheckImage" style="display: none; margin: 0px;"  src="${resource(dir: 'images')}/checkyes.png"  height="15" width="15">
                    </button>
                    <button type="button" data-dojo-type= "dijit.form.Button" class="cancelbtn" onclick="ImportDataFromCloudstack.checkDataImportVolumeTypeStatus()">
                        <g:message code="common.check"/>
                    </button>
                </div>
            </div> 
         </div--> 
        
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullAllDataConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullAllDataMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.pullAllDataMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullAllData()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelpullAllData()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullDomainConform" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullDomainMessage' /></p></div>
                <!--<div class="span12"><p class="alert alert-info"><g:message code='common.pullDomainMessageNote' /></p></div>-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullDomain()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullDomain()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullAccountConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullAccountMessage' /></p></div>
                <!--<div class="span12"><p class="alert alert-info"><g:message code='common.pullAccountMessageNote' /></p></div>-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullAccount()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullAccount()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullUserConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullUserMessage' /></p></div>
                <!--<div class="span12"><p class="alert alert-info"><g:message code='common.pullUserMessageNote' /></p></div>-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullUser()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullUser()"><g:message code="common.cancel"/></button>
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
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullPlan()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullPlan()"><g:message code="common.cancel"/></button>
        </div>
    </div>
         
    <div data-dojo-type="dijit.Dialog" id="pullStorageConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullStorageMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.pullStorageMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullStorage()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullStorage()"><g:message code="common.cancel"/></button>
        </div>
    </div> 
    <div data-dojo-type="dijit.Dialog" id="pullTemplateConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullTemplateMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.pullTemplateMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullTemplate()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullTemplate()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullVPCOfferingConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullVPCOfferingMessage' /></p></div>
                <!--div class="span12"><p class="alert alert-info"><g:message code='common.pullVPCOfferingMessageNote' /></p></div-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullVPCOffering()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullVPCOffering()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullNetworkOfferingConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullNetworkOfferingMessage' /></p></div>
                <!--div class="span12"><p class="alert alert-info"><g:message code='common.pullNetworkOfferingMessageNote' /></p></div-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullNetworkOffering()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullNetworkOffering()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullNetworkConfirm" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullNetworkMessage' /></p></div>
                <!--div class="span12"><p class="alert alert-info"><g:message code='common.pullNetworkMessageNote' /></p></div-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullNetwork()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullNetwork()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullImageConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.openstack.pullImageMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullImageMessageNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullImages()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullImages()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullVolumeTypeConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.openstack.pullVolumeTypeMessage' /></p></div>
                <div class="span12"><p class="alert alert-info"><g:message code='common.openstack.pullVolumeTypeNote' /></p></div>
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullVolumeType()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullVolumeType()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullZoneConfirm" class="span4" style="display:none;">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullZonesMessage' /></p></div>
                <!--div class="span12"><p class="alert alert-info"><g:message code='common.pullZoneNote' /></p></div-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullAllZone()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelZonePull()"><g:message code="common.cancel"/></button>
        </div>
    </div>
    <div data-dojo-type="dijit.Dialog" id="pullVPCConform" class="span4">
        <div class="row-fluid">
            <!--<div class="span2"><img src='images/popup-icons/ip_icon.png'/></div>-->
            <div class="span10">
                <div class="span12"><p><g:message code='common.pullVPCMessage' /></p></div>
                <!--<div class="span12"><p class="alert alert-info"><g:message code='common.pullVPCMessageNote' /></p></div>-->
            </div>                                    
        </div>
        <div class="row-fluid">
            <button  type="button" class="primarybtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.pullVPC()"><g:message code="common.ok"/></button>
            <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="ImportDataFromCloudstack.cancelPullVPC()"><g:message code="common.cancel"/></button>
        </div>
    </div>
</div>