# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2022-11-10

### Added

- Added color and formatting buttons to the sign editor screen, anvil screen, and the book edit screen
- Added color and formatting support to fabric server's when installed on a server
- Added formatting code prefix configuration to support servers that don't use the the default `§` (for example, Purpur has built in support for `&`)

### Known Issues
- Adding formatting to the beginning of a large string can mess with the formatting of the text 
  in the input field ([#8](https://github.com/ChristopherHaws/mc-text-utilities/issues/8)) 
