package com.ui_demo.service;

import java.util.function.BiFunction;

public class DemoService {
    BiFunction<Integer,Integer,Integer> price = (quantity,price)-> quantity*price;

    public void calculatePrice(){
        Integer totalPrice = price.apply(10, 20);
        System.out.println("total price" +totalPrice);

    }

}
