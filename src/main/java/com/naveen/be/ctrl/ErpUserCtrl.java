package com.naveen.be.ctrl;

import com.naveen.be.dto.LoginRes;
import com.naveen.be.model.ErpUser;
import com.naveen.be.srv.ErpUserSrv;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class ErpUserCtrl {
    private final ErpUserSrv erpUserSrv;
    @PostMapping("/register")
    public ResponseEntity<ErpUser> registerUser(@RequestBody ErpUser erpUser) {
        return ResponseEntity.ofNullable(erpUserSrv.saveUser(erpUser));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody ErpUser erpUser) {
        return ResponseEntity.ofNullable(erpUserSrv.verify(erpUser));
    }
}
