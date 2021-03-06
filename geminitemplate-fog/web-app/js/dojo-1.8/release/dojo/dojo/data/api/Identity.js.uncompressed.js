/*
	Copyright (c) 2004-2012, The Dojo Foundation All Rights Reserved.
	Available via Academic Free License >= 2.1 OR the modified BSD license.
	see: http://dojotoolkit.org/license for details
*/

//>>built
define("dojo/data/api/Identity",["../../_base/declare","./Read"],function(a,b){return a("dojo.data.api.Identity",b,{getFeatures:function(){return{"dojo.data.api.Read":!0,"dojo.data.api.Identity":!0}},getIdentity:function(){throw Error("Unimplemented API: dojo.data.api.Identity.getIdentity");},getIdentityAttributes:function(){throw Error("Unimplemented API: dojo.data.api.Identity.getIdentityAttributes");},fetchItemByIdentity:function(a){if(!this.isItemLoaded(a.item))throw Error("Unimplemented API: dojo.data.api.Identity.fetchItemByIdentity");
}})});