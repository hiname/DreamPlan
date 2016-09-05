import java.util.HashMap;

import Idxs.ActionIdx;
import Idxs.StatIdx;


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
		"독서실 가기",
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

	public String getActionNameList(){
		return arrMerge(actionList);
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
		switch (idx) {
			case INTELLECT : // 10 ~ 100
			case STRENGTH :
				min = 10; max = 100;
			break;
			
			case WET :
				min = 3; max = 300;
			break;
			
			case VISION : // -2.5 ~ 2.5
				min = -2.5; max = 2.5;
			break;
		}
		
		double sumValue = originValue + value;
		
		if (sumValue < min) sumValue = min;
		else if (sumValue > max) sumValue = max;
		
		double diffValue = sumValue - originValue;
		String diffPrint = ((diffValue > 0) ? "+" : "") + diffValue;
		
		statList[idx][1] = String.valueOf(sumValue);
		
		if (print.equals("")) 
			print = statList[idx][0] + " 변화(" + diffPrint + ") => " + statList[idx][1];
		
		System.out.println(print);
		sleeper();
	}
		
	public void startAction(int idx) {
		System.out.println("시작합니다 : " + actionList[idx]);
		String startMsg = "";
		String endMsg = "";
		switch (idx) {
			case DAYEND :
				startMsgPrint("잘자요!");
				addStat(FATIGUE, -50);
				addStat(BEAUTY, +3);
				addStat(FEEL, +20);
				endMsg = "잘잤다!";
			break;
						
			case WALK :
				startMsgPrint("나가야지!");
				addStat(FATIGUE, +20);
				addStat(WET, -1);
				addStat(STRENGTH, +2);
				addStat(BEAUTY, +1);
				addStat(FEEL, 10);
				endMsgPrint("다녀왔습니다!");
			break;
			
			case RICE :
				startMsgPrint("냠냠");
				addStat(FATIGUE, +10);
				addStat(WET, +2);
				addStat(STRENGTH, +2);
				addStat(BEAUTY, -1);
				addStat(FEEL, +10);
				endMsgPrint("배부르다..");
			break;
			
			case NAP :
				startMsgPrint("꾸벅..꾸벅..");
				addStat(FATIGUE, -30);
				addStat(BEAUTY, +2);
				addStat(FEEL, +10);
				endMsgPrint("음??");
			break;
				
			case HEALTH :
				startMsgPrint("헛둘헛둘");
				addStat(FATIGUE, +40);
				addStat(WET, -1);
				addStat(STRENGTH, +5);
				addStat(BEAUTY, +1);
				addStat(FEEL, -5);
				endMsgPrint("휴 덥다");
			break;
			
			case PCROOM :
				startMsgPrint("컴퓨터??");
				addStat(FATIGUE, +30);
				addStat(VISION, -0.05);
				addStat(BEAUTY, -1);
				endMsgPrint("왜 간거지?");
			break;
			
			case SALON :
				startMsgPrint("미용실!");
				addStat(FATIGUE, -5);
				addStat(BEAUTY, +2);
				addStat(FEEL, +10);
				endMsgPrint("나 이뻐요??");
			break;
			
			case SHOWER :
				startMsgPrint("(쏴아아아)");
				addStat(FATIGUE, -10);
				addStat(BEAUTY, +1);
				endMsgPrint("(나른해)");
			break;
			
			case READING :
				startMsgPrint("쉬잇!");
				addStat(FATIGUE, -10);
				addStat(VISION, -0.05);
				endMsgPrint("책이 좋아!");
			break;		
		}
	}
	
	public void startMsgPrint(String msg){
		Ani.printTyping(msg, 100);
	}
	
	public void endMsgPrint(String msg){
		Ani.printTyping(msg, 100);
	}
}
