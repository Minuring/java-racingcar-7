package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest extends IOTest {

    @DisplayName("자동차 이름들을 자동차로 읽어들인다.")
    @Test
    void readCars() {
        systemIn("carA,carB");
        List<Car> cars = InputView.readCars();

        assertThat(cars).hasSize(2);
        assertThat(cars.get(0).getName()).isEqualTo("carA");
        assertThat(cars.get(1).getName()).isEqualTo("carB");
    }

    @DisplayName("게임 진행 횟수를 읽어들인다.")
    @Test
    void readRepetitions() {
        systemIn("2");
        int repetitions = InputView.readRepetitions();
        assertThat(repetitions).isEqualTo(2);
    }

    @DisplayName("게임 진행 횟수 유효성 검사 실패")
    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "a", "!"})
    void readRepetitionsInvalidInput(String invalidInput) {
        systemIn(invalidInput);
        assertThatThrownBy(InputView::readRepetitions)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("횟수는 반드시 정수여야 합니다.");
    }
}