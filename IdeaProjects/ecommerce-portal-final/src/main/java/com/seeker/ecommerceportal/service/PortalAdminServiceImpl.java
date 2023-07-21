package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.Repository.ItemRepository;

import com.seeker.ecommerceportal.Repository.CategoryRepository;

import com.seeker.ecommerceportal.Repository.CustomerRepository;


import com.seeker.ecommerceportal.Repository.*;
import com.seeker.ecommerceportal.entity.*;
import com.seeker.ecommerceportal.error.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class  PortalAdminServiceImpl implements  PortalAdminService {

    private final Logger LOGGER = LoggerFactory.getLogger("name:PortalAdminServiceImpl");
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ShipmentStageRepository shipmentStageRepository;

    @Autowired
    private ShipmentTrackerRepository shipmentTrackerRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public Category saveCategory(Category category) { return categoryRepository.save(category) ;}

    @Override
    public Category findCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        return category;
    }

    @Override
    public List<Category> fetchCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public Category fetchCategoryById(Long category_id) throws ItemNotFoundException {
        Optional<Category> category = categoryRepository.findById(category_id);
        if(!category.isPresent()){
            throw new ItemNotFoundException("Category of Item Not Found ");
        }
        return category.get();
    }

    @Override
    public Customer fetchCustomerById(Long customer_id) throws ItemNotFoundException {
        Optional<Customer> customer =  customerRepository.findById(customer_id);
        if(!customer.isPresent()){
            throw new ItemNotFoundException("Customer Signup Not Found ");
        }
        return customer.get();
    }


    @Override
    public List<Customer> fetchCustomerList() { // check again
        return customerRepository.findAll();
    }

    @Override
    public CustomerOrder fetchCustomerOrderById(Long order_id) throws ItemNotFoundException {
        Optional<CustomerOrder> customerOrder =  customerOrderRepository.findById(order_id);
        if(!customerOrder.isPresent()){
            throw new ItemNotFoundException("Customer Order Not Found ");
        }
        return customerOrder.get();

    }

    @Override
    public List<CustomerOrder> fetchCustomerOrderList() {
        return customerOrderRepository.findAll() ;
    }

    public Shipment saveShipment(Long orderId){
        Shipment shipment = new Shipment();
        shipment.setOrder(customerOrderRepository.findById(orderId).get());
        return shipmentRepository.save(shipment);
    }

    public ShipmentStage saveShipmentStage(ShipmentStagePayload shipmentStagePayload){
        ShipmentStage stage = new ShipmentStage();
        stage.setShipment(shipmentRepository.findById(shipmentStagePayload.getShipment_id()).get());
        stage.setStageName(shipmentStagePayload.getStageName());
        stage.setStageOrder(shipmentStagePayload.getStageOrder());
        return shipmentStageRepository.save(stage);
    }
    public ShipmentTracker saveShipmentTracker(ShipmentTrackerPayload shipmentTrackerPayload){
        ShipmentTracker shipmentTracker = new ShipmentTracker();
        shipmentTracker.setCurrentStageOfShipment(shipmentStageRepository.findById(shipmentTrackerPayload.getShipmentstage_id()).get());
        return shipmentTrackerRepository.save(shipmentTracker);
    }



    @Override
    public Inventory addInventoryById(Long itemId,Long itemQty) {
        Inventory inventory = inventoryRepository.findById(itemId).get() ;
        Long existingQty = inventory.getItem_qty() ;
        inventory.setItem_qty(itemQty+existingQty);
        inventoryRepository.update(inventory);
        return inventory;
//        inventoryRepository.findById(itemQty) ;

    }


}