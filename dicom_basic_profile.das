version "6.3"

// DICOM PS3.15 Annex E - Basic Application Level Confidentiality Profile
// Complete anonymization script based on the standard requirements

// ============================================================================
// REQUIRED MODIFICATIONS - Patient Identification
// ============================================================================
(0010,0010) := "ANONYMIZED"          // Patient Name - MUST BE MODIFIED
(0010,0020) := "ANON_ID"             // Patient ID - MUST BE MODIFIED

// ============================================================================
// REQUIRED REMOVALS - All tags below MUST be removed per DICOM profile
// ============================================================================

// Instance Creation
-(0008,0012)                         // Instance Creation Date
-(0008,0013)                         // Instance Creation Time

// Study/Series Identification
-(0008,0050)                         // Accession Number

// Institution Information
-(0008,0080)                         // Institution Name
-(0008,0081)                         // Institution Address

// Referring Physician
-(0008,0090)                         // Referring Physician's Name
-(0008,0092)                         // Referring Physician's Address
-(0008,0094)                         // Referring Physician's Telephone Numbers

// Device/Station
-(0008,1010)                         // Station Name

// Descriptions
-(0008,1030)                         // Study Description
-(0008,1040)                         // Institutional Department Name

// Physician Names
-(0008,1048)                         // Physician(s) of Record
-(0008,1050)                         // Performing Physician's Name
-(0008,1060)                         // Name of Physician(s) Reading Study
-(0008,1070)                         // Operators' Name

// Diagnoses
-(0008,1080)                         // Admitting Diagnoses Description

// Patient Demographics
-(0010,0030)                         // Patient's Birth Date
-(0010,0032)                         // Patient's Birth Time

// Patient IDs and Names
-(0010,1000)                         // Other Patient IDs
-(0010,1001)                         // Other Patient Names
-(0010,1005)                         // Patient's Birth Name

// Patient Characteristics
-(0010,1010)                         // Patient's Age
-(0010,1020)                         // Patient's Size
-(0010,1030)                         // Patient's Weight
-(0010,1040)                         // Patient's Address

// Patient History
-(0010,1060)                         // Patient's Mother's Birth Name
-(0010,1080)                         // Military Rank
-(0010,1081)                         // Branch of Service
-(0010,1090)                         // Medical Record Locator

// Patient Contact Info
-(0010,2154)                         // Patient's Telephone Numbers

// Patient Demographics Extended
-(0010,2160)                         // Ethnic Group
-(0010,2180)                         // Occupation
-(0010,21B0)                         // Additional Patient History
-(0010,21D0)                         // Last Menstrual Date

// Patient Comments
-(0010,4000)                         // Patient Comments

// Device Serial Numbers
-(0018,1000)                         // Device Serial Number

// Protocol Information
-(0018,1030)                         // Protocol Name

// Image Comments
-(0020,4000)                         // Image Comments

// Requesting Physician
-(0032,1032)                         // Requesting Physician

// Procedure Descriptions
-(0032,1060)                         // Requested Procedure Description

// Study Comments
-(0032,4000)                         // Study Comments

// Performed Procedure
-(0040,0254)                         // Performed Procedure Step Description
-(0040,0280)                         // Comments on the Performed Procedure Step

// ============================================================================
// ADDITIONAL RECOMMENDED REMOVALS (Extended Profile Coverage)
// ============================================================================

// Dates and Times
-(0008,0020)                         // Study Date
-(0008,0021)                         // Series Date
-(0008,0022)                         // Acquisition Date
-(0008,0023)                         // Content Date
-(0008,002A)                         // Acquisition DateTime
-(0008,0030)                         // Study Time
-(0008,0031)                         // Series Time
-(0008,0032)                         // Acquisition Time
-(0008,0033)                         // Content Time

// Additional Patient Info
-(0010,0021)                         // Issuer of Patient ID
-(0010,0040)                         // Patient's Sex (optional - keep if needed for research)
-(0010,2000)                         // Medical Alerts
-(0010,2110)                         // Allergies
-(0010,2150)                         // Country of Residence
-(0010,2152)                         // Region of Residence
-(0010,21A0)                         // Smoking Status
-(0010,21C0)                         // Pregnancy Status
-(0010,21F0)                         // Patient's Religious Preference

// Additional Institution/Device
-(0008,0070)                         // Manufacturer (optional - keep if needed)
-(0008,0082)                         // Institution Code Sequence
-(0008,1084)                         // Admitting Diagnoses Code Sequence
-(0008,1090)                         // Manufacturer's Model Name (optional - keep if needed)
-(0008,1110)                         // Referenced Study Sequence
-(0008,1111)                         // Referenced Performed Procedure Step Sequence
-(0008,1120)                         // Referenced Patient Sequence

// Additional Physician Sequences
-(0008,0096)                         // Referring Physician Identification Sequence
-(0008,1049)                         // Physician(s) of Record Identification Sequence
-(0008,1052)                         // Performing Physician Identification Sequence
-(0008,1062)                         // Physician(s) Reading Study Identification Sequence
-(0008,1072)                         // Operator Identification Sequence

// Study/Visit Info
-(0008,0051)                         // Issuer of Accession Number Sequence
-(0020,0010)                         // Study ID (optional - keep if needed for tracking)
-(0032,0012)                         // Study ID Issuer
-(0032,1033)                         // Requesting Service

// Visit/Admission
-(0038,0010)                         // Admission ID
-(0038,0011)                         // Issuer of Admission ID
-(0038,0060)                         // Service Episode ID
-(0038,0061)                         // Issuer of Service Episode ID
-(0038,0500)                         // Patient State

// Additional Device IDs
-(0018,1002)                         // Device UID
-(0018,1004)                         // Plate ID
-(0018,1005)                         // Generator ID
-(0018,1007)                         // Cassette ID
-(0018,1008)                         // Gantry ID
-(0018,1020)                         // Software Version(s) (optional - keep if needed)

// Additional Descriptions/Comments
-(0018,1400)                         // Acquisition Device Processing Description
-(0018,4000)                         // Acquisition Comments
-(0018,9424)                         // Acquisition Protocol Description
-(0040,2400)                         // Imaging Service Request Comments

// Scheduled/Performed Procedure Info
-(0040,0001)                         // Scheduled Station AE Title
-(0040,0002)                         // Scheduled Procedure Step Start Date
-(0040,0003)                         // Scheduled Procedure Step Start Time
-(0040,0004)                         // Scheduled Procedure Step End Date
-(0040,0005)                         // Scheduled Procedure Step End Time
-(0040,0006)                         // Scheduled Performing Physician's Name
-(0040,0007)                         // Scheduled Procedure Step Description
-(0040,0009)                         // Scheduled Procedure Step ID
-(0040,0010)                         // Scheduled Station Name
-(0040,0011)                         // Scheduled Procedure Step Location
-(0040,0012)                         // Pre-Medication
-(0040,0241)                         // Performed Station AE Title
-(0040,0242)                         // Performed Station Name
-(0040,0243)                         // Performed Location
-(0040,0244)                         // Performed Procedure Step Start Date
-(0040,0245)                         // Performed Procedure Step Start Time
-(0040,0250)                         // Performed Procedure Step End Date
-(0040,0251)                         // Performed Procedure Step End Time
-(0040,0253)                         // Performed Procedure Step ID
-(0040,0275)                         // Request Attributes SQ

// Additional Person Names/Info
-(0010,2297)                         // Responsible Person
-(0010,2298)                         // Responsible Person Role
-(0010,2299)                         // Responsible Organization
-(0040,0555)                         // Acquisition Context SQ
-(0040,A073)                         // Verifying Observer Name
-(0040,A075)                         // Verifying Observer Identification Code SQ
-(0040,A123)                         // Person Name
-(0040,A027)                         // Verifying Organization

// Graphic/Overlay Data (may contain PHI)
-(0070,0001)                         // Graphic Annotation SQ
-(0088,0200)                         // Icon Image Sequence

// Patient Insurance
-(0010,0050)                         // Patient's Insurance Plan Code SQ
-(0010,0101)                         // Patient's Primary Language Code SQ
-(0010,1002)                         // Other Patient IDs SQ

// ============================================================================
// NOTE: The following tags are typically KEPT for research purposes:
// - (0008,0060) Modality
// - (0018,0015) Body Part Examined
// - All technical imaging parameters (TR, TE, Field Strength, etc.)
// - Image geometry and pixel data specifications
// - UIDs (handled separately by UID remapping if needed)
// ============================================================================
