package co.edu.usbcali.ecommerceusb.service.impl;

import co.edu.usbcali.ecommerceusb.dto.CreateUserRequest;
import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.mapper.UserMapper;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.model.User;
import co.edu.usbcali.ecommerceusb.repository.DocumentTypeRepository;
import co.edu.usbcali.ecommerceusb.repository.UserRepository;
import co.edu.usbcali.ecommerceusb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) return List.of();
        return UserMapper.modelToUserResponseList(users);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        if (id == null || id <= 0) throw new RuntimeException("Debe ingresar un id válido para buscar");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario no encontrado con el id: %d", id)));
        return UserMapper.modelToUserResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        if (email == null || email.isBlank()) throw new RuntimeException("Debe ingresar email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(String.format("Usuario no encontrado con el email: %s", email)));
        return UserMapper.modelToUserResponse(user);
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) throws Exception {
        if (Objects.isNull(request)) throw new Exception("El objeto request no puede ser nulo.");
        if (Objects.isNull(request.getFullName()) || request.getFullName().isBlank())
            throw new Exception("El campo fullName no puede ser nulo.");
        if (Objects.isNull(request.getPhone()) || request.getPhone().isBlank())
            throw new Exception("El campo phone no puede ser nulo.");
        if (Objects.isNull(request.getEmail()) || request.getEmail().isBlank())
            throw new Exception("El campo email no puede ser nulo.");
        if (request.getDocumentTypeId() == null || request.getDocumentTypeId() <= 0)
            throw new Exception("El campo documentTypeId debe ser mayor a 0.");
        if (Objects.isNull(request.getDocumentNumber()) || request.getDocumentNumber().isBlank())
            throw new Exception("El campo documentNumber no puede ser nulo.");
        if (Objects.isNull(request.getBirthDate()) || request.getBirthDate().isBlank())
            throw new Exception("El campo birthDate no puede ser nulo.");
        if (Objects.isNull(request.getCountry()) || request.getCountry().isBlank())
            throw new Exception("El campo country no puede ser nulo.");
        if (Objects.isNull(request.getAddress()) || request.getAddress().isBlank())
            throw new Exception("El campo address no puede ser nulo.");

        DocumentType documentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new Exception("DocumentType no encontrado con id: " + request.getDocumentTypeId()));
        if (userRepository.existsByEmail(request.getEmail()))
            throw new Exception("El email ya existe.");
        if (userRepository.existsByDocumentNumberAndDocumentTypeId(request.getDocumentNumber(), request.getDocumentTypeId()))
            throw new Exception("El documento ya existe.");

        User user = UserMapper.createUserRequestToUser(request, documentType);
        return UserMapper.modelToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Integer id, CreateUserRequest request) throws Exception {
        if (id == null || id <= 0) throw new Exception("Debe ingresar un id válido.");
        if (Objects.isNull(request)) throw new Exception("El objeto request no puede ser nulo.");
        if (Objects.isNull(request.getFullName()) || request.getFullName().isBlank())
            throw new Exception("El campo fullName no puede ser nulo.");
        if (Objects.isNull(request.getPhone()) || request.getPhone().isBlank())
            throw new Exception("El campo phone no puede ser nulo.");
        if (Objects.isNull(request.getEmail()) || request.getEmail().isBlank())
            throw new Exception("El campo email no puede ser nulo.");
        if (request.getDocumentTypeId() == null || request.getDocumentTypeId() <= 0)
            throw new Exception("El campo documentTypeId debe ser mayor a 0.");
        if (Objects.isNull(request.getDocumentNumber()) || request.getDocumentNumber().isBlank())
            throw new Exception("El campo documentNumber no puede ser nulo.");
        if (Objects.isNull(request.getBirthDate()) || request.getBirthDate().isBlank())
            throw new Exception("El campo birthDate no puede ser nulo.");
        if (Objects.isNull(request.getCountry()) || request.getCountry().isBlank())
            throw new Exception("El campo country no puede ser nulo.");
        if (Objects.isNull(request.getAddress()) || request.getAddress().isBlank())
            throw new Exception("El campo address no puede ser nulo.");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario no encontrado con id: " + id));

        DocumentType documentType = documentTypeRepository.findById(request.getDocumentTypeId())
                .orElseThrow(() -> new Exception("DocumentType no encontrado con id: " + request.getDocumentTypeId()));

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setDocumentType(documentType);
        user.setDocumentNumber(request.getDocumentNumber());
        user.setBirthDate(LocalDate.parse(request.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        user.setCountry(request.getCountry());
        user.setAddress(request.getAddress());
        user.setUpdatedAt(OffsetDateTime.now());

        return UserMapper.modelToUserResponse(userRepository.save(user));
    }
}