package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.PercentileRankDto;
import by.modus.percentilerank.dto.Scorable;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestPercentileRankCalculatorService {

    private PercentileRankCalculatorService calculator = new PercentileRankCalculatorService();

    @Test(expectedExceptions = NullPointerException.class)
    public void testCalculatePercentileRanks_Null_Input() {
        calculator.calculatePercentileRanks(null);
    }

    @Test
    public void testCalculatePercentileRanks_Empty_Input() {
        List<PercentileRank> result = calculator.calculatePercentileRanks(new ArrayList<>());
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testCalculatePercentileRanks_One_Score() {
        List<Scorable> oneScoreList = Collections.singletonList(new Scorable() {
            @Override
            public String getName() {
                return "John";
            }

            @Override
            public double getScore() {
                return 4.5;
            }
        });
        List<PercentileRank> result = calculator.calculatePercentileRanks(oneScoreList);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(50, result.get(0).getPercentileRank());
    }

    class TScore implements Scorable {
        private String name;
        private double score;

        TScore(String name, double score) {
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
    public void testCalculatePercentileRanks() {
        List<Scorable> input = Arrays.asList(new TScore("John", 3.5), new TScore("Don", 2.3),
                new TScore("James", 4.3), new TScore("Bond", 1.3), new TScore("Chris", 3.5));
        List<PercentileRank> expectedResult = Arrays.asList(new PercentileRankDto(input.get(0), 60), new PercentileRankDto(input.get(1), 30),
                new PercentileRankDto(input.get(2), 90), new PercentileRankDto(input.get(3), 10), new PercentileRankDto(input.get(4), 60));

        List<PercentileRank> actualResult = calculator.calculatePercentileRanks(input);

        Assert.assertEquals(expectedResult.size(), actualResult.size());
        for (int i = 0; i < expectedResult.size(); i++) {
            Assert.assertEquals(expectedResult.get(i).getScorable().getName(), actualResult.get(i).getScorable().getName());
            Assert.assertEquals(expectedResult.get(i).getScorable().getScore(), actualResult.get(i).getScorable().getScore());
            Assert.assertEquals(expectedResult.get(i).getPercentileRank(), actualResult.get(i).getPercentileRank());
        }
    }
}
