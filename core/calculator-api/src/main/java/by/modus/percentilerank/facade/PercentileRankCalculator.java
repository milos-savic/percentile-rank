package by.modus.percentilerank.facade;

import by.modus.percentilerank.dto.PercentileRank;
import by.modus.percentilerank.dto.Scorable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface PercentileRankCalculator<T extends Scorable, R extends PercentileRank> {
    /**
     * Calculate percentile ranks base on <a href="https://en.wikipedia.org/wiki/Percentile_rank"/>
     *
     */
    List<R> calculatePercentileRanks(@NotNull List<? extends T> scores);
}
