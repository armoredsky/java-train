package com.mstreeter.trains.service;

import com.mstreeter.trains.domain.AdjacentMap;
import com.mstreeter.trains.domain.TrainGraph;
import com.mstreeter.trains.utils.TestBase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GraphAnalyzerServiceTest extends TestBase {

    GraphAnalyzerService service;
    TrainGraph givenTG;

    @Before
    public void setup() {
        service = new GraphAnalyzerService();
        givenTG = service.buildTrainGraph("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

    @Test
    public void testGivenGraphBuilder() {
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
    public void testGivenDistance() {
        assertThat(service.getDistance(givenTG, "ABC"), is("9"));
        assertThat(service.getDistance(givenTG, "AD"), is("5"));
        assertThat(service.getDistance(givenTG, "ADC"), is("13"));
        assertThat(service.getDistance(givenTG, "AEBCD"), is("22"));
        assertThat(service.getDistance(givenTG, "AED"), is("NO SUCH ROUTE"));
    }


    @Test
    public void testGetRouteCount() {
        long expected = 2L;
        long actual = service.getRouteCount(givenTG, "C", "C", 3);

        assertThat(actual, is(expected));

        //largest long before it goes overboard
        assertThat(service.getRouteCount(givenTG, "C", "C", 113), is(6_565_747_955_185_067_648L));
    }

    @Test
    public void testGetRouteCountAtMax() {

        long expected = 2L;
        long actual = service.getRouteCount(givenTG, "C", "C", 4, true);

        assertThat(actual, is(expected));

        //largest long before it goes overboard
        assertThat(service.getRouteCount(givenTG, "C", "C", 134, true), is(6_074_759_510_567_587_936L));
    }

    @Test
    public void testGivenShortestDistance() {
        assertThat(service.getShortestRoute(givenTG, "A", "C"), is("9"));
        assertThat(service.getShortestRoute(givenTG, "B", "B"), is("9"));
    }

    @Test
    public void testShortestDistanceNoSuchRoute() {
        TrainGraph tg = service.buildTrainGraph("AB5, BC4");
        assertThat(service.getShortestRoute(tg, "E", "P"), is("NO SUCH ROUTE"));
        assertThat(service.getShortestRoute(tg, "C", "B"), is("NO SUCH ROUTE"));
        assertThat(service.getShortestRoute(tg, "C", "A"), is("NO SUCH ROUTE"));
    }

    @Test
    public void testRouteLessThanDistanceLargeDistance() {
        assertThat(service.getRouteCountLessThanDistance(givenTG, "C", "C", 100), is(1_907));
        assertThat(service.getRouteCountLessThanDistance(givenTG, "C", "C", 150), is(87_707));
        assertThat(service.getRouteCountLessThanDistance(givenTG, "C", "C", 175), is(590_587));
        // runs out of memory at 200
        //assertThat(trainGraph.getRouteCountLessThanDistance("C", "C", 200), is(??));
    }

    @Test
    public void testGivenRoutesLessThanDistance() {
        assertThat(service.getRouteCountLessThanDistance(givenTG, "C", "C", 30), is(7));
    }


}