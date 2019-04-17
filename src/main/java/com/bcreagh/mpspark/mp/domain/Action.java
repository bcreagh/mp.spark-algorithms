package com.bcreagh.mpspark.mp.domain;

import com.bcreagh.mpspark.mp.domain.requestdetails.RequestDetails;

import java.util.ArrayList;

public class Action {
    private String name = "";
    private String description = "";
    private String route = "";
    private String instructions = "";
    private Readme readme = new Readme();
    private ArrayList<Example> examples = new ArrayList<>();
    private RequestDetails requestDetails = new RequestDetails();

    public Action() {
    }

    public Action(Action action) {
        this.name = action.name;
        this.description = action.description;
        this.route = action.route;
        this.instructions = action.instructions;
        this.readme = new Readme(action.readme);
        for(Example exampleToClone : action.examples) {
            Example example = new Example(exampleToClone);
            this.examples.add(example);
        }
        this.requestDetails = new RequestDetails(action.requestDetails);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Readme getReadme() {
        return readme;
    }

    public void setReadme(Readme readme) {
        this.readme = readme;
    }

    public ArrayList<Example> getExamples() {
        return examples;
    }

    public void setExamples(ArrayList<Example> examples) {
        this.examples = examples;
    }

    public RequestDetails getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(RequestDetails requestDetails) {
        this.requestDetails = requestDetails;
    }
}
