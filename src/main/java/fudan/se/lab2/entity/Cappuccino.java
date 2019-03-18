package fudan.se.lab2.entity;

import fudan.se.lab2.constant.FileConstant;
import fudan.se.lab2.constant.InfoConstant;
import fudan.se.lab2.util.FileUtil;


public class Cappuccino extends Coffee {


    @Override
    public double cost() {
        //todo

        double cost=0;
        if(getSize()!=1&&getSize()!=2&&getSize()!=3){//如果size不符合大中小三种的话抛出异常
            throw new RuntimeException(InfoConstant.FAILED_SIZE);
        }

        //单价读出来是个String;
        String stringPrice = FileUtil.readByName(getName(), FileConstant.CAPPUCCINO_CSV)[2];
        double price = Double.parseDouble(stringPrice);
        setPrice(price);
        cost = getPrice() + getSize() * 2;
        return cost;
    }


}
