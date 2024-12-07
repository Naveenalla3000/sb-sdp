package com.naveen.be.repo;

import com.naveen.be.model.ErpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ErpUserRepo extends JpaRepository<ErpUser, Serializable> {
    ErpUser findByUsername(String username);
}
