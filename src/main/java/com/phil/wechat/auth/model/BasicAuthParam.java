package com.phil.wechat.auth.model;

import java.io.Serializable;
import java.util.Map;


public abstract class BasicAuthParam implements Serializable {

    private static final long serialVersionUID = 3375127810872852675L;

    public abstract Map<String, String> getParams();
}
