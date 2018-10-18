package ru.bmstu.bioinf.lab2;

public class FineTableTest {
    public static void main(String[] args) {
        FineTable table = new FineTable("C:\\Users\\trishlex\\IdeaProjects\\BioInformaticsLabs\\src\\main\\java\\ru\\bmstu\\bioinf\\lab2\\blosum.txt");
        System.out.println(table.get('A', 'Y'));
    }
}
