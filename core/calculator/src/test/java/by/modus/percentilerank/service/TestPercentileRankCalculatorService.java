package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.Scorable;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
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
}
