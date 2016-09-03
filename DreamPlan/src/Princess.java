import java.util.HashMap;

interface StatIdx {
	public static final int NAME = 0;
	public static final int AGE = 1;
	public static final int FATIGUE = 2;
	public static final int INTELLECT = 3;
	public static final int WET = 4;
	public static final int VISION = 5;
	public static final int STRENGTH = 6;
	public static final int HAIRLENGTH = 7;
	public static final int SKINCOLOR = 8;
	public static final int HEIGHT = 9;
	public static final int BEAUTY = 10;
	public static final int FEEL = 11;
}

interface ActionIdx {
	public static final int DAYEND = 0;
	public static final int WALK = 1;
	public static final int RICE = 2;
	public static final int NAP = 3;
	public static final int HEALTH = 4;
	public static final int PCROOM = 5;
	public static final int SALON = 6;
	public static final int SHOWER = 7;
	public static final int READING = 8;
}

public class Princess implements StatIdx, ActionIdx{
	
	String[][] statList = {
		{ "이름", "name"}, 
		{ "나이", "1"},
		{ "피로도", "20"}, 
		{ "지능", "30"},
		{ "몸무게", "10"}, 
		{ "시력", "1.5"},
		{ "근력", "20"},
		{ "머리길이", "45"}, 
		{ "피부색", "살색"}, 
		{ "키", "90"},
		{ "미모", "50"},
		{ "기분", "50"},
	};
	
	String[]	actionList	= { 
		"수면", 
		"산책", 
		"밥 먹기", 
		"낮잠", 
		"헬스", 
		"PC방 가기", 
		"미용실 가기", 
		"샤워 하기", 
		"독서실 가기"
	};

	public Princess() {
		
	}
	
	public Princess(HashMap<String, String> hash){
		setData(hash);
	}
	
	public Princess(String name, String skinColor){
		setStat(NAME, name);
		setStat(SKINCOLOR, skinColor);
	}

	public String getActionNameList(){
		String nameList = "";
		for (int i = 1; i < actionList.length; i++)
			nameList += i + "." + actionList[i] + " ";
		return nameList;
	}
	
	public Double getStatDbl(int idx){
		return Double.parseDouble(statList[idx][1]);
	}
	
	public String getStatStr(int idx){
		return statList[idx][1];
	}
	
	public String getSaveData(){
		String data = "";
		for (String[] stats : statList) {
			String key = stats[0];
			String value = stats[1];
			data += (key + " : " + value + "\r\n");
		}
		
		return data;
	}
	
	public void setData(HashMap<String, String> hash){
		for (int i = 0; i < statList.length; i++) {
			String key = statList[i][0];
			statList[i][1] = hash.get(key);
		}
	}
	
	public void printStatAll(){
		System.out.println(getSaveData());
	}
	
	public void sleeper(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setStat(int idx, String statData) {
		statList[idx][1] = statData;
		System.out.println(statList[idx][0] + " 지정 => " + statList[idx][1]);
		sleeper();
	}
	
	public void addStat(int idx, double value) {
		double sumData = Double.parseDouble(statList[idx][1]) + value;
		double min = 0, max = 100;
		String print = "";
		switch (idx) {
			case INTELLECT : // 10 ~ 100
			case STRENGTH :
				min = 10; max = 100;
			break;
			
			case VISION : // -2.5 ~ 2.5
				min = -2.5; max = 2.5;
			break;
		}
		
		if (sumData < min) sumData = min;
		else if (sumData > max) sumData = max;
		
		statList[idx][1] = String.valueOf(sumData);
		
		if (print.equals("")) print = statList[idx][0] + " 변화(" + value + ") => " + statList[idx][1];
		
		System.out.println(print);
		sleeper();
	}
		
	public void startAction(int idx) {
		switch (idx) {
			case DAYEND :
				System.out.println();
				System.out.println("오늘 하루도 수고하셨습니다.");
				addStat(FATIGUE, -50);
				addStat(BEAUTY, +3);
				addStat(FEEL, +20);
			break;
						
			case WALK :
				addStat(FATIGUE, +20);
				addStat(WET, -1);
				addStat(STRENGTH, +2);
				addStat(BEAUTY, +1);
				addStat(FEEL, 10);
			break;
			
			case RICE :
				addStat(FATIGUE, +10);
				addStat(WET, +2);
				addStat(STRENGTH, +2);
				addStat(BEAUTY, -1);
				addStat(FEEL, +10);
			break;
			
			case NAP :
				addStat(FATIGUE, -30);
				addStat(BEAUTY, +2);
				addStat(FEEL, +10);
			break;
				
			case HEALTH :
				addStat(FATIGUE, +40);
				addStat(WET, -1);
				addStat(STRENGTH, +5);
				addStat(BEAUTY, +1);
				addStat(FEEL, -5);
			break;
			
			case PCROOM :
				addStat(FATIGUE, +30);
				addStat(VISION, -0.05);
				addStat(BEAUTY, -1);
			break;
			
			case SALON :
				addStat(FATIGUE, -5);
				addStat(BEAUTY, +2);
				addStat(FEEL, +10);
			break;
			
			case SHOWER :
				addStat(FATIGUE, -10);
				addStat(BEAUTY, +1);
			break;
			
			case READING :
				addStat(FATIGUE, -10);
				addStat(VISION, -0.05);
			break;		
		}
	}
}
