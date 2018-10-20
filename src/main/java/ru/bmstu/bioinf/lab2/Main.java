package ru.bmstu.bioinf.lab2;

import org.apache.commons.cli.*;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option gapOption = new Option("g", "gap", true, "gap error, default is -2");
        gapOption.setRequired(false);
        options.addOption(gapOption);

        Option fileOption = new Option("o", true, "file output");
        fileOption.setRequired(false);
        options.addOption(fileOption);

        Option type = new Option("t", "type", true, "AA for amino acids, N for nucleotides");
        type.setRequired(true);
        options.addOption(type);

        Option input = new Option("f", "file", true, "File with two sequences in fasta format");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (!cmd.getOptionValue("t").equals("AA") && !cmd.getOptionValue("t").equals("N")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("arguments", options);
                throw new IllegalStateException("Wrong argument for type");
            }

            TaskType taskType = cmd.getOptionValue("t").equals("AA") ? TaskType.BLOSUM62 : TaskType.DNA_FULL;

            Integer gap = cmd.hasOption("g") ? Integer.parseInt(cmd.getOptionValue("g")) : null;

            String file = cmd.getOptionValue("f");

            SequenceReader sequenceReader = new SequenceReader(file);
            Sequence up = sequenceReader.next();
            Sequence left = sequenceReader.next();

            Table table;
            if (gap == null) {
                table = new Table(up, left, taskType);
            } else {
                table = new Table(up, left, gap, taskType);
            }

            if (cmd.hasOption("o")) {
                table.printResult(new File(cmd.getOptionValue("o")));
            } else {
                table.printResult();
            }

        } catch (ParseException e) {
            throw new IllegalStateException("Can't parse arguments", e);
        }
    }
}
