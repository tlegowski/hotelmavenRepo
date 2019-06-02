package Model;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "USER")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userID;

	@NaturalId
	@Column(unique = true)
	private String login;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String surname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_address")
	private Address idAddress;

	@ManyToMany(mappedBy = "users")
	private List<Opinion> opinions = new ArrayList<>();

	@Column
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@Column
	private String civilianID;

	@Column
	private String evidenceNr;

	public User(String login, String password, String name, String surname, Address idAddress, UserType userType, String civilianID, String evidenceNr) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.idAddress = idAddress;
		this.userType = userType;
		this.civilianID = civilianID;
		this.evidenceNr = evidenceNr;
	}

	public User() {
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Address idAddress) {
		this.idAddress = idAddress;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCivilianID() {
		return civilianID;
	}

	public void setCivilianID(String civilianID) {
		this.civilianID = civilianID;
	}

	public String getEvidenceNr() {
		return evidenceNr;
	}

	public void setEvidenceNr(String evidenceNr) {
		this.evidenceNr = evidenceNr;
	}

	public List<Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(List<Opinion> opinions) {
		this.opinions = opinions;
	}
}