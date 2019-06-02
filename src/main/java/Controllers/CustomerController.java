package Controllers;

import Model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button checkReservationButton;

	@FXML
	private TextField settingName;

	@FXML
	private Slider sliderOpinion;

	@FXML
	private TextField settingSurname;

	@FXML
	private Button changeNameButton;

	@FXML
	private PasswordField settingPassword;

	@FXML
	private TextField settingCity;

	@FXML
	private TextField settingStreet;

	@FXML
	private TextField settingApartment;

	@FXML
	private Button changeAddressButton;

	@FXML
	private TextField settingEvidenceNr;

	@FXML
	private Button sendOpinionButton;

	@FXML
	private Label helloName;

	private User customer;

	public void show() {
		System.out.println(sliderOpinion.getValue());
	}

	public void setCustomer(User user) {
		customer = user;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Platform.runLater(() -> {
			System.out.println(customer);
			helloName.setText(customer.getName());
			//Platform.runLater(() -> System.out.println(customer.getName()));

		});
	}

}
//helloName.setText(customer.getName())