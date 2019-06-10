package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String report;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RESERVATION_ID")
	private Reservation reservationID;

	@ManyToMany(mappedBy = "reports")
	private List<User> users = new ArrayList<>();


	public Report(String report){
		this.report = report;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reservation getReservationID() {
		return reservationID;
	}

	public void setReservationID(Reservation reservationID) {
		this.reservationID = reservationID;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Report(){}
}
