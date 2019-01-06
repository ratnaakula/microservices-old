package com.example.demo.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path="/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"));
        return getMappingJacksonValue(list);
    }

    @GetMapping(path="/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList() {
        List<SomeBean> list = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
                new SomeBean("Value21", "Value22", "Value23"));
        return getMappingJacksonValue(list);
    }

    private MappingJacksonValue getMappingJacksonValue(List<SomeBean> list) {
        SimpleBeanPropertyFilter someFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filter = new SimpleFilterProvider().addFilter("SomeBeanFilter", someFilter);
        MappingJacksonValue value = new MappingJacksonValue(list);
        value.setFilters(filter);
        return value;
    }
}
