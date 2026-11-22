# Vampirism: Witch v0.2.1

Minecraft 26.1.2, NeoForge, FactionAPI, Vampirism.

## Changes
- Witch faction screen displays **Lord Level N** when a lord rank is assigned, rather than always **Level 14**.
- Unique purple **normal level 1–14 HUD** positioned centrally between the health and hunger bars.
- HUD shows only ordinary levels, **not lord ranks**.
- Witch lord rank names intentionally left unassigned for future updates.

## Testing
```mcfunction
/factions faction set vampirismwitch:witch level 1
/factions faction set vampirismwitch:witch level 14
/factions faction set vampirismwitch:witch lord 5
/factions faction get @s
```

With the level 5 lord rank, press P: the faction menu should say `Lord Level 5`; after closing it, the HUD should still show normal level `14`.

## Download
GitHub > Actions > latest green Build Vampirism Witch JAR > Artifacts > Vampirism-Witch-0.2.1. Unzip, install the .jar into mods and delete previous Witch JAR.
