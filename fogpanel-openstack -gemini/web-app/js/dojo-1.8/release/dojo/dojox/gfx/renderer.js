//>>built
define("dojox/gfx/renderer",["./_base","dojo/_base/lang","dojo/_base/sniff","dojo/_base/window","dojo/_base/config"],function(k,l,f,i,d){var g=null;f.add("vml",function(b,d,c){c.innerHTML='<v:shape adj="1"/>';b="adj"in c.firstChild;c.innerHTML="";return b});return{load:function(b,m,c){function j(){m(["dojox/gfx/"+a],function(b){k.renderer=a;g=b;c(b)})}if(g&&"force"!=b)c(g);else{for(var a=d.forceGfxRenderer,b=!a&&(l.isString(d.gfxRenderer)?d.gfxRenderer:"svg,vml,canvas,silverlight").split(","),h,e;!a&&
b.length;)switch(b.shift()){case "svg":"SVGAngle"in i.global&&(a="svg");break;case "vml":f("vml")&&(a="vml");break;case "silverlight":try{f("ie")?(h=new ActiveXObject("AgControl.AgControl"))&&h.IsVersionSupported("1.0")&&(e=!0):navigator.plugins["Silverlight Plug-In"]&&(e=!0)}catch(n){e=!1}finally{h=null}e&&(a="silverlight");break;case "canvas":i.global.CanvasRenderingContext2D&&(a="canvas")}"canvas"===a&&!1!==d.canvasEvents&&(a="canvasWithEvents");"svg"==a&&"undefined"!=typeof window.svgweb?window.svgweb.addOnLoad(j):
j()}}}});