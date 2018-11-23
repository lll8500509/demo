package com.example.demo.model;

import java.util.Date;

public class Author {
    private Long authorId;

    private String name;

    private String authorUrl;

    private String authorUrlMd5;

    private Integer postNum;

    private Integer postAge;

    private Date updateTime;

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

    public String getAuthorUrlMd5() {
        return authorUrlMd5;
    }

    public void setAuthorUrlMd5(String authorUrlMd5) {
        this.authorUrlMd5 = authorUrlMd5;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}