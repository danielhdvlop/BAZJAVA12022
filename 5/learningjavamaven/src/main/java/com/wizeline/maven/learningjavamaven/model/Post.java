package com.wizeline.maven.learningjavamaven.model;

public class Post {
    private String userId;
    private Long id;
    private String title;
    private String body;

    public String getUserId(){
        return userId;
    }
    public Long getId(){
        return id;
    };
    public String getTitle(){
        return title;
    };
    public String getBody(){
        return body;
    };
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setId(Long id){
        this.id = id;
    };
    public void setTitle(String title){
        this.title = title;
    };
    public void setBody(String body){
        this.body = body;
    };
}
