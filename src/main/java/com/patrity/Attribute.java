package com.patrity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.File;

@Data
public class Attribute {

    public final AttributeType type;
    public final String name;
    @JsonIgnore
    public final File file;
    public final Rarity rarity;
}
