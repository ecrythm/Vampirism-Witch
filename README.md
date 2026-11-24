# Vampirism: Witch v0.5.0

NeoForge / Minecraft 26.1.2, Vampirism and FactionAPI.

## New skill: Potion Resistance
- Unlock after Becoming a Witch.
- Costs **1 skill point**.
- When enabled, removes harmful status effects from the Witch automatically (about every two game ticks).
- Does not remove beneficial or neutral effects. All harmful effects are checked by their Minecraft effect category.
- Turning off the skill stops the protection.

## Skill points
- A Witch has **2 skill points per faction level**, including the first one.
- Level 1: 2 total points; level 7: 14; level 14: 28. Unlock costs are subtracted from this total.
- The skill-point provider applies exclusively to the Witch faction level skill tree and does not change Vampires or Hunters.

## Test
1. Become a Witch, level 1.
2. Open the Witch skills screen and purchase Potion Resistance for one point.
3. Try harmful potion status effects such as Poison or Weakness; they should disappear automatically.
4. Beneficial effects such as Speed and Regeneration should remain.
5. Raise faction level; your available skill points should increase by two per level.

Download JAR in GitHub Actions, artifact Vampirism-Witch-0.5.0.
