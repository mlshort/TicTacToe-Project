/**
 *  @file       TicTacToeModel.java
 *  @brief      TicTacToeModel class implementation
 *  @author     Mark L. Short
 *  @date       Dec 4, 2025
 *
 *  <dl>
 *       <dt>Course</dt><dd>CS 3354 Object Oriented Design and Programming</dd><BR>
 *       <dt>Instructor</dt><dd>Dr. Alberto Castro-Hernandez</dd>
 *  </dl>
 *
 */

package src.main.java;
import java.beans.PropertyChangeListener;
import javax.swing.event.SwingPropertyChangeSupport;

/**
 * The Model component in the MVC (Model-View-Controller) design pattern
 * demonstrates the data and business logic of an application. It is
 * responsible for managing the application's data, processing business rules,
 * and responding to requests for information from other components, such as
 * the View and the Controller.
 *
 * @sa https://java-design-patterns.com/patterns/model-view-controller/
 */
public class TicTacToeModel
{
    /**
     * to make access to underlying BOARD_SIZE more convenient
     */
    public static final int BOARD_SIZE = TicTacToeBoard.BOARD_SIZE;

    public static final String BOARD_UPDATE_PROP_NAME = "board update";
    private final SwingPropertyChangeSupport propertyChanges = new SwingPropertyChangeSupport(this);

    private TicTacToeBoard gameBoard = new TicTacToeBoard();
    private TttPlayerOwner currentPlayer;
    private boolean bIsGameOver;

    /**
     * Default constructor
     */
    public TicTacToeModel()
    {
        bIsGameOver = false;
        currentPlayer = TttPlayerOwner.X;
        gameBoard.clear();
    }

    /**
     *
     * @return TicTacToeBoard model
     */
    public TicTacToeBoard getGameBoard()
    {
        return gameBoard;
    }

    public boolean isGameOver()
    {
        return bIsGameOver;
    }

    /**
     *
     * @return
     */
    public int getBoardWinType()
    {
        return gameBoard.getWinType();
    }

    public int getBoardWinIndex()
    {
        return gameBoard.getWinIndex();
    }

    /**
     * Updates the game board model with new square owner
     *
     * @param [in] row            Square row
     * @param [in] col            Square column
     * @param [in] playerOwner    Square owner
     * @throws TicTacToeException
     */
    public void updateBoard(int row, int col, TttPlayerOwner playerOwner) throws TicTacToeException
    {
        if (bIsGameOver)
        {
            return;
        }
        // update the gameBoard model data
        gameBoard.update(row, col, playerOwner);

        int iWinner = gameBoard.checkForWinner();
        switch(iWinner)
        {
            case TicTacToeBoard.NO_WINNER:
                switchPlayer();
                break;
            case TicTacToeBoard.X_WINNER:
            case TicTacToeBoard.O_WINNER:
            case TicTacToeBoard.DRAW:
                bIsGameOver = true;
                break;
        }

        propertyChanges.firePropertyChange(BOARD_UPDATE_PROP_NAME, null, gameBoard);
    }

    /**
     * Returns board model to its original state and notifies potential listeners
     */
    public void clearBoard()
    {
        bIsGameOver   = false;
        currentPlayer = TttPlayerOwner.X;
        gameBoard.clear();

        propertyChanges.firePropertyChange(BOARD_UPDATE_PROP_NAME, null, gameBoard);
    }

    /**
     * Mouse click event handler
     *
     * @param [in] row            Square row
     * @param [in] col            Square column
     * @throws TicTacToeException
     */
    public void onBoardClick(int row, int col) throws TicTacToeException
    {
        updateBoard(row, col, currentPlayer);
    }

    public boolean isBoardFull()
    {
        return gameBoard.isFull();
    }

    /**
     * Switches current player between 'X' and 'O'
     */
    private void switchPlayer()
    {
        currentPlayer = (currentPlayer == TttPlayerOwner.X) ? TttPlayerOwner.O : TttPlayerOwner.X;
    }

    /**
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     * The same listener object may be added more than once, and will be called
     * as many times as it is added.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChanges.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered
     * for all properties.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChanges.removePropertyChangeListener(listener);
    }

}