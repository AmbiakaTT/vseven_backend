package com.vseven.launchpad.exception.response;

public enum ErrorDictionary {
    NF_001("QuickLink not found"),
    NF_002("User not found"),
    NF_003("Link not found in QuickLinks");

    private String errorMessage;

    ErrorDictionary(String errorId) {this.errorMessage = errorId;}

    public String getErrorMessage() {return errorMessage;}

}
