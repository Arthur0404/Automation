package org.tophap.runner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class SingleTest extends BaseTest {

    @BeforeEach
    private void setUp() throws MalformedURLException {
        startTest();
    }

    @AfterEach
    public void setDown() {
        finishTest();
    }
}
