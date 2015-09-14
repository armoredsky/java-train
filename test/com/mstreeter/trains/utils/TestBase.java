package com.mstreeter.trains.utils;

import com.mstreeter.trains.domain.NoRouteException;
import com.mstreeter.trains.domain.TrainGraph;

abstract public class TestBase {

    public boolean compareTreeGraphs(TrainGraph actual, TrainGraph expected) {
        if(!actual.getNodeSet().equals(expected.getNodeSet())){
            return false;
        }

        for(String startNode: actual.getNodeSet()){
            if(!actual.getAdjacentNodesKeys(startNode).equals(expected.getAdjacentNodesKeys(startNode))){
                return false;
            }
            for(String endNode : actual.getAdjacentNodesKeys(startNode)){
                try{
                    if(actual.getDistance(startNode, endNode) != expected.getDistance(startNode, endNode)){
                        return false;
                    }
                } catch (NoRouteException nre){
                    return false;
                }
            }
        }
        return true;
    }
}
