package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.ConsoleDao;
import com.capstone.AninPringleOfori.model.item.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleDao consoleDao;


    @RequestMapping(value = "/console/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody @Valid Console console) {
        return consoleDao.addConsole(console);
    }


    @RequestMapping(value = "/console/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Console getConsole(@PathVariable int id) {
        return consoleDao.findById(id);
    }


    @RequestMapping(value = "/console/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsoles() {
        return consoleDao.findAllConsoles();
    }


    @RequestMapping(value = "/console/manufacturer/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleDao.findConsolesByManufacturer(manufacturer);
    }


    @RequestMapping(value = "/console/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateConsole(@RequestBody @Valid Console console) {
        consoleDao.updateConsole(console);
    }


    @RequestMapping(value = "/console/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        consoleDao.deleteConsole(id);
    }
}
