package ch.matthieu.greetings.repository;

import ch.matthieu.greetings.domain.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Integer> {
}
