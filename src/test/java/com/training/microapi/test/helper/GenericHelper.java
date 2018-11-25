package com.training.microapi.test.helper;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class GenericHelper {

    public static Answer<?> createDefaultAnswer() {
        return (InvocationOnMock invocation) -> {
            return invocation.getArguments()[0];
        };
    }
}