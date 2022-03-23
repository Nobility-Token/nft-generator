package com.patrity.model;

import com.patrity.model.Attribute;
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
