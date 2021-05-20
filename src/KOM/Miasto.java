package KOM;

public class Miasto {

	int x;
	int y;

	public Miasto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getx() {
		return x;
	}

	void setx(int x) {
		this.x=x;
	}
	
	public int gety() {
		return y;
	}

	void sety(int y) {
		this.y=y;
	}


public String toString() {
	return "["+getx()+","+gety()+"]";
}
}
