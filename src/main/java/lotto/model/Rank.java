package lotto.model;

import java.util.Arrays;
import java.util.Map;

public enum Rank {

    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, 2_000_000_000),
    NONE(0, 0);

    private final int matchingNumber;
    private final boolean bonusMatch;
    private final int reward;

    Rank(int matchingNumber, int reward) {
        this.matchingNumber = matchingNumber;
        bonusMatch = false;
        this.reward = reward;
    }

    Rank(int matchingNumber, boolean bonusMatch, int reward) {
        this.matchingNumber = matchingNumber;
        this.bonusMatch = bonusMatch;
        this.reward = reward;
    }

    public static Rank find(int matchingNumber, boolean bonusMatch) {
        if (isEqualsToSecondRankMatchingNumber(matchingNumber, bonusMatch)) {
            return SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> isSameMatchingNumber(rank.matchingNumber, matchingNumber))
                .filter(rank -> rank != SECOND)
                .findAny()
                .orElse(NONE);
    }

    public static int calculateTotalReward(Map<Rank, Integer> resultDetails) {
        return Arrays.stream(Rank.values())
                .mapToInt(rank -> rank.reward * resultDetails.get(rank))
                .sum();
    }

    private static boolean isEqualsToSecondRankMatchingNumber(int matchingNumber, boolean bonusMatch) {
        return matchingNumber == SECOND.matchingNumber && bonusMatch == SECOND.bonusMatch;
    }

    private static boolean isSameMatchingNumber(int matchingNumberOfRank, int matchingNumber) {
        return matchingNumberOfRank == matchingNumber;
    }
}
