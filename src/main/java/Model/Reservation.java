package Model;

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
	private LocalDate date;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerID")
	private User customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeID")
	private User employee;


	public Reservation(){}
//LocaldDate costam = LocalDate.of(2014, Month.JANUARY, 1);
}
