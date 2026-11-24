# Vampirism: Witch v0.4.1

NeoForge 26.1.2 / Vampirism / FactionAPI.

### Fixes
- Witch Brewing skill uses the existing pixel-art Witch Hat icon instead of the missing-texture square.
- Skill description: **You're now a witch! You can use the witch's cauldron now!**
- The root skill is re-enabled when necessary after leveling up or reloading, so it remains displayed as unlocked.
- Redesigned cauldron: legless vanilla cauldron bowl directly above the vanilla campfire models. Fire visuals follow the lit state. Rebuilt the water levels.
- Clicking the cauldron with an unrelated item no longer displays brewing instructions.

### Advancement
- New Witch advancements tab, with its first advancement:
  - **This is only the begging!**
  - **Become a witch**
- Uses FactionAPI's faction advancement trigger when Witch level 1 is reached.

### Verification
- The witch cauldron remains level-gated.
- Commands and special Archmage HUD remain unchanged.

### Install
Go to GitHub Actions, download **Vampirism-Witch-0.4.1** from the latest successful build, unzip it, and replace the prior Witch JAR in both the client and host/server mods folders.
