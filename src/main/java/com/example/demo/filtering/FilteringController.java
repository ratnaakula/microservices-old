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
        SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");
        SimpleBeanPropertyFilter someFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filter = new SimpleFilterProvider().addFilter("SomeBeanFilter", someFilter);
        MappingJacksonValue value = new MappingJacksonValue(someBean);
        value.setFilters(filter);
        return value;
    }

    @GetMapping(path="/filtering-list")
    public List<SomeBean> retrieveSomeBeanList() {
        return Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
                new SomeBean("Value21", "Value22", "Value23"));
    }
}
