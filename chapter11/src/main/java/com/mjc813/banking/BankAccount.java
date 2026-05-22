package com.mjc813.banking;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BankAccount {
    private final String accountNumber;
    private final String name;
    private int money = 0;
}
