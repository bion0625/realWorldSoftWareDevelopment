package com.app;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngine {

    private final List<Rule> rules;
    private final Facts facts;

    public BusinessRuleEngine(Facts facts) {
        this.facts = facts;
        this.rules = new ArrayList<>();
    }

    public void addRule(final Rule rule) {
        rules.add(rule);
    }

    public void run() {
        this.rules.forEach(action -> action.Perform(this.facts));
    }
}
