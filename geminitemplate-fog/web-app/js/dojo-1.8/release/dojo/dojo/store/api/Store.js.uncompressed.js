/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/store/api/Store",["../../_base/declare"],function(b){var a=b(null,{idProperty:"id",queryEngine:null,get:function(){},getIdentity:function(){},put:function(){},add:function(){},remove:function(b){delete this.index[b];for(var a=this.data,d=this.idProperty,c=0,e=a.length;c<e;c++)if(a[c][d]==b){a.splice(c,1);break}},query:function(){},transaction:function(){},getChildren:function(){},getMetadata:function(){}});a.PutDirectives=b(null,{});a.SortInformation=b(null,{});a.QueryOptions=b(null,
{});a.QueryResults=b(null,{forEach:function(){},filter:function(){},map:function(){},then:function(){},observe:function(){},total:0});a.Transaction=b(null,{commit:function(){},abort:function(){}});return a});