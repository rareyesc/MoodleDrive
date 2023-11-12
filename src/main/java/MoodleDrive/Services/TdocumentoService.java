package MoodleDrive.Services;

import java.util.List;
import MoodleDrive.Models.Tdocumento;
import org.springframework.stereotype.Service;
import MoodleDrive.Repositories.TdocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TdocumentoService {
 @Autowired
 private TdocumentoRepository tdocumentoRepository;
 public List<Tdocumento> getAllDocumentTypes() {
 return tdocumentoRepository.findAll();
 }
}
