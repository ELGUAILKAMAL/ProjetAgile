package com.gestionprojetagile.ProjetAgile.web.service.ImplService;


import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import com.gestionprojetagile.ProjetAgile.web.repositories.ProdBacklogRepo;
import com.gestionprojetagile.ProjetAgile.web.service.InterfaceService.IProductBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBacklogImpl implements IProductBacklog {

        @Autowired
        private ProdBacklogRepo productBacklogRepo;

        //@Autowired
       // private UserStoryRepo userStoryRepo;

        @Override
        public ProductBacklog createProductBacklog(ProductBacklog productBacklog) {
            try {
                return productBacklogRepo.save(productBacklog);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la récupération des productBaklog: " + e.getMessage());
            }
        }

        @Override
        public ProductBacklog getProductBacklogById(Long id) {
            Optional<ProductBacklog> productBacklogOptional = productBacklogRepo.findById(id);
            return productBacklogOptional.orElseThrow(() -> new RuntimeException("Product Backlog non trouvé avec l'ID : " + id));
        }

        @Override
        public List<ProductBacklog> getAllProductBacklogs() {
            return productBacklogRepo.findAll();
        }

        @Override
        public ProductBacklog updateProductBacklog(Long id, ProductBacklog productBacklog) {
            ProductBacklog existingProductBacklog = getProductBacklogById(id);
            return productBacklogRepo.save(existingProductBacklog);
        }

        @Override
        public void deleteProductBacklog(Long id) {
            productBacklogRepo.deleteById(id);
        }


}
