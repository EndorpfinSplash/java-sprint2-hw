package domain;

public class Transaction {

    String item_name;
    Boolean is_expense;

    Integer quantity;

    Double unit_price;

    public Transaction(String item_name, Boolean is_expense, Integer quantity, Double unit_price) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }
    public String getItem_name() {
        return item_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getUnit_price() {
        return unit_price;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "item_name='" + item_name + '\'' +
                ", is_expense=" + is_expense +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }

}
