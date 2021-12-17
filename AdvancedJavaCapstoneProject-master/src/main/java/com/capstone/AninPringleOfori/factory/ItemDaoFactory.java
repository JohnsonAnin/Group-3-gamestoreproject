package com.capstone.AninPringleOfori.factory;

import com.capstone.AninPringleOfori.dao.ConsoleDao;
import com.capstone.AninPringleOfori.dao.GameDao;
import com.capstone.AninPringleOfori.dao.ItemDao;
import com.capstone.AninPringleOfori.dao.TShirtDao;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ItemDaoFactory {
    final GameDao gameDao;
    final TShirtDao tShirtDao;
    final ConsoleDao consoleDao;
    final Map<String, ItemDao> orderUpdateDaoMap;


    public ItemDaoFactory(GameDao gameDao, TShirtDao tShirtDao, ConsoleDao consoleDao) {
        this.gameDao = gameDao;
        this.tShirtDao = tShirtDao;
        this.consoleDao = consoleDao;
        orderUpdateDaoMap = ImmutableMap.of(
                "game", gameDao,
                "tshirt", tShirtDao,
                "console", consoleDao);
    }

    public ItemDao getDaoInstance(String itemType) {
        if (orderUpdateDaoMap.containsKey(itemType)) {
            return orderUpdateDaoMap.get(itemType.toLowerCase());
        }
        throw new IllegalArgumentException("Invalid itemType specified: Must be 'tshirt', 'game' or 'console'.");
    }
}
