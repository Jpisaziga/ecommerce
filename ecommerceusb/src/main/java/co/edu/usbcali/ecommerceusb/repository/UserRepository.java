package co.edu.usbcali.ecommerceusb.repository;
import co.edu.usbcali.ecommerceusb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    Boolean existsByDocumentNumberAndDocumentTypeId(String documentNumber, Integer documentTypeId);
}
