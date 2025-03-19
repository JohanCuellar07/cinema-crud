package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.reviewsDTO;
import com.sena.crud_basic.model.reviews;
import com.sena.crud_basic.repository.Ireviews;

@Service
public class reviewsService {
    
    @Autowired
    private Ireviews data;

    public void save(reviewsDTO reviewsDTO){
        reviews reviewsRegister = converToModel(reviewsDTO);
        data.save(reviewsRegister);
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
