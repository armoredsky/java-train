package com.mstreeter.trains.domain;

import java.util.Set;

public interface TrainGraph {

    public Set<String> getNodeSet();

    public void addNode(String node);

    public void addAdjacentNode(String node, String Adjacent, int distance);

    public Set<String> getAdjacentNodes(String node);

    public long getDistance(String startNode, String endNode ) throws NoRouteException;
}
