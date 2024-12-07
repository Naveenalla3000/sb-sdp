package com.naveen.be.srv;

import com.naveen.be.model.ErpUser;
import com.naveen.be.model.UserPrinciples;
import com.naveen.be.repo.ErpUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final ErpUserRepo erpUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ErpUser erpUser = erpUserRepo.findByUsername(username);
        if(erpUser == null) {
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrinciples(erpUser);
    }
}
