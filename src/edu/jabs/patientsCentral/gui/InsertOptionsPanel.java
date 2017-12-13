package edu.jabs.patientsCentral.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel with options for the insertion of a patient
 */
public class InsertOptionsPanel extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private final static String BEFORE = "Before";

    private final static String AFTER = "After";

    private final static String FIRST = "First";

    private final static String LAST = "Last";

    private final static String CONTINUE = "Continue";

    private final static String CANCEL = "Cancel";

    // -----------------------------------------------------------------
    // Fields
    // -----------------------------------------------------------------

    /**
     * Reference to the dialogue in which the panel is
     */
    private InsertOptionsDialogue dialogue;

    // -----------------------------------------------------------------
    // GUI Fields
    // -----------------------------------------------------------------

    /**
     * Option to add the first patient
     */
    private JRadioButton buttonFirst;

    /**
     * Option to add the last patient
     */
    private JRadioButton buttonLast;

    /**
     * Option to add the patient before another patient
     */
    private JRadioButton buttonBefore;

    /**
     * Option to add the patient after another patient
     */
    private JRadioButton buttonAfter;

    /**
     * Text field to insert the patient code before the addition is going to be made
     */
    private JTextField textoBefore;

    /**
     * Text field to insert the patient code after the addition is going to be made
     */
    private JTextField textoAfter;

    /**
     * Group for the gender buttons
     */
    private ButtonGroup group;

    /**
     * Button to continue with the addition
     */
    private JButton botonContinue;

    /**
     * Button to cancel the addition
     */
    private JButton botonCancel;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Panel constructor
     * @param diag Options Dialogue
     */
    public InsertOptionsPanel( InsertOptionsDialogue diag )
    {
        dialogue = diag;
        setBorder( new TitledBorder( "Options to add the Patient" ) );

        setLayout( new GridBagLayout( ) );

        // Button to add at the beginning
        buttonFirst = new JRadioButton( "At the beginning" );
        buttonFirst.addActionListener( this );
        buttonFirst.setActionCommand( FIRST );
        buttonFirst.setSelected( true );
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add( buttonFirst, gbc );

        // Button to add at the end
        buttonLast = new JRadioButton( "At the end" );
        buttonLast.addActionListener( this );
        buttonLast.setActionCommand( LAST );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add( buttonLast, gbc );

        // Button to add before another patient
        buttonBefore = new JRadioButton( "Before the patient with code" );
        buttonBefore.addActionListener( this );
        buttonBefore.setActionCommand( BEFORE );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 5, 0 );
        add( buttonBefore, gbc );

        textoBefore = new JTextField( "" );
        textoBefore.setEnabled( false );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 5, 0 );
        gbc.gridwidth = 2;
        add( textoBefore, gbc );

        // Button to add after another patient
        buttonAfter = new JRadioButton( "After the patient with code" );
        buttonAfter.addActionListener( this );
        buttonAfter.setActionCommand( AFTER );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets( 0, 0, 10, 0 );
        gbc.fill = GridBagConstraints.BOTH;
        add( buttonAfter, gbc );

        textoAfter = new JTextField( "" );
        textoAfter.setEnabled( false );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets( 0, 0, 10, 0 );
        gbc.gridwidth = 2;
        gbc.ipadx = 100;
        add( textoAfter, gbc );

        // Group buttons
        group = new ButtonGroup( );
        group.add( buttonBefore );
        group.add( buttonAfter );
        group.add( buttonFirst );
        group.add( buttonLast );

        // Panel for buttons
        JPanel buttonsPanel = new JPanel( );
        buttonsPanel.setLayout( new GridBagLayout( ) );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add( buttonsPanel, gbc );

        // Button to perform the addition
        botonContinue = new JButton( );
        botonContinue.addActionListener( this );
        botonContinue.setActionCommand( CONTINUE );
        botonContinue.setIcon( new ImageIcon( "data/continue.gif" ) );
        botonContinue.setToolTipText( "Continue" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets( 0, 0, 0, 10 );
        buttonsPanel.add( botonContinue, gbc );

        // Button to cancel the addition
        botonCancel = new JButton( );
        botonCancel.addActionListener( this );
        botonCancel.setActionCommand( CANCEL );
        botonCancel.setIcon( new ImageIcon( "data/cancel.gif" ) );
        botonCancel.setToolTipText( "Cancel" );
        gbc = new GridBagConstraints( );
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        buttonsPanel.add( botonCancel, gbc );

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Disable panel fields
     */
    private void disableTextFields( )
    {
        textoBefore.setEnabled( false );
        textoAfter.setEnabled( false );
    }

    /**
     * Clean the panel fields
     */
    private void cleanTextFields( )
    {
        textoBefore.setText( "" );
        textoAfter.setText( "" );
    }

    /**
     * It returns the form of insertion selected by the user
     * @return the selected form of insertion
     */
    public int getFormInsertion( )
    {
        int form = -1;
        if( buttonBefore.isSelected( ) )
        {
            form = PatientsCentralGUI.BEFORE;
        }
        else if( buttonAfter.isSelected( ) )
        {
            form = PatientsCentralGUI.AFTER;
        }
        else if( buttonFirst.isSelected( ) )
        {
            form = PatientsCentralGUI.FIRST;
        }
        else if( buttonLast.isSelected( ) )
        {
            form = PatientsCentralGUI.END;
        }

        return form;
    }

    /**
     * It returns the patient's code in relation to the one where the insertion is to be made
     * @return the patient's code in relation to the one where the insertion is to be made. If the form of addition is first or last -1 is returned.
     */
    public int getPatientCode( )
    {
        int code = -1;
        if( buttonBefore.isSelected( ) )
        {
            code = Integer.parseInt( textoBefore.getText( ) );
        }
        else if( buttonAfter.isSelected( ) )
        {
            code = Integer.parseInt( textoAfter.getText( ) );
        }
        return code;
    }

    /**
     * Handling button events
     * @param e Event generated by the action - e != null.
     */
    public void actionPerformed( ActionEvent e )
    {

        String command = e.getActionCommand( );

        if( command.equals( CONTINUE ) )
        {
            dialogue.continuePatientAddition( );
        }
        else if( command.equals( CANCEL ) )
        {
            dialogue.cancel( );
        }
        else if( command.equals( FIRST ) )
        {
            disableTextFields( );
            cleanTextFields( );
        }
        else if( command.equals( LAST ) )
        {
            disableTextFields( );
            cleanTextFields( );
        }
        else if( command.equals( BEFORE ) )
        {
            disableTextFields( );
            cleanTextFields( );
            textoBefore.setEnabled( true );
        }
        else if( command.equals( AFTER ) )
        {
            disableTextFields( );
            cleanTextFields( );
            textoAfter.setEnabled( true );
        }
    }

}