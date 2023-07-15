package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.Repository.ItemRepository;

import com.seeker.ecommerceportal.Repository.CategoryRepository;

import com.seeker.ecommerceportal.Repository.CustomerRepository;

import com.seeker.ecommerceportal.Repository.CustomerOrderRepository;
import com.seeker.ecommerceportal.Repository.OrderItemRepository;
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
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    public Category saveCategory(Category category) { return categoryRepository.save(category) ;}

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

//    @Override
//    public Category findCategoryById(Long categoryId) {
//        Category category = categoryRepository.findById(categoryId).get();
//        return category;
//    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer fetchCustomerById(Long customer_id) throws ItemNotFoundException {
        Optional<Customer> customer =  customerRepository.findById(customer_id);
        if(!customer.isPresent()){
            throw new ItemNotFoundException("Customer Signup Not Found ");
        }
        return customer.get();
    }

    public CustomerOrderPayload saveCustomerOrder(CustomerOrderPayload customerOrderPayload){
        Customer customer = customerRepository.findById(customerOrderPayload.getCustomer_id()).get();
        LOGGER.info(customer.toString());
        List<OrderItemPayload> orderItemPayloadList = customerOrderPayload.getOrderItems();
        LOGGER.info(" Count of customer order items "+orderItemPayloadList.size());

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setShippingAddress(customerOrderPayload.getShippingAddress());
        customerOrder.setOrderDate(java.time.Instant.now());
        customerOrderRepository.save(customerOrder);
        java.util.Iterator iter = orderItemPayloadList.iterator();

        LOGGER.info("Iter = "+iter);
        OrderItemPayload orderItemPayload;
        OrderItem orderItem;
        LOGGER.info("list = "+orderItemPayloadList.toString());
        LOGGER.info("orderItemPayload[1] "+ orderItemPayloadList.get(0).toString());

        for(int i = 0; i < orderItemPayloadList.size(); i++){
            orderItemPayload = (OrderItemPayload)  orderItemPayloadList.get(i);
            LOGGER.info("orderItemPayload = "+ orderItemPayload.toString());
            orderItem = new OrderItem();
            orderItem.setItem(itemRepository.findById(orderItemPayload.getItem_id()).get());
            orderItem.setOrder(customerOrder);
            orderItem.setOrderItemQty(orderItemPayload.getOrderitemqty());
            orderItemRepository.save(orderItem);
        }
        return customerOrderPayload;
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
}


