package edu.jabs.patientsCentral.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Dialogue with options for inserting a patient
 */
public class InsertOptionsDialogue extends JDialog
{

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the main application window
     */
    private PatientsCentralGUI principal;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Panel with options
     */
    private InsertOptionsPanel optionsPanel;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Dialogue constructor
     * @param window Reference to the main application window - window!=null
     */
    public InsertOptionsDialogue( PatientsCentralGUI window )
    {
        super( window, true );
        principal = window;
        setLayout( new BorderLayout( ) );
        setResizable( false );
        setTitle( "Patients Central" );
        setPreferredSize( new Dimension( 339, 197 ) );

        // Panel with options
        optionsPanel = new InsertOptionsPanel( this );
        add( optionsPanel, BorderLayout.NORTH );

        pack( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * It cancels the addition of a patient closing the dialogue
     */
    public void cancel( )
    {
        dispose( );
    }

    /**
     * It shows the dialogue to continue with the patient addition
     */
    public void continuePatientAddition( )
    {
        try
        {
            int form = optionsPanel.getFormInsertion( );
            int code = optionsPanel.getPatientCode( );
            dispose( );
            principal.showInsertPatientDialogue( form, code );
        }
        catch( NumberFormatException e )
        {
            JOptionPane.showMessageDialog( this, "The patient code must be a numeric value", "Patients Addition", JOptionPane.ERROR_MESSAGE );
        }
    }

}
