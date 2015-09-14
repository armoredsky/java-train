package com.mstreeter.trains;

import com.mstreeter.trains.domain.TrainGraph;
import com.mstreeter.trains.service.GraphAnalyzerService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String []args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Input graph string like: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        String graphString = br.readLine();
        GraphAnalyzerService service = new GraphAnalyzerService();
        TrainGraph tg = service.buildTrainGraph(graphString);
        StringBuilder sb = new StringBuilder();

        sb.append("Output #1: ").append(service.getDistance(tg, "ABC")).append("\n")
                .append("Output #2: ").append(service.getDistance(tg, "AD")).append("\n")
                .append("Output #3: ").append(service.getDistance(tg, "ADC")).append("\n")
                .append("Output #4: ").append(service.getDistance(tg, "AEBCD")).append("\n")
                .append("Output #5: ").append(service.getDistance(tg, "AED")).append("\n")

                .append("Output #6: ").append(service.getRouteCount(tg, "C", "C", 3)).append("\n")

                .append("Output #7: ").append(service.getRouteCount(tg, "A", "C", 4, true)).append("\n")

                .append("Output #8: ").append(service.getShortestRoute(tg, "A", "C")).append("\n")
                .append("Output #9: ").append(service.getShortestRoute(tg, "C", "C")).append("\n")

                .append("Output #10: ").append(service.getRouteCountLessThanDistance(tg, "C", "C", 30)).append("\n")
                ;
        System.out.println(sb.toString());

    }


}
