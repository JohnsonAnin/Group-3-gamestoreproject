package com.capstone.AninPringleOfori.controller;

import com.capstone.AninPringleOfori.dao.ItemDao;
import com.capstone.AninPringleOfori.model.order.Invoice;
import com.capstone.AninPringleOfori.model.order.Order;
import com.capstone.AninPringleOfori.service.ServiceLayer;
import com.capstone.AninPringleOfori.view.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    ServiceLayer serviceLayer;


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Invoice placeOrder(@RequestBody @Valid Order order) {
        final ItemDao itemDao = serviceLayer.getDao(order.getItemType());
        final OrderViewModel orderViewModel = new OrderViewModel();

        orderViewModel.setName(order.getName());
        orderViewModel.setStreet(order.getStreet());
        orderViewModel.setCity(order.getCity());
        orderViewModel.setState(order.getState());
        orderViewModel.setZipCode(order.getZipCode());
        orderViewModel.setItemType(order.getItemType());
        orderViewModel.setItem(itemDao.findById(order.getItemId()));
        orderViewModel.setOrderQuantity(order.getOrderQuantity());

        return serviceLayer.generateInvoice(orderViewModel);
    }
}
