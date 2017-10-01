package by.modus.percentilerank.service;

import by.modus.percentilerank.dto.Scorable;

import java.util.List;

class PercentileRankFnArg {
    private final Scorable scorable;
    private final List<? extends Scorable> allScores;
    private final long countLess;
    private final long frequency;

    PercentileRankFnArg(Scorable scorable, List<? extends Scorable> allScores) {
        this.scorable = scorable;
        this.allScores = allScores;
        countLess = countLess();
        frequency = frequency();
    }

    /**
     * The count of all scores less than the scorable of interest.
     *
     */
    private long countLess() {
        return allScores.parallelStream().filter(sc -> sc.getScore() < scorable.getScore()).count();
    }

    /**
     * The frequency of the scorable of interest.
     *
     */
    private long frequency() {
        return allScores.parallelStream().filter(sc -> sc.getScore() == scorable.getScore()).count();
    }

    Scorable getScorable() {
        return scorable;
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
