package Controllers;

import Model.Main;
import Model.User;
import Model.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController {

	@FXML
	private ChoiceBox<?> choiceBox;

	@FXML
	private Button deleteUserButton;

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
	private Label deleteInfoLabel;

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
							UserType.EMPLOYEE,
							civilianIdTextField.getText(),
							evidenceNrTextField.getText());

			session.save(user);
			session.getTransaction().commit();
			addInfoLabel.setText("Zarejestrowano pracownika");
		} catch (HibernateException e) {
			addInfoLabel.setText("Login zajety, sprobuj inny");
			e.printStackTrace();
		}

	}

	/*@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadUsers();
	}

	public void loadUsers(){
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		String hql = "FROM Users";
		Query query = session.createQuery(hql);
		List<User> users= query.list();
		//choiceBox.getItems().addAll(users);
		for(User user: users){
			System.out.println(user);
		}
	}*/
}
