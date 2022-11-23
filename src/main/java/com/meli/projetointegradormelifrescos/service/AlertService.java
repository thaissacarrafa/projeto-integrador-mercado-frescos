/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.Batch;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class AlertService implements IAlertService{


    public Boolean startAlertForProduct(Batch batch, Category category) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        LocalDate dueDate = batch.getDueDate();
        LocalDate todayDate = LocalDate.now();
        int cutoffDate = category.getStartAlert();

        Date past = new Date(batch.getDueDate().getYear(), batch.getDueDate().getMonthValue(), batch.getDueDate().getDayOfMonth());
        Date today = new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        int daysToDue = Days.daysBetween(new DateTime(today), new DateTime(past)).getDays();

        if (daysToDue <= cutoffDate) {
          return true;
        }

        return false;

    }
}


