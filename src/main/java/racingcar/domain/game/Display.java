package racingcar.domain.game;

import java.util.List;
import racingcar.domain.car.Car;

public interface Display {

    public static final String HEADER_LABEL = "\n실행 결과\n";
    public static final String WINNERS_LABEL = "최종 우승자 : ";

    void header();
    void progress(List<Car> cars);
    void winners(List<Car> winners);
}
