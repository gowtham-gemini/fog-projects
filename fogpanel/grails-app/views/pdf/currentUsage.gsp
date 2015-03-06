<!DOCTYPE HTML>
<html>
   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Invoice</title>
	<link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-responsive.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/bootstrap/', file: 'bootstrap-overrides.css')}" media="screen">
    <link rel="stylesheet" href="${resource(dir: 'css/lib/', file: 'font-awesome.css')}" media="screen">   
    <link rel="stylesheet" href="${resource(dir: 'css/', file: 'invoice.css')}" media="all">
	<script type="text/javascript" src="${resource(dir: 'js')}/jquery-1.9.1.min.js" ></script>
	<script type="text/javascript" src="${resource(dir: 'js')}/jquery-ui.js" ></script>
    <style media="print">
      @page {
        size: 8.5in 8in;  /* width height */
        margin: 0.25in;
        }
    </style>
    <style media="screen">
      .content {
        width: 700px;
        display: block;
        }
    </style>
    <script type="text/javascript">
        var $s = jQuery.noConflict(); 
			$s(function(){
                            $s(".cuinfo-zone-item").click(function(){
                var a=$s(this).index();
                var id=$a(this).attr("id");

                $s('.current-usage-info-vms').css("display", "none");
                $j('.current-usage-info-vms-desc').css("display", "none");
                var effect = 'slide';
                var options = { direction: 'left' };
                var duration = 700;                

                $a('.current-usage-info-vms').each(function() {
                    var id1=$a(this).attr("id");
                    if(id==id1){
                        $s(this).toggle(effect, options, duration);
                        $s(this).css("display", "inline");
                    }else{
                        $s(this).css("display", "none");
                   }                   

                });
            });
        });
        var $j = jQuery.noConflict(); 
			$j(function(){
				$j(".cuinfo-vms-item").click(function(){
                var a=$j(this).index();
                var id=$j(this).attr("id");
                //  alert(a);
                $j('.current-usage-info-vms-desc').css("display", "none");
                var effect = 'slide';
                var options = { direction: 'left' };
                var duration = 700;
                //$j('.current-usage-info-vms-desc').toggle(effect, options, duration);
                //$j('.current-usage-info-vms-desc').css("display", "inline");
                                        
                $j('.current-usage-info-vms-desc').each(function() {
                    var id1=$j(this).attr("id");
                                    if(id==id1){
                        $j(this).css("display", "inline");
                                    }else{
                        $j(this).css("display", "none");
                    }

                });
            });
        });
			
        var $a = jQuery.noConflict(); 
			$a(function(){
				$a('.cuinfo-zone-item').click(function(){
                var id=$a(this).attr("id");
                $a('.cuinfo-zone-item').each(function() {
                    var id1=$a(this).attr("id");
					if(id==id1){
                        $a(this).addClass( "cuinfo-zone-item-liactive" );
                        $a("#z"+id).css('display','block');
					}else{
                            $a(this).removeClass( "cuinfo-zone-item-liactive" );
					 $a("#z"+id1).css('display','none'); }
                        });                                                                                                                                                               
                                 
                                
                                
                                
                                
                    });
                });			
			
                var $a = jQuery.noConflict(); 
			$a(function(){
				$a('.cuinfo-vms-item').click(function(){
                        var id=$a(this).attr("id");
                        $a('.cuinfo-vms-item').each(function() {
                            var id1=$a(this).attr("id");
                                     	if(id==id1)
					{
                                $a(this).addClass("cuinfo-vms-item-liactive" );
                                $a("#i"+id).css('display','block');
                                         }
					else
					{
                                    $a(this).removeClass("cuinfo-vms-item-liactive" );
                                    $a("#i"+id1).css('display','none'); 
                                }
                            });                                                                
                                
                                
                        });
                    });		
		
    </script>	
	
   </head>
   <body class="invoice_frame">	
       <div class="row-fluid">
           <div class="span3">
                <h3><g:message code="menu.user.billing.currentUsage"/></h3>
            </div>
                <div style="text-align:center;" class="span3 duedate_cont"><g:message code="common.asOnDate"/>:${invoiceDate}</div>
            <div class="pull-right value_dollar"><g:message code="default.valueIn"/> ${currency} </div>
        </div>
        <g:if test="${refund == 0.0}">
            <div class="invoice_pricebox_ui_wotrefund">
        </g:if>
        <g:else>
            <div class="invoice_pricebox_ui">
        </g:else>
                <ul>
                    <li class="prev-bal">
                        <div class="row-fluid amtvalue"><g:formatNumber number="${invoice.previousBalance}" format="###,##0.00"/></div>
                        <g:message code="common.billing.prevBalance"/>
                    </li>
                    <li class="payment">
                        <div class="row-fluid amtvalue"><g:formatNumber number="${invoice.payment}" format="###,##0.00"/></div>
                        <g:message code="menu.admin.billing.payments"/>
                    </li>
                    <g:if test="${refund == 0.0}">
                    </g:if>
                    <g:else>
                        <li class="credit">
                            <div class="row-fluid amtvalue"><g:formatNumber number="${refund}" format="###,##0.00"/></div>
                            <g:message code="menu.admin.billing.refund"/>
                        </li>
                    </g:else>
                        <li class="curr-chrg">
                            <div class="row-fluid amtvalue"><g:formatNumber number="${invoice.currentDue}" format="###,##0.00"/></div>
                            <g:message code="menu.admin.billing.currentCharge"/>
                        </li>
                        <li class="total-pay">
                            <div class="row-fluid amtvalue"><g:formatNumber number="${Math.ceil((totalAmount) * 100d) / 100d}" format="###,##0.00"/></div>
                            <g:message code="common.billing.totalPayable"/>
                        </li>
                        <g:if test="${credit == 0.0}">         
                        </g:if>
                        <g:else>
                            <div class="creditamt_cont">
                                <span>*</span><g:message code="common.availableCredit"/> ${currency} <g:formatNumber number="${credit}" format="###,##0.00"/>
                            </div>
                        </g:else>
                        <g:if test="${userAccount.credit == 0.0}">         
                        </g:if>
                        <g:else>
                            <div class="creditamt_cont">
                                <span>*</span>${currency} ${userAccount.credit} <g:message code="common.creditAdded"/>
                            </div>
                        </g:else>
                </ul>
            </div>                    
                        <div class="row-fluid current-usage-info-cont">
                            <div class="span12" style="position:relative">
                                <div class="span3 current-usage-info-zone">
                                    <ul>
                                        <li class="cuinfo-zone-item-title"><g:message code="common.zoneCharges"/> <div class="cuinfo-zone-item-title-price"><g:formatNumber number="${invoice.currentDue}" format="0.00"/></div></li>
                                        <g:each in="${zoneCost}"> 
                                        <li class="cuinfo-zone-item" id="${it.zoneCount}">
                                            <i class="icon-flag-alt"></i>${it.zone} <div class="cuinfo-zone-item-price"><g:formatNumber number="${it.zoneCost}" format="###,##0.00"/></div>
                                            <div class="cuinfo-zone-item-active" id="z${it.zoneCount}"></div>
                                        </li>
                                        </g:each>
                                    </ul>
                                </div>
                                        <g:each in="${zoneCost}"> 
                                            <div class="span3 current-usage-info-vms" style="display:none;" id="${it.zoneCount}">
                                                <ul>
                                                    <li class="cuinfo-vms-item-title"><g:message code="common.itemBreakup"/> <div class="cuinfo-vms-item-title-price"><g:formatNumber number="${it.zoneCost}" format="###,##0.00"/></div></li>
                                                    <g:each in="${it.billableItem}"> 
                                                    <li class="cuinfo-vms-item" id="${it.billableItemCount}">
                                                        ${it.billableItem} <div class="cuinfo-vsm-item-price"><g:formatNumber number="${it.billableItemCost}" format="###,##0.00"/></div>
                                                        <div class="cuinfo-vms-item-active" id="i${it.billableItemCount}"></div>
                                                    </li>
                                                    </g:each> 
                                                </ul>			
                                            </div>
                                        </g:each> 
                                        <g:each in="${zoneCost}"> 
                                            <g:each in="${it.billableItem}"> 
                                                <div class="span6 current-usage-info-vms-desc" style="display:none;" id="${it.billableItemCount}">
                                                    <ul>
                                                        <li class="cuinfo-vms-desc-item-title">${it.billableItem} <div class="cuinfo-vms-desc-item-title-price"><g:formatNumber number="${it.billableItemCost}" format="0.00"/></div></li>
                                                        <div class="cuinfo-vms-desc-table">
                                                            <div class="divTable">
                                                                <div class="divTableRow_title">
                                                                    <div class="divTableCell first"><g:message code="common.itemName"/></div>
                                                                    <div class="divTableCell"><g:message code="common.plan"/></div>
                                                                    <div class="divTableCell"><g:message code="common.units"/></div>
                                                                    <div class="divTableCell"><g:message code="common.qty"/></div>
                                                                    <div class="divTableCell"><g:message code="common.charges"/></div>
                                                                </div>
                                                                    <g:each in="${it.invoiceItem}"> 
                                                                        <div class="divTableRow">
                                                                            <div id="${it.vmStatus}" class="divTableCell" ><span title="${it.vmStatus}" class="${it.vmStatus}"></span>${it.name}</div>
                                                                            <div class="divTableCell">${it.plan}</div>
                                                                            <div class="divTableCell"><g:formatNumber number="${it.price}" format="#.########"/>${it.priceUnit} </div>
                                                                            <div class="divTableCell"><g:formatNumber number="${it.hours}" format="###,##0.00"/>${it.usageUnit}</div>
                                                                            <div class="divTableCell"><g:formatNumber number="${it.cost}" format="###,##0.00"/></div>
                                                                            </div>
                                                                    </g:each>					
                                                            </div>
                                                        </div>
                                                    </ul>
                                                </div>
                                            </g:each>
                                        </g:each>
                            </div>
                        </div>
            </body>
            </html>
