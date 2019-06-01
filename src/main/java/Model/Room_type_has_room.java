package Model;

import javax.persistence.*;

@Entity
public class Room_type_has_room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room")
	private Room idRoom;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room_type")
	private Room_type idRoomType;
}
