package org.tnmk.practicejson.pro01jsondynamictype.item.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service

public class ItemService {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final ItemsCountsService itemsCountsService;

  public ItemService(ItemsCountsService itemsCountsService) {
    this.itemsCountsService = itemsCountsService;
  }

  public void createItem(int itemId) {
    logger.info("Created item '{}'", itemId);
    itemsCountsService.increaseCountAsync(itemId);
  }
}
