package com.mstreeter.trains.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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
    public Set<String> getAdjacentNodes(String node) {
        return adjacencyMap.get(node).keySet();
    }

    @Override
    public int getDistance(String startNode, String endNode) {

        return adjacencyMap.get(startNode).get(endNode);
    }

    @Override
    public int getRouteDistance(List<String> route) {
        int distance = 0;
        String start = route.remove(0);
        for( String end: route){
            distance += getDistance(start, end);
            start = end;
        }

        return distance;
    }

  }
