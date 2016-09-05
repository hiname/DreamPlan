public class Ani{
	public static long	defTypeDelay	= 150;
	
	public static void printTyping(String story){
		printTyping(story, defTypeDelay);
	}
	
	public static void printTyping(String story, long typeDelay){
		String storyList[] = story.split("\n");
		for (String storyEle : storyList) {
			for (int i = 0; i < storyEle.length(); i++) {
				System.out.print(storyEle.charAt(i));
				sleep(typeDelay);
			}
			System.out.println();
			sleep(1000);
		}
	}
	
	public static void sleep(long millis){
		if(!Main.skipMode)
			try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace();}
	}
}
