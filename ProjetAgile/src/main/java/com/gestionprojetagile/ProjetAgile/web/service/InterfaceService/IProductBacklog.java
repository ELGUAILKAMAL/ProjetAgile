package com.gestionprojetagile.ProjetAgile.web.service.InterfaceService;



import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;

import java.util.List;

public interface IProductBacklog {

    //Gestion des ProductBacklog
    ProductBacklog createProductBacklog(ProductBacklog productBacklog);
    ProductBacklog getProductBacklogById(Long id);
    List<ProductBacklog> getAllProductBacklogs();
    ProductBacklog updateProductBacklog(Long id, ProductBacklog productBacklog);
    void deleteProductBacklog(Long id);














}
