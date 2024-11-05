package org.sid.bank_account_service.entities;

import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.PostMapping;

@Projection(types = BankAccount.class)
public interface AccountProjection {
    public String getId();
    public AccountType getType();
    public Double getBalance();

}
