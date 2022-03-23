package com.patrity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class Attribute {

    @JsonProperty("trait_type")
    public AttributeType type;
    @JsonProperty("value")
    public String name;
    @JsonIgnore
    public File file;
    public Rarity rarity;

    // Default no-arg constructor required for jackson
    public Attribute() {
    }
}
