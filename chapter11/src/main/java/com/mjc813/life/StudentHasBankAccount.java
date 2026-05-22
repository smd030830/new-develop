package com.mjc813.life;

import com.mjc813.banking.BankAccount;
import com.mjc813.student.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StudentHasBankAccount {
    private final Student student;
    private final BankAccount bankAccount;

    public void income(int money) {
        //입금
        int current = bankAccount.getMoney();
        bankAccount.setMoney(current + money);
    }

    public void outcome(int money) {
        //출금
        int current = bankAccount.getMoney();
        bankAccount.setMoney(current - money);
    }

    public int getCurrentMoney() {
        //잔액
        return bankAccount.getMoney();
    }
}
