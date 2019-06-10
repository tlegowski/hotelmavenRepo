package Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OPINION")
public class Opinion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OPINION_ID")
	private int id;

	@Column
	private String opinion;

	@ManyToMany(mappedBy = "opinions")
	private List<User> users = new ArrayList<>();

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Opinion(String opinion) {
		this.opinion = opinion;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Opinion(){}
}
