package com.app;

public class DefaultRule implements Rule{
    private final Condition condition;
    private final Action action;

    public DefaultRule(Condition condition, Action action) {
        this.condition = condition;
        this.action = action;
    }

    @Override
    public void Perform(Facts facts) {
        if (condition.evaluate(facts)) action.execute(facts);
    }
}
