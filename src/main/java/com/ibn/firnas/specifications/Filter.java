package com.ibn.firnas.specifications;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@Builder
public class Filter {
    private String field;
    private QueryOperator queryOperator;
    private String value;
    private List<String> values;
}
