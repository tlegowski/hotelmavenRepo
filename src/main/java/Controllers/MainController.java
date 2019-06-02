package Controllers;

import Model.Main;
import Model.User;
import Model.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class MainController {
	@FXML
	private Label labeltxt;

	@FXML
	private TextField userTxt;

	@FXML
	private PasswordField passwordtxt;

	public void Login(ActionEvent event) {
		Session session = Main.sessionFactory.openSession();
		session.beginTransaction();

		try {
			String log = userTxt.getText();
			String pass = passwordtxt.getText();

			User user = session.byNaturalId(User.class).using("login", log).load();
			if (user != null) {
				if (user.getPassword().equals(pass) && user.getUserType().equals(UserType.CUSTOMER)) {

					Stage registerStage = new Stage();
					registerStage.initModality(Modality.APPLICATION_MODAL);
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/customer_stage.fxml"));
					Parent root = fxmlLoader.load();

					CustomerController customerController = fxmlLoader.getController();
					customerController.setCustomer(user);


					registerStage.setTitle("Hotel");
					registerStage.setScene(new Scene(root, 433, 400));
					registerStage.setResizable(false);
					registerStage.showAndWait();

				}else{
					labeltxt.setText("Zle haslo");
				}
			}else{
				labeltxt.setText("Nieprawidlowe dane");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		session.getTransaction().commit();

	}


	public void Register(ActionEvent event) throws IOException {
		Stage registerStage = new Stage();
		registerStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("/register_stage.fxml"));
		registerStage.setTitle("Hotel");
		registerStage.setScene(new Scene(root, 410, 440));
		registerStage.setResizable(false);
		registerStage.showAndWait();
	}
}
