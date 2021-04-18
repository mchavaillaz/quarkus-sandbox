package ch.matthieu.greetings.domain.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "greeting")
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "created_moment", nullable = false)
	private Instant createdMoment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Instant getCreatedMoment() {
		return createdMoment;
	}

	public void setCreatedMoment(Instant createdMoment) {
		this.createdMoment = createdMoment;
	}
}
