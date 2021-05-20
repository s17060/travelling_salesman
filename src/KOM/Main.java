package KOM;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;
//import org.graphstream.graph.Graph;
//import org.graphstream.graph.implementations.SingleGraph;

public class Main {
	public static Miasto tabiszon[] = new Miasto[0];
	public static List<Droga> lista = new ArrayList<Droga>(); // wszystkie drogi
	public static ArrayList<Droga> suma = new ArrayList<Droga>(); // sciezka
	public static List<Miasto> lista_miast;

	public static void main(String[] args) {

		List<Droga> lista2 = new ArrayList<Droga>(); // kopia
		List<Droga> pom = new ArrayList<Droga>(); // wszytskie drogi o podanych pierwszych wspolrz
		List<Droga> suma = new ArrayList<Droga>(); // sciezka

		// wczytanie
		File plik = new File("miasta.txt");
		Scanner in = null;
		try {
			in = new Scanner(plik);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//podaj ilosc wierzcholkow
		Scanner scan = new Scanner(System.in);
//        System.out.println("Podaj liczbe wierzcholkow:");
//        int lwierz = scan.nextInt();
		int lwierz = 400;
        System.out.println();
        tabiszon = new Miasto[lwierz]; // wspolrzedne miast
        
		// wspolrzedne miast
		for (int i = 0; i < lwierz; i++) {
			tabiszon[i] = new Miasto(in.nextInt(), in.nextInt());
		}
		
//		System.out.println("Wspolrzedne miast: ");
//		// Wypisanie wspolrzednyh miast
//		for (int i = 0; i < lwierz; i++) {
//			System.out.println(i + ". " + tabiszon[i]);
//		}
		
		System.out.println();
		scan = new Scanner(System.in);
        System.out.println("Wskaz pierwszy wierzcholek [0-"+(lwierz-1)+"]:");
        int numwierz = scan.nextInt();

		// STWORZENIE listy wszystkich polaczen
		for (int i = 0; i < lwierz; i++) {
			for (int j = 0; j < lwierz; j++) {
				Droga drog = new Droga(tabiszon[i], tabiszon[j]);
				if (!tabiszon[i].equals(tabiszon[j]))
					lista.add(drog);
			}
		}
		// klon listy
		lista2.addAll(lista);

		// wypisanie wszystkich krawedzi - LISTA
		//for (int i = 0; i < lista.size(); i++) {
		//	System.out.println(i + ". " + lista.get(i));
		//}

		// WYBOR DROGI POCZATEK
		// Wybierz wierzcholek od 0-5
			
		long start=System.currentTimeMillis();
		Miasto tmp = tabiszon[numwierz];
		//System.out.println();
		//System.out.println("START - Algorytm zachlanny:");
		//System.out.println();
		for (int k = 0; k < lwierz-1; k++) {
			for (int i = 0; i < lista.size(); i++) {
				if (tmp.equals(lista.get(i).obiekt1))
					pom.add(lista.get(i));
			}
			Droga zm = pom.get(0);
			for (int i = 1; i < pom.size(); i++) {
				if (zm.dlugosc > pom.get(i).dlugosc)
					zm = pom.get(i);
			}

			//System.out.println(zm);
			for (int i = 0; i < lista.size(); i++) {
				if (zm.obiekt1.equals(lista.get(i).obiekt1) || zm.obiekt1.equals(lista.get(i).obiekt2)) {
					lista.remove(i);
					i--;
				}
			}
			for (int i = 0; i < lista.size(); i++) {
				//System.out.println(i + ". " + lista.get(i));
			}
			tmp = zm.obiekt2;
			pom.clear();
			suma.add(zm);
			//System.out.println();
		}
		
		//dodaje ostatnia droge
		Miasto miasto_poczatkowe = suma.get(0).getMiasto1();
		Miasto miasto_koncowe = suma.get(suma.size() - 1).getMiasto2();
		
		suma.add(new Droga(miasto_koncowe, miasto_poczatkowe));
		
		
		System.out.println();

		// Wypisanie sciezki dla zachlannego
		System.out.println("Algorytm zachlanny:");
		double sumadl = 0;
		for (int i = 0; i < suma.size(); i++) {
			for (int j = 0; j < tabiszon.length; j++) {
//				if (suma.get(i).obiekt1.equals(tabiszon[j]))
//					System.out.println(j + " " + suma.get(i));
			}
			sumadl += suma.get(i).dlugosc;
		}
		double wynik_greedy = Droga.zaokragl(sumadl);
		System.out.println("Suma dlugosci: " + wynik_greedy);
		
		long stop=System.currentTimeMillis();
		
		System.out.println("Czas wykonania (w milisekundach): "+(stop-start));
		
//		for (int i = 0; i < suma.size(); i++) {
//		System.out.println(suma.get(i));
//		}
		System.out.println();
		EventQueue.invokeLater(new Runnable() { 

			@Override
			public void run() {
				new MyFrame(suma);
			}
		});
		
		
		
		
		
		
		//WSPINACZKOWY
		MyFrame2 wspin = new MyFrame2();
		
		System.out.println("Algorytm wspinaczkowy: ");
			start=System.currentTimeMillis();
			lista_miast= new ArrayList<>(); //przechowuje w niej aktualnie najlepsza trase
			
			//tworze losowa trase 
			List<Integer> lista_id_miast = new ArrayList<Integer>();
			for(int i = 0; i<lwierz; i++) {
				lista_id_miast.add(i);
			}
			Collections.shuffle(lista_id_miast);
			for(Integer i: lista_id_miast) {
				lista_miast.add(tabiszon[i]);
			}
			int ilosc_miast = lista_miast.size();
			
			//wyswietlam powstala trase i obliczam jej dlugosc
//			for(Miasto m: lista_miast) { 
//				System.out.println(m);
//			}
			
			double min_dlugosc = dlugoscDrog(lista_miast);
//			System.out.println(min_dlugosc);
			
			//przeprowadzam iteracje algorytmu wspinaczkowego
			List<Miasto> najlepszy_set = new ArrayList<>();
			najlepszy_set.addAll(lista_miast);
			boolean czyPoprawa = false;
			int ilosc_wykonan = 0;
			do {
				
				for(int i = 0; i < ilosc_miast-1; i++) {
					for(int j = i+1; j < ilosc_miast; j++) {
						List<Miasto> temp_miasta = new ArrayList<>();
						temp_miasta.addAll(lista_miast);
						
						Miasto temp = temp_miasta.get(i);
						temp_miasta.set(i, temp_miasta.get(j));
						temp_miasta.set(j, temp);
						
						double dlug = dlugoscDrog(temp_miasta);
						if(dlug < min_dlugosc) {
							najlepszy_set = temp_miasta;
						}
					}
				}
				double stara_dlugosc = dlugoscDrog(lista_miast);
				double nowa_dlugosc = dlugoscDrog(najlepszy_set);
//				System.out.println("Dlugosc najlepszego setu w tej iteracji: " + nowa_dlugosc);
//				drukuj(najlepszy_set);
//				System.out.println(stara_dlugosc + " " +  nowa_dlugosc);
				ilosc_wykonan++;
				if(nowa_dlugosc < stara_dlugosc) {
					lista_miast.clear();
					lista_miast.addAll(najlepszy_set);
					min_dlugosc = dlugoscDrog(lista_miast);
					czyPoprawa = true;
//					System.out.println("tak");
				} else {
					czyPoprawa = false;
//					System.out.println("nie");
		
				}
				wspin.repaint();
			}while(czyPoprawa);
			System.out.println("ilosc iteracji: " + ilosc_wykonan);
				stop=System.currentTimeMillis();
			System.out.println("Czas wykonania (w milisekundach): "+(stop-start));
			System.out.println("Dlugosc wspinaczkowy: " + min_dlugosc);
	
		
		
	}
	

	public static double dlugoscDrog(List<Miasto> list) {
		double wynik = 0;
		for(int i = 1; i< list.size(); i++) {
			Droga d = new Droga(list.get(i-1), list.get(i));
			wynik += d.getdlugosc();
		}
		Droga d = new Droga(list.get(list.size()-1), list.get(0));
		wynik += d.getdlugosc();
		return wynik;
	}
	public static void drukuj(List<Miasto> list) {
		System.out.println("--DRUKUJE MIASTA--");
		for(int i = 0; i< list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}