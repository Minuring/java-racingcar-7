package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import racingcar.application.config.BeanFactory;

class BeanFactoryTest {

    @Test
    public void 스캔_테스트() throws Exception {
        //given

        //when
        BeanFactory beanFactory = BeanFactory.getInstance(TestConfig.class);

        TestClassB testClassBInTestClassAField = ((TestClassA) beanFactory.get(TestClassA.class)).getTestClassB();
        TestClassB testClassBInContainer = (TestClassB) beanFactory.get(TestClassB.class);

        //then
        assertThat(testClassBInContainer).isEqualTo(testClassBInTestClassAField);
        assertThat(testClassBInContainer == testClassBInTestClassAField).isTrue();
    }


    static class TestConfig {

        public TestConfig() {
        }

        public TestClassA testClassA() {
            return new TestClassA(testClassB());
        }

        public TestClassB testClassB() {
            return new TestClassB();
        }
    }

    static class TestClassA {

        private final TestClassB testClassB;

        TestClassA(TestClassB testClassB) {
            this.testClassB = testClassB;
        }

        public TestClassB getTestClassB() {
            return testClassB;
        }
    }

    static class TestClassB {

        TestClassB() {
        }
    }
}