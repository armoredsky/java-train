package com.mstreeter.trains.service;

import com.mstreeter.trains.domain.AdjacentMap;
import com.mstreeter.trains.domain.TrainGraph;
import sun.security.util.BigInt;

import java.math.BigInteger;
import java.util.HashMap;

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

    public long doshit(TrainGraph tg, String start, String end, Integer maxStops){
        long dcount = 0L;
        HashMap<String, Long> nextMap;

        HashMap<String, Long> currentMap = new HashMap<>();
        currentMap.put(start, 1L);

        for(int i=0; i < maxStops; i++){
            nextMap = new HashMap<>();
            for(String key: currentMap.keySet()){
                long numberOnNode = currentMap.get(key);
                for(String adjacentNode: tg.getAdjacentNodes(key)){
                    long num = nextMap.containsKey(adjacentNode) ? nextMap.get(adjacentNode) :0;
                    nextMap.put(adjacentNode, num + numberOnNode);
                    //System.out.println("adding " + (num + numberOnNode) + " to " + adjacentNode);
                }

            }
            if(nextMap.containsKey(end)){
                dcount += nextMap.get(end);
            }

            currentMap = nextMap;

        }

        return dcount;
    }


}
