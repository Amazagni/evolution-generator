package agh.ics.oop;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CreateStats {
    private int day;
    private int numberOfAnimals;
    private int numberOfGrass;
    private int numberOfEmptyTiles;
    private int averageEnergyLevel;
    private int averageLifeLength;
    ArrayList<Integer> genes;
    private String name;

    public CreateStats(int day, int numberOfAnimals, int numberOfGrass, int numberOfEmptyTiles,
                       int averageEnergyLevel, int averageLifeLength,ArrayList<Integer> genes, String name,boolean flag) throws IOException {
        this.day = day;
        this.numberOfAnimals = numberOfAnimals;
        this.numberOfGrass = numberOfGrass;
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.averageEnergyLevel = averageEnergyLevel;
        this.averageLifeLength = averageLifeLength;
        this.genes = genes;
        this.name = name;
        if(flag)CreateHeader();
        PrintToFile();

    }
    private void CreateHeader() throws IOException {
        FileWriter writer = new FileWriter("statistics/"+name,true);
        writer.write("day, ");
        writer.write("numberOfAnimals, ");
        writer.write("numberOfGrass, ");
        writer.write("numberOfEmptyTiles, ");
        writer.write("averageEnergyLevel, ");
        writer.write("averageLifeLenght, ");
        writer.write("strongestGenes, \n");
        writer.close();

    }
    private void PrintToFile() throws IOException {
        FileWriter writer = new FileWriter("statistics/"+name,true);
        ArrayList<String> data = new ArrayList<>();
        data.add(Integer.toString(day));
        data.add(", ");
        data.add(Integer.toString(numberOfAnimals));
        data.add(", ");
        data.add(Integer.toString(numberOfGrass));
        data.add(", ");
        data.add(Integer.toString(numberOfEmptyTiles));
        data.add(", ");
        data.add(Integer.toString(averageEnergyLevel));
        data.add(", ");
        data.add(Integer.toString(averageLifeLength));
        data.add(", ");
        String geneString = "";
        for(int i = 0; i < this.genes.size(); i++){
            geneString = geneString + this.genes.get(i) + " ";
        }

        data.add(geneString);
        for(int i = 0; i < data.size(); i++){
            writer.write(data.get(i));
        }
        writer.write("\n");
        writer.close();
    }


}
