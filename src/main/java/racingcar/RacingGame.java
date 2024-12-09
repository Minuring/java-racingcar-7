package racingcar;

import java.util.List;
import racingcar.strategy.ForwardCondition;

public class RacingGame {

    private final List<Car> cars;

    public RacingGame(List<Car> cars) {
        if (cars.size() < 2) {
            throw new IllegalArgumentException("자동차가 최소 두 대 이상 있어야합니다.");
        }
        this.cars = cars;
    }

    public void proceed(int repetitions, ForwardCondition forwardCondition) {
        while (repetitions-- > 0) {
            cars.forEach(car -> car.moveForward(forwardCondition));
            OutputView.printCars(cars);
        }
    }

    public List<Car> getWinners() {
        int winnerPosition = getWinnerPosition();
        return cars.stream()
            .filter(car -> car.getPosition() == winnerPosition)
            .toList();
    }

    private int getWinnerPosition() {
        return cars.stream()
            .max(Car::compareTo)
            .map(Car::getPosition)
            .orElse(Car.START_POSITION);
    }
}
