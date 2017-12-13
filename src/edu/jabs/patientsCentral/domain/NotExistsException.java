package edu.jabs.patientsCentral.domain;

/**
 * Exception generated when a patient with a given code does not exist
 */
public class NotExistsException extends Exception
{
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * It builds an exception with the patient code
     * @param code Patient code that is not registered in the central
     */
    public NotExistsException( int code )
    {
        super( "The patient with code " + code + " is not registered" );
    }
}
