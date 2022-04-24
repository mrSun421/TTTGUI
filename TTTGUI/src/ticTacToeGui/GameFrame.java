package ticTacToeGui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ActionListener
{
	JButton[][] buttons = { { new JButton(" "), new JButton(" "), new JButton(" ") },
			{ new JButton(" "), new JButton(" "), new JButton(" ") },
			{ new JButton(" "), new JButton(" "), new JButton(" ") } };

	HashMap<JButton, Integer[]> buttonCoordMap = new HashMap<>();

	int playCounter = 0;
	
	public GameFrame()
	{
		for (int i = 0; i < buttons.length; i++)
		{
			for (int j = 0; j < buttons[i].length; j++)
			{
				buttonCoordMap.put(buttons[i][j], new Integer[] { i, j });
				buttons[i][j].addActionListener(this);
				buttons[i][j].setFocusable(false);
			}
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("TicTacToe");
		this.setSize(600, 600);
		this.setResizable(false);
		this.setLayout(new GridLayout(3, 3));
		this.setVisible(true);

		for (int i = 0; i < buttons.length; i++)
		{
			for (int j = 0; j < buttons[i].length; j++)
			{
				this.add(buttons[i][j]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Integer[] coord = buttonCoordMap.get(e.getSource());
		
		if (playCounter % 2 == 0)
		{
			buttons[coord[0]][coord[1]].setText("X");
			buttons[coord[0]][coord[1]].setEnabled(false);
			if (xCheckTTT(buttons))
			{
				dialogBox("X wins!");
				playCounter--;
			}
		} else
		{
			buttons[coord[0]][coord[1]].setText("O");
			buttons[coord[0]][coord[1]].setEnabled(false);
			if (oCheckTTT(buttons))
			{
				dialogBox("O wins!");
				playCounter--;
			}
		}
		
		playCounter++;
		
		if (playCounter == 9)
		{
			dialogBox("Game is tied!");
		}
		
	}

	private void dialogBox(String text)
	{
		String[] responses = {"Play again!", "Quit Game"};
		int n = JOptionPane.showOptionDialog(
				null, 
				text, 
				"Game Over!", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, 
				null, 
				responses, 
				0);

		if (n == 1 || n == -1)
		{
			System.exit(0);
		}
		if (n == 0)
		{
			playCounter = 0;
			for (int i = 0; i < buttons.length; i++)
			{
				for (int j = 0; j < buttons[i].length; j++)
				{
					buttons[i][j].setText(" ");
					buttons[i][j].setEnabled(true);
				}
			}
		}
	}
	
	private static boolean xCheckTTT(JButton[][] buttons)
	{
		boolean xWin = checkRows(buttons, "X") || checkColumns(buttons, "X") || checkDiagTL(buttons, "X")
				|| checkDiagTR(buttons, "X");

		return xWin;
	}
	
	private static boolean oCheckTTT(JButton[][] buttons)
	{
		boolean oWin = checkRows(buttons, "O") || checkColumns(buttons, "O") || checkDiagTL(buttons, "O")
				|| checkDiagTR(buttons, "O");

		return oWin;
	}

	private static boolean checkRows(JButton[][] buttons, String XO)
	{
		for (int i = 0; i < buttons.length; i++)
		{
			for (int j = 0; j < buttons[i].length; j++)
			{
				if (XO != buttons[i][j].getText())
				{
					break;
				}
				if (j == buttons[i].length - 1)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean checkColumns(JButton[][] buttons, String XO)
	{
		for (int i = 0; i < buttons.length; i++)
		{
			for (int j = 0; j < buttons[i].length; j++)
			{
				if (XO != buttons[j][i].getText())
				{
					break;
				}
				if (j == buttons[i].length - 1)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean checkDiagTL(JButton[][] buttons, String XO)
	{
		for (int i = 0; i < buttons.length; i++)
		{
			if (XO != buttons[i][i].getText())
				break;
			if (i == buttons.length - 1)
				return true;
		}
		return false;
	}
	
	private static boolean checkDiagTR(JButton[][] buttons, String XO)
	{
		for (int i = buttons.length - 1; i >= 0; i--)
		{
			if (XO != buttons[(buttons.length - 1) - i][i].getText())
				break;
			if (i == 0)
				return true;
		}
		return false;
	}

}
