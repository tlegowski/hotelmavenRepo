package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROOM_TYPE")
public class Room_type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_TYPE_ID")
	private int roomTypeID;

	@Column
	private int floor;

	@Column
	private String view;

	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name = "room_type_has_room",
			joinColumns = {@JoinColumn(name = "fk_room_type")},
			inverseJoinColumns = {@JoinColumn(name = "fk_room")})
	private List<Room> rooms = new ArrayList<>();
}
