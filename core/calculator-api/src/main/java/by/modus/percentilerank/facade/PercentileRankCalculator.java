package by.modus.percentilerank.facade;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.Score;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PercentileRankCalculator<T extends Score, R extends PercentileRank> {
    /**
     * Calculate percentile ranks base on <a href="https://en.wikipedia.org/wiki/Percentile_rank"/>
     *
     * @param scores
     * @return
     */
    List<R> calculatePercentileRanks(@NotNull List<T> scores);
}
