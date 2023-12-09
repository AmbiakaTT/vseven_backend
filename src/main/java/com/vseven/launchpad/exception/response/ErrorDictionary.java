package com.vseven.launchpad.exception.response;

public enum ErrorDictionary {
    NF_001("QuickLink not found"),
    NF_002("User not found"),
    NF_003("Link not found in QuickLinks"),
    NF_004("User already bookmarked QuickLink"),

    NF_005("Link not found");
    private String errorMessage;

    ErrorDictionary(String errorId) {this.errorMessage = errorId;}

    public String getErrorMessage() {return errorMessage;}

}
