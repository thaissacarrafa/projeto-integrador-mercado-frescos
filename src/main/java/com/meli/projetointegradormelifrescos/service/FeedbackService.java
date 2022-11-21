package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.FeedbackDTO;
import com.meli.projetointegradormelifrescos.enums.repository.AnnouncementRepo;
import com.meli.projetointegradormelifrescos.enums.repository.FeedbackRepo;
import com.meli.projetointegradormelifrescos.exception.ListIsEmptyException;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService implements IFeedbackService{

    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private FeedbackRepo feedbackRepo;

    /**
     * cria o feedback do produto e calcula a média da avaliação
     * @author Amanda Lobo
     * @param productId -> Long
     * @param feedback -> Long
     * @return retorna um FeedbackDTO
     */
    @Transactional
    public FeedbackDTO createFeedbackProduct(Long productId, Feedback feedback) {
        Announcement announcement = announcementRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("product not found"));
        announcement.getFeedbacks().add(feedback);
        Double newAverageRating = announcement.getFeedbacks().stream().reduce(0D,(subtotal, element) ->
                subtotal + element.getEvaluation(), Double::sum);
        feedback.setAnnouncement(announcement);
        Feedback newFeedback = feedbackRepo.save(feedback);
        announcement.setAvarageEvaluation(newAverageRating/announcement.getFeedbacks().size());
        announcementRepo.save(announcement);
        return new FeedbackDTO(newFeedback.getId(),newFeedback.getAnnouncement().getName(),newFeedback.getComment(),
                newFeedback.getEvaluation());
    }

    //atualizar o feedback e calcular a média da avaliação
//    public FeedbackDTO updateFeedback(Long feedbackId, Feedback feedback){
//        Feedback feedback = feedbackRepo.findById(feedback).orElseThrow(() -> new NotFoundException("feedback not found"));
//        Announcement announcement = feedback.getAnnouncement();
//
//        feedback.setId(feedbackId);
//        feedback.setComment(feedback.getComment());
//        feedback.setEvaluation(feedback.getEvaluation());
//    }


    /**
     * lista os feedbacks pelo ID
     * @author Amanda Lobo
     * @param productId -> Long
     * @return retorna uma lista de feedbacks
     * @Exception ListIsEmptyException
     */
    @Override
    public List<FeedbackDTO> getFeedbackByProductId(Long productId) {
        Announcement announcement = announcementRepo.findById(productId).orElseThrow(() -> new NotFoundException("product not found"));

        if (announcement.getFeedbacks().isEmpty()){
            throw new ListIsEmptyException("no feedback found");
        }
        return announcement.getFeedbacks().stream().map(a -> new FeedbackDTO(a.getId(),a.getComment(),
                        a.getAnnouncement().getName(),a.getEvaluation())).collect(Collectors.toList());
    }

}
