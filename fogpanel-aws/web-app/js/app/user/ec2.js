"use strict";
require([
    "dojo",
    "dojo/i18n",
    "dojo/i18n!js/dojo-1.8/FogPanel/nls/translator.js",
    "dijit/dijit",
    "dojo/dom-class",
    "FogPanel/VPNStatus",
    "dojo/dom-construct",
    "dojo/store/JsonRest",
    "dojo/query",
    "dijit/registry",
    "dijit/form/FilteringSelect",
    "dijit/form/Select",
    "dojo/data/ItemFileWriteStore",
    "dojo/store/Memory",
    "dojo/store/Observable",
    "dojox/grid/DataGrid",
    "dojox/grid/EnhancedGrid",
    "dojox/calendar/Calendar",
    "dijit/layout/TabContainer",
    "dijit/layout/ContentPane",
    "dojo/dnd/Source",
    "dijit/form/DateTextBox",
    "dijit/form/MultiSelect",
    "dojo/NodeList-traverse",
    "dojox/form/PasswordValidator",
    "dijit/form/Form",
    "FogPanel/WizardListItem",
    "FogPanel/StorageAction",
    "dojox/widget/Toaster",
    "FogPanel/ZoneItem",
    "dijit/TitlePane",
    "dojo/dnd/Source",
    "dijit/form/MultiSelect",
    "dojo/dom",
    "dojo/_base/window",
    "FogPanel/Navigator",
    "FogPanel/VerticalMenuBar",
    "FogPanel/Wizard",
    "dijit/layout/BorderContainer",
    "dijit/form/DropDownButton",
    "dijit/layout/TabContainer",
    "dijit/TooltipDialog",
    "dijit/form/HorizontalRule",
    "dijit/form/HorizontalRuleLabels",
    "dijit/form/HorizontalSlider",
    "dijit/form/Textarea",
    "Zone/ZoneCost",
    "dojox/widget/rotator/Slide",
    "dojox/widget/Rotator",
    "dijit/form/Button",
    "dojox/validate/regexp",
    "dijit/form/ValidationTextBox",
    "dijit/Dialog"
],
        function (dojo, i18n, translator, dijit, VPNStatus, domClass, domConstruct, JsonRest, query, registry, FilteringSelect, Select,
                ItemFileWriteStore, Memory, Observable, DataGrid, EnhancedGrid, Calendar, ContentPane, Source, MultiSelect, dom, win) {
            window.query = query;
            window.domClass = domClass;
            window.domConstruct = domConstruct;
            window.VPNStatus = VPNStatus;
            window.JsonRest = JsonRest;
            window.registry = registry;
            window.FilteringSelect = FilteringSelect;
            window.ItemFileWriteStore = ItemFileWriteStore;
            window.Observable = Observable;
            window.Memory = Memory;
            window.Select = Select;
            window.ContentPane = ContentPane;
            window.DataGrid = DataGrid;
            window.EnhancedGrid = EnhancedGrid;
            window.Calendar = Calendar;
            window.Source = Source;
            window.MultiSelect = MultiSelect;
            window.dom = dom;
            window.win = win;
            controller({
                name: "ec2",
                module: "user",
                filePath: "/js/app/user/ec2.js",
                layout: {
                    name: "ec2",
                    containerId: "content"
                },
                scaffold: false
            },
            {
                "dashboard": action(function () {
                    if (dijit.byId("serviceTooltipDialogue")) {
                        dijit.byId("serviceTooltipDialogue").destroyRecursive();
                    }
                    core.ui.loadTemplate("ec2Dashboard", core.ui.getContentId());
                    EC2MenuInfo.init();
                    EC2MenuInfo.populateEC2Menus();
                   
                }),
            });
        });

var EC2MenuInfo = {
    init: function () {
        var accountListStore = new JsonRest({
            target: core.getContextPath() + "/api/account/currentAccount"
        });
        accountListStore.query().then(function (data) {
            dojo.forEach(data, function (el) {
                dojo.byId("usageCost").innerHTML = LocaleNumber.format(el.daily.toFixed(2));
                dojo.byId("usageCurrency").innerHTML = el.currency;

                dojo.byId("usagePeriod").innerHTML = el.dailyDate;
                dojo.byId("dueCurrency").innerHTML = el.currency;
                dojo.byId("currentDue").innerHTML = LocaleNumber.format(el.currentDue.toFixed(2));

                dojo.byId("Payments").innerHTML = LocaleNumber.format(el.payments.toFixed(2));
                dojo.byId("paymentCurrency").innerHTML = el.currency;

                dojo.byId("PaymentPeriod").innerHTML = el.paymentPeriod;
                dojo.byId("currentDuePeriod").innerHTML = el.paymentPeriod;

                if (el.accountType === "TRIAL") {
                    dojo.byId("creditLimitDiv").style.display = "block";
                    dojo.byId("creditLimitSpan").innerHTML = el.creditLimit;
                }
            });
        });
        
        EC2MenuInfo.populateEC2StatInfo();
        
    },
    populateEC2Menus: function () {
        var ec2MenuList = [
            {menuItemName: translator.common.ec2.home, href: "#/user/ec2/dashboard", iconClass: "icon-home", subMenu: false, dropdownIcon: "", aTagClasses: "singleMenu"},
            {menuItemName: translator.common.ec2.instances, href: "#/user/server", iconClass: "index_title_icons_vpc", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent: [
                    {subMenuItemName: translator.common.ec2.instances, href: "#/user/server"}
                ]
            },
            {menuItemName: translator.common.ec2.images, href: "#/user/image/list", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent: [
                    {subMenuItemName: translator.common.ec2.ami, href: "#/user/image/list"}
                ]
            },
            {menuItemName: translator.common.ec2.ebs, href: "#/user/volume/list", iconClass: "srv-submenu-title-vpn", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent: [
                    {subMenuItemName: translator.common.ec2.volumes, href: "#/user/volume/list"},
                    {subMenuItemName: translator.common.ec2.snapshots, href: "#/user/snapshot/list"}
                ]
            },
            {menuItemName: translator.common.ec2.networkAndSecurity, href: "#/user/securityGroup/list", iconClass: "icon-bar-chart", subMenu: true, dropdownIcon: "icon-chevron-down", aTagClasses: "dropdown-toggle",
                submenuContent: [
                    {subMenuItemName: translator.common.ec2.securityGroups, href: "#/user/securityGroup/list"},
                    {subMenuItemName: translator.common.ec2.keypairs, href: "#/user/cloud/sshKey"}
                ]
            }

        ];
        var ec2options = {
            identifier: 'id',
            label: 'name',
            items: [{id: "option", name: translator.common.allVPC}]
        };
        var ec2List = new ItemFileWriteStore({data: ec2options});
        if (dijit.byId("userVerticalMenuBarWidget")) {
            dijit.byId("userVerticalMenuBarWidget").unsubscribe();
            dijit.byId("userVerticalMenuBarWidget").destroyRecursive();
        }
        var VerticalMenuBar = new FogPanel.VerticalMenuBar({
            id: "userVerticalMenuBarWidget",
            options: true,
            backtoDashboardLabel: translator.common.backtoDashboard,
            onBackButtonclick: function () {
                dojo.byId("selectedANVPCID").value = "";
                core.router.go("#/user/home");
                RegionConfigForMenu.populateValue();
            },
            selectOptionContent: ec2List
        }).placeAt("userVerticalMenuBar");
        VerticalMenuBar.populateMenu(ec2MenuList);
        VerticalMenuBar.subscribe("/FogPanel/event/route/changed");                

    },
    
    populateEC2StatInfo: function() {
        
        dojo.byId("runningInstanceCount").innerHTML = 1;   
        dojo.byId("imageCount").innerHTML = 1;
        dojo.byId("volumeCount").innerHTML = 1;
        dojo.byId("snapshotCount").innerHTML = 1;
        dojo.byId("securityGroupCount").innerHTML = 1;
        dojo.byId("sshKeyCount").innerHTML = 1;
        
        var currentRegionId = dojo.byId("selectedRegionID").value;
        var vpcStatRestResource = new JsonRest({
            target: core.getContextPath() + "/api/vpc/stat"
        });
        
        vpcStatRestResource.query({regionId: currentRegionId}).then(function (data) {

            if (data === "undefined" || data === "" || data[0] === "undefined" || data.length === 0) {
                dojo.byId("runningInstanceCount").innerHTML = 0;   
                dojo.byId("imageCount").innerHTML = 0;
                dojo.byId("volumeCount").innerHTML = 0;
                dojo.byId("snapshotCount").innerHTML = 0;
                dojo.byId("securityGroupCount").innerHTML = 0;
                dojo.byId("sshKeyCount").innerHTML = 0;
            } else {
                dojo.byId("runningInstanceCount").innerHTML = 0;   
                dojo.byId("imageCount").innerHTML = 0;
                dojo.byId("volumeCount").innerHTML = 0;
                dojo.byId("snapshotCount").innerHTML = 0;
                dojo.byId("securityGroupCount").innerHTML = 0;
                dojo.byId("sshKeyCount").innerHTML = 0;
            }
        });
        
        
    }
};

Window.EC2MenuInfo = EC2MenuInfo;
