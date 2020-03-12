package edu.jsu.mcis.cs408.project1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

public class TicTacToeController implements PropertyChangeListener {

    // Property Change Names/Events

    public static final String SET_SQUARE_X = "SetSquareToX";
    public static final String SET_SQUARE_O = "SetSquareToO";

    private TicTacToeModel model;
    private TicTacToeView view;

    public TicTacToeController(TicTacToeView view) {

        model = new TicTacToeModel(this, TicTacToeModel.DEFAULT_SIZE);
        this.view = view;
        model.addPropertyChangeListener(this);

    }

    public void processInput(TicTacToeSquare square) {

        //
        // This method processes input from the user.  It accepts the target square selected by the
        // user as a TicTacToeSquare object.  This method should get the state of the game from the
        // Model, and if the game is not over, use the "setModelProperty" method (as shown in the
        // commented code below) to make the mark in the grid.  It should then re-check the state
        // of the game; if the mark that was just made has ended the game, the Result value should
        // be displayed in the View (using its "setResult()" method).
        //

        // setModelProperty("Mark", square);

        //
        // INSERT YOUR CODE HERE
        // DONE

        if(model.getResult() == TicTacToeModel.Result.NONE){
           // model.setMark(square);
            setModelProperty("Mark",square);

            if(model.getResult() != TicTacToeModel.Result.NONE){
                view.setResult((model.getResult().toString()));

            }
        }

    }

    public int getGridSize() {

        // This is a "passthrough" method for the View; it retrieves the size of the grid so that
        // the View can be (re)initialized.

        return model.getSize();

    }

    public String getMarkAsString(TicTacToeSquare square) {

        // This is a "passthrough" method for the View; it retrieves the contents of the indicated
        // square as a String so it can be displayed in the View.

        int row = square.getRow();
        int col = square.getCol();
        return (model.getMark(row, col).toString());

    }

    public void resetGame() {

        // This method resets the Model and View to their default states, using their respective
        // reset methods.

        model.resetModel(TicTacToeModel.DEFAULT_SIZE);
        view.resetView();

    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {

        //
        // This method is called automatically by "firePropertyChange()" when the Model changes its
        // state in a way that must be reflected in the View.  This method informs the View of the
        // change so that it can be updated accordingly.
        //

        view.modelPropertyChange(e);

    }

    protected void setModelProperty(String propertyName, Object newValue) {

        //
        // This method is called when a View informs the Controller of a user interaction which
        // requires a change to a Model. Using the property name, it identifies the corresponding
        // setter method, and then invokes this method so that the Model can be updated properly.
        //

        try {

            Method method = model.getClass().getMethod("set" + propertyName, new Class[]{newValue.getClass()});
            method.invoke(model, newValue);

        }
        catch (Exception e) { e.printStackTrace(); }

    }

}