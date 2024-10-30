package racingcar.application.config;

import racingcar.application.RandomForwardPolicy;
import racingcar.application.converter.CarNamesConverter;
import racingcar.application.converter.CountConverter;
import racingcar.application.validation.CarNamesValidator;
import racingcar.application.validation.CountValidator;
import racingcar.domain.car.ForwardPolicy;
import racingcar.domain.game.Display;
import racingcar.io.DisplayImpl;
import racingcar.io.InputStringReader;

public class AppConfig {

    public CarNamesValidator carNamesValidator() {
        return new CarNamesValidator();
    }

    public CountValidator countValidator() {
        return new CountValidator();
    }

    public Display display() {
        return new DisplayImpl();
    }

    public ForwardPolicy forwardPolicy() {
        return new RandomForwardPolicy();
    }

    public InputStringReader inputStringReader() {
        return new InputStringReader();
    }

    public CarNamesConverter carNamesConverter() {
        return new CarNamesConverter(carNamesValidator());
    }

    public CountConverter countConverter() {
        return new CountConverter(countValidator());
    }
}
