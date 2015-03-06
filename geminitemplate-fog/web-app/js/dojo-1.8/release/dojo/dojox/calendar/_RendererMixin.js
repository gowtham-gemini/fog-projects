//>>built
define("dojox/calendar/_RendererMixin",["dojo/_base/declare","dojo/_base/lang","dojo/dom-style","dojo/dom-class","dojo/Stateful"],function(j,k,h,i,l){return j("dojox.calendar._RendererMixin",l,{item:null,owner:null,edited:!1,focused:!1,hovered:!1,selected:!1,moveEnabled:!0,resizeEnabled:!0,_orientation:"vertical",_displayValue:"block",_displayValueMap:{},visibilityLimits:{resizeStartHandle:50,resizeEndHandle:-1,summaryLabel:15,startTimeLabel:45,endTimeLabel:50},_setSelectedAttr:function(a){this._setState("selected",
a,"Selected")},_setFocusedAttr:function(a){this._setState("focused",a,"Focused")},_setEditedAttr:function(a){this._setState("edited",a,"Edited")},_setHoveredAttr:function(a){this._setState("hovered",a,"Hovered")},_setState:function(a,c,f){this[a]!=c&&(i[c?"add":"remove"](this.stateNode||this.domNode,f),this._set(a,c))},_setItemAttr:function(a){null==a?(this.item&&this.item.cssClass&&i.remove(this.domNode,this.item.cssClass),this.item=null):(null!=this.item?(this.item.cssClass!=a.cssClass&&this.item.cssClass&&
i.remove(this.domNode,this.item.cssClass),this.item=k.mixin(this.item,a)):this.item=a,a.cssClass&&i.add(this.domNode,a.cssClass))},_setText:function(a,c,f){this.owner&&this.owner._setText(a,c,f)},_isElementVisible:function(a,c,f,g){var e=!0,b=this.visibilityLimits[a];switch(a){case "moveHandle":e=this.moveEnabled;break;case "resizeStartHandle":e=this.mobile?this.resizeEnabled&&!c&&this.edited&&(-1==b||g>b):this.resizeEnabled&&!c&&(-1==b||g>b);break;case "resizeEndHandle":e=this.mobile?this.resizeEnabled&&
!f&&this.edited&&(-1==b||g>b):this.resizeEnabled&&!f&&(-1==b||g>b);break;case "startTimeLabel":e=this.mobile?!c&&(!this.edited||this.edited&&(-1==b||g>b)):!c&&(-1==b||g>b);break;case "endTimeLabel":e=this.edited&&!f&&(-1==b||g>b);break;case "summaryLabel":e=this.mobile?!this.edited||this.edited&&(-1==b||g>b):-1==b||g>b}return e},_formatTime:function(a,c){return this.owner&&null!=this.owner.get("formatItemTimeFunc")?this.owner.formatItemTimeFunc(c,a):a.dateLocaleModule.format(c,{selector:"time"})},
getDisplayValue:function(){return this._displayValue},updateRendering:function(a,c){c=c||this.item.h;a=a||this.item.w;if(c||a){this.item.h=c;this.item.w=a;var f="vertical"==this._orientation?c:a,g=this.owner.renderData,e=0!=g.dateModule.compare(this.item.range[0],this.item.startTime),b=0!=g.dateModule.compare(this.item.range[1],this.item.endTime),d;null!=this.beforeIcon&&(d="horizontal"!=this._orientation||this.isLeftToRight()?e:b,h.set(this.beforeIcon,"display",d?this.getDisplayValue("beforeIcon"):
"none"));null!=this.afterIcon&&(d="horizontal"!=this._orientation||this.isLeftToRight()?b:e,h.set(this.afterIcon,"display",d?this.getDisplayValue("afterIcon"):"none"));this.moveHandle&&(d=this._isElementVisible("moveHandle",e,b,f),h.set(this.moveHandle,"display",d?this.getDisplayValue("moveHandle"):"none"));this.resizeStartHandle&&(d=this._isElementVisible("resizeStartHandle",e,b,f),h.set(this.resizeStartHandle,"display",d?this.getDisplayValue("resizeStartHandle"):"none"));this.resizeEndHandle&&(d=
this._isElementVisible("resizeEndHandle",e,b,f),h.set(this.resizeEndHandle,"display",d?this.getDisplayValue("resizeEndHandle"):"none"));this.startTimeLabel&&(d=this._isElementVisible("startTimeLabel",e,b,f),h.set(this.startTimeLabel,"display",d?this.getDisplayValue("startTimeLabel"):"none"),d&&this._setText(this.startTimeLabel,this._formatTime(g,this.item.startTime)));this.endTimeLabel&&(d=this._isElementVisible("endTimeLabel",e,b,f),h.set(this.endTimeLabel,"display",d?this.getDisplayValue("endTimeLabel"):
"none"),d&&this._setText(this.endTimeLabel,this._formatTime(g,this.item.endTime)));this.summaryLabel&&(d=this._isElementVisible("summaryLabel",e,b,f),h.set(this.summaryLabel,"display",d?this.getDisplayValue("summaryLabel"):"none"),d&&this._setText(this.summaryLabel,this.item.summary,!0))}}})});