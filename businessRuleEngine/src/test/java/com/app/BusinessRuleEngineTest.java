package com.app;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BusinessRuleEngineTest {

    @Test
    public void shouldHaveNoRulesInitially() {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(new Facts());

        Assert.assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddTwoActions() {
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(new Facts());

        businessRuleEngine.addAction(facts -> {});
        businessRuleEngine.addAction(facts -> {});

        Assert.assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldPerformAnActionWithFacts() {
        final Action mockAction = facts -> {
            Stage dealStage = Stage.valueOf(facts.getFact("stage"));
            double amount = Double.parseDouble(facts.getFact("amount"));

            var forecatedAmount = amount * switch (dealStage) {
                case LEAD -> 0.2;
                case EVALUATING -> 0.5;
                case INTERESTED -> 0.8;
                case CLOSED -> 1;
            };
            facts.addFact("forecatedAmount", String.valueOf(forecatedAmount));
        };
        final Facts mockFacts = new Facts();
        mockFacts.addFact("stage", "EVALUATING");
        mockFacts.addFact("amount", "3.3");
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        Assert.assertEquals(mockFacts.getFact("forecatedAmount"), String.valueOf(3.3 * 0.5));
    }
}