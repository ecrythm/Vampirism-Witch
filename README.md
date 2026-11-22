# Vampirism: Witch 0.3.0

Minecraft 26.1.2, NeoForge, FactionAPI, Vampirism.

## What's new
- **Witch level HUD:** renders directly after the main Minecraft GUI, independently of FactionAPI's overlay setting. Displays only regular Witch level 1–14.
- **Colors:** nickname and HUD use the same almost-black purple (#2B192F).
- **Archmage:** special rank beyond Witch Lord 5. No additional lord titles or abilities yet. It persists through saves/death and syncs to the client. Faction menu displays Archmage.

## Commands (requires cheats / operator)
```mcfunction
/factions faction set vampirismwitch:witch level 7
/factions faction set vampirismwitch:witch lord 5
/witch archmage
/witch archmage remove
```
`/witch archmage` grants Witch level 14 + Witch Lord level 5 + the Archmage flag.
Removing Archmage rank leaves the existing lord level unchanged.

## Install
Download the **Vampirism-Witch-0.3.0** artifact from latest successful GitHub Actions run. Extract JAR and replace older Witch JAR.
