## GUI / by Efy

**Import with Maven**

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
    <version>master-aa71371c6f-1</version>
</dependency>
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
```ja
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