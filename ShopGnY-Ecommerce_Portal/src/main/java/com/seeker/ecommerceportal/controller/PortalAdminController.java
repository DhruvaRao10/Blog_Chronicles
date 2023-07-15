package com.seeker.ecommerceportal.controller;

import com.seeker.ecommerceportal.entity.*;

import com.seeker.ecommerceportal.error.ItemNotFoundException;
import com.seeker.ecommerceportal.service.PortalAdminService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;


@RestController
public class PortalAdminController {


    private final Logger LOGGER = LoggerFactory.getLogger("name:PortalAdminController");

    @Autowired
    private PortalAdminService portalAdminService;

    @PostMapping("/saveitem")
    public Item saveItem(@Valid @RequestBody ItemPayload itemPayload) throws ItemNotFoundException {
        LOGGER.info("saveItem function");
        Category category = portalAdminService.fetchCategoryById(itemPayload.getCategory_id());
        LOGGER.info(category.toString());
        Item item = new Item();
        item.setItem_name(itemPayload.getItem_name());
        item.setCategory(category);
        return portalAdminService.saveItem(item);
    }
    @PostMapping("/savecategory")
    public Category saveCategory(@Valid @RequestBody Category category){
        LOGGER.info("saveCategory function");
        return portalAdminService.saveCategory(category);
    }

    @GetMapping("/category")
    public List<Category> fetchCategoryList(){
        return portalAdminService.fetchCategoryList();
    }
    @GetMapping("/category/{id}")
    public Category fetchCategoryById(@PathVariable("id") Long category_id) throws ItemNotFoundException {
        return portalAdminService.fetchCategoryById(category_id);
    }


    @PostMapping("/signup")
    public Customer saveCustomer(@Valid @RequestBody Customer customer){
        LOGGER.info("saveCustomer function");
        return portalAdminService.saveCustomer(customer);
    }
    @GetMapping("/signup")
    public List<Customer> fetchCustomerList(){
        return portalAdminService.fetchCustomerList();
    }

    @GetMapping("/signup/{id}")
    public Customer fetchCustomerById(@PathVariable("id") Long customer_id) throws ItemNotFoundException {
        return portalAdminService.fetchCustomerById(customer_id);
    }


    @PostMapping("/savecustomerorder")
    public CustomerOrderPayload saveOrder(@Valid @RequestBody CustomerOrderPayload customerOrderPayload){
        LOGGER.info("savecustomerorder function");
        return portalAdminService.saveCustomerOrder(customerOrderPayload);
    }
    @GetMapping("/customerorder")
    public List<CustomerOrder>fetchCustomerOrderList(){
        return portalAdminService.fetchCustomerOrderList() ;
    }


    @GetMapping("/customerorder/{id}")
    public CustomerOrder fetchCustomerOrderById(@PathVariable("id") Long order_id) throws ItemNotFoundException {
        return portalAdminService.fetchCustomerOrderById(order_id);
    }
}
