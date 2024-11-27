package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
