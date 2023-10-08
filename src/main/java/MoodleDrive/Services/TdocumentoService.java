package MoodleDrive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import MoodleDrive.Models.Tdocumento;
import MoodleDrive.Repositories.TdocumentoRepository;

@Service
public class TdocumentoService {

 @Autowired
 private TdocumentoRepository tdocumentoRepository;

 public List<Tdocumento> getAllDocumentTypes() {
 return tdocumentoRepository.findAll();
 }
}
