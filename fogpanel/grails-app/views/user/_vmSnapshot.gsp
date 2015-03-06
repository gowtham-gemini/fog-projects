<div class="row-fluid">
    <div class="span12 breadcrumbs">
        <ul>
          <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
          <li>/</li>
          <li><a href="#/user/cloud/"><g:message code="menu.admin.cloud"/></a></li>
          <li>/<li>
          <li><g:message code="menu.user.cloud.vmSnapShot"/></li>
        </ul>
    </div>
</div>
<div class="row-fluid">   
    <ul class="nav nav-tabs span12">
        <li>
          <a href="#/user/cloud/snapShot"><g:message code="menu.user.cloud.snapShot"/></a>
        </li>
        <li class="active">
          <a href="#/user/cloud/vmSnapShot"><g:message code="menu.user.cloud.vmSnapShot"/></a>
        </li> 
    </ul>
</div>
<div class="row-fluid" style="display: none">
  <div id="main-stats">
    <div class="row-fluid stats-row">
        <div class="span3 stat">
            <div class="data">
              <span class="number" id="">0</span>
              <g:message code="stat.totalSnapshot"/>
            </div>

        </div>
        <div class="span3 stat">
            <div class="data">
              <span class="number" id="">0</span>
                <g:message code="stat.snapshot"/>
            </div>

        </div>
        <div class="span3 stat">
            <div class="data">
              <span class="number" id="">0</span>
              <g:message code="stat.autoSnapshot"/>
            </div>            
        </div>
        <div class="span3 stat last">
            <div class="data">
              <span class="number" id="">0</span>
              <g:message code="stat.jobs"/>
            </div>   
        </div>
    </div>
  </div>
</div>
<div class="table-wrapper products-table">
    <div class="row-fluid filter-block">
        <div class="pull-right" id="createVMSnapshotDiv" style="display: none;">
            <a onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a><g:render template="rateCard" />
            <button data-dojo-type="dijit.form.Button" id="" onclick="VMSnapshotInfo.showCreateVMSnapshotDialog()" class="defaultbtn" >+<g:message code="user.snapshot.createVMSnapshot"/></button>
        </div>
    </div>
    <div class="row-fluid" style="text-align: right;">          
        <span>              
            <g:message code="common.warning"/>:          
        </span>                
            <p class="require" style="float: right;"><g:message code="common.vmSnapshotWarning"/></p>                            
    </div>    
    <div class="row-fluid">
      <form data-dojo-type="dijit.form.Form" id="" class="form-horizontal">
        <div class="row-fluid">
        <div id="vmSnapShotList"></div>
        <div class="alert alert-info hide" id="noVMSnapshotDiv" style="display: none">
            <i class="icon-exclamation-sign"></i> 
            <g:message code="user.vmSnapshot.noVMSnapshot"/>
        </div>
        </div>
      </form>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" class="full_loader" id="createVMSnapshotLoader" class="span6">
    <div class="row-fluid">
        <img src="images/vmload.gif" class="offset4"/>        
    </div>
    <div class="row-fluid">
        <p class="message span12"><g:message code="common.processing.donotReload"/></p>
    </div>
</div>

<div data-dojo-type="dijit.Dialog" id="addVMSnapshotDialog" title="<g:message code="user.vmSnapshot.addTitle"/>"  class="customDialgue">
    <div class="span5 dijitDialogueBackground">
        <div class="row-fluid" style="display: none">
              <div class="value_dollar pull-right"><g:message code="default.valueIn"/><span id="VMSnapshotCurrencyValue"></span></div>
        </div>
        <div class="row-fluid">
            <div class="span2"><img src='images/popup-icons/vm_snapshot_icon.png'/></div>
            <div class="span10">
                <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="addVMSnapshotFormPage">  
                    <div id="addVMSnapshotForm">
                        <div class="control-group"> 
                            <label for="vmSnapshotName" class="control-label">      
                                <g:message code="common.name"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                            <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
                                   required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
                                   regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="Name" id="VMSnapshotName">  
                            </div>
                        </div>
                        <div class="control-group"> 
                            <label for="vmSnapshotDescription" class="control-label">      
                                <g:message code="common.description"/>
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                            <input type="text" 
                                   data-dojo-type="dijit.form.ValidationTextBox" 
                                   data-dojo-props="invalidMessage: '<g:message code="common.description.invalid"/>',
                                   required: 'required', placeHolder: '<g:message code="common.description.prompt"/>', 
                                   regExp: '[a-zA-Z.0-9- ]{4,50}', propercase: true" 
                                   name="Name" id="vmSnapshotDescription">  
                            </div>
                        </div>
                        <div class="control-group"> 
                            <label for="snapshotMemory" class="control-label">      
                              <g:message code="common.snapshotMemory"/>
                            </label>
                            <div class="controls">
                                <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
                                           data-dojo-props="checked: false" id="snapshotMemory"> 
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="VMsnapshotInstanceList" class="control-label"><g:message code="common.instance"/> :
                                <span class="require">*</span>
                            </label>
                            <div class="controls">
                             <div id="" class="selectOption">
                                <div id="VMsnapshotInstanceList" ></div>  
                             </div>
                            </div>
                        </div>
                        <div class="control-group" id="VMSnapshotBillingTypeDiv"> 
                            <label for="" class="control-label"><g:message code="user.createVM.billingType.label"/>:</label>
                            <div class="controls">
                                <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="VMSnapshot-billingType" id="VMSnapshot-hourlyBilling" value="hourly" />
                              <label for="VMSnapshot-hourlyBilling"><g:message code="common.hourly"/></label> 
                              <input type="radio" data-dojo-type="dijit.form.RadioButton" name="VMSnapshot-billingType"  id="VMSnapshot-monthlyBilling" value="monthly" /> 
                              <label for="VMSnapshot-monthlyBilling" class=""><g:message code="common.monthly"/></label> 
                            </div>
                        </div>
                        <div class="control-group field-box">
                            <label class="control-label">              
                                <g:message code="common.warning"/>:          
                            </label>
                            <div class="controls">
                                <span class="require"><g:message code="common.vmSnapshotWarning"/></span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>      
        </div>
        <div>
            <div class="control-group span7"> 
                <button id="addVMSnapshotButton" class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.createVMSnapshot()"><g:message code="common.ok"/></button>
                <button id="cancelVMSshotButon" class="cancelbtn" type="button" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.closeCreateVMSnapshotDialog()"><g:message code="common.cancel"/></button>
            </div>
            <div class="span5"></div>
        </div>
    </div>
</div>
<input type="hidden" id="currentVMSnap">
<div data-dojo-type="dijit.Dialog" id="deleteVMSnap" title="<g:message code="user.snapshot.deleteVMSnapshot"/>" class="span4">
      <p><g:message code="user.snapshot.deleteVMSnapshot.confirm"/></p>
      <div class="row-fluid offset1">
        <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.deleteVMSnapshot()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.closeDeleteVMSnapshot()"><g:message code="common.cancel"/></button>
    </div>
</div>
<div data-dojo-type="dijit.Dialog" id="revertVMSnap" title="<g:message code="user.snapshot.revertVMSnapshot"/>" class="span4">
      <p><g:message code="user.snapshot.revertVMSnapshot.confirm"/></p>
      <div class="row-fluid offset1">
        <button  type="button" class="defaultbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.revertVMSnapshot()"><g:message code="common.ok"/></button>
        <button  type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="VMSnapshotInfo.closeRevertVMSnapshotDialog()"><g:message code="common.cancel"/></button>
    </div>
</div>
