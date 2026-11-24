# Vampirism: Witch 0.5.1

Minecraft 26.1.2 / NeoForge / Vampirism / FactionAPI

## Potion Resistance
The unlockable Potion Resistance skill costs one point. Active skills now:
- Reject harmful status effects before they're applied, retaining positive and neutral effects.
- Stop direct instant-damage splash potion damage through a narrow potion-projectile damage check.
- Keep the existing periodic cleanup for negative effects applied by nonstandard paths.

## Witch skill points
- Level 1: 0 total points.
- Level 2: 1; Level 3: 2; Level 4: 3.
- Level 5: 5; from then on +2 per level (Level 14: 23).
- Uses the custom Witch skill-point tag so the skill screen correctly deducts purchased skill costs.
- Other factions' skill point rules are unaffected.

## Test
1. Get level 1: expect 0 available points.
2. Get level 2: expect 1 point.
3. Buy Potion Resistance: expect 0 remaining.
4. Test negative effects (Poison, Weakness), positive effects (Speed) and a Splash Potion of Harming.
5. Gain levels 3, 4, 5: expect 1, 2 and 4 available points after buying Potion Resistance.

Get the JAR from the latest successful Actions artifact Vampirism-Witch-0.5.1.
