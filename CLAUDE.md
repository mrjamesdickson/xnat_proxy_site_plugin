# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an XNAT plugin (`morpheus-plugin`) that extends XNAT 1.8.4 with a React TypeScript frontend interface. The plugin integrates a modern React application (served at `/morpheus`) with Spring-based backend services for neuroimaging data management.

## Build Commands

Build the fat JAR (includes all dependencies):
```bash
./gradlew fatJar
```

Standard Gradle tasks:
```bash
./gradlew build      # Build and test
./gradlew clean      # Clean build directory
./gradlew test       # Run tests
./gradlew jar        # Build standard JAR
```

The fat JAR artifact is located at `build/libs/morpheus-plugin-1.0.0-fat.jar` after building.

## Architecture

### Plugin Structure

- **Main plugin class**: `MorpheusPlugin` (src/main/java/com/radiologics/morpheus/plugin/MorpheusPlugin.java)
  - Registers open URLs: `/xapi/morpheus/resetpassword/email`, `/xapi/morpheus/resetpassword/request`, `/morpheus/**`
  - Defines custom XNAT data model: `morpheus:qcGenericAssessorData` (code: DXQC)
  - Component scanning base package: `com.radiologics.morpheus`

### Frontend Integration

The React TypeScript application is bundled in `src/main/resources/META-INF/resources/morpheus/` with compiled JS chunks and assets. Frontend routing:
- Requests to `/morpheus/*` serve the React SPA
- Static assets (js, css, img, favicon.ico, lib/, assets/, cheerpj/) are served directly
- Built with Vite and includes OHIF DICOM viewer integration

### Data Model

Custom XNAT schema defined in `src/main/resources/schemas/morpheus/genericQCAssessor.xsd`:
- Defines the `morpheus:qcGenericAssessorData` data type
- Used for quality control assessment data
- Integrates with XNAT's data model framework

### Core Components (In Development)

The plugin structure includes packages for:
- **Configuration** (`configuration/` package): Spring configuration classes
- **Filters** (`filter/` package): Request/response filtering and routing
- **Listeners** (`listener/` package): Event handling for XNAT workflow events
- **Exceptions** (`exceptions/` package): Custom exception types (InvalidJsonException, NotUniqueException, BadRequestException)

### Frontend Application

The React TypeScript frontend (located in `xnat_proxy_site/`) provides:
- Modern UI for XNAT data management
- Project, Subject, Experiment, and Scan browsing
- OHIF DICOM viewer integration
- Dashboard with metrics and visualizations
- Upload and processing workflows
- Comprehensive search functionality

See `xnat_proxy_site/CLAUDE.md` for detailed frontend documentation.

### Dependencies

The plugin uses `compileOnly` dependencies from XNAT 1.8.4 parent BOM:
- XNAT web, data models, DICOM tools
- Spring Framework (beans, context, security, web)
- Hibernate/JPA for persistence
- Lombok for code generation
- Jackson for JSON processing
- dcm4che for DICOM operations

Test dependencies include JUnit, Mockito, Spring Test, and H2 database.

## Development Notes

- Java 8 source/target compatibility
- Uses Gradle Lombok plugin for annotation processing
- JaCoCo coverage reports generated in `build/jacocoHtml`
- Generated XNAT sources expected in `build/xnat-generated/src/main/java`
- Maven repositories: XNAT JFrog (libs-release, libs-snapshot), Maven Central

## Git Workflow - IMPORTANT

This project uses a **Git submodule** (`xnat_proxy_site/`) for the React frontend. Always check and commit BOTH repositories.

### Before Committing - Check Both Repos

```bash
# Check main plugin repo
git status

# Check submodule status
cd xnat_proxy_site && git status && cd ..
```

### Committing Changes - ALWAYS Follow This Order

**1. First: Commit and push the submodule**
```bash
cd xnat_proxy_site
git add -A
git commit -m "Your commit message"
git push origin main
cd ..
```

**2. Second: Update submodule reference in parent repo**
```bash
git add xnat_proxy_site
git commit -m "Update xnat_proxy_site submodule reference"
git push origin main
```

**3. If you also have plugin changes, commit those too**
```bash
git add -A
git commit -m "Your plugin changes"
git push origin main
```

### Why This Order Matters

The parent repo stores a reference to a specific commit hash in the submodule. If you:
- Commit parent first → the submodule reference will be wrong (pointing to uncommitted changes)
- Commit submodule first → the parent can reference the correct pushed commit

### Quick Check Script

```bash
# Run this to verify both repos are clean
echo "=== Main repo ===" && git status --short
echo "=== Submodule ===" && cd xnat_proxy_site && git status --short && cd ..
```
