package ru.nsu.yakovleva.dsl;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseSettings extends Configuration {
    String branch;
    boolean disableLongTests;
}
