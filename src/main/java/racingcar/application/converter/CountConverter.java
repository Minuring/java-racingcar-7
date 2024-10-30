package racingcar.application.converter;

import racingcar.application.validation.CountValidator;

public class CountConverter extends InputStringConverter<Integer> {

    private final CountValidator validator;

    public CountConverter(CountValidator validator) {
        this.validator = validator;
    }

    @Override
    protected Integer doConvert(String count) {
        return Integer.parseInt(count);
    }

    @Override
    protected void validate(String count) {
        validator.validate(count);
    }
}
