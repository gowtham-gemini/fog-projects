/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/uacss",["./dom-geometry","./_base/lang","./ready","./sniff","./_base/window"],function(g,h,k,a,d){var e=d.doc.documentElement,d=a("ie"),b=a("opera"),i=Math.floor,j=a("ff"),l=g.boxModel.replace(/-/,""),b={dj_quirks:a("quirks"),dj_opera:b,dj_khtml:a("khtml"),dj_webkit:a("webkit"),dj_safari:a("safari"),dj_chrome:a("chrome"),dj_gecko:a("mozilla")};if(d)b.dj_ie=!0,b["dj_ie"+i(d)]=!0,b.dj_iequirks=a("quirks");j&&(b["dj_ff"+i(j)]=!0);b["dj_"+l]=!0;var c="",f;for(f in b)b[f]&&(c+=f+" ");e.className=
h.trim(e.className+" "+c);k(90,function(){if(!g.isBodyLtr()){var a="dj_rtl dijitRtl "+c.replace(/ /g,"-rtl ");e.className=h.trim(e.className+" "+a+"dj_rtl dijitRtl "+c.replace(/ /g,"-rtl "))}});return a});