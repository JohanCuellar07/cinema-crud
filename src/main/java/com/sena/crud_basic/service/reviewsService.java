package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.reviewsDTO;
import com.sena.crud_basic.model.reviews;
import com.sena.crud_basic.repository.Ireviews;

@Service
public class reviewsService {
    
    @Autowired
    private Ireviews data;

    public List<reviews> findAll(){
        //return data.findAll();
        return data.getListReviewsActive();
    }

    public List<reviews> getListReviewsForName(String filter){
        return data.getListReviewsForName(filter);
    }
    /* 
    public List<reviews> getListReviewsForMovie(String filter){
        return data.getListReviewsForMovie(filter);
    }

    public List<reviews> getListReviewsForUser(String filter){
        return data.getListReviewsForUser(filter);
    }

    public List<reviews> getListReviewsForRating(String filter){
        return data.getListReviewsForRating(filter);
    }
    */

    public Optional<reviews> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteReview(int id){
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully deleted"
        );
        return respuesta;
    }

    public responseDTO save(reviewsDTO reviewsDTO){
        if (reviewsDTO.getMovie_id() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The movie_id cannot be empty"
            );
            return respuesta;
        }
        if (reviewsDTO.getNameReviewer().length() < 1 || reviewsDTO.getNameReviewer().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The name cannot be empty or exceed 50 characters"
            );
            return respuesta;
        }
        if (reviewsDTO.getRating() == 0) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The rating cannot be empty"
            );
            return respuesta;
        }
        if (reviewsDTO.getComment().length() > 150) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The comment cannot exceed 150 characters"
            );
            return respuesta;
        }
        reviews reviewsRegister = converToModel(reviewsDTO);
        data.save(reviewsRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully saved"
        );
        return respuesta;
    }

    public responseDTO updateReviews(int id, reviewsDTO dto){
        Optional<reviews> reviewsOpt = data.findById(id);
        if (!reviewsOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        reviews existingReviews = reviewsOpt.get();
        existingReviews.setMovie_id(dto.getMovie_id());
        existingReviews.setNameReviewer(dto.getNameReviewer());
        existingReviews.setRating(dto.getRating());
        existingReviews.setComment(dto.getComment());
        data.save(existingReviews);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
    }

    public reviewsDTO converToDTO(reviews reviews){
        reviewsDTO reviewsDTO = new reviewsDTO(
            reviews.getMovie_id(), 
            reviews.getNameReviewer(), 
            reviews.getRating(), 
            reviews.getComment()
        );
        return reviewsDTO;
    }
    

    public reviews converToModel(reviewsDTO reviewsDTO){
        reviews reviews = new reviews(
            0,
            reviewsDTO.getMovie_id(),
            reviewsDTO.getNameReviewer(),
            reviewsDTO.getRating(),
            reviewsDTO.getComment(),
            true
        );
        return reviews;
    }
}
