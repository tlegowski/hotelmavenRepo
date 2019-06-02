package Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "reservation_has_users",
		joinColumns = {@JoinColumn(name = "fk_reservation")},
		inverseJoinColumns = {@JoinColumn(name = "fk_user")})
	private List<User> users = new ArrayList<>();

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "reservation_has_room",
			joinColumns = {@JoinColumn(name = "fk_reservation")},
			inverseJoinColumns = {@JoinColumn(name = "fk_room")})
	private List<Room> rooms = new ArrayList<>();

	/*	@ManyToMany
		@JoinTable(name = "reservation_has_user",
			joinColumns = {@JoinColumn(name = "fk_reservation")},
				inverseJoinColumns = {@JoinColumn(name = "fk_user")})
		private List<User> users = new ArrayList<User>();*/

	public Reservation(){}
//LocaldDate costam = LocalDate.of(2014, Month.JANUARY, 1);
}
