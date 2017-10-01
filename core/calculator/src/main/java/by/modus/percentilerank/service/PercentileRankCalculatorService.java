package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.PercentileRankDto;
import by.modus.percentilerank.dto.Scorable;
import by.modus.percentilerank.facade.PercentileRankCalculator;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static by.modus.percentilerank.service.PercentileRankFunction.PERCENTILE_RANK_FUNCTION;

@Service("percentileRankCalculatorService")
public class PercentileRankCalculatorService implements PercentileRankCalculator<Scorable, PercentileRank> {

    @Override
    public List<PercentileRank> calculatePercentileRanks(@NotNull(message = "Scoring sample is NULL!") List<? extends Scorable> scores) {
        if (scores.isEmpty()) {
            return new ArrayList<>();
        }
        if (scores.size() == 1) {
            return Collections.singletonList(new PercentileRankDto(scores.get(0), 50));
        }

        List<PercentileRankFnArg> inputArgs = scores.stream().map(sc -> new PercentileRankFnArg(sc, scores)).collect(Collectors.toList());
        return inputArgs.stream().map(PERCENTILE_RANK_FUNCTION).collect(Collectors.toList());
    }
}
