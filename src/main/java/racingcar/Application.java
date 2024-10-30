package racingcar;

import java.util.List;
import racingcar.application.config.AppConfig;
import racingcar.application.config.BeanFactory;
import racingcar.application.converter.CarNamesConverter;
import racingcar.application.converter.CountConverter;
import racingcar.domain.car.Car;
import racingcar.domain.car.ForwardPolicy;
import racingcar.domain.game.Display;
import racingcar.domain.game.Game;
import racingcar.io.InputStringReader;

public class Application {

    public static void main(String[] args) {
        BeanFactory beanFactory = null;
        try {
            beanFactory = BeanFactory.getInstance(AppConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        InputStringReader reader = beanFactory.get(InputStringReader.class);
        CarNamesConverter carNamesConverter = beanFactory.get(CarNamesConverter.class);
        CountConverter countConverter = beanFactory.get(CountConverter.class);
        Display display = beanFactory.get(Display.class);
        ForwardPolicy forwardPolicy = beanFactory.get(ForwardPolicy.class);

        String rawCarNames = reader.readCarNames();
        List<Car> cars = carNamesConverter.convert(rawCarNames);

        String count = reader.readCount();
        int repetitions = countConverter.convert(count);

        Game game = Game.of(cars, display);
        game.start(repetitions, forwardPolicy);
    }
}
