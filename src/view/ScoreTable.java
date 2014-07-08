/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mapBuilder.Player;

class ScoreTable extends JFrame
 {
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	NonEditableTable table;
	private	JScrollPane scrollPane;

	// Constructor of main frame
	public ScoreTable()
	{
		// Set the frame characteristics
		setTitle( "Score Card" );
		setSize( 300, 200 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

                //create the table
		createTable(null);

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
                
	}

        public void createTable(Player[] players) {
        String[] playerColours = {"Blue", "Red", "Green", "Purple", "Yello"};
        if (players == null) {
            String columnNames[] = {"ID", "Points $", "Coins $", "Life %"};
            Object rowData[][] = {
                {"P0", "0", "0", "0"},
                {"P1", "0", "0", "0"},
                {"P2", "0", "0", "0"},
                {"P3", "0", "0", "0"},
                {"P4", "0", "0", "0"}};
            table = new NonEditableTable(rowData, columnNames);
        } else {
            for (int i = 0; i < 5; i++) {
                if (players[i] != null) {
                    Player p = players[i];
                    if (p.isOpponent()) {
                        table.setValueAt("P" + i + " [" + playerColours[i] + "]", i + 1, 0);
                    } else {
                        table.setValueAt("P" + i + " [User - " + playerColours[i] + "]", i + 1, 0);
                    }

                    table.setValueAt(p.getPoints(), i + 1, 1);
                    table.setValueAt(p.getCoins(), i + 1, 2);
                    table.setValueAt(p.getHealth(), i + 1, 3);
                }
            }
        }
       // table.add
        }
       /* 
	// Main entry point for this example
	public static void main( String args[] )
	{
		// Create an instance of the test application
		ScoreTable mainFrame	= new ScoreTable();
		mainFrame.setVisible( true );
	}
        */
}
