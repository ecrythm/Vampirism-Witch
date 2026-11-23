# Vampirism: Witch v0.3.1

Minecraft 26.1.2, NeoForge, Vampirism and FactionAPI.

## Changes

- The Witch level HUD now uses an automatically subscribed client event, no longer manual registration. The dark-purple number has a pale outline for contrast.
- Two command aliases are registered automatically: `/witch archmage` and `/vampirismwitch archmage`.
- Debug command `/witch status` displays current Witch faction, level and Archmage flag.
- The Archmage rank grants Witch level 14 and Lord level 5; P-screen shows Archmage. HUD still shows normal levels 1–14.
- Faction nickname stays almost black-purple (hex 2B192F).

## Install
Replace the older JAR in mods with version 0.3.1. On multiplayer/LAN, install it **on the server/host as well as on the client**; server-only commands will not exist otherwise.

## Commands
```
/witch status
/witch archmage
/vampirismwitch archmage
/witch archmage remove
/factions faction set vampirismwitch:witch level 7
```

The Archmage commands require cheats (OP permissions).
If tab completion does not show `/witch`, check `logs/latest.log` for `Vampirism: Witch 0.3.1 initialized` and `Registered Witch commands`.
