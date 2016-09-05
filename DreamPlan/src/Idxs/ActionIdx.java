package Idxs;
public interface ActionIdx{
	public static final int	DAYEND	= 0;
	public static final int	WALK	= 1;
	public static final int	RICE	= 2;
	public static final int	NAP		= 3;
	public static final int	HEALTH	= 4;
	public static final int	PCROOM	= 5;
	public static final int	SALON	= 6;
	public static final int	SHOWER	= 7;
	public static final int	READING	= 8;
	public static final int	SCHOOL	= 9;
	public static final int	ACADEMY	= 10;
	public static final int	ALCOHOL	= 11;
	public static final int	EYEREST	= 12;

	String[][]	actionList	= { // 명칭, 나이 최소-대 , 시간 시작-끝 ,
							 	 {"수면",    "0", "999",  "0", "24"},
								 {"산책",    "0", "999",  "0", "24"},
								 {"밥",      "0", "999",  "0", "24"}, 
								 {"낮잠",    "0", "999",  "0", "24"},
								 {"헬스",    "8", "999",  "0", "24"},
								 {"PC방",    "8", "999",  "0", "24"},
								 {"미용실",  "8", "999",  "0", "24"},
								 {"샤워",    "0", "999",  "0", "24"},
								 {"독서실",  "0", "999",  "0", "24"},
								 {"등교",    "8",  "23",  "8",  "9"},
								 {"학원",    "8",  "23",  "0", "24"},
								 {"술담배", "17", "999",  "0", "24"},
								 {"눈운동",  "0", "999",  "0", "24"},								 
							};
}
