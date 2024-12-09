package racingcar;

import java.util.List;

public class OutputView {

    public static void printCars(List<Car> cars) {
        cars.forEach(System.out::println);
        System.out.println();
    }
}
