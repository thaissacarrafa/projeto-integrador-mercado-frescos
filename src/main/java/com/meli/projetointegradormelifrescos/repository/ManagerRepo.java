/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ManagerRepo extends JpaRepository<Manager, Long> {

    Manager findManagerById(Long managerid);

}
