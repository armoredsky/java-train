package com.mstreeter.trains.service;

import com.mstreeter.trains.domain.AdjacentMap;
import com.mstreeter.trains.domain.TrainGraph;

import java.util.HashMap;

public class GraphAnalyzer {
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


}
