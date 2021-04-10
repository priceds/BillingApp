package sample;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Controller {



      private String user_name;
      private String user_password;
      private Boolean login_Status;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_login;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_password;



    @FXML
    private Label currentTime;

//    This Method Gets Current System Time And Date
     public void getTime()
     {
         DateTimeFormatter DTF  =  DateTimeFormatter.ofPattern("HH:mm  dd/MM/yyyy  ");

         LocalDateTime now = LocalDateTime.now();
         String getTime = DTF.format(now);
         currentTime.setText("Time : "+getTime);
     }

//     This Method Checks The Input From User Against Database Records & Calls Appropriate Scenes
    public void checkLogin() throws SQLException, IOException {
        user_name = txt_name.getText();
        user_password= txt_password.getText();
        DBHandler Handler = new DBHandler();
        login_Status=Handler.checkLogin(user_name,user_password);
        if(login_Status==true)
        {

            switchToMainScene();
            Stage current_stage = (Stage) btn_login.getScene().getWindow();
            current_stage.close();
        }
        else
        {
            System.out.println("Wrong ID AND PASS TRY AGAIN");
        }


    }
//    This Method Launches Main Scene Upon Calling With User Name As Arguement
    public void switchToMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("billscene.fxml"));
        Parent root =loader.load();
        Controller2 MainSceneController = loader.getController();
        MainSceneController.setUserName(txt_name.getText());
        Stage stage  = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("pos_icon.png")));
        stage.setTitle("Keddies Kitchen POS");
        stage.show();

    }

//    This Method Contains Code That Runs When Scene launches
    public void initialize()
    {

        getTime();
    }


}
