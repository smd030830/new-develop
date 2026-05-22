package excoding;

import lombok.Getter;

import javax.naming.InsufficientResourcesException;

public class Account {
    private long balance;

    public Account(){}

    public long getBalance(){
        return balance;
    }
    public void deposit(int money){
        balance += money;
    }
    public void withdraw(int money) throws InsufficientResourcesException {
        if(balance<money){
            throw new InsufficientResourcesException("잔고 부족: " + (money-balance));
        }
    }
}
