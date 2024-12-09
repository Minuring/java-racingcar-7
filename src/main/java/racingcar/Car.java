package racingcar;

import java.util.Optional;
import org.junit.platform.commons.util.StringUtils;
import racingcar.strategy.ForwardCondition;

public class Car {

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    public static final int START_POSITION = 0;

    private final String name;
    private int position = START_POSITION;
    private Optional<ForwardCondition> forwardCondition = Optional.empty();

    public Car(String name) {
        throwIfNameContainsWhiteSpace(name);
        throwIfNameLengthOver(name);
        this.name = name;
    }

    public Car(String name, int startPosition) {
        this(name);
        this.position = startPosition;
    }

    public Car(String name, int startPosition, ForwardCondition forwardCondition) {
        this(name, startPosition);
        this.forwardCondition = Optional.of(forwardCondition);
    }

    public void moveForward(ForwardCondition condition) {
        Boolean canMoveForward = this.forwardCondition
            .map(ForwardCondition::test)
            .orElseGet(condition::test);

        if (canMoveForward) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    private void throwIfNameContainsWhiteSpace(String name) {
        if (name.isBlank() || StringUtils.containsWhitespace(name)) {
            throw new IllegalArgumentException("자동차 이름에 공백을 포함할 수 없습니다.");
        }
    }

    private void throwIfNameLengthOver(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 " + MAX_NAME_LENGTH + "자를 넘을 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        String progress = "-".repeat(position);
        return name + " : " + progress;
    }
}
