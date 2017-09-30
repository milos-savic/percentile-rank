package by.modus.percentilerank.dto;

public class PercentileRankDto implements PercentileRank {

    private final Score score;
    private final double percentileRank;

    public PercentileRankDto(Score score, double percentileRank) {
        this.score = score;
        this.percentileRank = percentileRank;
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public double getPercentileRank() {
        return percentileRank;
    }
}
