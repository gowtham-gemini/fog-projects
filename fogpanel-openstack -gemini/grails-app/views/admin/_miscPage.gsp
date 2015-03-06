
<!--Bread Crump :: Replaced :: Begins Here -->
<ul class="breadcrumb">
    <li><a href="#/admin/dashboard"><i class="icon-home"></i></a><span class="divider">/</span></li>
    <li><a href="#/admin/services" ><g:message code="menu.admin.services"/></a><span class="divider">/</span></li>
    <li><a href="#"><g:message code="menu.admin.services.misc"/></a></li> 
</ul>
<!--Bread Crump :: Replaced :: Ends Here -->

<div class="row-fluid">
    <div data-dojo-type="dijit/layout/TabContainer" id="miscTabWidget" class="span12" style="overflow: visible;" tabStrip="true">
      <div data-dojo-type="dijit/layout/ContentPane" onshow="VolumeSnapshotInfo.populateValues()" title="<g:message code="common.volumeSnapshot"/>" selected="true">
          <g:render template="volumeSnapshotPage" />
      </div>
      <div data-dojo-type="dijit/layout/ContentPane" onshow="SnapshotInfo.populateValues()" title="<g:message code="common.imageSnapshot"/>">
          <g:render template="imageSnapshotPage" />
      </div>
      <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.network"/>" onshow="NetworkSnapshotInfo.populateValues()" >
          <g:render template="networkMiscPage" />
      </div>
      <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.bandwidthWrite"/>" onshow="NetworkWriteSnapshotInfo.populateValues()" >
          <g:render template="networkWritePage" />
      </div>
      <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.bandwidthRead"/>" onshow="NetworkReadSnapshotInfo.populateValues()" >
          <g:render template="networkReadPage" />
      </div>
      <div data-dojo-type="dijit/layout/ContentPane" title="<g:message code="common.floatingIP"/>" onshow="FloatingIPSnapshotInfo.populateValues()" >
          <g:render template="floatingIPMiscReadPage" />
      </div>
    </div>
</div>