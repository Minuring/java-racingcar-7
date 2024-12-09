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

    public static int readRepetitions() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();

        return toInteger(input);
    }

    private static List<Car> asListCarNames(String input) {
        String[] inputCarNames = input.split(",");
        return Arrays.stream(inputCarNames)
            .map(Car::new)
            .toList();
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("횟수는 반드시 정수여야 합니다.");
        }
    }
}
