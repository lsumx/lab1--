package fudan.se.lab2.service.impl;

import fudan.se.lab2.constant.InfoConstant;
import fudan.se.lab2.entity.Coffee;

import fudan.se.lab2.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Map;

public class PriceServiceImpl implements PriceService {


    private Logger logger =  LoggerFactory.getLogger(AccountServiceImpl.class);


    @Override
    public double cost(Map<Coffee, Integer> order) {
        double amount=0;
        if(!order.isEmpty()) {
            for (Map.Entry<Coffee, Integer> entry : order.entrySet()) {
                Coffee coffee = entry.getKey();
                int number = entry.getValue();
                double temp = coffee.cost() * number;
                amount += temp;
                logger.info(

//                        MessageFormat.format("name:{0},size:{1};number:{2},price:{3}$", coffee.getName(), coffee.getSize(), number, temp)

                        MessageFormat.format(InfoConstant.PRICE_SERVICE_COST, coffee.getName(), coffee.getSize(), number, temp)
                );
            }
        }
        logger.info(
                MessageFormat.format("total:{0}$", amount)
        );
        return amount;
    }
}
