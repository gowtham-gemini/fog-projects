define("FogPanel/Navigator", [
	"require",
	"dojo/_base/declare", // declare
	"dojo/has",
	"dojo/keys", // keys.LEFT_ARROW keys.RIGHT_ARROW
	"dojo/ready",
	"dijit/_Widget",
        "dojo/dom-class",
	"dijit/_KeyNavContainer",
	"dijit/_TemplatedMixin",
        "dojo/NodeList-traverse"
], function(require, declare, has, keys, ready, _Widget, domClass, _KeyNavContainer, _TemplatedMixin){	
	return declare("FogPanel.Navigator", [_Widget, _TemplatedMixin, _KeyNavContainer], {
		
		templateString:
			'<div class="navbar-inner" tabIndex="${tabIndex}" data-dojo-attach-point="containerNode">' +
                        '<button type="button" class="btn btn-navbar visible-phone" data-dojo-attach-point="menuContainer">' +
                        '<span class="icon-bar"></span>'+
                        '<span class="icon-bar"></span>'+
                        '<span class="icon-bar"></span>'+
                        '</button>'+
			'</div>',

		baseClass: "navbar-inner",
                verticalManuBarId:"",
		postCreate: function() {
                    var widget = this;
//                    widget.onToggleButtonClick();
			this.inherited(arguments);   
                        this.menuContainer.onclick = function() {
                           widget.onToggleButtonClick();
                        };
//                        widget.setLogo();
		},
                setLogo : function(logo, url) {                    
//                    logo.src = url;
                },
                disableNavigator: function() {                    
                    var buttonList = dojo.query(".navbar-inner .dijitToolbar .dashboardContent");
                    dojo.forEach(buttonList, function(el, index) {                 
                            domClass.add(el, "disable");                                               
                    });        
                },
                enableNavigator: function() {
                    dojo.query(".navbar-inner .dijitToolbar .dashboardContent").forEach(function(el) {
                        domClass.remove(el, "disable");
                    });        
                },
                
                onToggleButtonClick : function() {                   
                    var widget = this;    
                    var navDiv = dojo.byId(widget.id)                               
                    var parentNav = dojo.query(navDiv).parent()[0];                      
                    var sideMenuBar = dojo.query(parentNav).next()[0];                        
                    sideMenuBar.onclick = function() {  
                        widget.onToggleButtonClick();
                    }
                    var varticalMenuBar = dojo.byId(sideMenuBar.id);        
                    var newClass = "";
                    newClass = varticalMenuBar.className.split(' ')[1]; // first class
                    if(newClass == "display") {
                        domClass.remove(varticalMenuBar, "display");                     
                    } else if(newClass == "" || newClass == undefined || newClass != "display") {
                        domClass.add(varticalMenuBar, "display");
                    }                  
                }	
            });
        });
