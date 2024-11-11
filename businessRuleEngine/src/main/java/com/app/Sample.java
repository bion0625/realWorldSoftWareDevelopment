package com.app;

public class Sample {
    public static void main(String[] args) {
        final Condition condition = facts -> "CEO".equals(facts.getFact("jobTitle"));
        final Action action = facts -> {
            String name = facts.getFact("name");
            System.out.println("Relevant customer!!!: " + name);
        };

        DefaultRule defaultRule = new DefaultRule(condition, action);

        Rule rule = RuleBuilder
                .when(facts -> "CEO".equals(facts.getFact("jobTitle")))
                .then(facts -> {
                    String name = facts.getFact("name");
                    System.out.println("Relevant customer!!!: " + name);
                });
    }
}
