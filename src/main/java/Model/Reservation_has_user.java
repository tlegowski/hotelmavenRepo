package Model;

import javax.persistence.*;

@Entity
public class Reservation_has_user {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reservation")
	private Reservation idReservation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	private User idUser;
}
