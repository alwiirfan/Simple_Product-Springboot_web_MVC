package com.alwi.irfani.dto;

public class SearchFormData {
    private String keyword;


    public SearchFormData() {
    }

    public SearchFormData(String keyword) {
        this.keyword = keyword;
    }


    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
