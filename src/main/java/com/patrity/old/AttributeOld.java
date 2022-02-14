package com.patrity.old;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.patrity.AttributeType;
import com.patrity.Rarity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class AttributeOld {

    public AttributeType type;
    public String name;
    @JsonIgnore
    public File file;
    public Rarity rarity;

    // Default no-arg constructor required for jackson
    public AttributeOld() {
    }
}
