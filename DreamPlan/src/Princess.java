import java.util.HashMap;

import Idxs.ActionIdx;
import Idxs.StatIdx;


public class Princess implements StatIdx, ActionIdx{
	
	String[][] statList = {
		{ "이름", "name"}, 
		{ "나이", "1"},
		{ "피로도", "0"}, 
		{ "지능", "10"},
		{ "몸무게", "3"}, 
		{ "시력", "3.0"},
		{ "근력", "10"},
		{ "머리길이", "0"}, 
		{ "피부색", "살색"}, 
		{ "키", "50"},
		{ "미모", "0"},
		{ "기분", "0"},
		{ "배부름", "0"},
	};
	
	String[][]	actionList	= { 
		{"수면", "0", "999", "0", "24"},
		{"산책", "0", "999", "0", "24"},
		{"밥 먹기", "0", "999", "0", "24"}, 
		{"낮잠", "0", "999", "0", "24"},
		{"헬스", "8", "999", "0", "24"},
		{"PC방 가기", "8", "999", "0", "24"},
		{"미용실 가기", "8", "999", "0", "24"},
		{"샤워 하기", "0", "999", "0", "24"},
		{"독서실 가기", "0", "999", "0", "24"},
		{"등교", "8", "23", "8", "9"},
		{"학원", "8", "23", "0", "24"},
		{"술 담배", "17", "999", "0", "24"},
		
	};
	
	String[]	skinColorList	= {
		"살색",
		"백인",
		"황인",
		"흑인",
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

	public String getActionName(int idx){
		return actionList[idx][0];
	}
	
	public String getActionNameList(){
		String nameList = "";
		int returnIdx = 1;
		double myAge = Double.parseDouble(statList[StatIdx.AGE][1]);
		double nowTime = Main.nowTime;
		for (int i = 1; i < actionList.length; i++) {
			int actionAgeMin = Integer.parseInt(actionList[i][1]);
			int actionAgeMax = Integer.parseInt(actionList[i][2]);
			
			int actionTimeMin = Integer.parseInt(actionList[i][3]);
			int actionTimeMax = Integer.parseInt(actionList[i][4]);
			
			if ((actionAgeMin <= myAge && myAge <= actionAgeMax)
				&& (actionTimeMin <= nowTime && nowTime <= actionTimeMax))
				nameList += returnIdx++ + "." + actionList[i][0] + ", ";
		}
		nameList = nameList.substring(0, nameList.length() - 2);
		return nameList;
	}
	
	public String getSkinColorNameList(){
		return arrMerge(skinColorList);
	}
	
	private String arrMerge(String[] arr){
		String nameList = "";
		for (int i = 1; i < arr.length; i++)
			nameList += i + "." + arr[i] + " ";
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
		if(!Main.skipMode)
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public void setStat(int idx, String statData) {
		
		switch (idx) {
			case SKINCOLOR :
				statData = (Main.isNum(statData)) ? skinColorList[Integer.parseInt(statData)] : statData;
			break;
		}
		
		statList[idx][1] = statData;
		System.out.println(statList[idx][0] + " 지정 => " + statList[idx][1]);
		sleeper();
	}
	
	public void addStat(int idx, double value) {
		
		double originValue = Double.parseDouble(statList[idx][1]);
		double min = 0, max = 100;
		String print = "";
		String measure = "";
		switch (idx) {
			case INTELLECT : // 10 ~ 100
			case STRENGTH :
				min = 10; max = 999;
			break;
			
			case WET :
				min = 3; max = 300;
				measure = "kg";
			break;
			
			case VISION : // -3.0 ~ 3.0
				int nerfPer = (int)(Math.random() * 10);
				if (nerfPer < 2) value = 0.0; 
				min = -3.0; max = 3.0;
			break;
			
			case HEIGHT :
				min = 50; max = 164;
				measure = "cm";
			break;
			
			case HAIRLENGTH :
				measure = "cm";
			break;
		}
		
		double sumValue = originValue + value;
		
		if (sumValue < min) sumValue = min;
		else if (sumValue > max) sumValue = max;
		
		double diffValue = Main.round2(sumValue - originValue);
		String diffPrint = ((diffValue > 0) ? "+" : "") + diffValue + measure;
		
		statList[idx][1] = String.valueOf(Main.round2(sumValue));
		
		if (print.equals("")) 
			print = statList[idx][0] + " 변화(" + diffPrint + ") => " + statList[idx][1] + measure;
		
		System.out.println(print);
		sleeper();
	}
	
	public int getActionIdx(String actionName) {
		for (int i = 0; i < actionList.length; i++) {
			if (actionList[i][0].equals(actionName))
				return i;
		}
		
		return -1;
	}
	
	public void startAction(String actionName) {
		int idx = getActionIdx(actionName);
		startAction(idx);
	}
	
	public void startAction(int idx) {
		System.out.println("시작합니다 : " + actionList[idx][0]);
		String startMsg = "";
		String endMsg = "";
		switch (idx) {
			case DAYEND :
				startMsgPrint("잘자요!");
				addStat(FATIGUE, -Double.parseDouble(statList[FATIGUE][1]));
				addStat(BEAUTY, +3);
				addStat(FEEL, +20);
				timePass(10);
				endMsg = "잘잤다!";
			break;
						
			case WALK :
				startMsgPrint("*.*.*.*.*.*");
				addStat(FATIGUE, +10);
				addStat(WET, -1);
				addStat(STRENGTH, +2);
				addStat(BEAUTY, +1);
				addStat(FEEL, +10);
				addStat(BELLY, -10);
				timePass(3);
				endMsgPrint("몸무게좀 빠졌나?");
			break;
			
			case RICE :
				startMsgPrint("호로록~");
				addStat(FATIGUE, +10);
				addStat(WET, +2);
				addStat(STRENGTH, +2);
				addStat(BEAUTY, -1);
				addStat(FEEL, +10);
				addStat(BELLY, +30);
				timePass(1);
				endMsgPrint("아.. 다이어트해야 되는데..");
			break;
			
			case NAP :
				startMsgPrint("zzZ");
				addStat(FATIGUE, -40);
				addStat(BEAUTY, +2);
				addStat(FEEL, +10);
				timePass(2);
				endMsgPrint("잘잤다~");
			break;
				
			case HEALTH :
				startMsgPrint("1..2..3..");
				addStat(FATIGUE, +40);
				addStat(WET, -3);
				addStat(STRENGTH, +5);
				addStat(BEAUTY, +1);
				addStat(FEEL, -5);
				addStat(BELLY, -20);
				timePass(1);
				endMsgPrint("왜 이렇게 안빠지지");
			break;
			
			case PCROOM :
				startMsgPrint("옛썰! 지이잉~!");
				addStat(FATIGUE, +40);
				addStat(VISION, -0.01);
				addStat(BEAUTY, -1);
				addStat(FEEL, +20);
				timePass(5);
				endMsgPrint("집중했더니 피곤하네");
			break;
			
			case SALON :
				startMsgPrint("언니 예쁘게 잘라주세요~");
				addStat(FATIGUE, -5);
				addStat(BEAUTY, +2);
				addStat(FEEL, +10);
				addStat(HAIRLENGTH, -20);
				timePass(3);
				endMsgPrint("다신 여기 안와야지");
			break;
			
			case SHOWER :
				startMsgPrint("랄랄라~이쁜거봐ㅋ");
				addStat(FATIGUE, -20);
				addStat(BEAUTY, +1);
				timePass(1);
				endMsgPrint("살이좀 쪗네");
			break;
			
			case READING :
				startMsgPrint("독서중...");
				addStat(FATIGUE, +20);
				addStat(INTELLECT, +10);
				addStat(VISION, -0.01);
				timePass(5);
				endMsgPrint("피곤해");
			break;
			
			case SCHOOL :
				startMsgPrint("학교 가기 싫다.");
				addStat(FATIGUE, +40);
				addStat(INTELLECT, +5);
				timePass(8);
				endMsgPrint("피곤해");
			break;
			
			case ACADEMY :
				startMsgPrint("학원 가기 싫다.");
				addStat(FATIGUE, +20);
				addStat(INTELLECT, +3);
				addStat(FEEL, -10);
				timePass(4);
				endMsgPrint("피곤해");
			break;
			
			case ALCOHOL :
				startMsgPrint("뭘 꼬라봐.");
				addStat(FATIGUE, +10);
				addStat(BEAUTY, -5);
				addStat(FEEL, +10);
				addStat(WET, +3);
				addStat(INTELLECT, -10);
				timePass(1);
				endMsgPrint("중독되는 것 같다.");
			break;
			
		}
	}
	
	public void startMsgPrint(String msg){
		Ani.printTyping(msg, 100);
	}
	
	public void endMsgPrint(String msg){
		Ani.printTyping(msg, 100);
	}
	
	public void timePass(int time) {
		Main.timePass(time);
	}
}
