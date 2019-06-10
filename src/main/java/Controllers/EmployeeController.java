package Controllers;

import Model.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class EmployeeController implements Initializable {

	@FXML
	private TableView<Reservation> tableViewReservation;

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

	public void setEmployee(User user) {
		employee = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			helloLabel.setText("Witaj " + employee.getName());
			changeNameButton.setOnAction(event -> ChangeName());
			changeAddressButton.setOnAction(event -> ChangeAddress());

			dateReservationColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			ReadReservations();
		});
	}


	public void ReadReservations() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Reservation";
		Query query = session.createQuery(hql);

		List<Reservation> reservations = query.list();
		Set<Reservation> busyReservations = new HashSet<>();


		for(Reservation re :reservations){
			if(re.getEmployee().getUserID() == employee.getUserID()){
				busyReservations.add(re);
			}
		}
		tableViewReservation.setItems(FXCollections.observableArrayList(busyReservations));



		/*if(!reservations.isEmpty()){
			System.out.println(reservations.get(0));
		}else
			System.out.println("nie znaleziono");*/
		//List<Reservation> reservations = query.list();
		//tableViewReservation.setItems(FXCollections.observableArrayList());
	}

	public void ChangeName() {
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
