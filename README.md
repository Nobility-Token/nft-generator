
![Logo](https://nobilitytoken.com/logo.png)


# [Nobility NFT Generator](https://nobilitytoken.com)

This project is used to generate our custom Knights NFT Collection


## Authors

- [@Patrity (Tony Costanzo)](https://www.github.com/Patrity)


## Run Locally

### Requirements
- Java JDK 16+
- Gradle 7+

### Clone the project

```bash
  git clone https://github.com/Nobility-Token/nft-generator.git
```

### Open the project

- Open your favorite Java IDE such as IntelliJ
- Open the project using the `build.gradle` file

### File structure
Place your images in the following folder structure
```
Root Directory
|-output
|- -images
|- -metadata
|-images
|- -1.backgrounds
|- -2.glow
|- -3.sword
|- -4.akimbo
|- -5.body
|- -6.breastplate
|- -7.pauldron
|- -8.helmet
|- -9.front
```
The only files that are important in this file structure are `output` and `images`.
If you change the amount of components in the images directory, be sure to change the field `totalLayers` in `Generator.java`
