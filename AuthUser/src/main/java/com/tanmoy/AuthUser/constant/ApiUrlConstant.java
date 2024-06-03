package com.tanmoy.AuthUser.constant;

public class ApiUrlConstant {

    public static final String API_VERSION = "/api/v1";
    public static final String USER = API_VERSION + "/user";


    public static final String ID = "/{id}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update" + ID;
    public static final String DELETE = "/delete" + ID;
    public static final String FIND = "/find"+ ID;

    private ApiUrlConstant () {}
}
