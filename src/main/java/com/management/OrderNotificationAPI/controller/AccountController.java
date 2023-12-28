package com.management.OrderNotificationAPI.controller;
import com.management.OrderNotificationAPI.*;
import com.management.OrderNotificationAPI.model.Account;
import com.management.OrderNotificationAPI.model.LoginResponse;
import com.management.OrderNotificationAPI.model.Response;
import com.management.OrderNotificationAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public Response register(@RequestBody Account p) {
        System.out.println("in add person" + p);
        boolean res = accountService.addAccount(p);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Account Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Account is created successfully");
        return response;
    }

    @GetMapping("/login/{username}/{password}")
    public Response login(@PathVariable("username") String username, @PathVariable("password") String password) {
        LoginResponse response = new LoginResponse();
        Account account = accountService.getAccount(username);
        if(account == null){
            response.setMessage("Account doesn't exist");
            response.setStatus(false);
        }
        else{
            if(account.getPassword().equals(password)) {
                response.setMessage("Login successfully");
                response.setStatus(true);
                response.setAccount(account);
            }
            else{
                response.setMessage("Wrong password");
                response.setStatus(false);
            }
        }
        return response;
    }

    @GetMapping("/get")
    public Account[] getAll() {
        return accountService.getAllAccounts();
    }
}