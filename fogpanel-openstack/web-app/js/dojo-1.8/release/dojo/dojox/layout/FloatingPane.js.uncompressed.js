//>>built
require({cache:{"url:dojox/layout/resources/FloatingPane.html":'<div class="dojoxFloatingPane" id="${id}">\n\t<div tabindex="0" role="button" class="dojoxFloatingPaneTitle" dojoAttachPoint="focusNode">\n\t\t<span dojoAttachPoint="closeNode" dojoAttachEvent="onclick: close" class="dojoxFloatingCloseIcon"></span>\n\t\t<span dojoAttachPoint="maxNode" dojoAttachEvent="onclick: maximize" class="dojoxFloatingMaximizeIcon">&thinsp;</span>\n\t\t<span dojoAttachPoint="restoreNode" dojoAttachEvent="onclick: _restore" class="dojoxFloatingRestoreIcon">&thinsp;</span>\t\n\t\t<span dojoAttachPoint="dockNode" dojoAttachEvent="onclick: minimize" class="dojoxFloatingMinimizeIcon">&thinsp;</span>\n\t\t<span dojoAttachPoint="titleNode" class="dijitInline dijitTitleNode"></span>\n\t</div>\n\t<div dojoAttachPoint="canvas" class="dojoxFloatingPaneCanvas">\n\t\t<div dojoAttachPoint="containerNode" role="region" tabindex="-1" class="${contentClass}">\n\t\t</div>\n\t\t<span dojoAttachPoint="resizeHandle" class="dojoxFloatingResizeHandle"></span>\n\t</div>\n</div>\n'}});
require({cache:{"url:dojox/layout/resources/FloatingPane.html":'<div class="dojoxFloatingPane" id="${id}">\n\t<div tabindex="0" role="button" class="dojoxFloatingPaneTitle" dojoAttachPoint="focusNode">\n\t\t<span dojoAttachPoint="closeNode" dojoAttachEvent="onclick: close" class="dojoxFloatingCloseIcon"></span>\n\t\t<span dojoAttachPoint="maxNode" dojoAttachEvent="onclick: maximize" class="dojoxFloatingMaximizeIcon">&thinsp;</span>\n\t\t<span dojoAttachPoint="restoreNode" dojoAttachEvent="onclick: _restore" class="dojoxFloatingRestoreIcon">&thinsp;</span>\t\n\t\t<span dojoAttachPoint="dockNode" dojoAttachEvent="onclick: minimize" class="dojoxFloatingMinimizeIcon">&thinsp;</span>\n\t\t<span dojoAttachPoint="titleNode" class="dijitInline dijitTitleNode"></span>\n\t</div>\n\t<div dojoAttachPoint="canvas" class="dojoxFloatingPaneCanvas">\n\t\t<div dojoAttachPoint="containerNode" role="region" tabindex="-1" class="${contentClass}">\n\t\t</div>\n\t\t<span dojoAttachPoint="resizeHandle" class="dojoxFloatingResizeHandle"></span>\n\t</div>\n</div>\n'}});
define("dojox/layout/FloatingPane","dojo/_base/kernel,dojo/_base/lang,dojo/_base/window,dojo/_base/declare,dojo/_base/fx,dojo/_base/connect,dojo/_base/array,dojo/_base/sniff,dojo/window,dojo/dom,dojo/dom-class,dojo/dom-geometry,dojo/dom-construct,dijit/_TemplatedMixin,dijit/_Widget,dijit/BackgroundIframe,dojo/dnd/Moveable,./ContentPane,./ResizeHandle,dojo/text!./resources/FloatingPane.html,./Dock".split(","),function(g,b,i,j,h,k,f,l,m,n,e,d,o,p,q,r,s,t,u,v,w){g.experimental("dojox.layout.FloatingPane");
return j("dojox.layout.FloatingPane",[t,p],{closable:!0,dockable:!0,resizable:!1,maxable:!1,resizeAxis:"xy",title:"",dockTo:"",duration:400,contentClass:"dojoxFloatingPaneContent",_showAnim:null,_hideAnim:null,_dockNode:null,_restoreState:{},_allFPs:[],_startZ:100,templateString:v,attributeMap:b.delegate(q.prototype.attributeMap,{title:{type:"innerHTML",node:"titleNode"}}),postCreate:function(){this.inherited(arguments);new s(this.domNode,{handle:this.focusNode});if(!this.dockable)this.dockNode.style.display=
"none";if(!this.closable)this.closeNode.style.display="none";if(!this.maxable)this.maxNode.style.display="none",this.restoreNode.style.display="none";this.resizable?this.domNode.style.width=d.getMarginBox(this.domNode).w+"px":this.resizeHandle.style.display="none";this._allFPs.push(this);this.domNode.style.position="absolute";this.bgIframe=new r(this.domNode);this._naturalState=d.position(this.domNode)},startup:function(){if(!this._started){this.inherited(arguments);if(this.resizable)l("ie")?this.canvas.style.overflow=
"auto":this.containerNode.style.overflow="auto",this._resizeHandle=new u({targetId:this.id,resizeAxis:this.resizeAxis},this.resizeHandle);if(this.dockable){var a=this.dockTo;this.dockTo=this.dockTo?dijit.byId(this.dockTo):dijit.byId("dojoxGlobalFloatingDock");if(!this.dockTo){var c;a?(c=a,a=n.byId(a)):(a=o.create("div",null,i.body()),e.add(a,"dojoxFloatingDockDefault"),c="dojoxGlobalFloatingDock");this.dockTo=new w({id:c,autoPosition:"south"},a);this.dockTo.startup()}("none"==this.domNode.style.display||
"hidden"==this.domNode.style.visibility)&&this.minimize()}this.connect(this.focusNode,"onmousedown","bringToTop");this.connect(this.domNode,"onmousedown","bringToTop");this.resize(d.position(this.domNode));this._started=!0}},setTitle:function(a){g.deprecated("pane.setTitle","Use pane.set('title', someTitle)","2.0");this.set("title",a)},close:function(){this.closable&&(k.unsubscribe(this._listener),this.hide(b.hitch(this,function(){this.destroyRecursive()})))},hide:function(a){h.fadeOut({node:this.domNode,
duration:this.duration,onEnd:b.hitch(this,function(){this.domNode.style.display="none";this.domNode.style.visibility="hidden";this.dockTo&&this.dockable&&this.dockTo._positionDock(null);a&&a()})}).play()},show:function(a){h.fadeIn({node:this.domNode,duration:this.duration,beforeBegin:b.hitch(this,function(){this.domNode.style.display="";this.domNode.style.visibility="visible";this.dockTo&&this.dockable&&this.dockTo._positionDock(null);"function"==typeof a&&a();this._isDocked=!1;if(this._dockNode)this._dockNode.destroy(),
this._dockNode=null})}).play();this.resize(d.position(this.domNode));this._onShow()},minimize:function(){this._isDocked||this.hide(b.hitch(this,"_dock"))},maximize:function(){if(!this._maximized)this._naturalState=d.position(this.domNode),this._isDocked&&(this.show(),setTimeout(b.hitch(this,"maximize"),this.duration)),e.add(this.focusNode,"floatingPaneMaximized"),this.resize(m.getBox()),this._maximized=!0},_restore:function(){if(this._maximized)this.resize(this._naturalState),e.remove(this.focusNode,
"floatingPaneMaximized"),this._maximized=!1},_dock:function(){if(!this._isDocked&&this.dockable)this._dockNode=this.dockTo.addNode(this),this._isDocked=!0},resize:function(a){this._currentState=a=a||this._naturalState;var c=this.domNode.style;if("t"in a)c.top=a.t+"px";else if("y"in a)c.top=a.y+"px";if("l"in a)c.left=a.l+"px";else if("x"in a)c.left=a.x+"px";c.width=a.w+"px";c.height=a.h+"px";a={l:0,t:0,w:a.w,h:a.h-this.focusNode.offsetHeight};d.setMarginBox(this.canvas,a);this._checkIfSingleChild();
this._singleChild&&this._singleChild.resize&&this._singleChild.resize(a)},bringToTop:function(){var a=f.filter(this._allFPs,function(a){return a!==this},this);a.sort(function(a,b){return a.domNode.style.zIndex-b.domNode.style.zIndex});a.push(this);f.forEach(a,function(a,b){a.domNode.style.zIndex=this._startZ+2*b;e.remove(a.domNode,"dojoxFloatingPaneFg")},this);e.add(this.domNode,"dojoxFloatingPaneFg")},destroy:function(){this._allFPs.splice(f.indexOf(this._allFPs,this),1);this._resizeHandle&&this._resizeHandle.destroy();
this.inherited(arguments)}})});