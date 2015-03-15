package com.zhang3r.yodle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RunMain {
	public static void main(String[] args) {
		// known 12000 jugglers
		// 2000 circuits
		// 6 jugglers per circuit
		Circuit[] cx = new Circuit[2000];
		Juggler[] jugglers = new Juggler[12000];
		try {
			int x = 0;
			int y = 0;
			File file = new File("input.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("C")) {
					// Circuit detects
					String[] tokens = line.split(" ");
					if (tokens.length == 5) {
						Circuit c = new Circuit(tokens[1],
								Integer.valueOf(tokens[2].substring(tokens[2]
										.indexOf(":") + 1)),
								Integer.valueOf(tokens[3].substring(tokens[3]
										.indexOf(":") + 1)),
								Integer.valueOf(tokens[4].substring(tokens[4]
										.indexOf(":") + 1)));
						cx[x] = c;
						x++;
					}
				} else if (line.startsWith("J")) {
					String[] tokens = line.split(" ");
					if (tokens.length == 6) {
						Juggler j = new Juggler(tokens[1],
								Integer.valueOf(tokens[2].substring(tokens[2]
										.indexOf(":") + 1)),
								Integer.valueOf(tokens[3].substring(tokens[3]
										.indexOf(":") + 1)),
								Integer.valueOf(tokens[4].substring(tokens[4]
										.indexOf(":") + 1)));
						String[] preference = tokens[5].split(",");
						j.setPreference(preference);
						jugglers[y] = j;
						y++;
					}
				}
			}
			br.close();

			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Juggler j : jugglers) {
			for (Circuit c : cx) {
				for (String preference : j.getPreference()) {
					if (preference.equals(c.getCircuitName())) {
						double cxj = DotProductUtil.dotProduct(
								c.getCircuitSkill(), j.getSkills());
						j.getScore().put(c.getCircuitName(), (int) cxj);
					}
				}
			}
			allocateJuggler(cx, j, 0);
		}
		// write to file
		try {

			File file = new File("output.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Circuit c : cx) {
				bw.write(c.getCircuitName() + " | ");
				for (Juggler j : c.getJugglers()) {
					bw.write(j.getName() + " : " + j.getScore() + ",");

				}
				bw.newLine();
				bw.write("----------------------");
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done! :)");
		
		
		
	}

	public static void allocateJuggler(Circuit[] circuits, Juggler j,
			int prefCircuit) {
		// determine the preference

		String preference = j.getPreference()[prefCircuit % 10];
		for (Circuit c : circuits) {
			if (c.getCircuitName().equals(preference)) {
				if (c.getJugglers().size() < 6) {
					c.getJugglers().add(j);
					return;
				} else if (j.getScoreFromCircuit(c.getCircuitName()) < c
						.getLowestScoreJuggler().getScoreFromCircuit(
								c.getCircuitName())) {
					// if juggler has no skill for circuit
					// reallocate him to lower preference
					if (prefCircuit < 10) {
						allocateJuggler(circuits, j, prefCircuit + 1);
					} else {
						// we've done a whole loop of preferences and the score
						// is lower than all preferences
						// finding a circuit less than 6 jugglers and assign
						// this juggler

						for (Circuit c1 : circuits) {
							if (c1.getJugglers().size() < 6) {
								j.getPreference()[0] = c1.getCircuitName();
								j.getScore().put(
										c1.getCircuitName(),
										(int) DotProductUtil.dotProduct(
												c1.getCircuitSkill(),
												j.getSkills()));
							}
						}

					}

				} else if (j.getScoreFromCircuit(c.getCircuitName()) > c
						.getLowestScoreJuggler().getScoreFromCircuit(
								c.getCircuitName())) {
					// get lowest scored juggler
					Juggler lowest = c.getLowestScoreJuggler();
					// replace lowest with current juggler
					c.getJugglers().remove(c.getLowestScoreJuggler());
					c.getJugglers().add(j);
					// re-allocate juggler
					allocateJuggler(circuits, lowest, prefCircuit + 1);

				}
			}
		}

	}
}
