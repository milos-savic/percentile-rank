package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.Score;

import java.util.List;

class PercentileRankFnArg {
    private final Score score;
    private final List<? extends Score> allScores;
    private final long countLess;
    private final double frequency;

    PercentileRankFnArg(Score score, List<? extends Score> allScores) {
        this.score = score;
        this.allScores = allScores;
        countLess = countLess();
        frequency = frequency();
    }

    /**
     * The count of all scores less than the score of interest.
     *
     */
    private long countLess() {
        return allScores.parallelStream().filter(sc -> sc.getScore() < score.getScore()).count();
    }

    /**
     * The frequency of the score of interest.
     *
     */
    private double frequency() {
        long f = allScores.parallelStream().filter(sc -> sc.getScore() == score.getScore()).count();
        return f / allScores.size();
    }

    Score getScore() {
        return score;
    }

    long getCountLess() {
        return countLess;
    }

    double getFrequency() {
        return frequency;
    }

    int sampleSize() {
        return allScores.size();
    }
}
