package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.Person;

public class SendMoneyStepDefinitions {

    Person danny, larry;

    @Before//Before hooks run before the first step in each scenario
    public void setUp() {
        //We can use this to set up test data for each scenario
        danny = new Person("Danny");
        larry = new Person("Larry");
    }

    @Given("Danny has {double} euros on his Revolut account")
    public void dannyHasEurosOnHisRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }

    @And("Larry has {double} euros on his Revolut account")
    public void larryHasEurosOnHisRevolutAccount(double startingBalance) {
        larry.setAccountBalance(startingBalance);
    }

    @When("Danny sends {double} euros to Larry")
    public void dannySendsEurosToLarry(double sendAmount) {
        danny.getAccount("EUR").addFunds(-sendAmount);
        larry.getAccount("EUR").addFunds(sendAmount);
    }

    @Then("Danny will have {double} euros on his Revolut account")
    public void dannyWillHaveEurosOnHisRevolutAccount(double newBalance) {
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(newBalance, actualResult,0);
        System.out.println("Danny's new final balance is: " + actualResult);
    }

    @And("Larry will have {double} euros on this Revolut account")
    public void larryWillHaveEurosOnThisRevolutAccount(double newBalance) {
        //Act
        double actualResult = larry.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(newBalance, actualResult,0);
        System.out.println("Larry's new final balance is: " + actualResult);
    }
}
