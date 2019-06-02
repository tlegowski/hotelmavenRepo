package Model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESERVATION_ID")
	private int reservationID;

	@Column
	private int roomID;

	@Column
	@NaturalId
	private LocalDate date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerID")
	private User customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeID")
	private User employee;


	public Reservation(){}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"reservationID=" + reservationID +
				", roomID=" + roomID +
				", date=" + date +
				", customer=" + customer +
				", employee=" + employee +
				'}';
	}

	//LocaldDate costam = LocalDate.of(2014, Month.JANUARY, 1);
}
