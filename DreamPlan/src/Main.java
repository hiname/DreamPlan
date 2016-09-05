import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import Idxs.ActionIdx;
import Idxs.StatIdx;

public class Main {
	public static void main(String[] args) {
		new Main().main2();
	}
	
	// public static final String saveDir = "../../save";
	public static final String saveDir = "./save";
	public static final String savePath = saveDir + "/save.txt";
	public static final Scanner sc = new Scanner(System.in);
	public static int nowTime = 8;
	public static int day = 1;
	public static Princess prin = null;
	public static boolean afterDay = false;
	public static int addDay = 0;
	public static final String nowTimeKey = "현재시간";
	public static final String dayKey = "현재날짜";
	public static boolean sleepWait = false;
	public static boolean skipMode = false;
	
	public void main2() {
		System.out.println("스킵 모드 활성화? 1.네 2.아니오");
		skipMode = (sc.nextInt() == 1) ? true : false;
		
		File saveDirPath = new File(saveDir);
		if(!saveDirPath.exists()) saveDirPath.mkdirs();
		
		boolean isLoad = false;
		if(new File(savePath).exists()) {
			System.out.println("딸이 존재합니다. 불러올까요? 1.네 2.아니오");
			isLoad = (sc.nextInt() == 1) ? true : false;
		}
		
		if (isLoad) {
			load();
		} else {
			prin = new Princess();
			firstUser(prin);
		}
		
		int count = 0;
		while(true){
			count++;
			System.out.println("\n◎ 현재 " + nowTime + "시 ◎ ♠ 나이 " + prin.getStatStr(StatIdx.AGE) + "살 ♠ ▶ " + day + "일 ◀");
			
			int selectIdx = -1;
			String selectAction = "";
			
			if (22 <= nowTime || nowTime <= 7) sleepWait = true;
			
			if (sleepWait) { // 강제 하루 종료
				selectAction = prin.getActionName(ActionIdx.DAYEND);
				sleepWait = false;
			} else if (prin.getStatDbl(StatIdx.FATIGUE) >= 100) { // 강제 낮잠
				selectAction = prin.getActionName(ActionIdx.NAP);
			} else { // 사용자 선택
				System.out.println(count + "번째 동작. 무엇을 할까요?");
				String listActions = prin.getActionNameList();
				System.out.println(listActions);
				selectIdx = Integer.parseInt(sc.next());
				selectAction = listActions.split(", ")[selectIdx - 1];
				selectAction = selectAction.substring(selectAction.indexOf(".") + 1);
			}

			prin.startAction(selectAction);
			sleep(1000);
			System.out.println();
			
			if (afterDay) {
				nextDay();
				save();
				afterDay = false;
				count = 0;
			}
		}
		
		// System.out.println();
		// princess.printStatAll();
		
		// sc.close();
	}
	
	public static void nextDay(){
		day += addDay;
		if (addDay == 1)
			Ani.printTyping("하루가 지났습니다.");
		else
			Ani.printTyping(addDay + "일이 지났습니다.");
		
		prin.addStat(StatIdx.HAIRLENGTH, +2);
		prin.addStat(StatIdx.HEIGHT, +1.08);
		prin.addStat(StatIdx.WET, +2);
		
		if ((int)((day % 7.0)) == 0) {
			int addAge = (int)((day / 7.0) + 1.0);
			prin.setStat(StatIdx.AGE, String.valueOf(addAge));
			prin.printStatAll();
		} 
	}
	
	public static void save(){
		System.out.println("딸의 정보를 저장 중 입니다.");
		sleep(1500);
		FileMgr.saveText(
				savePath, prin.getSaveData() 
				+ nowTimeKey + " : " + nowTime + "\r\n"
				+ dayKey + " : " + day + "\r\n" 
				);
	}
	
	public static void load(){
		HashMap<String, String> map = FileMgr.toMap(FileMgr.loadText(savePath));
		System.out.println("딸을 불러오고 있습니다.");
		Ani.printTyping("↓↙←←←", 250);
		System.out.println();
		prin = new Princess(map);
		prin.printStatAll();
		
		nowTime = Integer.parseInt(map.get(nowTimeKey));
		System.out.println(nowTimeKey + " " + nowTime + "시");
		
		day = Integer.parseInt(map.get(dayKey));
		
		sleep(3000);
	}
	
	public void openning(){
		Ani.printTyping(". . . . . . ", 500);
		Ani.printTyping(". . . . ", 250);
		Ani.printTyping(". . . . . (똑 똑 똑)", 250);
		Ani.printTyping(". . . . .", 250);
		Ani.printTyping("(똑똑똑똑)", 200);
		Ani.printTyping("(잠을 자던 나는 노크 소리에 잠이 깼다.)", 250);
		Ani.printTyping("나 : 음? 누가 왔나?", 250);
		Ani.printTyping("(없는 척 하려고 했는데 문 열리는 소리가 났다.)", 250);
		Ani.printTyping("(어.... 어? 뭐 뭐야? 도..도둑!?)", 150);
		Ani.printTyping("(노크하는 도둑이라니!? 하여튼 진정하고 천천히 문을 열어봤다.)", 250);
		Ani.printTyping("나 : (흠.. 딱히 인기척은 없는데.. 어떻게 된거지.. 라고 생각할 찰나. . . !)", 250);
		Ani.printTyping("(응애- 응애→ 응애↗↗↗↗↗)", 150);
		Ani.printTyping("나 : 어..? 뭐야? 왜 애 우는 소리가 집안에서?", 250);
		Ani.printTyping("(소리는 분명히 현관쪽에서 나는 것 같았다.)", 250);
		Ani.printTyping("(응애↓ 응애↑ 응애↗↗↑↑↑)", 150);
		Ani.printTyping("(아.. 일단 확인을 해봐야 할 것 같아서 천천히 현관쪽으로 나갔다.)", 250);
		Ani.printTyping("(응애↘ 응애→ 응애☆★○●◎)", 150);
		Ani.printTyping("(아 이게 자다말고 뭐하는 짓이여;;)", 150);
		Ani.printTyping("(소리가 나는 곳에는 바구니가 있었고 그 안에는 아이가 한명 있었다.)", 250);
		Ani.printTyping("(어.. 이 아이는..)");
		Ani.printTyping("회상 > > >");
	}
	
	public void firstUser(Princess prin){
		// 전체적인 게임스토리 : 딸아이를 키워 결혼까지 시키기 엔딩크레딧-
		System.out.println("오프닝을 보시겠습니까? 1.네 2.아니오");
		if (sc.nextInt() == 1) openning();
		Ani.printTyping("우리는 대학교 cc로 만나 5년간의 연애 끝에\n결혼을 하고 예쁜 딸 아이를 낳고 부인은 생을마감했다.\n나에게 남겨진 딸 아이.. 난 꼭 이 아이를 잘 키워볼 것이야!!");
		sleep(1000);
		System.out.print("아이의 이름이 뭔가요? ");
		prin.setStat(StatIdx.NAME, sc.next());
		System.out.print("아이의 피부는 어떤색 인가요? " + prin.getSkinColorNameList());
		prin.setStat(StatIdx.SKINCOLOR, sc.next());
		int tmpCount = (int) (Math.random() * 5) + 3;
		while (tmpCount-- > 0) {
			System.out.print(". ");
			sleep(1000);
		}
		System.out.println("! ");
		sleep(1500);
		System.out.println("딸이 태어났습니다!");
		sleep(1000);
		prin.printStatAll();
		
		/*
		 * -배우자선택확률
			등교횟수가 몇 회 이상이면 선생님
			지능값이 높으면 교수님
			몸무게가 높으면 샐러리맨
			근력이 높으면 운동선수
			미모가 높으면 재벌2세
			미모가 낮으면 결혼에 실패
			몸무게가 낮으면 펀드매니져
			지능값이 낮으면 공사장인부
		*/
	}

	public static void timePass(int time) {
		nowTime += time;
		
		int addDay = nowTime / 24;
		if (addDay > 0) { 
			afterDay = true;
			Main.addDay = addDay;
		}
		nowTime %= 24;
		
		Ani.printTyping(time + "시간이 지났습니다.");
	}
	
	public static void sleep(long millis) {
		if(!Main.skipMode)
			try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	public static boolean isNum(String str){
		return str.matches("[+-]?\\d*(\\.\\d+)?");
	}

	public static double round2(double value){
		return Math.round(value * 100d) / 100d;
	}
}
