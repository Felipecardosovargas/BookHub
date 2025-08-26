package br.com.bookhub.controller;

import br.com.bookhub.controller.reponse.ProviderResponse;
import br.com.bookhub.controller.request.ProviderRequest;
import br.com.bookhub.entity.Provider;
import br.com.bookhub.mapper.ProviderMapper;
import br.com.bookhub.service.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookhub/provider")
@RequiredArgsConstructor
public class ProviderController implements ProviderDocs {

    private final ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderResponse>> getAllProviders() {
        List<Provider> providers = providerService.findAll();
        List<ProviderResponse> list = providers.stream()
                .map(ProviderMapper::toProviderResponse)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ProviderResponse> saveProvider(@Valid @RequestBody ProviderRequest request) {
        Provider newProvider = ProviderMapper.toProvider(request);
        Provider savedProvider = providerService.save(newProvider);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProviderMapper.toProviderResponse(savedProvider));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getProviderById(@PathVariable Long id) {
        return providerService.findById(id)
                .map(provider -> ResponseEntity.ok(ProviderMapper.toProviderResponse(provider)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProviderById(@PathVariable Long id) {
        providerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}