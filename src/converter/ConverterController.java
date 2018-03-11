package converter;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * UI controller for events and initializing components.
 */
public class ConverterController {
	@FXML
	private TextField textfield1;
	@FXML
	private TextField textfield2;
	@FXML
	private ComboBox<Length> unitbox1 = new ComboBox<>();
	@FXML
	private ComboBox<Length> unitbox2 = new ComboBox<>();

	private String temp1 = "";
	private String temp2 = "";
	private Length[] type = Length.values();

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		// This is for testing
		System.out.println("Running initialize");
		if (unitbox1 != null) {
			unitbox1.getItems().addAll(type);
			unitbox1.getSelectionModel().select(0); // select an item to show
		}
		if (unitbox2 != null) {
			unitbox2.getItems().addAll(type);
			unitbox2.getSelectionModel().select(1); // select an item to show
		}
	}

	/**
	 * Convert a distance from one unit to another.
	 */
	public void handleConvert(ActionEvent event) {
		DecimalFormat df4 = new DecimalFormat(".####"); // set decimal number
		try {
			this.textfield1.setStyle("-fx-background-color: white;");
			this.textfield2.setStyle("-fx-background-color: white;");

			String currentText1 = this.textfield1.getText();
			String currentText2 = this.textfield2.getText();

			// box1 convert to box2
			if (!currentText1.equals(temp1) && currentText2.equals(temp2)) {
				if (Double.parseDouble(currentText1) >= 0) {
					temp1 = currentText1;
					Double value = converter(Double.parseDouble(temp1), unitbox1.getValue(), unitbox2.getValue());
					temp2 = df4.format(value);
					textfield2.setText(temp2);
				} else {
					this.textfield1.setStyle("-fx-background-color: red;");
					temp1 = currentText1;
				}

			}
			// box2 to box1
			else if (currentText1.equals(temp1) && !currentText2.equals(temp2)) {
				temp2 = currentText2;
				Double value = converter(Double.parseDouble(temp2), unitbox2.getValue(), unitbox1.getValue());
				temp1 = df4.format(value);
				textfield1.setText(temp1);
			}

		} catch (NumberFormatException e) {
			this.textfield1.setStyle("-fx-background-color: red;");
			this.textfield2.setStyle("-fx-background-color: red;");
		}

	}

	/**
	 * clear both text-field
	 * 
	 * @param event
	 */
	public void handleClear(ActionEvent event) {
		this.textfield1.setStyle("-fx-background-color: white;");
		this.textfield2.setStyle("-fx-background-color: white;");
		System.out.println(event);
		textfield1.setText("");
		textfield2.setText("");
		this.temp1 = "";
		this.temp2 = "";
	}

	/**
	 * press enter to take action
	 * 
	 * @param event
	 */
	public void handleEnterPress(ActionEvent event) {
		handleConvert(event);
	}

	/**
	 * convert input value with from unit to another.
	 * 
	 * @param value
	 *            input value.
	 * @param fromUnit
	 *            base unit.
	 * @param toUnit
	 *            unit wants to convert.
	 * @return converted unit.
	 */
	public double converter(double value, Length fromUnit, Length toUnit) {
		return (value * fromUnit.getValue()) / toUnit.getValue();
	}

}
