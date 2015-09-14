package com.mstreeter.trains.domain;

import java.util.*;

public class AdjacentMap implements TrainGraph{

    private LinkedHashMap<String, HashMap<String, Integer>> adjacencyMap;

    public AdjacentMap(){
        adjacencyMap = new LinkedHashMap<>();
    }

    @Override
    public Set<String> getNodeSet() {
        return adjacencyMap.keySet();
    }

    @Override
    public void addNode(String node) {
        if(!adjacencyMap.containsKey(node)){
            adjacencyMap.put(node, new HashMap<String, Integer>());
        }
    }

    @Override
    public void addAdjacentNode(String node, String adjacent, int distance) {
        addNode(node);
        adjacencyMap.get(node).put(adjacent, distance);
    }

    @Override
    public Set<String> getAdjacentNodesKeys(String node) {
        if(adjacencyMap.get(node) == null){
            return new HashSet<>();
        }
        return adjacencyMap.get(node).keySet();
    }

    //i don't actually like this method. only here for my naieve implementating of getRouteCountLessThanDistance
    public HashMap<String, Integer> getAdjacentNodes(String node) {
        if(adjacencyMap.get(node) == null){
            return new HashMap<>();
        }
        return adjacencyMap.get(node);
    }

    @Override
    public long getDistance(String startNode, String endNode) throws NoRouteException {
        try{
            return adjacencyMap.get(startNode).get(endNode);
        }catch (Exception e){
            throw new NoRouteException();
        }
    }
}
