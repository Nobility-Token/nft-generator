package com.patrity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class Attribute {

    public AttributeType type;
    public String name;
    @JsonIgnore
    public File file;
    public Rarity rarity;

    // Default no-arg constructor required for jackson
    public Attribute() {
    }
}
