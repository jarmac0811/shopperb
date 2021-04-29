package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.OrderItem;
import com.recruitment.beerRestApiTask.domain.UserOrder;
import com.recruitment.beerRestApiTask.repositories.UserOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserOrderService {

    private UserOrderRepository userOrderRepository;

    public UserOrderService(UserOrderRepository UserOrderRepository) {
        this.userOrderRepository = UserOrderRepository;
    }

    public List<UserOrder> getAllOrders() {
        return userOrderRepository.findAll();
    }

    public UserOrder saveOrder(UserOrder userOrder) {
        Set<OrderItem> items = userOrder.getItems();
            items.forEach(item -> item.setUserOrder(userOrder));
        return userOrderRepository.save(userOrder);
    }

    public UserOrder getNewOrder() {
        UserOrder userOrder = new UserOrder();
        return userOrderRepository.save(userOrder);
    }

    public void deleteOrder(String id) {
        userOrderRepository.deleteById(Long.valueOf(id));
    }

    public Optional<UserOrder> getOrder(String id) {
        Optional<UserOrder> order = userOrderRepository.findById(Long.valueOf(id));
        return order;
    }
    public List<UserOrder> getOrderByUserId(String id) {
        List<UserOrder> orders = userOrderRepository.findByUserId(id);
        return orders;
    }

}
