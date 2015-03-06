//>>built
define("dojox/mobile/ScrollablePane","dojo/_base/array,dojo/_base/declare,dojo/_base/sniff,dojo/_base/window,dojo/dom,dojo/dom-construct,dojo/dom-style,./_ScrollableMixin,./Pane".split(","),function(k,l,i,m,n,j,g,o,p){return l("dojox.mobile.ScrollablePane",[p,o],{roundCornerMask:!1,radius:0,baseClass:"mblScrollablePane",buildRendering:function(){var b=this.containerNode=j.create("div",{className:"mblScrollableViewContainer",style:{width:"v"===this.scrollDir?"100%":""}});this.inherited(arguments);
if(this.srcNodeRef)for(var a=0,c=this.srcNodeRef.childNodes.length;a<c;a++)this.containerNode.appendChild(this.srcNodeRef.firstChild);if(this.roundCornerMask&&i("webkit"))b=this.containerNode,a=this.maskNode=j.create("div",{className:"mblScrollablePaneMask",style:{webkitMaskImage:"-webkit-canvas("+this.id+"_mask)"}}),a.appendChild(b),b=a;this.domNode.appendChild(b);n.setSelectable(this.containerNode,!1)},resize:function(){this.inherited(arguments);this.roundCornerMask&&this.createRoundMask();k.forEach(this.getChildren(),
function(b){b.resize&&b.resize()})},isTopLevel:function(){var b=this.getParent&&this.getParent();return!b||!b.resize},createRoundMask:function(){if(i("webkit")&&0!=this.domNode.offsetHeight){this.maskNode.style.height=this.domNode.offsetHeight+"px";var b=this.getChildren()[0],a=this.containerNode,c=b?b.domNode:0<a.childNodes.length&&(1===a.childNodes[0].nodeType?a.childNodes[0]:a.childNodes[1]),a=this.radius;if(!a){var e=function(a){return parseInt(g.get(a,"borderTopLeftRadius"))};b?(a=e(b.domNode),
a||(a=(a=b.getChildren()[0])?e(a.domNode):0)):a=e(c)}var d=this.domNode.offsetWidth,b=c.offsetWidth,e=this.domNode.offsetHeight,f=g.get(c,"marginTop"),h=g.get(c,"marginBottom"),c=g.get(c,"marginLeft"),d=m.doc.getCSSCanvasContext("2d",this.id+"_mask",d,e);d.fillStyle="#000000";d.beginPath();d.moveTo(c+a,f);d.arcTo(c+b,f,c+b,e-h-a,a);d.arcTo(c+b,e-h,c+a,e-h,a);d.arcTo(c,e-h,c,f+a,a);d.arcTo(c,f,c+a,f,a);d.closePath();d.fill()}}})});