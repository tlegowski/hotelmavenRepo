package Controllers;

import Model.Main;
import Model.User;
import Model.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class RegisterContoller {
	@FXML
	private TextField registerName;

	@FXML
	private TextField registerSurname;

	@FXML
	private TextField registerCivilianID;

	@FXML
	private TextField registerEvidenceNr;

	@FXML
	private TextField registerLogin;

	@FXML
	private PasswordField registerPass;

	@FXML
	private Button registerButton;

	@FXML
	private Button registerBack;

	@FXML
	private Label registerInfo;

	public void RegisterButton(ActionEvent event) {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			User user = new User
					(registerLogin.getText(), registerPass.getText(), registerName.getText(), registerSurname.getText(), null, UserType.CUSTOMER, registerCivilianID.getText(), registerEvidenceNr.getText());

			session.save(user);
			session.getTransaction().commit();
			registerInfo.setText("Zarejestrowano pomyslnie");
		} catch (HibernateException e) {
			registerInfo.setText("Login zajety, sprobuj inny");
			e.printStackTrace();
		}

	}
}
