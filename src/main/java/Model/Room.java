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
	private List<Room_type> room_types = new ArrayList<>();

	@Override
	public String toString() {
		return "Room{" +
				"id=" + id +
				", number=" + number +
				", describe='" + describe + '\'' +
				", room_types=" + room_types +
				'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public List<Room_type> getRoom_types() {
		return room_types;
	}

	public void setRoom_types(List<Room_type> room_types) {
		this.room_types = room_types;
	}
}
