<!--<div class="span11 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard">Back to Dashboard</a></li>
    <li>/</li>
    <li>Ip Management</li>
  </ul>
</div>-->
<div class="alert alert-danger errorMessage" id="errorInfo">
  <span>
    The connection with the server was terminated
  </span>
</div>
<div id="pad-wrapper">
<div class="table-wrapper products-table section">
    <div class="row-fluid head">
        <div class="span12">
            <h4>IP Management</h4>
        </div>
    </div>
    <div class="row-fluid filter-block">
        <div class="pull-right">
          <div class="ui-select" id="dashboardIpZoneList">
<!--                <select>
                  <option>Filter users</option>
                  <option>Signed last 30 days</option>
                  <option>Active users</option>
                </select>-->
            </div>
          <div class="ui-select" id="dashboardIpPodList">              
          </div>
            <input type="text" class="search">
            <a class="btn-flat success new-product" href="#/admin/ipManagement/add">+ Add IP</a>
        </div>
    </div>

    <div class="row-fluid">
      <div id="adminIpList">  
      </div>
    </div>
</div>
</div>
               

                
              

              
    

<!--<div class="row-fluid">
  <div class="span8">
    <form id="dashboardIpManagementForm" data-dojo-type="dijit.form.Form"
          class="form-horizontal">
      <div class="row-fluid">
        <div class="control-group span5">
          <input type="hidden" id="dashboardCurrentIpListItem">
          <label for="dashboardIpZone" class="control-label">
            Zone: 
          </label>
          <div class="controls">
            <div id="dashboardIpZoneList"></div>
          </div>
        </div>
        <div class="control-group span5">
          <label for="dashboardIpPod"  class="control-label">
            Pod:
          </label>
          <div class="controls">
            <div id="dashboardIpPodList"></div>
          </div>
        </div> 
      </div>
      </form>
    <div class="fluid">
      <form class="form-horizontal" id="dashboardIpManagementPageForm" data-dojo-type="dijit.form.Form">
        <div class="row-fluid" id="dashboardIpManagementPage"> 
        <div class="control-group span8"> 
          <label for="dashboardIpName" class="control-label">
            <span class="require">*</span>
            IP Block Name:
          </label>
          <div class="controls">
            <input type="text" 
                   data-dojo-type="dijit.form.ValidationTextBox" 
                   data-dojo-props="invalidMessage: 'invalid IP Block Name',
                   required: 'required', placeHolder: 'Enter IP Block Name', 
                   regExp: '[a-zA-Z0-9]{4,20}', propercase: true, 
                   promptMessage:'Enter IP Block Name'" 
                   name="dashboardIpName" id="dashboardIpName">  
          </div>
        </div> 
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="dashboardStartIp" class="control-label">
              <span class="require">*</span>
              Start Ip:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid Start Ip',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'Start Ip', promptMessage:'Start Ip'"  
                     id="dashboardStartIp" onblur="DashboardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="dashboardEndIp" class="control-label">
              <span class="require">*</span>
              End Ip:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid End Ip',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'End Ip', 
                     promptMessage:'End Ip'" id="dashboardEndIp" onblur="DashboardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div>
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="dashboardIpNetmask" class="control-label">
              <span class="require">*</span>
              Netmask
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid Netmask',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'Netmask', 
                     promptMessage:'Netmask'" id="dashboardIpNetmask" onblur="DashboardIpManagement.validateNetmask(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="dashboardIpGateway" class="control-label">
              <span class="require">*</span>
              Gateway:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="required:'true', invalidMessage:'Invalid Gateway',
                     regExp: dojox.validate.regexp.ipAddress, placeHolder: 'Gateway',  promptMessage:'Gateway'"  
                     id="dashboardIpGateway" onblur="DashboardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="dashboardIpDNS1" class="control-label">
              DNS1
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage:'Invalid DNS1', regExp: dojox.validate.regexp.ipAddress,
                     placeHolder: 'DNS1', promptMessage:'DNS1'" id="dashboardIpDNS1" onblur="DashboardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div>
        <div class="row-fluid">
          <div class="control-group span8"> 
            <label for="dashboardIpDNS2" class="control-label">
              DNS2:
            </label>
            <div class="controls">
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props=" invalidMessage:'Invalid DNS2', 
                     regExp: dojox.validate.regexp.ipAddress,placeHolder: 'DNS2',
                     promptMessage:'DNS2'" id="dashboardIpDNS2" onblur="DashboardIpManagement.ipValidate(this.value, this.id)">  
            </div>
          </div> 
        </div> 
        <div class="row-fluid">
          <button type="button" data-dojo-type= "dijit.form.Button" 
                  onclick="DashboardIpManagement.add()" id="dashboardIpAddButton">
            OK
          </button>
          <button id="" data-dojo-type="dijit.form.Button"
                  onclick="DashboardIpManagement.cancel()">
            Cancel
          </button>
          <button onclick="DashboardIpManagement.update()"
                  data-dojo-type="dijit.form.Button"
                  id="dashboardIpUpdateButton" style="display: none">
            Apply
          </button>
        </div>
      </div>   
    </form>
  </div>
</div>
  <div class="span4" id="dashboardIpListCollection">
    <div id="dashboardIpListItem"></div>
  </div>
</div>
  