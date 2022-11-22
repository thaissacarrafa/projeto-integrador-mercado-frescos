package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ManagerRepo extends JpaRepository<Manager, Long> {

    Manager findManagerById(Long managerid);

}
