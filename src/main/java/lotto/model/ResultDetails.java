package lotto.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultDetails {

    private static final int PERCENTAGE_FACTOR = 100;

    private final Map<Rank, Integer> resultDetails = new HashMap<>();

    public ResultDetails() {
        initializeWinningCountsForRanks();
    }

    public void updateResultDetails(final LottoTicket lottoTicket, final WinningNumbers winningNumbers) {
        lottoTicket.getLottos().stream()
                .map(lotto -> Rank.find(winningNumbers.countMatchingNumbers(lotto),
                        winningNumbers.hasBonusNumber(lotto)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((rank, count) -> resultDetails.put(rank, count.intValue()));
    }

    public double calculateProfitRate(final Money purchaseAmount) {
        return (double) Rank.calculateTotalReward(resultDetails) / purchaseAmount.getMoney() * PERCENTAGE_FACTOR;
    }

    public int getWinnerCountByRank(final Rank rank) {
        return resultDetails.get(rank);
    }

    private void initializeWinningCountsForRanks() {
        Arrays.stream(Rank.values())
                .forEach(rank -> resultDetails.put(rank, 0));
    }
}
