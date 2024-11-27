package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @DisplayName("5자 이하의 이름을 가지는 자동차를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"A", "AB", "abc5", "ABCD", "abcde"})
    void createCarWithValidName(String name) {
        Car car = new Car(name);
        assertThat(car.getName()).isEqualTo(name);
    }

    @DisplayName("자동차 이름이 공백을 포함하면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", " a", "a ", "a b"})
    void createCarWithBlankName(String name) {
        assertThatThrownBy(() -> new Car(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("공백");
    }

    @DisplayName("자동차 이름이 최대 글자수를 넘으면 예외 발생")
    @Test
    void createCarWithOverLengthName() {
        assertThatThrownBy(() -> new Car("ABCDEF"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Car.MAX_NAME_LENGTH + "");
    }
}
