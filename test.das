version "6.3"

// Comprehensive DICOM Anonymization Script
// Based on DICOM PS3.15 Annex E - Basic Application Level Confidentiality Profile
// This script implements the required tag modifications for de-identification

// ============================================================================
// PART 1: DICOM Basic Confidentiality Profile Required Removals/Modifications
// ============================================================================

// Patient Characteristics - REMOVE (per PS3.15 Table E.1-1)
(0010,0030) := ""                    // Patient's Birth Date - REMOVE
(0010,0032) := ""                    // Patient's Birth Time - REMOVE
(0010,0040) := ""                    // Patient's Sex - REMOVE or keep for research
(0010,1000) := ""                    // Other Patient IDs - REMOVE
(0010,1001) := ""                    // Other Patient Names - REMOVE
(0010,1005) := ""                    // Patient's Birth Name - REMOVE
(0010,1010) := ""                    // Patient's Age - REMOVE or replace with age range
(0010,1020) := ""                    // Patient's Size - REMOVE
(0010,1030) := ""                    // Patient's Weight - REMOVE
(0010,1040) := ""                    // Patient's Address - REMOVE
(0010,1060) := ""                    // Patient's Mother's Birth Name - REMOVE
(0010,1080) := ""                    // Military Rank - REMOVE
(0010,1081) := ""                    // Branch of Service - REMOVE
(0010,1090) := ""                    // Medical Record Locator - REMOVE
(0010,2000) := ""                    // Medical Alerts - REMOVE
(0010,2110) := ""                    // Allergies - REMOVE
(0010,2150) := ""                    // Country of Residence - REMOVE
(0010,2152) := ""                    // Region of Residence - REMOVE
(0010,2154) := ""                    // Patient's Telephone Numbers - REMOVE
(0010,2160) := ""                    // Ethnic Group - REMOVE
(0010,2180) := ""                    // Occupation - REMOVE
(0010,21A0) := ""                    // Smoking Status - REMOVE
(0010,21B0) := ""                    // Additional Patient History - REMOVE
(0010,21C0) := ""                    // Pregnancy Status - REMOVE
(0010,21D0) := ""                    // Last Menstrual Date - REMOVE
(0010,21F0) := ""                    // Patient's Religious Preference - REMOVE
(0010,4000) := ""                    // Patient Comments - REMOVE

// Patient Identification - MODIFY (per PS3.15 Table E.1-1)
(0010,0010) := "ANONYMIZED"          // Patient Name - REPLACE with anonymized value
(0010,0020) := "ANON_ID"             // Patient ID - REPLACE with anonymized ID
(0010,0021) := ""                    // Issuer of Patient ID - REMOVE

// Study/Series Identification - REMOVE (per PS3.15 Table E.1-1)
(0008,0050) := ""                    // Accession Number - REMOVE
(0008,0051) := ""                    // Issuer of Accession Number Sequence - REMOVE
(0020,0010) := ""                    // Study ID - REMOVE or keep for tracking
(0032,0012) := ""                    // Study ID Issuer - REMOVE

// Dates and Times - REMOVE or MODIFY (per PS3.15 Table E.1-1)
(0008,0012) := ""                    // Instance Creation Date - REMOVE
(0008,0013) := ""                    // Instance Creation Time - REMOVE
(0008,0020) := ""                    // Study Date - REMOVE or shift
(0008,0021) := ""                    // Series Date - REMOVE or shift
(0008,0022) := ""                    // Acquisition Date - REMOVE or shift
(0008,0023) := ""                    // Content Date - REMOVE or shift
(0008,002A) := ""                    // Acquisition DateTime - REMOVE or shift
(0008,0030) := ""                    // Study Time - REMOVE or shift
(0008,0031) := ""                    // Series Time - REMOVE or shift
(0008,0032) := ""                    // Acquisition Time - REMOVE or shift
(0008,0033) := ""                    // Content Time - REMOVE or shift

// Institution/Device Identification - REMOVE (per PS3.15 Table E.1-1)
(0008,0070) := ""                    // Manufacturer - REMOVE or keep
(0008,0080) := ""                    // Institution Name - REMOVE
(0008,0081) := ""                    // Institution Address - REMOVE
(0008,0082) := ""                    // Institution Code Sequence - REMOVE
(0008,1010) := ""                    // Station Name - REMOVE
(0008,1030) := ""                    // Study Description - REMOVE
(0008,1040) := ""                    // Institutional Department Name - REMOVE
(0008,1048) := ""                    // Physician(s) of Record - REMOVE
(0008,1049) := ""                    // Physician(s) of Record Identification Sequence - REMOVE
(0008,1050) := ""                    // Performing Physician's Name - REMOVE
(0008,1052) := ""                    // Performing Physician Identification Sequence - REMOVE
(0008,1060) := ""                    // Name of Physician(s) Reading Study - REMOVE
(0008,1062) := ""                    // Physician(s) Reading Study Identification Sequence - REMOVE
(0008,1070) := ""                    // Operators' Name - REMOVE
(0008,1072) := ""                    // Operator Identification Sequence - REMOVE
(0008,1080) := ""                    // Admitting Diagnoses Description - REMOVE
(0008,1084) := ""                    // Admitting Diagnoses Code Sequence - REMOVE
(0008,1090) := ""                    // Manufacturer's Model Name - REMOVE or keep
(0008,1110) := ""                    // Referenced Study Sequence - REMOVE
(0008,1111) := ""                    // Referenced Performed Procedure Step Sequence - REMOVE
(0008,1120) := ""                    // Referenced Patient Sequence - REMOVE

// Device/Equipment Serial Numbers - REMOVE (per PS3.15 Table E.1-1)
(0018,1000) := ""                    // Device Serial Number - REMOVE
(0018,1002) := ""                    // Device UID - REMOVE
(0018,1004) := ""                    // Plate ID - REMOVE
(0018,1005) := ""                    // Generator ID - REMOVE
(0018,1007) := ""                    // Cassette ID - REMOVE
(0018,1008) := ""                    // Gantry ID - REMOVE
(0018,1020) := ""                    // Software Version(s) - REMOVE or keep

// Protocol and Procedure Info - REMOVE (per PS3.15 Table E.1-1)
(0018,1030) := ""                    // Protocol Name - REMOVE
(0018,1400) := ""                    // Acquisition Device Processing Description - REMOVE
(0018,4000) := ""                    // Acquisition Comments - REMOVE
(0018,9424) := ""                    // Acquisition Protocol Description - REMOVE
(0020,4000) := ""                    // Image Comments - REMOVE
(0032,1060) := ""                    // Requested Procedure Description - REMOVE
(0032,4000) := ""                    // Study Comments - REMOVE
(0040,0254) := ""                    // Performed Procedure Step Description - REMOVE
(0040,0280) := ""                    // Comments on the Performed Procedure Step - REMOVE
(0040,2400) := ""                    // Imaging Service Request Comments - REMOVE

// Requesting/Referring Physician - REMOVE (per PS3.15 Table E.1-1)
(0008,0090) := ""                    // Referring Physician's Name - REMOVE
(0008,0092) := ""                    // Referring Physician's Address - REMOVE
(0008,0094) := ""                    // Referring Physician's Telephone Numbers - REMOVE
(0008,0096) := ""                    // Referring Physician Identification Sequence - REMOVE
(0032,1032) := ""                    // Requesting Physician - REMOVE
(0032,1033) := ""                    // Requesting Service - REMOVE
(0010,0021) := ""                    // Issuer of Patient ID
(0010,0032) := ""                    // Patient's Birth Time
(0010,0050) := ""                    // Patient's Insurance Plan Code SQ
(0010,0101) := ""                    // Patient's Primary Language Code SQ
(0010,1000) := ""                    // Other Patient IDs
(0010,1001) := ""                    // Other Patient Names
(0010,1002) := ""                    // Other Patient IDs SQ
(0010,1005) := ""                    // Patient's Birth Name
(0010,1010) := ""                    // Patient's Age
(0010,1040) := ""                    // Patient's Address
(0010,1060) := ""                    // Patient's Mother's Birth Name
(0010,1080) := ""                    // Military Rank
(0010,1081) := ""                    // Branch of Service
(0010,1090) := ""                    // Medical Record Locator
(0010,2000) := ""                    // Medical Alerts
(0010,2110) := ""                    // Allergies
(0010,2150) := ""                    // Country of Residence
(0010,2152) := ""                    // Region of Residence
(0010,2154) := ""                    // Patient's Telephone Numbers
(0010,2160) := ""                    // Ethnic Group

// UPDATED Tags - These will be modified
(0010,0010) := "TEST_PATIENT"        // Patient Name
(0010,0020) := "TEST_ID_001"         // Patient ID
(0012,0062) := "YES"                 // Patient Identity Removed
(0012,0063) := "Modified for testing" // De-identification Method

// Institution Tags - Removed
(0008,0080) := ""                    // Institution Name
(0008,0081) := ""                    // Institution Address
(0008,1040) := ""                    // Institutional Department Name
(0008,1070) := ""                    // Operators Name

// Physician Tags - Removed
(0008,0090) := ""                    // Referring Physician Name
(0008,1048) := ""                    // Physician(s) of Record
(0008,1050) := ""                    // Performing Physician's Name
(0008,1060) := ""                    // Name of Physician(s) Reading Study

// Additional Patient Demographics - Removed
(0010,0030) := ""                    // Patient's Birth Date
(0010,0040) := ""                    // Patient's Sex (keeping for research often, but removing for test)
(0010,1020) := ""                    // Patient's Size
(0010,1030) := ""                    // Patient's Weight
(0010,2000) := ""                    // Medical Alerts
(0010,2110) := ""                    // Allergies
(0010,2180) := ""                    // Occupation
(0010,21A0) := ""                    // Smoking Status
(0010,21B0) := ""                    // Additional Patient History
(0010,21C0) := ""                    // Pregnancy Status
(0010,21D0) := ""                    // Last Menstrual Date
(0010,21F0) := ""                    // Patient's Religious Preference
(0010,4000) := ""                    // Patient Comments

// Request/Visit Identification - Removed
(0008,0051) := ""                    // Issuer of Accession Number SQ
(0008,1110) := ""                    // Referenced Study SQ
(0008,1111) := ""                    // Referenced Performed Procedure Step SQ
(0008,1120) := ""                    // Referenced Patient SQ
(0032,1032) := ""                    // Requesting Physician
(0032,1033) := ""                    // Requesting Service
(0032,1060) := ""                    // Requested Procedure Description
(0038,0010) := ""                    // Admission ID
(0038,0011) := ""                    // Issuer of Admission ID
(0038,0060) := ""                    // Service Episode ID
(0038,0061) := ""                    // Issuer of Service Episode ID
(0038,0500) := ""                    // Patient State

// Scheduled/Performed Procedure Info - Removed
(0040,0001) := ""                    // Scheduled Station AE Title
(0040,0002) := ""                    // Scheduled Procedure Step Start Date
(0040,0003) := ""                    // Scheduled Procedure Step Start Time
(0040,0004) := ""                    // Scheduled Procedure Step End Date
(0040,0005) := ""                    // Scheduled Procedure Step End Time
(0040,0006) := ""                    // Scheduled Performing Physician's Name
(0040,0007) := ""                    // Scheduled Procedure Step Description
(0040,0009) := ""                    // Scheduled Procedure Step ID
(0040,0010) := ""                    // Scheduled Station Name
(0040,0011) := ""                    // Scheduled Procedure Step Location
(0040,0012) := ""                    // Pre-Medication
(0040,0241) := ""                    // Performed Station AE Title
(0040,0242) := ""                    // Performed Station Name
(0040,0243) := ""                    // Performed Location
(0040,0244) := ""                    // Performed Procedure Step Start Date
(0040,0245) := ""                    // Performed Procedure Step Start Time
(0040,0250) := ""                    // Performed Procedure Step End Date
(0040,0251) := ""                    // Performed Procedure Step End Time
(0040,0253) := ""                    // Performed Procedure Step ID
(0040,0254) := ""                    // Performed Procedure Step Description
(0040,0275) := ""                    // Request Attributes SQ

// Study/Visit Comments - Removed
(0008,1030) := ""                    // Study Description
(0020,4000) := ""                    // Image Comments
(0032,4000) := ""                    // Study Comments
(0040,0280) := ""                    // Comments on the Performed Procedure Step
(0040,2400) := ""                    // Imaging Service Request Comments

// Device/Equipment Identity - Removed
(0008,0070) := ""                    // Manufacturer
(0008,1010) := ""                    // Station Name
(0008,1040) := ""                    // Institutional Department Name
(0008,1090) := ""                    // Manufacturer's Model Name
(0018,1000) := ""                    // Device Serial Number
(0018,1020) := ""                    // Software Versions
(0018,1030) := ""                    // Protocol Name
(0018,1400) := ""                    // Acquisition Device Processing Description

// Person Names (additional) - Removed
(0008,1048) := ""                    // Physician(s) of Record
(0008,1049) := ""                    // Physician(s) of Record Identification SQ
(0008,1050) := ""                    // Performing Physician's Name
(0008,1052) := ""                    // Performing Physician Identification SQ
(0008,1060) := ""                    // Name of Physician(s) Reading Study
(0008,1062) := ""                    // Physician(s) Reading Study Identification SQ
(0008,1070) := ""                    // Operators' Name
(0008,1072) := ""                    // Operator Identification SQ
(0010,2297) := ""                    // Responsible Person
(0010,2298) := ""                    // Responsible Person Role
(0010,2299) := ""                    // Responsible Organization

// Dates and Times (identifying) - Removed
(0008,0012) := ""                    // Instance Creation Date
(0008,0013) := ""                    // Instance Creation Time
(0008,0021) := ""                    // Series Date
(0008,0022) := ""                    // Acquisition Date
(0008,0023) := ""                    // Content Date
(0008,002A) := ""                    // Acquisition DateTime
(0008,0031) := ""                    // Series Time
(0008,0032) := ""                    // Acquisition Time
(0008,0033) := ""                    // Content Time
(0008,0081) := ""                    // Institution Address

// UIDs that might contain dates - Keep as is (handled by UID remapping)
// (0008,0018) - SOP Instance UID
// (0020,000D) - Study Instance UID
// (0020,000E) - Series Instance UID

// Curve and Overlay Data - Removed
(0040,0555) := ""                    // Acquisition Context SQ
(0040,A073) := ""                    // Verifying Observer Name
(0040,A075) := ""                    // Verifying Observer Identification Code SQ
(0040,A123) := ""                    // Person Name
(0040,A027) := ""                    // Verifying Organization
(0070,0001) := ""                    // Graphic Annotation SQ (may contain PHI in text)
(0088,0200) := ""                    // Icon Image Sequence (may contain PHI)

// Study/Series Tags that should remain UNCHANGED (not in script)
// (0008,0020) - Study Date (often kept for research)
// (0008,0030) - Study Time
// (0008,0060) - Modality
// (0018,0015) - Body Part Examined
// (0018,0080) - Repetition Time
// (0018,0081) - Echo Time
// (0018,0082) - Inversion Time
// (0018,0083) - Number of Averages
// (0018,0084) - Imaging Frequency
// (0018,0085) - Imaged Nucleus
// (0018,0087) - Magnetic Field Strength
// (0018,0088) - Spacing Between Slices
// (0018,0089) - Number of Phase Encoding Steps
// (0018,1310) - Acquisition Matrix
// (0018,1314) - Flip Angle
// (0020,0010) - Study ID
// (0020,0011) - Series Number
// (0020,0013) - Instance Number
// (0020,0032) - Image Position Patient
// (0020,0037) - Image Orientation Patient
// (0020,0052) - Frame of Reference UID
// (0020,1041) - Slice Location
// (0028,0010) - Rows
// (0028,0011) - Columns
// (0028,0030) - Pixel Spacing
// (0028,0100) - Bits Allocated
// (0028,0101) - Bits Stored
// (0028,0102) - High Bit
// (0028,0103) - Pixel Representation
