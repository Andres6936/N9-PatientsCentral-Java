package edu.jabs.patientsCentral.domain;

/**
 * Exception to report that the patient is to be added is already registered in the central
 */
public class AlreadyExistsException extends Exception
{
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     *
     * It builds an exception with the patient code
     * @param code Patient code that is not registered in the central
     */
    public AlreadyExistsException( int code )
    {
        super( "The patient with code " + code + " is already registered" );
    }
}