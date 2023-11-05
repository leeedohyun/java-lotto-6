package lotto.model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinningNumbersDataTest {

    @Test
    public void 당첨_번호가_1보다_작으면_예외가_발생한다() {
        // given
        final List<Integer> winningNumbers = List.of(0, 1, 2, 3, 4, 5);

        // then
        Assertions.assertThatThrownBy(() -> new WinningNumbersData(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨_번호가_45보다_크면_예외가_발생한다() {
        // given
        final List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 46);

        // then
        Assertions.assertThatThrownBy(() -> new WinningNumbersData(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨_번호의_개수가_6개보다_작으면_예외가_발생한다() {
        // given
        final List<Integer> winningNumbers = List.of(1, 2, 3);

        // then
        Assertions.assertThatThrownBy(() -> new WinningNumbersData(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨_번호의_개수가_6개보다_크면_예외가_발생한다() {
        // given
        final List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6, 7);

        // then
        Assertions.assertThatThrownBy(() -> new WinningNumbersData(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        // given
        final List<Integer> winningNumbers = List.of(1, 1, 2, 3, 4, 5);

        // then
        Assertions.assertThatThrownBy(() -> new WinningNumbersData(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 당첨_번호에_포함된_숫자이면_true를_반환한다() {
        // given
        final WinningNumbersData winningNumbersData = new WinningNumbersData(List.of(1, 2, 3, 4, 5, 6));

        // then
        org.junit.jupiter.api.Assertions.assertTrue(winningNumbersData.contains(2));
    }

    @Test
    public void 당첨_번호에_포함되지_않은_숫자이면_false를_반환한다() {
        // given
        final WinningNumbersData winningNumbersData = new WinningNumbersData(List.of(1, 2, 3, 4, 5, 6));

        // then
        org.junit.jupiter.api.Assertions.assertFalse(winningNumbersData.contains(7));
    }

    @Test
    public void 당첨_번호와_로또_번호의_일치하는_숫자의_개수_테스트() {
        // given
        final WinningNumbersData winningNumbersData = new WinningNumbersData(List.of(1, 2, 3, 4, 5, 6));
        final Lotto lotto = new Lotto(List.of(1, 2, 4, 7, 8, 9));

        // when
        int matchingNumber = winningNumbersData.countMatchingNumber(lotto);

        // then
        org.junit.jupiter.api.Assertions.assertEquals(matchingNumber, 3);
    }
}