package PaooGame;

import PaooGame.Entity.*;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.CollisionObjects;
import PaooGame.Graphics.CustomFont;
import PaooGame.States.PlayState;
import PaooGame.States.SettingsState;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
/*! \public class Database
    \brief clasa care se ocupa de baza de date
 */
public class Database {
    /*! \public void saveSettings(int controls,int music)
    \brief functie care salveaza setarile
 */
    public void saveSettings(int controls,int music)
    {
        Connection connection;
        Statement statement;
        try{
            connection= DriverManager.getConnection("jdbc:sqlite:database.db");
            statement=connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS Setari;");
            statement.execute("CREATE TABLE IF NOT EXISTS Setari (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	value integer\n"
                    + ");");
            statement.execute("INSERT INTO Setari(id,name,value) "+"VALUES (0, 'controls',"+ controls+");");
            statement.execute("INSERT INTO Setari(id,name,value) "+"VALUES (1, 'music',"+ music+");");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("eroare: "+e.getMessage());
        }
    }
    /*! \public void saveGame(int level,int score)
\brief functie care salveaza nivelul
*/
    public void saveGame(int level,int score)
    {
        Connection connection;
        Statement statement;
        try{
            connection= DriverManager.getConnection("jdbc:sqlite:database.db");
            statement=connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS Save;");
            statement.execute("CREATE TABLE IF NOT EXISTS Save (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + "	name text NOT NULL,\n"
                    + "	value integer\n"
                    + ");");
            statement.execute("INSERT INTO Save(id,name,value) "+"VALUES (0, 'level',"+ level+");");
            statement.execute("INSERT INTO Save(id,name,value) "+"VALUES (1, 'score',"+ score+");");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("eroare: "+e.getMessage());
        }
    }
    /*! \public static void LoadGame()
\brief functie care incarca nivelul
*/
    public static void LoadGame()
    {
        Connection connection;
        Statement statement;
        int level;
        int score;
        try{
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM Save WHERE id="+0+";");
            level=Integer.parseInt(rs.getString("value"));
            PlayState.setLevel(level);
            rs=statement.executeQuery("SELECT * FROM Save WHERE id="+1+";");
            score=Integer.parseInt(rs.getString("value"));
            PlayState.setTotal_Score(score);
            rs.close();
            statement.close();
            connection.close();
            //System.out.println(controls+" "+music);
        }
        catch (Exception e) {
            System.out.println("eroare: "+e.getMessage());
            System.exit(0);
        }

    }
    /*! \public static void getSettings()
\brief functie care incarca setarile
*/
    public static void getSettings()
    {
        Connection connection;
        Statement statement;
        int controls;
        int music;
        try{
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM Setari WHERE id="+0+";");
            controls=Integer.parseInt(rs.getString("value"));
            SettingsState.setControls(controls);
            rs=statement.executeQuery("SELECT * FROM Setari WHERE id="+1+";");
            music=Integer.parseInt(rs.getString("value"));
            SettingsState.setMusic(music);
            rs.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("eroare: "+e.getMessage());
            System.exit(0);
        }

    }
    public static void getLink()
    {
        Connection connection;
        Statement statement;
        int controls;
        int music;
        try{
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);
            statement=connection.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM Setari WHERE id="+0+";");
            controls=Integer.parseInt(rs.getString("value"));
            SettingsState.setControls(controls);
            rs=statement.executeQuery("SELECT * FROM Setari WHERE id="+1+";");
            music=Integer.parseInt(rs.getString("value"));
            SettingsState.setMusic(music);
            rs.close();
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("eroare: "+e.getMessage());
            System.exit(0);
        }

    }


}
