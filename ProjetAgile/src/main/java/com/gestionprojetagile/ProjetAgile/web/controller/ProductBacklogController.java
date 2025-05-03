package com.gestionprojetagile.ProjetAgile.web.controller;

import com.gestionprojetagile.ProjetAgile.web.DTO.ProductBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import com.gestionprojetagile.ProjetAgile.web.mapping.ProductBacklogMapper;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IProductBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product-backlogs")
public class ProductBacklogController {
    private ProductBacklogMapper productBacklogMapper;
    private IProductBacklog productBacklogService;
    public ProductBacklogController(IProductBacklog productBacklogService, ProductBacklogMapper productBacklogMapper){
        this.productBacklogService=productBacklogService;
        this.productBacklogMapper=productBacklogMapper;
    }
    @PostMapping("/createProductBacklog")
    public ResponseEntity<ProductBacklogDTO> createProductBacklog(@RequestBody ProductBacklogDTO productBacklogDTO) {
        ProductBacklog productBacklog = productBacklogMapper.productBlDtoToProductBl(productBacklogDTO);
        ProductBacklog createdBacklog = productBacklogService.createProductBacklog(productBacklog);
        return new ResponseEntity<>(productBacklogMapper.productBlToProductBlDto(createdBacklog), HttpStatus.CREATED);
    }

    @GetMapping("/getProductBacklog/{id}")
    public ResponseEntity<ProductBacklogDTO> getProductBacklogById(@PathVariable Long id) {
        ProductBacklog productBacklog = productBacklogService.getProductBacklogById(id);
        return ResponseEntity.ok(productBacklogMapper.productBlToProductBlDto(productBacklog));
    }

    @GetMapping("/getProductBacklogs")
    public ResponseEntity<List<ProductBacklogDTO>> getAllProductBacklogs() {
        List<ProductBacklog> backlogs = productBacklogService.getAllProductBacklogs();
        List<ProductBacklogDTO> backlogDTOs = new ArrayList<>();
        for(ProductBacklog productBacklog : backlogs){
            backlogDTOs.add(productBacklogMapper.productBlToProductBlDto(productBacklog));
        }
        return ResponseEntity.ok(backlogDTOs);
    }

    @PutMapping("/modifyProductBacklog/{id}")
    public ResponseEntity<ProductBacklogDTO> updateProductBacklog(
            @PathVariable Long id,
            @RequestBody ProductBacklogDTO productBacklogDTO) {
        ProductBacklog productBacklog = productBacklogMapper.productBlDtoToProductBl(productBacklogDTO);
        ProductBacklog updatedBacklog = productBacklogService.updateProductBacklog(id, productBacklog);
        return ResponseEntity.ok(productBacklogMapper.productBlToProductBlDto(updatedBacklog));
    }

    @DeleteMapping("/deleteProductBacklog/{id}")
    public ResponseEntity<Void> deleteProductBacklog(@PathVariable Long id) {
        productBacklogService.deleteProductBacklog(id);
        return ResponseEntity.noContent().build();
    }


}