package Modelo.Model;

import java.util.*;

/**
 * 
 */
public class Order {

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private Client client;

    /**
     * 
     */
    private List<OrderLine> lines;

    /**
     * 
     */
    private float finalPrice;

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private float discount;



}