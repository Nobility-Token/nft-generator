package com.patrity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Knight {
    public int id;
    public String image;
    public List<Attribute> attributes;

    // Default no-arg constructor required for jackson
    public Knight() {
    }
}
