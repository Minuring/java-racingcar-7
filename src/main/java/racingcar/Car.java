package racingcar;

import org.junit.platform.commons.util.StringUtils;

public class Car {

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Car(String name) {
        throwIfNameContainsWhiteSpace(name);
        throwIfNameLengthOver(name);

        this.name = name;
    }

    public String getName() {
        return name;
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
