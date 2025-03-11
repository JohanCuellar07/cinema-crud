package com.sena.crud_basic.DTO;

public class moviesDTO {
    
    private String title;

    private int time_min;

    private java.sql.Date launch_year;

    public moviesDTO(String title, int time_min, java.sql.Date launch_year) {
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

    public java.sql.Date getLaunch_year(){
        return launch_year;
    }

    public void setLaunch_year(java.sql.Date launch_year){
        this.launch_year = launch_year;
    }
}
