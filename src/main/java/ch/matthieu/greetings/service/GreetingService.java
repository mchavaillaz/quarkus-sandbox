package ch.matthieu.greetings.service;

import ch.matthieu.greetings.domain.dto.CreateGreeting;
import ch.matthieu.greetings.domain.dto.ReadGreeting;

import java.util.List;

public interface GreetingService {
	ReadGreeting createGreeting(CreateGreeting createGreeting);

	List<ReadGreeting> getGreetings(Integer limit);
}
