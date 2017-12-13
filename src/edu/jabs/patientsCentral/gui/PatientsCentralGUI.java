package edu.jabs.patientsCentral.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.jabs.patientsCentral.domain.PatientsCentral;
import edu.jabs.patientsCentral.domain.NotExistsException;
import edu.jabs.patientsCentral.domain.Patient;
import edu.jabs.patientsCentral.domain.AlreadyExistsException;

/**
 * This is the main application window.
 */
public class PatientsCentralGUI extends JFrame
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    /**
     * Constant to add before
     */
    public static final int BEFORE = 0;

    /**
     * Constant to add after
     */
    public static final int AFTER = 1;

    /**
     * Constant to add at the beginning
     */
    public static final int FIRST = 2;

    /**
     * Constant to add at the end
     */
    public static final int END = 3;

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Main class of the world
     */
    private PatientsCentral central;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Extension Panel
     */
    private ExtensionPanel extensionPanel;

    /**
     * Panel with the list of patient
     */
    private PatientListPanel listPanel;

    /**
     * Panel with the image
     */
    private ImagePanel imagePanel;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Window constructor. <br>
     * <b>post: </b> The graphical components were initialized
     */
    public PatientsCentralGUI( )
    {
        // The main class is initialized
        central = new PatientsCentral( );

        // The layout is set
        setLayout( new GridBagLayout( ) );
        setSize( 370, 347 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Patients Central" );
        setResizable( false );

        // Panels
        imagePanel = new ImagePanel( );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( imagePanel, gbc );

        listPanel = new PatientListPanel( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add( listPanel, gbc );

        extensionPanel = new ExtensionPanel( this );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add( extensionPanel, gbc );

        setLocationRelativeTo( null );

        pack( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It show the dialogue to add a patient.
     * @param additionForm Where the patient is going to be added(At the beginning, at the end, before another patient or after another patient). additionForm belongs to
     *        {BEFORE, AFTER, FIRST, END}
     * @param code The patient's code in relation to which it will perform addition. Its value is only considered if additionFor is BEFORE or AFTER
     */
    public void showInsertPatientDialogue( int additionForm, int code )
    {
        AddPatientDialogue dialogue = new AddPatientDialogue( this, additionForm, code );
        dialogue.setLocationRelativeTo( this );
        dialogue.setVisible( true );
    }

    /**
     * If the list of patients is not empty, it shows the dialogue to select the addition type. If the list of
     * patients is empty, the dialogue to add a new patient is displayed
     *
     */
    public void showAddPatientOptionsDialogue( )
    {
        if( central.getNumberPatients( ) > 0 )
        {
            InsertOptionsDialogue dialogue = new InsertOptionsDialogue( this );
            dialogue.setLocationRelativeTo( this );
            dialogue.setVisible( true );
        }
        else
        {
            showInsertPatientDialogue( FIRST, -1 );
        }
    }

    /**
     * It adds a patient to the list
     * @param additionForm The type of addition (at the beginning, at the end, before another one or after another patient). additionForm
     *        belongs to {BEFORE, AFTER, FIRST, END}
     * @param codPatient The code of the patient where the new patient is going to be added
     * @param theCode The code of the patient - theCode>0
     * @param theName The name of the patient - theName!=null && theName!=""
     * @param theHospital The hospital of the patient - theHospital!=null && theHospital!=""
     * @param theMedicalInformation The medical information of the patient - theMedicalInformation!=null && theMedicalInformation!=""
     * @param theGender The gender of the patient.
     * @throws NotExistsException If the code of the patient where the addition is going to be made does not exist
     * @throws AlreadyExistsException
     * @throws AlreadyExistsException If there is a patient with the code that is going to be assigned to the new patient
     */
    public void addPatient( int additionForm, int codPatient, int theCode, String theName, String theHospital, String theMedicalInformation, int theGender ) throws NotExistsException, AlreadyExistsException
    {
        Patient patient = new Patient( theCode, theName, theHospital, theMedicalInformation, theGender );

        // It verifies that the patient does not exist
        if( central.findPatient( theCode ) != null )
        {
            throw new AlreadyExistsException( theCode );
        }
        else
        {
            switch( additionForm )
            {
                case BEFORE:
                    central.addPatientBefore( codPatient, patient );
                    break;
                case AFTER:
                    central.addPatientAfter( codPatient, patient );
                    break;
                case FIRST:
                    central.addPatientAtTheBeginning( patient );
                    break;
                case END:
                    central.addPatientAtTheEnd( patient );
                    break;
            }
        }
    }

    /**
     * It returns the list of hopitals managed by the central
     * @return the list of hopitals managed by the central
     */
    public ArrayList getHospitalsCentral( )
    {
        return central.getHospitalsList( );
    }

    /**
     * It updates the patient list
     *
     */
    public void updatePatientList( )
    {
        listPanel.updateList( central.getPatients( ) );
    }

    /**
     * It asks for the code of the patient to be searched, if it is found it displays its information in a dialogue.If it is not found a message is displayed.
     */
    public void searchPatient( )
    {
        String code = JOptionPane.showInputDialog( this, "Code:", "Patient Search", JOptionPane.QUESTION_MESSAGE );
        try
        {
            if( code != null )
            {
                int cod = Integer.parseInt( code );

                Patient patient = central.findPatient( cod );

                if( patient != null )
                {
                    showPatientInformation( patient );
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "The patient with code " + cod + " is not registered", "Patient Search", JOptionPane.INFORMATION_MESSAGE );
                }
            }
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "The code of the patient must be a numerical value", "Patient Search", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * It asks for the code of the patient to be deleted, a message is shown if the patient is deleted or not.
     */
    public void deletePatient( )
    {
        String code = JOptionPane.showInputDialog( this, "Code:", "Delete Patient", JOptionPane.QUESTION_MESSAGE );
        try
        {
            if( code != null )
            {
                int cod = Integer.parseInt( code );
                central.deletePatient( cod );
                updatePatientList( );
                JOptionPane.showMessageDialog( this, "The patient was deleted", "Delete Patient", JOptionPane.INFORMATION_MESSAGE );
            }
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "The code must be a numerical value", "Delete Patient", JOptionPane.ERROR_MESSAGE );
        }
        catch( NotExistsException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Delete Patient", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * It shows a dialogue with the information of a given patient
     * @param patient The patient to which the information is going to be displayed. patient!=null.
     */
    public void showPatientInformation( Patient patient )
    {
        ShowPatientInformationDialogue dialogo = new ShowPatientInformationDialogue( this, patient );
        dialogo.setLocationRelativeTo( this );
        dialogo.setVisible( true );
    }

    // -----------------------------------------------------------------
    // Extension Methods
    // -----------------------------------------------------------------

    /**
     * Extension Method 1
     */
    public void reqFuncOption1( )
    {
        String result = central.method1( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 2
     */
    public void reqFuncOption2( )
    {
        String result = central.method2( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 3
     */
    public void reqFuncOption3( )
    {
        String result = central.method3( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 4
     */
    public void reqFuncOption4( )
    {
        String result = central.method4( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extension Method 5
     */
    public void reqFuncOption5( )
    {
        String result = central.method5( );
        JOptionPane.showMessageDialog( this, result, "Answer", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main Method
    // -----------------------------------------------------------------

    /**
     * This method executes the application, building a new gui
     * @param args The arguments for the application. None is required.
     */
    public static void main( String[] args )
    {
        PatientsCentralGUI gui = new PatientsCentralGUI( );
        gui.setVisible( true );
    }
}
