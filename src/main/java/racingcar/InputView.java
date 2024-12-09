package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;

public class InputView {

    public static List<Car> readCars() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();

        return asListCarNames(input);
    }

    private static List<Car> asListCarNames(String input) {
        String[] inputCarNames = input.split(",");
        if (inputCarNames.length < 2) {
            throw new IllegalArgumentException("자동차가 최소 두 대 이상 있어야합니다.");
        }

        return Arrays.stream(inputCarNames)
            .map(Car::new)
            .toList();
    }
}
