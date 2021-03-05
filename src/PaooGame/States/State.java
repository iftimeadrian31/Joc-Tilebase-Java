package PaooGame.States;


import PaooGame.Game;
import PaooGame.MusicPlayer;

import java.awt.*;
import java.io.FileNotFoundException;

/*! \public abstract class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: New Game, Load Game, Settings, About etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State
{
        ///Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    protected static State previousState  = null; /*!< Referinta catre starea anterioara a jocului.*/
    protected static State currentState   = null; /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected Game game;
    protected MusicPlayer soundtrack;

    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state,State previousState)
    {

        State.previousState = previousState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }

        ///Metoda abstracta destinata actualizarii starii curente
    public abstract void Update() throws FileNotFoundException;
        ///Metoda abstracta destinata desenarii starii curente
    public abstract void Draw(Graphics g) throws FileNotFoundException;
    public Game getGame()
    {
        return game;
    }
    public void setGame(Game game)
    {
        this.game=game;
    }
}
