# Vampirism: Witch v0.4.8

Minecraft 26.1.2, NeoForge, Vampirism and FactionAPI.

- Witch cauldron bubbles appear slightly more often when heated: chance increased from 1/5 to 1/4 per animation tick.
- Fill the cauldron with ordinary **water potion bottles** (Minecraft water bottles) in increments of one water level each, up to three.
- Each water potion used for filling produces an empty glass bottle. Other potion types are ignored.
- Brewing state is reset when new water is poured in, so added water cannot inherit a finished potion.
- Water buckets still fill the cauldron completely; drawing with empty bottles still reduces the level one at a time.
- All other faction mechanics remain unchanged.

## Testing
1. Become a level 1 Witch or higher.
2. Use one Water Bottle on an empty Witch Cauldron: water becomes level 1 and the bottle becomes empty.
3. Repeat twice to fill it to level 3.
4. Take out three water bottles with empty glass bottles.
5. Heat filled cauldron: bubbles are a little more frequent.

## Download
GitHub > Actions > latest green build > Artifacts > Vampirism-Witch-0.4.8.
Remove older Witch jars from mods before installing.
