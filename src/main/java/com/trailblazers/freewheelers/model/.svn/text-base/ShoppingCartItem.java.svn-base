package com.trailblazers.freewheelers.model;


public class ShoppingCartItem {

    private Item item;
    private int quantity;
    private double tax;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCartItem that = (ShoppingCartItem) o;

        if (quantity != that.quantity) return false;
        if (Double.compare(that.tax, tax) != 0) return false;
        if (!item.equals(that.item)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = item.hashCode();
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(tax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
