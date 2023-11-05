package ro.mihai.terminal;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import ro.mihai.exceptions.CodeException;
import ro.mihai.exceptions.ErrorCode;


@Service
public class TerminalService {


  @Autowired
  private TerminalRepository terminalRepository;

  public List<TerminalDTO> getTerminals(String terminalIdParam) {

    List<Terminal> terminals = terminalRepository.findAll();

    if (terminalIdParam == null) {

      return (terminals.stream()
          .map(TerminalMapper.INSTANCE::mapTerminalEtyToTerminalDto)
          .collect(Collectors.toList())
      );

    } else {

      return (terminals.stream()
          .filter(terminal ->
              terminal.getId().equals(terminalIdParam)

          )
          .map(TerminalMapper.INSTANCE::mapTerminalEtyToTerminalDto)
          .collect(Collectors.toList())
      );
    }
  }


  @Transactional()
  public void create(TerminalDTO terminalDTO) {

    Terminal term = terminalRepository.findById(terminalDTO.getId()).orElse(null);

    if (term == null) {

      Terminal toSave = new Terminal();

      toSave.setId(terminalDTO.getId());
      toSave.setTerminalName(terminalDTO.getTerminalName());

      TerminalMapper.INSTANCE.mapTerminalEtyToTerminalDto(terminalRepository.save(toSave));

    } else {
      throw new CodeException(
          CodeException.CodeExceptionElement.builder()
              .errorDescription(ErrorCode.TERMINAL_ID_EXISTS).build());
    }
  }

  @Transactional
  public void edit(String id, TerminalData updateData) {

    Terminal ter = terminalRepository.findById(id)
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.TERMINAL_NOT_FOUND).build()));

    ter.setTerminalName(updateData.getTerminalName());

    TerminalMapper.INSTANCE.mapTerminalEtyToTerminalDto(terminalRepository.save(ter));
  }

  @Transactional
  public void delete(String id) {

    terminalRepository.findById(id)
        .orElseThrow(() -> new CodeException(
            CodeException.CodeExceptionElement.builder()
                .errorDescription(ErrorCode.TERMINAL_NOT_FOUND).build()));

    terminalRepository.deleteById(id);
  }
  
}
