package edu.psuti.pp.practice.bank;

import edu.psuti.pp.practice.bank.exceptions.InsufficientFundsException;
import edu.psuti.pp.practice.bank.main.*;

import java.util.ArrayList;
import java.util.List;

public class BankTest {

    public static void main(String[] args) {

        // Методы тестирования классов

        testAccountNumberGenerator();

        testCreditAccount();

        testDebitAccount();

        testInsufficientFundsException();

        testNaturalClient();
    }

    private static void testAccountNumberGenerator() {

        System.out.println(">>>>>>>>>>Test Class AccountNumberGenerator");
        AccountNumberGenerator a = new AccountNumberGenerator();
        System.out.print("Начальное значение : ");
        System.out.println(AccountNumberGenerator.getCurrent());
        System.out.println("Новое значение :");
        System.out.println(AccountNumberGenerator.getNext());
        System.out.println(AccountNumberGenerator.getCurrent());
    }

    private static void testCreditAccount() {

        System.out.println(">>>>>>>>>> Test Class CreditAccount");
        // Тест конструкторов
        CreditAccount creditAccount_01 = new CreditAccount(
                AccountNumberGenerator.getCurrent()
        );
        CreditAccount creditAccount_02 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                50_00.0
        );
        CreditAccount creditAccount_03 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                10_000.0,
                100.0
        );
        CreditAccount creditAccount_04 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                25_000.0,
                150.0,
                Currency.USD
        );
        CreditAccount creditAccount_05 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                45_000.0,
                80.0,
                Currency.RUB,
                5.5,
                100_000.0
        );
        // Тест геттеров-сеттеров
        System.out.println(">Тест геттеров-сеттеров<<<");
        System.out.println(creditAccount_01.getPercentRate());
        System.out.println(creditAccount_01.getCreditCardLimit());
        System.out.println(creditAccount_01.getAssessedPercent());
        System.out.println(creditAccount_01.getAssessedCommission());
        System.out.println(creditAccount_01.getBalance());
        System.out.println(creditAccount_01.getCommission());
        System.out.println(creditAccount_01.getId());
        System.out.println(creditAccount_01.getCurrentCurrency());

        creditAccount_01.setPercentRate(5.5);
        creditAccount_01.setCreditCardLimit(150_000.0);
        creditAccount_01.setAssessedPercent(312.2);
        creditAccount_01.setAssessedCommission(500.0);
        creditAccount_01.setCommission(33.2);
        creditAccount_01.setId(AccountNumberGenerator.getNext());
        creditAccount_01.setCurrentCurrency(Currency.RUB);

        System.out.println(">Поменяли значения");
        System.out.println(creditAccount_01.getPercentRate());
        System.out.println(creditAccount_01.getCreditCardLimit());
        System.out.println(creditAccount_01.getAssessedPercent());
        System.out.println(creditAccount_01.getAssessedCommission());
        System.out.println(creditAccount_01.getBalance());
        System.out.println(creditAccount_01.getCommission());
        System.out.println(creditAccount_01.getId());
        System.out.println(creditAccount_01.getCurrentCurrency());

        System.out.println(">>setCurrentCurrency");
        creditAccount_01.setCurrentCurrency(Currency.JOY);
        System.out.println(creditAccount_01.getCommission());
        System.out.println(creditAccount_01.getBalance());
        System.out.println(">Поменяли валюту ");
        System.out.println(creditAccount_01.getCurrentCurrency());
        System.out.println(creditAccount_01.getCommission());
        System.out.println(creditAccount_01.getBalance());


        System.out.println(">>Тест методов");
        System.out.println(">accrualPercent метод начисления процентов");
        System.out.println("Начисленные проценты: " + creditAccount_05.getAssessedPercent());
        creditAccount_05.accrualPercent();
        System.out.println("Новые проценты: " + creditAccount_05.getAssessedPercent());

        CreditAccount creditAccount_06 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                45_000.0,
                80.0,
                Currency.RUB,
                5.5,
                100_000.0
        );

        System.out.println(">Метод comissFromBalance, вычитающий комиссию из остатка c обработкой ошибки");
        System.out.println(creditAccount_06.getCommission());
        System.out.println(creditAccount_06.getBalance());
        try {
            creditAccount_06.commissionFromBalance();
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        System.out.println(creditAccount_06.getAssessedCommission());
        System.out.println(creditAccount_06.getBalance());

        System.out.println(">addToBalance, метод пополнения счёта");
        System.out.println("Остаток " + creditAccount_06.getBalance());
        creditAccount_06.setAssessedCommission(10_000.0);
        System.out.println("Начисленная комиссия " + creditAccount_06.getAssessedCommission());
        System.out.println("Начисленные проценты " + creditAccount_06.getAssessedPercent());
        try {
            creditAccount_06.addToBalance(15_000.0);
        } catch (InsufficientFundsException e) {
            e.getMessage();

        }

        System.out.println("Остаток после " + creditAccount_06.getBalance());
        System.out.println("Начисленная комиссия " + creditAccount_06.getAssessedCommission());
        System.out.println("Начисленные проценты " + creditAccount_06.getAssessedPercent());
        System.out.println();
        System.out.println(">метод списывания суммы со счёта deductFromTheBalance");
        System.out.println(creditAccount_06.getBalance());
        try {
            creditAccount_06.deductFromTheBalance(10_000.0);
        } catch (InsufficientFundsException e) {
            e.getMessage();
        }
        System.out.println(creditAccount_06.getBalance());
        System.out.println(">Equals");
        CreditAccount creditAccount_07 = new CreditAccount(
                6,
                45_000.0,
                80.0,
                Currency.RUB,
                5.5,
                100_000.0
        );
        Account creditAccount_08 = new CreditAccount(
                6,
                45_000.0,
                80.0,
                Currency.RUB,
                5.5,
                100_000.0
        );
        System.out.println(creditAccount_08.equals(creditAccount_07));

        System.out.println(">Hashcode");
        System.out.println(creditAccount_07.hashCode() + " " + creditAccount_08.hashCode());
        System.out.println(creditAccount_07.hashCode() == creditAccount_08.hashCode());
        System.out.println(">ToString");
        System.out.println(creditAccount_08);

    }

    private static void testDebitAccount() {

        System.out.println(">> Class DebitAccount");
        System.out.println("Class DebitAccount");
        // Тест конструкторов
        AccountNumberGenerator generator = new AccountNumberGenerator();

        DebitAccount DebitAccountAccount_01 = new DebitAccount(
                AccountNumberGenerator.getCurrent()
        );
        DebitAccount creditAccount_02 = new DebitAccount(
                AccountNumberGenerator.getNext(),
                50_00.0
        );
        DebitAccount creditAccount_03 = new DebitAccount(
                AccountNumberGenerator.getNext(),
                10_000.0,
                100.0
        );
        DebitAccount creditAccount_04 = new DebitAccount(
                AccountNumberGenerator.getNext(),
                25_000.0,
                150.0,
                Currency.USD
        );
        System.out.println(">>>Тест методов");
        DebitAccount creditAccount_05 = new DebitAccount(
                5,
                25_000.0,
                150.0,
                Currency.USD
        );
        Account creditAccount_06 = new DebitAccount(
                5,
                25_000.0,
                150.0,
                Currency.USD
        );
        System.out.println(">Equals");
        System.out.println(creditAccount_06.equals(creditAccount_05));
        System.out.println(">HashCode");
        System.out.println("одинаковые хешкоды");
        System.out.println(creditAccount_06.hashCode() + " " + creditAccount_05.hashCode());

        System.out.println(">toString()");
        System.out.println(creditAccount_06);
    }

    private static void testInsufficientFundsException() {

        System.out.println(">>>>>>>>>>Class InsufficientFundsException");
        CreditAccount creditAccount_06 = new CreditAccount(
                AccountNumberGenerator.getNext(),
                45_000.0,
                80.0,
                Currency.RUB,
                5.5,
                100.0
        );
        try {
            creditAccount_06.addToBalance(999_999);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }

    private static void testNaturalClient() {

        System.out.println(">>>>>>>>>>Class NaturalClient");
        // Тест конструкторов
        List<Account> C = new ArrayList<>();
        C.add(new CreditAccount(
                111,
                45_000.0,
                80.0,
                Currency.RUB,
                5.5,
                100_000.0
        ));
        C.add(new CreditAccount(
                222,
                5_000.0,
                42.0,
                Currency.USD,
                7.5,
                100_000.0
        ));
        C.add(new DebitAccount(
                333,
                30_000.0,
                150.0,
                Currency.USD
        ));
        C.add(new DebitAccount(
                444,
                25_000.0,
                20.0,
                Currency.JOY
        ));
        NaturalClient c1 = new NaturalClient("иван", "тванов", 1552, 34);
        NaturalClient c2 = new NaturalClient(
                "Александр",
                "Петров",
                1242,
                65,
                C
        );
        System.out.println(">>>Тест методов");
        // getLinkToAccount, возвращающий ссылку на счёт по его уникальному номеру
        System.out.println(">метод, возвращающий ссылку на счёт по его уникальному номеру");
        System.out.println(c2.getLinkToAccount(333).getId());
        // getAccountList, возвращающий массив всех счетов
        System.out.println(">метод, возвращающий массив всех счетов");
        List<Account> test12312 = c2.getAccountList();
        for (Account i : test12312) {
            System.out.println(i.getBalance());
        }
        // возвращающий список (класс ArrayList<Account>) счетов дебетовых карт
        System.out.println(">возвращающий список счетов дебетовых карт");
        List<Account> test21212 = c2.getDebitList();
        for (Account f : test21212) {
            System.out.println(f.getId());
        }
        // метод, возвращающий список (класс ArrayList<Account>) счетов кредитных карт
        System.out.println(">метод, возвращающий список счетов кредитных карт");
        List<Account> test422 = c2.getCreditList();
        for (Account f : test422) {
            System.out.println(f.getId());
        }
        // метод, возвращающий суммарный остаток на всех дебетовых счетах
        System.out.println(">метод, возвращающий суммарный остаток на всех дебетовых счетах");
        System.out.println(c2.getDebitBalance());
        // метод, возвращающий сумму долга клиента (сумма начисленных процентов
        // и комиссионных по всем кредитным счетам, а также отрицательный остаток по картам)
        // и метод добавления счёта
        System.out.println(">метод, возвращающий сумму долга клиента + метод добавления счёта");

        c2.addAccount(new CreditAccount(
                213,
                -99_000,
                42.0,
                Currency.USD,
                7.5,
                100_000.0
        ));
        System.out.println(c2.getDuty());
        // метод, возвращающий список (класс ArrayList<Account>) счетов с
        //   положительным остатком на счете
        System.out.println(">метод, возвращающий список  счетов с положительным остатком на счете");
        List<Account> test223 = c2.getPositiveSummaryBalance();
        for (Account i : test223) {
            System.out.println(i.getBalance());
        }
        // метод удаления счета по его номеру
        System.out.println(">метод удаления счета по его номеру");
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " ");
        }
        System.out.println();
        c2.deleteAccount(213);
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " ");
        }
        System.out.println();
        // метод списывания средств со счета (принимает номер счета и размер суммы)
        System.out.println(">метод списывания средств со счета ");
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " " + i.getBalance());
        }
        System.out.println();
        c2.decreaseAccount(444, 10_000);
        for (Account i : c2.getAccountList()) {
            System.out.print(i.getId() + " " + i.getBalance());
        }
        System.out.println();
        // метод, возвращающий суммарный остаток на всех счетах
        System.out.println(">метод, возвращающий суммарный остаток на всех счетах");
        System.out.println(c2.getSummaryBalance());

        // tostring
        System.out.println(">ToString");
        System.out.println(c2);

        // метод уменьшения размера остатка счета
        // (принимает ссылку на счет и размер суммы)
        System.out.println(">метод уменьшения размера остатка счета");
        System.out.println(c2.getLinkToAccount(444).getBalance());
        c2.decBalance(c2.getLinkToAccount(444), 12.2);
        System.out.println(c2.getLinkToAccount(444).getBalance());

        //  метод увеличения размера остатка счета (принимает ссылку на
        // счет и размер суммы)
        System.out.println(">метод увеличения размера остатка счета");
        System.out.println(c2.getLinkToAccount(444).getBalance());
        c2.incBalance(c2.getLinkToAccount(444), 1322.2);
        System.out.println(c2.getLinkToAccount(444).getBalance());
    }
}
