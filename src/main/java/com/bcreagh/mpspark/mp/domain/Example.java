package com.bcreagh.mpspark.mp.domain;

public class Example {
    private String name = "";
    private boolean hasOwnRoute = false;
    private String route = "";
    private Object input = "";

    public Example() {
    }

    public Example(Example example) {
        this.name = example.name;
        this.hasOwnRoute = example.hasOwnRoute;
        this.route = example.route;
        this.input = example.input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getHasOwnRoute() {
        return hasOwnRoute;
    }

    public void setHasOwnRoute(boolean hasOwnRoute) {
        this.hasOwnRoute = hasOwnRoute;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }
}
