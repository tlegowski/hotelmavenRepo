package Controllers;

import Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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

	@FXML
	private Label infoAddressChange;

	@FXML
	private Label datePickerLabel;


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
		checkReservationButton.setOnAction(event -> CheckReservation());
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
		Address address = customer.getIdAddress();
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();
		try{
			address.setCity(settingCity.getText());
			address.setStreet(settingStreet.getText());
			try {
				address.setApartment(Integer.parseInt(settingApartment.getText()));
			}catch(NumberFormatException e){
				infoAddressChange.setText("Numer mieszkania musi byc liczba");
				e.printStackTrace();
				return;
			}
			session.update(address);
			session.getTransaction().commit();
			infoAddressChange.setText("Zmieniono adres");

		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public void CheckReservation(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();
		LocalDate localDate = datePicker.getValue();

		Reservation reservation = session.byNaturalId(Reservation.class)
				.using("date", localDate)
				.load();

		session.close();
		if(reservation != null) {
			datePickerLabel.setText("Termin zajęty, wybierz inny");
		}else if(datePicker.getValue() == null){
			datePickerLabel.setText("Wybierz date");
		}else{
			BookRoom();
		}
	}

	public void BookRoom(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Room";
		Query query = session.createQuery(hql);
		List<Room> roomsList = query.list();

		hql = "FROM Reservation";
		query = session.createQuery(hql);
		List<Reservation> ReservationList = query.list();

		List<Room> freeRooms;

		for(Room room : roomsList){
			System.out.println(room);
		}
		for(Reservation reservation1: ReservationList){
			System.out.println(reservation1);
		}
	}

/*	public void SaveOpinion() {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			Opinion opinion = new Opinion(Double.toString(sliderOpinion.getValue()));
			customer.getOpinions().add(opinion);

			session.save(opinion);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}*/

}
