package edu.jabs.patientsCentral.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import edu.jabs.patientsCentral.domain.Patient;

/**
 * Panel to manage patient information
 */
public class PatientInformationPanel extends JPanel
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    /**
     * It represents the male gender
     */
    public static final String MALE = "Male";

    /**
     * It represents the female gender
     */
    public static final String FEMALE = "Female";

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Text field for the patient's name
     */
    private JTextField txtName;

    /**
     * Text field for the patient's code
     */
    private JTextField txtCode;

    /**
     * ComboBox for the patient's hospital
     */
    private JComboBox comboHospitals;

    /**
     * Button for patient's gender
     */
    private JRadioButton buttonMale;

    /**
     * Button for patient's gender
     */
    private JRadioButton buttonFemale;

    /**
     * Area for the patient's medical information
     */
    private JTextArea areaMedicalInformation;

    /**
     * Group for the gender buttons
     */
    private ButtonGroup group;

    /**
     * Label for the Hospital
     */
    private JLabel hospitalLabel;

    /**
     * label for the gender
     */
    private JLabel genderLabel;

    /**
     * label for the medical information
     */
    private JLabel medicalInformationLabel;

    /**
     * label for the name
     */
    private JLabel nameLabel;

    /**
     * label for the code
     */
    private JLabel codeLabel;

    /**
     * Scroll bar for the medical information area
     */
    private JScrollPane medicalInformationScrollBar;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor del panel
     */
    public PatientInformationPanel( )
    {
        setLayout( new GridBagLayout( ) );
        setBorder( new TitledBorder( "Patient Information" ) );

        // Name of the patient
        nameLabel = new JLabel( "Name" );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( nameLabel, gbc );

        txtName = new JTextField( );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add( txtName, gbc );

        // Code of the patient
        codeLabel = new JLabel( "Code" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add( codeLabel, gbc );

        txtCode = new JTextField( );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        add( txtCode, gbc );

        // Hospital assigned to the patient
        hospitalLabel = new JLabel( "Hospital" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add( hospitalLabel, gbc );

        comboHospitals = new JComboBox( );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        add( comboHospitals, gbc );

        // Gender of the patient
        genderLabel = new JLabel( "Gender" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add( genderLabel, gbc );

        buttonMale = new JRadioButton( MALE );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        buttonMale.setSelected( true );
        add( buttonMale, gbc );

        buttonFemale = new JRadioButton( FEMALE );
        gbc = new GridBagConstraints( );
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add( buttonFemale, gbc );

        group = new ButtonGroup( );
        group.add( buttonMale );
        group.add( buttonFemale );

        // Medical information of the patient
        medicalInformationLabel = new JLabel( "Medical Information" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add( medicalInformationLabel, gbc );

        areaMedicalInformation = new JTextArea( );
        areaMedicalInformation.setLineWrap( true );
        areaMedicalInformation.setWrapStyleWord( true );
        medicalInformationScrollBar = new JScrollPane( areaMedicalInformation );
        medicalInformationScrollBar.setPreferredSize( new Dimension( 200, 100 ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        add( medicalInformationScrollBar, gbc );

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It changes the hospital combo box information with the given list
     * @param hospitals The new list of hospitals. hopitals!=null
     */
    public void changeInformationComboHospital( ArrayList hospitals )
    {
        comboHospitals.removeAllItems( );

        for( int cont = 0; cont < hospitals.size( ); cont++ )
        {
            comboHospitals.addItem( hospitals.get( cont ) );
        }
        comboHospitals.setSelectedIndex( -1 );
    }

    /**
     * It enables the medical information area
     *
     */
    public void enableMedicalInformationArea( )
    {
        areaMedicalInformation.setEditable( true );
    }

    /**
     * It enables all the components of the panel
     *
     */
    public void enableComponents( )
    {
        txtCode.setEditable( true );
        txtName.setEditable( true );
        buttonFemale.setEnabled( true );
        buttonMale.setEnabled( true );
        areaMedicalInformation.setEditable( true );
        comboHospitals.setEnabled( true );
    }

    /**
     * It disables all the components of the panel
     *
     */
    public void disableComponents( )
    {
        txtCode.setEditable( false );
        txtName.setEditable( false );
        buttonFemale.setEnabled( false );
        buttonMale.setEnabled( false );
        areaMedicalInformation.setEditable( false );
        comboHospitals.setEnabled( false );
    }

    /**
     * It returns the code of the patient that is going to be inserted
     * @return The code of the patient to be inserted
     * @throws NumberFormatException If the code inserted by the user is not a number
     */
    public int getPatientCode( ) throws NumberFormatException
    {
        String string = txtCode.getText( );
        int ced = Integer.parseInt( string );

        return ced;
    }

    /**
     * It returns the name of the patient that is going to be inserted
     * @return The name of the patient that is going to be inserted
     */
    public String getPatientName( )
    {
        return txtName.getText( );
    }

    /**
     * It returns the hospital of the patient that is going to be inserted
     * @return The hospital of the patient that is going to be inserted
     */
    public String getPatientHospital( )
    {
        return ( String )comboHospitals.getSelectedItem( );
    }

    /**
     * It returns the gender of the patient that is going to be inserted
     * @return The gender of the patient that is going to be inserted
     */
    public int getPatientGender( )
    {
        return buttonFemale.isSelected( ) ? Patient.FEMALE : Patient.MALE;
    }

    /**
     * It returns the medical information of the patient that is going to be inserted
     * @return The medical information of the patient that is going to be inserted
     */
    public String getPatientMedicalInformation( )
    {
        return areaMedicalInformation.getText( );
    }

    /**
     * It displays the information of the patient
     * @param patient The patient which information is to be displayed - patient!=null
     */
    public void showPatientInformation( Patient patient )
    {
        txtName.setText( patient.getName( ) );
        txtCode.setText( Integer.toString( patient.getCode( ) ) );
        areaMedicalInformation.setText( patient.getMedicalInformation( ) );
        comboHospitals.setSelectedItem( patient.getHospital( ) );

        if( patient.getGender( ) == Patient.MALE )
        {
            buttonMale.setSelected( true );
        }
        else
        {
            buttonFemale.setSelected( true );
        }
    }
}
