package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_ID")
	private int id;

	@Column
	private int number;

	@Column
	private String describe;

	@ManyToMany(mappedBy = "rooms")
	private List<Reservation> reservations = new ArrayList<>();

	@ManyToMany(mappedBy = "rooms")
	private List<Room_type> room_types = new ArrayList<>();
}
