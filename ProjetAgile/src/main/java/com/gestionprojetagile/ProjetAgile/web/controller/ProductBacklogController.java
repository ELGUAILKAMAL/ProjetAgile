package com.gestionprojetagile.ProjetAgile.web.controller;

import com.gestionprojetagile.ProjetAgile.web.DTO.ProductBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IProductBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-backlogs")
public class ProductBacklogController {

    private IProductBacklog productBacklogService;
    public ProductBacklogController(IProductBacklog productBacklogService){
        this.productBacklogService=productBacklogService;
    }
    @PostMapping
    public ResponseEntity<ProductBacklogDTO> createProductBacklog(@RequestBody ProductBacklogDTO productBacklogDTO) {
        ProductBacklog productBacklog = convertToEntity(productBacklogDTO);
        ProductBacklog createdBacklog = productBacklogService.createProductBacklog(productBacklog);
        return new ResponseEntity<>(convertToDTO(createdBacklog), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklogDTO> getProductBacklogById(@PathVariable Long id) {
        ProductBacklog productBacklog = productBacklogService.getProductBacklogById(id);
        return ResponseEntity.ok(convertToDTO(productBacklog));
    }

    @GetMapping
    public ResponseEntity<List<ProductBacklogDTO>> getAllProductBacklogs() {
        List<ProductBacklog> backlogs = productBacklogService.getAllProductBacklogs();
        List<ProductBacklogDTO> backlogDTOs = backlogs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(backlogDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklogDTO> updateProductBacklog(
            @PathVariable Long id,
            @RequestBody ProductBacklogDTO productBacklogDTO) {
        ProductBacklog productBacklog = convertToEntity(productBacklogDTO);
        ProductBacklog updatedBacklog = productBacklogService.updateProductBacklog(id, productBacklog);
        return ResponseEntity.ok(convertToDTO(updatedBacklog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductBacklog(@PathVariable Long id) {
        productBacklogService.deleteProductBacklog(id);
        return ResponseEntity.noContent().build();
    }

    private ProductBacklog convertToEntity(ProductBacklogDTO dto) {
        ProductBacklog productBacklog = new ProductBacklog();
        productBacklog.setId(dto.getId());
        productBacklog.setName(dto.getName());
        return productBacklog;
    }

    private ProductBacklogDTO convertToDTO(ProductBacklog productBacklog) {
        return new ProductBacklogDTO(
                productBacklog.getId(),
                productBacklog.getName()
        );
    }
}