package ro.mihai.terminal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TerminalRepository extends JpaRepository<Terminal, String>,
    QuerydslPredicateExecutor<Terminal> {

}
