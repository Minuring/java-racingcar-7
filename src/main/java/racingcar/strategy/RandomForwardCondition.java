package racingcar.strategy;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class RandomForwardCondition implements ForwardCondition {

    private static final int RANGE_MIN = 0;
    private static final int RANGE_MAX = 9;
    private static final int FORWARDING_THRESHOLD = 4;

    @Override
    public boolean test() {
        return pickNumberInRange(RANGE_MIN, RANGE_MAX) >= FORWARDING_THRESHOLD;
    }
}
