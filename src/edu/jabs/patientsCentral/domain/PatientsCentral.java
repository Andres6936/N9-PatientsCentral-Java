package edu.jabs.patientsCentral.domain;

import java.util.ArrayList;

/**
 * This class represents the central where the patients list is managed <br>
 * <b>inv:</b> <br>
 * numPatients == Patients list length <br>
 * The patient codes are unique in the central <br>
 * hospitalList != null
 */
public class PatientsCentral
{
    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * First patient on the list
     */
    private Patient first;

    /**
     * Number of patients in the central
     */
    private int numPatients;

    /**
     * Vector of hospitals managed by the central
     */
    private ArrayList hospitalList;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * It builds a new central without patients and with a predefined list of hospitals
     */
    public PatientsCentral( )
    {
        first = null;
        numPatients = 0;

        hospitalList = new ArrayList( );
        hospitalList.add( "St Joseph's Hospital" );
        hospitalList.add( "St Mary's Hospital" );
        hospitalList.add( "St Patrick's Hospital" );
        hospitalList.add( "St Lucie's Hospital" );
        hospitalList.add( "South Bay Hospital" );
        hospitalList.add( "Other" );

        invariantChecking( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It returns the number of patients on the list
     * @return the number of patients
     */
    public int getNumberPatients( )
    {
        return numPatients;
    }

    /**
     * It adds a patients at the beginning of the list
     * @param pac THe patient to be added at the beginning of the list. <br>
     *        pac!=null and there is no other patient with the same code
     */
    public void addPatientAtTheBeginning( Patient pac )
    {
        if( first == null ) // It builds the head if it does not exist
        {
            first = pac;
        }
        else
        // It adds the patient at the beginning of the list
        {
            pac.setNext( first );
            first = pac;
        }
        numPatients++;
        invariantChecking( );
    }

    /**
     * It adds a patient at the end of the list.  If the list is empty, the patient is the first
     * @param pac The patient to be added at the end of the list. <br>
     *        pac!=null and there is no other patient with the same code
     */
    public void addPatientAtTheEnd( Patient pac )
    {
        if( first == null ) // If the head does not exists, the patient is the first
        {
            first = pac;
        }
        else
        {
            // It searches for the last patient on the list
            Patient p = findLast( );

            // It adds the patient after the last patient
            p.insertAfter( pac );
        }
        numPatients++;
        invariantChecking( );
    }

    /**
     * It adds the patient to the list before the patient with the given code. <br>
     * <b> pre: </b> first!= null. <br>
     * @param cod The patient code next to the new patient will be added.
     * @param pac The patient to be added. <br>
     *        pac!=null and there is no other patient with the same code
     * @throws NotExistsException If the next patient is not registered.
     */
    public void addPatientBefore( int cod, Patient pac ) throws NotExistsException
    {
        if( first == null )
            throw new NotExistsException( cod );
        else if( cod == first.getCode( ) )
        {
            pac.setNext( first );
            first = pac;
        }
        else
        {
            Patient previous = findPrevious( cod );
            if( previous == null )
                throw new NotExistsException( cod );
            previous.insertAfter( pac );
        }
        numPatients++;
        invariantChecking( );
    }

    /**
     * It adds the patient to the list after the patient with the given code. <br>
     * @param cod The patient code after which the new patient will be added.
     * @param pac The patient to be added. <br>
     *        pac!=null and there is no other patient with the same code
     * @throws NotExistsException If the previous patient is not registered.
     */
    public void addPatientAfter( int cod, Patient pac ) throws NotExistsException
    {
        Patient previous = findPatient( cod );

        if( previous == null ) // If the patient after the addition that is made is not registered, the exception is thrown
        {
            throw new NotExistsException( cod );
        }
        else
        // The patient is added
        {
            previous.insertAfter( pac );
        }
        numPatients++;
        invariantChecking( );
    }

    /**
     * It searches for the patient with the given code.
     * @param code The patient code to search
     * @return The patient with the given code. If the patient does not exists it returns null
     */
    public Patient findPatient( int code )
    {
        Patient actual = first;
        while( actual != null && actual.getCode( ) != code )
        {
            actual = actual.getNext( );
        }
        return actual;
    }

    /**
     * It searches the previous patient to the patient of the given code
     * @param cod The previous patient to the given patient code
     * @return The previous patient to the given patient code. It returns null if the patient with the given code does not exist or if it the first on the list
     */
    public Patient findPrevious( int cod )
    {
        Patient previous = null;
        Patient actual = first;

        while( actual != null && actual.getCode( ) != cod )
        {
            previous = actual;
            actual = actual.getNext( );
        }

        return actual != null ? previous : null;
    }

    /**
     * It returns the last patient on the list
     * @return The last patient on the list. If the list is empty, it returns null
     */
    public Patient findLast( )
    {
        Patient actual = first;

        if( actual != null )
        {
            while( actual.getNext( ) != null )
            {
                actual = actual.getNext( );
            }
        }
        return actual;
    }

    /**
     * It deletes the patient with the given code.
     * @param cod The patient code to be deleted. cod >= 0
     * @throws NotExistsException If the patient with the given code does not exists and it could not be deleted from the list
     */
    public void deletePatient( int cod ) throws NotExistsException
    {
        if( first == null )
            throw new NotExistsException( cod );
        else if( cod == first.getCode( ) )
        {
            // The patient is the first on the list
            first = first.getNext( );
        }
        else
        {
            // The patient is in the middle of the list
            Patient previous = findPrevious( cod );
            if( previous == null )
                throw new NotExistsException( cod );
            previous.removePatient( );
        }
        numPatients--;
        invariantChecking( );
    }

    /**
     * It returns the list of patients
     * @return List of patients
     */
    public ArrayList getPatients( )
    {
        ArrayList patients = new ArrayList( );
        Patient actual = first;
        while( actual != null )
        {
            patients.add( actual );
            actual = actual.getNext( );
        }
        return patients;
    }

    /**
     * It returns the list of hospitals managed by the central
     * @return the list of hospitals managed by the central
     */
    public ArrayList getHospitalsList( )
    {
        return hospitalList;
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * It verifies that the invariant is satisfied. If something fails, an AssertError is thrown. <br>
     * <b>inv: </b> <br>
     * numPatients == Patients list length <br>
     * The patient codes are unique in the central <br>
     * hospitalList != null
     */
    private void invariantChecking( )
    {
        assert numPatients == getLength( ) : "Invalid number of patients";
        assert !thereAreRepeatedCodes( ) : "The codes are not unique";
        assert hospitalList != null : "The list of hospitals can not be null";
    }

    /**
     * It returns the length of the list
     * @return the length of the list of patients
     */
    private int getLength( )
    {
        Patient actual = first;
        int length = 0;

        while( actual != null )
        {
            length++;
            actual = actual.getNext( );
        }
        return length;
    }

    /**
     * It tells if there are patients with the same code on the list
     * @return True if there are repeated codes or false otherwise
     */
    private boolean thereAreRepeatedCodes( )
    {
        boolean repeated = false;

        Patient actual = first;

        while( actual != null && !repeated )
        {
            Patient thePatient = actual.getNext( );

            while( thePatient != null && !repeated )
            {
                if( actual.getCode( ) == thePatient.getCode( ) )
                {
                    repeated = true;
                }
                thePatient = thePatient.getNext( );
            }

            actual = actual.getNext( );
        }

        return repeated;
    }

    // -----------------------------------------------------------------
    // Extension Points
    // -----------------------------------------------------------------

    /**
     * Extension Method 1
     * @return answer1
     */
    public String method1( )
    {
        return "Answer 1";
    }

    /**
     * Extension Method 2
     * @return answer2
     */
    public String method2( )
    {
        return "Answer 2";
    }

    /**
     * Extension Method 3
     * @return answer3
     */
    public String method3( )
    {
        return "Answer 3";
    }

    /**
     * Extension Method 4
     * @return answer4
     */
    public String method4( )
    {
        return "Answer 4";
    }

    /**
     * Extension Method 5
     * @return answer5
     */
    public String method5( )
    {
        return "Answer 5";
    }
}