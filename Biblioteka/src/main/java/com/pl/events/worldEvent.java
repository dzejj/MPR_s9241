package com.pl.events;

import com.pl.biblioteka.*;

public class worldEvent {

	private Book ksiazka;
	
	public void WorldEvent(Object source, Book ksiazka) {
		this.ksiazka = ksiazka;
		
	}

	public Book getKsiazka() {
		return ksiazka;
	}

	public void setKsiazka(Book ksiazka) {
		this.ksiazka = ksiazka;
	}
}
