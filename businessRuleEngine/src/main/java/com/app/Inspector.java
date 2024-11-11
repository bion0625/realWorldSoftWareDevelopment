package com.app;

import java.util.ArrayList;
import java.util.List;

public class Inspector {
    private final List<ConditionalAction> conditionalActionList;

    public Inspector(List<ConditionalAction> conditionalActionList) {
        this.conditionalActionList = conditionalActionList;
    }

    public Inspector(ConditionalAction conditionalAction) {
        this.conditionalActionList = new ArrayList<>();
        conditionalActionList.add(conditionalAction);
    }

    public List<Report> inspect(final Facts facts) {
        final List<Report> reportList = new ArrayList<>();
        for (ConditionalAction conditionalAction : conditionalActionList) {
            boolean conditionResult = conditionalAction.evaluate(facts);
            reportList.add(new Report(facts, conditionalAction, conditionResult));
        }
        return reportList;
    }
}
