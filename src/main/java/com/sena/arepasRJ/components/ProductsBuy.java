package com.sena.arepasRJ.components;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class ProductsBuy {

    private String productName;
    private int quantity;
    private BigDecimal price;

    public ProductsBuy() {
    }

    public ProductsBuy(String productName, int quantity, BigDecimal price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
