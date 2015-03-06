<div class="row-fluid">
  <div class="span12 breadcrumbs">
  	<ul>
                <li><a href="#/user/home"><i class="icon-home"></i></a></li>
		<li>/</li>
                <li><a href="#/user/billing"><g:message code="menu.user.billing"/></a></li>
		<li>/</li>
		<li><g:message code="menu.user.billing.currentUsage"/></li>
	</ul>	
  </div>

<div class="generatebtn_pdf">
    <a href="" id="currentPdf" class="">
        <div class="pdf_icon"></div><g:message code="common.downloadPdf"/>
    </a>
</div>
  <div id="noCurrentUsage" style="display: none;">
    
    <div class="span12 db_graph">
    <div class="create_item_box span10 offset1">
      <h1>
      <span><g:message code="common.VM.heading"/> </span>
      </h1>
        <p><g:message code="user.cloud.message"/> </p>
        <a title="Launch VM" class="btn-flat new-product" href="#/user/cloud/createVm"><img src="${resource(dir: 'images')}/vm_quicklch_icon.png" alt=""></img><g:message code="user.cloud.launchVM"/> </a>  
  </div>
</div>
    
  </div>  
<div class="row-fluid" id="usagePageLoader">
  <iframe class="span12" height="600px" frameborder="0" id="currentInvoice" name="cuurrentInvoiceFrame"></iframe>
</div>
<!-- src="pdf/invoiceSummary?invoiceId=10002"-->