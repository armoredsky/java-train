package com.mstreeter.trains.domain;

import java.util.List;

public interface TrainGraph {

    public void addNode(String node);

    public void addAdjacentNode(String node, String Adjacent, int distance);

    public List<String> getAdjacentNodes(String node);

    public int getDistance(String startNode, String endNode );

    public int getRouteDistance(List<String> route);
}
