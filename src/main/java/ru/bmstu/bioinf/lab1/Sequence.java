package ru.bmstu.bioinf.lab1;

public class Sequence {
    private String name;
    private String sequence;

    public Sequence(String name, String sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    public int size() {
        return sequence.length();
    }

    public char get(int i) {
        return sequence.charAt(i);
    }

    @Override
    public String toString() {
        return "name: " + name + "\n" + "sequence: " + sequence;
    }
}
