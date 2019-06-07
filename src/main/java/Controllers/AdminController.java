package Controllers;

import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

/*
	@FXML
	private ChoiceBox<UserType> choiceBox;

	@FXML
	private ChoiceBox<User> choiceUsers;
*/

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
	private ChoiceBox<User> choiceUsersDelete;

	@FXML
	private Button deleteUserButton;

	@FXML
	private Label deleteInfoLabel;

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
	private TableColumn<Report, Integer> reservationIdColumn;

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

		choiceUsersDelete.setItems(FXCollections.observableArrayList(users));
		deleteUserButton.setOnAction(event -> DeleteUser());
		choiceUserForAddress.setItems(FXCollections.observableArrayList(users));
		changeAddressButton.setOnAction(event -> ChangeAddress());

		ShowOpinions();
		ShowReports();
		//ShowReservations();
		ShowRoomTypes();
		session.close();
	}


	//problem z wyswietlaniem ID
	/*public void ShowReservations(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Opinion";
		Query query = session.createQuery(hql);
		List<Reservation> reservations = query.list();

		idReservationColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		dateReservationColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		clientReservationColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
		employeeReservationColumn.setCellValueFactory(new PropertyValueFactory<>("employee"));
		roomsReservationColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));

		tableViewReservation.setItems(FXCollections.observableArrayList(reservations));


	}*/

	public void ShowRoomTypes(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Room_type";
		Query query = session.createQuery(hql);
		List<Room_type> room_types = query.list();

		idRoomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		describeRoomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("describe"));
		tableViewRoomType.setItems(FXCollections.observableArrayList(room_types));
	}
	public void ShowOpinions(){
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

	public void ShowReports(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Report";
		Query query = session.createQuery(hql);
		List<Report> reports = query.list();

		idReportColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		reservationIdColumn.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
		reportColumn.setCellValueFactory(new PropertyValueFactory<>("report"));
		tableViewReports.setItems(FXCollections.observableArrayList(reports));
	}
	public void ChangeAddress(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		Address address = choiceUserForAddress.getValue().getIdAddress();

		try{
			address.setCity(changeCityTextField.getText());
			address.setStreet(changeStreetTextField.getText());
			address.setApartment(Integer.parseInt(changeApartmTextField.getText()));

			session.update(address);
			session.getTransaction().commit();
			changeAddressInfoLabel.setText("Zmieniono adres");
		}catch (HibernateException e){
			e.printStackTrace();
		}catch (NumberFormatException e){
			changeAddressInfoLabel.setText("Numer mieszkania musi byc liczba");
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	public void DeleteUser() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();
		Address address = choiceUsersDelete.getValue().getIdAddress();
		List<Opinion> opinion = choiceUsersDelete.getValue().getOpinions();
		List<Report> reports = choiceUsersDelete.getValue().getReports();

		try {
			User user = choiceUsersDelete.getValue();
			session.delete(opinion);
			session.delete(reports);
			session.delete(user);

		} catch (LazyInitializationException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		deleteInfoLabel.setText("Usunieto uzytkownika");
	}
}
