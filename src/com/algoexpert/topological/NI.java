package com.algoexpert.topological;

import java.util.List;

class NI implements NestedInteger {

    private Integer value;
    private List<NestedInteger> list;

    public NI(Integer value) {
        this.value = value;
    }

    public NI(List<NestedInteger> list) {
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}
