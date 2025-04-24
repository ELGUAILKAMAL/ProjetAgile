package com.gestionprojetagile.ProjetAgile.web.repositories;


import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdBacklogRepo extends JpaRepository <ProductBacklog,Long> {



}
