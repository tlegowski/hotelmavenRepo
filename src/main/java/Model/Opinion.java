package Model;

import javax.persistence.*;

@Entity
public class Opinion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String opinion;
}
