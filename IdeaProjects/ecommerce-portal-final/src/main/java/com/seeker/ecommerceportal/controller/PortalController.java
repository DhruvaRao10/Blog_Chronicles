package com.seeker.ecommerceportal.controller;

import com.seeker.ecommerceportal.entity.*;

import com.seeker.ecommerceportal.error.ItemNotFoundException;
import com.seeker.ecommerceportal.service.PortalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class PortalController {

    @Autowired
    private PortalService portalService;

    private final Logger LOGGER = LoggerFactory.getLogger("name:PortalController");


    @GetMapping("/items")
    public List<Item> fetchItemsList(){
        return portalService.fetchItemsList();
    }


    @GetMapping("/items/{id}")
    public Item fetchCustomerById(@PathVariable("id") Long item_id) throws ItemNotFoundException {
        return portalService.fetchItemsById(item_id);
    }

    @PostMapping("/saveshoppingcart")
    public ShoppingCartPayload saveShoppingCart(@Valid @RequestBody ShoppingCartPayload shoppingCartPayload){
        LOGGER.info("saveShoppingCart function");
        return portalService.saveShoppingCart(shoppingCartPayload);
    }

    @GetMapping("shoppingcart")
    public List<ShoppingCart> fetchShoppingcartList(){
        return portalService.fetchShoppingCartList();
    }

    @GetMapping("/getallordersforcustomer")
    public List<CustomerOrder> getAllOrdersForCustomer(@Valid @RequestBody  Long customerId){
        LOGGER.info("getallordersforcustomer function");
        List<CustomerOrder> orders = portalService.getAllOrdersForCustomer(customerId);
        LOGGER.info(" size " + orders.size());
        return orders;
    }

}
