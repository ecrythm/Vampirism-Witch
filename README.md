# Vampirism: Witch v0.3.3

NeoForge / Minecraft 26.1.2 / Vampirism / FactionAPI.

## Fixes
- Witch HUD is drawn **only** in Survival and Adventure, never Creative or Spectator.
- Fixed missing alpha in the text color (the previous text was fully transparent).
- Standard witch levels 1–14 are shown as a single clean, dark-purple numeral; Archmage displays `Arch`.
- The Witch Archmage rank command is now inside the FactionAPI commands, with optional player target.
- Removed `/witch`, `/vampirismwitch`, `/witch status`, and the `archmage remove` subcommand.

## Commands (requires cheats / operator privileges)
Give Archmage to yourself:
```mcfunction
/factions faction set vampirismwitch:witch archmage
```

Give Archmage to a selected player, the nearest player, or every player:
```mcfunction
/factions faction set vampirismwitch:witch archmage @s
/factions faction set vampirismwitch:witch archmage @p
/factions faction set vampirismwitch:witch archmage @a
/factions faction set vampirismwitch:witch archmage PlayerName
```

The argument accepts **player selectors**, not arbitrary non-player entities.
Granting Archmage sets Witch 14, Lord 5, and the persistent Archmage status.

## Installation
Remove previous Witch JAR versions from mods. Install the GitHub Actions v0.3.3 JAR on the host/server and client for multiplayer. The HUD is rendered only on the client.
