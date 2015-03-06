//>>built
require({cache:{"url:dojox/form/resources/Uploader.html":'<span class="dijit dijitReset dijitInline"\n\t><span class="dijitReset dijitInline dijitButtonNode"\n\t\tdojoAttachEvent="ondijitclick:_onClick"\n\t\t><span class="dijitReset dijitStretch dijitButtonContents"\n\t\t\tdojoAttachPoint="titleNode,focusNode"\n\t\t\trole="button" aria-labelledby="${id}_label"\n\t\t\t><span class="dijitReset dijitInline dijitIcon" dojoAttachPoint="iconNode"></span\n\t\t\t><span class="dijitReset dijitToggleButtonIconChar">&#x25CF;</span\n\t\t\t><span class="dijitReset dijitInline dijitButtonText"\n\t\t\t\tid="${id}_label"\n\t\t\t\tdojoAttachPoint="containerNode"\n\t\t\t></span\n\t\t></span\n\t></span\n\t><\!--no need to have this for Uploader \n\t<input ${!nameAttrSetting} type="${type}" value="${value}" class="dijitOffScreen" tabIndex="-1"\n\t\tdojoAttachPoint="valueNode"\n/--\></span>\n'}});
require({cache:{"url:dojox/form/resources/Uploader.html":'<span class="dijit dijitReset dijitInline"\n\t><span class="dijitReset dijitInline dijitButtonNode"\n\t\tdojoAttachEvent="ondijitclick:_onClick"\n\t\t><span class="dijitReset dijitStretch dijitButtonContents"\n\t\t\tdojoAttachPoint="titleNode,focusNode"\n\t\t\trole="button" aria-labelledby="${id}_label"\n\t\t\t><span class="dijitReset dijitInline dijitIcon" dojoAttachPoint="iconNode"></span\n\t\t\t><span class="dijitReset dijitToggleButtonIconChar">&#x25CF;</span\n\t\t\t><span class="dijitReset dijitInline dijitButtonText"\n\t\t\t\tid="${id}_label"\n\t\t\t\tdojoAttachPoint="containerNode"\n\t\t\t></span\n\t\t></span\n\t></span\n\t><\!--no need to have this for Uploader \n\t<input ${!nameAttrSetting} type="${type}" value="${value}" class="dijitOffScreen" tabIndex="-1"\n\t\tdojoAttachPoint="valueNode"\n/--\></span>\n'}});
define("dojox/form/Uploader","dojo/_base/kernel,dojo/_base/declare,dojo/_base/lang,dojo/_base/array,dojo/_base/connect,dojo/_base/window,dojo/dom-style,dojo/dom-geometry,dojo/dom-attr,dojo/dom-construct,dojo/dom-form,dijit,dijit/form/Button,dojox/form/uploader/Base,dojo/i18n!./nls/Uploader,dojo/text!./resources/Uploader.html".split(","),function(l,g,q,e,f,r,b,s,h,i,m,t,j,n,o,p){l.experimental("dojox.form.Uploader");g("dojox.form.Uploader",[n,j],{uploadOnSelect:!1,tabIndex:0,multiple:!1,label:o.label,
url:"",name:"uploadedfile",flashFieldName:"",uploadType:"form",showInput:"",_nameIndex:0,templateString:p,baseClass:"dijitUploader "+j.prototype.baseClass,postMixInProperties:function(){this._inputs=[];this._cons=[];this.inherited(arguments)},buildRendering:function(){this.inherited(arguments);b.set(this.domNode,{overflow:"hidden",position:"relative"});this._buildDisplay();h.set(this.titleNode,"tabIndex",-1)},_buildDisplay:function(){if(this.showInput)this.displayInput=dojo.create("input",{"class":"dijitUploadDisplayInput",
tabIndex:-1,autocomplete:"off"},this.containerNode,this.showInput),this._attachPoints.push("displayInput"),this.connect(this,"onChange",function(a){for(var d=0,c,b=[];c=a[d++];)c&&c.name&&b.push(c.name);this.displayInput.value=b.join(", ")}),this.connect(this,"reset",function(){this.displayInput.value=""})},startup:function(){if(!this._buildInitialized)this._buildInitialized=!0,this._getButtonStyle(this.domNode),this._setButtonStyle(),this.inherited(arguments)},onChange:function(){},onBegin:function(){},
onProgress:function(){},onComplete:function(){this.reset()},onCancel:function(){},onAbort:function(){},onError:function(){},upload:function(){},submit:function(a){a=a?a.tagName?a:this.getForm():this.getForm();this.upload(m.toObject(a))},reset:function(){delete this._files;this._disconnectButton();e.forEach(this._inputs,i.destroy,dojo);this._inputs=[];this._nameIndex=0;this._createInput()},getFileList:function(){var a=[];this.supports("multiple")?e.forEach(this._files,function(d,b){a.push({index:b,
name:d.name,size:d.size,type:d.type})},this):e.forEach(this._inputs,function(b,c){b.value&&a.push({index:c,name:b.value.substring(b.value.lastIndexOf("\\")+1),size:0,type:b.value.substring(b.value.lastIndexOf(".")+1)})},this);return a},_getValueAttr:function(){return this.getFileList()},_setValueAttr:function(){console.error("Uploader value is read only")},_setDisabledAttr:function(a){this._disabled!=a&&(this.inherited(arguments),b.set(this.inputNode,"display",a?"none":""))},_getButtonStyle:function(a){this.btnSize=
{w:b.get(a,"width"),h:b.get(a,"height")}},_setButtonStyle:function(){this.inputNodeFontSize=Math.max(2,Math.max(Math.ceil(this.btnSize.w/60),Math.ceil(this.btnSize.h/15)));this._createInput()},_getFileFieldName:function(){return this.multiple&&this.supports("multiple")?this.name+"s[]":this.name+(this.multiple?this._nameIndex:"")},_createInput:function(){this._inputs.length&&(b.set(this.inputNode,{top:"500px"}),this._disconnectButton(),this._nameIndex++);var a=this._getFileFieldName();this.focusNode=
this.inputNode=i.create("input",{type:"file",name:a},this.domNode,"first");this.supports("multiple")&&this.multiple&&h.set(this.inputNode,"multiple",!0);this._inputs.push(this.inputNode);b.set(this.inputNode,{position:"absolute",fontSize:this.inputNodeFontSize+"em",top:"-3px",right:"-3px",opacity:0});this._connectButton()},_connectButton:function(){this._cons.push(f.connect(this.inputNode,"change",this,function(a){this._files=this.inputNode.files;this.onChange(this.getFileList(a));!this.supports("multiple")&&
this.multiple&&this._createInput()}));if(-1<this.tabIndex)this.inputNode.tabIndex=this.tabIndex,this._cons.push(f.connect(this.inputNode,"focus",this,function(){this.titleNode.style.outline="1px dashed #ccc"})),this._cons.push(f.connect(this.inputNode,"blur",this,function(){this.titleNode.style.outline=""}))},_disconnectButton:function(){e.forEach(this._cons,f.disconnect);this._cons.splice(0,this._cons.length)}});dojox.form.UploaderOrg=dojox.form.Uploader;var k=[dojox.form.UploaderOrg];dojox.form.addUploaderPlugin=
function(a){k.push(a);g("dojox.form.Uploader",k,{})};return dojox.form.Uploader});