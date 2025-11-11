#!/usr/bin/env python3
"""
Create a test DICOM file with all DICOM Basic Application Level
Confidentiality Profile tags populated for testing anonymization.
"""

try:
    import pydicom
    from pydicom.dataset import Dataset, FileDataset
    import datetime
    import numpy as np
except ImportError:
    print("Error: pydicom is required. Install with: pip install pydicom numpy")
    exit(1)

def create_test_dicom_with_phi():
    """Create a DICOM file with all PHI tags from PS3.15 Annex E populated"""

    # Create a new DICOM dataset
    file_meta = Dataset()
    file_meta.MediaStorageSOPClassUID = '1.2.840.10008.5.1.4.1.1.4'  # MR Image Storage
    file_meta.MediaStorageSOPInstanceUID = '1.2.3.4.5.6.7.8.9.0'
    file_meta.TransferSyntaxUID = '1.2.840.10008.1.2'  # Implicit VR Little Endian
    file_meta.ImplementationClassUID = '1.2.3.4.5.6.7.8.9.10'

    # Create the FileDataset instance
    ds = FileDataset("test_phi_dicom.dcm", {}, file_meta=file_meta, preamble=b"\0" * 128)

    # Set creation date/time
    dt = datetime.datetime.now()
    ds.ContentDate = dt.strftime('%Y%m%d')
    ds.ContentTime = dt.strftime('%H%M%S.%f')

    # === Patient Identification ===
    ds.PatientName = "Doe^John^M"
    ds.PatientID = "MRN123456789"
    ds.IssuerOfPatientID = "University Hospital"

    # === Patient Demographics ===
    ds.PatientBirthDate = "19800515"
    ds.PatientBirthTime = "143000"
    ds.PatientSex = "M"
    ds.PatientAge = "044Y"
    ds.PatientSize = "1.75"  # meters
    ds.PatientWeight = "75.0"  # kg
    ds.PatientAddress = "123 Main Street, Anytown, ST 12345, USA"
    ds.PatientTelephoneNumbers = "555-123-4567"

    # === Patient History ===
    ds.PatientComments = "Patient has history of hypertension and diabetes"
    ds.AdditionalPatientHistory = "Previous MRI scan 2023-01-15. No known allergies."
    ds.MedicalAlerts = "Pacemaker - MRI unsafe"
    ds.Allergies = "Penicillin, Shellfish"
    ds.SmokingStatus = "FORMER SMOKER"
    ds.PregnancyStatus = 4  # Not pregnant
    ds.EthnicGroup = "Caucasian"
    ds.Occupation = "Software Engineer"
    ds.CountryOfResidence = "USA"
    ds.RegionOfResidence = "California"

    # === Extended Patient Info ===
    ds.OtherPatientIDs = "ALT-ID-999888"
    ds.OtherPatientNames = "Smith^John (maiden name: Johnson)"
    ds.PatientBirthName = "Johnson^John^M"
    ds.PatientMotherBirthName = "Williams^Mary^A"
    ds.MilitaryRank = "Captain"
    ds.BranchOfService = "US Navy"
    ds.MedicalRecordLocator = "Building A, Floor 3, Room 301"
    ds.PatientReligiousPreference = "Catholic"

    # === Study/Visit Identification ===
    ds.StudyInstanceUID = '1.2.840.113619.2.55.3.123456789.123'
    ds.SeriesInstanceUID = '1.2.840.113619.2.55.3.123456789.456'
    ds.SOPInstanceUID = file_meta.MediaStorageSOPInstanceUID
    ds.AccessionNumber = "ACC2024-001234"
    ds.StudyID = "STU-2024-5678"

    # === Dates and Times ===
    ds.InstanceCreationDate = "20241025"
    ds.InstanceCreationTime = "093045"
    ds.StudyDate = "20241025"
    ds.StudyTime = "090000"
    ds.SeriesDate = "20241025"
    ds.SeriesTime = "091500"
    ds.AcquisitionDate = "20241025"
    ds.AcquisitionTime = "091530"
    ds.AcquisitionDateTime = "20241025091530"

    # === Institution/Device ===
    ds.InstitutionName = "University Medical Center"
    ds.InstitutionAddress = "456 University Ave, Medical District, ST 54321"
    ds.InstitutionalDepartmentName = "Radiology - MRI Unit 3"
    ds.StationName = "MRI-3T-SCANNER-02"
    ds.Manufacturer = "Siemens"
    ds.ManufacturerModelName = "MAGNETOM Skyra 3T"
    ds.DeviceSerialNumber = "12345-ABCDE"
    ds.SoftwareVersions = "syngo MR E11"

    # === Physician/Operator Names ===
    ds.ReferringPhysicianName = "Smith^Robert^MD"
    ds.ReferringPhysicianAddress = "789 Medical Plaza, Suite 200"
    ds.ReferringPhysicianTelephoneNumbers = "555-234-5678"
    ds.PerformingPhysicianName = "Johnson^Sarah^MD"
    ds.NameOfPhysiciansReadingStudy = "Williams^Michael^MD^PhD"
    ds.OperatorsName = "Tech^Mary^RT"
    ds.PhysiciansOfRecord = "Davis^James^MD"

    # === Procedure/Protocol Info ===
    ds.StudyDescription = "Brain MRI with and without contrast for headache evaluation"
    ds.SeriesDescription = "T1 MPRAGE Sagittal Pre-Contrast"
    ds.ProtocolName = "BRAIN_MRI_PROTOCOL_V3"
    ds.RequestingPhysician = "Brown^Patricia^MD"
    ds.RequestingService = "Neurology"
    ds.RequestedProcedureDescription = "MRI Brain for chronic headaches, rule out mass"
    ds.PerformedProcedureStepDescription = "MRI brain completed per protocol"
    ds.AdmittingDiagnosesDescription = "Chronic headaches, unspecified"

    # === Comments ===
    ds.StudyComments = "Patient cooperative. Contrast administered without complications."
    ds.ImageComments = "Good quality scan, minimal motion artifact"
    ds.AcquisitionComments = "Patient tolerated scan well"
    ds.CommentsOnThePerformedProcedureStep = "Procedure completed successfully at 09:30"
    ds.ImagingServiceRequestComments = "Rush - patient scheduled for neurology consult tomorrow"

    # === Visit/Admission Info ===
    ds.AdmissionID = "ADM-2024-98765"
    ds.ServiceEpisodeID = "EP-2024-11223"
    ds.PatientState = "Outpatient"

    # === Scheduled/Performed Procedure Info ===
    ds.ScheduledProcedureStepStartDate = "20241025"
    ds.ScheduledProcedureStepStartTime = "090000"
    ds.ScheduledProcedureStepEndDate = "20241025"
    ds.ScheduledProcedureStepEndTime = "093000"
    ds.ScheduledPerformingPhysicianName = "Johnson^Sarah^MD"
    ds.ScheduledProcedureStepDescription = "Brain MRI with contrast"
    ds.ScheduledStationName = "MRI-3T-SCANNER-02"
    ds.PerformedProcedureStepStartDate = "20241025"
    ds.PerformedProcedureStepStartTime = "091000"
    ds.PerformedProcedureStepEndDate = "20241025"
    ds.PerformedProcedureStepEndTime = "093000"
    ds.PerformedStationName = "MRI-3T-SCANNER-02"
    ds.PerformedLocation = "MRI Suite 3, Ground Floor"
    ds.PerformedStationAETitle = "MRI_SCANNER_02"

    # === Technical Parameters (these should remain) ===
    ds.Modality = "MR"
    ds.BodyPartExamined = "BRAIN"
    ds.SamplesPerPixel = 1
    ds.PhotometricInterpretation = "MONOCHROME2"
    ds.Rows = 256
    ds.Columns = 256
    ds.BitsAllocated = 16
    ds.BitsStored = 16
    ds.HighBit = 15
    ds.PixelRepresentation = 0

    # MR-specific technical parameters
    ds.RepetitionTime = 2300
    ds.EchoTime = 2.98
    ds.InversionTime = 900
    ds.MagneticFieldStrength = 3.0
    ds.ImagingFrequency = 123.25
    ds.ImagedNucleus = "1H"
    ds.FlipAngle = 9
    ds.SliceThickness = 1.0
    ds.SpacingBetweenSlices = 1.0

    # Create dummy pixel data (small image)
    pixel_array = np.random.randint(0, 4096, (256, 256), dtype=np.uint16)
    ds.PixelData = pixel_array.tobytes()

    # Set required values
    ds.is_little_endian = True
    ds.is_implicit_VR = True

    # Save the file
    output_file = "test_dicom_with_phi.dcm"
    ds.save_as(output_file, write_like_original=False)
    print(f"✓ Created test DICOM file: {output_file}")
    print(f"\nThis file contains all PHI tags from DICOM PS3.15 Annex E:")
    print(f"  - Patient Name: {ds.PatientName}")
    print(f"  - Patient ID: {ds.PatientID}")
    print(f"  - Birth Date: {ds.PatientBirthDate}")
    print(f"  - Institution: {ds.InstitutionName}")
    print(f"  - Referring Physician: {ds.ReferringPhysicianName}")
    print(f"  - Study Date: {ds.StudyDate}")
    print(f"  - And ~60+ more PHI tags...")
    print(f"\nUse this file to test your anonymization script!")

    return output_file

if __name__ == "__main__":
    create_test_dicom_with_phi()
