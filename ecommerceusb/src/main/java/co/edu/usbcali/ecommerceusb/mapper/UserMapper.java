package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.UserResponse;
import co.edu.usbcali.ecommerceusb.model.DocumentType;
import co.edu.usbcali.ecommerceusb.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    // User (entity - model) -> UserResponse(dto)
    public static UserResponse modelToUserResponse(User user) {
        DocumentType documentType = user.getDocumentType();

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                //uso de un if ternario
                .documentTypeId(
                        user.getDocumentType() != null ? user.getDocumentType().getId() : null)
                //Uso de un if ternario
                .documentTypeName(
                        user.getDocumentType() != null ? user.getDocumentType().getName() : null)
                .documentNumber(user.getDocumentNumber())
                .build();
    }


    public static List<UserResponse> modelToUserResponseList(List<User> users) {
        return users.stream()
                .map(UserMapper::modelToUserResponse)
                .toList();
    }
}