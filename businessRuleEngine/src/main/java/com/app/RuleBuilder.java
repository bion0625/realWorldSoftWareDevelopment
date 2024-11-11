package com.app;

public class RuleBuilder {
    private Condition condition;

    private RuleBuilder(final Condition condition) {
        this.condition = condition;
    }

    static public RuleBuilder when(final Condition condition) {
        return new RuleBuilder(condition);
    }

    public Rule then(final Action action) {
        return new DefaultRule(condition, action);
    }
}
