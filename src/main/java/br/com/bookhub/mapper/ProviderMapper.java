package br.com.bookhub.mapper;

import br.com.bookhub.controller.reponse.ProviderResponse;
import br.com.bookhub.controller.request.ProviderRequest;
import br.com.bookhub.entity.Provider;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProviderMapper {

    public static Provider toProvider(ProviderRequest request){
        return Provider
                .builder()
                .name(request.name())
                .build();
    }

    public static ProviderResponse toProviderResponse(Provider provider){
        return ProviderResponse
                .builder()
                .id(provider.getId())
                .name(provider.getName())
                .build();
    }
}
