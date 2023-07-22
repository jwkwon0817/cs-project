package me.jwkwon0817.cs.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VariantFilter {
	
	private static final String KEY_LIST_DIR = "/Users/jwkwon0817/IdeaProjects/cs/src/main/resources/datas/gene_list.txt";
	
	private static List<String> getKeyList(String keyListDir) {
		File keyListFile = new File(keyListDir);
		
		BufferedReader dataReader;
		
		try {
			dataReader = new BufferedReader(new FileReader(keyListFile));
			String line;
			List<String> keyList = new ArrayList<>();
			
			while ((line = dataReader.readLine()) != null) {
				keyList.add(line);
			}
			
			return keyList;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String grepTsvData(String dataDir) {
		List<String> keyList = getKeyList(KEY_LIST_DIR);
		
		StringBuilder condition = new StringBuilder();
		condition.append("(");
		for (int i = 0; i < keyList.size(); i++) {
			condition.append("$gene_column == \"").append(keyList.get(i)).append("\"");
			if (i != keyList.size() - 1) {
				condition.append(" || ");
			}
		}
		condition.append(")");
		
		String awk = "awk -F'\\t' 'NR == 1 { for (i=1; i<=NF; i++) { if ($i == \"GENE\") gene_column = i; if ($i == \"GT\") gt_column = i } }\n" +
			"           gene_column && gt_column && " + condition + " && ($gt_column == \"0/1\" || $gt_column == \"1/1\") { print }' " + dataDir;
		
		String[] command = { "/bin/zsh", "-c", awk };
		
		return runCommand(command);
	}
	
	private static String runCommand(String[] command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder();
			
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			return sb.toString();
		} catch (IOException ex) {
			return null;
		}
	}
}
