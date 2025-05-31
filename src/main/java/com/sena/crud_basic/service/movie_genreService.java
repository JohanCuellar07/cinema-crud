package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.movie_genreDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.movie_genre;
import com.sena.crud_basic.repository.Imovie_genre;

@Service
public class movie_genreService {
    @Autowired
    private Imovie_genre data;

    public List<movie_genre> findAll(){
        return data.findAll();
    }

    public Optional<movie_genre> findById(int id){
        return data.findById(id);
    }

    public List<movie_genre> findAllByGenreId(int genre_id) {
        return data.findAllByGenreId(genre_id);
    }

    public responseDTO deleteByMovieId(int movie_id) {
        List<movie_genre> movieGenres = data.findAllByMovieId(movie_id);
        
        if (movieGenres.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "No genres found for the given movie"
            );
            return respuesta;
        }
    
        data.deleteByMovieId(movie_id);
    
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "All genre relations for the movie were deleted successfully"
        );
        return respuesta;
    }

    public responseDTO deleteByGenreId(int genre_id) {
        List<movie_genre> movieGenres = data.findAllByGenreId(genre_id);
        
        if (movieGenres.isEmpty()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "No movies found for the given genre"
            );
            return respuesta;
        }
    
        data.deleteByGenreId(genre_id);
    
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "All movie relations for the genre were deleted successfully"
        );
        return respuesta;
    }

    public responseDTO save(movie_genreDTO movie_genreDTO){
        if (movie_genreDTO.getMovie_id() == null || movie_genreDTO.getGenre_id() == null) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "The movie_id or genre_id cannot be null"
            );
            return respuesta;
        }
        movie_genre movie_genreRegister = converToModel(movie_genreDTO);
        data.save(movie_genreRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was succesfully saved"
        );
        return respuesta;
       
    }

    public responseDTO updateMovie_Genre(int id, movie_genreDTO dto){
        Optional<movie_genre> movie_genreOpt = data.findById(id);
        if (!movie_genreOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "The register does not exist"
            );
            return respuesta;
        }
        movie_genre existingMovieGenre = movie_genreOpt.get();
        existingMovieGenre.setMovie_id(dto.getMovie_id());
        existingMovieGenre.setGenre_id(dto.getGenre_id());
        data.save(existingMovieGenre);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Was successfully updated"
        );
        return respuesta;
    }

    public movie_genreDTO converToDTO(movie_genre movie_genre){
        movie_genreDTO movie_genreDTO = new movie_genreDTO(
            movie_genre.getMovie_id(),
            movie_genre.getGenre_id()
        );
        return movie_genreDTO;
    }

    public movie_genre converToModel(movie_genreDTO movie_genreDTO){
        movie_genre movie_genre = new movie_genre(
            0,
            movie_genreDTO.getMovie_id(),
            movie_genreDTO.getGenre_id()
        );
        return movie_genre;
    }
}
