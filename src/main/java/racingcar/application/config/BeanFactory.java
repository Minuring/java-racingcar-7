package racingcar.application.config;

import java.util.Map;

public class BeanFactory {

    private Map<Class<?>, Object> container;
    private boolean initialized = false;

    private BeanFactory() {
    }

    private static class SingletonHolder {
        private static final BeanFactory INSTANCE = new BeanFactory();
    }

    public static BeanFactory getInstance(Class<?> configurationClass) throws Exception {
        BeanFactory instance = SingletonHolder.INSTANCE;
        if (instance.isInitialized()) {
            return instance;
        }

        instance.initializeConfiguration(configurationClass);
        return instance;
    }

    public boolean isInitialized() {
        return initialized;
    }

    private void initializeConfiguration(Class<?> configurationClass) throws Exception {
        BeanFactoryInitializer beanFactoryInitializer = new BeanFactoryInitializer();
        beanFactoryInitializer.register(configurationClass);
        this.container = beanFactoryInitializer.getContainer();
        this.initialized = true;
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(container.get(clazz));
    }
}
