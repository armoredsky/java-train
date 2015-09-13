package com.mstreeter.trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TrainGraph {
    private HashMap<String, HashMap<String, Integer>> adjacencyMap;
    final String NO_ROUTE = "NO SUCH ROUTE";

    public TrainGraph(String graphString) {
        adjacencyMap = buildMap(graphString);
    }

    public HashMap<String, HashMap<String, Integer>> buildMap(String graphString) {
        String[] valuesArray = graphString.split(", ");
        HashMap<String, HashMap<String, Integer>> map = new HashMap<>();

        for (String values : valuesArray) {
            String start = values.substring(0, 1);
            String end = values.substring(1, 2);
            Integer distance = Integer.valueOf(values.substring(2));

            if (!map.containsKey(start)) {
                map.put(start, new HashMap<String, Integer>());
            }

            map.get(start).put(end, distance);
        }
        return map;
    }

    public int getRouteCountLessThanDistance(String start, String end, int maxDistance) {
        int routeCount = 0;

        LinkedHashMap<String, Integer> routesDistanceMap = getRouteDistanceMapMaxDistance(start, maxDistance);
        for (String route : routesDistanceMap.keySet()) {
            if (route.endsWith(end) && !route.equals(start)) {
                routeCount++;
            }
        }
        return routeCount;
    }

    public int getShortestRoute(String start, String end) {
        int maxLength = adjacencyMap.keySet().size();
        int distance = 0;

        LinkedHashMap<String, Integer> routesDistanceMap = getRoutesDistanceMapMaxStops(start, maxLength);
        for (String route : routesDistanceMap.keySet()) {
            if (route.endsWith(end)) {
                if (distance == 0 || distance > routesDistanceMap.get(route)) {
                    distance = routesDistanceMap.get(route);
                }
            }
        }
        return distance;
    }

    public int getRouteCountAtStops(String start, String end, int stopCount) {
        LinkedHashMap<String, Integer> routesDistanceMap = getRoutesDistanceMapMaxStops(start, stopCount);

        int routeCount = 0;
        for (String route : routesDistanceMap.keySet()) {
            if (route.endsWith(end) && route.length() == stopCount + 1) {
                routeCount++;
            }
        }
        return routeCount;
    }

    public Integer getRouteCount(String start, String end, int stopCount) {
        LinkedHashMap<String, Integer> routesDistanceMap = getRoutesDistanceMapMaxStops(start, stopCount);

        int routeCount = 0;
        for (String route : routesDistanceMap.keySet()) {
            if (route.endsWith(end) && routesDistanceMap.get(route) > 0) {
                routeCount++;
            }
        }

        return routeCount;
    }

    public String getDistance(String route) {
        String[] routeArray = route.split("(?!^)");
        Integer distance = 0;

        for (int i = 1; i < routeArray.length; i++) {
            String start = routeArray[i - 1];
            String end = routeArray[i];
            HashMap<String, Integer> adjacentNode = adjacencyMap.get(start);
            if (adjacentNode == null || !adjacentNode.containsKey(end)) {
                return NO_ROUTE;
            }

            distance += adjacentNode.get(end);
        }
        return distance.toString();
    }

    public LinkedHashMap<String, Integer> getRouteDistanceMapMaxDistance(String start, int maxDistance) {
        LinkedHashMap<String, Integer> routesDistanceMap = new LinkedHashMap<>();

        ArrayList<String> currentRoutes = new ArrayList<>();
        ArrayList<String> nextRoutes;
        routesDistanceMap.put(start, 0);
        currentRoutes.add(start);

        while (currentRoutes.size() > 0) {
            nextRoutes = new ArrayList<>();
            for (String currentRoute : currentRoutes) {
                String lastStop = currentRoute.substring(currentRoute.length() - 1);
                HashMap<String, Integer> adjacentNodes = adjacencyMap.get(lastStop);
                if (adjacentNodes != null) {
                    for (String adjacentKey : adjacentNodes.keySet()) {
                        Integer currentDistance = routesDistanceMap.get(currentRoute);
                        String newRoute = currentRoute + adjacentKey;
                        Integer newDistance = currentDistance + adjacentNodes.get(adjacentKey);
                        if (newDistance < maxDistance) {
                            nextRoutes.add(newRoute);
                            routesDistanceMap.put(newRoute, newDistance);
                        }
                    }
                }
            }
            currentRoutes.clear();
            currentRoutes.addAll(nextRoutes);
        }
        return routesDistanceMap;
    }

    public LinkedHashMap<String, Integer> getRoutesDistanceMapMaxStops(String start, int maxStops) {
        LinkedHashMap<String, Integer> routesDistanceMap = new LinkedHashMap<>();

        ArrayList<String> currentRoutes = new ArrayList<>();
        ArrayList<String> nextRoutes;
        routesDistanceMap.put(start, 0);
        currentRoutes.add(start);

        int count = 0;
        while (count < maxStops) {
            count++;
            nextRoutes = new ArrayList<>();
            for (String currentRoute : currentRoutes) {
                String lastStop = currentRoute.substring(currentRoute.length() - 1);
                HashMap<String, Integer> adjacentNodes = adjacencyMap.get(lastStop);
                if(adjacentNodes != null){
                    for (String adjacentKey : adjacentNodes.keySet()) {
                        Integer currentDistance = routesDistanceMap.get(currentRoute);
                        String newRoute = currentRoute + adjacentKey;
                        Integer newDistance = currentDistance + adjacentNodes.get(adjacentKey);

                        nextRoutes.add(newRoute);
                        routesDistanceMap.put(newRoute, newDistance);
                    }
                }
            }
            currentRoutes.clear();
            currentRoutes.addAll(nextRoutes);
        }
        return routesDistanceMap;
    }
}
