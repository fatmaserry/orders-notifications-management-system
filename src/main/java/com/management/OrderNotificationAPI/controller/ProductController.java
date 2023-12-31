package com.management.OrderNotificationAPI.controller;
import com.management.OrderNotificationAPI.model.Category;
import com.management.OrderNotificationAPI.model.Product;
import com.management.OrderNotificationAPI.model.response.ProductResponse;
import com.management.OrderNotificationAPI.model.response.Response;
import com.management.OrderNotificationAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public Response addProduct(@RequestBody Product product) {
        boolean res = productService.addProduct(product);
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

    @PostMapping("/addProducts")
    public Response addProducts(@RequestBody ArrayList<Product> products) {
        Response response = new Response();

        for (Product product: products){
            if(productService.get(product.getSerialNumber()) != null){
                response.setStatus(false);
                response.setMessage("Product Already Exists");
                return response;
            }
        }

        for (Product product: products){
            productService.addProduct(product);
        }

        response.setStatus(true);
        response.setMessage("Products are added successfully");
        return response;
    }

    @DeleteMapping("/delete/{serialNumber}")
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

    @GetMapping("/get")
    public ArrayList<Product> getAll() {
        return productService.getProducts();
    }

    @GetMapping("/get/{serialNumber}")
    public ProductResponse getProduct(@PathVariable("serialNumber") int serialNumber) {
        ProductResponse response = new ProductResponse();
        Product product = productService.get(serialNumber);
        if(product == null){
            response.setMessage("Product doesn't exist");
            response.setStatus(false);
        }
        else{
            response.setMessage("success");
            response.setStatus(true);
            response.setProduct(product);
        }
        return response;
    }

    @GetMapping("/countPerCategory")
    public Map<Category,Integer> countProductsPerCategory() {
        ArrayList<Product> products = productService.getProducts();
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
