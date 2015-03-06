//Closure for architecture runs only once when the script is included
(function() {
    "use strict";
    //App init Logic
    var appInit = function() {
        require([
            "dojo/dom",
            "dojo/parser",
            "dojo/router",
            "dijit/registry",
            "dojo/topic",
            "dojo/request",
            "dojo/store/JsonRest",
            "dojo/_base/connect",
            "dijit/layout/BorderContainer",
            "dijit/layout/ContentPane",
            "dojox/layout/ContentPane",
            "dojox/widget/Toaster",
            "dojo/request/script",
            "dojo/json",
            "dijit/Tooltip",
            "dojox/dtl",
            "dojox/dtl/Context",
            "dojo/request/xhr",
            "dijit/Dialog",
            "dojo/dom-form",
            "dijit/TitlePane",
            "dijit/form/Form",
            "dijit/form/TextBox",
            "dijit/form/CheckBox",
            "dijit/form/NumberTextBox",
            "dijit/form/ValidationTextBox",
            "dijit/form/Button",
            "dojox/grid/EnhancedGrid",
            "dojox/grid/enhanced/plugins/Pagination",
            "dojox/grid/enhanced/plugins/Search",
            "dojox/grid/enhanced/plugins/Filter",
            "dojo/data/ItemFileWriteStore",
            "FogPanel/Navigator",
            "FogPanel/VerticalMenuBar",
            "FogPanel/Notification",
            "FogPanel/Wizard",
            "dijit/Toolbar",
            "dijit/form/DropDownButton",
            "dijit/layout/TabContainer",
            "dijit/TooltipDialog",
            "dijit/form/ValidationTextBox",
            "dijit/form/TextBox",
            "dojox/validate/regexp",
            "dijit/form/SimpleTextarea",
            "dijit/form/FilteringSelect",
            "dijit/form/Button",
            "dojox/form/PasswordValidator",
            "dojo/query",
            "dojox/widget/Toaster",
            "dojo/NodeList-manipulate",
            "dojo/NodeList-fx",
            "dojox/grid/enhanced/plugins/Menu",
            "dijit/popup"
        ], function(dom, parser, router, registry, topic, request, rest, connect) {
            parser.parse();
            appToaster = registry.byId("appToaster");

            route.init(router);

            _request = request;

            _enableSecurity(connect);

            if (!window.location.hash) {
                router.go(core.getHomepage());
            }

            ui.hideAppLoader();

            _rest = rest;
        });
    };

    var _request = null;
    var _rest = null;
    var inherit = function(prototype, object) {

        var newObject = Object.create(prototype);

        for (var prop in object) {
            if (object.hasOwnProperty(prop)) {
                newObject[prop] = object[prop];
            }
        }

        return newObject;
    };

    var action = function(fn) {
        //Mark it is a router function
        fn.isAction = true;
        //Write code for hash change when this action function is called through javascript
        return fn;
    };


    var controller = function(args, fn) {

        if (args.scaffold == true) {
            fn = scaffold(args, fn);
        }

        //Register the controller and notify 
        core.router.register(args.module, args.name, fn, args.layout);
        core.plugin.notify(args.filePath);

        window[args.name] = fn;
    };


    var _getNotProcessedRestAttachment = function() {
        var currentAttachment = null;
        for (var key in _attachRestData) {
            if (!_attachRestData[key].status || _attachRestData[key].status == "NOT_PROCESSED") {
                _attachRestData[key].status = "IN_PROGRESS";
                currentAttachment = _attachRestData[key];
                break;
            }
        }
        return currentAttachment;
    };

    var _resetRestAttachment = function() {
        for (var key in _attachRestData) {
            if (_attachRestData[key].status || _attachRestData[key].status == "PROCESSED") {
                _attachRestData[key].status = "NOT_PROCESSED";
            }
        }
    };

    var _attachProcessedResponse = function(data) {
        var currentAttachment = null;

        for (var key in _attachRestData) {
            if (_attachRestData[key].status && _attachRestData[key].status == "IN_PROGRESS") {
                _attachRestData[key].data = data;
                _attachRestData[key].status = "PROCESSED";
                break;
            }
        }
        return currentAttachment;
    };

    var _attachRestData = null;

    var _processRestAttachments = function(args, fparam, entity) {
        var currentAttachment = _getNotProcessedRestAttachment();
        if (currentAttachment != null) {
            ajax(currentAttachment.restUrl, "GET", currentAttachment.requestParams ? currentAttachment.requestParams : "").then(
                    function(data) {
                        _attachProcessedResponse(data);
                        _processRestAttachments(args, fparam, entity);
                    },
                    function(error) {
                        console.log("An error occured when accessing the rest url :" + args.attachRestData[rest]);
                    }
            );
        }
        else {
            var data = {};
            for (var key in _attachRestData) {
                if (_attachRestData[key].status && _attachRestData[key].status == "PROCESSED") {
                    data[_attachRestData[key].name] = _attachRestData[key].data;
                }
            }
            _resetRestAttachment();
            var containerId = core.ui.getContentId();
            core.ui.loadTemplate(args.name + "_form", containerId, {
                'fparam': fparam,
                'rest': data,
                'entity': entity
            });
        }
    };


    var scaffold = function(args, fn) {

        var containerId = core.ui.getContentId();

        if (args.layout) {
            if (args.layout.containerId) {
                containerId = args.layout.containerId;
            }
            else {
                alert("You must have container id declared in the layout");
            }
        }

        //Validate the args	

        //Create new rest entity if not exists
        if (!fn.rest) {
            //alert(baseConfig.baseURL + args.scaffoldParams.restUrl);
            fn.rest = new _rest({
                target: baseConfig.baseURL + args.scaffoldParams.restUrl
            });
        }

        //Create add action if not exists
        if (!fn.add) {
            fn.add = action(function() {
                var fparam = null;

                if (args.scaffoldParams.attachFormParams) {
                    fparam = args.scaffoldParams.attachFormParams();
                }

                if (args.scaffoldParams.attachRestData) {
                    _attachRestData = args.scaffoldParams.attachRestData;
                    _processRestAttachments(args, fparam);
                }
                else {
                    core.ui.loadTemplate(args.name + "_form", containerId, {
                        fparam: fparam
                    });
                }


            });
        }

        //Create edit action if not exists
        if (!fn.edit) {
            fn.edit = action(function(id) {
                var fparam = null;
                if (args.scaffoldParams.attachFormParams) {
                    fparam = args.scaffoldParams.attachFormParams();
                }

                if (args.scaffoldParams.attachRestData) {
                    _attachRestData = args.scaffoldParams.attachRestData;
                    this.rest.get(id).then(function(data) {
                        _processRestAttachments(args, fparam, data);
                    });
                }
                else {
                    this.rest.get(id).then(function(data) {
                        core.ui.loadTemplate(args.name + "_form", containerId, {
                            'entity': data,
                            'fparam': fparam
                        });
                    });
                }

            });
        }

        //Create remove action method if not exists
        if (!fn.remove) {
            fn.remove = action(function(id) {
                //core.ui.showConfirmDialog("Confirm", "Are you sure want to remove?").then(function(data) {
                //console.log(data);
                if (confirm("Are you sure want to remove?")) {
                    this.rest.remove(id).then(function(result) {
                        core.router.go("/" + args.module + "/" + args.name + "/index");
                        core.ui.showInfo("Removed Successfuly");
                    }, function(error) {
                        core.ui.showDialog("Failure", "Removing object failed").then(function(data) {
                            //console.log(data);
                        });
                    });
                }
                //});

            });
        }

        //Create index action if not exists
        if (!fn.index) {
            fn.index = action(function() {
                var deferred = this.rest.query();

                deferred.then(
                        function(data) {
                            core.ui.loadTemplate(args.name + "_index", containerId);
                            ui.renderList(args.scaffoldParams.listDetails.id, args.scaffoldParams.listDetails, data);
                        },
                        function(error) {
                            alert("An error occured: " + error);
                        }
                );
            });
        }

        //Create save method if not exists
        if (!fn.save) {
            fn.save = function(id) {
                var formObj = dijit.byId(args.name + "Form");

                if (formObj.validate()) {

                    var formData = null;

                    if (args.scaffoldParams.manipulateFormData) {
                        formData = args.scaffoldParams.manipulateFormData();
                    }
                    else {
                        formData = dojo.formToObject(args.name + "Form");
                        if (args.scaffoldParams.booleanFields) {
                            formData = form.convertBooleanValues(formData, args.scaffoldParams.booleanFields);
                        }
                    }

                    if (id) {
                        this.rest.put(formData).then(
                                function(data) {
                                    _handleSaveResponse(data, args);
                                });
                    } else {
                        this.rest.post(formData).then(
                                function(data) {
                                    _handleSaveResponse(data, args);
                                });
                    }
                }
            };
        }
        return fn;
    };

    var _enableSecurity = function(connect) {
        
        connect.subscribe("/dojo/io/done", function(/*dojo.Deferred*/ dfd, /*Object*/ response){
            return dfd.then(function(response) {
                return response;
            }, function(error) {
                if (dfd.ioArgs.xhr.status === 401) {
                    alert("Your session has expired");
                    window.location = "/FogPanel/";
                }
            });


        });        
        
    };

    //private method to handle save method response
    var _handleSaveResponse = function(data, args) {

        if (data) {
            form.showFieldErrors(data);
        }
        else {
            //console.log("/" + args.plugin + "/" + args.module + "/" + args.name + "/index");
            core.router.go("/" + args.module + "/" + args.name + "/index");
            core.ui.showInfo("Saved Successfuly");
        }
    };

    /**
     * Renders a dojo enhanced grid
     * 
     * id - the id attribute where the list has to render
     * config - the configuration details which is required to render the enhanced grid
     * data - data from rest services to form the grid
     * 
     */
    var renderList = function(id, config, data) {

        var list = {
            'identifier': config.listConfig.identifier,
            'items': data
        };

        var store = new dojo.data.ItemFileWriteStore({
            'data': list
        });

        var grid = new dojox.grid.EnhancedGrid({
            'id': 'list',
            'store': store,
            'structure': config.layout,
            'rowSelector': config.listConfig.rowSelector,
            'escapeHTMLInData': config.listConfig.escapeHTMLInData,
            'plugins': {
                'pagination': {
                    'pageSizes': config.listConfig.plugins.pagination.pageSizes,
                    'description': config.listConfig.plugins.pagination.description,
                    'sizeSwitch': config.listConfig.plugins.pagination.sizeSwitch,
                    'pageStepper': config.listConfig.plugins.pagination.pageStepper,
                    'gotoButton': config.listConfig.plugins.pagination.gotoButton,
                    'maxPageStep': config.listConfig.plugins.pagination.maxPageStep,
                    'position': config.listConfig.plugins.pagination.position
                }
            }
        }, document.createElement('div'));

        dojo.byId(id).appendChild(grid.domNode);
        grid.startup();
    };

    var listConfig = {
        'identifier': 'id',
        'rows': 60,
        'rowSelector': '20px',
        'escapeHTMLInData': false,
        'plugins': {
            'pagination': {
                'pageSizes': ["25", "50", "75", "All"],
                'description': true,
                'sizeSwitch': true,
                'pageStepper': true,
                'gotoButton': true,
                'maxPageStep': 4, // page step to be displayed		                      
                'position': "bottom" //position of the pagination bar
            }
        }
    };

    //UI related variables
    var templateAreaId = "templateArea";
    var contentId = "content";
    var appToaster = null;

    /**
     * Object for UI related tasks
     * 
     */
    var ui = {
        'listConfig': listConfig,
        'renderList': function(id, config, data) {
            config.listConfig = inherit(listConfig, config.listConfig);
            renderList(id, config, data);
        },
        'getTemplateAreaId': function() {
            return templateAreaId;
        },
        'getContentId': function() {
            return contentId;
        },
        'showAppLoader': function() {
            dojo.byId("screen-loader").style.display = "block";
        },
        'hideAppLoader': function() {
            dojo.byId("screen-loader").style.display = "none";
        },
        'showInfo': function(message) {
            appToaster.setContent(message, 'message');
        },
        'showWarning': function(message) {
            appToaster.setContent(message, 'warning');
        },
        'showError': function(message) {
            appToaster.setContent(message, 'error');
        },
        'showDialog': function(title, content, options) {
            var dialog = new dijit.Dialog({
                'title': title,
                'content': content,
                'style': "width: 300px"
            });

            var deferred = new dojo.Deferred();
            dialog.on('hide', function() {
                dialog.destroy();
                deferred.resolve("OK");
            });

            dialog.show();
            return deferred;
        },
        'showConfirmDialog': function(title, content, options) {

            //			var dialog = new ConfirmDialog({
            //			    title: title,
            //			    message: content       
            //			});	    	 
            //	    	 var deferred = new dojo.Deferred();
            //	    	 	dialog.on('hide', function() {
            //	    	 		 dialog.destroyRecursive();
            //	    	 });
            //	    	 
            //	    	 dialog.on('hide', function() {
            //	    		 dialog.destroyRecursive();
            //	    		 deferred.cancel("CANCELLED");
            //	    	 });
            //	    	 
            //	    	 dialog.show();
            //	    	 return deferred;

            if (confirm(content)) {

            }
            else {

            }
        },
        /**
         * Creates a dojo button
         *  
         * @param label
         * @param url
         * @returns {dijit.form.Button}
         */
        'createButton': function(label, url) {
            var button = new dijit.form.Button({
                'label': label,
                'showLabel': true,
                'iconClass': "dijitEditorIcon dijitEditorIconSelectAll",
                'class': 'gridButton',
                'onClick': function() {
                    document.location.href = url;
                }
            });
            return button;
        },
        /**
         * Loads the given template id to the given container object, It also parse the given jsonData to the template.
         * 
         * @param templateId
         * @param containerId
         * @param jsonData
         */
        'loadTemplate': function(templateId, containerId, jsonData) {
            //alert(ui.getTemplateAreaId());
            var contentId = core.ui.getContentId();

            //this.clearContent(containerId);			        	

            if (_currentLayout && dojo.byId(_currentLayout.containerId) == null) {
                //console.log(_currentLayout);
                var layoutTemp = dojo.byId(_currentLayout.name);
                var dtlLayoutTemp = new dojox.dtl.Template(layoutTemp.innerHTML);

                var layoutContainer = dojo.byId(contentId);
                if (_currentLayout.param) {
                    var layoutContext = new dojox.dtl.Context(_currentLayout.param);
                    layoutContainer.innerHTML = dtlLayoutTemp.render(layoutContext);
                }
                else {
                    layoutContainer.innerHTML = dtlLayoutTemp.render();
                }
                dojo.parser.parse(layoutContainer);

                if (!containerId) {
                    containerId = _currentLayout.containerId;
                }
            }

            if (!containerId) {
                containerId = contentId;
            }

            var container = dojo.byId(containerId);

            var template = dojo.byId(templateId);

            var dtlTemplate = new dojox.dtl.Template(template.innerHTML);

            if (jsonData) {
                var context = new dojox.dtl.Context(jsonData);
                container.innerHTML = dtlTemplate.render(context);
            }
            else {
                container.innerHTML = dtlTemplate.render();
            }

            //console.log(container.innerHTML);
            dojo.parser.parse(container);
        },
        /**
         * Loads the page related to the url in the given container id
         * 
         * @param url
         * @param containerId
         */
        'loadPage': function(url, containerId) {
            var container = dojo.byId(containerId);
            var xhrArgs = {
                'url': url,
                'handleAs': "text",
                'load': function(html) {
                    container.innerHTML = html;
                },
                'error': function(error) {
                    alert("An unexpected error occurred: " + error);
                }
            };

            // Call the asynchronous xhrGet
            var deferred = dojo.xhrGet(xhrArgs);
        },
        'loadCss': function(url) {
            var elem = document.createElement("link");

            elem.href = url;
            elem.type = "text/css";
            elem.rel = "stylesheet";
            elem.media = "screen";

            document.getElementsByTagName("head")[0].appendChild(elem);
        },
        /**
         * Clears all the widgets associated with the given container id.
         * 
         * @param containerId
         */
        'clearContent': function(containerId) {
            var content = dojo.byId(containerId);
            var widgets = dijit.findWidgets(content);
            dojo.forEach(widgets, function(w) {
                w.destroyRecursive(true);
            });
        },
        'getRadioButtonValue': function(dojoWidgetName) {
            var checkedButtons = dojo.query('[name=' + dojoWidgetName + ']').filter(function(radio) {
                return radio.checked;
            });
            return checkedButtons.attr("value")[0];
        }
    };

    var form = {
        /**
         * Method which converts the boolean values from form data
         * 
         * A boolen value from for data was converted as string from dojo formtoObject method
         * 		"true", "on", "1"
         * to make it as a proper json object acceptable for REST services, we have to convert it to boolean true.
         * 
         */
        'convertBooleanValues': function(formData, booleanFields /*Array*/) {
            for (var key in formData) {
                if (booleanFields.indexOf(key) > -1) {
                    if (formData[booleanFields[booleanFields.indexOf(key)]] == "true" ||
                            formData[booleanFields[booleanFields.indexOf(key)]] == "on" ||
                            formData[booleanFields[booleanFields.indexOf(key)]] == "1") {
                        formData[booleanFields[booleanFields.indexOf(key)]] = true;
                    }
                    else {
                        formData[booleanFields[booleanFields.indexOf(key)]] = false;
                    }
                }
            }
            return formData;
        },
        'showFieldErrors': function(errors) {
            for (var i = 0; i < errors.length; i++) {
                var textBox = dijit.byId(errors[i].field);

                var originalValidator = textBox.validator;
                textBox.validator = function() {
                    return false;
                };
                textBox.validate();
                textBox.validator = originalValidator;

                dijit.showTooltip(errors[i].defaultMessage, textBox.domNode, textBox.get('tooltipPosition'));
            }
            core.ui.showError("You have errors in the form");
        }
    };

    var scriptDependencies = [];

    var isValueExistsInArray = function(arr, value) {

        var result = false;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                result = true;
                break;
            }
        }
        //console.log(arr);
        //console.log("Condition : " + value + " Result: " + result);

        return result;
    };

    var removeArrayValue = function(arr, value) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                arr.splice(i, 1);
            }
        }
    };

    var subscribeHandler = null;

    /**
     * 
     */
    var plugin = {
        'load': function(path) {
            //console.log("Load: " + path);		
            require(["dojo/text!" + baseConfig.baseURL + "/" + path], function(template) {
                dojo.byId(ui.getTemplateAreaId()).innerHTML = template;
                plugin.postPluginContentLoad();
            });
        },
        'postPluginContentLoad': function() {
            if (subscribeHandler) {
                subscribeHandler.remove();
            }

            var list = dojo.query("#" + ui.getTemplateAreaId() + " script[src]");
            var count = 0;

            dojo.forEach(list, function(script) {
                count++;
                scriptDependencies.push(script.src);

                console.log("Downloading Script - " + script.src);
                require(["dojo/request/script"], function(request) {
                    request.get(script.src).then(function(data) {
                        // This function is called when the library is successfully loaded
                        // You should place your application code that depends on the library here
                    }, function(err) {
                        // This function is called if an error occurs
                        alert("TODO: Script Error");
                    });
                });

            });

            var cssList = dojo.query("#" + ui.getTemplateAreaId() + " link[href]");
            var cssCount = 0;

            dojo.forEach(cssList, function(url) {
                cssCount++;
                //console.log(url.href);
                ui.loadCss(url.href);


            });

            subscribeHandler = dojo.subscribe("/core/request/scripts/", function(name) {

                var url = baseConfig.baseURL + name;

                if (isValueExistsInArray(scriptDependencies, url)) {
                    removeArrayValue(scriptDependencies, url);
                    //console.log("Download completed marked: " + name);
                }
                else {
                    console.log("Bad scenario: " + url);
                    //console.log(scriptDependencies);
                }

                if (scriptDependencies.length == 0) {
                    //console.log("Calling router");
                    core.router.execute();
                }

            });
        },
        'notify': function(name) {
            //console.log("Notify : " + name);
            dojo.publish("/core/request/scripts/", name);
        }
    };

    //Route related variables
    var appRoutes = [];
    var currentRouterEvent = null;
    var currentPlugin = "";
    var currentModule = "";
    var appRouter = null;
    var _currentLayout = null;

    var route = {
        /**
         *
         * @param moduleName String
         * @param controllerName String
         * @param handlerObject Object
         */
        'register': function(moduleName, controllerName, handlerObject, layout) {
            //console.log("Regsiter: " + pluginName + " " + controllerName);
            if (!moduleName) {
                moduleName = "!";
            }
            if (!appRoutes[moduleName]) {
                appRoutes[moduleName] = new Object();
            }

            appRoutes[moduleName][controllerName] = handlerObject;
            appRoutes[moduleName][controllerName]._layout = layout;

        },
        /**
         *
         */
        'unregister': function(moduleName, controllerName) {
            alert("Find a scenario");

            if (moduleName && !controllerName) {
                delete appRoutes[moduleName];
            } else if (moduleName) {
                delete appRoutes[moduleName][controllerName];
            }
        },
        'execute': function() {

            if (currentRouterEvent) {

                var moduleName = currentRouterEvent.params.module;
                var controllerName = currentRouterEvent.params.controller;
                var actionName = currentRouterEvent.params.action ? currentRouterEvent.params.action : "index";
                var id = currentRouterEvent.params.id;

                if (!moduleName || !controllerName) {
                    alert("Module or controller missing");
                    return;
                }

                if (appRoutes[moduleName] &&
                        appRoutes[moduleName][controllerName] &&
                        appRoutes[moduleName][controllerName][actionName]) {

                    currentModule = moduleName;

                    if (appRoutes[moduleName][controllerName][actionName].isAction) {
                        _currentLayout = appRoutes[moduleName][controllerName]._layout;
                        appRoutes[moduleName][controllerName][actionName](id);
                    }
                    else {
                        _currentLayout = null;
                        alert("Not a registered action");
                    }
                }
                else {
                    alert(" - Controller/action could not be matched in the registered route ");
                }
            }
            else {
                alert("No Route Event");
            }
        },
        'go': function(url) {
            appRouter.go(url);
        },
        'init': function(router) {
            //Should have register route per module
            //TODO: Fire by module
            appRouter = router;

            var routePath = function(evt) {
                //console.log("Called RoutePath");		              
                var template = "{module}/";
                if (!evt.params.module || evt.params.module == "" || evt.params.module == "!") {
                    template = "default/";
                }

                currentRouterEvent = evt;

                if (currentRouterEvent.params.controller == "") {
                    delete currentRouterEvent.params.controller;
                }

                if (currentRouterEvent.params.action == "") {
                    delete currentRouterEvent.params.action;
                }

                if (currentRouterEvent.params.module == "") {
                    currentRouterEvent.params.module = "!";
                }

                if (currentPlugin == "" || currentModule == "") {
                    //console.log("Everything Empty");
                    currentPlugin = evt.params.plugin;
                    plugin.load(dojo.replace(template, evt.params));
                }
                else if (currentPlugin != currentRouterEvent.params.plugin ||
                        currentModule != currentRouterEvent.params.module) {
                    currentPlugin = evt.params.plugin;
                    //console.log("Everything Not Equal");
                    plugin.load(dojo.replace(template, evt.params));
                }
                else {
                    //console.log("Call Route Execution");
                    route.execute();
                }

            };

            router.register("/", function(evt) {
                //window.location = "/";
            });

            router.register("/:module", function(evt) {
                routePath(evt);
            });

            router.register("/:module/", function(evt) {
                routePath(evt);
            });

            router.register("/:module/:controller", function(evt) {
                routePath(evt);
            });

            router.register("/:module/:controller/", function(evt) {
                routePath(evt);
            });

            router.register("/:module/:controller/:action", function(evt) {
                routePath(evt);
            });

            router.register("/:module/:controller/:action/", function(evt) {
                routePath(evt);
            });

            router.register("/:module/:controller/:action/:id", function(evt) {
                routePath(evt);
            });

            router.register("/:module/:controller/:action/:id/", function(evt) {
                routePath(evt);
            });

            router.startup();
        }
    };

    var ajax = function(url, method, data, handleAs) {
        var deffered = _request(baseConfig.baseURL + url, {
            'method': method ? method : "GET",
            'data': data ? data : "",
            'handleAs': handleAs ? handleAs : "json"
        });
        return deffered;
    };

    var Rest = function(options) {

        if (options) {
            options.target = baseConfig.baseURL + "/" + options.target;
            this.options = options;
        }

        //console.log(options);

        if (!options || !options.target) {
            throw new Error("Target is mandatory for Rest Calls");
        }

        if (!options.handleAs) {
            this.options.handleAs = "json";
        }

        this.options.headers = {
            'Content-Type': 'application/json'
        };

        var _xhr = null;
        require(["dojo/request/xhr"], function(xhr) {
            _xhr = xhr;
        });

        this._xhr = _xhr;

    };

    Rest.prototype.get = function(id) {
        id = id ? id : "";

        return this._xhr(this.options.target + id, {
            'handleAs': this.options.handleAs,
            'method': "GET",
            'headers': this.options.headers,
            'preventCache': true
        });
    };

    Rest.prototype.query = function(params) {

        return this._xhr(this.options.target, {
            'handleAs': this.options.handleAs,
            'method': "GET",
            'query': params,
            'headers': this.options.headers
        });
    };

    Rest.prototype.post = function(data) {
        return this._xhr(this.options.target, {
            'handleAs': this.options.handleAs,
            'data': JSON.stringify(data),
            'headers': this.options.headers,
            'preventCache': true,
            'method': "POST"
        });
    };

    Rest.prototype.put = function(data) {
        return this._xhr(this.options.target, {
            'handleAs': this.options.handleAs,
            'method': "PUT",
            'data': JSON.stringify(data),
            'headers': this.options.headers,
            'preventCache': true,
            error: function(error, ioargs) {
                //console.log(ioargs);
                //console.log(error);
            }
        });
    };

    Rest.prototype.remove = function(id) {
        id = id ? id : "";
        return this._xhr(this.options.target + id, {
            'handleAs': this.options.handleAs,
            'method': "DELETE",
            'preventCache': true,
            'headers': this.options.headers
        });
    };

    var baseConfig = {
        'baseURL': "/FogPanel/",
        'homepage': "#/"
    };

    var core = {
        'router': route,
        '_defaultURI': "#/",
        'ui': ui,
        'plugin': plugin,
        'Rest': _rest,
        'ajax': ajax,
        'form': form,
        'getHomepage': function() {
            return baseConfig.homepage;
        },
        'getBaseURL': function() {
            return baseConfig.baseURL;
        },
        'setConfig': function(config) {
            for (var param in config) {
                baseConfig[param] = config[param];

            }
        },
        'getGridConfig': function() {
            return {
                'plugins': {
                    'pagination': {
                        'pageSizes': ["3", "5", "10", "All"],
                        'description': true,
                        'sizeSwitch': true,
                        'pageStepper': true,
                        'gotoButton': true,
                        /*page step to be displayed*/
                        'maxPageStep': 4,
                        /*position of the pagination bar*/
                        'position': "bottom"
                    },
                    'filter': {
                        // Set the maximum rule count to 5
                        'ruleCount': 5,
                        // Set the name of the items
                        'itemsName': "filter"
                    },
                    'search': true
                }
            };
        }
    };

    require(["dojo/ready"], function(ready) {
        ready(appInit);
    });

    window["core"] = core;

    window["controller"] = controller;
    window["action"] = action;
})();