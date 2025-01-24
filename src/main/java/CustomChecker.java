// Start of HEAD

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
// End of HEAD

// Start of BODY

/**
 * TestStruct::
 * testcase_id                   [long] ID of the test-case
 * testcase_input_path           [String] File path to test-case input
 * testcase_output_path          [String] File path to test-case output generated by the problem solver
 * testcase_expected_output_path [String] File path to test-case expected output to be matched with
 * metadata_file_paths           [ArrayList<String>] File paths to Question metadata (Extra files usually used for defining traning sets)
 * submission_code_path          [String] File path to submission source code
 * testcase_result               [boolean] Set to true if test-case output matches test-case expected output. Matching is done line by line
 * testcase_signal               [long] Exit code of the test-case process
 * testcase_time                 [double] Time taken by the test-case process in seconds
 * testcase_memory               [long] Peak memory of the test-case process determined in bytes
 * data                          [String] <Future use>
 * <p>
 * <p>
 * ResultStruct::
 * result      [boolean]  Assign test-case result. true determines success. false determines failure
 * score       [double] Assign test-case score. Normalized between 0 to 1
 * message     [String] Assign test-case message. This message is visible to the problem solver
 **/

public class CustomChecker {
    private static DecimalFormat df = new DecimalFormat("0.################", new DecimalFormatSymbols(Locale.US));

    static String f2s(double x) {
        return df.format(x);
    }

    static void run_custom_checker(final TestStruct t_obj, ResultStruct r_obj) {
        // Don't print anything to STDOUT in this function
        // Enter your custom checker scoring logic here
        double a, b, c;
        try (Scanner inf = new Scanner(new BufferedReader(new FileReader(t_obj.testcase_input_path)))) {
            inf.useLocale(Locale.US);
            // Read input
            a = inf.nextDouble();
            b = inf.nextDouble();
            c = inf.nextDouble();
        } catch (IOException e) {
            r_obj.result = false;
            r_obj.score = 0;
            r_obj.message = "Error reading input file " + t_obj.testcase_input_path;
            return;
        }

        try (Scanner ouf = new Scanner(new BufferedReader(new FileReader(t_obj.testcase_output_path)));
             Scanner ans = new Scanner(new BufferedReader(new FileReader(t_obj.testcase_expected_output_path)))) {
            ouf.useLocale(Locale.US);

            // Check number of solutions
            int cont = ouf.nextInt();
            int jury = ans.nextInt();

            if (cont != jury) {
                r_obj.result = false;
                r_obj.score = 0;
                r_obj.message = String.format("solutions: jury %d, cont %d", jury, cont);
                return;
            }

            // Add source equation
            double EPS = 1e-15;
            StringBuilder sb = new StringBuilder();
            if (Math.abs(a) > EPS) {
                sb.append(f2s(a));
                sb.append("*x^2");
            }
            if (Math.abs(b) > EPS) {
                if (b >= -EPS && sb.length() > 0) {
                    sb.append("+");
                }
                sb.append(f2s(b));
                sb.append("*x");
            }
            if (c >= -EPS && sb.length() > 0) {
                sb.append("+");
            }
            sb.append(f2s(c));
            sb.append(" = 0");
            if (Math.abs(a) > EPS) {
                double D = Math.pow(b, 2) - 4 * a * c;
                sb.append("  D = ").append(f2s(D));
            }
            sb.append("  roots: ").append(cont).append(" ");

            // Check every x-value
            for (int i = 0; i < cont; i++) {
                double x = ouf.nextDouble();
                sb.append(" ").append(f2s(x));
                double t = a * Math.pow(x, 2) + b * x + c;
                if (Math.abs(t) > 1e-6) {
                    r_obj.result = false;
                    r_obj.score = 0;
                    r_obj.message = String.format("x: %g   a*x^2+b*x+c = %g", x, t);
                }
            }

            // All OK
            r_obj.result = true;
            r_obj.score = 1.0d;
            r_obj.message = sb.toString().trim();
        } catch (IOException e) {
            r_obj.result = false;
            r_obj.score = 0;
            r_obj.message = "Error reading output file " + t_obj.testcase_input_path;
        }
    }
    // End of BODY

    // Start of TAIL
    @SuppressWarnings("unchecked")
    static int read_input_json(final String json_file_path, TestStruct t_obj) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json_obj = (JSONObject) parser.parse(new FileReader(json_file_path));

            // Read values
            t_obj.testcase_id = (long) json_obj.get("testcase_id");
            t_obj.testcase_input_path = (String) json_obj.get("input_file_path");
            t_obj.testcase_output_path = (String) json_obj.get("output_file_path");
            t_obj.testcase_expected_output_path = (String) json_obj.get("expected_output_file_path");

            JSONArray metadata_file_path_node = (JSONArray) json_obj.get("metadata_file_paths");
            if (metadata_file_path_node != null && !metadata_file_path_node.isEmpty()) {
                @SuppressWarnings("unchecked")
                Iterator<String> metadata_file_paths_it = (Iterator<String>) metadata_file_path_node.iterator();
                while (metadata_file_paths_it.hasNext())
                    t_obj.metadata_file_paths.add(metadata_file_paths_it.next());
            }

            t_obj.submission_code_path = (String) json_obj.get("submission_code_path");
            t_obj.testcase_result = (boolean) json_obj.get("testcase_result");
            t_obj.testcase_signal = (long) json_obj.get("testcase_signal");
            t_obj.testcase_time = ((Number) json_obj.get("testcase_time")).doubleValue();
            t_obj.testcase_memory = (long) json_obj.get("testcase_memory");
            t_obj.data = (String) json_obj.get("data");
        } catch (Exception err) {
            return 1;
        }

        return 0;
    }

    static void write_result_json(final ResultStruct r_obj) {
        JSONObject json_obj = new JSONObject();
        json_obj.put("custom_result", new Integer((r_obj.result) ? 1 : 0));
        json_obj.put("custom_score", new Double(Math.max(((r_obj.score > 1.0d) ? 1.0d : r_obj.score), 0.0d)));
        json_obj.put("custom_message", (r_obj.message.length() > 4096) ? r_obj.message.substring(0, 4095) : r_obj.message);
        System.out.println(json_obj);
    }

    public static void main(String args[]) {
        // Input parameters
        TestStruct t_obj = new TestStruct();
        // Out parameters
        ResultStruct r_obj = new ResultStruct();

        if (args.length < 1) {
            write_result_json(r_obj);
            System.exit(1);
        }

        // Decode input JSON
        int failure = read_input_json((String) args[0], t_obj);
        // Incase input JSON was malformed or not existent
        if (failure != 0) {
            r_obj.message = "Unable to read input json";
            write_result_json(r_obj);
            System.exit(2);
        }

        // Run the custom checker evaluator
        run_custom_checker(t_obj, r_obj);

        // Encode result JSON
        write_result_json(r_obj);
        System.exit(0);
    }
}


final class TestStruct {
    long testcase_id = 0;
    String testcase_input_path;
    String testcase_output_path;
    String testcase_expected_output_path;
    ArrayList<String> metadata_file_paths = new ArrayList<String>();
    String submission_code_path;
    boolean testcase_result = false;
    long testcase_signal = 0;
    double testcase_time = 0.0f;
    long testcase_memory = 0;
    String data;
}


final class ResultStruct {
    boolean result = false;
    double score = 0.0f;
    String message = "Uninitialized";
}
// End of TAIL