/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/uacss",["./dom-geometry","./_base/lang","./ready","./sniff","./_base/window"],function(h,i,j,a,f){var e=f.doc.documentElement,b=a("ie"),k=a("opera"),c=Math.floor,l=a("ff"),f=h.boxModel.replace(/-/,""),b={dj_ie:b,dj_ie6:6==c(b),dj_ie7:7==c(b),dj_ie8:8==c(b),dj_ie9:9==c(b),dj_quirks:a("quirks"),dj_iequirks:b&&a("quirks"),dj_opera:k,dj_khtml:a("khtml"),dj_webkit:a("webkit"),dj_safari:a("safari"),dj_chrome:a("chrome"),dj_gecko:a("mozilla"),dj_ff3:3==c(l)};b["dj_"+f]=!0;var d="",g;for(g in b)b[g]&&
(d+=g+" ");e.className=i.trim(e.className+" "+d);j(90,function(){if(!h.isBodyLtr()){var a="dj_rtl dijitRtl "+d.replace(/ /g,"-rtl ");e.className=i.trim(e.className+" "+a+"dj_rtl dijitRtl "+d.replace(/ /g,"-rtl "))}});return a});