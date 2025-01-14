package com.mavericks.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {

    @Column (name = "customer_id")
    private Long customerId;

    @Id
    @Column (name = "account_number")
    private Long accountNumber;

    @Column (name = "account_type")
    private String accountType;

    @Column (name = "branch_address")
    private String branchAddress;

    @Column (name = "status")
    private int status;

}
