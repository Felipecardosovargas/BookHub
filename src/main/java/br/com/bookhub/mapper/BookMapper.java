package br.com.bookhub.mapper;

import br.com.bookhub.controller.reponse.BookResponse;
import br.com.bookhub.controller.reponse.CategoryResponse;
import br.com.bookhub.controller.reponse.ProviderResponse;
import br.com.bookhub.controller.request.BookRequest;
import br.com.bookhub.entity.Book;
import br.com.bookhub.entity.Category;
import br.com.bookhub.entity.Provider;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BookMapper {

    public static Book toBook(BookRequest bookRequest) {
        List<Category> categories = bookRequest.categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Provider> providers = bookRequest.providers()
                .stream()
                .map(providerId -> Provider.builder().id(providerId).build())
                .toList();


        return Book.builder()
                .title(bookRequest.title())
                .description(bookRequest.description())
                .publishDate(bookRequest.publishDate())
                .rating(bookRequest.rating())
                .categories(categories)
                .providers(providers)
                .build();
    }

    public static BookResponse toBookResponse(Book book) {

        List<CategoryResponse> categories = book.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<ProviderResponse> providers = book.getProviders()
                .stream()
                .map(ProviderMapper::toProviderResponse)
                .toList();

        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .publishDate(book.getPublishDate())
                .rating(book.getRating())
                .categories(categories)
                .providers(providers)
                .build();
    }
}
