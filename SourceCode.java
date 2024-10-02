import java.util.ArrayList;
import java.util.Scanner;

public class Source_Code {
    static int[][] costs = new int[7][7];
    static int start_link;
    static int[][] general_array = new int[6][3];

    static String[][] results = new String[7][6];

    public static void main(String[] args) {

        for (int i = 0; i < 6; i++) {
            general_array[i][0] = 0;
            general_array[i][1] = Integer.MAX_VALUE;
            general_array[i][2] = 0;
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                costs[i][j] = 0;
            }
        }

        costs[1][2] = take_int(1, 2);
        costs[2][1] = costs[1][2];

        costs[1][4] = take_int(1, 4);
        costs[4][1] = costs[1][4];

        costs[2][3] = take_int(2, 3);
        costs[3][2] = costs[2][3];

        costs[2][4] = take_int(2, 4);
        costs[4][2] = costs[2][4];

        costs[3][5] = take_int(3, 5);
        costs[5][3] = costs[3][5];

        costs[3][6] = take_int(3, 6);
        costs[6][3] = costs[3][6];

        costs[4][5] = take_int(4, 5);
        costs[5][4] = costs[4][5];

        costs[5][6] = take_int(5, 6);
        costs[6][5] = costs[5][6];

        Scanner scann = new Scanner(System.in);
        System.out.print("Enter start link: ");
        while (true) {
            try {
                String temp = scann.next();
                start_link = Integer.parseInt(temp);

                if (start_link < 1 || start_link > 6) {
                    throw new NumberFormatException();
                }

                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter again start link: ");
            }
        }

        results[0][0] = "From link to link";
        for (int i = 1; i < 7; i++) {
            results[i][0] = start_link + " -> " + i;
        }
        results[0][1] = "Cost (Bellman-Ford)";
        results[0][2] = "Cost (Dijkstra)";
        results[0][3] = "Best Route";

        for (int i = 1; i < 7; i++) {
            if (i != start_link) {
                String temp = mini_Dijkstra(i);

                results[i][2] = temp.split(":")[0];
                results[i][3] = temp.split(":")[1];

                for (int j = 0; j < 6; j++) {
                    general_array[j][0] = 0;
                    general_array[j][1] = Integer.MAX_VALUE;
                    general_array[j][2] = 0;
                }
            } else {
                results[i][2] = "0";
                results[i][3] = i + " -> " + i;
            }
        }

        general_array[start_link - 1][1] = 0;

        boolean something_changed = true;
        while (something_changed) {
            something_changed = false;
            for (int i = 1; i < 7; i++) {

                if (general_array[i - 1][1] == Integer.MAX_VALUE) {
                    continue;
                }

                for (int link : connected(i)) {
                    if (general_array[link - 1][1] > (costs[i][link] + general_array[i - 1][1])) {
                        general_array[link - 1][1] = costs[i][link] + general_array[i - 1][1];
                        something_changed = true;
                    }
                }
            }
        }
        for (int i = 1; i < 7; i++) {
            results[i][1] = "" + general_array[i - 1][1];

        }
        for (int i = 0; i < 6; i++) {
            general_array[i][0] = 0;
            general_array[i][1] = Integer.MAX_VALUE;
            general_array[i][2] = 0;
        }

        System.out.println("\n");

        int[] max_len = new int[7];

        for (int i = 0; i < 6; i++) {
            max_len[i] = 0;
            for (int j = 0; j < 7; j++) {
                if (results[j][i] == null) {
                    results[j][i] = "0";
                }
                if (results[j][i].length() > max_len[i]) {
                    max_len[i] = results[j][i].length();
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.print("|");
            if (i == 1) {
                for (int j = 0; j < 4; j++) {
                    System.out.print("-");
                    for (int k = 0; k < max_len[j] - 1; k++) {
                        System.out.print("-");
                    }
                    System.out.print("-|");
                }
                System.out.print("\n|");
            }

            for (int j = 0; j < 4; j++) {
                System.out.print(results[i][j]);
                for (int k = 0; k < (max_len[j] - results[i][j].length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(" |");
            }
        }
    }

    private static String mini_Dijkstra(int end_link) {
        for (int link : connected(start_link)) {
            general_array[link - 1][0] = start_link;
            general_array[link - 1][1] = costs[start_link][link];
        }
        general_array[start_link - 1][2] = 1;

        while (true) {

            ArrayList<Integer> temp_cost = new ArrayList<Integer>();
            ArrayList<Integer> temp_link = new ArrayList<Integer>();

            for (int i = 0; i < 6; i++) {
                if (general_array[i][2] == 0) {
                    if (temp_cost.size() == 0) {
                        temp_cost.add(general_array[i][1]);
                        temp_link.add(i + 1);
                    } else {
                        if (general_array[i][1] < temp_cost.get(0)) {
                            temp_cost.clear();
                            temp_link.clear();

                            temp_cost.add(general_array[i][1]);
                            temp_link.add(i + 1);
                        }
                    }
                }
            }

            int min_link = temp_link.get(0);

            if (min_link == end_link) {
                String toReturn = "" + min_link;

                int temp = general_array[min_link - 1][0];
                while (temp != 0) {
                    toReturn = temp + " -> " + toReturn;
                    temp = general_array[temp - 1][0];
                }

                return general_array[min_link - 1][1] + ":" + toReturn;
            } else {
                for (int link : connected(min_link)) {
                    if (general_array[link - 1][2] == 1) {
                        continue;
                    } else if (general_array[link - 1][1] > general_array[min_link - 1][1] + costs[min_link][link]) {
                        general_array[link - 1][0] = min_link;
                        general_array[link - 1][1] = general_array[min_link - 1][1] + costs[min_link][link];
                    } else if (general_array[link - 1][1] <= general_array[min_link - 1][1] + costs[min_link][link]) {
                        int temp = general_array[min_link - 1][1] + costs[min_link][link];
                    }
                }
                general_array[min_link - 1][2] = 1;
            }
        }
    }

    private static int take_int(int from, int to) {
        Scanner scann = new Scanner(System.in);
        int toReturn;

        System.out.print("Enter cost from " + from + " link to " + to + ": ");
        while (true) {
            try {
                String temp = scann.next();
                toReturn = Integer.parseInt(temp);

                if (toReturn < 0) {
                    throw new NumberFormatException();
                }

                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter again cost from " + from + " link to " + to + ": ");
            }
        }
        return toReturn;
    }

    private static ArrayList<Integer> connected(int link) {
        ArrayList<Integer> toReturn = new ArrayList<Integer>();

        if (link == 1) {
            toReturn.add(2);
            toReturn.add(4);
        } else if (link == 2) {
            toReturn.add(1);
            toReturn.add(3);
            toReturn.add(4);
            toReturn.add(5);
        } else if (link == 3) {
            toReturn.add(2);
            toReturn.add(4);
            toReturn.add(5);
            toReturn.add(6);
        } else if (link == 4) {
            toReturn.add(1);
            toReturn.add(2);
            toReturn.add(3);
            toReturn.add(5);
        } else if (link == 5) {
            toReturn.add(2);
            toReturn.add(3);
            toReturn.add(4);
            toReturn.add(6);
        } else if (link == 6) {
            toReturn.add(3);
            toReturn.add(5);
        }
        return toReturn;
    }
}
