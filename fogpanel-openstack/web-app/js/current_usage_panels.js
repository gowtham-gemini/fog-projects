jQuery.noConflict();
(function( $ ) {
$(function(){
	var $trigger = $(".cuinfo-zone-item");
	var $menu = $(".current-usage-info-vms");
	$trigger.toggle(function show() {
		$menu.animate({
			width: 200,
			marginLeft: 0,
			display: 'toggle'
		}, 'slow');
	}, function hide() {
		$menu.animate({
			width: 200,
			marginLeft: -240,
			display: 'toggle'
		}, 'slow');
	});
    
});
})(jQuery);

jQuery.noConflict();
(function( $ ) {
$(function(){
    var $trigger = $(".cuinfo-vms-item");
	var $menu = $(".cuinfo-vms-item-infobox");
	$trigger.toggle(function show() {
		$menu.animate({
			width: 200,
			marginLeft: 0,
			display: 'toggle'
		}, 'slow');
	}, function hide() {
		$menu.animate({
			width: 200,
			marginLeft: -420,
			display: 'toggle'
		}, 'slow');
	});
});
})(jQuery);