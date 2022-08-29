import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;


public class programmingAssignment9 extends Application{

    Stage window;
	double inputVal = 0;//user input as a double
	String measurements;
	ChoiceBox<String> startingBox = new ChoiceBox<>();//creates starting units drop down options
	ChoiceBox<String> convertBox = new ChoiceBox<>(); //creates conversion units drop down options
	static TextField startingInput = new TextField();//starting units Input
	static TextField output = new TextField();//output from calculation

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Temperature Conversion Calculator");
        window.getIcons().add(new Image("Colonial.png"));

        Button startingDropDown = new Button(); //drop down to pick current units
        Button convertDropDown = new Button(); //drop down to pick desired units to convert to
        Button convertButton = new Button("Calculate");//calculate button

		//styling the convert button

        convertButton.setStyle("-fx-font-size: 2em; ");
        //convertButton.setStyle("-fx-border-color: #E74C3C; -fx-border-width: 3px;");
        //convertButton.setStyle("fx-text-fill: #28B463");
		convertButton.setStyle("-fx-background-color: #F1948A");

		Label startingLabel = new Label("From:");//starting units Label - constrains use (child, column, row)
		startingLabel.setStyle("-fx-text-fill: #E74C3C"); //sets font color to blue
		startingLabel.setFont(Font.font("Bahnschrift Light", 14)); //sets font size to 13 and font style to arial

		Label convertLabel = new Label("To");//converted Unit Label
		convertLabel.setStyle("-fx-text-fill: #E74C3C"); //sets font color to blue
		convertLabel.setFont(Font.font("Bahnschrift Light", 14)); //sets font size to 13 and font style to arial

		TextField convertUnit = new TextField("Converted Units");//converted units label


        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #CCD1D1;"); // changes background color


        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10); //vertical
        grid.setHgap(10); //horizontal

		//data collection from user
        String startData = startingInput.getText();

        try{
        inputVal = Double.parseDouble(startData);
        }catch(Exception e){
			output.setText("Enter a Number Please");
		}

		//getItems returns the ObservableList object which you can add items to
		startingBox.getItems().addAll("Fahrenheit","Celsius","Kelvin");

		//decides the action of each button
		convertDropDown.setOnAction(e -> getConvertChoice(convertBox));
		startingDropDown.setOnAction(e -> getStartingChoice(startingBox));
		convertButton.setOnAction(e -> convertUnit.setText(calcButton(startingBox,convertBox)));
		startingBox.setOnAction(e-> dropdown(startingBox,convertBox));

		// activate convert button using ENTER key
    	grid.setOnKeyPressed(event -> {
    		{
				if (event.getCode()==(KeyCode.ENTER))
				    {
						convertUnit.setText(calcButton(startingBox,convertBox));
					}
				}
    		});

		//starting formatting
        grid.setConstraints(startingLabel,1,0);//Label "From"
        grid.setConstraints(startingInput,2,0);//textfield for user input
		grid.setConstraints(startingBox,3,0);//drop down choose different units
		grid.setConstraints(convertBox,3,1);//convert unit drop down
        GridPane.setConstraints(convertLabel, 1, 1);//Label "To"
        GridPane.setConstraints(convertUnit, 2, 1);//converted units label before calculation
		GridPane.setConstraints(convertButton, 2, 2);//calculate button


		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(startingBox,startingDropDown,convertDropDown,convertBox);

        grid.getChildren().addAll(startingLabel,startingInput,convertLabel,
        startingBox,convertUnit,convertBox,convertButton);	//Add everything to grid

        Scene scene = new Scene(grid, 400, 200);


        window.setScene(scene);
        window.show();
    }

//-------------------------------------------------------------------------------------------------------------------------------

	public static String calcButton(ChoiceBox<String> startingBox,ChoiceBox<String> convertBox)
	{//start of calcButton

		double inputVal =0;
	try{
	        inputVal = Double.parseDouble(startingInput.getText());
	        }catch(Exception e){
				output.setText("Enter a Number Please");
		}

	//decides which conversion for convert button

		if(convertBox.getValue().equals("Kelvin"))
		{//start of if
			return(Double.toString(convertToKelvin(startingBox,inputVal)));
		}//end of if
		if(convertBox.getValue().equals("Fahrenheit"))
		{//start of if
			return(Double.toString(convertToFahrenheit(startingBox,inputVal)));
		}//end of if
		if(convertBox.getValue().equals("Celsius"))
		{//start of if
			return(Double.toString(convertToCelsius(startingBox,inputVal)));
		}//end of if
		return "";
	}//end of calcButton

//-------------------------------------------------------------------------------------------------------------------------------

	public static void dropdown(ChoiceBox<String> startingBox,ChoiceBox<String> convertBox)
	{
		convertBox.getItems().clear();//clears convertBox
		convertBox.getItems().addAll("Fahrenheit","Celsius","Kelvin");
		convertBox.getItems().remove(startingBox.getValue());
	}

//-------------------------------------------------------------------------------------------------------------------------------

	 public static double convertToKelvin(ChoiceBox<String> startingBox,double inputVal)
		 {// start of convertToKelvin
			if(startingBox.getValue().equals("Celsius")) // C
			{// start of if
				return(inputVal+273.15); // converting from celsius to kelvin
			}//end of if
			else //F
			{//start of else
				return((inputVal)+459.67)*(5.0/9.0); //converting from fahrenheit to kelvin
			}//end of else
	 }// end of convertToKelvin

//-------------------------------------------------------------------------------------------------------------------------------

	public static double convertToCelsius(ChoiceBox<String> startingBox,double inputVal)
	{// start of convertToCelsius
		if(startingBox.getValue().equals("Kelvin"))//K
		{// start of if
			return(inputVal-273.15); //conversion to celsius from kevlin
		}//end of if

		else//F
		{// start of if
			return((inputVal)-32.0)*(5.0/9.0); //conversion to Celsius from fahrenheit
		}// end of if
	}// end of convertToCelsius

//-------------------------------------------------------------------------------------------------------------------------------

	public static double convertToFahrenheit(ChoiceBox<String> startingBox,double inputVal)
	{// start of convertToFahrenheit
		if(startingBox.getValue().equals("Kelvin")) //K
		{// end of if
			return((9.0/5.0)*((inputVal) - 273.15)) + 32;
		}// end of if

		else//C
		{// start of if
			return((9d/5d)*inputVal) + 32;
		}// end of if

	}// end of convertToFahrienheit

//-------------------------------------------------------------------------------------------------------------------------------

	private void getStartingChoice(ChoiceBox<String> startingBox)
	{//start of getStartingChoice
	        measurements = startingBox.getValue();
        	System.out.println(measurements);
    }//end of getStartingChoice

//------------------------------------------------------------

    private void getConvertChoice(ChoiceBox<String> convertBox)
    {//start of getCovnertChoice
			measurements = convertBox.getValue();
			System.out.println(measurements);
	}//end of getCovnertChoice
}//end of class
