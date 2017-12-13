package edu.jabs.patientsCentral.domain;

/**
 * THis class represents a hospital patient <br>
 * <b>inv:</b> <br>
 * code >= 0 <br>
 * name != null && name != "" <br>
 * hospital != null && hospital != "" <br>
 * medicalInformation != null <br>
 * gender == MALE or gender == FEMALE
 */
public class Patient
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant to represent a male
     */
    public final static int MALE = 1;

    /**
     * Constant to represent a female
     */
    public final static int FEMALE = 2;

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Patient's code
     */
    private int code;

    /**
     * Patient's name
     */
    private String name;

    /**
     * Patient's hospital
     */
    private String hospital;

    /**
     * Patient's medical information
     */
    private String medicalInfromation;

    /**
     * Patient's gender
     */
    private int gender;

    /**
     * The next patient on the list
     */
    private Patient next;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * It builds a patient
     * @param cod patient code - cod >= 0
     * @param nam Patient name - nam!=null y nam!=""
     * @param hosp Patient hospital - hosp!=null y hosp!=""
     * @param infoMed Patient medical information - infoMed!=null
     * @param gend Patient gender. Gender belongs to{ MALE, FEMALE }
     */
    public Patient( int cod, String nam, String hosp, String infoMed, int gend )
    {
        code = cod;
        name = nam;
        hospital = hosp;
        medicalInfromation = infoMed;
        gender = gend;
        next = null;

        invariantChecking( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It returns the patient code
     * @return THe patient code
     */
    public int getCode( )
    {
        return code;
    }

    /**
     * It returns the patient name
     * @return The patient name
     */
    public String getName( )
    {
        return name;
    }

    /**
     * It returns the patient hospital
     * @return The patient hospital
     */
    public String getHospital( )
    {
        return hospital;
    }

    /**
     * It returns the patient medical information
     * @return The patient medical information
     */
    public String getMedicalInformation( )
    {
        return medicalInfromation;
    }

    /**
     * It returns the patient gender
     * @return The patient gender
     */
    public int getGender( )
    {
        return gender;
    }

    /**
     * It returns the next patient on the list
     * @return The next patient on the list
     */
    public Patient getNext( )
    {
        return next;
    }

    /**
     * It sets the next patient
     * @param pac The next patient
     */
    public void setNext( Patient pac )
    {
        next = pac;
    }

    /**
     * It removes the next patient on the list. <br>
     * <b>pre: </b> It is not the last patient on the list <br>
     * <b>post: </b> The next patient was removed.
     */
    public void removePatient( )
    {
        next = next.next;

    }

    /**
     * It inserts a patient after the current one. <br>
     * <b>post: </b> The patient was inserted after the current one. <br>
     * @param pac The patient to be inserted - pac!=null
     */
    public void insertAfter( Patient pac )
    {
        pac.next = next;
        next = pac;
    }

    /**
     * It returns a String with the patient information
     * @return [ code ]: name
     */
    public String toString( )
    {
        return "[ " + code + " ]: " + name;
    }

    /**
     * It sets the patient medical information
     * @param information The new patient medical information
     */
    public void changeMedicalInformation( String information )
    {
        medicalInfromation = information;
    }

    // -----------------------------------------------------------------
    // Invariant
    // -----------------------------------------------------------------

    /**
     * It verifies the class invariant is satisfied. If something fails an AssertError is thrown. <br>
     * <b>inv: </b> <br>
     * code >= 0
     * name != null <br>
     * name != "" hospital != null <br>
     * hospital != "" <br>
     * medicalInformation != null <br>
     * gender == MALE or gender == FEMALE
     */
    private void invariantChecking( )
    {
        assert code >= 0 : "Invalid Code";
        assert name != null && !name.equals( "" ) : "Invalid Name";
        assert hospital != null && !hospital.equals( "" ) : "Invalid Hospital";
        assert medicalInfromation != null : "Invalid Medical information";
        assert gender == MALE || gender == FEMALE : "Invalid Gender";
    }
}
