package com.gestionprojetagile.ProjetAgile.web.repositories;

import com.gestionprojetagile.ProjetAgile.web.Enities.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpicRepo extends JpaRepository <Epic, Long> {
}
