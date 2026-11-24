# Vampirism: Witch v0.5.2

NeoForge / Minecraft 26.1.2 / Vampirism / FactionAPI.

## Fixed
- Correct localized text for Witch skill points in both English and Russian (including 1-point singular).
- The Witch skill-point provider now gets the current Witch level from FactionPlayerHandler, rather than a potentially stale player capability.
- This provider affects only the Witch level skill tree.
- Skill progression stays: Level 1 = 0; Level 2 = 1; Level 3 = 2; Level 4 = 3; Level 5 = 5; then +2 per level.
- Skill cost is deducted by FactionAPI via the custom Witch skill-tree point tag.

## Test
1. Switch to Witch level 2 and open skills: should show 1 skill point.
2. Buy Potion Resistance, then re-open: should show 0 points.
3. Increase to level 3: should show 1 point available after the purchase.
4. If the point count still shows zero at level 2, attach latest.log so we can inspect player synchronization and registration.
