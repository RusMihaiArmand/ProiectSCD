package ro.mihai.terminal;

import org.springframework.data.repository.CrudRepository;
//import ro.mihai.terminal.Terminal;

/**
 * @author Radu Miron
 * @version 1
 */
public interface TerminalRepository extends CrudRepository<Terminal, String> {
}
