package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.strategy.RandomForwardCondition;

public class RacingGameTest extends IOTest {

    RacingGame racingGame;
    Car car1;
    Car car2;
    Car car3;

    @BeforeEach
    void setUp() {
        super.setUp();
        car1 = new Car("Car1", 0, () -> true);
        car2 = new Car("Car2", 1, () -> false);
        car3 = new Car("Car3", 2, () -> true);

        racingGame = new RacingGame(List.of(car1, car2, car3));
    }

    @DisplayName("자동차가 두 대 미만인 경우 예외가 발생한다.")
    @Test
    void lessThanTwoCarsRaisesException() {
        assertThatThrownBy(() -> new RacingGame(List.of(car1)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자동차는 2대 이상 50대 이하여야 합니다.");
    }

    @DisplayName("자동차 경주 게임을 진행한다.")
    @Test
    void proceedRacingGame() {
        racingGame.proceed(5, new RandomForwardCondition());

        assertThat(car1.getPosition()).isEqualTo(5); // 0 + 5x1
        assertThat(car2.getPosition()).isEqualTo(1); // 1 + 5x0
        assertThat(car3.getPosition()).isEqualTo(7); // 2 + 5x1
    }

    @DisplayName("자동차 전진 횟수가 1이상 50이하가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 51})
    void repetitionsNotInValidRange(int reps) {
        assertThatThrownBy(
            () -> racingGame.proceed(reps, new RandomForwardCondition()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자동차는 1번 이상 50번 이하만큼 전진시킬 수 있습니다.");
    }

    @DisplayName("자동차 경주 진행 과정을 출력한다.")
    @Test
    void proceedRacingGamePrintCars() {
        racingGame.proceed(2, new RandomForwardCondition());
        assertThat(systemOutput()).contains(
            "Car1 : -", "Car2 : -", "Car3 : ---",
            "Car1 : --", "Car2 : -", "Car3 : ----");
    }

    @DisplayName("자동차 경주 우승자를 알려준다.")
    @Test
    void getWinnersOfRacingGame() {
        racingGame.proceed(5, new RandomForwardCondition());
        List<Car> winners = racingGame.getWinners();

        assertThat(winners).containsOnly(car3);
    }
}
