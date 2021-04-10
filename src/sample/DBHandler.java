package sample;

import java.sql.SQLException;
import java.sql.*;
public class DBHandler {


    public Boolean checkLogin(String usr_name,String usr_pass) throws SQLException
    {
        Boolean login_Status=false;
        try
        {

            DBConnect db = new DBConnect();
            Connection Link = db.getConnection();

            PreparedStatement pstmt = Link.prepareStatement("SELECT * FROM users WHERE user_name=? and user_pass=?");
            pstmt.setString(1,usr_name);
            pstmt.setString(2,usr_pass);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                String user_nme=rs.getString("user_name");
                String user_pss=rs.getString("user_pass");
                if(usr_name.equals(user_nme)&&usr_pass.equals(user_pss))
                {
                    login_Status=true;
                }
                else
                {
                    login_Status=false;
                }
            }
        }catch(Exception e)
        {
            System.out.println("Failed To Login Because : "+e);
        }


        return login_Status;
    }

    public int getDishPrice(int dish_id) throws SQLException
    {
        int dish_prices = 0;
        try
        {

            DBConnect db = new DBConnect();
            Connection Link = db.getConnection();

            PreparedStatement pstmt = Link.prepareStatement("SELECT dish_price FROM dish WHERE dish_id=?");
            pstmt.setInt(1,dish_id);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                dish_prices=rs.getInt(1);
                System.out.println(dish_prices);
            }
        }catch(Exception e)
        {
            System.out.println("Failed To Fetch Because of : "+e);
        }


        return dish_prices;
    }

}
