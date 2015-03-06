/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/touch","./_base/kernel,./aspect,./dom,./on,./has,./mouse,./domReady,./_base/window".split(","),function(m,i,n,e,j,f,o,b){function d(a,c){return k?function(b,d){var f=e(b,c,d),g=e(b,a,function(a){(!h||(new Date).getTime()>h+1E3)&&d.call(this,a)});return{remove:function(){f.remove();g.remove()}}}:function(c,b){return e(c,a,b)}}var k=j("touch"),g=!1;j("ios")&&(i=navigator.userAgent.match(/OS ([\d_]+)/)?RegExp.$1:"1",g=5>parseFloat(i.replace(/_/,".").replace(/_/g,"")));var h,l,c;k&&(o(function(){c=
b.body();b.doc.addEventListener("touchstart",function(a){h=(new Date).getTime();var b=c;c=a.target;e.emit(b,"dojotouchout",{target:b,relatedTarget:c,bubbles:!0});e.emit(c,"dojotouchover",{target:c,relatedTarget:b,bubbles:!0})},!0);e(b.doc,"touchmove",function(a){h=(new Date).getTime();if((a=b.doc.elementFromPoint(a.pageX-(g?0:b.global.pageXOffset),a.pageY-(g?0:b.global.pageYOffset)))&&c!==a)e.emit(c,"dojotouchout",{target:c,relatedTarget:a,bubbles:!0}),e.emit(a,"dojotouchover",{target:a,relatedTarget:c,
bubbles:!0}),c=a})}),l=function(a,d){return e(b.doc,"touchmove",function(e){if(a===b.doc||n.isDescendant(c,a))e.target=c,d.call(this,e)})});f={press:d("mousedown","touchstart"),move:d("mousemove",l),release:d("mouseup","touchend"),cancel:d(f.leave,"touchcancel"),over:d("mouseover","dojotouchover"),out:d("mouseout","dojotouchout"),enter:f._eventHandler(d("mouseover","dojotouchover")),leave:f._eventHandler(d("mouseout","dojotouchout"))};return m.touch=f});