package com.huntercodexs.unittestsdemo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModel {

    protected String name;
    protected String document;
    protected String mail;

    public boolean insertUser(UserModel userModel) {
        return !userModel.getName().equals("") && !userModel.getDocument().equals("") && !userModel.getMail().equals("");
    }

}
