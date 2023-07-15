package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.entity.Item;
import com.seeker.ecommerceportal.entity.ShoppingCart;
import com.seeker.ecommerceportal.entity.ShoppingCartPayload;
import com.seeker.ecommerceportal.error.ItemNotFoundException;

import java.util.List;
public interface PortalService {

    public List<Item> fetchItemsList();

    ShoppingCart fetchShoppingCartById(Long item_id) throws ItemNotFoundException;

    ShoppingCartPayload saveShoppingCart(ShoppingCartPayload shoppingCartPayload);

    public Item fetchItemsById(Long customer_id) throws ItemNotFoundException;

   public  List<ShoppingCart> fetchShoppingCartList();
}