package agh.ics.oop;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CreateStats {
    private int day;
    private int numberOfAnimals;
    private int numberOfGrass;
    private int numberOfEmptyTiles;
    private int averageEnergyLevel;
    private int averageLifeLength;
    ArrayList<Integer> genes;
    private String name = new Date().toString();
    private FileWriter writer = new FileWriter("statistics/"+name,true);


    //    with custom name
    public CreateStats(String name,boolean flag) throws IOException {
        this.name = name;
        this.writer = new FileWriter("statistics/"+name,true);
        if(flag)CreateHeader();
    }

    public FileWriter getWriter() {
        return this.writer;
    }

    public CreateStats(boolean flag) throws IOException {
        if(flag)CreateHeader();
    }

    private void CreateHeader() throws IOException {
        this.writer.write("day, ");
        this.writer.write("numberOfAnimals, ");
        this.writer.write("numberOfGrass, ");
        this.writer.write("numberOfEmptyTiles, ");
        this.writer.write("averageEnergyLevel, ");
        this.writer.write("averageLifeLength, ");
        this.writer.write("strongestGenes, \n");
        this.writer.close();

    }
    public void PrintToFile(int day, int numberOfAnimals, int numberOfGrass, int numberOfEmptyTiles,
                             int averageEnergyLevel, int averageLifeLength, ArrayList<Integer> genes, FileWriter writer) throws IOException {
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
        for(int i = 0; i < genes.size(); i++){
            geneString = geneString + genes.get(i) + " ";
        }

        data.add(geneString);
        for(int i = 0; i < data.size(); i++){
            this.writer.write(data.get(i));
            System.out.println("dupa");
        }
        this.writer.write("\n");
        this.writer.close();
    }


}
