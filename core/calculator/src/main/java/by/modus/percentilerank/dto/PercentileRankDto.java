package by.modus.percentilerank.dto;

public class PercentileRankDto implements PercentileRank {

    private final Scorable scorable;
    private final double percentileRank;

    public PercentileRankDto(Scorable scorable, double percentileRank) {
        this.scorable = scorable;
        this.percentileRank = percentileRank;
    }

    @Override
    public Scorable getScorable() {
        return scorable;
    }

    @Override
    public double getPercentileRank() {
        return percentileRank;
    }
}
