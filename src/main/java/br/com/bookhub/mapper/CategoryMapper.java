package br.com.bookhub.mapper;

import br.com.bookhub.controller.reponse.CategoryResponse;
import br.com.bookhub.controller.request.CategoryRequest;
import br.com.bookhub.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest request){
        return Category
                .builder()
                .name(request.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
