package com.recruitment.beerRestApiTask.controllers;

import com.recruitment.beerRestApiTask.domain.ShoppingCart;
import com.recruitment.beerRestApiTask.services.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shoppingCarts")
//@CrossOrigin(origins = {"http://localhost:4200"},
//        allowedHeaders = {"Access-Control-Allow-Origin", "Content-Type"})
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        return ResponseEntity.ok(this.shoppingCartService.getAllShoppingCarts());
    }

    @GetMapping("/new")
    @ResponseBody
    public ResponseEntity<ShoppingCart> getNewShoppingCart() {
        return ResponseEntity.ok(this.shoppingCartService.getNewShoppingCart());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable String id) {
        Optional<ShoppingCart> shoppingCart = this.shoppingCartService.getShoppingCart(id);
        if (shoppingCart.isPresent()) {
            return ResponseEntity.ok(shoppingCart.get());
        }
        return ResponseEntity.notFound().build();
    }

    //    @PutMapping("/{id}")
//    @ResponseBody
//    public ShoppingCart updateShoppingCart(@RequestBody ShoppingCart shoppingCart,@PathVariable String id) {
//        return this.shoppingCartService.updateShoppingCart(shoppingCart, id);
//    }
    @PostMapping
    @ResponseBody
    public ShoppingCart saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
/*        Product p1=new Product();
        p1.setId(1);
        p1.setTitle("Spinach");
        Product p2=new Product();
        p2.setId(2);
        p2.setTitle("Avocado");
        HashMap products=new HashMap();
        products.put(p1.getTitle(),1);
        products.put(p2.getTitle(),2);

        ShoppingCart cart=new ShoppingCart();
        cart.setProductsQuantityMap(products);*/
        return this.shoppingCartService.saveShoppingCart(shoppingCart);
    }

    @DeleteMapping("/{id}")
    public void deleteShoppingCart(@PathVariable String id) {
        this.shoppingCartService.deleteShoppingCart(id);
    }
}


