package src.main.java;

/**
 *  @file       TicTacToeException.java
 *  @brief      TicTacToeException class implementation
 *  @author     Mark L. Short
 *  @date       Dec 4, 2025
 *
 *  <dl>
 *       <dt>Course</dt><dd>CS 3354 Object Oriented Design and Programming</dd><BR>
 *       <dt>Instructor</dt><dd>Dr. Alberto Castro-Hernandez</dd>
 *  </dl>
 *
 */

@SuppressWarnings("serial")
class TicTacToeException extends Exception
{
    /**
     * Default constructor
     */
    public TicTacToeException()
    {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param  [in] message   the detail message.
     */
    public TicTacToeException(String message)
    {
        super(message);
    }
}