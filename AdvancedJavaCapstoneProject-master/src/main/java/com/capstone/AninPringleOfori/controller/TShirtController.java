package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.TShirtDao;
import com.capstone.AninPringleOfori.model.item.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TShirtController {

    @Autowired
    TShirtDao tShirtDao;


    @RequestMapping(value = "/tshirt/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt createtShirt(@RequestBody @Valid TShirt tShirt) {
        return tShirtDao.addtShirt(tShirt);
    }


    @RequestMapping(value = "/tshirt/id/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TShirt gettShirt(@PathVariable int id) {
        return tShirtDao.findById(id);
    }


    @RequestMapping(value = "/tshirt/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAlltShirts() {
        return tShirtDao.findAlltShirts();
    }


    @RequestMapping(value = "/tshirt/size/{size}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> gettShirtBySize(@PathVariable String size) {
        return tShirtDao.findtShirtBySize(size);
    }


    @RequestMapping(value = "/tshirt/color/{color}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> gettShirtsByColor(@PathVariable String color) {
        return tShirtDao.findtShirtByColor(color);
    }


    @RequestMapping(value = "/tshirt/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatetShirt(@RequestBody @Valid TShirt tShirt) {
        tShirtDao.updatetShirt(tShirt);
    }


    @RequestMapping(value = "/tshirt/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletetShirt(@PathVariable int id) {
        tShirtDao.deletetShirt(id);
    }
}
