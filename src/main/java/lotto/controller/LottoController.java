package lotto.controller;

import java.util.List;
import lotto.model.BonusNumber;
import lotto.model.LottoConstants;
import lotto.model.LottoTicket;
import lotto.model.MoneyValidator;
import lotto.model.ResultDetails;
import lotto.model.WinningNumbers;
import lotto.model.WinningNumbersData;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final ResultDetails resultDetails = new ResultDetails();

    public void play() {
        int purchaseAmount = generateValidMoney();
        int numberOfLottoPurchased = purchaseAmount / LottoConstants.LOTTO_PRICE_UNIT;

        LottoTicket lottoTicket = LottoTicket.create(numberOfLottoPurchased);

        OutputView.printNumberOfLotto(numberOfLottoPurchased);
        OutputView.printLottoTicket(lottoTicket);

        WinningNumbers winningNumbers = createValidWinningNumbers();

        resultDetails.updateResultDetails(lottoTicket, winningNumbers);

        OutputView.printWinningStatisticsHeader();
        OutputView.printWinningStatistics(resultDetails);
        OutputView.printProfitRate(resultDetails.calculateProfitRate(purchaseAmount));
    }

    private int generateValidMoney() {
        while (true) {
            try {
                OutputView.printPurchaseAmountMessage();
                int purchaseAmount = InputView.inputNumber();
                MoneyValidator.validateMoney(purchaseAmount);
                return purchaseAmount;
            } catch (IllegalArgumentException illegalArgumentException) {
                OutputView.printExceptionMessage(illegalArgumentException.getMessage());
            }
        }
    }

    private WinningNumbers createValidWinningNumbers() {
        WinningNumbersData winningNumbersData = createValidWinningNumbersData();
        while (true) {
            try {
                OutputView.printBonusNumberMessage();

                BonusNumber bonusNumber = new BonusNumber(InputView.inputNumber());
                return new WinningNumbers(winningNumbersData, bonusNumber);
            } catch (IllegalArgumentException illegalArgumentException) {
                OutputView.printExceptionMessage(illegalArgumentException.getMessage());
            }
        }
    }

    private WinningNumbersData createValidWinningNumbersData() {
        while (true) {
            try {
                OutputView.printWinningNumbersMessage();
                List<Integer> winningNumbers = InputView.inputWinningNumbers();
                return new WinningNumbersData(winningNumbers);
            } catch (IllegalArgumentException illegalArgumentException) {
                OutputView.printExceptionMessage(illegalArgumentException.getMessage());
            }
        }
    }
}
