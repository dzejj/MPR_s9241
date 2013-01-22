package com.pl.services;

import com.pl.biblioteka.*;

public abstract class Condition {

	public abstract boolean getCondition(Book book);

	public abstract boolean getCondition(Student customer);
}