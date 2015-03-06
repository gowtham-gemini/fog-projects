/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/ready",["./_base/kernel","./has","require","./domReady","./_base/lang"],function(a,c,b,m,j){var l=0,g,d=[],h=0,i=function(){if(l&&!h&&d.length){h=1;var a=d.shift();try{a()}finally{h=0}h=0;d.length&&g(i)}};b.on&&b.on("idle",i);g=function(){(b.idle?b.idle():1)&&i()};var c=a.ready=a.addOnLoad=function(b,c,f){var e=j._toArray(arguments);"number"!=typeof b?(f=c,c=b,b=1E3):e.shift();f=f?j.hitch.apply(a,e):function(){c()};f.priority=b;for(e=0;e<d.length&&b>=d[e].priority;e++);d.splice(e,0,f);
g()},k=a.config.addOnLoad;if(k)c[j.isArray(k)?"apply":"call"](a,k);a.config.parseOnLoad&&!a.isAsync&&c(99,function(){a.parser||(a.deprecated("Add explicit require(['dojo/parser']);","","2.0"),b(["dojo/parser"]))});m(function(){l=1;a._postLoad=a.config.afterOnLoad=!0;d.length&&g(i)});return c});