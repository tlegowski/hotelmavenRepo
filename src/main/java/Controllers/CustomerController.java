package Controllers;

import Model.*;
import javafx.application.Platform;
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
import java.util.*;

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

	@FXML
	private Label infoAddressChange;

	@FXML
	private Label datePickerLabel;


	@FXML
	private TableView<Room> tableView;

	@FXML
	private TableColumn<Room, Integer> idRoomColumn;

	@FXML
	private TableColumn<Room, String> describeColumn;

	@FXML
	private TableColumn<Room, Integer> numberColumn;

	@FXML
	private Label sendInfo;

	@FXML
	private Button reservationButton;

	@FXML
	private Label reservationLabel;

	private User customer;

/*	public void show() {
		System.out.println(sliderOpinion.getValue());
	}*/

	public void setCustomer(User user) {
		customer = user;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() ->
				helloName.setText(customer.getName()));
		sendOpinionButton.setOnAction(event -> SaveOpinion());
		changeNameButton.setOnAction(event -> ChangeName());
		changeAddressButton.setOnAction(event -> ChangeAddress());
		checkReservationButton.setOnAction(event -> CheckReservation());
		//sliderOpinion.setMinorTickCount(0);
		sliderOpinion.setSnapToTicks(true);
		idRoomColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		describeColumn.setCellValueFactory(new PropertyValueFactory<>("describe"));
		numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
		reservationButton.setOnAction(event -> MakeReservation());

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
		} finally {
			session.close();
		}
	}

	public void ChangeAddress() {
		//System.out.println(customer);
		Address address = customer.getIdAddress();
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
			customer.setIdAddress(address);
			if(newOne){
				session.save(address);
				session.update(customer);
			}else{
				session.update(address);
			}
			//	session.update(customer);
			session.getTransaction().commit();
			infoAddressChange.setText("Zmieniono adres");

		} catch (HibernateException e) {
			e.printStackTrace();
		}catch (NumberFormatException e) {
			infoAddressChange.setText("Numer mieszkania musi byc liczba");
			e.printStackTrace();
			return;
		}
		finally {
			session.close();
		}
	}

	public void CheckReservation() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();
		LocalDate localDate = datePicker.getValue();

		Reservation reservation = session.byNaturalId(Reservation.class)
				.using("date", localDate)
				.load();

		session.close();
		if (reservation != null) {
			datePickerLabel.setText("Termin zajęty, wybierz inny");
		} else if (datePicker.getValue() == null) {
			datePickerLabel.setText("Wybierz date");
		} else {
			ShowFreeRooms();
		}
	}

	public void ShowFreeRooms() {
		datePickerLabel.setText("Wybierz pokoj:");
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Room";
		Query query = session.createQuery(hql);
		List<Room> roomsList = query.list();

		hql = "FROM Reservation";
		query = session.createQuery(hql);
		List<Reservation> ReservationList = query.list();
		session.close();
		ArrayList<Room> freeRooms = new ArrayList<>();
		Set<Room> busyRooms = new HashSet<>();
		for (Reservation re : ReservationList) {
			busyRooms.add(re.getRoomID());
		}

		for (Room room : roomsList) {
			if (!busyRooms.contains(room)) {
				freeRooms.add(room);
			}
		}
		tableView.setItems(FXCollections.observableArrayList(freeRooms));
	}

	public void MakeReservation() {
		Room room = tableView.getSelectionModel().getSelectedItem();
		if (room != null) {
			LocalDate localDate = datePicker.getValue();

			Session session = Main.sessionFactory.openSession();
			session.beginTransaction();

			String hql = "FROM User WHERE USER_ID = 2";
			Query query = session.createQuery(hql);

			List<User> employee = query.list();

			Reservation reservation = new Reservation(room, localDate, customer, employee.get(0));
			reservationLabel.setText("Zarezerwowano");
			tableView.getItems().clear();
			session.save(reservation);
			session.getTransaction().commit();
			session.close();
		}else{
			reservationLabel.setText("Wybierz date");
		}
	}


	public void SaveOpinion() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			Opinion opinion = new Opinion(Double.toString(sliderOpinion.getValue()));
			opinion.getUsers().add(customer);

			session.save(opinion);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		sendOpinionButton.setDisable(true);
		sliderOpinion.setDisable(true);
		sendInfo.setText("Dziekujemy");
	}

}
