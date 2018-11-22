package com.example.demo.model;

public class Author {
    private Long authorId;

    private String name;

    private String authorUrl;

    private Integer postNum;

    private Integer postAge;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getPostAge() {
        return postAge;
    }

    public void setPostAge(Integer postAge) {
        this.postAge = postAge;
    }
}