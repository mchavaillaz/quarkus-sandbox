package ch.matthieu.greetings.domain.dto;

import java.time.Instant;
import java.util.Objects;

public class ReadGreeting {
	private Integer id;
	private String name;
	private Instant createdMoment;

	public ReadGreeting() {
	}

	public ReadGreeting(Integer id, String name, Instant createdMoment) {
		this.id = id;
		this.name = name;
		this.createdMoment = createdMoment;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReadGreeting that = (ReadGreeting) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return createdMoment != null ? createdMoment.equals(that.createdMoment) : that.createdMoment == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (createdMoment != null ? createdMoment.hashCode() : 0);
		return result;
	}
}
