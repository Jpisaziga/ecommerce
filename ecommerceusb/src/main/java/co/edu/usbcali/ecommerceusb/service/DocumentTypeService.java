package co.edu.usbcali.ecommerceusb.service;

import co.edu.usbcali.ecommerceusb.dto.DocumentTypeResponse;

import java.util.List;

public interface DocumentTypeService {

    List<DocumentTypeResponse> findAll();

    DocumentTypeResponse findById(Integer id);
}