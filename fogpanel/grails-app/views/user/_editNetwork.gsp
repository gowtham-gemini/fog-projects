<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
            <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
            <li>/</li>
            <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
            <li>/</li>
            <li><a href="#/user/network/"><g:message code="menu.user.services.network"/></a></li>  
            <li>/</li>
            <li><a id="currentNetworkName"></a></li>
        </ul>
    </div>
</div>
<div class="row-fluid header"></div>
<div class="new-user createVm">
    <div class="row-fluid form-wrapper">
        <div class="span12" id="userEditNetworkPage">     
            <div class="span3 createvm-banner">
                <img src="${resource(dir: 'images')}/cloud_comput_icon.png" height="151" width="238">
            </div>
            <div class="span6 createvm-form with-sidebar">
                <div class="container">
                    <form id="userEditNetworkZoneForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">	
                        <input id="networkId" type="hidden">
                        <div class="row-fluid">
                            <div class="span12">
                                <div class="row-fluid">
                                    <div class="control-group span8 horizontalcontent">
                                        <label for="networkName" class="control-label">          
                                            <g:message code="common.name"/>: 
                                            <span class="require">*</span>
                                        </label>
                                            <div class="controls elements">
                                                <input type="text" data-dojo-type="dijit.form.ValidationTextBox"
                                                id="editNetworkName" data-dojo-props="required: 'true',
                                                invalidMessage: '<g:message code="common.invalidMessage.name"/>', placeHolder: '<g:message code="common.name"/>',regExp:'[a-zA-Z0-9- ]{4,200}',
                                                promptMessage:'<g:message code="common.name.placeHolder"/>'">
                                            </div>
                                    </div>
                                </div>
                            <div class="row-fluid">
                        <div class="control-group span8 horizontalcontent">
                            <label for="networkDescription"  class="control-label">         
                             <g:message code="common.desc"/> :
                               <span class="require">*</span>
                            </label>
                            <div class="controls elements">
                              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                                        id="editNetworkDescription" data-dojo-props="required: 'true',
                                     invalidMessage: '<g:message code="common.description.invalid"/>', placeHolder: '<g:message code="common.description"/>',regExp:'[a-zA-Z0-9- .,]{4,200}',
                                     promptMessage:'<g:message code="common.description.prompt"/>'"/>

                            </div>
                       </div> 
                    </div>
               </div>
            </div>
        </form>
        <div id="userEditNetworkContent">
            <form id="userEditNetworkContentForm" data-dojo-type="dijit.form.Form" accept-charset="" class="form-horizontal">
                <div class="row-fluid">
                    <div class="control-group span12 horizontalcontent">
                        <label for="networkZoneList" class="control-label">
                          <g:message code="user.createVM.zone.label"/>: 
                        </label>
                        <span id="networkZoneName" ></span>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span12 horizontalcontent">
                        <label for="networkZoneList" class="control-label">
                          <g:message code="common.networkoffering"/>: 
                        </label>
                        <span id="networkOfferName" ></span>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="control-group span6">
                        <label for="" class="control-label">
                          <g:message code="common.gateway"/>: 
                        </label>
                        <span id="networkGateway" ></span>
                    </div>
                    <div class="control-group span6">
                            <label for="" class="control-label">
                              <g:message code="common.cidr"/>: 
                            </label>
                            <span id="networkCidr" ></span>
                    </div>
                </div>
                <div class="row-fluid">
                <div class="span8">
                </div>
                <div class="span4" id="editNetworkButtonDiv">
                    <button   id=""  data-dojo-type="dijit.form.Button" onclick="EditNetwork.edit()" class="okbtn">
                      <g:message code="common.update"/>
                    </button>
                    <button   id=""  data-dojo-type="dijit.form.Button" onclick="EditNetwork.cancel()" class="cancelbtn">
                      <g:message code="common.cancel"/>
                    </button>
                </div>
                <div class="span2" id="editNetworkLoader" style="display: none;">
                    <img src="${resource(dir: 'images')}/preloader_circle.gif" 
                     alt="<g:message code="common.reset" />" height="20" width="20">
                </div>
              </div>
            </form>
        </div>
            </div>
        </div>
        <div class="span3"></div>
    </div>
</div>
</div>
