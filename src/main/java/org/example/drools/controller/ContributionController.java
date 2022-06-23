package org.example.drools.controller;

import org.example.drools.contribution.ContributionRequest;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContributionController {
    private final KieContainer kieContainer;

    public ContributionController(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @PostMapping("/contribution")
    private ContributionRequest getContribution(@RequestBody ContributionRequest request) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(request);
        kieSession.fireAllRules();
        kieSession.dispose();
        return request;
    }
}