package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.FeedbackDTO;
import com.meli.projetointegradormelifrescos.exception.InvalidEvaluationException;
import com.meli.projetointegradormelifrescos.repository.AnnouncementRepo;
import com.meli.projetointegradormelifrescos.repository.FeedbackRepo;
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

        Double newAverageEvaluation = announcement.getFeedbacks().stream().reduce(0D,(subtotal, element) ->
                subtotal + element.getEvaluation(), Double::sum);
        feedback.setAnnouncement(announcement);

        Feedback newFeedback = feedbackRepo.save(feedback);

        announcement.setAvarageEvaluation(newAverageEvaluation/announcement.getFeedbacks().size());
        announcementRepo.save(announcement);

        return new FeedbackDTO(newFeedback.getId(),newFeedback.getAnnouncement().getName(),newFeedback.getComment(),
                newFeedback.getEvaluation());
    }

    /**
     * método que atualiza o feedback de um produto e calcula a sua média de avaliação
     * @author Amanda Lobo
     * @param feedbackId -> Long
     * @param feedback -> Feedback
     * @Return new FeedbackDTO -> retorna o feedback atualizado
     */
    @Transactional
    public FeedbackDTO updateFeedback(Long feedbackId, Feedback feedback){
        Feedback feedbacks = feedbackRepo.findById(feedbackId).orElseThrow(() -> new NotFoundException("feedback not found"));
        Announcement announcement = feedbacks.getAnnouncement();

        feedbacks.setId(feedbackId);
        feedbacks.setComment(feedback.getComment());
        feedbacks.setEvaluation(feedback.getEvaluation());

        Feedback update = feedbackRepo.save(feedbacks);

        Double newAverageEvaluation = announcement.getFeedbacks().stream()
                .reduce(0D,(subtotal,element)-> subtotal+element.getEvaluation(), Double::sum);

        announcement.setAvarageEvaluation(newAverageEvaluation/announcement.getFeedbacks().size());
        announcementRepo.save(announcement);

        return new FeedbackDTO(update.getId(),update.getAnnouncement().getName(),
                update.getComment(), update.getEvaluation());
    }

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
        return announcement.getFeedbacks().stream().map(a -> new FeedbackDTO(a.getId(), a.getAnnouncement().getName(),
                a.getComment(), a.getEvaluation())).collect(Collectors.toList());
    }

    /**
     * listar os produtos filtrados por nota (de 0 a 5)
     *
     * @param filtredEvaluation -> Double
     * @return AnnouncementDTO
     * @author Amanda Lobo
     * @Excpetion ListIsEmptyException
     * @Excpetion InvalidEvaluationException
     */
    public List<AnnoucementDTO> listProductsByMinimumRating(Double filtredEvaluation) {
        List<Announcement> announcementList = feedbackRepo.finAllByEvaluation(filtredEvaluation);

        if (announcementList.isEmpty()) {
            throw new ListIsEmptyException("product without minimum rating");
        }

        if (filtredEvaluation > 5.0 || filtredEvaluation < 0.0) {
            throw new InvalidEvaluationException("products rated only between 0 and 5");
        }

        return announcementList.stream()
                .map(announcement -> new AnnoucementDTO(announcement.getId(), announcement.getName(), announcement.getPrice(),
                        announcement.getCategory(), announcement.getAvarageEvaluation()))
                .collect(Collectors.toList());
    }
}
