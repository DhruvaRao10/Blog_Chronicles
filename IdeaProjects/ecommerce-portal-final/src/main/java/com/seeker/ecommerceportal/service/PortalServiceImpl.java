package com.seeker.ecommerceportal.service;


import com.seeker.ecommerceportal.Repository.*;
import com.seeker.ecommerceportal.entity.*;
import com.seeker.ecommerceportal.error.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seeker.ecommerceportal.Repository.ShoppingCartRepository;


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
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PromotionsRepository promotionRepository;


    @Autowired
    private ReviewsRepository reviewRepository;


    @Autowired
    private ReturnsRepository returnRepository;

    @Override
    public List<Item> fetchItemsList() {
        return itemRepository.findAll();
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




    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    private Long calculateBill(CustomerOrder order)
    {
        // find all order items for this order item by writing a new method
        // and then calculate the amount by sum( item x (item price - discount%))
        // List<OrderItem> orderItems = orderItemRepository.find.getOrder_id()
        return 0L;
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


        //save order items
        java.util.Iterator iter = orderItemPayloadList.iterator();

        OrderItemPayload orderItemPayload;
        OrderItem orderItem;
        LOGGER.info("orderItemPayload[1] "+ orderItemPayloadList.get(0).toString());
        Long billAmt = 0L;
        for(int i = 0; i < orderItemPayloadList.size(); i++){
            orderItemPayload = (OrderItemPayload)  orderItemPayloadList.get(i);
            LOGGER.info("orderItemPayload = "+ orderItemPayload.toString());
            Long itemId = orderItemPayload.getItem_id() ;
            Long itemQty = orderItemPayload.getOrderitemqty() ;



            billAmt +=  itemRepository.findById(orderItemPayload.getItem_id()).get().getItem_price() *
                        orderItemPayload.getOrderitemqty();


            orderItem = new OrderItem();
            orderItem.setItem(itemRepository.findById(orderItemPayload.getItem_id()).get());
            orderItem.setOrder(customerOrder);
            orderItem.setOrderItemQty(orderItemPayload.getOrderitemqty());
            orderItemRepository.save(orderItem);
        }
        // generate a bill
        Bill bill = new Bill();
        bill.setCustomerOrder(customerOrder);
        bill.setBill_amt(billAmt);
        billRepository.save(bill);
        return customerOrderPayload;
    }


    @Override
    public List<CustomerOrder> getAllOrdersForCustomer(Long customerId) {
        List<CustomerOrder> orders = customerOrderRepository.getAllOrdersForCustomer(customerId);
        return orders;
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
    public Reviews saveReview(ReviewPayload payload) {
        Reviews review = new Reviews();
        review.setReviewStars(payload.getStars());
        review.setReview(payload.getReviewDesc());
        review.setItem(itemRepository.findById(payload.getItem_id()).get());
        return reviewRepository.save(review);
    }
    @Override
    public  Returns saveReturn(Long orderItemId){
        Returns returns = new Returns();
        returns.setOrderItem(orderItemRepository.findById(orderItemId).get());
        return  returnRepository.save(returns);
    }

    public Promotions  savePromotion(PromotionPayload payload ){
        Promotions promotion = new Promotions();
        promotion.setItem(itemRepository.findById(payload.getItemId()).get());
        promotion.setDiscount_percentage(payload.getPercentage());
        return promotionRepository.save(promotion);
    }


}