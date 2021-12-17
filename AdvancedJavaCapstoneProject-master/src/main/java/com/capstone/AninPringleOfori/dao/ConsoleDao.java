package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Console;

import java.util.List;

public interface ConsoleDao extends ItemDao {
    public Console addConsole(Console console);
    public void deleteConsole(int id);
    public Console findById(int id);
    public void updateConsole(Console console);
    public List<Console> findAllConsoles();
    public List<Console> findConsolesByManufacturer(String manufacturer);
}
