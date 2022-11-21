package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.FeedbackDTO;

import java.util.List;

public interface IFeedbackService {

    /**
     * lista os feedbacks pelo ID
     * @author Amanda Lobo
     * @param productId -> Long
     * @return retorna uma lista de feedbacks
     * @Exception ListIsEmptyException
     */
    List<FeedbackDTO> getFeedbackByProductId(Long productId);
}
