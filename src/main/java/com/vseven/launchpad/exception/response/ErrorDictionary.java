package com.vseven.launchpad.exception.response;

public enum ErrorDictionary {
    NF_001("QuickLink not found");

    private String errorMessage;

    ErrorDictionary(String errorId) {this.errorMessage = errorId;}

    public String getErrorMessage() {return errorMessage;}

}
