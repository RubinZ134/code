import java.util.ArrayList;
import java.util.Scanner;

public class stretch {
	
	static int r, c, s, n;
	static int[][] grid;
	static ArrayList<String> string = new ArrayList<String>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int m = 0; m < 5; m++) {
			r = in.nextInt();
			c = in.nextInt();
			s = in.nextInt()-1;
			n = in.nextInt();
			grid = new int[r][c];
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					grid[i][j] = i*c + j + 1;
				}
			}
			for(int i = 0; i < n; i++) {
				int blocked = in.nextInt()-1;
				grid[blocked/c][blocked%c] = -1;
			}
			
			if(s%c == 0) recf("", s/c, s%c, false, false);
			else recb("", s/c, s%c, false, false);
		}
		for(int m = 0; m < 5; m++) {
			System.out.println(string.get(m));
		}
		System.exit(0);
	}
	
	public static boolean recf(String path, int row, int col, boolean down, boolean up) {
		if(col==c && !(path.charAt(path.length()-1) == 'B' || path.charAt(path.length()-1) == 'D')) {
			string.add(path);
			return true;
		}
		boolean next = false;
		if(path.length() == 0 || path.charAt(path.length()-1) == 'E') {
			if(goodblock(row, col) && goodblock(row, col+1) && goodblock(row, col+2)) {
				if(recf(path + "A", row, col+3, false, false)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'A')) {
			if(!up && (goodblock(row, col) && goodblock(row-1, col) && goodblock(row-2, col))) {
				if(recf(path + "B", row-2, col+1, true, false)) return true;
				if(recf(path + "B", row, col+1, false, true)) return true;
			}
			if(!down && (goodblock(row, col) && goodblock(row+1, col) && goodblock(row+2, col))) {
				if(recf(path + "B", row+2, col+1, false, true)) return true;
				if(recf(path + "B", row, col+1, true, false)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'B')) {
			if(!down && goodblock(row, col) && goodblock(row+1, col) && goodblock(row+1, col+1)) {
				if(recf(path + "C", row+1, col+2, false, false)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'C') {
			if(goodblock(row, col) && goodblock(row, col+1) && goodblock(row+1, col+1) && goodblock(row+2, col+1)) {
				if(recf(path + "D", row+2, col+2, false, true)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'D') {
			if(goodblock(row, col) && goodblock(row, col+1) && goodblock(row+1, col+1) && goodblock(row+1, col+2)) {
				if(recf(path + "E", row+1, col+3, false, true)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'E') {
			if(goodblock(row, col) && goodblock(row, col+1) && goodblock(row, col+2)) {
				if(recf(path + "A", row, col+3, false, false)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'A')) {
			if(!up && (goodblock(row, col) && goodblock(row-1, col) && goodblock(row-2, col))) {
				if(recf(path + "B", row-2, col+1, true, false)) return true;
				if(recf(path + "B", row, col+1, false, true)) return true;
			}
			if(!down && (goodblock(row, col) && goodblock(row+1, col) && goodblock(row+2, col))) {
				if(recf(path + "B", row+2, col+1, false, true)) return true;
				if(recf(path + "B", row, col+1, true, false)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'B')) {
			if(!down && goodblock(row, col) && goodblock(row+1, col) && goodblock(row+1, col+1)) {
				if(recf(path + "C", row+1, col+2, false, false)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'C' || next) {
			if(goodblock(row, col) && goodblock(row, col+1) && goodblock(row+1, col+1) && goodblock(row+2, col+1)) {
				if(recf(path + "D", row+1, col+2, false, true)) return true;
			}
			next = true;
		}
		
		return false;
	}
	
	public static boolean recb(String path, int row, int col, boolean up, boolean down) {
		if(col==-1 && !(path.charAt(path.length()-1) == 'B' || path.charAt(path.length()-1) == 'C')) {
			string.add(path);
			return true;
		}
		boolean next = false;
		if(path.length() == 0 || path.charAt(path.length()-1) == 'E') {
			if(goodblock(row, col) && goodblock(row, col-1) && goodblock(row, col-2)) {
				if(recb(path + "A", row, col-3, false, false)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'A')) {
			if(!up && (goodblock(row, col) && goodblock(row-1, col) && goodblock(row-2, col))) {
				if(recb(path + "B", row-2, col-1, true, false)) return true;
				if(recb(path + "B", row, col-1, false, true)) return true;
			}
			if(!down && (goodblock(row, col) && goodblock(row+1, col) && goodblock(row+2, col))) {
				if(recb(path + "B", row+2, col-1, false, true)) return true;
				if(recb(path + "B", row, col-1, true, false)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'B') {
			if(goodblock(row, col) && goodblock(row, col-1) && goodblock(row-1, col-1)) {
				if(recb(path + "C", row-1, col-2, false, true)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'C')) {
			if(!up && goodblock(row, col) && goodblock(row-1, col) && goodblock(row-2, col) && goodblock(row-2, col-1)) {
				if(recb(path + "D", row-2, col-2, false, false)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'D') {
			if(goodblock(row, col) && goodblock(row, col-1) && goodblock(row-1, col-1) && goodblock(row-1, col-2)) {
				if(recb(path + "E", row-1, col-3, false, true)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'E') {
			if(goodblock(row, col) && goodblock(row, col-1) && goodblock(row, col-2)) {
				if(recb(path + "A", row, col-3, false, false)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'A')) {
			if(!up && (goodblock(row, col) && goodblock(row-1, col) && goodblock(row-2, col))) {
				if(recb(path + "B", row-2, col-1, true, false)) return true;
				if(recb(path + "B", row, col-1, false, true)) return true;
			}
			if(!down && (goodblock(row, col) && goodblock(row+1, col) && goodblock(row+2, col))) {
				if(recb(path + "B", row+2, col-1, false, true)) return true;
				if(recb(path + "B", row, col-1, true, false)) return true;
			}
			next = true;
		}
		if(next || path.charAt(path.length()-1) == 'B') {
			if(goodblock(row, col) && goodblock(row, col-1) && goodblock(row-1, col-1)) {
				if(recb(path + "C", row-1, col-2, false, true)) return true;
			}
			next = true;
		}
		if(path.length() != 0 && (next || path.charAt(path.length()-1) == 'C')) {
			if(!up && goodblock(row, col) && goodblock(row-1, col) && goodblock(row-2, col) && goodblock(row-2, col-1)) {
				if(recb(path + "D", row-2, col-2, false, false)) return true;
			}
			next = true;
		}
		
		return false;
	}
	
	public static boolean goodblock(int row, int col) {
		return row < r && row >= 0 && col < c && col >= 0 && grid[row][col] != -1;
	}

}
