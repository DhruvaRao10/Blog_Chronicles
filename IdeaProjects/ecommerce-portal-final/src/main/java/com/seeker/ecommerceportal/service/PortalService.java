package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.entity.*;
import com.seeker.ecommerceportal.error.ItemNotFoundException;

import java.util.List;
public interface PortalService {


    public List<Item> fetchItemsList();
    public Customer saveCustomer(Customer customer);
    ShoppingCart fetchShoppingCartById(Long item_id) throws  ItemNotFoundException ;

    ShoppingCartPayload saveShoppingCart(ShoppingCartPayload shoppingCartPayload);


    public Item fetchItemsById(Long customer_id) throws ItemNotFoundException;

    public  List<ShoppingCart> fetchShoppingCartList();

    Returns saveReturn(Long orderItemId) ;

    Reviews saveReview(ReviewPayload payload);

    Promotions savePromotion(PromotionPayload payload);

    CustomerOrderPayload saveCustomerOrder(CustomerOrderPayload customerOrderPayload);



    public List<CustomerOrder> getAllOrdersForCustomer(Long customer_id);
}