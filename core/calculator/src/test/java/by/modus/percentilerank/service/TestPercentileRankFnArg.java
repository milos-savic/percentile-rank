package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.Scorable;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TestPercentileRankFnArg {

    class Score implements Scorable {
        private final String name;
        private final double score;

        Score(String name, double score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public double getScore() {
            return score;
        }
    }

    @Test
    public void testCountLess() {
        Score score = new Score("John", 2.3);
        List<Scorable> scores = Arrays.asList(new Score("James", 1.2), new Score("Bond", 2.5), new Score("Klark", 4.1),
                new Score("Kent", 2.1));
        PercentileRankFnArg arg = new PercentileRankFnArg(score, scores);

        Assert.assertEquals(2, arg.getCountLess());
    }

    @Test
    public void testFrequency(){
        Score score = new Score("John", 2.3);
        List<Scorable> scores = Arrays.asList(new Score("James", 1.2), new Score("Bond", 2.5), new Score("Klark", 4.1),
                new Score("Kent", 2.3));
        PercentileRankFnArg arg = new PercentileRankFnArg(score, scores);

        Assert.assertEquals(1d, arg.getFrequency());
    }
}
