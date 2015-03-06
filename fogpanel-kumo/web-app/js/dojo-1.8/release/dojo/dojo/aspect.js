/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/aspect",[],function(){function m(e,d,h,i){var c=e[d],g="around"==d,a;if(g){var j=h(function(){return c.advice(this,arguments)});a={remove:function(){j&&(j=e=h=null)},advice:function(a,b){return j?j.apply(a,b):c.advice(a,b)}}}else a={remove:function(){if(a.advice){var f=a.previous,b=a.next;if(!b&&!f)delete e[d];else if(f?f.next=b:e[d]=b,b)b.previous=f;e=h=a.advice=null}},id:l++,advice:h,receiveArguments:i};if(c&&!g)if("after"==d){for(;c.next&&(c=c.next););c.next=a;a.previous=c}else{if("before"==
d)e[d]=a,a.next=c,c.previous=a}else e[d]=a;return a}function k(e){return function(d,h,i,c){var g=d[h],a;if(!g||g.target!=d){d[h]=a=function(){for(var d=l,f=arguments,b=a.before;b;)f=b.advice.apply(this,f)||f,b=b.next;if(a.around)var c=a.around.advice(this,f);for(b=a.after;b&&b.id<d;){if(b.receiveArguments)var e=b.advice.apply(this,f),c=void 0===e?c:e;else c=b.advice.call(this,c,f);b=b.next}return c};if(g)a.around={advice:function(a,c){return g.apply(a,c)}};a.target=d}d=m(a||g,e,i,c);i=null;return d}}
var l=0,n=k("after"),o=k("before"),p=k("around");return{before:o,around:p,after:n}});