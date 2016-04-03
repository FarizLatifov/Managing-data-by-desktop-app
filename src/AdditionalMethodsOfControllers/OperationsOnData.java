/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdditionalMethodsOfControllers;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import rmd.WorkOverDatabase.SquadData;
import rmd.objects.Country;
import rmd.objects.Position;
import rmd.objects.ShirtNumber;
import rmd.objects.Squad;

/**
 *
 * @author Fariz
 */
public class OperationsOnData {

    SquadData squadData = new SquadData();
    ObservableList<Squad> squadList = squadData.getDataFromDatabase();
    ObservableList<Position> positionList = squadData.getPositionFromDatabase();
    ObservableList<Country> countryList = squadData.getCountryFromDatabase();
    ObservableList<ShirtNumber> numberList = squadData.getShirtNumberFromDatabase();
    
    
    
    public ArrayList<Object> takeAppropriateData(String labelName) {

        ArrayList<Object> list = new ArrayList<>();
        ArrayList squadNumbers = new ArrayList<>();
        ArrayList allNumbers = new ArrayList<>();
          
        for(int i=0;i<squadList.size();i++){
        squadNumbers.add(squadList.get(i).getNumber());
        }
        for(int i=0;i<numberList.size();i++){
        allNumbers.add(numberList.get(i).getNumber());
        }
        
        switch (labelName) {
            case "Position":
                for (int i = 0; i < positionList.size(); i++) {
                    list.add(positionList.get(i).getName());
                }
                break;
            case "Country":
                for (int i = 0; i < countryList.size(); i++) {
                    list.add(countryList.get(i).getName());
                }
                break;
            case "Shirt number":
                allNumbers.removeAll(squadNumbers);
                list=allNumbers;
                    break;
            default:
                System.out.println("Please look over relevance of switch-case operator on OperationsOnData.takeAppropriateData() method");
                break;

        }

        return list;
    }

}
