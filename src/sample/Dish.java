package sample;

public class Dish {
    String dish_type, dish_name;
    int dish_id=0, dish_price=0,quantity=0,total=0;




    public Dish(int dish_id, int dish_price, String dish_name,String dish_type,int quantity,int total) {
        this.dish_id=dish_id;
        this.dish_name=dish_name;
        this.dish_type=dish_type;
        this.dish_price=dish_price;
        this.quantity=quantity;
        this.total=total;

    }


    public String getDish_type() {
        return dish_type;
    }

    public void setDish_type(String dish_type) {
        this.dish_type = dish_type;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDish_price() {
        return dish_price;
    }

    public void setDish_price(int dish_price) {
        this.dish_price = dish_price;
    }
}
