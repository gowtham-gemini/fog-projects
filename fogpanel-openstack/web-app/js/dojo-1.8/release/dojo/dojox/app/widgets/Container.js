//>>built
define("dojox/app/widgets/Container","dojo/_base/declare,dojo/_base/lang,dijit/registry,dojo/dom-attr,dojo/dom-geometry,dojo/dom-style,dijit/_WidgetBase,dijit/_Container,dijit/_Contained,dojo/_base/array,dojo/query,../layout/utils,./_ScrollableMixin".split(","),function(j,i,k,e,c,f,l,m,n,o,p,q,r){return j("dojox.app.widgets.Container",[l,m,n,r],{scrollable:!1,buildRendering:function(){if(!this.region)this.region="center",e.set(this.srcNodeRef,"data-app-region","center");this.inherited(arguments);
f.set(this.domNode,"overflow-x","hidden");f.set(this.domNode,"overflow-y","auto");if(this.scrollable)this.inherited(arguments),this.domNode.style.position="absolute",this.domNode.style.width="100%",this.domNode.style.height="100%"},startup:function(){if(!this._started)this.scrollable&&this.inherited(arguments),this._started=!0},resize:function(a,s){var d=this.domNode;a&&c.setMarginBox(d,a);var b=s||{};i.mixin(b,a||{});if(!("h"in b)||!("w"in b))b=i.mixin(c.getMarginBox(d),b);var g=f.getComputedStyle(d),
h=c.getMarginExtents(d,g),e=c.getBorderExtents(d,g),b=this._borderBox={w:b.w-(h.w+e.w),h:b.h-(h.h+e.h)},h=c.getPadExtents(d,g);this._contentBox={l:f.toPixelValue(d,g.paddingLeft),t:f.toPixelValue(d,g.paddingTop),w:b.w-h.w,h:b.h-h.h};this.layout()},layout:function(){children=p("> [data-app-region], > [region]",this.domNode).map(function(a){var c=k.getEnclosingWidget(a);return c?(c.region=e.get(a,"data-app-region")||e.get(a,"region"),c):{domNode:a,region:e.get(a,"data-app-region")||dom.Attr.get(a,"region")}});
this._contentBox&&q.layoutChildren(this.domNode,this._contentBox,children);o.forEach(this.getChildren(),function(a){!a._started&&a.startup&&a.startup()})}})});