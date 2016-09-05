package Idxs;

public interface SentIdx{
	public static final String[] REBELLION = {
										"@제#내@ @맘#마음@대로 @할거거든#할건데@요!",
										"@됐거든요#됐다고요@!",
										"상관하지 말아요!",
										"@뭐야#흥!@",
										"이쪽 보지 마세요",
										"저한테 신경끄세요.",
										"그만 좀 해주실래요",
						};
	
	static class SentMgr{
		public static String getRebRndSent(){
			String[] lineElts = REBELLION[(int) (Math.random() * REBELLION.length)].split("@");
			for (int i = 0; i < lineElts.length; i++) {
				String elt = lineElts[i];
				if (elt.contains("#")) {
					String[] elts = elt.split("#");
					elt = elts[(int) (Math.random() * elts.length)];
				}
				lineElts[i] = elt;
			}
			
			String sent = "";
			for (String str : lineElts)
				sent += str;
			
			return sent;
		}
	}
}
