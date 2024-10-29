package racingcar.application;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.domain.car.ForwardPolicy;

public class RandomForwardPolicy implements ForwardPolicy {

    private static final int SUCCESS_THRESHOLD = 4;
    private static final int RANDOM_INT_FROM = 0;
    private static final int RANDOM_INT_TO = 9;

    @Override
    public boolean forward() {
        return SUCCESS_THRESHOLD <= Randoms.pickNumberInRange(RANDOM_INT_FROM, RANDOM_INT_TO);
    }
}
