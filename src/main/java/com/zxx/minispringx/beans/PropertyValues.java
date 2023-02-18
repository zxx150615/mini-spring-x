package com.zxx.minispringx.beans;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        List<PropertyValue> values = this.propertyValueList.stream().filter(x -> x.getName().equals(propertyName)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(values)) {
            return values.get(0);
        }
        return null;
    }
}
