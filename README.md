# Vampirism: Witch v0.4.0

NeoForge Minecraft 26.1.2, Vampirism and FactionAPI.

### Added
- The faction P-screen nickname/level title and HUD share a muted purple color `#74517F`.
- **Witch Cauldron** in the creative tab and recipe: a vanilla Cauldron above a Campfire in a crafting grid.
- A block with water levels, heating, and a simple first experimental night-vision brew: water bucket -> flint & steel -> glow berries -> glass bottle. Water holds up to 3 bottles. Only level 1+ Witches can operate it.
- **Witch Brewing**, a level-one root skill in the Witch skill tree registered with FactionAPI, intended to be auto-unlocked as soon as a Witch joins.
- Hostile mobs stop acquiring level 1+ witches as targets. Wither Skeletons and Endermen are exceptions; vanilla Enderman gaze aggression is preserved.
- The previous Archmage and level HUD features are unchanged.

### Notes
Mob neutrality is based on AI targeting, not damage immunity or projectile interception. Some modded mobs may use custom targeting systems not covered by NeoForge's targeting event. The night-vision brew is a first demonstrative recipe, not a complete potion catalogue.

### Commands
```
/factions faction set vampirismwitch:witch level 1
/factions faction set vampirismwitch:witch level 14
/factions faction set vampirismwitch:witch archmage
/factions faction set vampirismwitch:witch archmage @s
```

Download the latest green GitHub Actions `Vampirism-Witch-0.4.0` artifact and install the JAR on host/server and client. Remove the previous JAR.
