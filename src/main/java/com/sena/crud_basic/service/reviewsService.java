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
        return data.findAll();
    }

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
        if (reviewsDTO.getUser_id() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The user_id cannot be empty"
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

    public reviewsDTO converToDTO(reviews reviews){
        reviewsDTO reviewsDTO = new reviewsDTO(
            reviews.getMovie_id(), 
            reviews.getUser_id(), 
            reviews.getRating(), 
            reviews.getComment()
        );
        return reviewsDTO;
    }
    

    public reviews converToModel(reviewsDTO reviewsDTO){
        reviews reviews = new reviews(
            0,
            reviewsDTO.getMovie_id(),
            reviewsDTO.getUser_id(),
            reviewsDTO.getRating(),
            reviewsDTO.getComment()
        );
        return reviews;
    }
}
