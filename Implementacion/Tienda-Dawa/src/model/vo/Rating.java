package model.vo;

import java.util.*;

public class Rating {
    private int id;
    private float value;
    private Date date;
    private Product product;
    private Client client;
    private Comment comment;

    public Rating(int id, float value, Date date, Product product, Client client, Comment comment) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.product = product;
        this.client = client;
        this.comment = comment;
    }

    public Rating(float value, Product product, Client client, Comment comment) {
        this.value = value;
        this.product = product;
        this.client = client;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}