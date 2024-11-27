package racingcar;

import org.junit.platform.commons.util.StringUtils;
import racingcar.strategy.ForwardCondition;

public class Car {

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    public static final int START_POSITION = 0;

    private final String name;
    private int position = START_POSITION;

    public Car(String name) {
        throwIfNameContainsWhiteSpace(name);
        throwIfNameLengthOver(name);

        this.name = name;
    }

    public void moveForward(ForwardCondition condition) {
        if (condition.test()) {
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
}
