import parser.Expression;
import parser.ExpressionParser;

import java.io.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        ExpressionParser expressionParser = new ExpressionParser();
//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt")));
        new ProofParser().parse();
    }
}
