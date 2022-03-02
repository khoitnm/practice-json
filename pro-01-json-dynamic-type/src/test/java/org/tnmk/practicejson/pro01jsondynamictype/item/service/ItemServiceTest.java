package org.tnmk.practicejson.pro01jsondynamictype.item.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.tnmk.practicejson.pro01jsondynamictype.common.test.AwaitAssertions;

import java.lang.invoke.MethodHandles;
import java.time.ZonedDateTime;

@ActiveProfiles("test")
@SpringBootTest
public class ItemServiceTest {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  private ItemService itemService;

  @Autowired
  private ItemsCountsService itemsCountsService;

  @Test()
  public void test_whenAssertWithoutWaitingAsync_thenResultWillFail() {
    int itemId = (int) System.nanoTime();

    int currentCount = itemsCountsService.currentCount(itemId);
    int loopCount = 50;
    for (int i = 0; i < loopCount; i++) {
      itemService.createItem(itemId);
    }

    // It won't be equals because of the async:
    //  when running this assertion, the itemsCountsService.increaseCountAsync(...) may not even finish, so it will fails.
    int newCount = itemsCountsService.currentCount(itemId);
    Assertions.assertNotEquals(currentCount + loopCount, newCount);
  }

  @Test()
  public void test_whenAssertWaitForAsync_thenResultWillSuccess() {
    int itemId = (int) System.nanoTime();
    int currentCount = itemsCountsService.currentCount(itemId);
    int loopCount = 10;
    for (int i = 0; i < loopCount; i++) {
      itemService.createItem(itemId);
    }
    logger.info("Start assertion timeout " + ZonedDateTime.now());
    AwaitAssertions.await(2, (context) -> {
      logger.info("assertion is checking " + ZonedDateTime.now());
      int newCount = itemsCountsService.currentCount(itemId);
      Assertions.assertEquals(currentCount + loopCount, newCount, "count number is not as expected");
    });
    logger.info("End assertion timeout " + ZonedDateTime.now());
  }

}
