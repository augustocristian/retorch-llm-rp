package giis.retorch.llmrp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class TestClass1 {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() {
        log.info("****** Running test: {} ******", testName.getMethodName());
    }

    @Test
    public void testFunction11() {
        assertEquals("11", "11");
    }

}
