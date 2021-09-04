package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.PaymentService;
import revolut.Person;

public class StepDefinitions {

    private double topUpAmount;
    PaymentService topUpMethod;
    Person danny;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to set up test data for each scenario
        danny = new Person("Danny");
    }
    @Given("Danny has {double} euro in his euro Revolut account")
    @Given("Danny has a starting balance of {double}")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        danny.getAccount("EUR").addFunds(topUpAmount);
    }

    @When("Danny now tops up by {double}")
    public void danny_now_tops_up_by(double topUpAmount) {
        // Write code here that turns the phrase above into concrete actions
        this.topUpAmount = topUpAmount;
        danny.getAccount("EUR").addFunds(topUpAmount);
    }

    @When("The payment is {acceptedOrRejected}")
    public void thePaymentIsAcceptedOrRejected(boolean acceptedOrRejected) {
        // Write code here that turns the phrase above into concrete actions
        if (!acceptedOrRejected) {
            this.topUpAmount = 0;
        }
        danny.getAccount("EUR").addFunds(topUpAmount);
    }

    @Then("The new balance of his euro account should now be {double}")
    @Then("The balance in his euro account should be {double}")
    public void the_new_balance_of_his_euro_account_should_now_be(double newBalance) {
        // Write code here that turns the phrase above into concrete actions
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(newBalance, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }
}
