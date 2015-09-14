package com.mstreeter.trains.service;

import com.mstreeter.trains.domain.AdjacentMap;
import com.mstreeter.trains.domain.NoRouteException;
import com.mstreeter.trains.domain.TrainGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GraphAnalyzerService {
    public TrainGraph buildTrainGraph(String graphString){
        TrainGraph tg = new AdjacentMap();
        String[] valuesArray = graphString.split(", ");

        for (String values : valuesArray) {
            String start = values.substring(0, 1);
            String end = values.substring(1, 2);
            Integer distance = Integer.valueOf(values.substring(2));

            tg.addAdjacentNode(start, end, distance);
        }
        return tg;
    }

    public String getDistance(TrainGraph tg, String route){
        long distance = 0;
        String[] routeArray = route.split("(?!^)");

        try {
            String start = routeArray[0];
            for (int i =1; i < routeArray.length; i++) {
                distance += tg.getDistance(start, routeArray[i]);
                start = routeArray[i];
            }
        } catch (NoRouteException nre) {
            return nre.getMessage();
        }
        return Long.toString(distance);
    }


    public long getRouteCount(TrainGraph tg, String start, String end, Integer maxStops){
        return getRouteCount(tg, start, end, maxStops, false);
    }

    public long getRouteCount(TrainGraph tg, String start, String end, Integer maxStops, boolean onlyCountMax){
        long routeCount = 0L;
        HashMap<String, Long> nextMap;

        HashMap<String, Long> currentMap = new HashMap<>();
        currentMap.put(start, 1L);

        for(int i=0; i < maxStops; i++){
            nextMap = new HashMap<>();
            for(String key: currentMap.keySet()){
                long numberOnNode = currentMap.get(key);
                for(String adjacentNode: tg.getAdjacentNodes(key)){
                    long num = nextMap.containsKey(adjacentNode) ? nextMap.get(adjacentNode) : 0L;
                    nextMap.put(adjacentNode, num + numberOnNode);
                }
            }
            if(nextMap.containsKey(end)){
                routeCount += nextMap.get(end);
            }

            currentMap = nextMap;

        }

        if(onlyCountMax){
            if(currentMap.containsKey(end)){
                return currentMap.get(end);
            }
        }

        return routeCount;
    }

    public String getShortestRoute(TrainGraph tg, String start, String end) {
        Long shortestDistance = null;

        HashMap<String, Long> nextRouteDistanceMap;
        HashMap<String, Long> currentRouteDistanceMap = new HashMap<>();
        currentRouteDistanceMap.put(start, 0L);

        int count = 0;

        while(count < tg.getNodeSet().size()){
            count ++;
            nextRouteDistanceMap = new HashMap<>();
            for(String startNode: currentRouteDistanceMap.keySet()){
                for(String adjNode: tg.getAdjacentNodes(startNode)){
                    try{
                        long addedDistance = tg.getDistance(startNode, adjNode);
                        long newDistance = currentRouteDistanceMap.get(startNode) + addedDistance;
                        if(adjNode.equals(end)){
                            if(shortestDistance == null || shortestDistance > newDistance)
                                shortestDistance = newDistance;
                        } else {
                            nextRouteDistanceMap.put(adjNode, newDistance);
                        }

                    }catch(NoRouteException nre){}
                }
            }
            currentRouteDistanceMap = nextRouteDistanceMap;
        }
        if(shortestDistance != null){
            return shortestDistance.toString();
        }
        return "NO SUCH ROUTE";
    }












//
//    public int getRouteCountLessThanDistance(String start, String end, int maxDistance) {
//        int routeCount = 0;
//
//        LinkedHashMap<String, Integer> routesDistanceMap = getRouteDistanceMapMaxDistance(start, maxDistance);
//        for (String route : routesDistanceMap.keySet()) {
//            if (route.endsWith(end) && !route.equals(start)) {
//                routeCount++;
//            }
//        }
//        return routeCount;
//    }




//    public LinkedHashMap<String, Integer> getRouteDistanceMapMaxDistance(String start, int maxDistance) {
//        LinkedHashMap<String, Integer> routesDistanceMap = new LinkedHashMap<>();
//
//        ArrayList<String> currentRoutes = new ArrayList<>();
//        ArrayList<String> nextRoutes;
//        routesDistanceMap.put(start, 0);
//        currentRoutes.add(start);
//
//        while (currentRoutes.size() > 0) {
//            nextRoutes = new ArrayList<>();
//            for (String currentRoute : currentRoutes) {
//                String lastStop = currentRoute.substring(currentRoute.length() - 1);
//                HashMap<String, Integer> adjacentNodes = adjacencyMap.get(lastStop);
//                if (adjacentNodes != null) {
//                    for (String adjacentKey : adjacentNodes.keySet()) {
//                        Integer currentDistance = routesDistanceMap.get(currentRoute);
//                        String newRoute = currentRoute + adjacentKey;
//                        Integer newDistance = currentDistance + adjacentNodes.get(adjacentKey);
//                        if (newDistance < maxDistance) {
//                            nextRoutes.add(newRoute);
//                            routesDistanceMap.put(newRoute, newDistance);
//                        }
//                    }
//                }
//            }
//            currentRoutes.clear();
//            currentRoutes.addAll(nextRoutes);
//        }
//        return routesDistanceMap;
//    }

}
