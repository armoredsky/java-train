package com.mstreeter.trains.service;

import com.mstreeter.trains.domain.AdjacentMap;
import com.mstreeter.trains.domain.TrainGraph;
import com.mstreeter.trains.utils.TestBase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GraphAnalyzerServiceTest extends TestBase {

    GraphAnalyzerService service;

    @Before
    public void setup(){
        service = new GraphAnalyzerService();
    }

    @Test
    public void testGivenGraphBuilder(){
        TrainGraph expected = new AdjacentMap();
        expected.addAdjacentNode("A", "B", 5);
        expected.addAdjacentNode("B", "C", 4);
        expected.addAdjacentNode("C", "D", 8);
        expected.addAdjacentNode("D", "C", 8);
        expected.addAdjacentNode("D", "E", 6);
        expected.addAdjacentNode("A", "D", 5);
        expected.addAdjacentNode("C", "E", 2);
        expected.addAdjacentNode("E", "B", 3);
        expected.addAdjacentNode("A", "E", 7);

        TrainGraph tg = service.buildTrainGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

        assertTrue(compareTreeGraphs(tg, expected));
    }

    @Test
    public void doshit(){
        TrainGraph tg = service.buildTrainGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
        long expected = 2L;
        long actual = service.doshit(tg, "C", "C", 3);

        assertThat(actual, is(expected));

        assertThat(service.doshit(tg, "C", "C", 100), is(45_624_077_615_333_917L));

    }


}