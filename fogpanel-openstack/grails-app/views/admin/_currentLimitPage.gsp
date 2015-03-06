<div class="row-fluid">
    <form id="updateResourceLimitForm" data-dojo-type="dijit.form.Form" class="form-horizontal span8">                    
        <div id="updateResourceLimitPage">
            <div class="span12 field-box control-group" style="margin-left: 20px">
                <label for="retailMetaDataLimit" class="control-label settings_lable">                          
                    <g:message code="common.retailMetaDataLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Metadata Limit', promptMessage:'Enter Metadata Limit' " name="retailMetaDataLimit" id="retailMetaDataLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailCpuLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailCpuLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter CPU Limit', promptMessage:'Enter CPU Limit' " name="retailCpuLimit" id="retailCpuLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailInstanceLimit" class="control-label settings_lable">                          
                    <g:message code="common.retailInstanceLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Instance Limit', promptMessage:'Enter Instance Limit' " name="retailInstanceLimit" id="retailInstanceLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailInfectedFileLimit" class="control-label settings_lable">                          
                    <g:message code="common.retailInfectedFileLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Infected Files Limit', promptMessage:'Enter Infected Files Limit' " name="retailInfectedFileLimit" id="retailInfectedFileLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailInfectedFileContentLimit" class="control-label settings_lable">                          
                    <g:message code="common.retailInfectedFileContentLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Injected File Content Bytes Limit', promptMessage:'Enter Injected File Content Bytes Limit' " name="retailInfectedFileContentLimit" id="retailInfectedFileContentLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailStorageLimit" class="control-label settings_lable">                         
                    <g:message code="common.retailStorageLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Storage Limit', promptMessage:'Enter Storage Limit' " name="retailStorageLimit" id="retailStorageLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailSnapshotLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailSnapshotLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Snapshot Limit', promptMessage:'Enter Snapshot Limit' " name="snapshotLimit" id="retailSnapshotLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailTotalSizeOfVolumeAndSnapshot" class="control-label settings_lable">                        
                    <g:message code="common.retailTotalSizeOfVolumeAndSnapshot"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Total Size of Volumes and Snapshots Limit', promptMessage:'Enter Total Size of Volumes and Snapshots Limit' " name="retailTotalSizeOfVolumeAndSnapshot" id="retailTotalSizeOfVolumeAndSnapshot">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailMemoryLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailMemoryLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter RAM Limit', promptMessage:'Enter RAM Limit' " name="retailMemoryLimit" id="retailMemoryLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailSecurityGroupLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailSecurityGroupLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Security Groups Limit', promptMessage:'Enter Security Groups Limit' " name="retailSecurityGroupLimit" id="retailSecurityGroupLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailSecurityGroupRuleLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailSecurityGroupRuleLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Security Group Rules Limit', promptMessage:'Enter Security Group Rules Limit' " name="retailSecurityGroupRuleLimit" id="retailSecurityGroupRuleLimit">
                </div>
            </div>

            <div class="span12 field-box control-group">
                <label for="retailPublicIPLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailPublicIPLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Floating IP Limit', promptMessage:'Enter Floating IP Limit' " name="retailPublicIPLimit" id="retailPublicIPLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailNetworkLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailNetworkLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Network Limit', promptMessage:'Enter Network Limit' " name="retailNetworkLimit" id="retailNetworkLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="retailPortLimit" class="control-label settings_lable">                        
                    <g:message code="common.retailPortLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Port Limit', promptMessage:'Enter Port Limit' " name="retailPortLimit" id="retailPortLimit">
                </div>
            </div>


            <div class="span12 field-box control-group">
                <label for="routerLimit" class="control-label settings_lable">                        
                    <g:message code="common.routerLimit"/> 
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Router Limit', promptMessage:'Enter Router Limit' " name="routerLimit" id="routerLimit">
                </div>
            </div>
            <div class="span12 field-box control-group">
                <label for="subnetLimit" class="control-label settings_lable">                        
                    <g:message code="common.subnetLimits"/>
                    <span class="require">*</span>
                </label>
                <div class="controls">
                    <input type="text" data-dojo-type="dijit.form.NumberTextBox" data-dojo-props="invalidMessage: 'invalid No.', required:'true', constraints:{min: 0}, placeHolder: 'Enter Subnet Limit', promptMessage:'Enter Subnet Limit' " name="subnetLimit" id="subnetLimit">
                </div>
            </div>
        </div>   
    </form>
</div>
<div class="row-fluid  span8" id="resourceLimitBtnDiv">
    <div class="pull-right">
        <button data-dojo-type="dijit.form.Button" onclick="ResourceLimitForUser.update();" class="primarybtn">
            <g:message code="common.update"/>
        </button>
        <!--button data-dojo-type="dijit.form.Button" onclick="ResourceLimitForUser.closeResourceLimitDialog();" class="cancelbtn">
            <g:message code="common.cancel"/>
        </button-->
    </div>
</div>
<div class="span4 pull-right" id="resourceLimitLoader" style="display: none"> 
    <img src='images/preloader_circle.gif' alt='Loading' height='16' width='23'/>
</div>