package Model;

import javax.persistence.*;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String report;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRoom")
	private Room idRoom;
}
