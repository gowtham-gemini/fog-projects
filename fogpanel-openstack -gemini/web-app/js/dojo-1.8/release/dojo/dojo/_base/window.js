/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/_base/window",["./kernel","./lang","../sniff"],function(a,h,d){var c={global:a.global,doc:this.document||null,body:function(b){b=b||a.doc;return b.body||b.getElementsByTagName("body")[0]},setContext:function(b,e){a.global=c.global=b;a.doc=c.doc=e},withGlobal:function(b,e,d,j){var k=a.global;try{return a.global=c.global=b,c.withDoc.call(null,b.document,e,d,j)}finally{a.global=c.global=k}},withDoc:function(b,e,i,j){var k=c.doc,h=d("quirks"),m=d("ie"),f,g,l;try{a.doc=c.doc=b;a.isQuirks=
d.add("quirks","BackCompat"==a.doc.compatMode,!0,!0);if(d("ie")&&(l=b.parentWindow)&&l.navigator)f=parseFloat(l.navigator.appVersion.split("MSIE ")[1])||void 0,(g=b.documentMode)&&5!=g&&Math.floor(f)!=g&&(f=g),a.isIE=d.add("ie",f,!0,!0);i&&"string"==typeof e&&(e=i[e]);return e.apply(i,j||[])}finally{a.doc=c.doc=k,a.isQuirks=d.add("quirks",h,!0,!0),a.isIE=d.add("ie",m,!0,!0)}}};h.mixin(a,c);return c});