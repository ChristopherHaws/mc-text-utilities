# Text Utilities (for Fabric)
![GitHub license](https://img.shields.io/github/license/ChristopherHaws/mc-text-utilities.svg)
![GitHub issues](https://img.shields.io/github/issues/ChristopherHaws/mc-text-utilities.svg)
![GitHub tag](https://img.shields.io/github/tag/ChristopherHaws/mc-text-utilities.svg)
[![Discord chat](https://img.shields.io/badge/chat%20on-discord-7289DA?logo=discord&logoColor=white)](https://discord.gg/kQjty3rfJd)

Text Utilities is a free and open-source mod for Minecraft which adds color and formatting 
options to signs, books, and anvils.

<div align="center">
	<img src="src/main/resources/assets/textutilities/icon.png" width="128" />
</div>

---

## Features
- Adds color and formatting buttons to the sign editor screen, anvil screen, and the book edit screen
- Adds color and formatting support to fabric server's when installed on a server
- Formatting code prefix is configurable to support servers that don't use the the default `§` (for example, Purpur has built in support for `&`)

NOTE: At the moment, you have to press a color before a format button if you want to use both.

![image](https://user-images.githubusercontent.com/5934505/201258398-e3b3ea7f-af3e-4e69-8bc4-4e0713880568.png)

---

## Installation

### Manual installation

You will need Fabric Loader 0.10.x or newer installed in your game in order to load Text 
Utilities. If you haven't installed Fabric mods before, you can find a variety of community 
guides for doing so [here](https://fabricmc.net/wiki/install).

#### Stable releases
![GitHub release](https://img.shields.io/github/release/ChristopherHaws/mc-text-utilities.svg)

The latest releases of Text Utilities are published to our [Modrinth](https://modrinth.com/mod/text-utilities)
and [GitHub release](https://github.com/ChristopherHaws/mc-text-utilities/releases) pages. Releases
are considered to be **suitable for general use**, but they are not guaranteed to be free of bugs 
and other issues.

Usually, releases will be made available on GitHub slightly sooner than other locations.

#### Bleeding-edge builds (unstable)
[![GitHub build status](https://img.shields.io/github/workflow/status/ChristopherHaws/mc-text-utilities/build/1.19)](https://github.com/ChristopherHaws/mc-text-utilities/actions/workflows/build.yml)

If you are a player who is looking to get your hands on the latest **bleeding-edge changes for 
testing**, consider taking a look at the automated builds produced through our
[GitHub Actions workflow](https://github.com/ChristopherHaws/mc-text-utilities/actions/workflows/build.yml?query=event%3Apush).
This workflow automatically runs every time a change is pushed to the repository, and as such, 
the builds it produces will generally reflect the latest snapshot of development.

Bleeding edge builds will often include unfinished code that hasn't been extensively tested. 
That code may introduce incomplete features, bugs, crashes, and all other kinds of weird issues. 
You **should not use these bleeding edge builds** unless you know what you are doing and are 
comfortable with software debugging.

### Reporting Issues
You can report bugs and crashes by opening an issue on our [issue tracker](https://github.com/ChristopherHaws/mc-text-utilities/issues).
Before opening a new issue, use the search tool to make sure that your issue has not already 
been reported and ensure that you have completely filled out the issue template. Issues that are 
duplicates or do not contain the necessary information to triage and debug may be closed.

Please note that while the issue tracker is open to feature requests, development is primarily 
focused on improving hardware compatibility, performance, and finishing any unimplemented 
features necessary for parity with the vanilla renderer.

---
### License
Text Utilities is licensed under GNU LGPLv3, a free and open-source license. For more information, 
please see the [license file](https://github.com/ChristopherHaws/mc-text-utilities/blob/1.19/LICENSE).
