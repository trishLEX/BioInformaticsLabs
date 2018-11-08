package ru.bmstu.bioinf.lab2;

import org.apache.commons.cli.*;
import ru.bmstu.bioinf.Common.Sequence;
import ru.bmstu.bioinf.Common.SequenceReader;
import ru.bmstu.bioinf.Common.TaskType;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option gapOpenOption = new Option("go", "gapOpen", true, "open gap error, default is -2");
        gapOpenOption.setRequired(false);
        options.addOption(gapOpenOption);

        Option gapExtendOption = new Option("ge", "gapExtend", true, "extend gap error, default is -1");
        gapExtendOption.setRequired(false);
        options.addOption(gapExtendOption);

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

            Integer openGap = cmd.hasOption("go") ? Integer.parseInt(cmd.getOptionValue("go")) : null;
            Integer extendGap = cmd.hasOption("ge") ? Integer.parseInt(cmd.getOptionValue("ge")) : null;

            String file = cmd.getOptionValue("f");

            SequenceReader sequenceReader = new SequenceReader(file);
            Sequence up = sequenceReader.next();
            Sequence left = sequenceReader.next();

            FineTable fineTable;
            switch (taskType) {
                case DNA_FULL:
                    fineTable = new DNAfullTable(openGap, extendGap);
                    break;
                case BLOSUM62:
                    fineTable = new Blosum62Table(openGap, extendGap);
                    break;
                default:
                    fineTable = new DefaultFineTable(openGap, extendGap);
                    break;
            }

            Table table = new Table(up, left, fineTable);

            if (cmd.hasOption("o")) {
                table.printResult(new File(cmd.getOptionValue("o")));
            } else {
                table.printResult();
            }

        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("arguments", options);
            throw new IllegalStateException("Can't parse arguments", e);
        }
    }
}
