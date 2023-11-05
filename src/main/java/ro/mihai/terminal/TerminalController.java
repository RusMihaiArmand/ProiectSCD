package ro.mihai.terminal;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminals")
public class TerminalController {

  @Autowired
  private TerminalService terminalService;


  @GetMapping
  public ResponseEntity<List<TerminalDTO>> getAll(
      @RequestParam(name = "idTerminal", required = false) String idTermPar
  ) {
    return ResponseEntity.ok(terminalService.getTerminals(idTermPar));
  }


  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody TerminalDTO terminal) {

    terminalService.create(terminal);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> edit(@PathVariable String id,
      @Valid @RequestBody TerminalData terminalData) {

    terminalService.edit(id, terminalData);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {

    terminalService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
