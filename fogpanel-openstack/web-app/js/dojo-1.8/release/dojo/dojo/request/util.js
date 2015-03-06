/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/request/util","exports,../errors/RequestError,../errors/CancelError,../Deferred,../io-query,../_base/array,../_base/lang,../promise/Promise".split(","),function(f,n,k,o,l,p,h,q){function r(b){return m(b)}function s(b){return b.data||b.text}f.deepCopy=function(b,c){for(var e in c){var d=b[e],a=c[e];d!==a&&(d&&"object"===typeof d&&a&&"object"===typeof a?f.deepCopy(d,a):b[e]=a)}return b};f.deepCreate=function(b,c){var c=c||{},e=h.delegate(b),d,a;for(d in b)(a=b[d])&&"object"===typeof a&&
(e[d]=f.deepCreate(a,c[d]));return f.deepCopy(e,c)};var m=Object.freeze||function(b){return b};f.deferred=function(b,c,e,d,a,j){var g=new o(function(a){c&&c(g,b);return!a||!(a instanceof n)&&!(a instanceof k)?new k("Request canceled",b):a});g.response=b;g.isValid=e;g.isReady=d;g.handleResponse=a;e=g.then(r).otherwise(function(a){a.response=b;throw a;});f.notify&&e.then(h.hitch(f.notify,"emit","load"),h.hitch(f.notify,"emit","error"));var d=e.then(s),a=new q,i;for(i in d)d.hasOwnProperty(i)&&(a[i]=
d[i]);a.response=e;m(a);j&&g.then(function(a){j.call(g,a)},function(a){j.call(g,b,a)});g.promise=a;g.then=a.then;return g};f.addCommonMethods=function(b,c){p.forEach(c||["GET","POST","PUT","DELETE"],function(c){b[("DELETE"===c?"DEL":c).toLowerCase()]=function(d,a){a=h.delegate(a||{});a.method=c;return b(d,a)}})};f.parseArgs=function(b,c,e){var d=c.data,a=c.query;if(d&&!e&&"object"===typeof d)c.data=l.objectToQuery(d);a?("object"===typeof a&&(a=l.objectToQuery(a)),c.preventCache&&(a+=(a?"&":"")+"request.preventCache="+
+new Date)):c.preventCache&&(a="request.preventCache="+ +new Date);b&&a&&(b+=(~b.indexOf("?")?"&":"?")+a);return{url:b,options:c,getHeader:function(){return null}}};f.checkStatus=function(b){b=b||0;return 200<=b&&300>b||304===b||1223===b||!b}});