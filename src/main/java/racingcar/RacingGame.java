package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import racingcar.strategy.ForwardCondition;

public class RacingGame {
    
    private final List<Car> cars = new ArrayList<>();
    
    public void addCars(Car... cars) {
        this.cars.addAll(Arrays.asList(cars));
    }
    
    public void proceed(int repetitions, ForwardCondition forwardCondition) {
        while (repetitions-- > 0) {
            cars.forEach(car -> car.moveForward(forwardCondition));
            OutputView.printCars(cars);
        }
    }
}
