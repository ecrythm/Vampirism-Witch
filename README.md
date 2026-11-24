# Vampirism: Witch v0.4.5

Minecraft 26.1.2 / NeoForge / Vampirism / FactionAPI.

## Fixes
- The cauldron now uses vanilla water texture with translucent rendering and water tint, instead of a custom opaque-looking blue surface.
- Heated cauldron produces fewer, smaller vanilla splash particles rather than a constant cloud of bubble-pop particles.
- Fire under the bowl is wider while lit.
- The Witch advancements tab is titled **Witch**.
- The achievement **This is only the begging!** / **Become a witch** is now a separate child under the Witch tab. It checks the Witch faction both on joining and every tick, so it will be awarded to existing witches on reload and stay recorded in the world's advancement data.

### Install
Go to GitHub Actions > latest successful build > Artifacts > Vampirism-Witch-0.4.5.
Remove prior Witch JAR before installing. If on LAN or multiplayer, update both client and server.
