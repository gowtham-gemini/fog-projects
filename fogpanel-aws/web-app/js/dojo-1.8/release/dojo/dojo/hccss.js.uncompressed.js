/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/hccss","require,./_base/config,./dom-class,./dom-construct,./dom-style,./has,./ready,./_base/window".split(","),function(e,f,g,h,i,a,j,d){a.add("highcontrast",function(){var c=d.doc.createElement("div");c.style.cssText="border: 1px solid; border-color:red green; position: absolute; height: 5px; top: -999px;background-image: url("+(f.blankGif||e.toUrl("./resources/blank.gif"))+");";d.body().appendChild(c);var b=i.getComputedStyle(c),a=b.backgroundImage,b=b.borderTopColor==b.borderRightColor||
a&&("none"==a||"url(invalid-url:)"==a);h.destroy(c);return b});j(90,function(){a("highcontrast")&&g.add(d.body(),"dj_a11y")});return a});