package LLD.Patterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

class File {
    String filename;
    
    public File(String filename) { this.filename = filename; }

    public void ls() { System.out.println(filename); }
}

class Directory {
    String directoryName;
    List<Object> objectList ; // As list can have both files or directory

    public Directory(String name) { this.directoryName = name; objectList = new ArrayList<>(); }

    public void add(Object o) { objectList.add(o); }

    public void ls() {
        System.out.println("Directory: " + directoryName);
        for (Object o : objectList) { // So in this wrong approach we need to check and typecast , composite design pattern solves this 
            if (o instanceof File) {
                ((File) o).ls();
            } else {
                ((Directory) o).ls();
            }
        }
    }

}