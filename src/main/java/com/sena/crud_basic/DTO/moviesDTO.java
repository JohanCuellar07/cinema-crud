package com.sena.crud_basic.DTO;

import java.time.LocalDate;

public class moviesDTO {
    
    private String title;

    private int time_min;

    private LocalDate launch_year;

    public moviesDTO(String title, int time_min, LocalDate launch_year) {
        this.title = title;
        this.time_min = time_min;
        this.launch_year = launch_year;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getTime_min(){
        return time_min;
    }

    public void setTime_min(int time_min){
        this.time_min = time_min;
    }

    public LocalDate getLaunch_year(){
        return launch_year;
    }

    public void setLaunch_year(LocalDate launch_year){
        this.launch_year = launch_year;
    }
}
