package fudan.se.lab2;

import fudan.se.lab2.entity.Cappuccino;
import fudan.se.lab2.entity.Coffee;
import fudan.se.lab2.entity.Espresso;
import fudan.se.lab2.entity.Coffee;
import fudan.se.lab2.entity.User;
import fudan.se.lab2.service.impl.AccountServiceImpl;
import fudan.se.lab2.service.impl.PriceServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Lab2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);

        // todo: here to write your main business logic.
        User user = new User();
        user.setName("limcccc");
        user.setPassword("qqqq");
        AccountServiceImpl accountService =new AccountServiceImpl();
        PriceServiceImpl priceService = new PriceServiceImpl();
        Cappuccino cappuccino = new Cappuccino();
        cappuccino.setName("cappuccino");
        cappuccino.setSize(3);
        Espresso espresso =new Espresso();
        espresso.setName("espresso");
        espresso.setSize(1);
        Map<Coffee,Integer> order = new HashMap<>();

        order.put(espresso,2);
        order.put(cappuccino,5);
//         step1: signup
        accountService.signup(user);
//         step2: login
        accountService.login(user);
        // step3: check status
        accountService.checkStatus();
        // step4: price service
        if (accountService.checkStatus()){
            priceService.cost(order);
        }

    }

}
