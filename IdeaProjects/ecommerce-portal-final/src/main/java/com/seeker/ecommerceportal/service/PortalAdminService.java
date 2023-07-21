

package com.seeker.ecommerceportal.service;

        import com.seeker.ecommerceportal.entity.*;
        import com.seeker.ecommerceportal.error.ItemNotFoundException;

        import java.util.List;

public interface PortalAdminService {
    Item saveItem(Item item);
    Category saveCategory(Category category);

    Category findCategoryById(Long categoryId);

    public List<Category> fetchCategoryList();

    public Category fetchCategoryById(Long category_id) throws ItemNotFoundException;



//    public Category findCategoryById(Long categoryId);



    public  Customer fetchCustomerById(Long customer_id) throws ItemNotFoundException;




    public List<Customer> fetchCustomerList() ;

    public CustomerOrder fetchCustomerOrderById(Long order_id) throws ItemNotFoundException;

    public  List<CustomerOrder> fetchCustomerOrderList();

    public Shipment saveShipment(Long orderId);

    public ShipmentStage saveShipmentStage(ShipmentStagePayload shipmentStagePayload);
    public ShipmentTracker saveShipmentTracker(ShipmentTrackerPayload shipmentTrackerPayload);



    public Inventory addInventoryById(Long itemId,Long itemQty);
}
