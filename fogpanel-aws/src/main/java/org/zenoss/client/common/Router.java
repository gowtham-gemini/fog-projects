package org.zenoss.client.common;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Router {

    final static String DEVICE = "DeviceRouter";
    final static String JOBS = "JobsRouter";
    final static String MESSAGING = "MessagingRouter";
    final static String MIB = "MibRouter";
    final static String NAVIGATION = "DetailNavRouter";
    final static String NETWORK = "NetworkRouter";
    final static String PROCESS = "ProcessRouter";
    final static String REPORT = "ReportRouter";
    final static String SERVICE = "ServiceRouter";
    final static String SETTINGS = "SettingsRouter";
    final static String TEMPLATE = "TemplateRouter";
    final static String ZENPACK = "ZenPackRouter";
    final static String EVENTS = "EventsRouter";

    private String router;
    private String action;
    private static Map<String, Router> map;

    static {
        map = new HashMap<String, Router>();
        map.put(DEVICE, new Router("device", "DeviceRouter"));
        map.put(JOBS, new Router("jobs", "JobsRouter"));
        map.put(MESSAGING, new Router("messaging", "MessagingRouter"));
        map.put(MIB, new Router("mib", "MibRouter"));
        map.put(NAVIGATION, new Router("detailnav", "DetailNavRouter"));
        map.put(NETWORK, new Router("network", "NetworkRouter"));
        map.put(PROCESS, new Router("process", "ProcessRouter"));
        map.put(REPORT, new Router("report", "ReportRouter"));
        map.put(SERVICE, new Router("service", "ServiceRouter"));
        map.put(SETTINGS, new Router("settings", "SettingsRouter"));
        map.put(TEMPLATE, new Router("template", "TemplateRouter"));
        map.put(ZENPACK, new Router("zenpack", "ZenPackRouter"));
        map.put(EVENTS, new Router("evconsole", "EventsRouter"));

    }

    private Router(String router, String action) {
        this.router = router;
        this.action = action;
    }

    public String getValue() {
        return this.router;
    }

    public String getAction() {
        return this.action;
    }

    public static Router getDevice() {
        return map.get(DEVICE);
    }

    public static Router getJobs() {
        return map.get(JOBS);
    }

    public static Router getMessaging() {
        return map.get(MESSAGING);
    }

    public static Router getMib() {
        return map.get(MIB);
    }

    public static Router getNavigation() {
        return map.get(NAVIGATION);
    }

    public static Router getNetwrok() {
        return map.get(NETWORK);
    }

    public static Router getProcess() {
        return map.get(PROCESS);
    }

    public static Router getReport() {
        return map.get(REPORT);
    }

    public static Router getService() {
        return map.get(SERVICE);
    }

    public static Router getSettings() {
        return map.get(SETTINGS);
    }

    public static Router getTemplate() {
        return map.get(TEMPLATE);
    }

    public static Router getZenpack() {
        return map.get(ZENPACK);
    }

    public static Router getEvents() {
        return map.get(EVENTS);
    }


}
