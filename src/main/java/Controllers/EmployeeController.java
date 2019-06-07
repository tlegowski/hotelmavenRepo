package Controllers;

import Model.Address;
import Model.Main;
import Model.Reservation;
import Model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

	@FXML
	private TableView<Reservation> tableViewReservation;

	@FXML
	private TableColumn<Reservation, Integer> idReservationColumn;

	@FXML
	private TableColumn<Reservation, LocalDate> dateReservationColumn;

	@FXML
	private Label helloLabel;

	@FXML
	private TextField settingName;

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
	private Label infoNameChange;

	@FXML
	private Label infoAddressChange;

	private User employee;

	public void setEmployee(User user){
		employee = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() ->
			 	helloLabel.setText("Witaj " + employee.getName()));
				changeNameButton.setOnAction(event -> ChangeName());
				changeAddressButton.setOnAction(event -> ChangeAddress());

	}

	public void ChangeName(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			employee.setName(settingName.getText());
			employee.setSurname(settingSurname.getText());
			employee.setPassword(settingPassword.getText());
			employee.setEvidenceNr(settingEvidenceNr.getText());

			session.update(employee);
			session.getTransaction().commit();

			infoNameChange.setText("Dane zostaly zmienione");

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void ChangeAddress() {
		//System.out.println(customer);
		Address address = employee.getIdAddress();
		boolean newOne = false;
		if (address == null) {
			address = new Address();
			newOne = true;
		}
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();
		try {
			address.setCity(settingCity.getText());
			address.setStreet(settingStreet.getText());
			address.setApartment(Integer.parseInt(settingApartment.getText()));
			employee.setIdAddress(address);
			if (newOne) {
				session.save(address);
				session.update(employee);
			} else {
				session.update(address);
			}
			session.getTransaction().commit();
			infoAddressChange.setText("Zmieniono adres");

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			infoAddressChange.setText("Numer mieszkania musi byc liczba");
			e.printStackTrace();
			return;
		} finally {
			session.close();
		}
	}
}
