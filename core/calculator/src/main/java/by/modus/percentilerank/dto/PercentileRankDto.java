package by.modus.percentilerank.dto;

public class PercentileRankDto implements PercentileRank {

    private final Scorable scorable;
    private final int percentileRank;

    public PercentileRankDto(Scorable scorable, int percentileRank) {
        this.scorable = scorable;
        this.percentileRank = percentileRank;
    }

    @Override
    public Scorable getScorable() {
        return scorable;
    }

    @Override
    public int getPercentileRank() {
        return percentileRank;
    }
}
