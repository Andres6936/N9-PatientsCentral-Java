package edu.jabs.patientsCentral.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import edu.jabs.patientsCentral.domain.PatientsCentral;
import edu.jabs.patientsCentral.domain.NotExistsException;
import edu.jabs.patientsCentral.domain.Patient;

/**
 * This is the class used to verifie the methods of the PatientsCentral class
 */
public class PatientsCentralTest extends TestCase
{

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * It is the class where the tests are going to be performed
     */
    private PatientsCentral central;

    /**
     * The number of patients in each test
     */
    private int patientNumber;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It builds a new empty Patients central
     *
     */
    private void setupScenario1( )
    {
        central = new PatientsCentral( );
        patientNumber = 0;

    }

    /**
     * It builds a new Patients central with 10 patients
     *
     */
    private void setupScenario2( )
    {
        central = new PatientsCentral( );
        patientNumber = 10;
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            central.addPatientAtTheBeginning( new Patient( cont, "name" + cont, "St Patrick's Hospital" + cont, "The medical information" + cont, Patient.MALE ) );
        }
    }

    /**
     * It builds a new Patients central with 10 patients
     *
     */
    private void setupScenario3( )
    {
        central = new PatientsCentral( );
        patientNumber = 10;
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            central.addPatientAtTheEnd( new Patient( cont, "name" + cont, "St Lucie's Hospital" + cont, "The medical information" + cont, Patient.MALE ) );
        }
    }

    /**
     * It test the addition of patients. <br>
     * <b> Methods to test: </b> <br>
     * getPatients,addPatientAtTheBeginning. <br>
     * <b> Objective: </b> To test that the method adds patients at the beginning, patients successfully added to the list. <br>
     * <b> Expected Results: </b> <br>
     * 1. When a patient is added to an empty central it must be added without errors. <br>
     * 2. When a patient is added at the beginning of the list it must be the first one and no previously added patients must be lost. <br>
     */
    public void testAddAtTheBeginning( )
    {
        setupScenario1( );
        patientNumber = 11;
        ArrayList patients = new ArrayList( );
        Patient patient;
        Patient aux;
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            patient = new Patient( cont, "name" + cont, "St Joe's Hospital" + cont, "The medical information of the patient " + cont, Patient.FEMALE );
            patients.add( patient );
        }
        // It adds the patients and verifies that they are successfully added
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            patient = ( Patient )patients.get( cont );
            central.addPatientAtTheBeginning( new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );
            ArrayList thePatients = central.getPatients( );
            aux = ( Patient )thePatients.get( 0 );

            // It verifies that the information is correct
            assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getCode( ) );
            assertEquals( "The addition was not made successfully", patient.getHospital( ), aux.getHospital( ) );
            assertEquals( "The addition was not made successfully", patient.getMedicalInformation( ), aux.getMedicalInformation( ) );
            assertEquals( "The addition was not made successfully", patient.getName( ), aux.getName( ) );
            assertEquals( "The addition was not made successfully", patient.getGender( ), aux.getGender( ) );

            // It verifies that the number of patients is correct
            assertEquals( "The number of patients is not correct", cont + 1, thePatients.size( ) );
        }
    }

    /**
     * It test the addition of patients. <br>
     * <b> Methods to test: </b> <br>
     * getPatients,addPatientAtTheEnd. <br>
     * <b> Objective: </b> To test that the method adds patients at the end, patients successfully added to the list. <br>
     * <b> Expected Results: </b> <br>
     * 1. When a patient is added to an empty central it must be added without errors. <br>
     * 2. When a patient is added at the beginning of the list it must be the last one and no previously added patients must be lost. <br>
     */
    public void testAddPatientAtTheEnd( )
    {
        setupScenario1( );
        patientNumber = 11;
        ArrayList patients = new ArrayList( );
        Patient patient;
        Patient aux;
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            patient = new Patient( cont, "pacient name" + cont, "St Joe's Hospital" + cont, "The medical information of the patient " + cont, Patient.MALE );
            patients.add( patient );
        }
        // It adds the patients and verifies that they are successfully added

        for( int cont = 0; cont < patientNumber; cont++ )
        {
            patient = ( Patient )patients.get( cont );
            central.addPatientAtTheEnd( new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );
            ArrayList thePatients = central.getPatients( );
            aux = ( Patient )thePatients.get( cont );

            // It verifies that the information is correct
            assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getCode( ) );
            assertEquals( "The addition was not made successfully", patient.getHospital( ), aux.getHospital( ) );
            assertEquals( "The addition was not made successfully", patient.getMedicalInformation( ), aux.getMedicalInformation( ) );
            assertEquals( "The addition was not made successfully", patient.getName( ), aux.getName( ) );
            assertEquals( "The addition was not made successfully", patient.getGender( ), aux.getGender( ) );

            // It verifies that the number of patients is correct
            assertEquals( "The number of patients is not correct", cont + 1, thePatients.size( ) );
        }

    }

    /**
     * It test the addition of patients. <br>
     * <b> Methods to test: </b> <br>
     * getPatients,addPatientBefore, findPatient, addPatientAtTheBeginning. <br>
     * <b> Objective: </b> To test that the method addPatientBefore, patients successfully added to the list. <br>
     * <b> Expected Results: </b> <br>
     * 1. When a patient is added to an empty central it must be added without errors. <br>
     * 2. When a patient 'A' is added before a patient 'B' previously added, patient 'A' must be before 'B' in the list. <br>
     * 3. When a patient is added it must be registered
     */
    public void testAddPatientBefore( )
    {
        setupScenario1( );
        patientNumber = 11;
        ArrayList patients = new ArrayList( );
        Patient patient;
        Patient aux;
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            patient = new Patient( cont, "pacient name" + cont, "St Joe's Hospital" + cont, "The medical information of the patient " + cont, Patient.MALE );
            patients.add( patient );
        }

        // The first patient is added
        patient = ( Patient )patients.get( 0 );
        central.addPatientAtTheBeginning( new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );
        // It adds the patients and verifies that they are added successfully
        int ced = 0;

        try
        {
            for( int cont = 1; cont < patientNumber - 1; cont++ )
            {
                patient = ( Patient )patients.get( cont );
                central.addPatientBefore( ced, new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );
                aux = central.findPatient( patient.getCode( ) );

                // It verifies that the information is correct
                assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getCode( ) );
                assertEquals( "The addition was not made successfully", patient.getHospital( ), aux.getHospital( ) );
                assertEquals( "The addition was not made successfully", patient.getMedicalInformation( ), aux.getMedicalInformation( ) );
                assertEquals( "The addition was not made successfully", patient.getName( ), aux.getName( ) );
                assertEquals( "The addition was not made successfully", patient.getGender( ), aux.getGender( ) );
                ced = patient.getCode( );
            }

            // It verifies that the patients are added in the correct order
            ArrayList patientsHospital = central.getPatients( );

            for( int cont = 0; cont < patientNumber - 1; cont++ )
            {
                patient = ( Patient )patientsHospital.get( cont );
                assertEquals( "The addition was not made successfully", patientNumber - 2 - cont, patient.getCode( ) );
            }

            // It adds a patient in the middle and verifies that is was added successfully
            patient = ( Patient )patients.get( patientNumber - 1 );
            ced = 5;
            central.addPatientBefore( ced, new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );

            aux = central.findPatient( ced + 1 );
            assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getNext( ).getCode( ) );
            assertEquals( "The addition was not made successfully", ced, aux.getNext( ).getNext( ).getCode( ) );
            aux = aux.getNext( );
            assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getCode( ) );
            assertEquals( "The addition was not made successfully", patient.getHospital( ), aux.getHospital( ) );
            assertEquals( "The addition was not made successfully", patient.getMedicalInformation( ), aux.getMedicalInformation( ) );
            assertEquals( "The addition was not made successfully", patient.getName( ), aux.getName( ) );
            assertEquals( "The addition was not made successfully", patient.getGender( ), aux.getGender( ) );
        }
        catch( NotExistsException e )
        {
            fail( "The exception should not be thrown" );
        }
    }

    /**
     * It test that the addition of patients throws an error when the code of the next patient does not exist. <br>
     * <b> MMethods to test: </b> <br>
     * addPatientBefore. <br>
     * <b> Objective: </b> To test that the method addPatientBefore throws an exception when a patient is added before a none existing patient. <br>
     * <b> Expected results: </b> <br>
     * 1. An exception must be thrown when a patient is added before a none existing one.
     */
    public void testAddPatientBeforeError( )
    {
        setupScenario2( );

        // It tries to add a patient where there is a none existing patient
        try
        {
            central.addPatientBefore( 1000, new Patient( patientNumber, "john doe", "St Mary's", "The patient has chronic headache", Patient.MALE ) );
            fail( "The patient should not be added" );
        }
        catch( NotExistsException e1 )
        {
            // It is verified that the patient list has not grown up
            assertEquals( "The patient should not be added", patientNumber, central.getPatients( ).size( ) );
        }
    }

    /**
     * It test the addition of patients. <br>
     * <b> Methods to test: </b> <br>
     * addPatientAfter, findPatient. <br>
     * <b> Objective: </b> To test that the method addPatientAfter, patients successfully added to the list. <br>
     * <b> Expected Results: </b> <br>
     * 1. When a patient is added to an empty central it must be added without errors. <br>
     * 2. When a patient 'A' is added after a patient 'B' previously added, patient 'A' must be after 'B' in the list. <br>
     * 3. When a patient is added it must be registered
     */
    public void testAddPatientAfter( )
    {
        setupScenario1( );
        patientNumber = 11;
        ArrayList patients = new ArrayList( );
        Patient patient;
        Patient aux;
        for( int cont = 0; cont < patientNumber; cont++ )
        {
            patient = new Patient( cont, "pacient name" + cont, "St Joe's Hospital" + cont, "The medical information of the patient " + cont, Patient.MALE );
            patients.add( patient );
        }

        // It adds the first patient to have an element to insert after
        patient = ( Patient )patients.get( 0 );
        central.addPatientAtTheBeginning( new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );

        // It adds the patients and verifies that they are added successfully
        int ced = 0;
        try
        {
            for( int cont = 1; cont < patientNumber - 1; cont++ )
            {
                patient = ( Patient )patients.get( cont );
                central.addPatientAfter( ced, new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );
                aux = central.findPatient( patient.getCode( ) );

                // It verifies that the information is correct
                assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getCode( ) );
                assertEquals( "The addition was not made successfully", patient.getHospital( ), aux.getHospital( ) );
                assertEquals( "The addition was not made successfully", patient.getMedicalInformation( ), aux.getMedicalInformation( ) );
                assertEquals( "The addition was not made successfully", patient.getName( ), aux.getName( ) );
                assertEquals( "The addition was not made successfully", patient.getGender( ), aux.getGender( ) );
                ced = patient.getCode( );
            }

            // It verifies that the patients are added in correct order
            ArrayList patientsHospital = central.getPatients( );

            for( int cont = 0; cont < patientNumber - 1; cont++ )
            {
                patient = ( Patient )patientsHospital.get( cont );
                assertEquals( "The addition was not made successfully", cont, patient.getCode( ) );
            }

            // It adds a patient in the middle and it verifies that it has been added successfully
            patient = ( Patient )patients.get( patientNumber - 1 );
            ced = 3;
            central.addPatientAfter( ced, new Patient( patient.getCode( ), patient.getName( ), patient.getHospital( ), patient.getMedicalInformation( ), patient.getGender( ) ) );

            aux = central.findPatient( ced );
            assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getNext( ).getCode( ) );
            aux = aux.getNext( );
            assertEquals( "The addition was not made successfully", patient.getCode( ), aux.getCode( ) );
            assertEquals( "The addition was not made successfully", patient.getHospital( ), aux.getHospital( ) );
            assertEquals( "The addition was not made successfully", patient.getMedicalInformation( ), aux.getMedicalInformation( ) );
            assertEquals( "The addition was not made successfully", patient.getName( ), aux.getName( ) );
            assertEquals( "The addition was not made successfully", patient.getGender( ), aux.getGender( ) );
        }
        catch( NotExistsException e )
        {

            fail( "The exception should not be thrown" );
        }
    }

    /**
     * It test that the addition of patients throws an error when the code of the next patient does not exist. <br>
     * <b> Methods to test: </b> <br>
     * addPatientAfter. <br>
     * <b> Objective: </b> To test that the method addPatientAfter throws an exception when a patient is added after a none existing patient. <br>
     * <b> Expected results: </b> <br>
     * 1. An exception must be thrown when a patient is added after a none existing one.
     */
    public void testAddPatientAfterError( )
    {
        setupScenario2( );

        // It tries to add a patient where there is a none existing patient
        try
        {
            central.addPatientAfter( 1000, new Patient( patientNumber, "john doe", "St john's Hospital", "The patient has chikenpox", Patient.FEMALE ) );
            assertTrue( "The patient should not be added", false );
        }
        catch( NotExistsException e1 )
        {
            // It is verified that the patient list has not grown up
            assertEquals( "The patient should not be added", patientNumber, central.getPatients( ).size( ) );
        }
    }

    /**
     * It tests the deletion a patient. <br>
     * <b> Methods to test: </b> <br>
     * deletePatient, findPatient. <br>
     * <b> Objective: </b> To test that the method deletePatient() successfully deletes the patients. <br>
     * <b> Expected Results: </b> <br>
     * 1. After deleting a patient it should not be found. <br>
     * 2. After deleting a patient the list of patients must be successfully linked. <br>
     * 3. When a patient is added it must be registered
     */
    public void testDeletePatient( )
    {
        setupScenario3( );

        // It deletes the head of the list
        try
        {
            central.deletePatient( 0 );
            Patient patient = central.findPatient( 0 );

            assertNull( "The patient should not be found", patient );
            assertEquals( "The number of the patient is not correct", patientNumber - 1, central.getPatients( ).size( ) );

            // It deletes an element of the middle
            central.deletePatient( 5 );
            patient = central.findPatient( 5 );
            assertNull( "The patient should not be found", patient );
            assertEquals( "The number of the patient is not correct", patientNumber - 2, central.getPatients( ).size( ) );

            // It deletes the end of the list
            central.deletePatient( patientNumber - 1 );
            patient = central.findPatient( patientNumber - 1 );
            assertNull( "The patient should not be found", patient );
            assertEquals( "The number of the patient is not correct", patientNumber - 3, central.getPatients( ).size( ) );

            // It verifies that the list is successfully linked after deleting patients
            ArrayList patients = central.getPatients( );
            for( int cont = 0; cont < patientNumber - 3; cont++ )
            {
                patient = ( Patient )patients.get( cont );
                if( cont < 4 )
                {

                    assertEquals( "The deletion was not successful", cont + 1, patient.getCode( ) );
                }
                else if( cont > 4 )
                {
                    assertEquals( "The deletion was not successful", cont + 2, patient.getCode( ) );
                }
            }

            // It deletes the other patient and verifies that the deletion was successful
            patientNumber -= 3;
            for( int cont = 0; cont < patients.size( ) - 1; cont++ )
            {
                if( cont != 5 )
                {
                    patient = ( Patient )patients.get( cont + 1 );
                    central.deletePatient( patient.getCode( ) );
                    patientNumber--;
                    assertEquals( "The number of patient is not correct", patientNumber, central.getPatients( ).size( ) );
                }

            }

        }
        catch( NotExistsException e )
        {
            fail( "The exception should not be thrown" );
        }
    }

    /**
     * It test that the deletion of an none existent patient throws an exception. <br>
     * <b> Methods to test: </b> <br>
     * deletePatient. <br>
     * <b> Objective: </b> To test that the method deletePatientt() throws an exception when an none existent patient is deleted. <br>
     * <b> Expected results: </b> <br>
     * 1. An exception must be thrown
     */
    public void testEliminarPacienteError( )
    {
        setupScenario1( );

        try
        {
            central.deletePatient( 1000 );
            assertTrue( "No patient should be deleted", false );
        }
        catch( NotExistsException e )
        {

            // It verifies that no patient has been deleted
            assertEquals( "No patient should be deleted", patientNumber, central.getPatients( ).size( ) );

            setupScenario2( );

            try
            {
                central.deletePatient( 1000 );
                assertTrue( "No patient should be deleted", false );
            }
            catch( NotExistsException e1 )
            {
                // It verifies that no patient has been deleted
                assertEquals( "No patient should be deleted", patientNumber, central.getPatients( ).size( ) );
            }

        }
    }

    /**
     * It test that search of a patient. <br>
     * <b> Methods to test: </b> <br>
     * findPatient. <br>
     * <b> Objective: </b> To test that the method findPatient() is able to find the patients that are on the list<br>
     * <b> Expected results: </b> <br>
     * 1. When searching for an existing patient it should be found. <br>
     * 2. When searching for an none existing patient it should not be found.
     */
    public void testfindPatient( )
    {
        setupScenario2( );

        // It searches for a patient that is in the middle of the list
        Patient paciente = central.findPatient( patientNumber - 6 );
        assertNotNull( "The patient should have been found", paciente );
        assertEquals( "The patient was not searched successfully", patientNumber - 6, paciente.getCode( ) );

        // It searches for the last patient
        paciente = central.findPatient( patientNumber - 1 );
        assertNotNull( "The patient should have been found", paciente );
        assertEquals( "The patient was not searched successfully", patientNumber - 1, paciente.getCode( ) );

        paciente = central.findPatient( 1000 );
        assertNull( "The patient should not have been found", paciente );
    }

    /**
     * It test that the search for a previous patient is successful. <br>
     * <b> Methods to test: </b> <br>
     * findPrevious. <br>
     * <b> Objective: </b> To test that the method findPrevious() is able to find a previous patient of a given patient. <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for a patient in an empty list null should be returned. <br>
     * 2. When searching for the previous patient of the first one, null must be returned. <br>
     * 3. When searching for a patient in the middle, the previous one must be returned. <br>
     * 4. When a patient previous to the last one is searched, it should be found. <br>
     * 5. When searching for a previous patient to a patient that does not exist, null should be returned.
     */
    public void testFindPrevious( )
    {

        setupScenario1( );

        // It verifies that null is returned when the list is empty
        Patient patient = central.findPrevious( 0 );
        assertNull( "The previous patient should not exist", patient );

        setupScenario2( );

        // It verifies that null is returned when the previous patient to the first one is searched
        patient = central.findPrevious( patientNumber - 1 );
        assertNull( "The previous patient should not exist", patient );

        // It verifies that the previous patient to a middle one is successfully found
        patient = central.findPrevious( patientNumber / 2 );
        assertEquals( "The previous patient is not correct", ( patientNumber / 2 ) + 1, patient.getCode( ) );

        // It verifies that the previous patient to the last one id successfully found
        patient = central.findPrevious( 0 );
        assertEquals( "The previous patient is not correct", 1, patient.getCode( ) );

        // It verifies that null is returned when searching for a none existent patient
        patient = central.findPrevious( 1000 );
        assertNull( "The previous patient should not exist", patient );

    }

    /**
     * It tests that the search for a previous patient to another one is successful. <br>
     * <b> Methods to test: </b> <br>
     * findLast. <br>
     * <b> Objective: </b> To test that the method findLast() is able to find the last patient on the list. <br>
     * <b> Expected results: </b> <br>
     * 1. When searching for the last patient on an empty list, null should be returned. <br>
     * 2. hen searching for the last patient on none an empty list it should be found.
     */
    public void testFindLast( )
    {

        setupScenario1( );

        // It verifies that null is returned when the list is empty
        Patient patient = central.findLast( );
        assertNull( "The last patient should not exist", patient );

        setupScenario2( );

        // It verifies that the last patient is returned successfully
        patient = central.findLast( );
        assertEquals( "The last patient is not correct", 0, patient.getCode( ) );
    }

}
