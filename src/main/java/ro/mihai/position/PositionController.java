package ro.mihai.position;

//import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
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

/**
 * @author Radu Miron
 * @version 1
 */
@RestController // creates an instance of the current class
@RequestMapping("/positions") // maps the requests starting with '/positions' to this controller
public class PositionController {

  @Autowired // creates the injection of PositionService instance
  private PositionService positionService;

  //@Operation(summary = "Save a new position")
//    @PostMapping // maps the '/positions' POST requests to this method
//    public Position create(@RequestBody PositionDTO position) {
//        return positionService.create(position);
//    }

  @GetMapping // maps the '/positions' POST requests to this method
  public ResponseEntity<List<PositionDTO>> getAll(
      @RequestParam(name = "idTerminal", required = false) String idTermPar,
      @RequestParam(name = "startDate", required = false) String startPar,
      @RequestParam(name = "endDate", required = false) String endPar
  ) {
    return ResponseEntity.ok(positionService.getPositions(idTermPar, startPar, endPar));
  }

  // @Operation(summary = "Save a new position")
  @PostMapping // maps the '/positions' POST requests to this method
  public ResponseEntity<Void> create(@RequestBody PositionData position) {

    positionService.create(position);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> edit(@PathVariable Integer id,
      @RequestBody PositionData positionData) {

    positionService.edit(id, positionData);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {

    positionService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
