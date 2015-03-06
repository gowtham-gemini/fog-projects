<div class="row-fluid">
  <div class="span12 breadcrumbs">
  <ul>
    <li><a href="#/user/home"><i class="icon-home"></i></a></li> 
    <li>/</li>
    <li><a href="#/user/cloud/"><g:message code="menu.user.cloud"/></a></li>
    <li>/<li>
    <li><g:message code="menu.user.cloud.snapShot"/></li>
  </ul>
</div>
</div>
<div class="row-fluid">   
  <ul class="nav nav-tabs span12">
    <li  class="active">
      <a href="#/user/cloud/snapShot"><g:message code="menu.user.cloud.snapShot"/></a>
    </li>
    <li id="vmSnapshotLink">
      <a href="#/user/cloud/vmSnapShot"><g:message code="menu.user.cloud.vmSnapShot"/></a>
    </li> 
    </ul>
</div>

<!--Cloud stack bug,so this code has hidden temporary,  coming in next version-->

<div class="row-fluid" style="display: none">
  <div id="main-stats">
    <div class="row-fluid stats-row">
<div class="span3 stat">
        <div class="data">
          <span class="number" id="userTotalSnap">0</span>
         <g:message code="stat.totalSnapshot"/>
        </div>

      </div>
      <div class="span3 stat">
        <div class="data">
          <span class="number" id="userManualSnap">0</span>
             <g:message code="stat.snapshots"/>
        </div>

      </div>
      <div class="span3 stat">
        <div class="data">
          <span class="number" id="userAutoSnap">0</span>
          Auto Snapshot
        </div>            
      </div>
      <div class="span3 stat last">
        <div class="data">
          <span class="number" id="userSnapshotJobs">0</span>
          Jobs
        </div>   
      </div>
    </div>
  </div>
</div>
<!--End Of the Command-->
<div>
  
  <div class="table-wrapper products-table">
    <div class="row-fluid filter-block">
    <div class="alert alert-danger errorMessage" id="snapshotLimitReached" style="display: none">
        <i class="icon-exclamation-sign"></i> 
       <g:message code="common.user.reachedLimit"/>
    </div>
      <div class="pull-right" id="snapshotButton">
        <a onclick="RateCardInfo.showRateCardDialogue();RateCardInfo.populateValues();"><g:message code='common.rateCard' /></a><g:render template="rateCard" />
        <button data-dojo-type="dijit.form.Button" id="manualSnapshotAction" onclick="SnapShotInfo.showAddSnapShot(this.id)" class="defaultbtn overflowLabel" ><g:message code="menu.user.cloud.snapshot.createSnapshot"/></button>
<!--        <button data-dojo-type="dijit.form.Button" class="defaultbtn" onclick="SnapShotInfo.showAddAutoSnapShot(this.id)"
                id="autoSnapshotAction" title="Auto Snapshot">
          + Auto Snapshot
        </button>-->
<!--        <button data-dojo-type="dijit.form.Button" onclick="SnapShotInfo.showJob()" class="infobtn" title="Snapshot Jobs">
         Snapshot Jobs
        </button>-->
      </div>
    </div>
    <div class="row-fluid">
      <form data-dojo-type="dijit.form.Form" id="snapShotFrom" class="form-horizontal">
        <div class="row-fluid">
          <div id="snapShotList"></div>
          <div class="alert alert-info hide" id="noSnapMessageBox" style="display: none">
              <i class="icon-exclamation-sign"></i> 
              <g:message code="common.user.noSnapshot"/>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>

<div data-dojo-type="dijit.Dialog"  id="createSnapShotPage" title="<g:message code="common.disk.snapshotTile"/>" class="customDialgue span8">   
    <div class="row-fluid row_spacing">
        <div class="span2">
            <img src='images/popup-icons/disk_snapshot_icon.png'/>
            <input type="hidden" id="snapShotButtonId">
            <input type="hidden" id="snapShotStatus"> 
        </div>
        <div class="span10">       
            <!--<div class="span12">-->
                <div id="storagPageInfo" class="custom_text span12"><g:message code="user.storage.snapshotInfo"/></div>     
            <!--</div>-->                   
        </div>
    </div>
    <div class="row-fluid">                 
        <div class="smallLoader span12" id="volumeLoader" style="display: none">
            <div class="loader-cont">                       
                <div><g:message code="common.message.loading"/></div>            
            </div>
        </div>                                    
        <div id="snapShotStorageList" class="span12"><g:message code="common.message.loading"/></div>                                        
        <div  class="span12">
            <div class="alert alert-info hide" id="noSnapStorageMessageBox" style="display: none">
                <i class="icon-exclamation-sign"></i> 
                <g:message code="user.storage.noStorage"/>
            </div>
        </div>                          
    </div>                 
    <div class="row-fluid">                            
        <div class="span6"></div>
        <div class="span6" id="zoneMisceCostInfo"></div>                
    </div>
</div>             
<div data-dojo-type="dijit.Dialog" id="snapshotJobListPage"  title="<g:message code="common.user.job.title"/>" class="span8">  
    <div class="row-fluid head">
        <div class="span12">
            <p class="customHeader" id="snapshotJobListPageContent"><g:message code="user.snapshot.job.info"/></p>
        </div>
    </div> 
        <div id=""> 
            <div class="table-wrapper products-table"> 
                <div class="row-fluid">
                    <div id="snapshotAutoJobList" class="span12"></div>        
                </div>
                <div  class="row-fluid">
                    <div class="alert alert-info hide" id="noAutoSnapMessageBox" style="display: none">
                        <i class="icon-exclamation-sign"></i> 
                        <g:message code="user.storage.noStorage"/>
                    </div>
                </div>                
            </div>
        </div>   
</div>
<div data-dojo-type="dijit.Dialog" title="<g:message code='user.snapshot.recurring' />"  id="ConformationAutoSnapShotPage" class="span8" title="<g:message code='user.snapshot.recurring' />">
  <p class="customHeader"><g:message code="user.snapshot.job.info"/></p>
  <input type="hidden" id="currentStorageId">
  <input type="hidden" id="currentAutoSnapId">
  <div class="new-user">
    <div class="row-fluid form-wrapper">
      <div class="span12">
        <div class="container">
          <form class="form-horizontal" data-dojo-type="dijit.form.Form" id="autoSnapshotForm">
            <div class="span12 field-box">
              <label class="control-label" for="inputEmail"><g:message code="common.name"/>:</label>
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" id="autoSnapshotName"
                     data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>', required: 'true',
                     placeHolder: '<g:message code="menu.user.storage.addStorage.placeHolder.name"/>', regExp: '[a-z0-9A-Z]{1,20}'">
            </div>
            <div class="span12 field-box">
              <label class="control-label" for="inputEmail"><g:message code="common.storage.name"/>:</label>
              <span id="autoSnapDiskName"></span>
            </div>
            
            <div class="span12 field-box">
              <label class="control-label" for="inputEmail"><g:message code="user.snapshot.schedule"/></label>
              <select data-dojo-type="dijit.form.Select" id="autoSchedule" 
                      onchange="SnapShotInfo.showShedule(this)">
                <option value="DAILY"><g:message code="menu.user.vm.createVM.daily"/></option>
                <option value="WEEKLY"><g:message code="menu.user.vm.createVM.weekly"/></option>
                <option value="MONTHLY"><g:message code="menu.user.vm.createVM.monthly"/></option>
              </select>
            </div>
            <div class="span12 field-box">
              <label class="control-label" for="inputEmail"><g:message code="common.time"/></label>
              <select data-dojo-type="dijit.form.Select" id="scheduleHr"
                      data-dojo-props="maxHeight: -1">
                <option value="01">01</option>
                <option value="02">02</option>
                <option value="03">03</option>
                <option value="04">04</option>
                <option value="05">05</option>
                <option value="06">06</option>
                <option value="07">07</option>
                <option value="08">08</option>
                <option value="09">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
              </select>
              <select data-dojo-type="dijit.form.Select" id="scheduleMin" 
                      data-dojo-props="maxHeight: -1">
                <option value="00">00</option>
                <option value="01">01</option>
                <option value="02">02</option>
                <option value="03">03</option>
                <option value="04">04</option>
                <option value="05">05</option>
                <option value="06">06</option>
                <option value="07">07</option>
                <option value="08">08</option>
                <option value="09">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
                <option value="32">32</option>
                <option value="33">33</option>
                <option value="34">34</option>
                <option value="35">35</option>
                <option value="36">36</option>
                <option value="37">37</option>
                <option value="38">38</option>
                <option value="39">39</option>
                <option value="40">40</option>
                <option value="41">41</option>
                <option value="42">42</option>
                <option value="43">43</option>
                <option value="44">44</option>
                <option value="45">45</option>
                <option value="46">46</option>
                <option value="47">47</option>
                <option value="48">48</option>
                <option value="49">49</option>
                <option value="50">50</option>
                <option value="51">51</option>
                <option value="52">52</option>
                <option value="53">53</option>
                <option value="54">54</option>
                <option value="55">55</option>
                <option value="56">56</option>
                <option value="57">57</option>
                <option value="58">58</option>
                <option value="59">59</option>
              </select>      
            </div>
            <div class="span12 field-box" id="weeklyList" style="display: none">
              <label class="control-label" for="inputEmail"><g:message code="common.dayOfWeek"/></label>
              <select class="valid" name="timezone" data-dojo-type="dijit.form.Select" 
                      id="snapshotDay">
    
                <option value="1"><g:message code="common.sunday"/></option>
                <option value="2"><g:message code="common.monday"/></option>
                <option value="3"><g:message code="common.tuesday"/></option>
                <option value="4"><g:message code="common.wednesday"/></option>
                <option value="5"><g:message code="common.thursday"/></option>
                <option value="6"><g:message code="common.friday"/></option>
                <option value="7"><g:message code="common.saturday"/></option>
              </select>
            </div>
            <div class="span12 field-box" id="monthlyList" style="display: none">
              <label class="control-label" for="inputEmail"><g:message code="common.dayOfMonths"/></label>
              <select class="valid" name="timezone" data-dojo-type="dijit.form.Select" 
                      data-dojo-props="maxHeight: -1" id="snapshotMonth">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>  
              </select>
            </div>
            <div class="span12 field-box">
              <label class="control-label" for="inputEmail">Time zone</label>
              <select class="valid" name="timezone" data-dojo-type="dijit.form.Select" 
                      data-dojo-props="maxHeight: -1" id="snapshotTimeZone">
                <option value="Etc/GMT+12">[UTC-12:00] GMT-12:00</option>
                <option value="Etc/GMT+11">[UTC-11:00] GMT-11:00</option>
                <option value="Pacific/Samoa">[UTC-11:00] Samoa Standard Time</option>
                <option value="Pacific/Honolulu">[UTC-10:00] Hawaii Standard Time</option>
                <option value="US/Alaska">[UTC-09:00] Alaska Standard Time</option>
                <option value="America/Los_Angeles">[UTC-08:00] Pacific Standard Time</option>
                <option value="Mexico/BajaNorte">[UTC-08:00] Baja California</option>
                <option value="US/Arizona">[UTC-07:00] Arizona</option>
                <option value="US/Mountain">[UTC-07:00] Mountain Standard Time</option>
                <option value="America/Chihuahua">[UTC-07:00] Chihuahua, La Paz</option>
                <option value="America/Chicago">[UTC-06:00] Central Standard Time</option>
                <option value="America/Costa_Rica">[UTC-06:00] Central America</option>
                <option value="America/Mexico_City">[UTC-06:00] Mexico City, Monterrey</option>
                <option value="Canada/Saskatchewan">[UTC-06:00] Saskatchewan</option>
                <option value="America/Bogota">[UTC-05:00] Bogota, Lima</option>
                <option value="America/New_York">[UTC-05:00] Eastern Standard Time</option>
                <option value="America/Caracas">[UTC-04:00] Venezuela Time</option>
                <option value="America/Asuncion">[UTC-04:00] Paraguay Time</option>
                <option value="America/Cuiaba">[UTC-04:00] Amazon Time</option>
                <option value="America/Halifax">[UTC-04:00] Atlantic Standard Time</option>
                <option value="America/La_Paz">[UTC-04:00] Bolivia Time</option>
                <option value="America/Santiago">[UTC-04:00] Chile Time</option>
                <option value="America/St_Johns">[UTC-03:30] Newfoundland Standard Time</option>
                <option value="America/Araguaina">[UTC-03:00] Brasilia Time</option>
                <option value="America/Argentina/Buenos_Aires">[UTC-03:00] Argentine Time</option>
                <option value="America/Cayenne">[UTC-03:00] French Guiana Time</option>
                <option value="America/Godthab">[UTC-03:00] Greenland Time</option>
                <option value="America/Montevideo">[UTC-03:00] Uruguay Time]</option>
                <option value="Etc/GMT+2">[UTC-02:00] GMT-02:00</option>
                <option value="Atlantic/Azores">[UTC-01:00] Azores Time</option>
                <option value="Atlantic/Cape_Verde">[UTC-01:00] Cape Verde Time</option>
                <option value="Africa/Casablanca">[UTC] Casablanca</option>
                <option value="Etc/UTC">[UTC] Coordinated Universal Time</option>
                <option value="Atlantic/Reykjavik">[UTC] Reykjavik</option>
                <option value="Europe/London">[UTC] Western European Time</option>
                <option value="CET">[UTC+01:00] Central European Time</option>
                <option value="Europe/Bucharest">[UTC+02:00] Eastern European Time</option>
                <option value="Africa/Johannesburg">[UTC+02:00] South Africa Standard Time</option>
                <option value="Asia/Beirut">[UTC+02:00] Beirut</option>
                <option value="Africa/Cairo">[UTC+02:00] Cairo</option>
                <option value="Asia/Jerusalem">[UTC+02:00] Israel Standard Time</option>
                <option value="Europe/Minsk">[UTC+02:00] Minsk</option>
                <option value="Europe/Moscow">[UTC+03:00] Moscow Standard Time</option>
                <option value="Africa/Nairobi">[UTC+03:00] Eastern African Time</option>
                <option value="Asia/Karachi">[UTC+05:00] Pakistan Time</option>
                <option value="Asia/Kolkata">[UTC+05:30] India Standard Time</option>
                <option value="Asia/Bangkok">[UTC+05:30] Indochina Time</option>
                <option value="Asia/Shanghai">[UTC+08:00] China Standard Time</option>
                <option value="Asia/Kuala_Lumpur">[UTC+08:00] Malaysia Time</option>
                <option value="Australia/Perth">[UTC+08:00] Western Standard Time (Australia)</option>
                <option value="Asia/Taipei">[UTC+08:00] Taiwan</option>
                <option value="Asia/Tokyo">[UTC+09:00] Japan Standard Time</option>
                <option value="Asia/Seoul">[UTC+09:00] Korea Standard Time</option>
                <option value="Australia/Adelaide">[UTC+09:30] Central Standard Time (South Australia)</option>
                <option value="Australia/Darwin">[UTC+09:30] Central Standard Time (Northern Territory)</option>
                <option value="Australia/Brisbane">[UTC+10:00] Eastern Standard Time (Queensland)</option>
                <option value="Australia/Canberra">[UTC+10:00] Eastern Standard Time (New South Wales)</option>
                <option value="Pacific/Guam">[UTC+10:00] Chamorro Standard Time</option>
                <option value="Pacific/Auckland">[UTC+12:00] New Zealand Standard Time</option>
                <option value="Etc/GMT+12">[UTC-12:00] GMT-12:00</option>
                <option value="Etc/GMT+11">[UTC-11:00] GMT-11:00</option>
                <option value="Pacific/Samoa">[UTC-11:00] Samoa Standard Time</option>
                <option value="Pacific/Honolulu">[UTC-10:00] Hawaii Standard Time</option>
                <option value="US/Alaska">[UTC-09:00] Alaska Standard Time</option>
                <option value="America/Los_Angeles">[UTC-08:00] Pacific Standard Time</option>
                <option value="Mexico/BajaNorte">[UTC-08:00] Baja California</option>
                <option value="US/Arizona">[UTC-07:00] Arizona</option>
                <option value="US/Mountain">[UTC-07:00] Mountain Standard Time</option>
                <option value="America/Chihuahua">[UTC-07:00] Chihuahua, La Paz</option>
                <option value="America/Chicago">[UTC-06:00] Central Standard Time</option>
                <option value="America/Costa_Rica">[UTC-06:00] Central America</option>
                <option value="America/Mexico_City">[UTC-06:00] Mexico City, Monterrey</option>
                <option value="Canada/Saskatchewan">[UTC-06:00] Saskatchewan</option>
                <option value="America/Bogota">[UTC-05:00] Bogota, Lima</option>
                <option value="America/New_York">[UTC-05:00] Eastern Standard Time</option>
                <option value="America/Caracas">[UTC-04:00] Venezuela Time</option>
                <option value="America/Asuncion">[UTC-04:00] Paraguay Time</option>
                <option value="America/Cuiaba">[UTC-04:00] Amazon Time</option>
                <option value="America/Halifax">[UTC-04:00] Atlantic Standard Time</option>
                <option value="America/La_Paz">[UTC-04:00] Bolivia Time</option>
                <option value="America/Santiago">[UTC-04:00] Chile Time</option>
                <option value="America/St_Johns">[UTC-03:30] Newfoundland Standard Time</option>
                <option value="America/Araguaina">[UTC-03:00] Brasilia Time</option>
                <option value="America/Argentina/Buenos_Aires">[UTC-03:00] Argentine Time</option>
                <option value="America/Cayenne">[UTC-03:00] French Guiana Time</option>
                <option value="America/Godthab">[UTC-03:00] Greenland Time</option>
                <option value="America/Montevideo">[UTC-03:00] Uruguay Time]</option>
                <option value="Etc/GMT+2">[UTC-02:00] GMT-02:00</option>
                <option value="Atlantic/Azores">[UTC-01:00] Azores Time</option>
                <option value="Atlantic/Cape_Verde">[UTC-01:00] Cape Verde Time</option>
                <option value="Africa/Casablanca">[UTC] Casablanca</option>
                <option value="Etc/UTC">[UTC] Coordinated Universal Time</option>
                <option value="Atlantic/Reykjavik">[UTC] Reykjavik</option>
                <option value="Europe/London">[UTC] Western European Time</option>
                <option value="CET">[UTC+01:00] Central European Time</option>
                <option value="Europe/Bucharest">[UTC+02:00] Eastern European Time</option>
                <option value="Africa/Johannesburg">[UTC+02:00] South Africa Standard Time</option>
                <option value="Asia/Beirut">[UTC+02:00] Beirut</option>
                <option value="Africa/Cairo">[UTC+02:00] Cairo</option>
                <option value="Asia/Jerusalem">[UTC+02:00] Israel Standard Time</option>
                <option value="Europe/Minsk">[UTC+02:00] Minsk</option>
                <option value="Europe/Moscow">[UTC+03:00] Moscow Standard Time</option>
                <option value="Africa/Nairobi">[UTC+03:00] Eastern African Time</option>
                <option value="Asia/Karachi">[UTC+05:00] Pakistan Time</option>
                <option value="Asia/Kolkata">[UTC+05:30] India Standard Time</option>
                <option value="Asia/Bangkok">[UTC+05:30] Indochina Time</option>
                <option value="Asia/Shanghai">[UTC+08:00] China Standard Time</option>
                <option value="Asia/Kuala_Lumpur">[UTC+08:00] Malaysia Time</option>
                <option value="Australia/Perth">[UTC+08:00] Western Standard Time (Australia)</option>
                <option value="Asia/Taipei">[UTC+08:00] Taiwan</option>
                <option value="Asia/Tokyo">[UTC+09:00] Japan Standard Time</option>
                <option value="Asia/Seoul">[UTC+09:00] Korea Standard Time</option>
                <option value="Australia/Adelaide">[UTC+09:30] Central Standard Time (South Australia)</option>
                <option value="Australia/Darwin">[UTC+09:30] Central Standard Time (Northern Territory)</option>
                <option value="Australia/Brisbane">[UTC+10:00] Eastern Standard Time (Queensland)</option>
                <option value="Australia/Canberra">[UTC+10:00] Eastern Standard Time (New South Wales)</option>
                <option value="Pacific/Guam">[UTC+10:00] Chamorro Standard Time</option>
                <option value="Pacific/Auckland">[UTC+12:00] New Zealand Standard Time</option>
              </select>
            </div>
            <div class="span12 field-box">
              <label class="control-label" for="snapshotKeep"><g:message code="common.numberOfCopies"/></label>
              <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                     data-dojo-props="invalidMessage: '<g:message code="common.value.invalid"/>', required: 'true',
                     placeHolder: '<g:message code="common.numberOfCopies"/>', regExp: '[0-9]{1,20}'" id="snapshotKeep">
            </div>
            <div class="span11 field-box">
              <button type="button"  class="offset3 defaultbtn" onclick="SnapShotInfo.addAutoSnapshot()" 
                      data-dojo-type="dijit.form.Button" id="addAutoSnapButton"><g:message code="common.add"/></button>
              <button type="button"  class="defaultbtn" onclick="SnapShotInfo.updateAutoSnapshot()" 
                      data-dojo-type="dijit.form.Button" id="updateAutoSnapButton"><g:message code="default.button.update.common"/></button>
              <img id="snapLoader" src='images/preloader_circle.gif' alt='<g:message code="common.loading"/>' height='16' width='23' style="display: none"/>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="row-fluid">
      <div style="display: none" id="autoSnapCollection">
        <div id="autoSnapshotList"></div>
      </div>
    </div>
  </div>


</div>
<div data-dojo-type="dijit.Dialog"  id="createStoragePage" title="<g:message code="common.confirm"/>" class="customDialgue">
  <div  class="span6">
  <form data-dojo-type="dijit.form.Form" id="" class="form-horizontal">
    <div class="control-group generalContents">
      <label class="control-label" for="snapshotStorageName"><g:message code="common.disk"/><span class="require">*</span></label>
      <div class="controls ">
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: '<g:message code="common.name.invalid"/>',
               required: true, placeHolder: '<g:message code="common.name.placeHolder"/>', 
               regExp: '[a-zA-Z.0-9- ]{4,20}', propercase: true" 
                name="snapName" id="snapshotStorageName">  
      </div>
    </div>
    <div class="field-box control-group" id="snap-add-disk-billingTypeDiv">
        <label class="control-label"><g:message code="user.createVM.billingType.label"/>:<span class="require">*</span></label>
        <div class="controls">
          <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="snap-add-disk-billingType" id="snap-add-disk-hourlyBilling" value="hourly"/>
          <label style="padding-top: 0;" class="control-label"  for="snap-add-disk-hourlyBilling"><g:message code="common.hourly"/></label> 
          <input type="radio" data-dojo-type="dijit.form.RadioButton" name="snap-add-disk-billingType"  id="snap-add-disk-monthlyBilling" value="monthly"/> 
          <label style="padding-top: 0;" class="control-label" for="snap-add-disk-monthlyBilling" class=""><g:message code="common.monthly"/></label> 
        </div>
    </div>
    <div class="row-fluid offset1">
      <img id="createSnapshotLoader" src="${resource(dir: 'images')}/loader.gif" alt="loading" height="42" width="42" style="display: none">
      <button type="button" class="defaultbtn" id="storageAddButton" data-dojo-type="dijit.form.Button" onclick="SnapShotInfo.createStorage()"><g:message code="common.ok"/></button>
      <button type="button" class="cancelbtn" id="storageCancelButton" data-dojo-type="dijit.form.Button" onclick="SnapShotInfo.cancelStorage()"><g:message code="common.cancel"/></button>
    </div>
  </form>
  </div>
</div>
<div data-dojo-type="dijit.Dialog"  id="deleteSnapshotPage" title="<g:message code="common.confirm"/>" >
    <div class="row-fluid"><p><g:message code="user.snapshot.deleteConfirm"/></p></div>
    <div class="row-fluid">
        <div class="span6">
            <button class="defaultbtn" type="button" data-dojo-type="dijit.form.Button" onclick="SnapShotInfo.deleteSnapshot()"><g:message code="common.yes"/></button>
            <button type="button" class="cancelbtn" data-dojo-type="dijit.form.Button" onclick="SnapShotInfo.cancelDeleteSnapshot()"><g:message code="common.no"/></button>
        </div>
        <div class="span6"></div>           
    </div>  
</div>
<div data-dojo-type="dijit.Dialog" id="snapshotTempPage" 
     title="<g:message code="common.createTemplate"/>" class="span5">
  <form data-dojo-type="dijit.form.Form" id="" class="form-horizontal">
    <div class="control-group span4">
      <label class="control-label" for="snapshotTempName"><g:message code="common.name"/>:<span class="require">*</span></label>
      <div class="controls ">
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: '<g:message code="common.invalidMessage.name"/>',
               required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
               regExp: '[a-zA-Z.0-9 ]{4,20}', propercase: true" 
               name="diskName" id="snapshotTempName">  
      </div>
    </div>
    <div class="control-group span4">
      <label class="control-label" for="snapshotTempDesc"><g:message code="common.description"/>:<span class="require">*</span></label>
      <div class="controls">
        <input type="text" 
               data-dojo-type="dijit.form.ValidationTextBox" 
               data-dojo-props="invalidMessage: '<g:message code="common.name.invalid"/>',
               required: 'required', placeHolder: '<g:message code="common.name.placeHolder"/>', 
               regExp: '[a-zA-Z.0-9 ]{4,20}', propercase: true" 
               name="diskName" id="snapshotTempDesc">  
      </div>
    </div> 
    <div class="control-group span4">
      <label class="control-label" for="snapshotTempPasswordEnabled"><g:message code="common.passwordEnabled"/></label>
      <div class="controls">
        <input type="checkbox" data-dojo-type="dijit.form.CheckBox" 
               id="snapshotTempPasswordEnabled">
      </div>
    </div>
    <div class="control-group">

      <button type="button" class="offset1"  data-dojo-type="dijit.form.Button" 
              onclick="SnapShotInfo.createTemp()"><g:message code="common.ok"/></button>
      <button type="button"  data-dojo-type="dijit.form.Button"
              onclick="SnapShotInfo.cancelCreateTemp()"><g:message code="common.cancel"/></button>
    </div>
  </form>
</div>


<div data-dojo-type="dijit.Dialog" id="createConformationSnapShotPage" class="span4" title="<g:message code="user.snapshot.confirmation"/>"> 
  <div class="row-fluid container">
      <div class="span2"><img src='images/popup-icons/snapshot_icon.png'/></div>
      <div class="span9">
     <div class="form-horizontal">
              <div class="row-fluid">
                <div class="control-group">
                  <label class="control-label overflowLabel" for=""><g:message code="common.storage.name"/>:</label>
                  <div class="controls ">
                    <span id="diskName"></span>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label overflowLabel" for=""><g:message code="common.name"/>:<span class="require">*</span></label>
                  <div class="controls ">
                    <input type="text" data-dojo-type="dijit.form.ValidationTextBox" 
                        id="manualSnapshotName" data-dojo-props="regExp: '[a-zA-Z0-9-]{1,63}', required:true, missingMessage: '<g:message code="common.name.placeHolder"/>'">
                  </div>
                </div>
                <div class="control-group" id="add-snap-billingTypeTr">
                 <label for="" class="control-label overflowLabel"><g:message code="user.createVM.billingType.label"/>:</label>
                  <div class="controls ">
                    <div id="add-snap-billingTypeDiv"> 
                        <input type="radio" checked="true" data-dojo-type="dijit.form.RadioButton"  name="add-snap-billingType" id="add-snap-hourlyBilling" value="hourly"/>
                        <label class="control-label overflowLabel" for="add-snap-hourlyBilling"><g:message code="common.hourly"/></label> 
                        <input type="radio" data-dojo-type="dijit.form.RadioButton" name="add-snap-billingType"  id="add-snap-monthlyBilling" value="monthly"/> 
                        <label class="control-label overflowLabel" for="add-snap-monthlyBilling" class=""><g:message code="common.monthly"/></label>  
                        <div class="form_help_icon">
                            <i class="icon-info-sign" id="snap-createVmBillingTypeHelp"></i>
                            <div data-dojo-type="dijit.Tooltip" data-dojo-props="connectId:'snap-createVmBillingTypeHelp'">
                            <g:message code="user.snapshot.billingTypeInfo"/>
                            </div>
                        </div>
                    </div>
                  </div>
                </div>
              </div>
        </div> 
  </div>
    </div>
  <div class="row-fluid">
      <div class="span6">
          <img id="snapshotLoader" src="${resource(dir: 'images')}/loader.gif" alt="<g:message code="common.loading"/>" height="42" width="42" style="display: none">
          <button type="button" id="addManualSnapshotButton" data-dojo-type="dijit.form.Button" class="defaultbtn" onclick = "SnapShotInfo.takeSnapShot()"><g:message code="common.ok"/></button>
            <button type="button" class="cancelbtn" id="cancelManualSnapshotButton" data-dojo-type = "dijit.form.Button" onclick="SnapShotInfo.cancelSanpshot()"><g:message code="common.cancel"/></button>
      </div>
            <div class="span6"><span class="require" id="snapDiskCost"></span></div>
    
  </div>
</div>
