package org.tnmk.practicejson.pro01jsondynamictype.common.test;

import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class AwaitAssertions {
  public static void await(int awaitSeconds, Consumer<Map<String, String>> callable) {
    AtomicReference<AssertionFailedError> assertionFail = new AtomicReference();
    Map<String, String> originalContext = MDC.getCopyOfContextMap();
    try {
      Awaitility.waitAtMost(awaitSeconds, TimeUnit.SECONDS).pollInterval(Durations.ONE_HUNDRED_MILLISECONDS).until(() ->
          {
            // In general:
            // await() method is running on another thread.
            // So need to copy context from the original thread to children threads which will be necessary for:
            //  - logging
            //  - multi-tenant logic (this is just an example, we don't actually have multi-tenant context here).
            if (originalContext != null) {
              MDC.setContextMap(originalContext);
            }
            try {
              callable.accept(originalContext);
              return true;
            } catch (AssertionFailedError ex) {
              assertionFail.set(ex);
              return false;
            }
          }
      );
    } catch (ConditionTimeoutException ex) {
      Assertions.fail("Assertions failed after timeout: " + assertionFail.get().getMessage());
    }
  }
}
