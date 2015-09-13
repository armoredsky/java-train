package com.mstreeter.trains;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrainGraphTest {

    TrainGraph trainGraph;

    @Before
    public void setup (){
        trainGraph = new TrainGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

    @Test
    public void testBuildMap(){
        HashMap<String, HashMap<String, Integer>> map = trainGraph.buildMap("AB12, BC444, CA1");

        assertThat(map.get("A").get("B"), is(12));
        assertThat(map.get("B").get("C"), is(444));
        assertThat(map.get("C").get("A"), is(1));
        assertThat(map.get("A").get("C"), nullValue());
    }

    @Test
    public void testGivenDistances(){
        assertThat(trainGraph.getDistance("ABC"), is("9"));
        assertThat(trainGraph.getDistance("AD"), is("5"));
        assertThat(trainGraph.getDistance("ADC"), is("13"));
        assertThat(trainGraph.getDistance("AEBCD"), is("22"));
        assertThat(trainGraph.getDistance("AED"), is("NO SUCH ROUTE"));
    }

    @Test
    public void testGivenRouteCountMaxStops(){
        assertThat(trainGraph.getRouteCount("C", "C", 3), is(2));
    }


    @Test
    public void testGivenRouteCountAtStops(){
         assertThat(trainGraph.getRouteCountAtStops("A", "C", 4), is(3));
    }


    @Test
    public void testGivenShortestDistance(){
        assertThat(trainGraph.getShortestRoute("A", "C"), is(9));
        assertThat(trainGraph.getShortestRoute("B", "B"), is(9));
    }

    @Test
    public void testGivenRoutesLessThanDistance(){
        assertThat(trainGraph.getRouteCountLessThanDistance("C", "C", 30), is(7));
    }

    @Test
    public void testGivenRouteCountMaxStopsLarge(){
        assertThat(trainGraph.getRouteCount("C", "C", 36), is(1_083_303));
//        runs out of memory at 40
//        assertThat(trainGraph.getRouteCount("C", "C", 40), is(??));
    }

    @Test
    public void testRouteCountAtStopsLarge(){
        assertThat(trainGraph.getRouteCountAtStops("A", "C", 36), is(504_356));
//        runs out of memory at 40
//        assertThat(trainGraph.getRouteCountAtStops("A", "C", 40), is(504356));
    }

    @Test
    public void testRouteLessThanDistanceLargeDistance(){
        assertThat(trainGraph.getRouteCountLessThanDistance("C", "C", 100), is(1_907));
        assertThat(trainGraph.getRouteCountLessThanDistance("C", "C", 150), is(87_707));
        assertThat(trainGraph.getRouteCountLessThanDistance("C", "C", 175), is(590_587));
        // runs out of memory at 200
        //assertThat(trainGraph.getRouteCountLessThanDistance("C", "C", 200), is(??));
    }
}
