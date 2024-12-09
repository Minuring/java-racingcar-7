package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import racingcar.strategy.ForwardCondition;
import racingcar.strategy.RandomForwardCondition;

public class CarMoveTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car("Car_A");
    }

    @DisplayName("전진 조건이 충족되면 전진한다.")
    @Test
    void moveForwardPassedCondition() {
        int beforePosition = car.getPosition();
        car.moveForward(() -> true);
        int afterPosition = car.getPosition();

        assertThat(afterPosition).isGreaterThan(beforePosition);
    }

    @DisplayName("전진 조건이 충족되지 않으면 전진하지 않는다.")
    @Test
    void moveForwardFailedCondition() {
        int beforePosition = car.getPosition();
        car.moveForward(() -> false);
        int afterPosition = car.getPosition();

        assertThat(afterPosition).isEqualTo(beforePosition);
    }

    @DisplayName("자동차가 자신만의 전진 조건을 가지면 그 조건에 따라서만 전진한다. : 항상 전진")
    @RepeatedTest(10)
    void createCarWithForwardConditionAlwaysTrue(RepetitionInfo repetitionInfo) {
        Car carAlwaysMove = new Car("Car", 0, () -> true);
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        callMoveForwardInjectingRandomCondition(currentRepetition, carAlwaysMove);

        assertThat(carAlwaysMove.getPosition()).isEqualTo(currentRepetition);
    }

    @DisplayName("자동차가 자신만의 전진 조건을 가지면 그 조건에 따라서만 전진한다. : 항상 정지")
    @RepeatedTest(10)
    void createCarWithForwardConditionAlwaysFalse(RepetitionInfo repetitionInfo) {
        Car carAlwaysDontMove = new Car("Car", 0, () -> false);
        callMoveForwardInjectingRandomCondition(repetitionInfo.getCurrentRepetition(),
            carAlwaysDontMove);

        assertThat(carAlwaysDontMove.getPosition()).isEqualTo(0);
    }

    private static void callMoveForwardInjectingRandomCondition(int repetitions, Car car) {
        ForwardCondition byRandom = new RandomForwardCondition();
        while (repetitions-- > 0) {
            car.moveForward(byRandom);
        }
    }
}
