package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.FeedbackDTO;
import com.meli.projetointegradormelifrescos.model.Feedback;
import com.meli.projetointegradormelifrescos.service.AnnoucementService;
import com.meli.projetointegradormelifrescos.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    /**
     * Criar um novo feedback do produto
     * @author Amanda Lobo
     * @param productId -> Long
     * @param feedback -> Feedback
     * @return 201 - Created (feedback criado com sucesso)
     */
    @PostMapping("/create-feedback/{productId}")
    public ResponseEntity<FeedbackDTO> createNewFeedback(@PathVariable Long productId, @RequestBody Feedback feedback){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFeedbackProduct(productId,feedback));
    }

    /**
     * Atualizar um feedback pelo ID do Feedback
     * @author Amanda Lobo
     * @param feedbackId -> Long
     * @param feedback -> Feedback
     * @return 201 - Created (feedback atualizado com sucesso)
     */
    @PutMapping("/update-feedback/{feedbackId}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long feedbackId, @RequestBody Feedback feedback){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateFeedback(feedbackId, feedback));
    }

    /**
     * Listar todos os feedbacks de um produto pelo ID do produto
     * @author Amanda Lobo
     * @param productId -> Long
     * @return List<FeedbackDTO> -> lista de feedbacks
     */
    @GetMapping("/feedback-product/{productId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByProductId(@PathVariable Long productId){
        List<FeedbackDTO> feedbackProduct = service.getFeedbackByProductId(productId);
        return ResponseEntity.ok().body(feedbackProduct);
    }

    /**
     * Listar todos os feedbacks de uma determinada avaliação inserida (de 0 a 5)
     * @author Amanda Lobo
     * @param evaluation -> Double
     * @return List<AnnouncementDTO> -> Lista de productos com determinada avaliação
     */
    @GetMapping("/evaluation-feedback")
    public ResponseEntity<List<AnnoucementDTO>> getEvaluation(@RequestParam(required = false, name = "evaluation") Double evaluation){
        List<AnnoucementDTO> evaluationFeedback = service.listProductsByMinimumRating(evaluation);
        return ResponseEntity.ok().body(evaluationFeedback);
    }


}
