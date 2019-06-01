package Model;

import javax.persistence.*;

@Entity
public class Room_type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int floor;

	@Column
	private String view;
}
