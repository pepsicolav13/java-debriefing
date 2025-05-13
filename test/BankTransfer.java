class BankTransfer implements PaymentStrategy {
    private String accountNumber;

    public BankTransfer(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via Bank Transfer: " + accountNumber);
    }
}
