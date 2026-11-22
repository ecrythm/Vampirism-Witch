# Vampirism: Witch v0.2.0

Requires **Minecraft 26.1.2**, **NeoForge**, **Java 25**, **Vampirism**, **FactionAPI 1.0.0-beta.2+**.

This version registers `vampirismwitch:witch` through the real FactionAPI. Witch has levels **1–14** and lord levels **1–5**. Creative-mode tab with pixel hat from v0.1.0 remains.

## Commands (cheats / OP level 2)

Join Witch at level 1:
```
/factions faction set vampirismwitch:witch level 1
```

Set Witch level 14:
```
/factions faction set vampirismwitch:witch level 14
```

Set Witch lord level 5 (automatically grants max regular level):
```
/factions faction set vampirismwitch:witch lord 5
```

Read current faction:
```
/factions faction get @s
```

Test any intermediate level with `/factions faction set vampirismwitch:witch level 7` and any lord level with `/factions faction set vampirismwitch:witch lord 3`.

Commands are provided by FactionAPI. Potion recipes, skills, combat bonuses, and other Witch features are **not included** in this release.

## Download build

GitHub > Actions > latest successful `Build Vampirism Witch JAR` > Artifacts > `Vampirism-Witch-0.2.0`.
Unzip and copy the included `.jar` to `mods`. Remove v0.1.0 from mods before testing.
