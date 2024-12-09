package racingcar;

import java.util.List;
import racingcar.strategy.ForwardCondition;

public class RacingGame {

    private static final int MININUM_CARS_SIZE = 2;
    private static final int MAXINUM_CARS_SIZE = 20;
    private static final int MININUM_TRIABLE_COUNT = 1;
    private static final int MAXINUM_TRIABLE_COUNT = 50;

    private final List<Car> cars;

    public RacingGame(List<Car> cars) {
        if (cars.size() < MININUM_CARS_SIZE || cars.size() > MAXINUM_CARS_SIZE) {
            throw new IllegalArgumentException("자동차는 2대 이상 50대 이하여야 합니다.");
        }
        this.cars = cars;
    }

    public void proceed(int repetitions, ForwardCondition forwardCondition) {
        if (repetitions < MININUM_TRIABLE_COUNT || repetitions > MAXINUM_TRIABLE_COUNT) {
            throw new IllegalArgumentException("자동차는 1번 이상 50번 이하만큼 전진시킬 수 있습니다.");
        }
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
