package com.example.mrec.mrec_demo.enums;

public enum SourceEnum {
    crm("CRM"),
    diy("DIY"),
    difm("DIFM");

    private String sourceName;

    SourceEnum(String name){
        sourceName = name;
    }

    public String getSourceName(){
        return sourceName;
    }
}
