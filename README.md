# Morpheus XNAT Plugin

A modern XNAT plugin that extends XNAT 1.8.4 with a React TypeScript frontend interface for neuroimaging data management.

## Overview

This plugin integrates a modern React application served at `/morpheus` with Spring-based backend services. It provides an enhanced user interface for browsing and managing XNAT projects, subjects, experiments, and scans with integrated OHIF DICOM viewer support.

## Building

Build the fat JAR (includes all dependencies):

```bash
./gradlew fatJar
```

The artifact will be located at `build/libs/morpheus-plugin-1.0.0-fat.jar`.

### Other Build Commands

```bash
./gradlew build      # Build and test
./gradlew clean      # Clean build directory
./gradlew test       # Run tests
./gradlew jar        # Build standard JAR
```

## Installation

1. Build the fat JAR as described above
2. Copy `build/libs/morpheus-plugin-1.0.0-fat.jar` to your XNAT `plugins/` directory
3. Restart XNAT
4. Access the interface at `http://your-xnat-server/morpheus`

## Project Structure

```
├── src/main/java/com/radiologics/morpheus/
│   ├── plugin/          # Main plugin class (MorpheusPlugin)
│   ├── configuration/   # Spring configuration
│   ├── filter/          # Request/response filters
│   ├── listener/        # Event listeners
│   └── exceptions/      # Custom exception types
├── src/main/resources/
│   ├── META-INF/resources/morpheus/  # React frontend build
│   ├── schemas/xnatdx/               # XNAT data model schemas
│   ├── json/                         # Configuration files
│   └── config/roles/                 # Role definitions
├── xnat_proxy_site/     # React TypeScript frontend source
└── build.gradle         # Build configuration
```

## Features

- **Modern React UI**: TypeScript-based frontend with Tailwind CSS
- **XNAT Integration**: Full REST API integration for data management
- **OHIF Viewer**: Built-in DICOM viewer integration
- **Custom Data Models**: QC assessor data type (`morpheus:qcGenericAssessorData`)
- **Dashboard**: Metrics and visualizations for project overview
- **Search**: Comprehensive search across XNAT data

## Development

### Backend (Java)

- Java 8 source/target compatibility
- Spring Framework for dependency injection
- Hibernate/JPA for persistence
- Lombok for code generation

### Frontend (React)

The React application is located in `xnat_proxy_site/`. See `xnat_proxy_site/CLAUDE.md` for detailed frontend documentation.

```bash
cd xnat_proxy_site
npm install
npm run dev      # Development server
npm run build    # Production build
```

## Configuration

- **Plugin ID**: `morpheusplugin`
- **Open URLs**: `/xapi/morpheus/resetpassword/*`, `/morpheus/**`
- **Data Model**: `morpheus:qcGenericAssessorData` (code: DXQC)
- **Component Scan**: `com.radiologics.morpheus`

## License

Copyright (c) 2019, Radiologics Inc. All Rights Reserved.

## Requirements

- XNAT 1.8.4
- Java 8+
- Gradle 4.x+