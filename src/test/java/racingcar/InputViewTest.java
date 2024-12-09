package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest extends IOTest{

    @DisplayName("자동차 이름들을 자동차로 읽어들인다.")
    @Test
    void readCars() {
        systemIn("carA,carB");
        List<Car> cars = InputView.readCars();

        assertThat(cars).hasSize(2);
        assertThat(cars.get(0).getName()).isEqualTo("carA");
        assertThat(cars.get(1).getName()).isEqualTo("carB");
    }

    @DisplayName("두 대 미만의 자동차를 입력한 경우 예외가 발생한다.")
    @Test
    void lessThanTwoCarsRaisesException() {
        systemIn("carA");
        assertThatThrownBy(InputView::readCars)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자동차가 최소 두 대 이상 있어야합니다.");
    }
}