# Vampirism: Witch v0.3.2

Minecraft 26.1.2 / NeoForge / Vampirism / FactionAPI.

## Changes
- Normal Witch HUD uses clean Minecraft-style purple text with a single dark shadow. Removed the heavy four-sided pale outline.
- No Witch level HUD in Creative or Spectator mode.
- Archmage HUD displays **Arch** instead of normal level 14 (in Survival/Adventure).
- Archmage commands are now added to FactionAPI's `/factions faction set vampirismwitch:witch` command tree.
- Lord titles have not been customized.

## Commands (cheats/OP)
```mcfunction
/factions faction set vampirismwitch:witch level 7
/factions faction set vampirismwitch:witch lord 5
/factions faction set vampirismwitch:witch archmage
/factions faction set vampirismwitch:witch archmage remove
```

The last command removes the Archmage flag but leaves Witch Lord level 5 intact.

## Installing
Download the green Actions build artifact `Vampirism-Witch-0.3.2` and extract its JAR into `mods` after removing the older `vampirismwitch` JAR. The mod needs to be present on both client and LAN host/server for commands to work.
