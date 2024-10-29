package racingcar.io;

import camp.nextstep.edu.missionutils.Console;

public class InputStringReader {

    private static final String CAR_NAMES_LABEL = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String COUNT_LABEL = "시도할 횟수는 몇 회인가요?";

    public String readCarNames() {
        System.out.println(CAR_NAMES_LABEL);
        return Console.readLine();
    }

    public String readCount() {
        System.out.println(COUNT_LABEL);
        return Console.readLine();
    }
}
