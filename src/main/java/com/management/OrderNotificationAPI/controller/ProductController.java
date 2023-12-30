package com.management.OrderNotificationAPI.controller;
import com.management.OrderNotificationAPI.model.Category;
import com.management.OrderNotificationAPI.model.Product;
import com.management.OrderNotificationAPI.model.response.Response;
import com.management.OrderNotificationAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody Product p) {
        System.out.println("in add person" + p);
        boolean res = productService.addProduct(p);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Product Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Product is added successfully");
        return response;
    }

    @DeleteMapping("/deleteProduct/{serialNumber}")
    public Response deleteProduct(@PathVariable("serialNumber") int serialNumber) {
        Response response = new Response();
        boolean res = productService.deleteProduct(serialNumber);
        if(!res){
            response.setMessage("Product doesn't exist");
            response.setStatus(false);
        }
        else{
            response.setMessage("Product " + serialNumber +" deleted successfully");
            response.setStatus(true);
        }
        return response;
    }

    @GetMapping("/getAllProducts")
    public Product[] getAll() {
        return productService.getProducts();
    }


    @GetMapping("/getProduct/{serialNumber}")
    public Product getProduct(@PathVariable("serialNumber") int serialNumber) {
        return productService.get(serialNumber);
    }

    @GetMapping("/countProducts")
    public Map<Category,Integer> countProductsPerCategory() {
        Product[] products = productService.getProducts();
        Map<Category, Integer> countedProducts = new HashMap<>();
        for (Product p: products) {
            if (!countedProducts.containsKey(p.getCategory()))
                countedProducts.put(p.getCategory(),1);
            else
                countedProducts.put(p.getCategory(), countedProducts.get(p.getCategory()) + 1 );
        }
        return countedProducts;
    }

}
