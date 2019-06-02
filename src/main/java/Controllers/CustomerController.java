package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerController {

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



	public void show(){
		System.out.println(sliderOpinion.getValue());
	}




}
