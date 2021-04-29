package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.UserOrder;
import com.recruitment.beerRestApiTask.services.UserOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
//@CrossOrigin(origins = {"http://localhost:4200"})
public class UserOrderController {
    private UserOrderService userOrderService;

    public UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UserOrder>> getAllOrders() {
        return ResponseEntity.ok(this.userOrderService.getAllOrders());
    }
    @PostMapping("/validate")
    public boolean validateOrder(@RequestBody UserOrder userOrder, BindingResult result, Model model) {
        if(result.hasErrors()){
            return false;
        }
        return true;
    }
    @GetMapping("/new")
    @ResponseBody
    public ResponseEntity<UserOrder> getNewOrder() {
        return ResponseEntity.ok(this.userOrderService.getNewOrder());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<List<UserOrder>> getOrderByUserId(@PathVariable String id) {
        List<UserOrder> orders = this.userOrderService.getOrderByUserId(id);
        if (orders.size()>0) {
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.notFound().build();
    }

    //    @PutMapping("/{id}")
//    @ResponseBody
//    public Order updateOrder(@RequestBody Order order,@PathVariable String id) {
//        return this.orderService.updateOrder(order, id);
//    }
    @PostMapping
    @ResponseBody
    public UserOrder saveOrder(@RequestBody UserOrder userOrder) {
/*        Product p1=new Product();
        p1.setId(1);
        p1.setTitle("Spinach");
        Product p2=new Product();
        p2.setId(2);
        p2.setTitle("Avocado");
        HashMap products=new HashMap();
        products.put(p1.getTitle(),1);
        products.put(p2.getTitle(),2);

        Order cart=new Order();
        cart.setProductsQuantityMap(products);*/
        return this.userOrderService.saveOrder(userOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        this.userOrderService.deleteOrder(id);
    }
}


