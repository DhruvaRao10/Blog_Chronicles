package com.seeker.ecommerceportal.service;


import com.seeker.ecommerceportal.Repository.ShoppingCartRepository;
import com.seeker.ecommerceportal.entity.*;
import com.seeker.ecommerceportal.error.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seeker.ecommerceportal.Repository.ItemRepository;
import com.seeker.ecommerceportal.Repository.CustomerRepository;
import com.seeker.ecommerceportal.Repository.ShoppingCartItemRepository;


import java.util.List;
import java.util.Optional;

@Service
public class PortalServiceImpl implements  PortalService {


    private final Logger LOGGER = LoggerFactory.getLogger("name:PortalServiceImpl");
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;


    @Override
    public List<Item> fetchItemsList() {
        return itemRepository.findAll();
    }

    @Override
    public Item fetchItemsById(Long item_id) throws ItemNotFoundException {
        Optional<Item> item =  itemRepository.findById(item_id);
        if(!item.isPresent()){
            throw new ItemNotFoundException("Item Not Found ");
        }
        return item.get();
    }

    @Override
    public List<ShoppingCart> fetchShoppingCartList() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart fetchShoppingCartById(Long item_id) throws ItemNotFoundException {
        Optional<ShoppingCart> shoppingcart =  shoppingCartRepository.findById(item_id);
        if(!shoppingcart.isPresent()){
            throw new ItemNotFoundException("Item Not Found ");
        }
        return shoppingcart.get();
    }

    @Override
    public ShoppingCartPayload saveShoppingCart(ShoppingCartPayload shoppingCartPayload) {

            ShoppingCart shoppingCart = new ShoppingCart();
            Customer customer = customerRepository.findById(shoppingCartPayload.getCustomer_id()).get();
            shoppingCart.setCustomer(customer);
            shoppingCartRepository.save(shoppingCart);

            LOGGER.info(shoppingCart.toString());
            List<ShoppingCartItemPayload> shoppingCartItemPayloadList = shoppingCartPayload.getShoppingCartItems();
            LOGGER.info(" Count of shopping cart items "+shoppingCartItemPayloadList.size());

            ShoppingCartItemPayload shoppingCartItemPayload;
            ShoppingCartItem shoppingCartItem;
            LOGGER.info("list = "+shoppingCartItemPayloadList.toString());

            for(int i = 0; i < shoppingCartItemPayloadList.size(); i++){
                shoppingCartItemPayload = (ShoppingCartItemPayload)  shoppingCartItemPayloadList.get(i);
                shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setItem(itemRepository.findById(shoppingCartItemPayload.getItem_id()).get());
                shoppingCartItem.setShoppingCart(shoppingCart);
                shoppingCartItem.setItemQty(shoppingCartItemPayload.getItemQty());
                shoppingCartItemRepository.save(shoppingCartItem);
            }

            return shoppingCartPayload;
    }



}