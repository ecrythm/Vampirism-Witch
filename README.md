# Vampirism: Witch v0.2.2

Minecraft 26.1.2 / NeoForge / Vampirism / FactionAPI.

## Fix
- Normal Witch level 1–14 is displayed using **FactionAPI's built-in level HUD**, just like for vampires and hunters.
- The separate Witch GUI layer is removed to prevent conflicts.
- The `P` screen still shows `Lord Level 1–5` when you are a lord.
- HUD deliberately shows only the normal level (not lord titles).
- No new abilities or lord title names added.

## Commands
```mcfunction
/factions faction set vampirismwitch:witch level 1
/factions faction set vampirismwitch:witch level 7
/factions faction set vampirismwitch:witch level 14
/factions faction set vampirismwitch:witch lord 5
```

The HUD is shown in Survival, provided FactionAPI's client setting `showFactionLevelOverlay` is enabled. Its default vertical offset is 47 pixels from the bottom; change that setting if desired.

## Install
Download the latest successful GitHub Actions artifact `Vampirism-Witch-0.2.2`. Unzip the .jar and **remove older Vampirism Witch jars** from mods before launching.
