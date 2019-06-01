package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main extends Application {
	public static SessionFactory sessionFactory;

	public static void main(String[] args) {
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		launch(args);
		/*SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();*/

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/login_stage.fxml"));
		primaryStage.setTitle("Hotel");
		primaryStage.setScene(new Scene(root, 300, 300));
		primaryStage.show();

	}
}
