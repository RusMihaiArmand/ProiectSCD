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


@RestController
@RequestMapping("/positions")
public class PositionController {

  @Autowired
  private PositionService positionService;



  @GetMapping
  public ResponseEntity<List<PositionDTO>> getAll(
      @RequestParam(name = "idTerminal", required = false) String idTermPar,
      @RequestParam(name = "startDate", required = false) String startPar,
      @RequestParam(name = "endDate", required = false) String endPar
  ) {
    return ResponseEntity.ok(positionService.getPositions(idTermPar, startPar, endPar));
  }


  @PostMapping
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
