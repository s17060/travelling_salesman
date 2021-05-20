package KOM;

public class Droga {
	Miasto obiekt1;
	double dlugosc;
	Miasto obiekt2;

	public Droga(Miasto obiekt1, Miasto obiekt2) {
		this.obiekt1 = obiekt1;
		this.obiekt2 = obiekt2;
		this.dlugosc = euklides(obiekt1.getx(), obiekt1.gety(), obiekt2.getx(), obiekt2.gety());
	}

	Miasto getMiasto1() {
		return obiekt1;
	}

	Miasto getMiasto2() {
		return obiekt2;
	}

	double getdlugosc() {
		return dlugosc;
	}

	void setMiasto1(Miasto obiekt1) {
		this.obiekt1 = obiekt1;
	}

	void setMiasto2(Miasto obiekt2) {
		this.obiekt2 = obiekt2;
	}

	void setdlugosc(int dlugosc) {
		this.dlugosc = dlugosc;
	}

	static double euklides(int x1, int y1, int x2, int y2) {
		double odl = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		return zaokragl(odl);
	}

	static double zaokragl(double odl) {
		int zaokr = (int) Math.pow(10, 3);
		odl *= zaokr;
		odl = Math.round(odl);
		odl /= zaokr;
		return odl;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getMiasto1() + " " + getdlugosc() + " " + getMiasto2();
	}
}
