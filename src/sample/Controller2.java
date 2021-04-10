package sample;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class Controller2  {
int total_amount=0;
@FXML
private Label user_logged;

@FXML
private Label current_time;

@FXML
private TextField txt_searchbar;

@FXML
private TextField txt_qty;

    @FXML
    private TextField total_amt;

@FXML
private Button btn_add;
@FXML
private Button btn_print;

@FXML
private Button btn_exit;

@FXML
private TableView<Dish> CartTable;

@FXML
private TableColumn<Dish,Integer> col_dishid;

@FXML
private TableColumn<Dish,String> col_dishname;

@FXML
private TableColumn<Dish,Integer> col_dishprice;

    @FXML
    private TableColumn<Dish, Integer> col_dishquant;
    @FXML
    private TableColumn<Dish, Integer> col_totalamt;


final DateFormat format = DateFormat.getInstance();

int dish_id=0;
ArrayList<String> Suggestions  =  new ArrayList<String>();
    ObservableList<Dish> oblist =FXCollections.observableArrayList();


public void setUserName(String user)
{
    user_logged.setText("Logged In As :"+user);
}


public void initialize()
{
//    Here the method call is to fetch all dish names from table
    fetchDishNames();


//    Now Here We Convert ArrayList of DishNames To String Array

    String suggestions[]=new String[Suggestions.size()];
    for(int i=0;i<Suggestions.size();i++)
    {
        suggestions[i]=Suggestions.get(i);
    }

//    And Finally We Bind The String Array With Respective TextField
    TextFields.bindAutoCompletion(txt_searchbar,suggestions);

getTime();

}

    public void getTime()
    {
        DateTimeFormatter DTF  =  DateTimeFormatter.ofPattern("HH:mm  dd/MM/yyyy  ");

        LocalDateTime now = LocalDateTime.now();
        String getTime = DTF.format(now);
        current_time.setText("Time : "+getTime);
    }


public void getPrice() throws SQLException {
    int temp_dval=0;
    int qty=Integer.parseInt(txt_qty.getText());
     dish_id=Integer.parseInt(txt_searchbar.getText());
    int dishTotalPrice=0;

    DBHandler dhv = new DBHandler();
     temp_dval=dhv.getDishPrice(dish_id);
    dishTotalPrice=temp_dval*qty;
    total_amount+=dishTotalPrice;
    total_amt.setText(String.valueOf(total_amount));

     fetchData(dish_id,qty,dishTotalPrice);


}

public void fetchData(int dish_id,int qty,int dishTotaPrice)
{
try
{
DBConnect db = new DBConnect();
Connection Linkr = db.getConnection();

  PreparedStatement pst = Linkr.prepareStatement("SELECT * FROM dish WHERE dish_id=?");
  pst.setInt(1,dish_id);
  ResultSet rs = pst.executeQuery();
while(rs.next())
{
    oblist.add(new Dish(rs.getInt("dish_id"),rs.getInt("dish_price"), rs.getString("dish_name"),rs.getString("dish_type"),qty,dishTotaPrice));
    CartTable.setItems(oblist);
}

}catch(SQLException|ClassNotFoundException ex)
{
    System.out.println("Failed Becasue of"+ex);
}
    col_dishid.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("dish_id"));
    col_dishname.setCellValueFactory(new PropertyValueFactory<Dish,String>("dish_name"));
    col_dishprice.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("dish_price"));
    col_dishquant.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("quantity"));
    col_totalamt.setCellValueFactory(new PropertyValueFactory<Dish,Integer>("total"));
}

public void fetchDishNames()
{

    try
    {
        DBConnect db = new DBConnect();
        Connection Link = db.getConnection();
        String sql = "SELECT dish_name FROM dish";
        PreparedStatement ps = Link.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Suggestions.add(rs.getString("dish_name"));
        }

    }
    catch(SQLException|ClassNotFoundException ex)
    {
        System.out.println("Failed Because of : "+ex);
    }

}
}
