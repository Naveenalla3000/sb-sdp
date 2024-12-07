package com.naveen.be.srv;

import com.naveen.be.dto.LoginRes;
import com.naveen.be.model.ErpUser;
import com.naveen.be.model.Role;
import com.naveen.be.repo.ErpUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ErpUserSrv {
    private final AuthenticationManager authenticationManager;
    private final ErpUserRepo erpUserRepo;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public ErpUser saveUser(ErpUser erpUser){
        erpUser.setPassword(bCryptPasswordEncoder.encode(erpUser.getPassword()));
        erpUser.setRole(Role.STUDENT);
        return erpUserRepo.save(erpUser);
    }
    public LoginRes verify(ErpUser erpUser){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(erpUser.getUsername(), erpUser.getPassword()));
        if(authentication.isAuthenticated()) {
            System.out.println("User is authenticated");
            String token = jwtService.generateToken(erpUser.getUsername());
            ErpUser fk = erpUserRepo.findByUsername(erpUser.getUsername());
            LoginRes t1 = new LoginRes();
            t1.setUser(fk);
            t1.setToken(token);
            return t1;
        }
        return null;
    }
}
