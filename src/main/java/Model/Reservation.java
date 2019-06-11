package Model;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESERVATION_ID")
	private int reservationID;

	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room roomID;

	@Column
	@NaturalId
	private LocalDate date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User employee;


	public Reservation(){}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public Room getRoomID() {
		return roomID;
	}

	public void setRoomID(Room roomID) {
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


	public Reservation(Room roomID, LocalDate date, User customer, User employee) {
		this.roomID = roomID;
		this.date = date;
		this.customer = customer;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"reservationID=" + reservationID +
				", customer=" + customer +
				'}';
	}
}
