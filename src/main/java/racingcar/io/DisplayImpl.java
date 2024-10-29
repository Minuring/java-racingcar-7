package racingcar.io;

import java.util.List;
import racingcar.domain.car.Car;
import racingcar.domain.game.Display;

public class DisplayImpl implements Display {

    @Override
    public void header() {
        System.out.print(HEADER_LABEL);
    }

    @Override
    public void progress(List<Car> cars) {
        cars.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public void winners(List<Car> winners) {
        List<String> names = winners.stream()
            .map(Car::getName)
            .toList();
        String winnerNames = String.join(", ", names);

        System.out.print(WINNERS_LABEL + winnerNames);
    }
}
