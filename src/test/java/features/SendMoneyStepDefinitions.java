package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Person;

import java.util.HashMap;

public class SendMoneyStepDefinitions {

    HashMap<String, Person> people;
    Person danny, larry;

    @Before//Before hooks run before the first step in each scenario
    public void setUp() {
        //We can use this to set up test data for each scenario
        people = new HashMap<>();
        people.put("Danny", new Person("Danny"));
        people.put("Larry", new Person("Larry"));
    }

    @Given("{word} has {double} euros on his Revolut account")
    public void personHasEurosOnHisRevolutAccount(String person, double startingBalance) {
        people.get(person).setAccountBalance(startingBalance);
    }

    @When("{word} sends {double} euros to {word}")
    public void personSendsEurosToAnotherPerson(String person, double sendAmount, String anotherPerson) {
        people.get(person).getAccount("EUR").addFunds(-sendAmount);
        people.get(anotherPerson).getAccount("EUR").addFunds(sendAmount);
    }

    @Then("{word} will have {double} euros on his Revolut account")
    public void personWillHaveEurosOnHisRevolutAccount(String person, double newBalance) {
        //Act
        double actualResult = people.get(person).getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(newBalance, actualResult,0);
        System.out.println(person + "'s new final balance is: " + actualResult);
    }
}
