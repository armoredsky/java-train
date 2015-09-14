package com.mstreeter.trains.domain;

import java.util.HashMap;
import java.util.Set;

public interface TrainGraph {

    public Set<String> getNodeSet();

    public void addNode(String node);

    public void addAdjacentNode(String node, String Adjacent, int distance);

    public Set<String> getAdjacentNodesKeys(String node);

    public long getDistance(String startNode, String endNode ) throws NoRouteException;

    //i don't actually like this method. only here for my naieve implementating of getRouteCountLessThanDistance
    public HashMap<String, Integer> getAdjacentNodes(String node);
}
