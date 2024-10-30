package racingcar.application.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeanFactoryInitializer {

    private final Set<String> beanNames = new HashSet<>();
    private Object configurationInstance;
    private Map<Class<?>, Object> container = new HashMap<>();

    public Map<Class<?>, Object> getContainer() {
        return container;
    }

    public void register(Class<?> configurationClass) throws Exception {
        this.configurationInstance = configurationClass.getConstructor().newInstance();

        registerBeanNames(configurationClass);
        scan(configurationClass);
    }

    private void registerBeanNames(Class<?> configurationClass) throws Exception {
        Method[] declaredMethods = configurationClass.getDeclaredMethods();

        Arrays.stream(declaredMethods)
            .map(Method::getName)
            .map(this::toLowerCaseFirstLetter)
            .forEach(beanNames::add);
    }

    private void scan(Class<?> configurationClass) throws Exception {
        Method[] methods = configurationClass.getDeclaredMethods();

        for (Method method : methods) {
            Class<?> beanType = method.getReturnType();
            Object bean = method.invoke(configurationInstance);

            registerBean(beanType, bean);
        }

        // 등록된 빈의 필드에 컨테이너의 빈과 타입은 같지만 인스턴스가 다른 객체가 존재할 수 있음
        // 따라서 필드에 있는 객체를 컨테이너에 있는 객체로 교체
        replaceFieldBeansToContainerBeans();
    }

    private void registerBean(Class<?> beanType, Object bean) {
        if (container.containsKey(beanType)) {
            throw new IllegalStateException("등록할 타입이 겹칩니다. : " + beanType.getName());
        }
        container.put(beanType, bean);
    }

    private void replaceFieldBeansToContainerBeans() throws IllegalAccessException {
        for (Class<?> beanType : container.keySet()) {
            Object bean = container.get(beanType);
            replaceFieldBeanToContainerBean(bean);
        }
    }

    private void replaceFieldBeanToContainerBean(Object bean) throws IllegalAccessException {
        Class<?> beanType = bean.getClass();
        Field[] fieldsInBean = beanType.getDeclaredFields();

        for (Field field : fieldsInBean) {
            if (isOneOfBeans(field)) {
                Object containerBeanAboutField = container.get(field.getType());

                field.setAccessible(true);
                field.set(bean, containerBeanAboutField);
            }
        }
    }

    private boolean isOneOfBeans(Field field) {
        String fieldName = field.getName();
        String fieldTypeBeanName = toLowerCaseFirstLetter(field.getType().getSimpleName());
        return beanNames.contains(fieldName)
            || beanNames.contains(fieldTypeBeanName);
    }

    private String toLowerCaseFirstLetter(String input) {
        char lowerCase = Character.toLowerCase(input.charAt(0));
        return lowerCase + input.substring(1);
    }

}
