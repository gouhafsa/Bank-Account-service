package org.sid.bank_account_service.entities;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@NoArgsConstructor @AllArgsConstructor @Data @Builder

public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<BankAccount> bankAccountList;
}
