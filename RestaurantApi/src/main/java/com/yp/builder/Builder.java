package com.yp.builder;

public abstract class Builder {
    public Builder() {

    }
    public abstract Builder withId(Long id);
    public abstract Builder withName(String name);

}
