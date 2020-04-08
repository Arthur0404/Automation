package org.walmart.runner;

import org.junit.jupiter.api.*;
import java.io.IOException;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MultipleTest extends BaseTest {

    @BeforeAll
    private void setUpAll() throws IOException {
        initAll();
        startTest();
    }

    @AfterAll
    private void tearDownAll() {
        quitTest();
    }

}
