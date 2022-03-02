package org.tnmk.practicejson.pro01jsondynamictype.item.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ItemsCountsService {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private static final Map<Integer, Integer> itemCountsMapByItemId = new ConcurrentHashMap<>();

  @Async
  public synchronized void increaseCountAsync(Integer itemId) {
    synchronized (itemId) {
      Integer count = currentCount(itemId);
      count++;
      logger.info("count for item {} is {}", itemId, count);
      itemCountsMapByItemId.put(itemId, count);
    }
  }

  public Integer currentCount(Integer itemId) {
    Integer count = itemCountsMapByItemId.get(itemId);
    return count != null ? count : 0;
  }
}
