package com.mstreeter.trains;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String []args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input graph string like: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        String graphString = br.readLine();
//        TrainGraph graph = new TrainGraph(graphString);
//
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("Output #1: ").append(graph.getDistance("ABC")).append("\n")
//                .append("Output #2: ").append(graph.getDistance("AD")).append("\n")
//                .append("Output #3: ").append(graph.getDistance("ADC")).append("\n")
//                .append("Output #4: ").append(graph.getDistance("AEBCD")).append("\n")
//                .append("Output #5: ").append(graph.getDistance("AED")).append("\n")
//
//                .append("Output #6: ").append(graph.getRouteCount("C", "C", 3)).append("\n")
//
//                .append("Output #7: ").append(graph.getRouteCountAtStops("A", "C", 4)).append("\n")
//
//                .append("Output #8: ").append(graph.getShortestRoute("A", "C")).append("\n")
//                .append("Output #9: ").append(graph.getShortestRoute("C", "C")).append("\n")
//
//                .append("Output #10: ").append(graph.getRouteCountLessThanDistance("C", "C", 30)).append("\n");
//
//        System.out.println(sb.toString());

    }


}
