package com.app;

public class Report {
    final Facts facts;
    final ConditionalAction conditionalAction;
    final boolean isPositive;

    public Report(Facts facts, ConditionalAction conditionalAction, boolean isPositive) {
        this.facts = facts;
        this.conditionalAction = conditionalAction;
        this.isPositive = isPositive;
    }

    public ConditionalAction getConditionalAction() {
        return this.conditionalAction;
    }

    public Facts getFacts() {
        return this.facts;
    }

    public boolean isPositive() {
        return this.isPositive;
    }

    @Override
    public String toString() {
        return "Report{" +
                "facts=" + facts +
                ", conditionalAction=" + conditionalAction +
                ", isPositive=" + isPositive +
                '}';
    }
}
