package finalAtakanAytar;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.Statement;

public class Gui extends Application {
    Statement stmt;
    Connection connection;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("> Start Program ...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("> Driver Loaded successfully.");

            connection = DriverManager.getConnection("jdbc:oracle:thin:@ 199.212.26.208:1521:SQLD"," COMP228_w22_sy_42", "password");
            System.out.println("Database connected successfully.");
            stmt = connection.createStatement();



        }
        catch(Exception e2)
        {
            e2.printStackTrace();
        }


        GridPane pane = new GridPane();

        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Label studentIdLabel = new Label("Student Id:");
        TextField studentIdText = new TextField();
        Button displayOneButton = new Button("Display this Students records");
        Button deleteButton = new Button("Delete this Student");
        HBox hBox1 = new HBox(studentIdLabel,studentIdText,displayOneButton,deleteButton);


        Label nameLabel = new Label("Student Name:");
        TextField nameText= new TextField();
        HBox hBox2 = new HBox(nameLabel,nameText);



        Label tuitionLabel = new Label("Tuition Fee:");
        TextField tuitionText= new TextField();
        Button updatetuitionButton = new Button("Update Tuition Fee");
        HBox hBox3 = new HBox(tuitionLabel,tuitionText,updatetuitionButton);

        Label netLabel = new Label("Discounted Tuition Fee:");
        TextField netText= new TextField();
        Button reset = new Button("Reset");
        Button quit = new Button("Quit");
        HBox hBox4 = new HBox(netLabel,netText,reset,quit);


        pane.add(hBox1, 0, 1);
        pane.add(hBox2,0,2);
        pane.add(hBox3,0,3);
        pane.add(hBox4,0,4);

        updatetuitionButton.setOnAction(new EventHandler<ActionEvent>() // 28
        {

            public void handle(ActionEvent e) // 30
            {


                String sql1 = "SELECT * FROM STUDENTS WHERE STUDENTID="+studentIdText.getText();
                try {
                    System.out.println(sql1);
                    ResultSet rs = stmt.executeQuery(sql1);
                    rs.next();
                    String a = rs.getString("STNAME");

                    String sql = "UPDATE  STUDENTS SET STFEES="+ tuitionText.getText() +" WHERE STUDENTID="+studentIdText.getText();
                    try {
                        System.out.println(sql);
                        stmt.executeQuery(sql);
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setHeaderText("Updated");
                        errorAlert.setContentText("Updated the record");
                        errorAlert.showAndWait();


                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("No Student");
                    errorAlert.setContentText("There is no matching id");
                    errorAlert.showAndWait();
                }



            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() // 28
        {

            public void handle(ActionEvent e) // 30
            {

                String sql1 = "SELECT * FROM STUDENTS WHERE STUDENTID="+studentIdText.getText();
                try {
                    System.out.println(sql1);
                    ResultSet rs = stmt.executeQuery(sql1);
                    rs.next();
                    String a = rs.getString("STNAME");

                    String sql = "DELETE FROM  STUDENTS " + "WHERE STUDENTID="+studentIdText.getText();
                    try {
                        System.out.println(sql);
                        stmt.executeQuery(sql);
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setHeaderText("Deleted");
                        errorAlert.setContentText("Deleted the record");
                        errorAlert.showAndWait();



                    } catch (SQLException ex) {
                        ex.printStackTrace();


                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("No Student");
                    errorAlert.setContentText("There is no matching id");
                    errorAlert.showAndWait();
                }






            }
        });


        reset.setOnAction(new EventHandler<ActionEvent>() // 28
        {

            public void handle(ActionEvent e) // 30
            {
                studentIdText.setText("");
                nameText.setText("");
                tuitionText.setText("");
                netText.setText("");

            }
        });


        quit.setOnAction(new EventHandler<ActionEvent>() // 28
        {

            public void handle(ActionEvent e) // 30
            {
                try {

                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Good Bye!!");
                errorAlert.setContentText("Database Connection and Application is getting closed");
                errorAlert.showAndWait();


                System.exit(0);

            }
        });
        displayOneButton.setOnAction(new EventHandler<ActionEvent>() // 28
        {

            public void handle(ActionEvent e) // 30
            {
                String sql = "SELECT * FROM STUDENTS WHERE STUDENTID="+studentIdText.getText();
                try {
                    System.out.println(sql);
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    nameText.setText(rs.getString("STNAME"));
                    tuitionText.setText(rs.getString("STFEES"));
                    netText.setText(String.valueOf(rs.getInt("STFEES")*0.7));


                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("No Student");
                    errorAlert.setContentText("There is no matching id");
                    errorAlert.showAndWait();
                }


            }
        });
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Students"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}
