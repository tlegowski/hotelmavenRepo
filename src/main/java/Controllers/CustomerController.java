package Controllers;

import Model.Main;
import Model.Opinion;
import Model.User;
import Model.UserType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.awt.event.ActionEvent;
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

	@FXML
	private Label infoNameChange;


	private User customer;

/*	public void show() {
		System.out.println(sliderOpinion.getValue());
	}*/

	public void setCustomer(User user) {
		customer = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> helloName.setText(customer.getName()));
		//sendOpinionButton.setOnAction(event -> SaveOpinion());
		changeNameButton.setOnAction(event -> ChangeName());
		changeAddressButton.setOnAction(event -> ChangeAddress());
	}

	public void ChangeName() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			customer.setName(settingName.getText());
			customer.setSurname(settingSurname.getText());
			customer.setPassword(settingPassword.getText());
			customer.setEvidenceNr(settingEvidenceNr.getText());

			session.update(customer);
			session.getTransaction().commit();

			infoNameChange.setText("Dane zostaly zmienione");

		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public void ChangeAddress(){

	}
/*	public void SaveOpinion() {
		Session session = Main.sessionFactory.getCurrentSession();
		session.beginTransaction();

		try {
			Opinion opinion = new Opinion(Double.toString(sliderOpinion.getValue()));
			customer.getOpinions().add(opinion);

			session.save(customer);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}*/

}
