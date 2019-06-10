package Controllers;

import Model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField surnameTextField;

	@FXML
	private TextField civilianIdTextField;

	@FXML
	private TextField evidenceNrTextField;

	@FXML
	private TextField loginTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button addEmployeeButton;

	@FXML
	private Label addInfoLabel;

	@FXML
	private ChoiceBox<UserType> choiceUserType;

	@FXML
	private TextField changeCityTextField;

	@FXML
	private TextField changeStreetTextField;

	@FXML
	private TextField changeApartmTextField;

	@FXML
	private ChoiceBox<User> choiceUserForAddress;

	@FXML
	private Button changeAddressButton;

	@FXML
	private Label changeAddressInfoLabel;

	@FXML
	private TableView<Opinion> tableViewOpinions;

	@FXML
	private TableColumn<Opinion, Integer> idOpinionColumn;

	@FXML
	private TableColumn<Opinion, String> opinionColumn;

	@FXML
	private TableView<Report> tableViewReports;

	@FXML
	private TableColumn<Report, Integer> idReportColumn;

	@FXML
	private TableColumn<Report, String> reservationIdColumn;

	@FXML
	private TableColumn<Report, String> reportColumn;

	@FXML
	private TableView<Reservation> tableViewReservation;

	@FXML
	private TableColumn<Reservation, Integer> idReservationColumn;

	@FXML
	private TableColumn<Reservation, LocalDate> dateReservationColumn;

	@FXML
	private TableColumn<Reservation, Integer> clientReservationColumn;

	@FXML
	private TableColumn<Reservation, Integer> employeeReservationColumn;

	@FXML
	private TableColumn<Reservation, Integer> roomsReservationColumn;

	@FXML
	private TableView<Room_type> tableViewRoomType;

	@FXML
	private TableColumn<Room_type, Integer> idRoomTypeColumn;

	@FXML
	private TableColumn<Room_type, String> describeRoomTypeColumn;

	@FXML
	private TableView<Room> tableViewRooms;

	@FXML
	private TableColumn<Room, String> idRoomColumn;

	@FXML
	private TableColumn<Room, String> numberRoomColumn;

	@FXML
	private TableColumn<Room, String> describeRoomColumn;

	@FXML
	private Button deleteRoomButton;

	@FXML
	private Button deleteButtonReport;

	@FXML
	private Label deleteReportLabel;

	@FXML
	private Label deleteRoomLabel;

	@FXML
	private Button deleteRoomTypeButton;

	@FXML
	private Label deleteRoomTypeLabel;

	@FXML
	private ChoiceBox<User> choiceUsers;

	@FXML
	private Button deleteUserButton;

	@FXML
	private Label deleteUserLabel;

	@FXML
	private Button deleteOpinionButton;

	@FXML
	private Label deleteOpinionLabel;

	@FXML
	private Button deleteReservationButton;

	@FXML
	private Label deleteReservationLabel;

	public void RegisterButton(ActionEvent event) {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			User user = new User(
					loginTextField.getText(),
					passwordTextField.getText(),
					nameTextField.getText(),
					surnameTextField.getText(),
					null,
					choiceUserType.getValue(),
					civilianIdTextField.getText(),
					evidenceNrTextField.getText());

			session.save(user);
			session.getTransaction().commit();
			if (choiceUserType.getValue() == UserType.EMPLOYEE)
				addInfoLabel.setText("Zarejestrowano pracownika");
			else if (choiceUserType.getValue() == UserType.CUSTOMER)
				addInfoLabel.setText("Zarejestrowano klienta");
			else if (choiceUserType.getValue() == UserType.ADMIN)
				addInfoLabel.setText("Zarejestrowano admina");
		} catch (HibernateException e) {
			addInfoLabel.setText("Login zajety, sprobuj inny");
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<UserType> userTypes = Arrays.asList(UserType.values());
		choiceUserType.setItems(FXCollections.observableArrayList(userTypes));
		choiceUserType.setValue(UserType.EMPLOYEE);

		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> users = query.list();

		choiceUserForAddress.setItems(FXCollections.observableArrayList(users));
		changeAddressButton.setOnAction(event -> ChangeAddress());
		deleteRoomButton.setOnAction(event -> DeleteRoom());
		deleteButtonReport.setOnAction(event -> DeleteReport());
		deleteRoomTypeButton.setOnAction(event -> DeleteRoomType());
		deleteUserButton.setOnAction(event -> DeleteUser());
		deleteOpinionButton.setOnAction(event -> DeleteOpinion());
		deleteReservationButton.setOnAction(event -> DeleteReservation());
		ShowOpinions();
		ShowReports();
		ShowReservations();
		ShowRoomTypes();
		ShowRooms();
		LoadUsers();
		session.close();
	}

	public void DeleteReservation(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		session.delete(tableViewReservation.getSelectionModel().getSelectedItem());
		session.getTransaction().commit();
		session.close();
		deleteReservationLabel.setText("Usunieto");
		ShowReservations();
	}
	public void DeleteOpinion(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		session.delete(tableViewOpinions.getSelectionModel().getSelectedItem());
		session.getTransaction().commit();
		session.close();
		deleteOpinionLabel.setText("Usunieto");
		ShowOpinions();
	}

	public void DeleteUser(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		session.delete(choiceUsers.getSelectionModel().getSelectedItem());
		session.getTransaction().commit();
		session.close();
		deleteUserLabel.setText("Usunieto uzytkownika " + choiceUsers.getSelectionModel().getSelectedItem().getName());
		LoadUsers();

	}
	public void LoadUsers(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM User";
		Query query = session.createQuery(hql);

		List<User> users = query.list();

		choiceUsers.setItems(FXCollections.observableArrayList(users));

		session.getTransaction().commit();
		session.close();
	}
	public void DeleteRoomType(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();


		session.delete(tableViewRoomType.getSelectionModel().getSelectedItem());
		session.getTransaction().commit();
		session.close();
		deleteRoomTypeLabel.setText("Usunieto");
		ShowRoomTypes();
	}
	public void DeleteReport(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		session.delete(tableViewReports.getSelectionModel().getSelectedItem());
		session.getTransaction().commit();
		session.close();
		deleteReportLabel.setText("Usunieto");
		ShowReports();
	}
	public void DeleteRoom(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		session.delete(tableViewRooms.getSelectionModel().getSelectedItem());
		session.getTransaction().commit();
		session.close();
		deleteRoomLabel.setText("Usunieto");
		ShowRooms();
	}
	public void ShowRooms(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Room";
		Query query = session.createQuery(hql);
		List<Room> rooms = query.list();

		idRoomColumn.setCellValueFactory(Raport -> {
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set(Raport.getValue().getId());
			return property;
		});

		numberRoomColumn.setCellValueFactory(Raport -> {
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set(Raport.getValue().getNumber());
			return property;
		});

		describeRoomColumn.setCellValueFactory(Raport -> {
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set(Raport.getValue().getDescribe());
			return property;
		});
		tableViewRooms.setItems(FXCollections.observableArrayList(rooms));
		session.close();
	}

	public void ShowReservations() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Reservation";
		Query query = session.createQuery(hql);
		List<Reservation> reservations = query.list();

		idReservationColumn.setCellValueFactory(Raport -> {
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set(Raport.getValue().getReservationID());
			return property;
		});
		dateReservationColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

		clientReservationColumn.setCellValueFactory(Raport -> {
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set(Raport.getValue().getCustomer().getName() + " " +
					Raport.getValue().getCustomer().getSurname());
			return property;
		});

		employeeReservationColumn.setCellValueFactory(Raport ->{
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set(Raport.getValue().getEmployee().getName() + " " +
					Raport.getValue().getEmployee().getSurname());
			return property;
		});

		roomsReservationColumn.setCellValueFactory(Raport ->{
			SimpleObjectProperty property = new SimpleObjectProperty();
			property.set("Numer: (" + Raport.getValue().getRoomID().getNumber() + ") " +
					Raport.getValue().getRoomID().getDescribe());
			return property;
		});
		tableViewReservation.setItems(FXCollections.observableArrayList(reservations));

		session.close();
	}

	public void ShowRoomTypes() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Room_type";
		Query query = session.createQuery(hql);
		List<Room_type> room_types = query.list();

		idRoomTypeColumn.setCellValueFactory(Raport -> {
					SimpleObjectProperty property = new SimpleObjectProperty();
					property.setValue(Raport.getValue().getRoomTypeID());
					return property;
				}
		);
		describeRoomTypeColumn.setCellValueFactory(Raport -> {
					SimpleObjectProperty property = new SimpleObjectProperty();
					property.setValue(Raport.getValue().getDescribe());
					return property;
				}
		);
		tableViewRoomType.setItems(FXCollections.observableArrayList(room_types));
		session.close();
	}

	public void ShowOpinions() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Opinion";
		Query query = session.createQuery(hql);
		List<Opinion> opinions = query.list();

		idOpinionColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		opinionColumn.setCellValueFactory(new PropertyValueFactory<>("opinion"));
		tableViewOpinions.setItems(FXCollections.observableArrayList(opinions));

		session.close();
	}

	public void ShowReports() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Report";
		Query query = session.createQuery(hql);
		List<Report> reports = query.list();

		idReportColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		reservationIdColumn.setCellValueFactory(
				Raport -> {
					SimpleObjectProperty property = new SimpleObjectProperty();
					property.setValue("Nr pok: " + Raport.getValue().getReservationID().getRoomID().getNumber() + " Data rez: " + Raport.getValue().getReservationID().getDate());
					return property;
				}
		);
		reportColumn.setCellValueFactory(new PropertyValueFactory<>("report"));
		tableViewReports.setItems(FXCollections.observableArrayList(reports));
		session.close();
	}

	public void ChangeAddress() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		Address address = choiceUserForAddress.getValue().getIdAddress();

		try {
			address.setCity(changeCityTextField.getText());
			address.setStreet(changeStreetTextField.getText());
			address.setApartment(Integer.parseInt(changeApartmTextField.getText()));

			session.update(address);
			session.getTransaction().commit();
			changeAddressInfoLabel.setText("Zmieniono adres");
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			changeAddressInfoLabel.setText("Numer mieszkania musi byc liczba");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
