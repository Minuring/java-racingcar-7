package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.strategy.RandomForwardCondition;

public class RacingGameTest {

    RacingGame racingGame;
    Car car1;
    Car car2;
    Car car3;

    @BeforeEach
    void setUp() {
        car1 = new Car("Car1", 0, () -> true);
        car2 = new Car("Car2", 1, () -> false);
        car3 = new Car("Car3", 2, () -> true);

        racingGame = new RacingGame();
        racingGame.addCars(car1, car2, car3);
    }

    @DisplayName("자동차 경주 게임을 진행한다.")
    @Test
    void proceedRacingGame() {
        racingGame.proceed(5, new RandomForwardCondition());

        assertThat(car1.getPosition()).isEqualTo(5); // 0 + 5x1
        assertThat(car2.getPosition()).isEqualTo(1); // 1 + 5x0
        assertThat(car3.getPosition()).isEqualTo(7); // 2 + 5x1
    }
}
