package ch.matthieu.greetings.domain.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CreateGreeting {
	private String name;

	public CreateGreeting() {
	}

	public CreateGreeting(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CreateGreeting that = (CreateGreeting) o;

		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
