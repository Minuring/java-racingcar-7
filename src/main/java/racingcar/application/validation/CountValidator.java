package racingcar.application.validation;

public class CountValidator implements Validator {

    private static final String EMPTY_CONTENTS = "횟수를 입력해주세요.";
    private static final String TYPE_MUST_INTEGER = "정수만 입력 가능합니다.";
    private static final String RANGE_OUT = "1이상 50이하의 정수만 입력 가능합니다.";

    @Override
    public void validate(String string) {
        if (string == null || string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException(EMPTY_CONTENTS);
        }

        string = string.trim();

        if (!string.matches("\\d*")) {
            throw new IllegalArgumentException(TYPE_MUST_INTEGER);
        }

        int value = Integer.parseInt(string);
        if (value <= 0 || value >= 51) {
            throw new IllegalArgumentException(RANGE_OUT);
        }
    }
}
