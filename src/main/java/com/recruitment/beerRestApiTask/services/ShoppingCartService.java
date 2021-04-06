package com.recruitment.beerRestApiTask.services;

import com.recruitment.beerRestApiTask.domain.CartItem;
import com.recruitment.beerRestApiTask.domain.ShoppingCart;
import com.recruitment.beerRestApiTask.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository ShoppingCartRepository) {
        this.shoppingCartRepository = ShoppingCartRepository;
    }

    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        Optional<ShoppingCart> shoppingCartFromDb = shoppingCartRepository.findById(Long.valueOf(shoppingCart.getId()));
        ShoppingCart shoppingCartFromDB;
        Set<CartItem> items = shoppingCart.getItems();
        if (shoppingCartFromDb.isPresent()) {
            shoppingCartFromDB = shoppingCartFromDb.get();
            items.forEach(item -> item.setCart(shoppingCartFromDB));
            //            shoppingCartFromDB.setTotalPrice(shoppingCart.getTotalPrice());
        } else {
            shoppingCartFromDB = shoppingCartRepository.save(shoppingCart);
            items.forEach(item -> item.setCart(shoppingCartFromDB));
        }
        shoppingCartFromDB.getItems().clear();
        shoppingCartFromDB.getItems().addAll(items);
        return shoppingCartRepository.save(shoppingCartFromDB);
    }

    public ShoppingCart getNewShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        return shoppingCartRepository.save(shoppingCart);
    }

    public void deleteShoppingCart(String id) {
        shoppingCartRepository.deleteById(Long.valueOf(id));
    }

    public Optional<ShoppingCart> getShoppingCart(String id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(Long.valueOf(id));
        return shoppingCart;
    }

}
