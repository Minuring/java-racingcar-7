package racingcar.application.validation;

import java.util.List;

public class CarNamesValidator implements Validator {

    private static final String NOT_DUPLICATED = "자동차 이름은 중복될 수 없습니다.";
    private static final String MUST_MORE_THAN_TWO = "자동차는 최소 두 대 이상 있어야합니다.";
    private static final String NOT_CONTAINS_BLANK = "자동차 이름에는 공백이 있을 수 없습니다.";
    private static final String EXCEED_MAX_LENGTH = "자동차 이름은 5자 이하여야 합니다.";

    @Override
    public void validate(String rawString) {
        List<String> nameList = List.of(rawString.split(","));
        validateAltogether(nameList);
        validateEach(nameList);
    }

    private void validateAltogether(List<String> nameList) {
        int size = nameList.size();
        long distinctSize = nameList.stream().distinct().count();

        if (size != distinctSize) {
            throw new IllegalArgumentException(NOT_DUPLICATED);
        }

        if (size <= 1) {
            throw new IllegalArgumentException(MUST_MORE_THAN_TWO);
        }
    }

    private void validateEach(List<String> nameList) {
        nameList.forEach(name -> {
            if (name == null || name.isEmpty() || name.matches(".*\\s+.*")) {
                throw new IllegalArgumentException(NOT_CONTAINS_BLANK);
            }

            if (name.length() > 5) {
                throw new IllegalArgumentException(EXCEED_MAX_LENGTH);
            }
        });
    }
}
