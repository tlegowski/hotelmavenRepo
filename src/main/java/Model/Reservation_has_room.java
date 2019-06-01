package Model;

import javax.persistence.*;

@Entity
public class Reservation_has_room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reservation")
	private Reservation idReservation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room")
	private Room idRoom;
}
