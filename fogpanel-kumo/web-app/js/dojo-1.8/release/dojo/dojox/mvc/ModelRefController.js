//>>built
define("dojox/mvc/ModelRefController",["dojo/_base/array","dojo/_base/declare","dojo/_base/lang","dojo/Stateful","./_Controller"],function(k,i,l,m,n){return i("dojox.mvc.ModelRefController",n,{ownProps:null,_refModelProp:"model",_refInModelProp:"model",model:null,postscript:function(a,d){this._relTargetProp=(a||{})._refModelProp||this._refModelProp;this.inherited(arguments)},get:function(a){if(!this.hasControllerProperty(a)){var d=this[this._refModelProp];return!d?void 0:d.get?d.get(a):d[a]}return this.inherited(arguments)},
_set:function(a,d){if(!this.hasControllerProperty(a)){var b=this[this._refModelProp];b&&(b.set?b.set(a,d):b[a]=d);return this}return this.inherited(arguments)},watch:function(a,d){function b(c){e&&e.unwatch();c&&l.isFunction(c.set)&&l.isFunction(c.watch)&&(e=c.watch.apply(c,(a?[a]:[]).concat([function(a,c,b){d.call(g,a,c,b)}])))}function i(c,b){var f={};a?f[a]=1:k.forEach([c,b],function(a){var b=a&&a.get("properties");if(b)k.forEach(b,function(a){g.hasControllerProperty(a)||(f[a]=1)});else for(var c in a)a.hasOwnProperty(c)&&
!g.hasControllerProperty(c)&&(f[c]=1)});for(var e in f)d.call(g,e,!c?void 0:c.get?c.get(e):c[e],!b?void 0:b.get?b.get(e):b[e])}if(this.hasControllerProperty(a))return this.inherited(arguments);d||(d=a,a=null);var h=null,e=null,g=this,h=m.prototype.watch.call(this,this._refModelProp,function(a,d,e){d!==e&&(i(d,e),b(e))});b(this.get(this._refModelProp));var j={};j.unwatch=j.remove=function(){e&&(e.unwatch(),e=null);h&&(h.unwatch(),h=null)};return j},hasControllerProperty:function(a){return"_watchCallbacks"==
a||a==this._refModelProp||a==this._refInModelProp||a in(this.ownProps||{})||a in this.constructor.prototype||/^dojoAttach(Point|Event)$/i.test(a)}})});