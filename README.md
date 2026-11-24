# Vampirism: Witch 0.5.3

Fixes level 2 skill points by restoring the FactionAPI default skill point tag for the Witch level tree.

## Progression
- FactionAPI grants the normal first point at level 2 and one every later level.
- Witch adds one **extra** point at each level starting at level 5.
- Level 1: 0 points; level 2: 1; level 3: 2; level 4: 3; level 5: 5; level 6: 7; level 14: 23.
- Points spent on Potion Resistance are deducted by FactionAPI automatically.

The prior custom skill point tag prevented the built-in leveling provider from contributing. No separate custom skill point tag is needed.

Test at level 2 before buying Potion Resistance, then at levels 5 and 6. If a server changes FactionAPI's standard skill-points-per-level configuration from 1, that configuration also affects the base points.
