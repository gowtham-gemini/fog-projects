define("FogPanel/VerticalMenuBar", [
	"require",
	"dojo/_base/declare", // declare
	"dojo/has",
	"dojo/keys", // keys.LEFT_ARROW keys.RIGHT_ARROW
	"dojo/ready",
	"dijit/_Widget",
        "dijit/form/Button", 
        "dijit/form/Select",
        "dojo/dom-construct", 
        "dojo/dom-class",   
        "dojo/fx",
        "dojo/_base/connect",
        "dojo/request/registry",         
	"dijit/_KeyNavContainer",
	"dijit/_TemplatedMixin",
        "dojo/NodeList-traverse"
], function(require, declare, has, keys, ready, _Widget, Button,Select,  domConstruct, domClass, fx, connect, _KeyNavContainer, _TemplatedMixin) {	
    return declare("FogPanel.VerticalMenuBar", [_Widget, _TemplatedMixin, _KeyNavContainer], {
        templateString:
            '<div class="sidebar-nav" tabIndex="${tabIndex}" data-dojo-attach-point="containerNode">' +
            '</div>',
        baseClass: "sidebar-nav",
        serviceMenuId:"",
        options : false,
        filterOption : false,
        selectOptionContent:"",
        backtoDashboardLabel :"",
        selectBoxValue : "",
        _subscriptionHandle : "",
        __handle : "",
	postCreate: function() {       
            window.widget = this;
            this.inherited(arguments);                       
            var child = dojo.query(".sidebar-nav .dashboard-menu").children();
            var anchorTagList = dojo.query(".sidebar-nav .dashboard-menu li .dropdown-toggle");
            var subMenuList = dojo.query(".sidebar-nav .dashboard-menu li .submenu li a");            
            
            if (this.options === true) {     
                var backButtonNode = domConstruct.toDom('<a class="btn-glow primary new-product overflowLabel back_button">'+this.backtoDashboardLabel+'</a>');
                this.containerNode.appendChild(backButtonNode);
                backButtonNode.onclick = function () {widget.onBackButtonclick()};
                if(this.filterOption === true) {
                    var selectNode = domConstruct.toDom('<div></div>');
                    this.containerNode.appendChild(selectNode);
                    var selectBox = new Select({value: widget.selectBoxValue, store: widget.selectOptionContent, "class" :"vpn_box", onChange: function () {widget.onSelectBoxChange(this)} }, selectNode);
                }                                                
            }            
            dojo.forEach(anchorTagList, function(tagList, index) {
                var sibLink = dojo.query(tagList).next()[0];
                var wipeOutArgs = {
                    node: sibLink,
                    duration: 500,
                    onEnd : function() {
                        domClass.remove(sibLink, "active");
                    }
                };                            
                fx.wipeOut(wipeOutArgs).play(); 
                tagList.onclick = function() {                                
                };
            });                             
        },   
        onServiceMenuItemClick : function () {                        
        },
        onOtherMenuItemClick : function () {
            
        },
        onSelectBoxChange : function () {},
        onBackButtonclick : function () {},
        populateMenu : function (menuContentList) {
            var widget = this;
            var navigatorHTML = "";
            var anchowTagId = "";
            navigatorHTML = "<ul class='dashboard-menu'>";            
            dojo.forEach(menuContentList, function (menuList, index) { 
                var count = index + 1; 
                anchowTagId = "main_menu_" + count;
                if(menuList.action === true) {                    
                    widget.serviceMenuId = anchowTagId;
                    navigatorHTML +=                         
                            "<li>"+                            
                            "<a id="+anchowTagId+" class= 'mainMenu'onclick = "+'widget.onServiceMenuItemClick()'+">"+ 
                            "<i class= "+menuList.iconClass+"></i>" +  
                            "<span>"+ menuList.menuItemName + "</span>"+   
                            "<i class="+menuList.dropdownIcon+"></i>"+
                            "</a>";  
                } else {
                    navigatorHTML +=                         
                            "<li>"+                            
                            "<a id="+anchowTagId+" class= 'mainMenu "+ menuList.aTagClasses +"'href="+ menuList.href +" onclick="+'widget.onOtherMenuItemClick()'+">"+ 
                            "<i class= "+menuList.iconClass+"></i>" +  
                            "<span>"+ menuList.menuItemName + "</span>"+   
                            "<i class="+menuList.dropdownIcon+"></i>"+
                            "</a>";  
                }                                          
                if(menuList.subMenu === true) {                                
                    navigatorHTML += "<ul class='submenu' id='instanceMenu'>";
                    dojo.forEach(menuList.submenuContent, function (innerMenuList) {                                     
                        navigatorHTML += "<li>" + "<a href="+innerMenuList.href+" onclick="+'widget.onOtherMenuItemClick()'+">" +innerMenuList.subMenuItemName+ "</a>"+ "</li>";
                    });
                    navigatorHTML += "</ul>";
                }                            
                navigatorHTML += "</li>";                                                             
            });              
            navigatorHTML += "</ul>";                   
            domConstruct.place(navigatorHTML, window.widget.containerNode);
        },
        onSubMenuItemClick : function(curentSubMenuItem, index) {
            var subMenuList = dojo.query(".sidebar-nav .dashboard-menu li .submenu li a");
            dojo.forEach(subMenuList, function(subMenuItem, count) {
                if(index === count) {
                    domClass.add(subMenuItem, "bolder");
                } else {
                    domClass.remove(subMenuItem, "bolder");
                }                
            });          
        },
        onMenuItemClick : function(currentTag, index) {
            var sibLink = dojo.query(currentTag).next()[0];    
            var parentLink = dojo.query(currentTag).parent()[0];   
            if(sibLink) {
                var sibLinkClass = sibLink.className.split(' ')[1];
                if(sibLinkClass === "active") {    
                    var wipeOutArgs = {
                        node: sibLink,
                        duration: 500,
                        onEnd : function() {
                            domClass.remove(sibLink, "active");
                            domClass.remove(parentLink, "active"); 
                        }
                    };                            
                    fx.wipeOut(wipeOutArgs).play();                          
                } else {
                    domClass.add(sibLink, "active");
                    domClass.add(parentLink, "active"); 
                    var wipeInArgs = {
                        node: sibLink,
                        duration: 500                        
                    };                                                   
                    fx.wipeIn(wipeInArgs).play();                   
                }
            } 
            
            var anchorTagList = dojo.query(".sidebar-nav .dashboard-menu li .dropdown-toggle");
            dojo.forEach(anchorTagList, function(tagList, count) {
                if(count !== index) {
                    var sibLink = dojo.query(tagList).next()[0];
                    var otherparentLink = dojo.query(tagList).parent()[0];   
                    domClass.remove(otherparentLink, "activeMenu");
                    domClass.remove(otherparentLink, "active");                     
                    
                    if(sibLink) {
                        var wipeOutArgs = {
                            node: sibLink,
                            duration: 500,
                            onEnd : function() {
                                domClass.remove(sibLink, "active");
//                                domClass.remove(parentLink, "active"); 
                            }
                        };                            
                        fx.wipeOut(wipeOutArgs).play(); 
                    } 
                } else {
                    var currentparentLink = dojo.query(tagList).parent()[0];   
                    domClass.add(currentparentLink, "active");
                }
            });                
            var subMenuItemList = dojo.query(".sidebar-nav .dashboard-menu li .submenu li a");
            dojo.forEach(subMenuItemList, function(el) {  
                domClass.remove(el, "bolder");
            });
            
            var mainMenuTagList = dojo.query(".sidebar-nav .dashboard-menu li .singleMenu");
            dojo.forEach(mainMenuTagList, function(mainMenu) {
//                var noSibLink = dojo.query(mainMenu).next()[0];
                var mainParentLink = dojo.query(mainMenu).parent()[0]; 
//                if(!noSibLink) {
                    domClass.remove(mainParentLink, "active");
//                }
            });
        },
        onMainMenuItemClick : function(currentTag) {
            var menuList = dojo.query(".dashboard-menu a");
            dojo.forEach(menuList, function(currentTagItem, count) {
                if(currentTag.hash === currentTagItem.hash) {                    
                    var parentNodeList = dojo.query(currentTagItem).parent()[0];                      
                    domClass.add(parentNodeList, "active"); 
                } else {
                    var sibLink = dojo.query(currentTagItem).next()[0];
                    var parentLink = dojo.query(currentTagItem).parent()[0];   
                    if(sibLink) {
                        var wipeOutArgs = {
                            node: sibLink,
                            duration: 500,
                            onEnd : function() {
                                domClass.remove(sibLink, "active");
                                domClass.remove(parentLink, "active"); 
                            }
                        };                            
                        fx.wipeOut(wipeOutArgs).play();     
                    } else {
                        var otherParentNodeList = dojo.query(currentTagItem).parent()[0];                      
                        domClass.remove(otherParentNodeList, "active"); 
                    }
                }               
            })            
        },
        highLightSelectedMenuItem : function(url) {
            var menuList = dojo.query(".dashboard-menu a");
            var anchorTagList = dojo.query(".sidebar-nav .dashboard-menu li .dropdown-toggle");
            dojo.forEach(menuList, function(menuItem, mainCount) {               
                if(menuItem.hash === url) {
                    var BaseMenuItemClassName  = menuItem.className.split(" ");
                    if(BaseMenuItemClassName[0] === "mainMenu") {// Parent Menu 
                        var sibLink = dojo.query(menuItem).next()[0];
                        if(sibLink) {                            
                            dojo.forEach(anchorTagList, function(tagList, index) {                                
                                if(tagList.hash === url) {                                                            
                                    widget.onMenuItemClick(tagList, index);                        
                                }                              
                            });
                        } else {
                            widget.onMainMenuItemClick(menuItem);
                        }
                    } else {
                        var subMenuList = dojo.query(".sidebar-nav .dashboard-menu li .submenu li a");
                        dojo.forEach(subMenuList, function(subListItem, count) {
                            if(subListItem.hash === url) {
                                var parentNodeList = dojo.query(subListItem).parent().parent()[0];                          
                                var parentNode = dojo.query(parentNodeList).prev()[0];
                                dojo.forEach(anchorTagList, function(tagList, index) {                               
                                    if(tagList.hash === parentNode.hash) {
                                        var subMenuList = parentNodeList.className.split(" ");
                                        var grandParentNode = dojo.query(parentNodeList).parent()[0];                                            
                                        if(subMenuList[1] === "active") {                                            
                                            widget.onSubMenuItemClick(subListItem, count);                                             
//                                            domClass.add(grandParentNode, "active");
                                        } else {                                        
                                            domClass.add(parentNodeList, "active");
                                            var wipeInArgs = {
                                                node: parentNodeList,
                                                duration: 500,
                                                onBegin: function() {                           
                                                }
                                            };                                                   
                                            fx.wipeIn(wipeInArgs).play();  
                                            widget.onSubMenuItemClick(subListItem, count); 
                                            domClass.add(grandParentNode, "active");
                                        }            
                                    } else {
                                        var sibLinkNode = dojo.query(tagList).next()[0]; 
                                        var otherParentNode = dojo.query(sibLinkNode).parent()[0];
                                        domClass.remove(otherParentNode, "active");  
                                        domClass.remove(sibLinkNode, "active");                                                                                
                                    }                                                           
                                });                            
                            }       
                        });
                    }                    
                }
            });
        },
        onLiClick : function(currentLi, index) {                    
            var child = dojo.query(".sidebar-nav .dashboard-menu").children();
            dojo.forEach(child, function(liNode, count) { 
                if(count === index) {
                    domClass.add(liNode, "active");  
                } else {
                    domClass.remove(liNode, "active"); 
                }                        
            });  
            var activeMenuSubItemList = dojo.query(".sidebar-nav .dashboard-menu .submenu").children();
            dojo.forEach(activeMenuSubItemList, function(el, index) {
                el.onclick = function() {                          
                    widget.onSubLinkClick(this, index);
                };
            });
        },
        onSubLinkClick: function(currentSubMenuItem, index) {   
            var activeMenuSubItemList = dojo.query(".sidebar-nav .dashboard-menu .submenu").children();
            dojo.forEach(activeMenuSubItemList, function(el, count) {
                var child = el.childNodes;
                if(index === count) {
                    domClass.add(child[0], "bolder"); 
                } else {
                    domClass.remove(child[0], "bolder"); 
               }
           }); 
       },       
       subscribe: function(path) {                    
           widget._subscriptionHandle = connect.subscribe("/FogPanel/event/route/changed", function(data) {
               widget.highLightSelectedMenuItem(data);
           });
       },       
       unsubscribe: function() {
           connect.unsubscribe(widget._subscriptionHandle);
       },
       disable : function() {     
           domClass.add(this.containerNode, "disabled");
       },
       enable : function() {
           domClass.remove(this.containerNode, "disabled");
       }
   });
});
