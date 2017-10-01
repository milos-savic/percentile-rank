package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.PercentileRankDto;

import java.util.function.Function;


class PercentileRankFunction {

     static final Function<PercentileRankFnArg, PercentileRank> PERCENTILE_RANK_FUNCTION = input -> {
        int N = input.sampleSize();
        long countLess = input.getCountLess();
        double f = input.getFrequency();
        int percentileRank = (int)Math.rint(((countLess + 0.5*f)/N) * 100);
        return new PercentileRankDto(input.getScorable(), percentileRank);
    };
}
