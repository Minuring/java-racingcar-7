package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest extends IOTest {

    Car pobi = new Car("pobi");
    Car woni = new Car("woni");

    @DisplayName("한 자동차가 우승한 경우 자동차 이름만을 출력한다.")
    @Test
    void printOnlyOneWinner() {
        OutputView.printWinners(List.of(pobi));
        assertThat(systemOutput())
            .contains("최종 우승자 : ")
            .contains("pobi");
    }

    @DisplayName("여러 자동차가 우승한 경우 쉼표로 구분해 출력한다.")
    @Test
    void printWinners() {
        OutputView.printWinners(List.of(pobi, woni));
        assertThat(systemOutput())
            .contains("최종 우승자 : ")
            .contains("pobi, woni");
    }
}