# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.3.1]
### Added
- Added the ability to include additional click through blocks or entities
  - Added `create:placard` as an example which adds mod compatibility
- Tooltips are now shown when hovering over config settings

### Fixed
- Fixed an issue where you couldn't place a sign on another sign
- Build number should not show up in the jar name anymore

## [1.3.0]
### Added
- Add the ability to click through signs and item frames
  - Sign click through is prevented when holding a sign, dye, an ink sac, or a glowing ink sac in your main hand
  - Item Frame click through is prevented while sneaking or if the item frame has no item in it

### Changed
- Update yarn mappings version to 1.19.3+build.5
- Update fabric loader to 0.14.12
- Update fabric api to 0.70.0+1.19.3
- Update modmenu to 5.0.2
- Update cloth config to 9.0.94

## [1.2.0]
### Added
- Add the ability to edit signs ([#34](https://github.com/ChristopherHaws/mc-text-utilities/pull/34))

### Changed
- Split client and server code make it harder to introduce runtime bugs ([#31](https://github.com/ChristopherHaws/mc-text-utilities/pull/31))
- Update fabric loader to 0.14.11 ([#36](https://github.com/ChristopherHaws/mc-text-utilities/pull/36))
- Update fabric api to 0.68.0+1.19.2 ([#36](https://github.com/ChristopherHaws/mc-text-utilities/pull/36))
- Update modmenu to 4.1.1 ([#36](https://github.com/ChristopherHaws/mc-text-utilities/pull/36))
- Update minecraft version to 1.19.3
- Update yarn mappings version to 1.19.3+build.1
- Update fabric loader to 0.14.11
- Update fabric api to 0.68.1+1.19.3
- Update modmenu to 5.0.0-alpha.4
- Update cloth config to 9.0.94
- Update `ButtonWidget` to use new builder method

## [1.1.0]
### Added
- Added options to disable each screen type individually ([#21](https://github.com/ChristopherHaws/mc-text-utilities/pull/21))
- Increase max width and height of chat hud ([#22](https://github.com/ChristopherHaws/mc-text-utilities/pull/22))

### Fixed
- Fixed formatting issue with anvil screen ([#14](https://github.com/ChristopherHaws/mc-text-utilities/pull/14))
- Fixed issue with mod not working on fabric servers ([#15](https://github.com/ChristopherHaws/mc-text-utilities/pull/15))
- Fixed button offsets per screen type ([#17](https://github.com/ChristopherHaws/mc-text-utilities/pull/17))

## [1.0.0]
### Added
- Added color and formatting buttons to the sign editor screen, anvil screen, and the book edit screen
- Added color and formatting support to fabric server's when installed on a server
- Added formatting code prefix configuration to support servers that don't use the default `§` (for example, Purpur has built in support for `&`)

### Known Issues
- Adding formatting to the beginning of a large string can mess with the formatting of the text 
  in the input field ([#8](https://github.com/ChristopherHaws/mc-text-utilities/issues/8)) 
