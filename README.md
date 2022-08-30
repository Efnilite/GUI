## GUI / by Efy
[![](https://jitpack.io/v/Efnilite/GUI.svg)](https://jitpack.io/#Efnilite/GUI)

**Import with Maven**

Click the jitpack icon to view the latest compiled version.
```maven
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```maven
<dependency>
    <groupId>com.github.Efnilite</groupId>
    <artifactId>GUI</artifactId>
    <version>version</version>
</dependency>
```

**Setup**

Before you can activate or create a menu, you have to set the plugin instance this library will use. It's recommended to do this in one of the first lines of your `#onEnable()` or `#onLoad()` method.

```java
Menu.init(plugin);
```

**Examples**

Generic menu
```java
new Menu(3, "Blocks")
    .fillBackground(Material.GRAY_STAINED_GLASS_PANE) // fill unused slots with a specific item
    .animation(new SplitMiddleOutAnimation()) // the opening animation
    .distributeRowsEvenly() // distribute items evenly per row

    .item(9, new Item(Material.STONE, "Stone"))
    .item(10, new Item(Material.GRASS, "Grass")
        .glowing() // set item to glow
        .click(event -> { // what happens when this item gets clicked
            event.getPlayer().kickPlayer("Grass is illegal! :(");
        }))

    .open(player);
```

Paged menu
```java
PagedMenu menu = new PagedMenu(3, "Blocks")
    .displayRows(0, 1) // set the first two rows to display the provided items
    .addToDisplay(List.of(new Item(Material.GRASS, "Grass"), new Item(Material.STONE, "Stone"))); // add items to the display

menu
    .prevPage(18, new Item(Material.RED_DYE, "Previous page")
        .click(event -> menu.page(1))) // go to next page if the player clicks on this item
    .nextPage(26, new Item(Material.LIME_DYE, "Next page")
        .click(event -> menu.page(1))) // go to previous page if the player clicks

    .fillBackground(Material.WHITE_STAINED_GLASS_PANE)
    .animation(new RandomAnimation())
    .open(player);
```
