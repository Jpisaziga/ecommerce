package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CategoryResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCategoryRequest;
import co.edu.usbcali.ecommerceusb.model.Category;

import java.time.OffsetDateTime;
import java.util.List;

public class CategoryMapper {

    public static CategoryResponse modelToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(null) // el modelo no tiene este campo
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .createdAt(category.getCreatedAt())
                .build();
    }

    public static List<CategoryResponse> modelToCategoryResponseList(List<Category> list) {
        return list.stream()
                .map(CategoryMapper::modelToCategoryResponse)
                .toList();
    }

    public static Category createCategoryRequestToCategory(
            CreateCategoryRequest request,
            Category parent
    ) {
        return Category.builder()
                .name(request.getName())
                // description no existe en el modelo, se ignora
                .parent(parent)
                .createdAt(OffsetDateTime.now())
                .build();
    }
}