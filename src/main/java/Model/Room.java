package Model;

import javax.persistence.*;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private int number;

	@Column
	private String describe;

}
