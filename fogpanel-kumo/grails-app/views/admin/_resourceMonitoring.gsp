<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/admin/infrastructure/cloud"><g:message code="menu.admin.cloud"/></a></li>
    <li>/</li>
    <li><g:message code="common.resourceMonitoring"/></li>
  </ul>
</div>
</div> 

<!--<div id="pad-wrapper">-->
<div class="table-wrapper products-table">
    <div class="row-fluid">
      <div id="zoneListAccordion" class="span4"></div>
      <div id="" class="span8">
        <div class="row-fluid">
          <h3 id="contentAccordion"></h3>
        </div>
        <div class="row-fluid" id="resourceFormDiv" style="display: none;">
          <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="resourceForm">
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"> <g:message code="common.memoryName"/>:</div>
                <div class="span4 resource_items_allocation" id="memeoryTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="memeoryBar" id="memeoryBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"> <g:message code="common.cpu"/>:</div>
                <div class="span4 resource_items_allocation" id="cpuTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="cpuBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"> <g:message code="menu.admin.services.storage"/>:</div>
                <div class="span4 resource_items_allocation" id="storageTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="storageBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.storage.primaryStorageAllocated"/>:</div>
                <div class="span4 resource_items_allocation" id="pStorageTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="pStorageBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.storage.publicIPAddress"/>:</div>
                <div class="span4 resource_items_allocation" id="publicIpTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="publicIpBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.storage.managementIPAddresses"/>:</div>
                <div class="span4 resource_items_allocation" id="managementIpTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="managementIpBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.storage.secondaryStorage"/>:</div>
                <div class="span4 resource_items_allocation" id="secodaryTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="secodaryBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.VLAN"/>:</div>
                <div class="span4 resource_items_allocation" id="VLANTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-props="places:2" data-dojo-id="cpuBar" id="VLANBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.sharedNetworkIPs"/>:</div>
                <div class="span4 resource_items_allocation" id="directPublicIpTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="directPublicIpBar"></div>
                </div>
              </div>
            </div>
            <div class="row-fluid">
              <div class="span11 resource_items_cont">
                <div class="span3 resource_items_memory"><g:message code="common.storage.localStorage"/>:</div>
                <div class="span4 resource_items_allocation" id="localStorageTotal"></div>
                <div class="span4 resource_items_progress"> 
                  <div data-dojo-type="dijit/ProgressBar" data-dojo-props="places:2" data-dojo-id="cpuBar" id="localStorageBar"></div>
                </div>
              </div>
            </div>
          </form>
        </div>
         <div class="row-fluid" id="resourceHostFormDiv" style="display: none;">
           <form class="new_user_form inline-input" data-dojo-type="dijit.form.Form" id="resourceHostForm">
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="common.totalCPU"/>:</div>
                  <div class="span4 resource_items_allocation" id="totalCpu"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="common.cpuUlized"/>:</div>
                  <div class="span4 resource_items_allocation" id="cpuUtilized"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="common.CPUAllocatedVMs"/>:</div>
                  <div class="span4 resource_items_allocation" id="cpuAllocated"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="common.memoryTotal"/>:</div>
                  <div class="span4 resource_items_allocation" id="hostMemoryTotal"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="common.memoryAllocated"/>:</div>
                  <div class="span4 resource_items_allocation" id="hostAllocatedTotal"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="common.memoryUsed"/>:</div>
                  <div class="span4 resource_items_allocation" id="hostUsedTotal"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="user.vm.networkRead"/>:</div>
                  <div class="span4 resource_items_allocation" id="networkRead"></div>
                </div>
              </div>
              <div class="row-fluid">
                <div class="span11 resource_items_cont">
                  <div class="span3 resource_items_memory"><g:message code="user.vm.networkWrite"/>:</div>
                  <div class="span4 resource_items_allocation" id="networkWrite"></div>
                </div>
              </div>
           
           </form>
        </div>
      </div>
    </div>
</div>
<!--</div>-->              
