import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import Idxs.ActionIdx;
import Idxs.StatIdx;

public class Main {
	public static void main(String[] args) {
		new Main().main2();
	}
	
	public static final String saveDir = "./save";
	public static final String savePath = saveDir + "/save.txt";
	public static final Scanner sc = new Scanner(System.in);
	
	public void main2() {
		Princess prin = null;
		File saveDirPath = new File(saveDir);
		if(!saveDirPath.exists()) saveDirPath.mkdirs();
		
		boolean isLoad = false;
		if(new File(savePath).exists()) {
			System.out.println("딸이 존재합니다. 불러올까요? 1.ㅇㅇ 2.ㄴㄴ");
			isLoad = (sc.nextInt() == 1) ? true : false;
		}
		
		if (isLoad) {
			HashMap<String, String> map = FileMgr.toMap(FileMgr.loadText(savePath));
			System.out.println("딸을 불러오고 있습니다.");
			Ani.printTyping("↓↙←←←", 500);
			System.out.println();
			prin = new Princess(map);
			prin.printStatAll();
			System.out.println();
			sleep(3000);
		} else {
			prin = new Princess();
			firstUser(prin);
		}
		
		int count = 1;
		while(true){
			System.out.println(count + "번째 동작");
			System.out.println("무엇을 하시겠습니까?");
			
			System.out.println(prin.getActionNameList());
			int select = Integer.parseInt(sc.next());
			if (select > 0) {
				prin.startAction(select);
				sleep(1000);
			}
			
			if(select == 0 || count++ >= 4 || prin.getStatDbl(StatIdx.FATIGUE) >= 70) {
				count = 1;
				System.out.println("일정이 끝났습니다.");
				sleep(2000);
				prin.startAction(ActionIdx.DAYEND);
				System.out.println("딸의 정보를 저장 중 입니다.");
				sleep(1500);
				FileMgr.saveText(savePath, prin.getSaveData());
			}
			System.out.println();
		}
		
		// System.out.println();
		// princess.printStatAll();
		
		// sc.close();
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
		System.out.println("오프닝을 보시겠습니까? 1.ㅇㅇ 2.ㄴㄴ");
		if (sc.nextInt() == 1) openning();
		Ani.printTyping("우리는 대학교 cc로 만나 5년간의 연애 끝에 결혼을 하고 예쁜 딸 아이를 낳았다.\n난 꼭 이 아이를 잘 키워볼 것이야!");
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
	}

	public void sleep(long millis) {
		try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	public static boolean isNum(String str){
		return str.matches("[+-]?\\d*(\\.\\d+)?");
	}
}
