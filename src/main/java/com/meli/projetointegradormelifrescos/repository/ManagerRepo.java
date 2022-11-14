package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.model.Manager;
import com.meli.projetointegradormelifrescos.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepo extends JpaRepository<Manager, Long> {

    Manager findManagerById(Long managerid);

}
