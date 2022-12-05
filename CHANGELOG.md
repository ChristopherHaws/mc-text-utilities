# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.0]
### Added
- Add the ability to edit signs ([#34](https://github.com/ChristopherHaws/mc-text-utilities/pull/34))

### Changes
- Split client and server code make it harder to introduce runtime bugs ([#31](https://github.com/ChristopherHaws/mc-text-utilities/pull/31))
- Update fabric loader to 0.14.11 ([#35](https://github.com/ChristopherHaws/mc-text-utilities/pull/35))
- Update fabric api to 0.68.0+1.19.2 ([#35](https://github.com/ChristopherHaws/mc-text-utilities/pull/35))
- Update modmenu to 4.1.1 ([#35](https://github.com/ChristopherHaws/mc-text-utilities/pull/35))

## [1.1.0] - 2022-11-25
### Added
- Added options to disable each screen type individually ([#21](https://github.com/ChristopherHaws/mc-text-utilities/pull/21))
- Increase max width and height of chat hud ([#22](https://github.com/ChristopherHaws/mc-text-utilities/pull/22))

### Fixed
- Fixed formatting issue with anvil screen ([#14](https://github.com/ChristopherHaws/mc-text-utilities/pull/14))
- Fixed issue with mod not working on fabric servers ([#15](https://github.com/ChristopherHaws/mc-text-utilities/pull/15))
- Fixed button offsets per screen type ([#17](https://github.com/ChristopherHaws/mc-text-utilities/pull/17))

## [1.0.0] - 2022-11-10
### Added
- Added color and formatting buttons to the sign editor screen, anvil screen, and the book edit screen
- Added color and formatting support to fabric server's when installed on a server
- Added formatting code prefix configuration to support servers that don't use the the default `§` (for example, Purpur has built in support for `&`)

### Known Issues
- Adding formatting to the beginning of a large string can mess with the formatting of the text 
  in the input field ([#8](https://github.com/ChristopherHaws/mc-text-utilities/issues/8)) 
