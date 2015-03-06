//>>built
define("dojox/data/StoreExplorer",["dijit","dojo","dojox","dojo/require!dojox/grid/DataGrid,dojox/data/ItemExplorer,dijit/layout/BorderContainer,dijit/layout/ContentPane"],function(i,d,m){d.provide("dojox.data.StoreExplorer");d.require("dojox.grid.DataGrid");d.require("dojox.data.ItemExplorer");d.require("dijit.layout.BorderContainer");d.require("dijit.layout.ContentPane");d.declare("dojox.data.StoreExplorer",i.layout.BorderContainer,{constructor:function(a){d.mixin(this,a)},store:null,columnWidth:"",
stringQueries:!1,showAllColumns:!1,postCreate:function(){function a(e,a){var b=new i.form.Button({label:e});l.containerNode.appendChild(b.domNode);b.onClick=a;return b}var c=this;this.inherited(arguments);var l=(new i.layout.ContentPane({region:"top"})).placeAt(this),g=l.containerNode.appendChild(document.createElement("span"));g.innerHTML="Enter query: &nbsp;";g.id="queryText";var k=l.containerNode.appendChild(document.createElement("input"));k.type="text";k.id="queryTextBox";a("Query",function(){var e=
k.value;c.setQuery(c.stringQueries?e:d.fromJson(e))});l.containerNode.appendChild(document.createElement("span")).innerHTML="&nbsp;&nbsp;&nbsp;";var p=a("Create New",d.hitch(this,"createNew")),q=a("Delete",function(){for(var e=b.selection.getSelected(),a=0;a<e.length;a++)c.store.deleteItem(e[a])});this.setItemName=function(a){p.attr("label","<img style='width:12px; height:12px' src='"+d.moduleUrl("dijit.themes.tundra.images","dndCopy.png")+"' /> Create New "+a);q.attr("label","Delete "+a)};a("Save",
function(){c.store.save({onError:function(a){alert(a)}});c.tree.refreshItem()});a("Revert",function(){c.store.revert()});a("Add Column",function(){var a=prompt("Enter column name:","property");a&&(c.gridLayout.push({field:a,name:a,formatter:d.hitch(c,"_formatCell"),editable:!0}),c.grid.attr("structure",c.gridLayout))});var g=(new i.layout.ContentPane({region:"center"})).placeAt(this),b=this.grid=new m.grid.DataGrid({store:this.store});g.attr("content",b);b.canEdit=function(a,b){var c=this._copyAttr(b,
a.field);return!(c&&"object"==typeof c)||c instanceof Date};var g=(new i.layout.ContentPane({region:"trailing",splitter:!0,style:"width: 300px"})).placeAt(this),f=this.tree=new m.data.ItemExplorer({store:this.store});g.attr("content",f);d.connect(b,"onCellClick",function(){var a=b.selection.getSelected()[0];f.setItem(a)});this.gridOnFetchComplete=b._onFetchComplete;this.setStore(this.store)},setQuery:function(a,c){this.grid.setQuery(a,c)},_formatCell:function(a){return this.store.isItem(a)?this.store.getLabel(a)||
this.store.getIdentity(a):a},setStore:function(a){function c(a){return d._formatCell(a)}this.store=a;var d=this,g=this.grid;g._pending_requests[0]=!1;var k=this.gridOnFetchComplete;g._onFetchComplete=function(i,m){var b=d.gridLayout=[],f,e,j,h,n;f=a.getIdentityAttributes();for(j=0;j<f.length;j++)e=f[j],b.push({field:e,name:e,_score:100,formatter:c,editable:!1});for(j=0;e=i[j++];){var r=a.getAttributes(e);for(n=0;e=r[n++];){var o=!1;for(h=0;f=b[h++];)if(f.field==e){f._score++;o=!0;break}o||b.push({field:e,
name:e,_score:1,formatter:c,styles:"white-space:nowrap; ",editable:!0})}}b=b.sort(function(a,b){return b._score-a._score});if(!d.showAllColumns)for(h=0;f=b[h];h++)if(f._score<i.length/40*h){b.splice(h,b.length-h);break}for(h=0;f=b[h++];)f.width=d.columnWidth||Math.round(100/b.length)+"%";g._onFetchComplete=k;g.attr("structure",b);k.apply(this,arguments)};g.setStore(a);this.queryOptions={cache:!0};this.tree.setStore(a)},createNew:function(){var a=prompt("Enter any properties (in JSON literal form) to put in the new item (passed to the newItem constructor):",
"{ }");if(a)try{this.store.newItem(d.fromJson(a))}catch(c){alert(c)}}})});