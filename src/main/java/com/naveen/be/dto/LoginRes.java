package com.naveen.be.dto;

import com.naveen.be.model.ErpUser;
import lombok.Data;

@Data
public class LoginRes {
    private ErpUser user;
    private String token;
}
