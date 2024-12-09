package racingcar;

import java.util.List;
import racingcar.strategy.RandomForwardCondition;

public class Application {
    public static void main(String[] args) {
        List<Car> cars = InputView.readCars();
        int repetitions = InputView.readRepetitions();

        RacingGame game = new RacingGame(cars);
        game.proceed(repetitions, new RandomForwardCondition());
        List<Car> winners = game.getWinners();

        OutputView.printWinners(winners);
    }
}
