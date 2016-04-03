/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmd.WorkOverDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rmd.jdbc.DBConnection;
import rmd.objects.Country;
import rmd.objects.Position;
import rmd.objects.ShirtNumber;
import rmd.objects.Squad;

/**
 *
 * @author Fariz
 */
public class SquadData {

    public ObservableList<Country> getCountryFromDatabase() {
        ObservableList countryList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        Country country = null;

        String query = "SELECT * FROM country;";

        try {
            statement = DBConnection.getDBConnection().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                country = new Country(resultSet.getInt(1), resultSet.getString(2));
                countryList.add(country);
            }
            resultSet.close();
            statement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return countryList;
    }

    public ObservableList<Position> getPositionFromDatabase() {
        ObservableList positionList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        Position position = null;

        String query = "SELECT * FROM position;";

        try {
            statement = DBConnection.getDBConnection().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                position = new Position(resultSet.getInt(1), resultSet.getString(2));
                positionList.add(position);
            }
            resultSet.close();
            statement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return positionList;
    }

    public ObservableList<ShirtNumber> getShirtNumberFromDatabase() {
        ObservableList shirtNumberList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        ShirtNumber shirtNumber = null;

        String query = "SELECT * FROM shirtnumber;";

        try {
            statement = DBConnection.getDBConnection().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                shirtNumber = new ShirtNumber(resultSet.getInt(1), resultSet.getInt(2));
                shirtNumberList.add(shirtNumber);
            }
            resultSet.close();
            statement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return shirtNumberList;
    }

    public ObservableList<Squad> getDataFromDatabase() {

        String query
                = "select player_id, player.name, position.NAME,\n"
                + " country.NAME, shirtnumber.SHIRT_NUMBER, player.BIRTH_DATE\n"
                + "from realmadrid.country  join realmadrid.player \n"
                + "on country.id=player.country_id \n"
                + " join realmadrid.squad\n"
                + "on player.id=squad.player_id\n"
                + " join realmadrid.position\n"
                + "on squad.position_id=position.id\n"
                + "join realmadrid.shirtnumber\n"
                + "on squad.shirt_number_id=shirtnumber.id  order by squad.shirt_number_id;";
        ObservableList<Squad> squadList = FXCollections.observableArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = DBConnection.getDBConnection().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Squad squad = new Squad(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6));

                squadList.add(squad);

            }
            resultSet.close();
            statement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return squadList;
    }

    public void addNewDataToDatabase(Squad player, Boolean countryIsNew) {
        PreparedStatement statement = null;
        String query1 = "start transaction;\n"
                + "insert into player (name, birth_date, country_id) values ('" + player.getName() + "', '" + player.getBirthDate() + "', (SELECT id FROM country WHERE name='" + player.getCountry() + "'));\n"
                + "SET @last_id_player = LAST_INSERT_ID();\n"
                + "insert into squad (player_id, position_id, shirt_number_id) values (@last_id_player,(SELECT id FROM position WHERE name='" + player.getPosition() + "') , (SELECT id FROM shirtnumber WHERE shirt_number = " + player.getNumber() + ") );\n"
                + "commit;";
        String query2
                = "start transaction;\n"
                + "insert into country (name) values ('" + player.getCountry() + "');\n"
                + "SET @last_id_country = LAST_INSERT_ID();\n"
                + "insert into player (name, birth_date, country_id) values ('" + player.getName() + "', '" + player.getBirthDate() + "', @last_id_country);\n"
                + "SET @last_id_player = LAST_INSERT_ID();\n"
                + "insert into squad (player_id, position_id, shirt_number_id) values (@last_id_player,(SELECT id FROM position WHERE name='" + player.getPosition() + "') , (SELECT id FROM shirtnumber WHERE shirt_number = " + player.getNumber() + ") );\n"
                + "commit;";
        if (countryIsNew == true) {
            query1 = query2;
        }
        try {
            statement = DBConnection.getDBConnection().prepareStatement(query1);
            statement.executeUpdate();

            statement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    public void deleteDataFormDatabase(int playerID) {
        PreparedStatement statement = null;
        String query
                = "DELETE FROM player WHERE id=" + playerID + ";";
        try {
            statement = DBConnection.getDBConnection().prepareStatement(query);
            statement.executeUpdate();

            statement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public void updateDataOnDatabase(Squad editedPlayer, Boolean countryIsNew) {
        String query1 = "start transaction;\n"
                + "INSERT INTO country (name) values('" + editedPlayer.getCountry() + "' );\n"
                + "SET @last_id_country = LAST_INSERT_ID();\n"
                + "update player set name='" + editedPlayer.getName() + "' where id=" + editedPlayer.getId() + ";\n"
                + "update player set BIRTH_DATE='" + editedPlayer.getBirthDate() + "' where id=" + editedPlayer.getId() + ";\n"
                + "update player set country_id=@last_id_country where id=" + editedPlayer.getId() + ";\n"
                + "update squad set position_id=(select id from position where position.name='" + editedPlayer.getPosition() + "') where PLAYER_ID=" + editedPlayer.getId() + ";\n"
                + "update squad set SHIRT_NUMBER_ID=(SELECT id from realmadrid.shirtnumber where shirt_number=" + editedPlayer.getNumber() + ") where PLAYER_ID=" + editedPlayer.getId() + ";\n"
                + "commit;";
        String query2 = "start transaction;\n"
                + "update player set name='" + editedPlayer.getName() + "' where id=" + editedPlayer.getId() + ";\n"
                + "update player set BIRTH_DATE='" + editedPlayer.getBirthDate() + "' where id=" + editedPlayer.getId() + ";\n"
                + "update player set country_id=(Select id from country where name='" + editedPlayer.getCountry() + "') where id=" + editedPlayer.getId() + ";\n"
                + "update squad set position_id=(select id from position where position.name='" + editedPlayer.getPosition() + "') where PLAYER_ID=" + editedPlayer.getId() + ";\n"
                + "update squad set SHIRT_NUMBER_ID=(SELECT id from realmadrid.shirtnumber where shirt_number=" + editedPlayer.getNumber() + ") where PLAYER_ID=" + editedPlayer.getId() + ";\n"
                + "commit;";
        if (countryIsNew) {
            query2 = query1;
            System.out.println("falseeee");
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnection.getDBConnection().prepareStatement(query2);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            DBConnection.getDBConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (DBConnection.getDBConnection() != null) {
                try {
                    DBConnection.getDBConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(SquadData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
