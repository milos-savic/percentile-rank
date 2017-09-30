package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.PercentileRankDto;
import by.modus.percentilerank.dto.Score;
import by.modus.percentilerank.facade.PercentileRankCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static by.modus.percentilerank.service.PercentileRankFunction.PERCENTILE_RANK_FUNCTION;

@Service
class PercentileRankCalculatorService implements PercentileRankCalculator<Score, PercentileRank> {

    @Override
    public List<PercentileRank> calculatePercentileRanks(List<? extends Score> scores) {
        if (scores.isEmpty()) {
            return new ArrayList<>();
        }
        if (scores.size() == 1) {
            return Collections.singletonList(new PercentileRankDto(scores.get(0), 50));
        }

        List<PercentileRankFnArg> inputArgs = scores.parallelStream().map(sc -> new PercentileRankFnArg(sc, scores)).collect(Collectors.toList());
        return inputArgs.parallelStream().map(PERCENTILE_RANK_FUNCTION).collect(Collectors.toList());
    }
}
