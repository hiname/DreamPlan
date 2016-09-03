

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileMgr {

	static final boolean	DEFAULT_APPEND	= false;
	static final String	DEFAULT_ENC		= "UTF-8";

	public static void main(String[] args) {
		new FileMgr().main2();
	}

	public static void main2() {
		String path = "c:/temp/temp.txt";
		String loadTextLines[] = loadText(path);
		loadTextLines[1] = "abc";
		saveText(path, loadTextLines);
	}

	public static HashMap<String, String> toMap(String data){
		String spliter = "\n";
		if (data.contains("\r\n")) spliter = "\r\n";
		String textLineList[] = data.split(spliter);
		return toMap(textLineList);
	}
	
	public static HashMap<String, String> toMap(String[] dataList){
		HashMap<String, String> hash = new HashMap<String, String>();
		
		for (String line : dataList) {
			String keyValue[] = line.split(" : ");
			hash.put(keyValue[0], keyValue[1]);
		}
		return hash;
	}
	
	public static String[] loadText(String path) {
		System.out.println("파일 불러오기 : " + path);
		ArrayList<String> loader = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = br.readLine()) != null) {
				loader.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return loader.toArray(new String[loader.size()]);
	}

	public static void saveText(String path, String[] dataList) {
		saveText(path, dataList, DEFAULT_APPEND, DEFAULT_ENC);
	}

	public static void saveText(String path, String data) {
		saveText(path, new String[]{data}, DEFAULT_APPEND, DEFAULT_ENC);
	}

	public static void saveText(String path, String[] dataList, boolean isAppend, String enc) {
		System.out.println("파일 저장 : " + path + " / " + ((isAppend) ? "누적모드" : "덮어쓰기") + " / " + "인코딩 : " + enc);
		try {
			FileOutputStream fos = new FileOutputStream(path, isAppend);
			for (String data : dataList)
				fos.write(data.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
