package model.vo;

import java.util.*;

/**
 * 
 */
public class Order {
    private int id;
    private Client client;
    private List<OrderLine> lines;
    private float finalPrice;
    private Date date;
    private float discount;

}