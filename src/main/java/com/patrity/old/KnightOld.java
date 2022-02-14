package com.patrity.old;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class KnightOld {
    public int id;
    public String image;
    public List<AttributeOld> attributes;

    // Default no-arg constructor required for jackson
    public KnightOld() {
    }
}
