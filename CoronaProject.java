import java.io.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
//Benjamin S. Strait, 11/21/20
public class CoronaProject extends Application
{
   private Label firstLabel;
   private Label resultLabel;
   private String[] countryList;
   private String[] stateList;
   private String dateString;
   private int currentProgram;
   public static void main(String[] args)  throws IOException
   {
      launch(args);
   }
   public void start(Stage primaryStage)  throws IOException
   {
      CountryMining test = new CountryMining("train-covid.txt");
      currentProgram = 0;
      countryList = test.getListCountry();
      ComboBox<String> countryComboBox = new ComboBox<>();
      countryComboBox.getItems().addAll(countryList);
      stateList = test.getListUS();
      ComboBox<String> usComboBox = new ComboBox<>();
      usComboBox.getItems().addAll(stateList);
      resultLabel = new Label();
      final ToggleGroup group = new ToggleGroup();
      RadioButton rb1 = new RadioButton("No Date");
      rb1.setToggleGroup(group);
      rb1.setSelected(true);
      RadioButton rb2 = new RadioButton("Jan");
      rb2.setToggleGroup(group);
      RadioButton rb3 = new RadioButton("Feb");
      rb3.setToggleGroup(group);
      RadioButton rb4 = new RadioButton("Mar");
      rb4.setToggleGroup(group);
      RadioButton rb5 = new RadioButton("Apr");
      rb5.setToggleGroup(group);
      RadioButton rb6 = new RadioButton("May");
      rb6.setToggleGroup(group);
      Label dateExplination = new Label("choose a date for different case counts");
      Label countryExpliantaion = new Label("enter a country for specific data");
      Label stateExpliantaion = new Label("enter a state for specific data");
      Label topTier = new Label(test.highestCaseCount());
      TextField dateTextField = new TextField();
      dateTextField.setPromptText("Enter date");
      TextField textField = new TextField();
      textField.setPromptText("Enter country/state");
      Button getDate = new Button("get total from only date");
      getDate.setOnAction(
         event ->
         {
            if (rb1.isSelected())
            {
               dateString = "";
            }
            if (rb2.isSelected())
            {
               dateString = "1";
            }
            if (rb3.isSelected())
            {
               dateString = "2";
            }
            if (rb4.isSelected())
            {
               dateString = "3";
            }
            if (rb5.isSelected())
            {
               dateString = "4";
            }
            if (rb6.isSelected())
            {
               dateString = "5";
            }
            int total = 0;
            int date = Integer.parseInt(dateString);
            if (currentProgram == 0)
            {
               total = test.findTotalDate(date);
            } else
            {
               total = test.findTotalDateForStates(date);
            }
            
            String formatted = "The total is: " + total;
            resultLabel.setText(formatted);
         });
      Button getStateAvg = new Button("get average cases in this state");
      getStateAvg.setOnAction(
         event ->
         {
            double total = 0;
            String selected = usComboBox.getValue();
            total = test.findAverage(selected);
            String formatted = String.format("The average is %.1f ", total);
            resultLabel.setText(formatted);
         });
      Button getList = new Button("get from list");
      getList.setOnAction(
         event ->
         {
            if (rb1.isSelected())
            {
               dateString = "";
            }
            if (rb2.isSelected())
            {
               dateString = "1";
            }
            if (rb3.isSelected())
            {
               dateString = "2";
            }
            if (rb4.isSelected())
            {
               dateString = "3";
            }
            if (rb5.isSelected())
            {
               dateString = "4";
            }
            if (rb6.isSelected())
            {
               dateString = "5";
            }
            int total = 0;
            String selected = "";
            int date = 0;
            if (dateString.equals(""))
            {
               if (currentProgram == 0)
               {
                  selected = countryComboBox.getValue();
                  total = test.findTotalCountries(selected);
               } else
               {
                  selected = usComboBox.getValue();
                  total = test.findTotalStates(selected);
               }
            } else
            {
               date = Integer.parseInt(dateString);
               String s =countryComboBox.getValue();
               String u =usComboBox.getValue();
            
               if (currentProgram == 0)
               {
                  selected = countryComboBox.getValue();
                  total = test.findTotalCountriesOnDate(selected, date);
               } else
               {
                  selected = usComboBox.getValue();
                  total = test.findTotalSatesOnDate(selected, date);
               }
            }
            String formatted = "The total is: " + total;
            resultLabel.setText(formatted);
         });
      Button changeUS = new Button("Change to US?");
      
      HBox countDateBox = new HBox(10, rb1, rb2, rb3, rb4, rb5, rb6);
      VBox countTop = new VBox(10,dateExplination, countDateBox, getDate);
      VBox countMiddle = new VBox(10, countryExpliantaion, countryComboBox, getList);
      HBox countBottom = new HBox(10, resultLabel, changeUS);
      VBox countTotal = new VBox(10, countTop, countMiddle, countBottom);
      countTop.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
      countTop.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(2), Insets.EMPTY)));
      countMiddle.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
      countMiddle.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(2), Insets.EMPTY)));
      countBottom.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
      countBottom.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(2), Insets.EMPTY)));
      Scene countryScene = new Scene(countTotal, 700, 350);
      countTotal.setAlignment(Pos.CENTER);
      countTotal.setPadding(new Insets(10));
      primaryStage.setScene(countryScene);
      primaryStage.setTitle("Countries corona test map");
      
      changeUS.setOnAction(
         event ->
         {
            currentProgram = 1;
            HBox usDateBox = new HBox(10, rb1, rb2, rb3, rb4, rb5, rb6);
            VBox usTop = new VBox(10, dateExplination, usDateBox, getDate);
            VBox usMiddle = new VBox(10, stateExpliantaion, usComboBox, getList, getStateAvg);
            HBox usBottom = new HBox(10, resultLabel, topTier);
            VBox usTotal = new VBox(10, usTop, usMiddle, usBottom);
            usTop.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
            usTop.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii(2), Insets.EMPTY)));
            usMiddle.setBorder(new Border(new BorderStroke(Color.LIGHTGREY, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
            usMiddle.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), Insets.EMPTY)));
            usBottom.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(2))));
            usBottom.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(2), Insets.EMPTY)));
            Scene usScene = new Scene(usTotal, 700, 350);
            usTotal.setAlignment(Pos.CENTER);
            usTotal.setPadding(new Insets(10));
            primaryStage.setScene(usScene);
            primaryStage.setTitle("US corona test map");
         });
      primaryStage.show();
   }
}