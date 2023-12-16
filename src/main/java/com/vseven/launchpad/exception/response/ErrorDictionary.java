package com.vseven.launchpad.exception.response;

public enum ErrorDictionary {
    NF_001("QuickLink not found"),
    NF_002("User not found"),
    NF_003("Link not found in QuickLink"),
    NF_004("Link already bookmarked in QuickLink"),
    NF_005("Link not found"),
    NF_006("Section not found");

    private String errorMessage;

    ErrorDictionary(String errorId) {this.errorMessage = errorId;}

    public String getErrorMessage() {return errorMessage;}

}
