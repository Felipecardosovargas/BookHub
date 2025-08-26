package br.com.bookhub.service;

import br.com.bookhub.entity.Provider;
import br.com.bookhub.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    public void deleteById(Long id) {
        providerRepository.deleteById(id);
    }
}
