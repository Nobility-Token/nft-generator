package com.patrity;

import lombok.AllArgsConstructor;
/*
"./images/1.backgrounds",
                "./images/2.glow",
                "./images/3.sword",
                "./images/4.akimbo",
                "./images/5.body",
                "./images/6.breastplate",
                "./images/7.pauldron",
                "./images/8.helmet",
                "./images/9.front");
 */
@AllArgsConstructor
public enum AttributeType {
    BACKGROUND("./images/1.backgrounds"),
    GLOW("./images/2.glow"),
    SWORD("./images/3.sword"),
    AKIMBO("./images/4.akimbo"),
    BODY("./images/5.body"),
    BREASTPLATE("./images/6.breastplate"),
    PAULDRON("./images/7.pauldron"),
    HELMET("./images/8.helmet"),
    FRONT("./images/9.front");

    public String folderPath;
}
