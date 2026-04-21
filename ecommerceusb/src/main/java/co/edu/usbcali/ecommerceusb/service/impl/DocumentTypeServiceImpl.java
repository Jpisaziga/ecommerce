package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;
import co.edu.usbcali.ecommerceusb.mapper.DocumentTypeMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentTypeResponse> getDocumentTypes() {
        return DocumentTypeMapper.modelToDocumentTypeResponseList(
                documentTypeRepository.findAll()
        );
    }

    @Override
    public DocumentTypeResponse getDocumentTypeById(Integer id) throws Exception {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new Exception("DocumentType no encontrado"));

        return DocumentTypeMapper.modelToDocumentTypeResponse(documentType);
    }
}