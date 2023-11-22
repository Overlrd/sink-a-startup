import java.util.ArrayList;

class Startup {
    // startup instances variables
    // an array list of cell locations
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> loc){
        locationCells = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        // assume it's a miss unles told otherwise
        String result = "miss";
        // get the index of cell the user targeted 
        int index = locationCells.indexOf(userInput);
        // if it's a hit
        if (index >= 0) {
            // remove the cell
            locationCells.remove(index);
            // if it's was the last cell , it's a kill
            if (locationCells.isEmpty()) {
                result = "kill";
                System.out.println("Ouch! You sunk " + name + " :(");
            } else {
                result = "hit";
            }
        }
        return result;
    }
}
