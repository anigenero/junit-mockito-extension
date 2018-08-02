package com.anigenero.junit.mockito;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Parameter;

import static org.mockito.Mockito.mock;

public class MockitoExtension implements TestInstancePostProcessor, ParameterResolver {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        MockitoAnnotations.initMocks(testInstance);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().isAnnotationPresent(Mock.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getMock(parameterContext.getParameter(), extensionContext);
    }

    /**
     * Gets the mock from the parameter
     *
     * @param parameter {@link Parameter}
     * @param context   {@link ExtensionContext}
     * @return {@link Object}
     */
    private Object getMock(Parameter parameter, ExtensionContext context) {

        final Class<?> mockType = parameter.getType();
        final ExtensionContext.Store mocks = context.getStore(
                ExtensionContext.Namespace.create(MockitoExtension.class, mockType));

        final String mockName = getMockName(parameter);

        if (mockName != null) {
            return mocks.getOrComputeIfAbsent(mockName, key -> mock(mockType, mockName));
        } else {
            return mocks.getOrComputeIfAbsent(mockType.getCanonicalName(), key -> mock(mockType));
        }

    }

    /**
     * Gets the name of the mock
     *
     * @param parameter {@link Parameter}
     * @return {@link String}
     */
    private String getMockName(Parameter parameter) {

        String mockName = parameter.getAnnotation(Mock.class).name().trim();

        if (!mockName.isEmpty()) {
            return mockName;
        } else if (parameter.isNamePresent()) {
            return parameter.getName();
        }

        return null;

    }

}
