package extentions;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class Logging implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("=========== Test is starting ===========");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        System.out.println("=========== Test is ending ===========");
    }
}
