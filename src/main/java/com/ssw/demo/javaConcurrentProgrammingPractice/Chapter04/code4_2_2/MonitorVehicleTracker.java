package com.ssw.demo.javaConcurrentProgrammingPractice.Chapter04.code4_2_2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 线程安全的追踪器
 *
 * @author wss
 * @created 2020/9/18 14:36
 * @since 1.0
 */
public class MonitorVehicleTracker {

    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepcopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepcopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    private static Map<String, MutablePoint> deepcopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }
        // 返回不可修改的Map
        return Collections.unmodifiableMap(result);
    }
}
